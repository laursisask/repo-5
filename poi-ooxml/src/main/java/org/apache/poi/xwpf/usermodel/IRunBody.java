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

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.xmlbeans.XmlCursor;

import java.util.List;

/**
 * Simple interface describing both {@link XWPFParagraph}
 * and {@link XWPFSDTBlock}
 * <p>
 * TODO Should this be based on / extend {@link Paragraph}?
 */
public interface IRunBody {
    XWPFDocument getDocument();

    POIXMLDocumentPart getPart();

    List<IRunElement> getIRuns();

    List<XWPFRun> getRuns();

    List<XWPFSDTRun> getSDTRuns();

    XWPFHyperlinkRun insertNewHyperlinkRun(int pos, String uri);

    XWPFHyperlinkRun createHyperlinkRun(String uri);

    XWPFFieldRun insertNewFieldRun(int pos);

    XWPFFieldRun createFieldRun();

    XWPFRun insertNewRun(int pos);

    XWPFRun createRun();

    /** @deprecated */
    void setSDTRun(int pos, XWPFSDTRun sdt);

    XWPFSDTRun insertNewSdtRun(int pos);

    XWPFSDTRun createSdtRun();

    boolean removeIRunElement(int irunPos);

    boolean removeRun(int pos);

    boolean removeSdtRun(int irunPos);
}
