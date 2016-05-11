package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;


import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.Rectangle;


//@CoderLinks(compileOrder = @EncoderBuildOrder(order = { AbsoluteGeoCoordinate.LONGITUDE,AbsoluteGeoCoordinate.LATITUDE, BaseComponent.SELECTOR}), buildOrder = @EncoderBuildOrder(order = {
//		AbsoluteGeoCoordinate.LONGITUDE,AbsoluteGeoCoordinate.LATITUDE, BaseComponent.SELECTOR }))

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {Rectangle.LOWERLEFTCOORDINATE,Rectangle.UPPERRIGHTCOORDINATE,BaseComponent.SELECTOR}), buildOrder = @EncoderBuildOrder(order = {
		Rectangle.LOWERLEFTCOORDINATE,Rectangle.UPPERRIGHTCOORDINATE,BaseComponent.SELECTOR}))
public class Rectangle extends BaseComponent {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String LOWERLEFTCOORDINATE = "lowerLeftCoordinate";
    public static final String UPPERRIGHTCOORDINATE = "upperRightCoordinate";
	{
		this.setSelector();
	}
    
    @CoderItem(id = Rectangle.LOWERLEFTCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate lowerLeftCoordinate;
    
    @CoderItem(id = Rectangle.UPPERRIGHTCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate upperRightCoordinate;
   
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO }, dependentFieldDigit = {0}))
	private ByteFlag selector;
	
    public AbsoluteGeoCoordinate getLowerLeftCoordinate() {
		return lowerLeftCoordinate;
	}
	public void setLowerLeftCoordinate(AbsoluteGeoCoordinate lowerLeftCoordinate) {
		this.lowerLeftCoordinate = lowerLeftCoordinate;
	}

	public AbsoluteGeoCoordinate getUpperRightCoordinate() {
		return upperRightCoordinate;
	}
	public void setUpperRightCoordinate(AbsoluteGeoCoordinate upperRightCoordinate) {
		this.upperRightCoordinate = upperRightCoordinate;
	}

	public ByteFlag getSelector() {
		return selector;
	}
	
	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}
	
}

