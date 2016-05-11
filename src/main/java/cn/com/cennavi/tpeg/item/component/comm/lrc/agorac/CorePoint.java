package cn.com.cennavi.tpeg.item.component.comm.lrc.agorac;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * @author fengheliang
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, CorePoint.LONGITUDE1, CorePoint.LONGITUDE2, CorePoint.LONGITUDE3, CorePoint.LONGITUDEABS4, CorePoint.LATITUDE1,
		CorePoint.LATITUDE2, CorePoint.LATITUDE3, CorePoint.LATITUDEABS4, CorePoint.RPSIGNATURE, CorePoint.IPSIGNATURE, CorePoint.SIDEROADSIGNATURE, CorePoint.ATTRIBUTELIST, BaseComponent.SELECTOR,
		BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, BaseComponent.SELECTOR,
		CorePoint.LONGITUDE1, CorePoint.LONGITUDE2, CorePoint.LONGITUDE3, CorePoint.LONGITUDEABS4, CorePoint.LATITUDE1, CorePoint.LATITUDE2, CorePoint.LATITUDE3, CorePoint.LATITUDEABS4,
		CorePoint.RPSIGNATURE, CorePoint.IPSIGNATURE, CorePoint.SIDEROADSIGNATURE, CorePoint.ATTRIBUTELIST }))
public class CorePoint extends BaseComponent {

	private static final long serialVersionUID = 1L;

	public static final String LONGITUDE1 = "longitude1";

	public static final String LONGITUDE2 = "longitude2";

	public static final String LONGITUDE3 = "longitude3";

	public static final String LONGITUDEABS4 = "longitudeAbs4";

	public static final String LATITUDE1 = "latitude1";

	public static final String LATITUDE2 = "latitude2";

	public static final String LATITUDE3 = "latitude3";

	public static final String LATITUDEABS4 = "latitudeAbs4";

	public static final String RPSIGNATURE = "rpSignature";

	public static final String IPSIGNATURE = "ipSignature";

	public static final String SIDEROADSIGNATURE = "sideRoadSignature";

	public static final String ATTRIBUTELIST = "attributeList";

	@CoderItem(id = CorePoint.ATTRIBUTELIST, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private AttributeList attributeList;

	@CoderItem(id = CorePoint.SIDEROADSIGNATURE, type = CoderItemType.ITEM, length = 0)
	private SideRoadSignature sideRoadSignature;

	@CoderItem(id = CorePoint.RPSIGNATURE, type = CoderItemType.ITEM, length = 0)
	private RPSignature rpSignature;

	@CoderItem(id = CorePoint.IPSIGNATURE, type = CoderItemType.ITEM, length = 0)
	private IPSignature ipSignature;

	/**
	 * (bit 0: lcoationPoint\ bit 1:Dperp\ Bit 2: longitude 1\ Bit 3: longitude
	 * 2\ Bit 4: longitudeAbs 3\ Bit 5: longitudeAbs 4\ Bit 6: latitude 1\ Bit
	 * 7: latitude 2\ Bit 8: latitudeAbs 3\ Bit 9: latitudeAbs 4\ Bit 10:
	 * RFSignature\ Bit 11: IPSignature\ Bit 12: SideRoadSignature\ ) 2 bytes
	 */
	@CoderItem(id = CorePoint.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = -2, dependent = @Dependent(dependentField = { CorePoint.LONGITUDE1, CorePoint.LONGITUDE2,
			CorePoint.LONGITUDE3, CorePoint.LONGITUDEABS4, CorePoint.LATITUDE1, CorePoint.LATITUDE2, CorePoint.LATITUDE3, CorePoint.LATITUDEABS4, CorePoint.RPSIGNATURE, CorePoint.IPSIGNATURE,
			CorePoint.SIDEROADSIGNATURE }, dependentFieldDigit = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }))
	private ByteFlag selector;

	
	/**
	 * 锟斤拷锟斤拷一
	 */
	@CoderItem(id = CorePoint.LONGITUDE1, type = CoderItemType.ITEM, length = 1)
	private NumberField longitude1;

	/**
	 * 锟斤拷锟斤拷2
	 */
	@CoderItem(id = CorePoint.LONGITUDE2, type = CoderItemType.ITEM, length = 2)
	private NumberField longitude2;

	/**
	 * 锟斤拷锟斤拷3
	 */
	@CoderItem(id = CorePoint.LONGITUDE3, type = CoderItemType.ITEM, length = 3)
	private NumberField longitude3;

