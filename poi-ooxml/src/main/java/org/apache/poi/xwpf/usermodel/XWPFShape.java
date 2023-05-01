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
import com.microsoft.schemas.vml.CTTextbox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;

import java.util.ArrayList;
import java.util.List;

public class XWPFShape {
    private final CTShape ctShape;
    private final XWPFPict parent;
    private final List<XWPFTextBoxContent> textBoxContents = new ArrayList<>();

    public XWPFShape(CTShape ctShape, XWPFPict parent) {
        this.ctShape = ctShape;
        this.parent = parent;

        for (CTTextbox ctTextbox : ctShape.getTextboxArray()) {
            CTTxbxContent ctTxbxContent = ctTextbox.getTxbxContent();
            if (ctTxbxContent == null) {
                continue;
            }
            XWPFTextBoxContent textBoxContent = new XWPFTextBoxContent(ctTxbxContent, this);
            textBoxContents.add(textBoxContent);
        }
    }

    public XWPFTextBoxContent createTextBoxContent() {
        CTTxbxContent ctTxbxContent = ctShape.addNewTextbox().addNewTxbxContent();
        XWPFTextBoxContent textBoxContent = new XWPFTextBoxContent(ctTxbxContent, this);
        textBoxContents.add(textBoxContent);
        return textBoxContent;
    }

    public CTShape getCtShape() {
        return ctShape;
    }

    public XWPFPict getParent() {
        return parent;
    }

    public List<XWPFTextBoxContent> getTextBoxContents() {
        return textBoxContents;
    }
}
