package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.PathProperties;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, PathProperties.LFRNCP,PathProperties.DNP,PathProperties.SELECTOR, PathProperties.LENGTHATTR, PathProperties.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, PathProperties.LFRNCP,PathProperties.DNP,PathProperties.SELECTOR}))
public class PathProperties extends BaseComponent {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String LFRNCP = "lfrncp";
	
	public static final String DNP = "dnp";
	
	{
		this.setId(10);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}
	

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { PathProperties.LFRNCP,PathProperties.DNP,BaseComponent.SELECTOR}), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长 
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,PathProperties.LFRNCP,PathProperties.DNP,BaseComponent.SELECTOR }), length = -5)
	private NumberField lengthComp;

	/**
	 * 1byte长度
	 */
	@CoderItem(id = PathProperties.LFRNCP, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField lfrncp;
	
	@CoderItem(id = PathProperties.DNP, type = CoderItemType.ITEM, length = -4,toMapper=true)
	private NumberField dnp;
	
	
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { CoderItem.BYTEFLAG_dependentField_DEFAUTL_NO }, dependentFieldDigit = {0}))
	private ByteFlag selector;


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

	public NumberField getLfrncp() {
		return lfrncp;
	}

	public void setLfrncp(int lfrncp) {
		NumberField numberField = (NumberField) this.newInstanceItem(PathProperties.LFRNCP, false);
		numberField.setNumber(lfrncp);
	}

	public NumberField getDnp() {
		return dnp;
	}

	public void setDnp(int dnp) {
		NumberField numberField = (NumberField) this.newInstanceItem(PathProperties.DNP, false);
		numberField.setNumber(dnp);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}


}
