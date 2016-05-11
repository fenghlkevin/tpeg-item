package cn.com.cennavi.tpeg.item.component.comm;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.core.base.NumberLongField;
import cn.com.cennavi.tpeg.converter.TimestampConverter;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * MMC结构体
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, MMC.MESSAGEID, MMC.VERSIONID, MMC.MESSAGEEXPIRYTIME, MMC.MESSAGEGENERATIONTIME, BaseComponent.SELECTOR, MMC.PRIORITY,
		BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, MMC.MESSAGEID,
		MMC.VERSIONID, MMC.MESSAGEEXPIRYTIME, BaseComponent.SELECTOR, MMC.MESSAGEGENERATIONTIME, MMC.PRIORITY }))
public class MMC extends BaseComponent {

	{
		this.setId(1);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}
	
	private static final long serialVersionUID = 7207568120603698889L;

	public static final String MESSAGEID = "messageID";

	public static final String VERSIONID = "versionID";

	public static final String MESSAGEEXPIRYTIME = "messageExpiryTime";

	public static final String MESSAGEGENERATIONTIME = "messageGenerationTime";

	public static final String PRIORITY = "priority";

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { MMC.MESSAGEID, MMC.VERSIONID, MMC.MESSAGEEXPIRYTIME,
			BaseComponent.SELECTOR, MMC.MESSAGEGENERATIONTIME, MMC.PRIORITY }))
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, MMC.MESSAGEID, MMC.VERSIONID,
			MMC.MESSAGEEXPIRYTIME, BaseComponent.SELECTOR, MMC.MESSAGEGENERATIONTIME, MMC.PRIORITY }))
	private NumberField lengthComp;
	/**
	 * 有效时间，秒，UTC 4 bytes
	 */
	@CoderItem(id = MMC.MESSAGEEXPIRYTIME, type = CoderItemType.ITEM, length = 4,toMapperClass=TimestampConverter.class)
	private NumberField messageExpiryTime;
	/**
	 * 秒，UTC 4bytes
	 */
	@CoderItem(id = MMC.MESSAGEGENERATIONTIME, type = CoderItemType.ITEM, length = 4,toMapperClass=TimestampConverter.class)
	private NumberField messageGenerationTime;
	/**
	 * 1~255 可重复 1~5bytes
	 */
	@CoderItem(id = MMC.MESSAGEID, type = CoderItemType.ITEM, length = -5)
	private NumberLongField messageID;
	/**
	 * 1 byte
	 */
	@CoderItem(id = MMC.PRIORITY, type = CoderItemType.ITEM, length = 1)
	private NumberField priority;
	/**
	 * bit 0: cancelFlag,bit 1: messageGenerationTime,bit 2: priority
	 */
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { MMC.MESSAGEGENERATIONTIME, MMC.PRIORITY }, dependentFieldDigit = {
			1, 2 }))
	private ByteFlag selector;
	/**
	 * 1~255 1 byte
	 */
	@CoderItem(id = MMC.VERSIONID, type = CoderItemType.ITEM, length = 1)
	private NumberField versionID;

	public NumberField getMessageExpiryTime() {
		return messageExpiryTime;
	}

	public void setMessageExpiryTime(int messageExpiryTime) {
		NumberField numberField = (NumberField) this.newInstanceItem(MMC.MESSAGEEXPIRYTIME, false);
		numberField.setNumber(messageExpiryTime);
	}

	public NumberField getMessageGenerationTime() {
		return messageGenerationTime;
	}

	public void setMessageGenerationTime(int messageGenerationTime) {
		NumberField numberField = (NumberField) this.newInstanceItem(MMC.MESSAGEGENERATIONTIME,false);
		numberField.setNumber(messageGenerationTime);
	}

	public NumberLongField getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		NumberLongField numberField = (NumberLongField) this.newInstanceItem(MMC.MESSAGEID,false);
		numberField.setNumber(messageID);
	}

	public NumberField getPriority() {
		return priority;
	}

	public void setPriority(int  priority) {
		NumberField numberField = (NumberField) this.newInstanceItem(MMC.PRIORITY,false);
		numberField.setNumber(priority);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public NumberField getVersionID() {
		return versionID;
	}

	public void setVersionID(int versionID) {
		NumberField numberField = (NumberField) this.newInstanceItem(MMC.VERSIONID,false);
		numberField.setNumber(versionID);
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
