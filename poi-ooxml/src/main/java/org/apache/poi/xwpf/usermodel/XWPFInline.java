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

import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

public class XWPFInline implements IDrawingContent {
    private final CTInline ctInline;
    private final XWPFDrawing parent;
    private XWPFGraphicalObject graphic;

    public XWPFInline(CTInline ctInline, XWPFDrawing parent) {
        this.ctInline = ctInline;
        this.parent = parent;
        if (ctInline.getGraphic() != null) {
            this.graphic = new XWPFGraphicalObject(ctInline.getGraphic(), this);
        }
    }

    public XWPFGraphicalObject createGraphicalObject() {
        CTGraphicalObject ctGraphicalObject = ctInline.addNewGraphic();
        XWPFGraphicalObject graphicalObject = new XWPFGraphicalObject(ctGraphicalObject, this);
        graphic = graphicalObject;
        return graphicalObject;
    }

    public CTInline getCTInline() {
        return ctInline;
    }

    public XWPFDrawing getParent() {
        return parent;
    }

    public XWPFGraphicalObject getGraphicalObject() {
        return graphic;
    }
}
