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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;

public class XWPFGraphicalObject {
    private final CTGraphicalObject ctGraphicalObject;
    private final IDrawingContent parent;
    private XWPFGraphicalObjectData graphicalObjectData;

    public XWPFGraphicalObject(CTGraphicalObject ctGraphicalObject, IDrawingContent parent) {
        this.ctGraphicalObject = ctGraphicalObject;
        this.parent = parent;
        if (ctGraphicalObject.getGraphicData() != null) {
            this.graphicalObjectData = new XWPFGraphicalObjectData(ctGraphicalObject.getGraphicData(), this);
        }
    }

    public XWPFGraphicalObjectData createGraphicalObjectData() {
        CTGraphicalObjectData ctGraphicalObjectData = ctGraphicalObject.addNewGraphicData();
        XWPFGraphicalObjectData graphicalObjectData = new XWPFGraphicalObjectData(ctGraphicalObjectData, this);
        this.graphicalObjectData = graphicalObjectData;
        return graphicalObjectData;
    }

    public CTGraphicalObject getCTGraphicalObject() {
        return ctGraphicalObject;
    }

    public IDrawingContent getParent() {
        return parent;
    }

    public XWPFGraphicalObjectData getGraphicalObjectData() {
        return graphicalObjectData;
    }
}
