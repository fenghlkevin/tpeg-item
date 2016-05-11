package cn.com.cennavi.tpeg.item.component.tfp;


import java.lang.reflect.Field;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.MMC;
import cn.com.cennavi.tpeg.item.component.comm.lrc.LRC;

/**
 * TFPMessage结构体 注：ID在TFP中不能重复
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.TPEG_FIELDID_MMC, BaseComponent.TPEG_FIELD_LRC, TFPMessage.TFPDATA, BaseComponent.LENGTHATTR,
		BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, BaseComponent.TPEG_FIELDID_MMC,
		TFPMessage.TFPDATA, BaseComponent.TPEG_FIELD_LRC }))
public class TFPMessage extends BaseComponent {
	
	{
		this.setId(0);
		this.setLengthComp(0);
		this.setLengthAttr(0);
	}

	private static final long serialVersionUID = -2096986547225620991L;

	public static final String TFPDATA = "tfpData";

	@CoderItem(id = BaseComponent.TPEG_FIELD_LRC, type = CoderItemType.ITEM, length = 0)
	private LRC lrc;

	@CoderItem(id = BaseComponent.TPEG_FIELDID_MMC, type = CoderItemType.ITEM, length = 0)
	private MMC mmc;

	/**
	 * TFP data component (Flow Matrix)
	 */
	@CoderItem(id = TFPMessage.TFPDATA, type = CoderItemType.ITEM, length = 0)
	private TFPData tfpData;

	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, BaseComponent.TPEG_FIELDID_MMC,
			TFPMessage.TFPDATA, BaseComponent.TPEG_FIELD_LRC }))
	private NumberField lengthComp;

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.ITEM, length = -5)
	private NumberField lengthAttr;

	public LRC getLRC() {
		return lrc;
	}

	public void setLRC(LRC lrc) {
		this.lrc = lrc;
	}

	public MMC getMMC() {
		return mmc;
	}

	public void setMMC(MMC mmc) {
		this.mmc = mmc;
	}

	public TFPData getTfpData() {
		return tfpData;
	}

	public void setTfpData(TFPData tfpData) {
		this.tfpData = tfpData;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	@Override
	public void decoding() {
		Field mmt=ReflectUtil.getClassField(BaseComponent.TPEG_FIELDID_MMC, this.getClass());
		super.decoding(null,mmt);
		if(!this.mmc.getSelector().getLocationValue(0)){
			super.decoding(mmt,null);
		}
	}
}
