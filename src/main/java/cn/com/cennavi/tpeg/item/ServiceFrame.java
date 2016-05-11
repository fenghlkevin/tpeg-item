package cn.com.cennavi.tpeg.item;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.NTransUtil;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.codec.util.StrHelper;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.exception.TPEGEncodeException;
import cn.com.cennavi.tpeg.item.component.sni.FastTuningDataLine;
import cn.com.cennavi.tpeg.item.component.sni.SNITableDataLine;

/**
 * Service结构体
 * 
 * @author Administrator
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { ServiceFrame.SIDA, ServiceFrame.SIDB, ServiceFrame.SIDC, ServiceFrame.ENCRYPTION, ServiceFrame.SNICOMPONENT, ServiceFrame.SERVICES }), buildOrder = @EncoderBuildOrder(order = {
		ServiceFrame.SIDA, ServiceFrame.SIDB, ServiceFrame.SIDC, ServiceFrame.ENCRYPTION, ServiceFrame.SNICOMPONENT, ServiceFrame.SERVICES }))
public class ServiceFrame extends Frame {

	private static final long serialVersionUID = -7179155352263032427L;

	public static final String SIDA = "sidA";
	
	public static final String SIDB = "sidB";
	
	public static final String SIDC = "sidC";
	
	public static final String ENCRYPTION = "encryption";
	
	public static final String SNICOMPONENT = "sniComponent";
	
	public static final String SERVICES = "services";

	/**
	 * Sid-a 1 byte
	 */
	@CoderItem(id = ServiceFrame.SIDA, type = CoderItemType.ITEM, length = 1)
	private NumberField sidA;
	/**
	 * Sid-b 1 byte
	 */
	@CoderItem(id = ServiceFrame.SIDB, type = CoderItemType.ITEM, length = 1)
	private NumberField sidB;
	/**
	 * Sid-c 1 byte
	 */
	@CoderItem(id = ServiceFrame.SIDC, type = CoderItemType.ITEM, length = 1)
	private NumberField sidC;

	/**
	 * (0x0:不加密) 1 byte
	 */
	@CoderItem(id = ServiceFrame.ENCRYPTION, type = CoderItemType.ITEM, length = 1)
	private NumberField encryption;

	/**
	 * 必须有一个sni
	 */
	@CoderItem(id = ServiceFrame.SNICOMPONENT, type = CoderItemType.ITEM, length = 0)
	private SNIFrame sniComponent;

	/**
	 * 可以存储多个 如TFPFrame,或者TECFrame
	 */
	@CoderItem(id = ServiceFrame.SERVICES, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<ComponentFrame> services;

	/**
	 * @param clazz
	 * @return
	 */
	public ComponentFrame addComponentFrame(Class<? extends ComponentFrame> clazz) {
		if (services == null) {
			services = new ArrayList<ComponentFrame>();
		}
		try {
			ComponentFrame cf = clazz.newInstance();
			services.add(cf);
			return cf;
		} catch (InstantiationException e) {
			throw new TPEGEncodeException("Instantiation Argument error. ", e);
		} catch (IllegalAccessException e) {
			throw new TPEGEncodeException("IllegalAccess Argument error. ", e);
		}
	}

	public void addComponentFrame(ComponentFrame cf) {
		if (services == null) {
			services = new ArrayList<ComponentFrame>();
		}
		services.add(cf);
	}

	public NumberField getSidA() {
		return sidA;
	}

	public void setSidA(int sidA) {
		NumberField numberField = (NumberField) this.newInstanceItem(ServiceFrame.SIDA,false);
		numberField.setNumber(sidA);
	}

	public NumberField getSidB() {
		return sidB;
	}

	public void setSidB(int sidB) {
		NumberField numberField = (NumberField) this.newInstanceItem(ServiceFrame.SIDB,false);
		numberField.setNumber(sidB);
	}

	public NumberField getSidC() {
		return sidC;
	}

	public void setSidC(int sidC) {
		NumberField numberField = (NumberField) this.newInstanceItem(ServiceFrame.SIDC,false);
		numberField.setNumber(sidC);
	}

	public NumberField getEncryption() {
		return encryption;
	}

	public void setEncryption(int encryption) {
		NumberField numberField = (NumberField) this.newInstanceItem(ServiceFrame.ENCRYPTION,false);
		numberField.setNumber(encryption);
	}

	public SNIFrame getSniComponent() {
		return sniComponent;
	}

	public void setSniComponent(SNIFrame sniComponent) {
		this.sniComponent = sniComponent;
	}

	public List<ComponentFrame> getServices() {
		return services;
	}
	
	/**
	 * Map<serviceComponentId,applicationID>
	 */
	private Map<Integer,Integer> fastTurningDataMap=new HashMap<Integer, Integer>();
	@Override
	public void decoding() {
		//decoding sidA,sidB,sidC,encryption,sni
		super.decoding(null,ReflectUtil.getClassField(ServiceFrame.SNICOMPONENT, this.getClass()));
		try {
			for(SNITableDataLine line:this.getSniComponent().getFastTurningTable().getLines()){
				FastTuningDataLine fastTurningDataLine=(FastTuningDataLine)line;
				fastTurningDataMap.put(fastTurningDataLine.getSid().getNumber(), fastTurningDataLine.getApplicationID().getNumber());
			}
			while(this.getEncodedByteStream().available()>0){
				//read service component id
				this.getEncodedByteStream().mark(0);
				int serviceComponentID=this.getEncodedByteStream().read();
				this.getEncodedByteStream().reset();
				int applicationID=fastTurningDataMap.get(serviceComponentID);
				ComponentFrame componentFrame=null;
				if(applicationID==5){
					componentFrame=addComponentFrame(TECFrame.class);
					componentFrame.setEncodedStream(this.getEncodedByteStream());
					((TECFrame)componentFrame).decoding();
				}else if(applicationID==7){
					componentFrame=addComponentFrame(TFPFrame.class);
					this.getEncodedByteStream().reset();
					componentFrame.setEncodedStream(this.getEncodedByteStream());
					((TFPFrame)componentFrame).decoding();

				}
			}
		} catch (IOException e) {
			throw new TPEGDecodeException("Decoding service ERROR!",e);
		}
	}
	
	private Map<Integer, byte[]> splitService(ByteArrayInputStream stream) {
		Map<Integer, byte[]> services = new HashMap<Integer, byte[]>();

		while (stream.available() > 0) {
			byte[] sid = new byte[1];
			byte[] fieldLength = new byte[2];
			byte[] headCrc = new byte[2];

			try {
				stream.read(sid);
				stream.read(fieldLength);
				stream.read(headCrc);

				Integer key = new Integer(NTransUtil.hexByteToInt(sid));// hexbyteArray2Int
				int fieldLengthInt = NTransUtil.hexByteToInt(fieldLength);// byte2HexStr
				byte[] other = new byte[fieldLengthInt];
				stream.read(other);

				byte[] all = StrHelper.integrateByteArray(sid, fieldLength, headCrc, other);
				services.put(key, all);
			} catch (IOException e) {
				throw new TPEGDecodeException("读取sid时出现I/O异常");
			}
		}

		return services;
	}


}
