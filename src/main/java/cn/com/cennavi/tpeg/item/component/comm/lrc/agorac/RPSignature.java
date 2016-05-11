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
 * 
 * @author fengheliang
 *
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { RPSignature.BEARING, RPSignature.ROUTINGPOINTDISTANCE, RPSignature.PARCARRIAGEWAYIND, RPSignature.SELECTOR }), buildOrder = @EncoderBuildOrder(order = {
		RPSignature.BEARING, RPSignature.SELECTOR, RPSignature.ROUTINGPOINTDISTANCE, RPSignature.PARCARRIAGEWAYIND }))
public class RPSignature extends AbstractCoderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8195168421723982351L;

	public static final String BEARING = "bearing";

	public static final String SELECTOR = "selector";

	public static final String ROUTINGPOINTDISTANCE = "routingPointDistance";

	public static final String PARCARRIAGEWAYIND = "parCarriagewayInd";

	/**
	 * 1 byte
	 */
	@CoderItem(id = RPSignature.BEARING, type = CoderItemType.ITEM, length = 1)
	private NumberField bearing;

	/**
	 * 1 byte (bit 0: accessibleForRouting /bit 1: routingPointDistance0 /Bit 2:
	 * routingPointDistancePrecision /Bit 3: routingPointDistance /Bit 4:
	 * parCarriagewayInd )
	 */
	@CoderItem(id = RPSignature.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { RPSignature.ROUTINGPOINTDISTANCE,
			RPSignature.PARCARRIAGEWAYIND }, dependentFieldDigit = { 3, 4 }))
	private ByteFlag selector;

	/**
	 * 1~5 byte
	 */
	@CoderItem(id = RPSignature.ROUTINGPOINTDISTANCE, type = CoderItemType.ITEM, length = -5)
	private NumberField routingPointDistance;

	/**
	 * 1~5 byte
	 */
	@CoderItem(id = RPSignature.PARCARRIAGEWAYIND, type = CoderItemType.ITEM, length = -5)
	private NumberField parCarriagewayInd;

	public NumberField getBearing() {
		return bearing;
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public NumberField getRoutingPointDistance() {
		return routingPointDistance;
	}

	public NumberField getParCarriagewayInd() {
		return parCarriagewayInd;
	}

	public void setBearing(int bearing) {
		NumberField numberField = (NumberField) this.newInstanceItem(RPSignature.BEARING, false);
		numberField.setNumber(bearing);
	}

	public void setSelector() {
		this.newInstanceItem(RPSignature.SELECTOR, false);
	}

	public void setRoutingPointDistance(int routingPointDistance) {
		NumberField numberField = (NumberField) this.newInstanceItem(RPSignature.ROUTINGPOINTDISTANCE, false);
		numberField.setNumber(routingPointDistance);
	}

	public void setParCarriagewayInd(int parCarriagewayInd) {
		NumberField numberField = (NumberField) this.newInstanceItem(RPSignature.PARCARRIAGEWAYIND, false);
		numberField.setNumber(parCarriagewayInd);
	}

}
