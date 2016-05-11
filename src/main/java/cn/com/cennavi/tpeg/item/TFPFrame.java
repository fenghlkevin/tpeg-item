package cn.com.cennavi.tpeg.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.CRC;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.item.component.tfp.TFPMessage;

/**
 * TFP
 * 
 * @author Administrator
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { ComponentFrame.SID, ComponentFrame.GROUPPRIORITY, ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS, ComponentFrame.CRC,
		ComponentFrame.FIELDLENGTH, ComponentFrame.HEADERCRC }), buildOrder = @EncoderBuildOrder(order = { ComponentFrame.SID, ComponentFrame.FIELDLENGTH, ComponentFrame.HEADERCRC,
		ComponentFrame.GROUPPRIORITY, ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS, ComponentFrame.CRC }))
public class TFPFrame extends ComponentFrame {

	private static final long serialVersionUID = -3487391555247572304L;

	@CoderItem(id = ComponentFrame.SERVICECOMPONENTS, type = CoderItemType.LIST, length = -99999)
	private List<TFPMessage> serviceComponent;

	@CoderItem(id = ComponentFrame.FIELDLENGTH, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { ComponentFrame.GROUPPRIORITY, ComponentFrame.COMPONENTCOUNT,
			ComponentFrame.SERVICECOMPONENTS, ComponentFrame.CRC }), length = 2)
	private NumberField fieldLength;

	/**
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (从Header
	 * CRC之后的第一个byte开始算起) 2 bytes
	 */
	@CoderItem(id = ComponentFrame.CRC, type = CoderItemType.CRC, dependent = @Dependent(dependentField = { ComponentFrame.GROUPPRIORITY, ComponentFrame.COMPONENTCOUNT,
			ComponentFrame.SERVICECOMPONENTS }, dependentFieldDigit = { 1, 1, 99999 }), length = 2)
	private CRC crc;

	/**
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (去掉CRC的前16个bytes) 2 bytes
	 */
	@CoderItem(id = ComponentFrame.HEADERCRC, type = CoderItemType.CRC, dependent = @Dependent(dependentField = { ComponentFrame.SID, ComponentFrame.FIELDLENGTH,
			ComponentFrame.GROUPPRIORITY,ComponentFrame.COMPONENTCOUNT , ComponentFrame.SERVICECOMPONENTS }, dependentFieldDigit = { 1, 2, 1, 1, 11 }), length = 2)
	private CRC headerCRC;

	/**
	 * 1bytes
	 */
	@CoderItem(id = ComponentFrame.GROUPPRIORITY, type = CoderItemType.ITEM, length = 1)
	private NumberField groupPriority;

	public NumberField getGroupPriority() {
		return groupPriority;
	}

	public void setGroupPriority(int groupPriority) {
		NumberField numberField = (NumberField) this.newInstanceItem(ComponentFrame.GROUPPRIORITY, false);
		numberField.setNumber(groupPriority);
	}

	public List<TFPMessage> getServiceComponent() {
		return serviceComponent;
	}

	public void addServiceComponent(TFPMessage msg) {
		if (serviceComponent == null) {
			serviceComponent = new ArrayList<TFPMessage>();
		}
		serviceComponent.add(msg);
	}

	public void setServiceComponent(List<TFPMessage> serviceComponent) {
		this.serviceComponent = serviceComponent;
	}

	public NumberField getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		NumberField numberField = (NumberField) this.newInstanceItem(ComponentFrame.FIELDLENGTH, false);
		numberField.setNumber(fieldLength);
	}

	public CRC getCrc() {
		return crc;
	}

	public void setCrc() {
		this.newInstanceItem(ComponentFrame.CRC, false);
	}

	public CRC getHeaderCRC() {
		return headerCRC;
	}

	public void setHeaderCRC() {
		this.newInstanceItem(ComponentFrame.HEADERCRC, false);
	}

	@Override
	public void decoding() {
		// "sid","fieldLength","headerCRC","groupPriority","componentCount",
		super.decoding();
		// 手动解码 "serviceComponents",
		this.serviceComponent = new ArrayList<TFPMessage>(this.getComponentCount().getNumber());
		for (int i = 0; i < this.getComponentCount().getNumber(); i++) {
		    
			TFPMessage msg = new TFPMessage();
			msg.setEncodedStream(getEncodedByteStream());
			msg.decoding();
			this.serviceComponent.add(msg);
		}
		// 解码"crc"
		super.decoding(ReflectUtil.getClassField(ComponentFrame.SERVICECOMPONENTS, this.getClass()));
		this.encoding();
	}

}
