package cn.com.cennavi.tpeg.item.component.tfp;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { FlowVectorSection.SPATIALOFFSET, FlowVectorSection.STATUS, TFPData.SPATIALRESOLUTION, FlowVectorSection.SELECTOR }), buildOrder = @EncoderBuildOrder(order = {
		FlowVectorSection.SPATIALOFFSET, FlowVectorSection.STATUS, FlowVectorSection.SELECTOR, TFPData.SPATIALRESOLUTION }))
public class FlowVectorSection extends AbstractCoderItem implements Item {
	
	{
		this.setSelector();
	}

	private static final long serialVersionUID = -5328385979901296241L;

	public static final String SELECTOR = "selector";

	public static final String SPATIALOFFSET = "spatialOffset";

	public static final String STATUS = "status";
	/**
	 * (bit 0: spatialResolution 1 byte
	 */
	@CoderItem(id = FlowVectorSection.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { TFPData.SPATIALRESOLUTION }, dependentFieldDigit = { 0 }))
	private ByteFlag selector;
	/**
	 * 1~5byte
	 */
	@CoderItem(id = FlowVectorSection.SPATIALOFFSET, type = CoderItemType.ITEM, length = -5)
	private NumberField spatialOffset;

	/**
	 * 1 byte
	 */
	@CoderItem(id = TFPData.SPATIALRESOLUTION, type = CoderItemType.ITEM, length = 1)
	private NumberField spatialResolution;

	@CoderItem(id = FlowVectorSection.STATUS, type = CoderItemType.ITEM, length = 0)
	private Status status;

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		 this.newInstanceItem(FlowVectorSection.SELECTOR,false);
	}

	public NumberField getSpatialOffset() {
		return spatialOffset;
	}

	public void setSpatialOffset(int spatialOffset) {
		NumberField numberField = (NumberField) this.newInstanceItem(FlowVectorSection.SPATIALOFFSET,false);
		numberField.setNumber(spatialOffset);
	}

	public NumberField getSpatialResolution() {
		return spatialResolution;
	}

	public void setSpatialResolution(int spatialResolution) {
		NumberField numberField = (NumberField) this.newInstanceItem(TFPData.SPATIALRESOLUTION,false);
		numberField.setNumber(spatialResolution);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
