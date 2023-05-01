/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.xwpf.usermodel;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class XWPFDrawing implements IDrawing {
    private final CTDrawing ctDrawing;
    private final XWPFRun parent;
    private final List<IDrawingContent> drawingContents = new ArrayList<>();

    public XWPFDrawing(CTDrawing ctDrawing, XWPFRun parent) {
        this.ctDrawing = ctDrawing;
        this.parent = parent;

        try (XmlCursor cursor = ctDrawing.newCursor()) {
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject child = cursor.getObject();
                if (child instanceof CTAnchor) {
                    drawingContents.add(new XWPFAnchor((CTAnchor) child, this));
                } else if (child instanceof CTInline) {
                    drawingContents.add(new XWPFInline((CTInline) child, this));
                }
            }
        }
    }

    public XWPFAnchor createAnchor() {
        CTAnchor ctAnchor = ctDrawing.addNewAnchor();
        XWPFAnchor anchor = new XWPFAnchor(ctAnchor, this);
        drawingContents.add(anchor);
        return anchor;
    }

    public XWPFInline createInline() {
        CTInline ctInline = ctDrawing.addNewInline();
        XWPFInline inline = new XWPFInline(ctInline, this);
        drawingContents.add(inline);
        return inline;
    }

    public CTDrawing getCtDrawing() {
        return ctDrawing;
    }

    public XWPFRun getParent() {
        return parent;
    }

    public List<IDrawingContent> getDrawingContents() {
        return drawingContents;
    }

    public List<XWPFGraphicalObject> getGraphics() {
        List<XWPFGraphicalObject> graphics = new ArrayList<>();
        for (IDrawingContent iDrawingContent : drawingContents) {
            XWPFGraphicalObject graphic = iDrawingContent.getGraphicalObject();
            if (graphic == null) {
                continue;
            }
            graphics.add(graphic);
        }
        return graphics;
    }

    public List<XWPFGraphicalObjectData> getGraphicData() {
        List<XWPFGraphicalObjectData> graphicalObjectData = new ArrayList<>();
        for (XWPFGraphicalObject graphic : getGraphics()) {
            XWPFGraphicalObjectData graphicData = graphic.getGraphicalObjectData();
            if (graphicData == null) {
                continue;
            }
            graphicalObjectData.add(graphicData);
        }
        return graphicalObjectData;
    }

    public List<XWPFPicture> getPictures() {
        List<XWPFPicture> pictures = new ArrayList<>();
        for (XWPFGraphicalObjectData graphicDatum : getGraphicData()) {
            XWPFPicture picture = graphicDatum.getPicture();
            if (picture == null) {
                continue;
            }
            pictures.add(picture);
        }
        return pictures;
    }

    public List<XWPFWordprocessingShape> getWordprocessingShapes() {
        List<XWPFWordprocessingShape> wordprocessingShapes = new ArrayList<>();
        for (XWPFGraphicalObjectData graphicDatum : getGraphicData()) {
            XWPFWordprocessingShape wordprocessingShape = graphicDatum.getWordprocessingShape();
            if (wordprocessingShape == null) {
                continue;
            }
            wordprocessingShapes.add(wordprocessingShape);
        }
        return wordprocessingShapes;
    }

    public List<XWPFTextBoxContent> getTextBoxContents() {
        List<XWPFTextBoxContent> textBoxContents = new ArrayList<>();
        for (XWPFWordprocessingShape wordprocessingShape : getWordprocessingShapes()) {
            XWPFTextBoxContent textBoxContent = wordprocessingShape.getTextBoxContent();
            if (textBoxContent == null) {
                continue;
            }
            textBoxContents.add(textBoxContent);
        }
        return textBoxContents;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        XmlObject[] ts = ctDrawing.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:t");
        for (XmlObject t : ts) {
            NodeList kids = t.getDomNode().getChildNodes();
            for (int n = 0; n < kids.getLength(); n++) {
                if (kids.item(n) instanceof Text) {
                    if (text.length() > 0) {
                        text.append("\n");
                    }
                    text.append(kids.item(n).getNodeValue());
                }
            }
        }
        return text.toString();
    }
}
