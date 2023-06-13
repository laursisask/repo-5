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

import com.microsoft.schemas.office.word.x2010.wordprocessingShape.CTWordprocessingShape;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;

public class XWPFGraphicalObjectData {
    private final CTGraphicalObjectData ctGraphicalObjectData;
    private final XWPFGraphicalObject parent;
    private XWPFPicture picture;
    private XWPFWordprocessingShape wordprocessingShape;

    public XWPFGraphicalObjectData(CTGraphicalObjectData ctGraphicalObjectData, XWPFGraphicalObject parent) {
        this.ctGraphicalObjectData = ctGraphicalObjectData;
        this.parent = parent;

        for (XmlObject o : ctGraphicalObjectData.selectChildren(QNameSet.ALL)) {
            if (o instanceof CTPicture) {
                picture = new XWPFPicture((CTPicture) o, parent.getParent().getParent().getParent());
            } else if (o instanceof CTWordprocessingShape) {
                wordprocessingShape = new XWPFWordprocessingShape((CTWordprocessingShape) o, this);
            }
        }
    }

    public XWPFPicture createPicture() {
        CTPicture ctPicture = ctGraphicalObjectData.addNewPic();
        XWPFPicture picture = new XWPFPicture(ctPicture, parent.getParent().getParent().getParent());
        this.picture = picture;
        return picture;
    }

    public XWPFWordprocessingShape createWordprocessingShape() {
        CTWordprocessingShape ctWordprocessingShape = ctGraphicalObjectData.addNewWsp();
        XWPFWordprocessingShape wordprocessingShape = new XWPFWordprocessingShape(ctWordprocessingShape, this);
        this.wordprocessingShape = wordprocessingShape;
        return wordprocessingShape;
    }

    public CTGraphicalObjectData getCTGraphicalObjectData() {
        return ctGraphicalObjectData;
    }

    public XWPFGraphicalObject getParent() {
        return parent;
    }

    public XWPFPicture getPicture() {
        return picture;
    }

    public XWPFWordprocessingShape getWordprocessingShape() {
        return wordprocessingShape;
    }
}
