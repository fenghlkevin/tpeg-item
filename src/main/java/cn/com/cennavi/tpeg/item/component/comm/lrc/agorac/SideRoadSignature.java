package cn.com.cennavi.tpeg.item.component.comm.lrc.agorac;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * @author fengheliang
 *
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { SideRoadSignature.CONNECTIONANGLE, SideRoadSignature.SELECTOR }), buildOrder = @EncoderBuildOrder(order = { SideRoadSignature.CONNECTIONANGLE,
		SideRoadSignature.SELECTOR }))
public class SideRoadSignature extends AbstractCoderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1441513343396112328L;

	public static final String CONNECTIONANGLE = "connectionAngle";

	public static final String SELECTOR = "selector";

	/**
	 * (bit 0: accessibleForRouting)1 byte
	 */
	@CoderItem(id = SideRoadSignature.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, dependent = @Dependent(dependentField = { CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO }, dependentFieldDigit = { 0 }),length = 1)
	private ByteFlag selector;

	/**
	 * 1 byte
	 */
	@CoderItem(id = SideRoadSignature.CONNECTIONANGLE, type = CoderItemType.ITEM, length = 1)
	private NumberField connectionAngle;

	public ByteFlag getSelector() {
		return selector;
	}

	public NumberField getConnectionAngle() {
		return connectionAngle;
	}

	public void setSelector(ByteFlag selector) {
		this.newInstanceItem(SideRoadSignature.SELECTOR, false);
	}

	public void setConnectionAngle(int connectionAngle) {
		NumberField numberField = (NumberField) this.newInstanceItem(SideRoadSignature.CONNECTIONANGLE, false);
		numberField.setNumber(connectionAngle);
	}

}
