package cn.com.cennavi.tpeg.item.component.tec;

import java.io.IOException;
import java.lang.reflect.Field;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.MMC;
import cn.com.cennavi.tpeg.item.component.comm.lrc.LRC;

/**
 * TECMessage对象，一个servicecomp里面最多255个
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.TPEG_FIELDID_MMC, BaseComponent.TPEG_FIELD_LRC, TECMessage.TECDATA, BaseComponent.LENGTHATTR,
		BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, BaseComponent.TPEG_FIELDID_MMC,
		TECMessage.TECDATA, BaseComponent.TPEG_FIELD_LRC }))
public class TECMessage extends BaseComponent {
	
	{
		this.setId(0);
		this.setLengthComp(0);
		this.setLengthAttr(0);
	}

	private static final long serialVersionUID = -6551037501471834553L;

	public static final String TECDATA = "tecData";
	
	@CoderItem(id = BaseComponent.TPEG_FIELD_LRC, type = CoderItemType.ITEM, length = 0)
	private LRC lrc;
	/**
	 * (如果cancel message，就只有MMC)
	 */
	@CoderItem(id = BaseComponent.TPEG_FIELDID_MMC, type = CoderItemType.ITEM, length = 0)
	private MMC mmc;

	/**
	 * 事件的详细信息
	 */
	@CoderItem(id = TECMessage.TECDATA, type = CoderItemType.ITEM, length = 0)
	private TECData tecData;

	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, BaseComponent.TPEG_FIELDID_MMC, TECMessage.TECDATA,
			BaseComponent.TPEG_FIELD_LRC }), length = -5)
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

	public TECData getTecData() {
		return tecData;
	}

	public void setTecData(TECData tecData) {
		this.tecData = tecData;
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
//	@Override
//	public void decoding() {
//		// BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,
//		// BaseComponent.TPEG_FIELDID_MMC,TFPMessage.TFPDATA
//		super.decoding();
//		// 手动解码 BaseComponent.TPEG_FIELD_LRC
//
//		try {
//			int lrcid = getEncodedByteStream().read();
//
//			if (lrcid == 1) {
////				this.lrc = new AgoracLRC();
//			} else if (lrcid == 2) {
//				this.lrc =new LRC();
//			}
//			this.lrc.setId(lrcid);
//			this.lrc.encoding();
//			this.lrc.setEncodedStream(this.getEncodedByteStream());
//			this.lrc.decoding(ReflectUtil.getClassField(LRC.ID, this.lrc.getClass()));
//		} catch (IOException e) {
//			throw new TPEGDecodeException("create LRC error", e);
//		}
//
//	}

}
