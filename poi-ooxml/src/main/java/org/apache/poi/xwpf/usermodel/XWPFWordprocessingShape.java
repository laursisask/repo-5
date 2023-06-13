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

import com.microsoft.schemas.office.word.x2010.wordprocessingShape.CTTextboxInfo;
import com.microsoft.schemas.office.word.x2010.wordprocessingShape.CTWordprocessingShape;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;

public class XWPFWordprocessingShape {
    private final CTWordprocessingShape ctWordprocessingShape;
    private final XWPFGraphicalObjectData parent;
    private XWPFTextBoxContent textBoxContent;

    public XWPFWordprocessingShape(CTWordprocessingShape ctWordprocessingShape, XWPFGraphicalObjectData parent) {
        this.ctWordprocessingShape = ctWordprocessingShape;
        this.parent = parent;
        CTTextboxInfo txbx = ctWordprocessingShape.getTxbx();
        if (txbx != null) {
            CTTxbxContent txbxContent = txbx.getTxbxContent();
            if (txbxContent != null) {
                textBoxContent = new XWPFTextBoxContent(txbxContent, this);
            }
        }
    }

    public XWPFTextBoxContent createTextBoxContent() {
        CTTxbxContent ctTxbxContent = ctWordprocessingShape.addNewTxbx().addNewTxbxContent();
        XWPFTextBoxContent textBoxContent = new XWPFTextBoxContent(ctTxbxContent, this);
        this.textBoxContent = textBoxContent;
        return textBoxContent;
    }

    public CTWordprocessingShape getCTWordprocessingShape() {
        return ctWordprocessingShape;
    }

    public XWPFGraphicalObjectData getParent() {
        return parent;
    }

    public XWPFTextBoxContent getTextBoxContent() {
        return textBoxContent;
    }
}
