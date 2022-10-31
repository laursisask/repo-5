package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;

public class XWPFSDTBlock extends XWPFAbstractSDT implements IBodyElement {
    private final CTSdtBlock ctSdtBlock;
    private final IBody part;
    private XWPFSDTContentBlock contentBlock;

    public XWPFSDTBlock(CTSdtBlock sdtBlock, IBody part) {
        super(sdtBlock.getSdtPr());
        this.ctSdtBlock = sdtBlock;
        this.part = part;
        this.contentBlock = new XWPFSDTContentBlock(sdtBlock.getSdtContent(), this);
    }

    @Override
    public XWPFSDTContentBlock getContent() {
        return contentBlock;
    }

    @Override
    public XWPFSDTContentBlock createSdtContent() {
        XWPFSDTContentBlock xwpfsdtContentBlock = new XWPFSDTContentBlock(this.ctSdtBlock.addNewSdtContent(), this);
        this.contentBlock = xwpfsdtContentBlock;
        return xwpfsdtContentBlock;
    }

    @Override
    public XWPFSDTPr createSdtPr() {
        XWPFSDTPr xwpfsdtPr = new XWPFSDTPr(this.ctSdtBlock.addNewSdtPr());
        this.sdtPr = xwpfsdtPr;
        return xwpfsdtPr;
    }

    /**
     * @return null
     */
    @Override
    public IBody getBody() {
        return part;
    }

    @Override
    public XWPFDocument getDocument() {
        if (part != null) {
            return part.getXWPFDocument();
        }
        return null;
    }

    @Override
    public POIXMLDocumentPart getPart() {
        if (part != null) {
            return part.getPart();
        }
        return null;
    }

    /**
     * @return partType
     */
    @Override
    public BodyType getPartType() {
        return BodyType.CONTENTCONTROL;
    }

    /**
     * @return element type
     */
    @Override
    public BodyElementType getElementType() {
        return BodyElementType.CONTENTCONTROL;
    }

    public CTSdtBlock getCtSdtBlock() {
        return ctSdtBlock;
    }
}