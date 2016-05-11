package cn.com.cennavi.tpeg.item;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.CRC;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * 帧结构体
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { TransportFrame.SYNCWORD, TransportFrame.FRAMETYPE, TransportFrame.SERVICEFRAME, TransportFrame.FIELDLENGTH, TransportFrame.HEADCRC }), buildOrder = @EncoderBuildOrder(order = {
		TransportFrame.SYNCWORD, TransportFrame.FIELDLENGTH, TransportFrame.HEADCRC, TransportFrame.FRAMETYPE, TransportFrame.SERVICEFRAME }))
public class TransportFrame extends Frame {

	public static final String SYNCWORD = "syncWord";

	public static final String FRAMETYPE = "frameType";

	public static final String SERVICEFRAME = "serviceFrame";

	public static final String FIELDLENGTH = "fieldLength";

	public static final String HEADCRC = "headCRC";

	private static final long serialVersionUID = 5766327792428349280L;

	/**
	 * 2 bytes 固定值：0xFF0F
	 */
	@CoderItem(id = TransportFrame.SYNCWORD, type = CoderItemType.ITEM, length = 2)
	private NumberField syncWord;

	/**
	 * Service Frame 长度， 2 bytes
	 */
	@CoderItem(id = TransportFrame.FIELDLENGTH, type = CoderItemType.LENGTH, length = 2, dependent = @Dependent(dependentField = { TransportFrame.SERVICEFRAME }))
	private NumberField fieldLength;

	/**
	 * 去掉CRC的前16个bytes 2 bytes
	 */
	@CoderItem(id = TransportFrame.HEADCRC, type = CoderItemType.CRC, length = 2, dependent = @Dependent(dependentField = { TransportFrame.SYNCWORD, TransportFrame.FIELDLENGTH,
			TransportFrame.FRAMETYPE, TransportFrame.SERVICEFRAME }, dependentFieldDigit = { 2, 2, 1, 11 }))
	private CRC headCRC;

	/**
	 * 0x01: Service Frame(暂时固定为01) 1 byte
	 */
	@CoderItem(id = TransportFrame.FRAMETYPE, type = CoderItemType.ITEM, length = 1)
	private NumberField frameType;

	/**
	 * service对象
	 */
	@CoderItem(id = TransportFrame.SERVICEFRAME, type = CoderItemType.ITEM, length = 0)
	private ServiceFrame serviceFrame;

	public ServiceFrame getServiceFrame() {
		return serviceFrame;
	}

	public void setServiceFrame(ServiceFrame serviceFrame) {
		this.serviceFrame = serviceFrame;
	}

	public NumberField getSyncWord() {
		return syncWord;
	}

	public void setSyncWord(int syncWord) {
		NumberField numberField = (NumberField) this.newInstanceItem(TransportFrame.SYNCWORD,false);
		numberField.setNumber(syncWord);
	}

	public NumberField getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength() {
		this.newInstanceItem(TransportFrame.FIELDLENGTH,false);
	}

	public CRC getHeadCRC() {
		return this.headCRC; 
	}

	public void setHeadCRC() {
		this.newInstanceItem(TransportFrame.HEADCRC,false);
	}

	public NumberField getFrameType() {
		return frameType;
	}

	public void setFrameType(int frameType) {
		NumberField numberField = (NumberField) this.newInstanceItem(TransportFrame.FRAMETYPE,false);
		numberField.setNumber(frameType);
	}
}
