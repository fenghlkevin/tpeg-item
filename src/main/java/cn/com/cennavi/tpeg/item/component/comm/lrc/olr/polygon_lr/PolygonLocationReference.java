package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.polygon_lr;

import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.RelativeGeoCoordinate;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {  BaseComponent.ID, PolygonLocationReference.ABSOLUTEGEOCOORDINATE,PolygonLocationReference.COUNT,PolygonLocationReference.RELATIVEGEOCOORDINATE,BaseComponent.SELECTOR,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,PolygonLocationReference.ABSOLUTEGEOCOORDINATE,PolygonLocationReference.COUNT,PolygonLocationReference.RELATIVEGEOCOORDINATE,BaseComponent.SELECTOR}))
public class PolygonLocationReference extends AbstractLocationReference {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String ABSOLUTEGEOCOORDINATE = "absoluteGeoCoordinate";
    public static final String RELATIVEGEOCOORDINATE = "relativeGeoCoordinate";
    public static final String COUNT = "count";
    
    {
        this.setId(5);
        this.setLengthComp(0);
        this.setLengthAttr(0);
        this.setSelector();
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = {PolygonLocationReference.RELATIVEGEOCOORDINATE,PolygonLocationReference.ABSOLUTEGEOCOORDINATE,PolygonLocationReference.COUNT,BaseComponent.SELECTOR}), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,PolygonLocationReference.RELATIVEGEOCOORDINATE, PolygonLocationReference.ABSOLUTEGEOCOORDINATE,PolygonLocationReference.COUNT,BaseComponent.SELECTOR}), length = -5)
    private NumberField lengthComp;
    
    @CoderItem(id = PolygonLocationReference.ABSOLUTEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate absoluteGeoCoordinate;
    
    @CoderItem(id=PolygonLocationReference.COUNT, type = CoderItemType.ITEM, length = -5)
    private NumberField count;
    
    @CoderItem(id = PolygonLocationReference.RELATIVEGEOCOORDINATE, type = CoderItemType.LIST, length = -99999)
    private List<RelativeGeoCoordinate> relativeGeoCoordinate;
    
    @CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {  CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO  }, dependentFieldDigit = {0}))
	private ByteFlag selector;
    
    public ByteFlag getSelector() {
		return selector;
	}
	
	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}
    
	public AbsoluteGeoCoordinate getAbsoluteGeoCoordinate() {
        return absoluteGeoCoordinate;
    }
    public void setAbsoluteGeoCoordinate(AbsoluteGeoCoordinate absoluteGeoCoordinate) {
        this.absoluteGeoCoordinate = absoluteGeoCoordinate;
    }

	public List<RelativeGeoCoordinate> getRelativeGeoCoordinate() {
		return relativeGeoCoordinate;
	}
	public void setRelativeGeoCoordinate(
			List<RelativeGeoCoordinate> relativeGeoCoordinate) {
		this.relativeGeoCoordinate = relativeGeoCoordinate;
	}
	public NumberField getLengthAttr() {
        return lengthAttr;
    }

    public NumberField getLengthComp() {
        return lengthComp;
    }

    public NumberField getCount() {
		return count;
	}
	public void setCount(int count) {
		NumberField numberField = (NumberField) this.newInstanceItem(PolygonLocationReference.COUNT, false);
        numberField.setNumber(count);
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
