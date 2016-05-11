package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LastLocationReferencePoint;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LineProperties;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.RelativeGeoCoordinate;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { LastLocationReferencePoint.RELATIVEGEOCOORDINATE,LastLocationReferencePoint.LINEPROPERTIES}), buildOrder = @EncoderBuildOrder(order = {
		LastLocationReferencePoint.RELATIVEGEOCOORDINATE,LastLocationReferencePoint.LINEPROPERTIES }))
public class LastLocationReferencePoint  extends AbstractCoderItem implements Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1452015530753048590L;

	public static final String RELATIVEGEOCOORDINATE = "relativeGeoCoordinate";

	public static final String LINEPROPERTIES = "lineProperties";
	
	@CoderItem(id = LastLocationReferencePoint.RELATIVEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private RelativeGeoCoordinate relativeGeoCoordinate;
	
	@CoderItem(id = LastLocationReferencePoint.LINEPROPERTIES, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private LineProperties lineProperties;

	public RelativeGeoCoordinate getAbsoluteGeoCoordinate() {
		return relativeGeoCoordinate;
	}

	public void setRelativeGeoCoordinate(RelativeGeoCoordinate relativeGeoCoordinate) {
		this.relativeGeoCoordinate = relativeGeoCoordinate;
	}

	public LineProperties getLineProperties() {
		return lineProperties;
	}

	public void setLineProperties(LineProperties lineProperties) {
		this.lineProperties = lineProperties;
	}
	
	

}
