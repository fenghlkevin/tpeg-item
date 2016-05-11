package cn.com.cennavi.tpeg.item.component.sni;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.exception.TPEGBaseException;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.exception.TPEGEncodeException;

/**
 * SNITable 抽象类
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
/**
 * @author Administrator
 * 
 */
public abstract class SNITableData extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = -7641776942055470680L;
	
	public static final String LINES="lines";
	
	public static final String TABLEID="tableID";
	
	public static final String VERSIONNUMBER="versionNumber";
	
	public static final String FIELDLENGTH="fieldLength";
	
	/**
	 * 每个不同 需要单独处理
	 * 该字段后面三部分,2byte
	 */
	//protected NumberField fieldLength;
	/**
	 * 1 byte,sni中每个table会有一个固定的ID
	 */
	@CoderItem(id=SNITableData.LINES, type=CoderItemType.LIST,length=CoderItem.ITEMLENGTH_NOMAXLENGTH)
	protected List<SNITableDataLine> lines;
	/**
	 * 每个table有固定的ID
	 */
	@CoderItem(id=SNITableData.TABLEID, type=CoderItemType.ITEM,length=1)
	protected NumberField tableID;
	/**
	 * 1byte,同步所有表 fix value=0
	 */
	@CoderItem(id=SNITableData.VERSIONNUMBER, type=CoderItemType.ITEM,length=1)
	protected NumberField versionNumber;

	

	public List<SNITableDataLine> getLines() {
		return lines;
	}
	
	protected abstract Class<? extends SNITableDataLine> type();

	public SNITableDataLine addLine() {
		if (lines == null) {
			lines = new ArrayList<SNITableDataLine>();
		}
		SNITableDataLine line;
		try {
			line = type().newInstance();
			lines.add(line);
			return line;
		} catch (InstantiationException e) {
			throw new TPEGEncodeException("Instantiation Argument error. ", e);
		} catch (IllegalAccessException e) {
			throw new TPEGEncodeException("IllegalAccess Argument error. ", e);
		}
	}

	public void setLines(List<SNITableDataLine> lines) {
		this.lines = lines;
	}

	public NumberField getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		NumberField numberField = (NumberField) this.newInstanceItem(SNITableData.TABLEID,false);
		numberField.setNumber(tableID);
	}

	public NumberField getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		NumberField numberField = (NumberField) this.newInstanceItem(SNITableData.VERSIONNUMBER,false);
		numberField.setNumber(versionNumber);
	}
	
	public abstract NumberField getFieldLength();

	public abstract void setFieldLength(int fieldLength);
	
	/**
	 * 获得lines所有长度 用fieldLength
	 * @return
	 */
	protected abstract int fieldLengthSubtraction();
	
	@Override
	public void decoding() {
		// 自动界面可解码字段,"fieldLength",等等
		super.decoding(ReflectUtil.getClassField(SNITableData.TABLEID, this.getClass()));
		// 手动解码 line对象
		// 获得lines所有长度 用fieldLength 减掉versionNumber,characterTableID
		byte[] lines = new byte[this.getFieldLength().getNumber() - fieldLengthSubtraction()];
		try {
			this.getEncodedByteStream().read(lines);
		} catch (IOException e) {
			throw new TPEGDecodeException("get FastTurningLine bytearry error", e);
		}
		//把所有line取出，并逐行放入对象里
		ByteArrayInputStream stream = null;
		try {
			stream = new ByteArrayInputStream(lines);
			SNITableDataLine line = null;
			while (stream.available() > 0) {
				line = this.addLine();
				line.setEncodedStream(stream);
				line.decoding();
			}
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new TPEGBaseException("close SNITableDataLine ByteArrayInputStream error",e);
				}
			}
		}

	}

}
