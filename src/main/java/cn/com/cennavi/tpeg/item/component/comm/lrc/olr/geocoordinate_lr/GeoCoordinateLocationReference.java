package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.geocoordinate_lr;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.FirstLocationReferencePoint;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {  BaseComponent.ID, GeoCoordinateLocationReference.ABSOLUTEGEOCOORDINATE,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,GeoCoordinateLocationReference.ABSOLUTEGEOCOORDINATE}))
public class GeoCoordinateLocationReference extends AbstractLocationReference {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String ABSOLUTEGEOCOORDINATE = "absoluteGeoCoordinate";
    
    {
        this.setId(1);
        this.setLengthComp(0);
        this.setLengthAttr(0);
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = { GeoCoordinateLocationReference.ABSOLUTEGEOCOORDINATE}), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, GeoCoordinateLocationReference.ABSOLUTEGEOCOORDINATE}), length = -5)
    private NumberField lengthComp;
    
    @CoderItem(id = FirstLocationReferencePoint.ABSOLUTEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate absoluteGeoCoordinate;

    public AbsoluteGeoCoordinate getAbsoluteGeoCoordinate() {
        return absoluteGeoCoordinate;
    }

    public void setAbsoluteGeoCoordinate(AbsoluteGeoCoordinate absoluteGeoCoordinate) {
        this.absoluteGeoCoordinate = absoluteGeoCoordinate;
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
