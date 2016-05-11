package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.FirstLocationReferencePoint;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LineProperties;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.PathProperties;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { FirstLocationReferencePoint.ABSOLUTEGEOCOORDINATE,FirstLocationReferencePoint.LINEPROPERTIES, FirstLocationReferencePoint.PATHPROPERTIES}), buildOrder = @EncoderBuildOrder(order = {
		FirstLocationReferencePoint.ABSOLUTEGEOCOORDINATE,FirstLocationReferencePoint.LINEPROPERTIES, FirstLocationReferencePoint.PATHPROPERTIES }))
public class FirstLocationReferencePoint  extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = 1452015530753048590L;

	public static final String ABSOLUTEGEOCOORDINATE = "absoluteGeoCoordinate";

	public static final String LINEPROPERTIES = "lineProperties";
	
	public static final String PATHPROPERTIES = "pathProperties";
	
	@CoderItem(id = FirstLocationReferencePoint.ABSOLUTEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private AbsoluteGeoCoordinate absoluteGeoCoordinate;
	
	@CoderItem(id = FirstLocationReferencePoint.LINEPROPERTIES, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private LineProperties lineProperties;
	
	@CoderItem(id = FirstLocationReferencePoint.PATHPROPERTIES, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private PathProperties pathProperties;

	public AbsoluteGeoCoordinate getAbsoluteGeoCoordinate() {
		return absoluteGeoCoordinate;
	}

	public void setAbsoluteGeoCoordinate(AbsoluteGeoCoordinate absoluteGeoCoordinate) {
		this.absoluteGeoCoordinate = absoluteGeoCoordinate;
	}

	public LineProperties getLineProperties() {
		return lineProperties;
	}

	public void setLineProperties(LineProperties lineProperties) {
		this.lineProperties = lineProperties;
	}

	public PathProperties getPathProperties() {
		return pathProperties;
	}

	public void setPathProperties(PathProperties pathProperties) {
		this.pathProperties = pathProperties;
	}
	

}
