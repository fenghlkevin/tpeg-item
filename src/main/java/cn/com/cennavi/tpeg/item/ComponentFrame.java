package cn.com.cennavi.tpeg.item;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * 服务组件抽象类
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
public abstract class ComponentFrame extends Frame {

	private static final long serialVersionUID = 1365316667560553381L;
	
	public static final String COMPONENTCOUNT = "componentCount";
	
	public static final String SID = "sid";
	
	public static final String GROUPPRIORITY = "groupPriority";
	
	public static final String CRC="crc";
	
	public static final String HEADERCRC="headerCRC";
	
	public static final String FIELDLENGTH="fieldLength";
	
	public static final String SERVICECOMPONENTS="serviceComponents";
	/**
	 * 1 byte
	 */
	@CoderItem(id=ComponentFrame.COMPONENTCOUNT, type=CoderItemType.ITEM,length=1)
	protected NumberField componentCount;
	
	/**
	 * 1 byte
	 */
	@CoderItem(id=ComponentFrame.SID, type=CoderItemType.ITEM,length=1)
	protected NumberField sid;
	
	/**
	 * 每个component不同，需要单独处理
	 * 2 bytes
	 */
	//protected NumberField fieldLength;
	/**
	 * 每个component不同，需要单独处理
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (从Header
	 * CRC之后的第一个byte开始算起) 2 bytes
	 */
	//protected CRC crc;

	/**
	 * 每个component不同，需要单独处理
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (去掉CRC的前16个bytes) 2 bytes
	 */
	//protected CRC headerCRC;

	public NumberField getComponentCount() {
		return componentCount;
	}

	public void setComponentCount(int componentCount) {
		NumberField numberField = (NumberField) this.newInstanceItem(ComponentFrame.COMPONENTCOUNT,false);
		numberField.setNumber(componentCount);
	}

//	public CRC getCrc() {
//		return crc;
//	}
//
//	public void setCrc(CRC crc) {
//		this.crc = crc;
//	}
//
//	public CRC getHeaderCRC() {
//		return headerCRC;
//	}
//
//	public void setHeaderCRC(CRC headerCRC) {
//		this.headerCRC = headerCRC;
//	}

	public NumberField getSid() {
		return sid;
	}

	public void setSid(int sid) {
		NumberField numberField = (NumberField) this.newInstanceItem(ComponentFrame.SID,false);
		numberField.setNumber(sid);
	}

//	public NumberField getFieldLength() {
//		return fieldLength;
//	}
//
//	public void setFieldLength(NumberField fieldLength) {
//		this.fieldLength = fieldLength;
//	}

}
