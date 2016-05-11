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
 * Status结构体
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={Status.LOS,Status.AVERAGESPEED,Status.DELAY,Status.SELECTOR}), 
		buildOrder=@EncoderBuildOrder(order={Status.SELECTOR,Status.LOS,Status.AVERAGESPEED,Status.DELAY}))
public class Status extends AbstractCoderItem  implements Item {
	
	{
		this.setSelector();
	}
	
	private static final long serialVersionUID = 6699927430889866395L;
	
	/**
	 * Stationary traffic： Code 005
		Quering traffic：Code 004
		Slow traffic ：Code 003
		Free traffic flow :Code 001
	 */
	public static final String LOS="los";
	
	public static final String AVERAGESPEED="averageSpeed";
	
	public static final String DELAY="delay";
	
	public static final String SELECTOR="selector";
	/**
	 * (km/h)
	 * 1 byte
	 */
	@CoderItem(id=Status.AVERAGESPEED, type=CoderItemType.ITEM,length=1)
	private NumberField averageSpeed;
	/**
	 * (分钟)
	 * 1~5 bytes
	 */
	@CoderItem(id=Status.DELAY, type=CoderItemType.ITEM,length=-5)
	private NumberField delay;
	/**
	 * 拥堵情况
	 */
	@CoderItem(id=Status.LOS, type=CoderItemType.ITEM,length=1)
	private NumberField los;
	/**
	 * (bit 0: LOS
	 * bit 1: averageSpeed
	 * bit 3: delay)
	 * 1 byte
	 */
	@CoderItem(id=Status.SELECTOR, type=CoderItemType.FLAGITEM_SECLECTOR,length=1
			,dependent=@Dependent(
					dependentField={Status.LOS,Status.AVERAGESPEED,Status.DELAY},
					dependentFieldDigit={0,1,3}))
	private ByteFlag selector;

	public NumberField getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(int averageSpeed) {
		NumberField numberField = (NumberField) this.newInstanceItem(Status.AVERAGESPEED,false);
		numberField.setNumber(averageSpeed);
	}

	public NumberField getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		NumberField numberField = (NumberField) this.newInstanceItem(Status.DELAY,false);
		numberField.setNumber(delay);
	}

	public NumberField getLos() {
		return los;
	}

	public void setLos(int los) {
		NumberField numberField = (NumberField) this.newInstanceItem(Status.LOS,false);
		numberField.setNumber(los);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(Status.SELECTOR,false);
	}
}
