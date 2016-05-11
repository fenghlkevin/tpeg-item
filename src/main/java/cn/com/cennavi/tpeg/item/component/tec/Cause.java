package cn.com.cennavi.tpeg.item.component.tec;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.linear_lr.IntermediateLocationReferencePoints;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.linear_lr.LinearLocationReference;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = {BaseComponent.ID, Cause.MAINCAUSE, Cause.WARNINGLEVEL, Cause.SUBCAUSE, Cause.LENGTHAFFECTED, Cause.LANERESTRICTIONTYPE,
		Cause.NUMBEROFLANES, Cause.N, Cause.FREETEXT, BaseComponent.SELECTOR, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP}), 
		buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, Cause.MAINCAUSE, Cause.WARNINGLEVEL, BaseComponent.SELECTOR, Cause.SUBCAUSE, Cause.LENGTHAFFECTED,
		Cause.LANERESTRICTIONTYPE, Cause.NUMBEROFLANES, Cause.N, Cause.FREETEXT}))
public class Cause extends BaseComponent {

	{
		this.setId(4);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}

	private static final long serialVersionUID = -7795395723986634994L;

	public static final String MAINCAUSE = "mainCause";

	public static final String WARNINGLEVEL = "warningLevel";

	public static final String SUBCAUSE = "subCause";

	public static final String LENGTHAFFECTED = "lengthAffected";

	public static final String LANERESTRICTIONTYPE = "laneRestrictionType";

	public static final String NUMBEROFLANES = "numberOfLanes";

	public static final String N = "n";

	public static final String FREETEXT = "freeText";

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = {Cause.MAINCAUSE, Cause.WARNINGLEVEL,
			BaseComponent.SELECTOR, Cause.SUBCAUSE, Cause.LENGTHAFFECTED, Cause.LANERESTRICTIONTYPE, Cause.NUMBEROFLANES, Cause.N, Cause.FREETEXT}))
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = {BaseComponent.LENGTHATTR, Cause.MAINCAUSE,
			Cause.WARNINGLEVEL, BaseComponent.SELECTOR, Cause.SUBCAUSE, Cause.LENGTHAFFECTED, Cause.LANERESTRICTIONTYPE, Cause.NUMBEROFLANES, Cause.N, Cause.FREETEXT}))
	private NumberField lengthComp;

	/**
	 * 1byte
	 */
	@CoderItem(id = Cause.LANERESTRICTIONTYPE, type = CoderItemType.ITEM, length = 1)
	private NumberField laneRestrictionType;
	/**
	 * 1~5bytes
	 */
	@CoderItem(id = Cause.LENGTHAFFECTED, type = CoderItemType.ITEM, length = -5)
	private NumberField lengthAffected;
	/**
	 * (tec002) 1 byte
	 */
	@CoderItem(id = Cause.MAINCAUSE, type = CoderItemType.ITEM, length = 1)
	private NumberField mainCause;
	/**
	 * 1 byte
	 */
	@CoderItem(id = Cause.NUMBEROFLANES, type = CoderItemType.ITEM, length = 1)
	private NumberField numberOfLanes;
	/**
	 * (bit 0: unverifiedInformation bit 1: subCause bit 2: lengthAffected bit
	 * 3: laneRestrictionType bit 4: numberOfLanes) 1 byte
	 */
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {Cause.SUBCAUSE, Cause.LENGTHAFFECTED,
			Cause.LANERESTRICTIONTYPE, Cause.NUMBEROFLANES, Cause.FREETEXT}, dependentFieldDigit = {1, 2, 3, 4, 5}))
	private ByteFlag selector;

	@CoderItem(id = Cause.N, type = CoderItemType.ITEM, length = -5)
	private NumberField n;

	@CoderItem(id = Cause.FREETEXT, type = CoderItemType.LIST, length = 0)
	private List<LocalisedShortString> freeText;

	/**
	 * 1 byte
	 */
	@CoderItem(id = Cause.SUBCAUSE, type = CoderItemType.ITEM, length = 1)
	private NumberField subCause;
	/**
	 * 1 byte
	 */
	@CoderItem(id = Cause.WARNINGLEVEL, type = CoderItemType.ITEM, length = 1)
	private NumberField warningLevel;

	public NumberField getLaneRestrictionType() {
		return laneRestrictionType;
	}

	public void setLaneRestrictionType(int laneRestrictionType) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.LANERESTRICTIONTYPE, false);
		numberField.setNumber(laneRestrictionType);
	}

	public NumberField getLengthAffected() {
		return lengthAffected;
	}

	public void setLengthAffected(int lengthAffected) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.LENGTHAFFECTED, false);
		numberField.setNumber(lengthAffected);
	}

	public NumberField getMainCause() {
		return mainCause;
	}

	public void setMainCause(int mainCause) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.MAINCAUSE, false);
		numberField.setNumber(mainCause);
	}

	public NumberField getNumberOfLanes() {
		return numberOfLanes;
	}

	public void setNumberOfLanes(int numberOfLanes) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.NUMBEROFLANES, false);
		numberField.setNumber(numberOfLanes);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR, false);
	}

	public NumberField getSubCause() {
		return subCause;
	}

	public void setSubCause(int subCause) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.SUBCAUSE, false);
		numberField.setNumber(subCause);
	}

	public NumberField getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(int warningLevel) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.WARNINGLEVEL, false);
		numberField.setNumber(warningLevel);
	}

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public List<LocalisedShortString> getFreeText() {
		return freeText;
	}

	public void setFreeText(List<LocalisedShortString> freeText) {
		NumberField numberField = (NumberField) this.newInstanceItem(Cause.N, false);
		numberField.setNumber(freeText.size());
		this.freeText = freeText;
	}
	
	@Override
	public void decoding() {
		Field numberOfLanes=ReflectUtil.getClassField(Cause.NUMBEROFLANES,this.getClass());
		super.decoding(null, numberOfLanes);
		if(this.getSelector().getLocationValue(5)){
			super.decoding(numberOfLanes, ReflectUtil.getClassField(Cause.N, this.getClass()));
			int count=this.n.getNumber();
			this.freeText=new ArrayList<LocalisedShortString>(count);
			for(int i=0;i<count;i++){
				LocalisedShortString ss=new LocalisedShortString();
				freeText.add(ss);
				ss.setEncodedStream(this.getEncodedByteStream());
				ss.decoding();
			}
		}
		Field intermediateLocationReference=ReflectUtil.getClassField(Cause.FREETEXT,this.getClass());
		super.decoding(intermediateLocationReference, null);
	}
}
