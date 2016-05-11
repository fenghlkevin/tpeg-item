package cn.com.cennavi.tpeg.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.CRC;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.NTransUtil;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.codec.util.StrHelper;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.sni.FastTuningTableData;
import cn.com.cennavi.tpeg.item.component.sni.SNITableData;
import cn.com.cennavi.tpeg.item.component.sni.VersioningTableData;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(
					order = { ComponentFrame.SID, ComponentFrame.COMPONENTCOUNT,ComponentFrame.SERVICECOMPONENTS , ComponentFrame.CRC,  ComponentFrame.FIELDLENGTH, ComponentFrame.HEADERCRC }), 
				buildOrder = @EncoderBuildOrder(
						order = {ComponentFrame.SID, ComponentFrame.FIELDLENGTH, ComponentFrame.HEADERCRC, ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS , ComponentFrame.CRC }))
public class SNIFrame extends ComponentFrame {
	
	private Logger logger=LoggerFactory.getLogger(SNIFrame.class);

	private static final long serialVersionUID = 986655119878641944L;
	
	private FastTuningTableData fastTurning;

	private VersioningTableData versioning;

	@CoderItem(id = ComponentFrame.SERVICECOMPONENTS, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<SNITableData> sniTables;

	/**
	 * 2 bytes
	 */
	@CoderItem(id =  ComponentFrame.FIELDLENGTH, type = CoderItemType.LENGTH, length = 2, dependent = @Dependent(dependentField = {
			ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS, ComponentFrame.CRC }))
	private NumberField fieldLength;

	/**
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (从Header
	 * CRC之后的第一个byte开始算起) 2 bytes
	 */
	@CoderItem(id = ComponentFrame.CRC, type = CoderItemType.CRC, length = 2, dependent = @Dependent(dependentField = {
			ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS }, dependentFieldDigit = { 1, 99999 }))
	private CRC crc;

	/**
	 * (X<sup>16</sup>+X<sup>12</sup>+X<sup>5</sup>+1) (去掉CRC的前16个bytes) 2 bytes
	 */
	@CoderItem(id = ComponentFrame.HEADERCRC, type = CoderItemType.CRC, length = 2, dependent = @Dependent(dependentField = {
			ComponentFrame.SID,  ComponentFrame.FIELDLENGTH, ComponentFrame.COMPONENTCOUNT, ComponentFrame.SERVICECOMPONENTS }, dependentFieldDigit = {
			1, 2, 1, 12 }))
	private CRC headerCRC;

	{
		this.fastTurning = new FastTuningTableData();
		fastTurning.setTableID(1);
		fastTurning.setFieldLength(0);
		fastTurning.setVersionNumber(0);
		fastTurning.setCharacterTableID(1);

		this.versioning = new VersioningTableData();
		versioning.setTableID(14);
		versioning.setFieldLength(0);
		versioning.setVersionNumber(0);

		sniTables = new ArrayList<SNITableData>(2);
		sniTables.add(fastTurning);
		sniTables.add(versioning);
	}
	
	public FastTuningTableData getFastTurningTable() {
		return fastTurning;
	}

	public VersioningTableData getVersioningTable() {
		return versioning;
	}

	public CRC getCrc() {
		return crc;
	}

	public void setCrc() {
		this.newInstanceItem(ComponentFrame.CRC,false);
	}

	public CRC getHeaderCRC() {
		return headerCRC;
	}

	public void setHeaderCRC() {
		this.newInstanceItem(ComponentFrame.HEADERCRC,false);
	}

	public NumberField getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		NumberField numberField = (NumberField) this.newInstanceItem(ComponentFrame.FIELDLENGTH,false);
		numberField.setNumber(fieldLength);
	}

	@Override
	public void decoding() {
		// 系统自动解码 "sid","fieldLength","headerCRC","componentCount"
		super.decoding();

		try {
			// 根据componentCount 解码table内容
			int count = this.getComponentCount().getNumber();
			this.sniTables = new ArrayList<SNITableData>(count);

			decodingTable(count);
			// 解码"crc"
			super.decoding(ReflectUtil.getClassField(ComponentFrame.SERVICECOMPONENTS, this.getClass()));
		} catch (IOException e) {
			throw new TPEGDecodeException("create SNI table error", e);
		}
	}

	private void decodingTable(int count) throws IOException {
		for (int i = 0; i < count; i++) {
			byte[] id = new byte[1];
			this.getEncodedByteStream().read(id);
			int type = NTransUtil.hexByteToInt(id);// hexbyteArray2Int
			SNITableData table = null;
			if (type == 1) {
				table = new FastTuningTableData();
				this.fastTurning = (FastTuningTableData) table;
			} else if (type == 14) {
				table = new VersioningTableData();
				this.versioning = (VersioningTableData) table;
			} else {
				NumberField n=new NumberField(0,2);
				byte[] b2=new byte[2];
				this.getEncodedByteStream().read(b2);
				n.setEncodedArray(b2);
				n.decoding();
				byte[] b3=new byte[n.getNumber()];
				this.getEncodedByteStream().read(b3);
				logger.debug(StrHelper.getContent("SNI table type:{0} error,can not create SNI table.",new Object[]{new Integer(type)}));
				continue;
				//throw new TPEGDecodeException("SNI table type error,can not create SNI table.");
			}
			//NumberField tid=new NumberField(type, 1);
			//tid.encoding();
			table.setTableID(type);
			table.setEncodedStream(this.getEncodedByteStream());
			table.decoding();
			this.sniTables.add(table);
		}
	}

	public List<SNITableData> getSniTables() {
		return sniTables;
	}

}