	/**
	 * 锟斤拷锟斤拷4
	 */
	@CoderItem(id = CorePoint.LONGITUDEABS4, type = CoderItemType.ITEM, length = -5)
	private NumberField longitudeAbs4;

	/**
	 * 纬锟斤拷1
	 */
	@CoderItem(id = CorePoint.LATITUDE1, type = CoderItemType.ITEM, length = 1)
	private NumberField latitude1;
	/**
	 * 纬锟斤拷2
	 */
	@CoderItem(id = CorePoint.LATITUDE2, type = CoderItemType.ITEM, length = 2)
	private NumberField latitude2;
	/**
	 * 纬锟斤拷3
	 */
	@CoderItem(id = CorePoint.LATITUDE3, type = CoderItemType.ITEM, length = 3)
	private NumberField latitude3;
	/**
	 * 纬锟斤拷4
	 */
	@CoderItem(id = CorePoint.LATITUDEABS4, type = CoderItemType.ITEM, length = -5)
	private NumberField latitudeAbs4;
	/**
	 * 1~5bytes 锟戒长锟街斤拷
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.SELECTOR, CorePoint.LONGITUDE1, CorePoint.LONGITUDE2,
			CorePoint.LONGITUDE3, CorePoint.LONGITUDEABS4, CorePoint.LATITUDE1, CorePoint.LATITUDE2, CorePoint.LATITUDE3, CorePoint.LATITUDEABS4, CorePoint.RPSIGNATURE, CorePoint.IPSIGNATURE,
			CorePoint.SIDEROADSIGNATURE, CorePoint.ATTRIBUTELIST }), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes锟戒长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, BaseComponent.SELECTOR, CorePoint.LONGITUDE1,
			CorePoint.LONGITUDE2, CorePoint.LONGITUDE3, CorePoint.LONGITUDEABS4, CorePoint.LATITUDE1, CorePoint.LATITUDE2, CorePoint.LATITUDE3, CorePoint.LATITUDEABS4, CorePoint.RPSIGNATURE,
			CorePoint.IPSIGNATURE, CorePoint.SIDEROADSIGNATURE, CorePoint.ATTRIBUTELIST }), length = -5)
	private NumberField lengthComp;

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR, false);
	}

	public NumberField getLongitude1() {
		return longitude1;
	}

	public NumberField getLongitude2() {
		return longitude2;
	}

	public NumberField getLongitude3() {
		return longitude3;
	}

	public NumberField getLongitudeAbs4() {
		return longitudeAbs4;
	}

	public NumberField getLatitude1() {
		return latitude1;
	}

	public NumberField getLatitude2() {
		return latitude2;
	}

	public NumberField getLatitude3() {
		return latitude3;
	}

	public NumberField getLatitudeAbs4() {
		return latitudeAbs4;
	}

	public void setLongitude1(int longitude1) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LONGITUDE1, false);
		numberField.setNumber(longitude1);
	}

	public void setLongitude2(int longitude2) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LONGITUDE2, false);
		numberField.setNumber(longitude2);
	}

	public void setLongitude3(int longitude3) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LONGITUDE3, false);
		numberField.setNumber(longitude3);
	}

	public void setLongitudeAbs4(int longitudeAbs4) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LONGITUDEABS4, false);
		numberField.setNumber(longitudeAbs4);
	}

	public void setLatitude1(int latitude1) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LATITUDE1, false);
		numberField.setNumber(latitude1);
	}

	public void setLatitude2(int latitude2) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LATITUDE2, false);
		numberField.setNumber(latitude2);
	}

	public void setLatitude3(int latitude3) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LATITUDE3, false);
		numberField.setNumber(latitude3);
	}

	public void setLatitudeAbs4(int latitudeAbs4) {
		NumberField numberField = (NumberField) this.newInstanceItem(CorePoint.LATITUDEABS4, false);
		numberField.setNumber(latitudeAbs4);
	}

	public AttributeList getAttributeList() {
		return attributeList;
	}

	public SideRoadSignature getSideRoadSignature() {
		return sideRoadSignature;
	}

	public RPSignature getRpSignature() {
		return rpSignature;
	}

	public IPSignature getIpSignature() {
		return ipSignature;
	}

	public void setAttributeList(AttributeList attributeList) {
		this.attributeList = attributeList;
	}

	public void setSideRoadSignature(SideRoadSignature sideRoadSignature) {
		this.sideRoadSignature = sideRoadSignature;
	}

	public void setRpSignature(RPSignature rpSignature) {
		this.rpSignature = rpSignature;
	}

	public void setIpSignature(IPSignature ipSignature) {
		this.ipSignature = ipSignature;
	}
}
