package cn.com.cennavi.tpeg.item.component.comm.lrc.tmc;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.core.base.StringItem;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.LocationReference;

/**
 * TMCLocationReference
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={BaseComponent.ID,TMCLocationReference.LOCATIONID,TMCLocationReference.COUNTRYCODE,TMCLocationReference.LOCATIONTABLENUMBER,TMCLocationReference.EXTENT,TMCLocationReference.EXTENDEDCOUNTRYCODE,TMCLocationReference.LOCATIONTABLEVERSION,TMCLocationReference.PRECISETMC,BaseComponent.SELECTOR,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
		buildOrder=@EncoderBuildOrder(order={BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,TMCLocationReference.LOCATIONID,TMCLocationReference.COUNTRYCODE,TMCLocationReference.LOCATIONTABLENUMBER,BaseComponent.SELECTOR,TMCLocationReference.EXTENT,TMCLocationReference.EXTENDEDCOUNTRYCODE,TMCLocationReference.LOCATIONTABLEVERSION,TMCLocationReference.PRECISETMC}))
public class TMCLocationReference extends LocationReference {
	
	{
		this.setId(2);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}
	
	private static final long serialVersionUID = -1189874689001114733L;
	
	public static final String LOCATIONID="locationID";
	
	public static final String COUNTRYCODE="countryCode";
	
	public static final String LOCATIONTABLENUMBER="locationTableNumber";
	
	public static final String EXTENT="extent";
	
	public static final String EXTENDEDCOUNTRYCODE="extendedCountryCode";
	
	public static final String LOCATIONTABLEVERSION="locationTableVersion";
	
	public static final String PRECISETMC="preciseTMC";
	

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id=BaseComponent.LENGTHATTR, type=CoderItemType.LENGTH,
			dependent=@Dependent(dependentField={TMCLocationReference.LOCATIONID,TMCLocationReference.COUNTRYCODE,TMCLocationReference.LOCATIONTABLENUMBER,BaseComponent.SELECTOR,TMCLocationReference.EXTENT,TMCLocationReference.EXTENDEDCOUNTRYCODE,TMCLocationReference.LOCATIONTABLEVERSION,TMCLocationReference.PRECISETMC})
			,length=-5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id=BaseComponent.LENGTHCOMP, type=CoderItemType.LENGTH,
			dependent=@Dependent(dependentField={BaseComponent.LENGTHATTR,TMCLocationReference.LOCATIONID,TMCLocationReference.COUNTRYCODE,TMCLocationReference.LOCATIONTABLENUMBER,BaseComponent.SELECTOR,TMCLocationReference.EXTENT,TMCLocationReference.EXTENDEDCOUNTRYCODE,TMCLocationReference.LOCATIONTABLEVERSION,TMCLocationReference.PRECISETMC})
			,length=-5)
	private NumberField lengthComp;
	/**
	 * 1byte
	 */
	@CoderItem(id=TMCLocationReference.COUNTRYCODE, type=CoderItemType.ITEM,length=1)
	private NumberField countryCode;
	/**
	 * fix value=240 1byte
	 */
	@CoderItem(id=TMCLocationReference.EXTENDEDCOUNTRYCODE, type=CoderItemType.ITEM,length=1)
	private NumberField extendedCountryCode;
	/**
	 * 1 byte
	 */
	@CoderItem(id=TMCLocationReference.EXTENT, type=CoderItemType.ITEM,length=1)
	private NumberField extent;
	/**
	 * 2byte
	 */
	@CoderItem(id=TMCLocationReference.LOCATIONID, type=CoderItemType.ITEM,length=2)
	private NumberField locationID;
	/**
	 * 1 byte
	 */
	@CoderItem(id=TMCLocationReference.LOCATIONTABLENUMBER, type=CoderItemType.ITEM,length=1)
	private NumberField locationTableNumber;
	/**
	 * 1~5 bytes
	 */
	@CoderItem(id=TMCLocationReference.LOCATIONTABLEVERSION, type=CoderItemType.ITEM,length=-5)
	private StringItem locationTableVersion;
	
	/**
	 * 暂时无用
	 */
	@CoderItem(id=TMCLocationReference.PRECISETMC, type=CoderItemType.ITEM,length=0)
	private PreciseTMC preciseTMC;
	/**
	 * (bit 0: dicrection
	 * bit 1: bothDirection
	 * bit 2: extent
	 * bit 3: extendedCountryCode
	 * bit 4: locationTableVersion
	 * bit 5: preciseTMC)
	 * 1 byte
	 */
	@CoderItem(id=BaseComponent.SELECTOR, type=CoderItemType.FLAGITEM_SECLECTOR,length=1
			,dependent=@Dependent(
					dependentField={TMCLocationReference.EXTENT,TMCLocationReference.EXTENDEDCOUNTRYCODE,TMCLocationReference.LOCATIONTABLEVERSION,TMCLocationReference.PRECISETMC},
					dependentFieldDigit={2,3,4,5}))
	private ByteFlag selector;

	public NumberField getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		NumberField numberField = (NumberField) this.newInstanceItem(TMCLocationReference.COUNTRYCODE,false);
		numberField.setNumber(countryCode);
	}

	public NumberField getExtendedCountryCode() {
		return extendedCountryCode;
	}

	public void setExtendedCountryCode(int extendedCountryCode) {
		NumberField numberField = (NumberField) this.newInstanceItem(TMCLocationReference.EXTENDEDCOUNTRYCODE,false);
		numberField.setNumber(extendedCountryCode);
	}

	public NumberField getExtent() {
		return extent;
	}

	public void setExtent(int extent) {
		NumberField numberField = (NumberField) this.newInstanceItem(TMCLocationReference.EXTENT,false);
		numberField.setNumber(extent);
	}

	public NumberField getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		NumberField numberField = (NumberField) this.newInstanceItem(TMCLocationReference.LOCATIONID,false);
		numberField.setNumber(locationID);
	}

	public NumberField getLocationTableNumber() {
		return locationTableNumber;
	}

	public void setLocationTableNumber(int locationTableNumber) {
		NumberField numberField = (NumberField) this.newInstanceItem(TMCLocationReference.LOCATIONTABLENUMBER,false);
		numberField.setNumber(locationTableNumber);
	}

	public StringItem getLocationTableVersion() {
		return locationTableVersion;
	}

	public void setLocationTableVersion(String locationTableVersion) {
		StringItem numberField = (StringItem) this.newInstanceItem(TMCLocationReference.LOCATIONTABLEVERSION,false);
		numberField.setStr(locationTableVersion);
	}

	public PreciseTMC getPreciseTMC() {
		return preciseTMC;
	}

	public void setPreciseTMC(PreciseTMC preciseTMC) {
		this.preciseTMC = preciseTMC;
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR,false);
		numberField.setNumber(lengthAttr);
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP,false);
		numberField.setNumber(lengthComp);
	}

}
