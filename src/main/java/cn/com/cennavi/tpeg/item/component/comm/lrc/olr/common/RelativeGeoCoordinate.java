package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.RelativeGeoCoordinate;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { RelativeGeoCoordinate.LONGITUDE,RelativeGeoCoordinate.LATITUDE, BaseComponent.SELECTOR}), buildOrder = @EncoderBuildOrder(order = {
		RelativeGeoCoordinate.LONGITUDE,RelativeGeoCoordinate.LATITUDE, BaseComponent.SELECTOR }))
public class RelativeGeoCoordinate  extends AbstractCoderItem implements Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1452015530753048590L;

	public static final String LONGITUDE = "longitude";

	public static final String LATITUDE = "latitude";
	
	{
		this.setSelector();
	}
	
	@CoderItem(id = RelativeGeoCoordinate.LONGITUDE, type = CoderItemType.ITEM, length = 2,toMapper=true)
	private NumberField longitude;
	
	@CoderItem(id = RelativeGeoCoordinate.LATITUDE, type = CoderItemType.ITEM, length = 2,toMapper=true)
	private NumberField latitude;
	
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO }, dependentFieldDigit = {0}))
	private ByteFlag selector;

	public NumberField getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		NumberField numberField = (NumberField) this.newInstanceItem(RelativeGeoCoordinate.LONGITUDE, false);
		numberField.setNumber(longitude);
	}

	public NumberField getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		NumberField numberField = (NumberField) this.newInstanceItem(RelativeGeoCoordinate.LATITUDE, false);
		numberField.setNumber(latitude);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}
}
