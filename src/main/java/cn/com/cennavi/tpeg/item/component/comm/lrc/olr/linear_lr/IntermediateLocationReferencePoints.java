package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.linear_lr;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LineProperties;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.PathProperties;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.RelativeGeoCoordinate;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { IntermediateLocationReferencePoints.RELATIVEGEOCOORDINATE,IntermediateLocationReferencePoints.LINEPROPERTIES, IntermediateLocationReferencePoints.PATHPROPERTIES}), buildOrder = @EncoderBuildOrder(order = {
		IntermediateLocationReferencePoints.RELATIVEGEOCOORDINATE,IntermediateLocationReferencePoints.LINEPROPERTIES, IntermediateLocationReferencePoints.PATHPROPERTIES }))
public class IntermediateLocationReferencePoints  extends AbstractCoderItem implements Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1452015530753048590L;

	public static final String RELATIVEGEOCOORDINATE = "relativeGeoCoordinate";

	public static final String LINEPROPERTIES = "lineProperties";
	
	public static final String PATHPROPERTIES = "pathProperties";
	
	@CoderItem(id = IntermediateLocationReferencePoints.RELATIVEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private RelativeGeoCoordinate relativeGeoCoordinate;
	
	@CoderItem(id = IntermediateLocationReferencePoints.LINEPROPERTIES, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private LineProperties lineProperties;
	
	@CoderItem(id = IntermediateLocationReferencePoints.PATHPROPERTIES, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private PathProperties pathProperties;

	public RelativeGeoCoordinate getRelativeGeoCoordinate() {
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

	public PathProperties getPathProperties() {
		return pathProperties;
	}

	public void setPathProperties(PathProperties pathProperties) {
		this.pathProperties = pathProperties;
	}
	

}
