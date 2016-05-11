package cn.com.cennavi.tpeg.item.component.sni;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={SNITableDataLine.SID,FastTuningDataLine.SIDA,FastTuningDataLine.SIDB,FastTuningDataLine.SIDC,FastTuningDataLine.CONTENTID,FastTuningDataLine.APPLICATIONID,FastTuningDataLine.OPERATIONTIME,FastTuningDataLine.ENCRYPTIONINDICATOR,FastTuningDataLine.BITSWITCH}), 
		buildOrder=@EncoderBuildOrder(order={SNITableDataLine.SID,FastTuningDataLine.BITSWITCH,FastTuningDataLine.SIDA,FastTuningDataLine.SIDB,FastTuningDataLine.SIDC,FastTuningDataLine.CONTENTID,FastTuningDataLine.APPLICATIONID,FastTuningDataLine.OPERATIONTIME,FastTuningDataLine.ENCRYPTIONINDICATOR}))
public class FastTuningDataLine extends SNITableDataLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892213435089996157L;
	
	public static final String SIDA="sidA";
	
	public static final String SIDB="sidB";
	
	public static final String SIDC="sidC";
	
	public static final String APPLICATIONID="applicationID";
	
	public static final String BITSWITCH="bitSwitch";
	
	public static final String CONTENTID="contentID";
	
	public static final String OPERATIONTIME="operationTime";
	
	public static final String ENCRYPTIONINDICATOR="encryptionIndicator";
	/**
	 * (TFP: 07
	 * TEC: 0x05)
	 * 2 bytes
	 */
	@CoderItem(id=FastTuningDataLine.APPLICATIONID, type=CoderItemType.ITEM,length=2)
	private NumberField applicationID;
	/**
	 * (xxxxxxx1: SID
	 * xxxxx1xx: operation time
	 * xxxx1xxx:Encryption indicator
	 * xxx1xxxx: safety flag)
	 * 1 byte
	 */
	@CoderItem(id=FastTuningDataLine.BITSWITCH, type=CoderItemType.FLAGITEM_BITSWITCH,length=1,
			dependent=@Dependent(
					dependentField={FastTuningDataLine.SIDA,FastTuningDataLine.SIDB,FastTuningDataLine.SIDC,FastTuningDataLine.OPERATIONTIME,FastTuningDataLine.ENCRYPTIONINDICATOR,"default-no"},
					dependentFieldDigit={0,0,0,2,3,4}))
	private ByteFlag bitSwitch;
	
	/**
	 * 1 byte
	 */
	@CoderItem(id=FastTuningDataLine.CONTENTID, type=CoderItemType.ITEM,length=1)
	private NumberField contentID;
	/**
	 * (0x00) 1 byte
	 */
	@CoderItem(id=FastTuningDataLine.ENCRYPTIONINDICATOR, type=CoderItemType.ITEM,length=1)
	private NumberField encryptionIndicator;
	/**
	 * 8byte
	 */
	@CoderItem(id=FastTuningDataLine.OPERATIONTIME, type=CoderItemType.ITEM,length=8)
	private NumberField operationTime;
	/**
	 * 1BYTE
	 */
	@CoderItem(id=FastTuningDataLine.SIDA, type=CoderItemType.ITEM,length=1)
	private NumberField sidA;
	/**
	 * 1BYTE
	 */
	@CoderItem(id=FastTuningDataLine.SIDB, type=CoderItemType.ITEM,length=1)
	private NumberField sidB;
	
	/**
	 * 1BYTE
	 */
	@CoderItem(id=FastTuningDataLine.SIDC, type=CoderItemType.ITEM,length=1)
	private NumberField sidC;
	

	public NumberField getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.APPLICATIONID,false);
		numberField.setNumber(applicationID);
	}

	public ByteFlag getBitSwitch() {
		return bitSwitch;
	}

	public void setBitSwitch() {
		this.newInstanceItem(FastTuningDataLine.BITSWITCH,false);
	}

	public NumberField getContentID() {
		return contentID;
	}

	public void setContentID(int contentID) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.CONTENTID,false);
		numberField.setNumber(contentID);
	}

	public NumberField getEncryptionIndicator() {
		return encryptionIndicator;
	}

	public void setEncryptionIndicator(int encryptionIndicator) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.ENCRYPTIONINDICATOR,false);
		numberField.setNumber(encryptionIndicator);
	}

	public NumberField getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(int operationTime) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.OPERATIONTIME,false);
		numberField.setNumber(operationTime);
	}

	public NumberField getSIDA() {
		return sidA;
	}

	public void setSIDA(int sida) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.SIDA,false);
		numberField.setNumber(sida);
	}

	public NumberField getSIDB() {
		return sidB;
	}

	public void setSIDB(int sidb) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.SIDB,false);
		numberField.setNumber(sidb);
	}

	public NumberField getSIDC() {
		return sidC;
	}

	public void setSIDC(int sidc) {
		NumberField numberField = (NumberField) this.newInstanceItem(FastTuningDataLine.SIDC,false);
		numberField.setNumber(sidc);
	}


}
