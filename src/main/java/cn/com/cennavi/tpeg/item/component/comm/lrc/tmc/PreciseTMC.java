package cn.com.cennavi.tpeg.item.component.comm.lrc.tmc;

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

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={PreciseTMC.DISTANCEACCURACY,PreciseTMC.HAZARDDISTANCE1,PreciseTMC.HAZARDDISTANCE2,PreciseTMC.PROBLEMLENGTH1,PreciseTMC.PROBLEMLENGTH2,"selector"}), 
		buildOrder=@EncoderBuildOrder(order={"selector",PreciseTMC.DISTANCEACCURACY,PreciseTMC.HAZARDDISTANCE1,PreciseTMC.HAZARDDISTANCE2,PreciseTMC.PROBLEMLENGTH1,PreciseTMC.PROBLEMLENGTH2}))
public class PreciseTMC extends AbstractCoderItem implements Item {
	
	private static final long serialVersionUID = 6386487379822453889L;
	
	public static final String DISTANCEACCURACY="distanceAccuracy";
	
	public static final String HAZARDDISTANCE1="hazardDistance1";
	
	public static final String HAZARDDISTANCE2="hazardDistance2";
	
	public static final String PROBLEMLENGTH1="problemLength1";
	
	public static final String PROBLEMLENGTH2="problemLength2";
	
	/**
	 * 1 byte
	 */
	@CoderItem(id=PreciseTMC.DISTANCEACCURACY, type=CoderItemType.ITEM,length=1)
	private NumberField distanceAccuracy;
	/**
	 * 1 byte
	 */
	@CoderItem(id=PreciseTMC.HAZARDDISTANCE1, type=CoderItemType.ITEM,length=1)
	private NumberField hazardDistance1;
	/**
	 * 2 bytes
	 */
	@CoderItem(id=PreciseTMC.HAZARDDISTANCE2, type=CoderItemType.ITEM,length=2)
	private NumberField hazardDistance2;
	/**
	 * 1 byte
	 */
	@CoderItem(id=PreciseTMC.PROBLEMLENGTH1, type=CoderItemType.ITEM,length=1)
	private NumberField problemLength1;
	/**
	 * 2 bytes
	 */
	@CoderItem(id=PreciseTMC.PROBLEMLENGTH2, type=CoderItemType.ITEM,length=2)
	private NumberField problemLength2;
	/**
	 * (bit 0: distanceAccuracy bit 1: hazardDistance1 bit 2: hazardDistance2
	 * bit 3: problemLength1 bit 4: problemLength2) 1 byte
	 */
	@CoderItem(id="selector", type=CoderItemType.FLAGITEM_SECLECTOR,length=1
			,dependent=@Dependent(
					dependentField={PreciseTMC.DISTANCEACCURACY,PreciseTMC.HAZARDDISTANCE1,PreciseTMC.HAZARDDISTANCE2,PreciseTMC.PROBLEMLENGTH1,PreciseTMC.PROBLEMLENGTH2},
					dependentFieldDigit={0,1,2,3,4}))
	private ByteFlag selector;


	public NumberField getDistanceAccuracy() {
		return distanceAccuracy;
	}

	public void setDistanceAccuracy(int distanceAccuracy) {
		NumberField numberField = (NumberField) this.newInstanceItem(PreciseTMC.DISTANCEACCURACY,false);
		numberField.setNumber(distanceAccuracy);
	}

	public NumberField getHazardDistance1() {
		return hazardDistance1;
	}

	public void setHazardDistance1(int hazardDistance1) {
		NumberField numberField = (NumberField) this.newInstanceItem(PreciseTMC.HAZARDDISTANCE1,false);
		numberField.setNumber(hazardDistance1);
	}

	public NumberField getHazardDistance2() {
		return hazardDistance2;
	}

	public void setHazardDistance2(int hazardDistance2) {
		NumberField numberField = (NumberField) this.newInstanceItem(PreciseTMC.HAZARDDISTANCE2,false);
		numberField.setNumber(hazardDistance2);
	}

	public NumberField getProblemLength1() {
		return problemLength1;
	}

	public void setProblemLength1(int problemLength1) {
		NumberField numberField = (NumberField) this.newInstanceItem(PreciseTMC.PROBLEMLENGTH1,false);
		numberField.setNumber(problemLength1);
	}

	public NumberField getProblemLength2() {
		return problemLength2;
	}

	public void setProblemLength2(int problemLength2) {
		NumberField numberField = (NumberField) this.newInstanceItem(PreciseTMC.PROBLEMLENGTH2,false);
		numberField.setNumber(problemLength2);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}
}
