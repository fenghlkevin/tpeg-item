package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.point_along_line_lr;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.PointLocationLineReferenceData;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID,PointAlongLineLocationReference.POINTLOCATIONLINEREFERENCEDATA,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,PointAlongLineLocationReference.POINTLOCATIONLINEREFERENCEDATA}))
public class PointAlongLineLocationReference extends AbstractLocationReference {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String POINTLOCATIONLINEREFERENCEDATA = "pointLocationLineReferenceData";
    
    {
        this.setId(2);
        this.setLengthComp(0);
        this.setLengthAttr(0);
    }
    
    @CoderItem(id = PointAlongLineLocationReference.POINTLOCATIONLINEREFERENCEDATA, type = CoderItemType.ITEM, length = 0)
    private PointLocationLineReferenceData pointLocationLineReferenceData;
    
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = {PointAlongLineLocationReference.POINTLOCATIONLINEREFERENCEDATA}), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { PointAlongLineLocationReference.LENGTHATTR, PointAlongLineLocationReference.POINTLOCATIONLINEREFERENCEDATA}), length = -5)
    private NumberField lengthComp;
    
    public PointLocationLineReferenceData getPointLocationLineReferenceData() {
        return pointLocationLineReferenceData;
    }

    public void setPointLocationLineReferenceData(PointLocationLineReferenceData pointLocationLineReferenceData) {
        this.pointLocationLineReferenceData = pointLocationLineReferenceData;
    }

    public NumberField getLengthAttr() {
        return lengthAttr;
    }

    public NumberField getLengthComp() {
        return lengthComp;
    }

    public void setLengthAttr(int lengthAttr) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
        numberField.setNumber(lengthAttr);
    }

    public void setLengthComp(int lengthComp) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
        numberField.setNumber(lengthComp);
    }
}
