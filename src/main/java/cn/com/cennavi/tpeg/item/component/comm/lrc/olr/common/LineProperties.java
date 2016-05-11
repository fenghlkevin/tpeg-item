package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LineProperties;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, LineProperties.FRC,LineProperties.FOW,LineProperties.BEARING,LineProperties.STBEARINGLEFT,LineProperties.STBEARINGRIGHT, LineProperties.SELECTOR,BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, LineProperties.FRC,LineProperties.FOW,LineProperties.BEARING,LineProperties.SELECTOR,LineProperties.STBEARINGLEFT,LineProperties.STBEARINGRIGHT }))
public class LineProperties extends BaseComponent {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String FRC = "frc";
	
	public static final String FOW = "fow";
	
	public static final String BEARING = "bearing";

	public static final String STBEARINGLEFT = "stBearingLeft";
	
	public static final String STBEARINGRIGHT = "stBearingRight";
	
	{
		this.setId(9);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { LineProperties.FRC,LineProperties.FOW,LineProperties.BEARING,BaseComponent.SELECTOR,LineProperties.STBEARINGLEFT,LineProperties.STBEARINGRIGHT }), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长 
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, LineProperties.FRC,LineProperties.FOW,LineProperties.BEARING,BaseComponent.SELECTOR,LineProperties.STBEARINGLEFT,LineProperties.STBEARINGRIGHT }), length = -5)
	private NumberField lengthComp;

	/**
	 * 1byte长度
	 */
	@CoderItem(id = LineProperties.FRC, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField frc;
	
	@CoderItem(id = LineProperties.FOW, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField fow;
	
	@CoderItem(id = LineProperties.BEARING, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField bearing;
	
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { LineProperties.STBEARINGLEFT,LineProperties.STBEARINGRIGHT  }, dependentFieldDigit = {0,1}))
	private ByteFlag selector;
	
	@CoderItem(id = LineProperties.STBEARINGLEFT, type = CoderItemType.ITEM, length = 1)
	private NumberField stBearingLeft;
	
	@CoderItem(id = LineProperties.STBEARINGRIGHT, type = CoderItemType.ITEM, length = 1)
	private NumberField stBearingRight;


	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public NumberField getFrc() {
		return frc;
	}

	public void setFrc(int frc) {
		NumberField numberField = (NumberField) this.newInstanceItem(LineProperties.FRC, false);
		numberField.setNumber(frc);
	}

	public NumberField getFow() {
		return fow;
	}

	public void setFow(int fow) {
		NumberField numberField = (NumberField) this.newInstanceItem(LineProperties.FOW, false);
		numberField.setNumber(fow);
	}

	public NumberField getBearing() {
		return bearing;
	}

	public void setBearing(int bearing) {
		NumberField numberField = (NumberField) this.newInstanceItem(LineProperties.BEARING, false);
		numberField.setNumber(bearing);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public NumberField getStBearingLeft() {
		return stBearingLeft;
	}

	public void setStBearingLeft(int stBearingLeft) {
		NumberField numberField = (NumberField) this.newInstanceItem(LineProperties.STBEARINGLEFT, false);
		numberField.setNumber(stBearingLeft);
	}

	public NumberField getStBearingRight() {
		return stBearingRight;
	}

	public void setStBearingRight(int stBearingRight) {
		NumberField numberField = (NumberField) this.newInstanceItem(LineProperties.STBEARINGRIGHT, false);
		numberField.setNumber(stBearingRight);
	}


}
