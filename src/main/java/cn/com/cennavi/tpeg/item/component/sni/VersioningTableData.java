package cn.com.cennavi.tpeg.item.component.sni;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { SNITableData.TABLEID, SNITableData.VERSIONNUMBER, SNITableData.LINES, SNITableData.FIELDLENGTH }), buildOrder = @EncoderBuildOrder(order = {
		SNITableData.TABLEID, SNITableData.FIELDLENGTH, SNITableData.VERSIONNUMBER, SNITableData.LINES }))
public class VersioningTableData extends SNITableData {

	private static final long serialVersionUID = -7774922072555169255L;

	/**
	 * 每个不同 需要单独处理 该字段后面三部分,2byte
	 */
	@CoderItem(id = SNITableData.FIELDLENGTH, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { SNITableData.VERSIONNUMBER, SNITableData.LINES }), length = 2)
	private NumberField fieldLength;

	@Override
	protected Class<? extends SNITableDataLine> type() {
		return VersioningDataLine.class;
	}

	public NumberField getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		NumberField numberField = (NumberField) this.newInstanceItem(SNITableData.FIELDLENGTH, false);
		numberField.setNumber(fieldLength);
	}

	@Override
	protected int fieldLengthSubtraction() {
		return 1;
	}

}
