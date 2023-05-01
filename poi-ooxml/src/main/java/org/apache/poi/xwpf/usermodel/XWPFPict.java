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

import com.microsoft.schemas.vml.CTShape;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.List;

public class XWPFPict implements IDrawing {
    private final CTPicture ctPicture;
    private final XWPFRun parent;
    private XWPFShape shape;

    public XWPFPict(CTPicture ctPicture, XWPFRun parent) {
        this.ctPicture = ctPicture;
        this.parent = parent;

        for (XmlObject o : ctPicture.selectChildren(QNameSet.ALL)) {
            if (o instanceof CTShape) {
                shape = new XWPFShape((CTShape) o, this);
            }
        }
    }

    public XWPFShape createShape() {
        CTShape ctShape = ctPicture.addNewShape();
        XWPFShape shape = new XWPFShape(ctShape, this);
        this.shape = shape;
        return shape;
    }

    public CTPicture getCtPicture() {
        return ctPicture;
    }

    public XWPFRun getParent() {
        return parent;
    }

    public XWPFShape getShape() {
        return shape;
    }

    public List<XWPFTextBoxContent> getTextBoxContents() {
        return shape.getTextBoxContents();
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        XmlObject[] ts = ctPicture.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:t");
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
