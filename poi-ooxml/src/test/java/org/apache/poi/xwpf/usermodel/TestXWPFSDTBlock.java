package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.XWPFTestDataSamples;
import org.apache.xmlbeans.XmlCursor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for manipulation of block level Content Controls.
 * Related classes are:
 *      {@link XWPFSDTBlock}, {@link XWPFSDTPr}, {@link XWPFSDTContentBlock}
 */
public final class TestXWPFSDTBlock {

    /**
     * Verify that Sdt Block Pr is added to Sdt Block
     * and the related object references were updated
     */
    @Test
    public void testCreateSdtBlockPr() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTBlock sdtBlock = document.createSdt();

            try (XmlCursor cursor = document.getDocument().newCursor()) {
                cursor.toFirstChild(); // move cursor to Body
                cursor.toFirstChild(); // move cursor to SDT
                Assertions.assertTrue(cursor.getObject() instanceof CTSdtBlock);

                sdtBlock.createSdtPr();

                cursor.toFirstChild();
                Assertions.assertTrue(cursor.getObject() instanceof CTSdtPr);
            }
        }
    }

    /**
     * Verify that Sdt Block Content is added to Sdt Block
     * and the related object references were updated
     */
    @Test
    public void testCreateSdtContentBlock() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTBlock sdtBlock = document.createSdt();

            try (XmlCursor cursor = document.getDocument().newCursor()) {
                cursor.toFirstChild(); // move cursor to Body
                cursor.toFirstChild(); // move cursor to SDT
                Assertions.assertTrue(cursor.getObject() instanceof CTSdtBlock);

                sdtBlock.createSdtContent();

                cursor.toFirstChild();
                Assertions.assertTrue(cursor.getObject() instanceof CTSdtContentBlock);
            }
        }
    }

    @Test
    public void testGetParagraphFromSdtBlockContent() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("blockAndInlineSdtTags.docx")) {
            XWPFSDTBlock sdtBlock = (XWPFSDTBlock) document.getBodyElements().get(2);

            CTP ctp = sdtBlock.getContent().getParagraphs().get(0).getCTP();
            assertSame(sdtBlock.getContent().getParagraphs().get(0), sdtBlock.getContent().getParagraph(ctp));
        }
    }

    @Test
    public void testInsertNewParagraphToSdtBlockContent() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("blockAndInlineSdtTags.docx")) {
            XWPFSDTBlock sdtBlock = (XWPFSDTBlock) document.getBodyElements().get(2);

            XWPFParagraph newParagraph;
            try (XmlCursor cursor = sdtBlock.getContent().getCtSdtContentBlock().newCursor()) {
                cursor.toFirstChild(); // move cursor to Tbl
                cursor.toEndToken(); // move cursor to the end of Tbl
                cursor.toNextToken(); // move cursor right after the Tbl

                assertEquals(1, sdtBlock.getContent().getParagraphs().size());

                newParagraph = sdtBlock.getContent().insertNewParagraph(cursor);
            }

            assertEquals(2, sdtBlock.getContent().getParagraphs().size());
            assertEquals(3, sdtBlock.getContent().getBodyElements().size());
            assertEquals(0, sdtBlock.getContent().getSdtBlocks().size());
            assertSame(newParagraph, sdtBlock.getContent().getParagraphs().get(0));
        }
    }

    @Test
    public void testInsertSdtBlockInDocument() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            // create few elements in body
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.createRun().setText("Text in first paragraph");
            document.createTable().createRow().createCell().addParagraph().createRun().setText("Text in Tbl cell");

            try (XmlCursor cursor = paragraph.getCTP().newCursor()) {
                cursor.toEndToken();
                cursor.toNextToken(); // move cursor right after the Paragraph

                document.insertNewSdtBlock(cursor);

                assertEquals(3, document.getBodyElements().size());
                assertEquals(1, document.getSdtBlocks().size());

                try (XmlCursor newCursor = paragraph.getCTP().newCursor()) {
                    newCursor.toEndToken();
                    newCursor.toNextToken();

                    // verify that Sdt Block is inserted
                    Assertions.assertTrue(newCursor.getObject() instanceof CTSdtBlock);
                }
            }
        }
    }

    @Test
    public void testInsertExistingParagraphToSdtContentBlock() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            document.createParagraph().createRun().setText("Some text1");
            XWPFSDTBlock sdtBlock = document.createSdt();
            XWPFSDTContentBlock sdtBlockContent = sdtBlock.createSdtContent();
            sdtBlockContent.cloneExistingIBodyElement(document.getParagraphs().get(0));

            assertEquals("Some text1", sdtBlockContent.getParagraphs().get(0).getText());
            assertEquals(1, sdtBlockContent.getParagraphs().size());
            assertEquals(1, sdtBlockContent.getBodyElements().size());
            assertEquals(0, sdtBlockContent.getSdtBlocks().size());
        }
    }

    @Test
    public void testInsertExistingTblToSdtContentBlock() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            document.createTable().createRow().createCell().addParagraph().createRun().setText("Deep in Tbl");
            XWPFSDTBlock sdtBlock = document.createSdt();
            XWPFSDTContentBlock sdtBlockContent = sdtBlock.createSdtContent();
            sdtBlockContent.cloneExistingIBodyElement(document.getTables().get(0));

            assertEquals("Deep in Tbl", sdtBlockContent.getTables().get(0).getText().trim());
            assertEquals(1, sdtBlockContent.getTables().size());
            assertEquals(1, sdtBlockContent.getBodyElements().size());
            assertEquals(0, sdtBlockContent.getSdtBlocks().size());
        }
    }

    @Test
    public void testInsertSdtIntoSdt() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTBlock dstSdtBlock = document.createSdt();
            XWPFSDTContentBlock dstSdtBlockContent = dstSdtBlock.createSdtContent();
            dstSdtBlockContent.createParagraph().createRun().setText("t1");
            dstSdtBlockContent.createTable().getRow(0).getCell(0).setText("t2");
            dstSdtBlockContent.createParagraph().createRun().setText("t3");
            dstSdtBlockContent.createSdt().createSdtContent().createParagraph().createRun().setText("t4");

            XWPFSDTBlock srcSdtBlock = document.createSdt();
            XWPFSDTContentBlock srcSdtBlockContent = srcSdtBlock.createSdtContent();
            srcSdtBlockContent.createTable().getRow(0).getCell(0).setText("t5");
            srcSdtBlockContent.createParagraph().createRun().setText("t6");
            srcSdtBlockContent.createSdt().createSdtContent().createParagraph().createRun().setText("t7");

            dstSdtBlockContent.cloneExistingIBodyElement(srcSdtBlock);

            assertEquals(2, document.getBodyElements().size());
            assertEquals(2, document.getSdtBlocks().size());

            XWPFSDTContentBlock sdtBlock1Content = document.getSdtBlocks().get(0).getContent();
            assertEquals(5, sdtBlock1Content.getBodyElements().size());
            assertEquals(2, sdtBlock1Content.getParagraphs().size());
            assertEquals(1, sdtBlock1Content.getTables().size());
            assertEquals(2, sdtBlock1Content.getSdtBlocks().size());
            assertEquals("t1", ((XWPFParagraph) sdtBlock1Content.getBodyElements().get(0)).getText());
            assertEquals("t2", ((XWPFTable) sdtBlock1Content.getBodyElements().get(1)).getRow(0).getCell(0).getText());
            assertEquals("t3", ((XWPFParagraph) sdtBlock1Content.getBodyElements().get(2)).getText());
            assertEquals("t4", ((XWPFSDTBlock) sdtBlock1Content.getBodyElements().get(3)).getContent().getText());

            XWPFSDTContentBlock innerSdtBlockContent = ((XWPFSDTBlock) sdtBlock1Content.getBodyElements().get(4)).getContent();
            assertEquals(3, innerSdtBlockContent.getBodyElements().size());
            assertEquals(1, innerSdtBlockContent.getParagraphs().size());
            assertEquals(1, innerSdtBlockContent.getTables().size());
            assertEquals(1, innerSdtBlockContent.getSdtBlocks().size());
            assertEquals("t5", ((XWPFTable) innerSdtBlockContent.getBodyElements().get(0)).getRow(0).getCell(0).getText());
            assertEquals("t6", ((XWPFParagraph) innerSdtBlockContent.getBodyElements().get(1)).getText());
            assertEquals("t7", ((XWPFSDTBlock) innerSdtBlockContent.getBodyElements().get(2)).getContent().getText());

            XWPFSDTContentBlock sdtBlock2Content = document.getSdtBlocks().get(1).getContent();
            assertEquals(3, sdtBlock2Content.getBodyElements().size());
            assertEquals(1, sdtBlock2Content.getParagraphs().size());
            assertEquals(1, sdtBlock2Content.getTables().size());
            assertEquals(1, sdtBlock2Content.getSdtBlocks().size());
            assertEquals("t5", ((XWPFTable) sdtBlock2Content.getBodyElements().get(0)).getRow(0).getCell(0).getText());
            assertEquals("t6", ((XWPFParagraph) sdtBlock2Content.getBodyElements().get(1)).getText());
            assertEquals("t7", ((XWPFSDTBlock) sdtBlock2Content.getBodyElements().get(2)).getContent().getText());
        }
    }

    @Test
    public void testInsertNewTblToSdtBlockContent() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("blockAndInlineSdtTags.docx")) {
            XWPFSDTBlock sdtBlock = (XWPFSDTBlock) document.getBodyElements().get(2);

            XWPFTable newTable;
            try (XmlCursor cursor = sdtBlock.getContent().getCtSdtContentBlock().newCursor()) {
                cursor.toFirstChild(); // move cursor to Tbl
                cursor.toEndToken(); // move cursor to the end of Tbl
                cursor.toNextToken(); // move cursor fight after the Tbl

                assertEquals(1, sdtBlock.getContent().getTables().size());

                newTable = sdtBlock.getContent().insertNewTbl(cursor);
            }

            assertEquals(2, sdtBlock.getContent().getTables().size());
            assertEquals(3, sdtBlock.getContent().getBodyElements().size());
            assertEquals(0, sdtBlock.getContent().getSdtBlocks().size());
            assertSame(newTable, sdtBlock.getContent().getTables().get(1));
        }
    }

    /**
     * Verify that existing Content Control in document is correctly
     * unmarshalled & we can proceed with modifying its content
     */
    @Test
    public void testUnmarshallingSdtBlock() throws Exception {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("blockAndInlineSdtTags.docx")) {
            XWPFSDTBlock sdtBlock = (XWPFSDTBlock) document.getBodyElements().get(2);

            // Tag
            assertEquals("block-sdt-tag", sdtBlock.getSdtPr().getTag());

            sdtBlock.getSdtPr().setTag("new-block-tag");
            assertEquals("new-block-tag", sdtBlock.getSdtPr().getTag());

            // Title
            assertEquals("block-sdt-title", sdtBlock.getSdtPr().getTitle());

            sdtBlock.getSdtPr().setTitle("new-block-title");
            assertEquals("new-block-title", sdtBlock.getSdtPr().getTitle());

            // Lock
            assertEquals(STLock.Enum.forInt(STLock.INT_SDT_CONTENT_LOCKED), sdtBlock.getSdtPr().getLock());

            sdtBlock.getSdtPr().setLock(STLock.UNLOCKED);
            assertEquals(STLock.UNLOCKED, sdtBlock.getSdtPr().getLock());

            // SdtContent
            assertEquals(
                    "Some content1",
                    sdtBlock.getContent()
                            .getTables()
                            .get(0)
                            .getRows()
                            .get(0)
                            .getCell(0)
                            .getText()
            );
        }
    }

    @Test
    public void testNestedSdtBlock() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTContentBlock sdtContent1 = document.createSdt().createSdtContent();
            sdtContent1.createParagraph();
            sdtContent1.createTable();
            sdtContent1.createTable();
            sdtContent1.createTable();

            XWPFSDTContentBlock sdtContent2 = sdtContent1.createSdt().createSdtContent();
            XWPFSDTContentBlock sdtContent3 = sdtContent1.createSdt().createSdtContent();

            sdtContent2.createSdt().createSdtContent();
            sdtContent2.createParagraph();
            sdtContent2.createParagraph();

            sdtContent3.createTable();
            sdtContent3.createTable();
            sdtContent3.createTable();

            assertEquals(1, document.getBodyElements().size());
            assertEquals(0, document.getParagraphs().size());
            assertEquals(0, document.getTables().size());
            assertEquals(1, document.getSdtBlocks().size());

            XWPFSDTContentBlock actual = document.getSdtBlocks().get(0)
                    .getContent();
            assertEquals(6, actual.getBodyElements().size());
            assertEquals(1, actual.getParagraphs().size());
            assertEquals(3, actual.getTables().size());
            assertEquals(2, actual.getSdtBlocks().size());

            actual = document.getSdtBlocks().get(0)
                    .getContent()
                    .getSdtBlocks()
                    .get(0)
                    .getContent();
            assertEquals(3, actual.getBodyElements().size());
            assertEquals(2, actual.getParagraphs().size());
            assertEquals(0, actual.getTables().size());
            assertEquals(1, actual.getSdtBlocks().size());

            actual = document.getSdtBlocks().get(0)
                    .getContent()
                    .getSdtBlocks()
                    .get(1)
                    .getContent();

            assertEquals(3, actual.getBodyElements().size());
            assertEquals(0, actual.getParagraphs().size());
            assertEquals(3, actual.getTables().size());
            assertEquals(0, actual.getSdtBlocks().size());
        }
    }
}
