package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.XWPFTestDataSamples;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestXWPFPict {
    @Test
    void testPictTextBox() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("pict-textbox.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFPict.class, iDrawing);
                    XWPFPict pict = (XWPFPict) iDrawing;
                    assertEquals(run, pict.getParent());
                    {
                        XWPFShape shape = pict.getShape();
                        assertEquals(pict, shape.getParent());
                        {
                            List<XWPFTextBoxContent> textBoxContents = shape.getTextBoxContents();
                            assertEquals(1, textBoxContents.size());
                            assertEquals(1, pict.getTextBoxContents().size());
                            assertEquals(textBoxContents, pict.getTextBoxContents());
                            {
                                XWPFTextBoxContent textBoxContent = textBoxContents.get(0);

                                assertEquals(1, textBoxContent.getBodyElements().size());
                                assertEquals(1, textBoxContent.getParagraphs().size());

                                XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);

                                assertEquals(2, innerParagraph.getIRuns().size());
                                assertEquals("qqww", innerParagraph.getRuns().get(0).text());
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void testPictImage() throws IOException {
        try (XWPFDocument document = XWPFTestDataSamples.openSampleDocument("pict-image.docx")) {
            assertEquals(1, document.getBodyElements().size());
            assertEquals(1, document.getParagraphs().size());

            XWPFParagraph paragraph = document.getParagraphs().get(0);
            assertEquals(1, paragraph.getRuns().size());
            {
                XWPFRun run = paragraph.getRuns().get(0);
                assertEquals(1, run.getIDrawings().size());
                {
                    IDrawing iDrawing = run.getIDrawings().get(0);
                    assertInstanceOf(XWPFPict.class, iDrawing);
                    XWPFPict pict = (XWPFPict) iDrawing;
                    assertEquals(run, pict.getParent());
                    {
                        XWPFShape shape = pict.getShape();
                        assertEquals(pict, shape.getParent());

                        assertEquals(0, shape.getTextBoxContents().size());
                    }
                }
            }
        }
    }

    @Test
    void testCreatePictTextBoxContent() throws IOException {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            {
                XWPFRun run = paragraph.createRun();
                {
                    XWPFPict pict = run.createPict();
                    {
                        XWPFShape shape = pict.createShape();
                        {
                            XWPFTextBoxContent textBoxContent = shape.createTextBoxContent();
                            textBoxContent.createParagraph().createRun().setText("1");
                        }
                    }
                }
            }

            assertEquals(
                    "<main:r xmlns:main=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><main:pict><main:shape><urn:textbox xmlns:urn=\"urn:schemas-microsoft-com:vml\"><main:txbxContent><main:p><main:r><main:t>1</main:t></main:r></main:p></main:txbxContent></urn:textbox></main:shape></main:pict></main:r>",
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
                    assertInstanceOf(XWPFPict.class, iDrawing);
                    XWPFPict pict = (XWPFPict) iDrawing;
                    {
                        XWPFShape shape = pict.getShape();
                        assertEquals(1, shape.getTextBoxContents().size());

                        XWPFTextBoxContent textBoxContent = shape.getTextBoxContents().get(0);

                        assertEquals(1, textBoxContent.getBodyElements().size());
                        assertEquals(1, textBoxContent.getParagraphs().size());

                        XWPFParagraph innerParagraph = textBoxContent.getParagraphs().get(0);
                        assertEquals(1, innerParagraph.getRuns().size());
                        assertEquals("1", innerParagraph.getRuns().get(0).text());
                    }
                }
            }
        }
    }
}