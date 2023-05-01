package org.apache.poi.xwpf.usermodel;

import java.util.List;

public interface IDrawing {
    XWPFRun getParent();
    List<XWPFTextBoxContent> getTextBoxContents();
}
