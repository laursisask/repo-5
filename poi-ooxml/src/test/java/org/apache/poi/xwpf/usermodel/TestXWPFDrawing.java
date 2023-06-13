package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.XWPFTestDataSamples;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestXWPFDrawing {
    @Test
    void testDrawingInlineImage() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("drawing-inline-image.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertInstanceOf(XWPFDrawing.class, drawing);
                    assertEquals(run, drawing.getParent());
                    assertEquals(1, drawing.getDrawingContents().size());
                    assertInstanceOf(XWPFInline.class, drawing.getDrawingContents().get(0));
                    {
                        XWPFInline inline = (XWPFInline) drawing.getDrawingContents().get(0);
                        assertEquals(drawing, inline.getParent());
                        {
                            XWPFGraphicalObject graphic = inline.getGraphicalObject();
                            assertEquals(1, drawing.getGraphics().size());
                            assertEquals(graphic, drawing.getGraphics().get(0));
                            assertEquals(inline, graphic.getParent());
                            {
                                XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
                                assertEquals(1, drawing.getGraphicData().size());
                                assertEquals(graphicData, drawing.getGraphicData().get(0));
                                assertEquals(graphic, graphicData.getParent());
                                {
                                    XWPFPicture picture = graphicData.getPicture();
                                    assertEquals(1, drawing.getPictures().size());
                                    assertEquals(picture, drawing.getPictures().get(0));
                                    assertEquals("image1.png", picture.getDescription());
                                    assertNull(graphicData.getWordprocessingShape());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void testDrawingAnchorTextBox() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("drawing-anchor-text-box.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertInstanceOf(XWPFDrawing.class, drawing);
                    assertEquals(run, drawing.getParent());
                    assertEquals(1, drawing.getDrawingContents().size());
                    assertInstanceOf(XWPFAnchor.class, drawing.getDrawingContents().get(0));
                    {
                        XWPFAnchor inline = (XWPFAnchor) drawing.getDrawingContents().get(0);
                        assertEquals(drawing, inline.getParent());
                        {
                            XWPFGraphicalObject graphic = inline.getGraphicalObject();
                            assertEquals(1, drawing.getGraphics().size());
                            assertEquals(graphic, drawing.getGraphics().get(0));
                            assertEquals(inline, graphic.getParent());
                            {
                                XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
                                assertEquals(1, drawing.getGraphicData().size());
                                assertEquals(graphicData, drawing.getGraphicData().get(0));
                                assertEquals(graphic, graphicData.getParent());
                                {
                                    assertNull(graphicData.getPicture());
                                    assertEquals(0, drawing.getPictures().size());
                                    XWPFWordprocessingShape wordprocessingShape = graphicData.getWordprocessingShape();
                                    assertEquals(1, drawing.getWordprocessingShapes().size());
                                    assertEquals(graphicData, wordprocessingShape.getParent());
                                    {
                                        XWPFTextBoxContent textBoxContent = wordprocessingShape.getTextBoxContent();
                                        assertEquals(1, drawing.getTextBoxContents().size());
                                        assertEquals(textBoxContent, drawing.getTextBoxContents().get(0));
                                        assertEquals(wordprocessingShape, textBoxContent.getParent());
                                        assertEquals(1, textBoxContent.getBodyElements().size());
                                        assertEquals(1, textBoxContent.getParagraphs().size());

                                        XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);
                                        assertEquals(2, innerParagraph.getRuns().size());
                                        assertEquals("qqww", innerParagraph.getRuns().get(0).text());
                                        assertEquals("", innerParagraph.getRuns().get(1).text());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void testDrawingInlineAlternateContentImage() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("drawing-inline-alternate-content-image.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(2, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertInstanceOf(XWPFDrawing.class, drawing);
                    assertEquals(run, drawing.getParent());
                    assertEquals(1, drawing.getDrawingContents().size());
                    assertInstanceOf(XWPFInline.class, drawing.getDrawingContents().get(0));
                    {
                        XWPFInline inline = (XWPFInline) drawing.getDrawingContents().get(0);
                        assertEquals(drawing, inline.getParent());
                        {
                            XWPFGraphicalObject graphic = inline.getGraphicalObject();
                            assertEquals(1, drawing.getGraphics().size());
                            assertEquals(graphic, drawing.getGraphics().get(0));
                            assertEquals(inline, graphic.getParent());
                            {
                                XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
                                assertEquals(1, drawing.getGraphicData().size());
                                assertEquals(graphicData, drawing.getGraphicData().get(0));
                                assertEquals(graphic, graphicData.getParent());
                                {
                                    XWPFPicture picture = graphicData.getPicture();
                                    assertEquals(1, drawing.getPictures().size());
                                    assertEquals(picture, drawing.getPictures().get(0));
                                    assertEquals("", picture.getDescription());
                                    assertNull(graphicData.getWordprocessingShape());
                                }
                            }
                        }
                    }
                    iDrawing = run.getIDrawings().get(1);
                    drawing = (XWPFDrawing) iDrawing;
                    assertInstanceOf(XWPFDrawing.class, drawing);
                    assertEquals(run, drawing.getParent());
                    assertEquals(1, drawing.getDrawingContents().size());
                    assertInstanceOf(XWPFInline.class, drawing.getDrawingContents().get(0));
                    {
                        XWPFInline inline = (XWPFInline) drawing.getDrawingContents().get(0);
                        assertEquals(drawing, inline.getParent());
                        {
                            XWPFGraphicalObject graphic = inline.getGraphicalObject();
                            assertEquals(1, drawing.getGraphics().size());
                            assertEquals(graphic, drawing.getGraphics().get(0));
                            assertEquals(inline, graphic.getParent());
                            {
                                XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
                                assertEquals(1, drawing.getGraphicData().size());
                                assertEquals(graphicData, drawing.getGraphicData().get(0));
                                assertEquals(graphic, graphicData.getParent());
                                {
                                    XWPFPicture picture = graphicData.getPicture();
                                    assertEquals(1, drawing.getPictures().size());
                                    assertEquals(picture, drawing.getPictures().get(0));
                                    assertEquals("", picture.getDescription());
                                    assertNull(graphicData.getWordprocessingShape());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void testDrawingAnchorAlternateContentTextBox() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("drawing-anchor-alternate-content-text-box.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(2, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertInstanceOf(XWPFDrawing.class, drawing);
                    assertEquals(run, drawing.getParent());
                    assertEquals(1, drawing.getDrawingContents().size());
                    assertInstanceOf(XWPFAnchor.class, drawing.getDrawingContents().get(0));
                    {
                        XWPFAnchor anchor = (XWPFAnchor) drawing.getDrawingContents().get(0);
                        assertEquals(drawing, anchor.getParent());
                        {
                            XWPFGraphicalObject graphic = anchor.getGraphicalObject();
                            assertEquals(1, drawing.getGraphics().size());
                            assertEquals(graphic, drawing.getGraphics().get(0));
                            assertEquals(anchor, graphic.getParent());
                            {
                                XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
                                assertEquals(1, drawing.getGraphicData().size());
                                assertEquals(graphicData, drawing.getGraphicData().get(0));
                                assertEquals(graphic, graphicData.getParent());
                                {
                                    assertNull(graphicData.getPicture());
                                    assertEquals(0, drawing.getPictures().size());
                                    XWPFWordprocessingShape wordprocessingShape = graphicData.getWordprocessingShape();
                                    assertEquals(1, drawing.getWordprocessingShapes().size());
                                    assertEquals(wordprocessingShape, drawing.getWordprocessingShapes().get(0));
                                    assertEquals(graphicData, wordprocessingShape.getParent());
                                    {
                                        XWPFTextBoxContent textBoxContent = wordprocessingShape.getTextBoxContent();
                                        assertEquals(1, drawing.getTextBoxContents().size());
                                        assertEquals(textBoxContent, drawing.getTextBoxContents().get(0));
                                        assertEquals(wordprocessingShape, textBoxContent.getParent());
                                        assertEquals(1, textBoxContent.getBodyElements().size());
                                        assertEquals(1, textBoxContent.getParagraphs().size());

                                        XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);
                                        assertEquals(2, innerParagraph.getRuns().size());
                                        assertEquals("qqww", innerParagraph.getRuns().get(0).text());
                                        assertEquals("", innerParagraph.getRuns().get(1).text());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void testCreateDrawingAnchorPicture() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            {
                XWPFRun run = paragraph.createRun();
                {
                    XWPFDrawing drawing = run.createDrawing();
                    {
                        XWPFAnchor anchor = drawing.createAnchor();
                        {
                            XWPFGraphicalObject graphicalObject = anchor.createGraphicalObject();
                            XWPFGraphicalObjectData graphicalObjectData = graphicalObject.createGraphicalObjectData();
                            graphicalObjectData.createPicture();
                        }
                    }
                }
            }

            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFDrawing.class, iDrawing);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertEquals(1, drawing.getDrawingContents().size());
                    {
                        IDrawingContent iDrawingContent = drawing.getDrawingContents().get(0);
                        assertInstanceOf(XWPFAnchor.class, iDrawingContent);
                        XWPFAnchor anchor = (XWPFAnchor) iDrawingContent;
                        {
                            XWPFGraphicalObject graphicalObject = anchor.getGraphicalObject();
                            XWPFGraphicalObjectData graphicData = graphicalObject.getGraphicalObjectData();
                            assertNotNull(graphicData.getPicture());
                            assertNull(graphicData.getWordprocessingShape());
                        }
                    }
                }
            }
        }
    }

    @Test
    void testCreateDrawingInlinePicture() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            {
                XWPFRun run = paragraph.createRun();
                {
                    XWPFDrawing drawing = run.createDrawing();
                    {
                        XWPFInline inline = drawing.createInline();
                        {
                            XWPFGraphicalObject graphicalObject = inline.createGraphicalObject();
                            XWPFGraphicalObjectData graphicalObjectData = graphicalObject.createGraphicalObjectData();
                            graphicalObjectData.createPicture();
                        }
                    }
                }
            }

            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFDrawing.class, iDrawing);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertEquals(1, drawing.getDrawingContents().size());
                    {
                        IDrawingContent iDrawingContent = drawing.getDrawingContents().get(0);
                        assertInstanceOf(XWPFInline.class, iDrawingContent);
                        XWPFInline anchor = (XWPFInline) iDrawingContent;
                        {
                            XWPFGraphicalObject graphicalObject = anchor.getGraphicalObject();
                            XWPFGraphicalObjectData graphicData = graphicalObject.getGraphicalObjectData();
                            assertNotNull(graphicData.getPicture());
                            assertNull(graphicData.getWordprocessingShape());
                        }
                    }
                }
            }
        }
    }

    @Test
    void testCreateDrawingAnchorTextBoxContent() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            {
                XWPFRun run = paragraph.createRun();
                {
                    XWPFDrawing drawing = run.createDrawing();
                    {
                        XWPFAnchor anchor = drawing.createAnchor();
                        {
                            XWPFGraphicalObject graphicalObject = anchor.createGraphicalObject();
                            XWPFGraphicalObjectData graphicalObjectData = graphicalObject.createGraphicalObjectData();
                            XWPFWordprocessingShape wordprocessingShape = graphicalObjectData.createWordprocessingShape();
                            XWPFTextBoxContent textBoxContent = wordprocessingShape.createTextBoxContent();
                            textBoxContent.createParagraph().createRun().setText("1");
                        }
                    }
                }
            }
            assertEquals(
                    "<main:r xmlns:main=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><main:drawing><wor:anchor xmlns:wor=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"><main1:graphic xmlns:main1=\"http://schemas.openxmlformats.org/drawingml/2006/main\"><main1:graphicData><wor1:wsp xmlns:wor1=\"http://schemas.microsoft.com/office/word/2010/wordprocessingShape\"><wor1:txbx><main:txbxContent><main:p><main:r><main:t>1</main:t></main:r></main:p></main:txbxContent></wor1:txbx></wor1:wsp></main1:graphicData></main1:graphic></wor:anchor></main:drawing></main:r>",
                    paragraph.getCTP().xmlText()
            );

            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFDrawing.class, iDrawing);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertEquals(1, drawing.getDrawingContents().size());
                    {
                        IDrawingContent iDrawingContent = drawing.getDrawingContents().get(0);
                        assertInstanceOf(XWPFAnchor.class, iDrawingContent);
                        XWPFAnchor anchor = (XWPFAnchor) iDrawingContent;
                        {
                            XWPFGraphicalObject graphicalObject = anchor.getGraphicalObject();
                            XWPFGraphicalObjectData graphicData = graphicalObject.getGraphicalObjectData();
                            XWPFWordprocessingShape wordprocessingShape = graphicData.getWordprocessingShape();
                            XWPFTextBoxContent textBoxContent = wordprocessingShape.getTextBoxContent();

                            assertEquals(1, textBoxContent.getBodyElements().size());
                            assertEquals(1, textBoxContent.getParagraphs().size());

                            XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);
                            assertEquals(1, innerParagraph.getRuns().size());
                            assertEquals("1", innerParagraph.getRuns().get(0).text());

                            assertNull(graphicData.getPicture());
                        }
                    }
                }
            }
        }
    }

    @Test
    void testCreateDrawingInlineTextBoxContent() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            {
                XWPFRun run = paragraph.createRun();
                {
                    XWPFDrawing drawing = run.createDrawing();
                    {
                        XWPFInline inline = drawing.createInline();
                        {
                            XWPFGraphicalObject graphicalObject = inline.createGraphicalObject();
                            XWPFGraphicalObjectData graphicalObjectData = graphicalObject.createGraphicalObjectData();
                            XWPFWordprocessingShape wordprocessingShape = graphicalObjectData.createWordprocessingShape();
                            XWPFTextBoxContent textBoxContent = wordprocessingShape.createTextBoxContent();
                            textBoxContent.createParagraph().createRun().setText("1");
                        }
                    }
                }
            }

            assertEquals(
                    "<main:r xmlns:main=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><main:drawing><wor:inline xmlns:wor=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\"><main1:graphic xmlns:main1=\"http://schemas.openxmlformats.org/drawingml/2006/main\"><main1:graphicData><wor1:wsp xmlns:wor1=\"http://schemas.microsoft.com/office/word/2010/wordprocessingShape\"><wor1:txbx><main:txbxContent><main:p><main:r><main:t>1</main:t></main:r></main:p></main:txbxContent></wor1:txbx></wor1:wsp></main1:graphicData></main1:graphic></wor:inline></main:drawing></main:r>",
                    paragraph.getCTP().xmlText()
            );

            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFDrawing.class, iDrawing);
                    XWPFDrawing drawing = (XWPFDrawing) iDrawing;
                    assertEquals(1, drawing.getDrawingContents().size());
                    {
                        IDrawingContent iDrawingContent = drawing.getDrawingContents().get(0);
                        assertInstanceOf(XWPFInline.class, iDrawingContent);
                        XWPFInline anchor = (XWPFInline) iDrawingContent;
                        {
                            XWPFGraphicalObject graphicalObject = anchor.getGraphicalObject();
                            XWPFGraphicalObjectData graphicData = graphicalObject.getGraphicalObjectData();
                            XWPFWordprocessingShape wordprocessingShape = graphicData.getWordprocessingShape();
                            XWPFTextBoxContent textBoxContent = wordprocessingShape.getTextBoxContent();

                            assertEquals(1, textBoxContent.getBodyElements().size());
                            assertEquals(1, textBoxContent.getParagraphs().size());

                            XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);
                            assertEquals(1, innerParagraph.getRuns().size());
                            assertEquals("1", innerParagraph.getRuns().get(0).text());

                            assertNull(graphicData.getPicture());
                        }
                    }
                }
            }
        }
    }
}