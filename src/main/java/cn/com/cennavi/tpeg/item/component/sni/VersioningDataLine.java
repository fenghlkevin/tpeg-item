package cn.com.cennavi.tpeg.item.component.sni;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * Versioning表
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { SNITableDataLine.SID, VersioningDataLine.MAJORVERSIONNUMBER, VersioningDataLine.MINORVERSIONNUMBER }), buildOrder = @EncoderBuildOrder(order = {
		SNITableDataLine.SID, VersioningDataLine.MAJORVERSIONNUMBER, VersioningDataLine.MINORVERSIONNUMBER }))
public class VersioningDataLine extends SNITableDataLine {

	private static final long serialVersionUID = 8777390764394428892L;

	public static final String MAJORVERSIONNUMBER = "majorVersionNumber";

	public static final String MINORVERSIONNUMBER = "minorVersionNumber";

	/**
	 * (TFP: 1 TEC: 3) 1 byte
	 */
	@CoderItem(id = VersioningDataLine.MAJORVERSIONNUMBER, type = CoderItemType.ITEM, length = 1)
	private NumberField majorVersionNumber;
	/**
	 * (TFP: 0 TEC: 0) 1 byte
	 */
	@CoderItem(id = VersioningDataLine.MINORVERSIONNUMBER, type = CoderItemType.ITEM, length = 1)
	private NumberField minorVersionNumber;

	public NumberField getMajorVersionNumber() {
		return majorVersionNumber;
	}

	public void setMajorVersionNumber(int majorVersionNumber) {
		NumberField numberField = (NumberField) this.newInstanceItem(VersioningDataLine.MAJORVERSIONNUMBER,false);
		numberField.setNumber(majorVersionNumber);
	}

	public NumberField getMinorVersionNumber() {
		return minorVersionNumber;
	}

	public void setMinorVersionNumber(int minorVersionNumber) {
		NumberField numberField = (NumberField) this.newInstanceItem(VersioningDataLine.MINORVERSIONNUMBER,false);
		numberField.setNumber(minorVersionNumber);
	}
}
