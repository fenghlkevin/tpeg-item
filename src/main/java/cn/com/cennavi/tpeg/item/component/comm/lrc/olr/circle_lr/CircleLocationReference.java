package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.circle_lr;

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

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {  BaseComponent.ID , CircleLocationReference.ABSOLUTEGEOCOORDINATE ,CircleLocationReference.RADIUS ,BaseComponent.SELECTOR,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID,BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,CircleLocationReference.ABSOLUTEGEOCOORDINATE,CircleLocationReference.RADIUS, BaseComponent.SELECTOR}))
public class CircleLocationReference extends AbstractLocationReference {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String ABSOLUTEGEOCOORDINATE = "absoluteGeoCoordinate";
    
    public static final String RADIUS = "radius";
    
    {
        this.setId(4);
        this.setLengthComp(0);
        this.setLengthAttr(0);
        
        this.setSelector();
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = { CircleLocationReference.ABSOLUTEGEOCOORDINATE ,CircleLocationReference.RADIUS,BaseComponent.SELECTOR}), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, CircleLocationReference.ABSOLUTEGEOCOORDINATE ,CircleLocationReference.RADIUS,BaseComponent.SELECTOR}), length = -5)
    private NumberField lengthComp;
    
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO }, dependentFieldDigit = {0}))
	private ByteFlag selector;
	
    @CoderItem(id = CircleLocationReference.ABSOLUTEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate absoluteGeoCoordinate;
    
	@CoderItem(id = CircleLocationReference.RADIUS, type = CoderItemType.ITEM, length = -5)
	private NumberField radius;

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
    
    public NumberField getRadius() {
        return radius;
    }
    
    public void setRadius(int radius) {
        NumberField numberField = (NumberField) this.newInstanceItem(CircleLocationReference.RADIUS, false);
        numberField.setNumber(radius);
    }

    public void setLengthAttr(int lengthAttr) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
        numberField.setNumber(lengthAttr);
    }

    public void setLengthComp(int lengthComp) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
        numberField.setNumber(lengthComp);
    }
    
	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}
}
