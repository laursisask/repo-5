package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.XWPFTestDataSamples;
import org.apache.xmlbeans.XmlCursor;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLock;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for manipulation of inline Content Controls.
 * Related classes are:
 *      {@link XWPFSDTRun}, {@link XWPFSDTPr}, {@link XWPFSDTContentRun}
 */
public final class TestXWPFSDTRun {
    @Test
    void testInsertNewRuns() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTContentRun sdtContent = paragraph.createSdtRun().createSdtContent();
            XWPFRun run = sdtContent.createRun();
            assertEquals(1, sdtContent.getRuns().size());
            assertEquals(0, sdtContent.getRuns().indexOf(run));

            XWPFHyperlinkRun hyperlinkRun = sdtContent.insertNewHyperlinkRun(0, "http://poi.apache.org");
            assertEquals(2, sdtContent.getRuns().size());
            assertEquals(0, sdtContent.getRuns().indexOf(hyperlinkRun));
            assertEquals(1, sdtContent.getRuns().indexOf(run));

            XWPFFieldRun fieldRun = sdtContent.insertNewFieldRun(2);
            assertEquals(3, sdtContent.getRuns().size());
            assertEquals(2, sdtContent.getRuns().indexOf(fieldRun));
        }
    }

    @Test
    void testCreateNewRuns() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTContentRun sdtContent = paragraph.createSdtRun().createSdtContent();
            XWPFRun run = sdtContent.createRun();
            assertEquals(1, sdtContent.getRuns().size());
            assertEquals(0, sdtContent.getRuns().indexOf(run));

            XWPFHyperlinkRun hyperlinkRun = sdtContent.createHyperlinkRun("http://poi.apache.org");
            assertEquals(2, sdtContent.getRuns().size());
            assertEquals(0, sdtContent.getRuns().indexOf(run));
            assertEquals(1, sdtContent.getRuns().indexOf(hyperlinkRun));

            XWPFFieldRun fieldRun = sdtContent.createFieldRun();
            assertEquals(3, sdtContent.getRuns().size());
            assertEquals(2, sdtContent.getRuns().indexOf(fieldRun));
        }
    }

    /**
     * Verify that Sdt Run Pr is added to Sdt Run
     * and the related object references were updated
     */
    @Test
    public void testSdtRunCreateSdtPr() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTRun sdtRun = document.createParagraph().createSdtRun();
            XWPFSDTPr sdtPr = sdtRun.createSdtPr();

            try (XmlCursor cursor = sdtRun.getCtSdtRun().newCursor()) {
                cursor.toFirstChild();

                assertEquals(sdtPr.getSdtPr(), cursor.getObject());
            }
        }
    }

    /**
     * Verify that Sdt Run Content is added to Sdt Run
     * and the related object references were updated
     */
    @Test
    public void testSdtRunCreateSdtContentRun() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTRun sdtRun = document.createParagraph().createSdtRun();
            XWPFSDTContentRun sdtContent = sdtRun.createSdtContent();

            try (XmlCursor cursor = sdtRun.getCtSdtRun().newCursor()) {
                cursor.toFirstChild();

                assertEquals(sdtContent.getCtContentRun(), cursor.getObject());
            }
        }
    }

    /**
     * Verify that Run is created inside Sdt Run Content
     * and the collections are updated relatively
     */
    @Test
    public void testCreateRunInsideSdtContent() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTRun sdt = paragraph.createSdtRun();

            XWPFSDTContentRun sdtContent = sdt.createSdtContent();
            XWPFRun run = sdtContent.createRun();
            run.setText("text in SDT");

            assertEquals(1, sdt.getContent().getIRuns().size());
            assertEquals(1, sdt.getContent().getRuns().size());
            assertEquals(0, sdt.getContent().getSDTRuns().size());
            assertEquals("text in SDT", ((XWPFRun) sdt.getContent().getIRuns().get(0)).getText(0));
        }
    }

    @Test
    public void testInsertSDTRun() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTRun sdtRun = document.createParagraph().createSdtRun();
            XWPFSDTContentRun sdtRunContent = sdtRun.createSdtContent();
            XWPFRun run = sdtRunContent.createRun();

            XWPFSDTRun newSdt1 = sdtRunContent.insertNewSdtRun(0);
            XWPFSDTRun newSdt2 = sdtRunContent.insertNewSdtRun(0);
            XWPFSDTRun newSdt3 = sdtRunContent.insertNewSdtRun(0);

            assertEquals(4, sdtRunContent.getIRuns().size());
            assertEquals(Arrays.asList(newSdt3, newSdt2, newSdt1, run), sdtRunContent.getIRuns());
            assertEquals(Arrays.asList(newSdt3, newSdt2, newSdt1), sdtRunContent.getSDTRuns());
        }
    }

    /**
     * Insert Sdt Run between chosen Run in paragraph
     * Then copy the content of this Run to Sdt Run Content
     * Then delete the Run
     * Verify that Run was "wrapped" in Sdt Run
     */
    @Test
    public void testInsertSDTRunBetweenRuns() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.createRun().setText("first ");
            XWPFRun run = paragraph.createRun();
            run.setText("second ");
            run.setFontFamily("Times New Roman");
            run.setFontSize(40);
            paragraph.createRun().setText("third ");

            XWPFSDTRun sdtRunBefore = paragraph.insertNewSdtRun(paragraph.getIRuns().indexOf(run));

            paragraph.insertNewSdtRun(paragraph.getIRuns().indexOf(run) + 1);

            try (XmlCursor cursor = paragraph.getCTP().newCursor()) {
                cursor.toChild(1);
                cursor.push();

                // verify that second element in paragraph is SDT
                assertTrue(cursor.getObject() instanceof CTSdtRun);

                cursor.toNextSibling(); // to next R
                cursor.toNextSibling(); // to Sdt after R

                assertTrue(cursor.getObject() instanceof CTSdtRun);

                // create Pr & Content for SDT
                XWPFSDTPr sdtPr = sdtRunBefore.createSdtPr();
                XWPFSDTContentRun sdtContent = sdtRunBefore.createSdtContent();

                sdtPr.setTag("new-inline-tag");
                sdtPr.setTitle("new-inline-title");
                sdtPr.setLock(STLock.SDT_CONTENT_LOCKED);

                // copy existing run to sdt content & remove run from Paragraph
                sdtContent.cloneExistingIRunElement(run);

                cursor.pop();
                cursor.toChild(1); // move to SdtContent
                cursor.toFirstChild(); // select copied run

                assertTrue(cursor.getObject() instanceof CTR);
                assertEquals("second ", new XWPFRun((CTR) cursor.getObject(), sdtRunBefore.getContent()).getText(0));
                assertEquals("Times New Roman", new XWPFRun((CTR) cursor.getObject(), sdtRunBefore.getContent()).getFontFamily());
            }

            assertEquals(5, paragraph.getIRuns().size());
            assertEquals(3, paragraph.getRuns().size());
            assertEquals(2, paragraph.getSDTRuns().size());
            assertEquals(XWPFSDTRun.class, paragraph.getIRuns().get(1).getClass());
            assertEquals(XWPFSDTRun.class, paragraph.getIRuns().get(3).getClass());
        }
    }

    @Test
    public void testInsertSDTIntoSdt() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();

            XWPFSDTRun dstSdtRun = paragraph.createSdtRun();
            XWPFSDTContentRun dstSdtContent = dstSdtRun.createSdtContent();
            dstSdtContent.createRun().setText("t1");
            dstSdtContent.createRun().setText("t2");

            XWPFSDTRun srcSdtRun = paragraph.createSdtRun();
            XWPFSDTPr srcSdtPr = srcSdtRun.createSdtPr();
            srcSdtPr.setTag("tag");
            XWPFSDTContentRun srcSdtContent = srcSdtRun.createSdtContent();
            srcSdtContent.createRun().setText("t3");
            srcSdtContent.createRun().setText("t4");

            dstSdtContent.cloneExistingIRunElement(srcSdtRun);

            assertEquals(1, document.getParagraphs().size());
            paragraph = document.getParagraphs().get(0);

            assertEquals(2, paragraph.getIRuns().size());
            XWPFSDTRun sdtRun1 = (XWPFSDTRun) paragraph.getIRuns().get(0);
            assertEquals(3, sdtRun1.getContent().getIRuns().size());
            assertEquals("t1", ((XWPFRun) sdtRun1.getContent().getIRuns().get(0)).text());
            assertEquals("t2", ((XWPFRun) sdtRun1.getContent().getIRuns().get(1)).text());

            XWPFSDTRun innerSdtRun = (XWPFSDTRun) sdtRun1.getContent().getIRuns().get(2);
            assertEquals("tag", innerSdtRun.getSdtPr().getTag());
            assertEquals(2, innerSdtRun.getContent().getIRuns().size());
            assertEquals("t3", ((XWPFRun) innerSdtRun.getContent().getIRuns().get(0)).text());
            assertEquals("t4", ((XWPFRun) innerSdtRun.getContent().getIRuns().get(1)).text());

            XWPFSDTRun sdtRun2 = (XWPFSDTRun) paragraph.getIRuns().get(1);
            assertEquals("tag", sdtRun2.getSdtPr().getTag());
            assertEquals(2, sdtRun2.getContent().getIRuns().size());
            assertEquals("t3", ((XWPFRun) sdtRun2.getContent().getIRuns().get(0)).text());
            assertEquals("t4", ((XWPFRun) sdtRun2.getContent().getIRuns().get(1)).text());
        }
    }

    @Test
    public void testInsertHyperlinkRunIntoSdt() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();

            XWPFSDTRun dstSdtRun = paragraph.createSdtRun();
            XWPFSDTContentRun dstSdtContent = dstSdtRun.createSdtContent();
            dstSdtContent.createRun().setText("t1");
            dstSdtContent.createRun().setText("t2");

            paragraph = document.createParagraph();
            XWPFSDTContentRun srcSdtContent = paragraph.createSdtRun().createSdtContent();
            srcSdtContent.createRun().setText("t3");
            srcSdtContent.createRun().setText("t4");
            XWPFHyperlinkRun srcHyperlinkRun = srcSdtContent.createHyperlinkRun("http://poi.apache.org");

            dstSdtContent.cloneExistingIRunElement(srcHyperlinkRun);

            assertEquals(2, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getIRuns().size());
            XWPFSDTRun sdtRun1 = (XWPFSDTRun) paragraph.getIRuns().get(0);
            assertEquals(3, sdtRun1.getContent().getIRuns().size());
            assertEquals("t1", ((XWPFRun) sdtRun1.getContent().getIRuns().get(0)).text());
            assertEquals("t2", ((XWPFRun) sdtRun1.getContent().getIRuns().get(1)).text());
            assertTrue(sdtRun1.getContent().getIRuns().get(2) instanceof XWPFHyperlinkRun);

            paragraph = document.getParagraphs().get(1);

            assertEquals(1, paragraph.getIRuns().size());
            XWPFSDTRun sdtRun2 = (XWPFSDTRun) paragraph.getIRuns().get(0);
            assertEquals(3, sdtRun2.getContent().getIRuns().size());
            assertEquals("t3", ((XWPFRun) sdtRun2.getContent().getIRuns().get(0)).text());
            assertEquals("t4", ((XWPFRun) sdtRun2.getContent().getIRuns().get(1)).text());
            assertTrue(sdtRun2.getContent().getIRuns().get(2) instanceof XWPFHyperlinkRun);
        }
    }

    @Test
    public void testInsertFieldRunIntoSdt() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();

            XWPFSDTRun dstSdtRun = paragraph.createSdtRun();
            XWPFSDTContentRun dstSdtContent = dstSdtRun.createSdtContent();
            dstSdtContent.createRun().setText("t1");
            dstSdtContent.createRun().setText("t2");

            paragraph = document.createParagraph();
            XWPFSDTContentRun srcSdtContent = paragraph.createSdtRun().createSdtContent();
            srcSdtContent.createRun().setText("t3");
            srcSdtContent.createRun().setText("t4");
            XWPFFieldRun srcHyperlinkRun = srcSdtContent.createFieldRun();

            dstSdtContent.cloneExistingIRunElement(srcHyperlinkRun);

            assertEquals(2, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getIRuns().size());
            XWPFSDTRun sdtRun1 = (XWPFSDTRun) paragraph.getIRuns().get(0);
            assertEquals(3, sdtRun1.getContent().getIRuns().size());
            assertEquals("t1", ((XWPFRun) sdtRun1.getContent().getIRuns().get(0)).text());
            assertEquals("t2", ((XWPFRun) sdtRun1.getContent().getIRuns().get(1)).text());
            assertTrue(sdtRun1.getContent().getIRuns().get(2) instanceof XWPFFieldRun);

            paragraph = document.getParagraphs().get(1);

            assertEquals(1, paragraph.getIRuns().size());
            XWPFSDTRun sdtRun2 = (XWPFSDTRun) paragraph.getIRuns().get(0);
            assertEquals(3, sdtRun2.getContent().getIRuns().size());
            assertEquals("t3", ((XWPFRun) sdtRun2.getContent().getIRuns().get(0)).text());
            assertEquals("t4", ((XWPFRun) sdtRun2.getContent().getIRuns().get(1)).text());
            assertTrue(sdtRun2.getContent().getIRuns().get(2) instanceof XWPFFieldRun);
        }
    }

    @Test
    void testRemoveRuns() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTContentRun sdtContent = paragraph.createSdtRun().createSdtContent();

            XWPFRun run = sdtContent.createRun();
            sdtContent.createRun();
            XWPFHyperlinkRun hyperlinkRun = sdtContent.createHyperlinkRun("http://poi.apache.org");
            XWPFFieldRun fieldRun = sdtContent.createFieldRun();

            assertEquals(4, sdtContent.getRuns().size());
            assertEquals(2, sdtContent.getRuns().indexOf(hyperlinkRun));
            assertEquals(3, sdtContent.getRuns().indexOf(fieldRun));

            sdtContent.removeRun(2);
            assertEquals(3, sdtContent.getRuns().size());
            assertEquals(-1, sdtContent.getRuns().indexOf(hyperlinkRun));
            assertEquals(2, sdtContent.getRuns().indexOf(fieldRun));

            sdtContent.removeRun(0);
            assertEquals(2, sdtContent.getRuns().size());
            assertEquals(-1, sdtContent.getRuns().indexOf(run));
            assertEquals(1, sdtContent.getRuns().indexOf(fieldRun));

            sdtContent.removeRun(1);
            assertEquals(1, sdtContent.getRuns().size());
            assertEquals(-1, sdtContent.getRuns().indexOf(fieldRun));
        }
    }

    @Test
    public void testRemoveSdtRun() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTContentRun sdtContent = paragraph.createSdtRun().createSdtContent();

            sdtContent.createRun();
            sdtContent.createSdtRun();

            assertEquals(1, sdtContent.getCtContentRun().getSdtList().size());
            assertTrue(sdtContent.removeSdtRun(1));
            assertEquals(0, sdtContent.getCtContentRun().getSdtList().size());
        }
    }

    @Test
    public void testRemoveIRunElement() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFSDTContentRun sdtContent = paragraph.createSdtRun().createSdtContent();

            sdtContent.createRun();
            sdtContent.createSdtRun();
            sdtContent.createRun().setText("last");

            assertEquals(1, sdtContent.getCtContentRun().getSdtList().size());
            assertEquals(2, sdtContent.getCtContentRun().getRList().size());

            assertTrue(sdtContent.removeIRunElement(1));
            assertTrue(sdtContent.removeIRunElement(0));

            assertEquals(0, sdtContent.getCtContentRun().getSdtList().size());
            assertEquals(1, sdtContent.getCtContentRun().getRList().size());

            assertEquals("last", ((XWPFRun) sdtContent.getIRuns().get(0)).text());
        }
    }

    /**
     * Verify that existing Content Control in document is correctly
     * unmarshalled & we can proceed with modifying its content
     */
    @Test
    public void testUnmarshallingSdtRun() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("blockAndInlineSdtTags.docx")) {
            XWPFParagraph paragraph = document.getParagraphArray(0);
            XWPFSDTRun sdtRun = (XWPFSDTRun) paragraph.getIRuns().get(1);

            // Tag
            assertEquals("inline-sdt-tag", sdtRun.getSdtPr().getTag());

            sdtRun.getSdtPr().setTag("new-inline-tag");
            assertEquals("new-inline-tag", sdtRun.getSdtPr().getTag());

            // Title
            assertEquals("inline-sdt-title", sdtRun.getSdtPr().getTitle());

            sdtRun.getSdtPr().setTitle("new-inline-title");
            assertEquals("new-inline-title", sdtRun.getSdtPr().getTitle());

            // Lock
            assertEquals(STLock.SDT_CONTENT_LOCKED, sdtRun.getSdtPr().getLock());

            sdtRun.getSdtPr().setLock(STLock.SDT_LOCKED);
            assertEquals(STLock.SDT_LOCKED, sdtRun.getSdtPr().getLock());

            // SdtContent
            assertEquals("inline-sdt", sdtRun.getContent().getRuns().get(0).getText(0));

            sdtRun.getContent().getRuns().get(0).setText("new-inline-sdt", 0);
            assertEquals("new-inline-sdt", sdtRun.getContent().getRuns().get(0).getText(0));
        }
    }

    @Test
    public void testNestedSdtRun() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFSDTContentRun sdtContent1 = document.createParagraph().createSdtRun().createSdtContent();
            sdtContent1.createRun();
            sdtContent1.createRun();

            XWPFSDTContentRun sdtContent2 = sdtContent1.createSdtRun().createSdtContent();

            XWPFSDTContentRun sdtContent3 = sdtContent2.createSdtRun().createSdtContent();
            sdtContent3.createSdtRun().createSdtContent();
            sdtContent3.createSdtRun().createSdtContent();
            sdtContent3.createSdtRun().createSdtContent();

            assertEquals(1, document.getParagraphs().size());

            XWPFSDTContentRun actual = document.getParagraphs().get(0)
                    .getSDTRuns()
                    .get(0)
                    .getContent();
            assertEquals(2, actual.getRuns().size());
            assertEquals(3, actual.getIRuns().size());
            assertEquals(1, actual.getSDTRuns().size());

            actual = document.getParagraphs().get(0)
                    .getSDTRuns()
                    .get(0)
                    .getContent()
                    .getSDTRuns()
                    .get(0)
                    .getContent();
            assertEquals(0, actual.getRuns().size());
            assertEquals(1, actual.getIRuns().size());
            assertEquals(1, actual.getSDTRuns().size());

            actual = document.getParagraphs().get(0)
                    .getSDTRuns()
                    .get(0)
                    .getContent()
                    .getSDTRuns()
                    .get(0)
                    .getContent()
                    .getSDTRuns()
                    .get(0)
                    .getContent();
            assertEquals(0, actual.getRuns().size());
            assertEquals(3, actual.getIRuns().size());
            assertEquals(3, actual.getSDTRuns().size());
        }
    }
}
