package cn.com.cennavi.tpeg.item.component.sni;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * 每个TABLE的LINE 抽象类
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
public abstract class SNITableDataLine extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = -241958190943452042L;
	
	public static final String SID="sid";

	/**
	 * Service Component Identifier,1 byte 表示是那种服务如：TFP/TEC等等
	 */
	@CoderItem(id = SNITableDataLine.SID, type = CoderItemType.ITEM, length = 1)
	protected NumberField sid;

	public NumberField getSid() {
		return sid;
	}

	public void setSid(int sid) {
		NumberField numberField = (NumberField) this.newInstanceItem(SNITableDataLine.SID,false);
		numberField.setNumber(sid);
	}

}
