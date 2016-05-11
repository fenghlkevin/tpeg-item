package cn.com.cennavi.tpeg.item.component.tec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.converter.TimestampConverter;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * TEC数据对象
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, TECData.EFFECTCODE, BaseComponent.STARTTIME, BaseComponent.STOPTIME,TECData.LENGTHAFFECTED,TECData.AVERAGESPEEDABSOLUTE, BaseComponent.SELECTOR, TECData.CAUSES,TECData.ADVICES,TECData.VEHICLERESTRICTIONS,
		BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, TECData.EFFECTCODE,
		BaseComponent.SELECTOR, BaseComponent.STARTTIME, BaseComponent.STOPTIME,TECData.LENGTHAFFECTED,TECData.AVERAGESPEEDABSOLUTE, TECData.CAUSES,TECData.ADVICES ,TECData.VEHICLERESTRICTIONS}))
public class TECData extends BaseComponent {
	
	{
		this.setId(3);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}

	private static final long serialVersionUID = 6411365524952399507L;

	public static final String CAUSES = "causes";
	
	public static final String ADVICES = "advices";
	
	public static final String VEHICLERESTRICTIONS= "vehicleRestrictions";

	public static final String EFFECTCODE = "effectCode";
	
	public static final String LENGTHAFFECTED = "lengthAffected";
	
	public static final String AVERAGESPEEDABSOLUTE = "averageSpeedAbsolute";

	/**
	 * 单个事件
	 */
	@CoderItem(id = TECData.CAUSES, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)//,decode=@Decode(id=4,type=DType.COMPONENT))
	private List<Cause> causes;
	
	@CoderItem(id = TECData.ADVICES, type = CoderItemType.LIST, length =CoderItem.ITEMLENGTH_NOMAXLENGTH)//,decode=@Decode(id=6,type=DType.COMPONENT))
    private List<Advice> advices;
	
	@CoderItem(id = TECData.VEHICLERESTRICTIONS, type = CoderItemType.LIST, length =CoderItem.ITEMLENGTH_NOMAXLENGTH)//,decode=@Decode(id=7,type=DType.COMPONENT))
	private List<VehicleRestriction> vehicleRestrictions;
	
	/**
	 * 1 byte
	 */
	@CoderItem(id = TECData.EFFECTCODE, type = CoderItemType.ITEM, length = 1)
	private NumberField effectCode;
	/**
	 * (bit 0: startTIme bit 1: stopTime) 1 byte
	 */
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { BaseComponent.STARTTIME, BaseComponent.STOPTIME,TECData.LENGTHAFFECTED,TECData.AVERAGESPEEDABSOLUTE }, dependentFieldDigit = {
			0, 1, 3,4 }))
	private ByteFlag selector;
	/**
	 * 秒，UTC 4 bytes
	 */
	@CoderItem(id = BaseComponent.STARTTIME, type = CoderItemType.ITEM, length = 4,toMapperClass=TimestampConverter.class)
	private NumberField startTIme;
	/**
	 * 秒，UTC 4 bytes
	 */
	@CoderItem(id = BaseComponent.STOPTIME, type = CoderItemType.ITEM, length = 4,toMapperClass=TimestampConverter.class)
	private NumberField stopTIme;
	
	@CoderItem(id = TECData.LENGTHAFFECTED, type = CoderItemType.ITEM, length = -5)
    private NumberField lengthAffected;
	
	@CoderItem(id = TECData.AVERAGESPEEDABSOLUTE, type = CoderItemType.ITEM, length = 1)
    private NumberField averageSpeedAbsolute;
	 

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { TECData.EFFECTCODE, "selector", BaseComponent.STARTTIME,
			BaseComponent.STOPTIME,TECData.LENGTHAFFECTED,TECData.AVERAGESPEEDABSOLUTE }))
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, TECData.EFFECTCODE, BaseComponent.SELECTOR,
			BaseComponent.STARTTIME, BaseComponent.STOPTIME,TECData.LENGTHAFFECTED,TECData.AVERAGESPEEDABSOLUTE, TECData.CAUSES,TECData.ADVICES,TECData.VEHICLERESTRICTIONS }))
	private NumberField lengthComp;

	public NumberField getLengthAffected() {
        return lengthAffected;
    }

    public void setLengthAffected(int lengthAffected) {
        NumberField numberField = (NumberField) this.newInstanceItem(TECData.LENGTHAFFECTED,false);
        numberField.setNumber(lengthAffected);
    }

//	public Cause getCauses() {
//		return causes;
//	}
//
//	public void setCauses(Cause causes) {
//		this.causes = causes;
//	}
	
	public NumberField getEffectCode() {
		return effectCode;
	}

	public void setEffectCode(int effectCode) {
		NumberField numberField = (NumberField) this.newInstanceItem(TECData.EFFECTCODE,false);
		numberField.setNumber(effectCode);
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public NumberField getStartTIme() {
		return startTIme;
	}

	public void setStartTIme(int startTIme) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.STARTTIME,false);
		numberField.setNumber(startTIme);
	}

	public NumberField getStopTIme() {
		return stopTIme;
	}

	public void setStopTIme(int stopTIme) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.STOPTIME,false);
		numberField.setNumber(stopTIme);
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
	
	private int getID() throws IOException{
		this.getEncodedByteStream().mark(0);
		int id=this.getEncodedByteStream().read();
		this.getEncodedByteStream().reset();
		return id;
	}

	@Override
	public void decoding() {
		// 解码到LENGTHAFFECTED
		super.decoding(null,ReflectUtil.getClassField(TECData.AVERAGESPEEDABSOLUTE, this.getClass()));
		
		//手动判断是否存在cause和 advice
		try {
			int id=this.getID();
			while(id==4||id==6){
				if(id==4){
					if(this.causes==null){
						this.causes=new ArrayList<Cause>();
					}
					Cause c=new Cause();
					c.setEncodedStream(this.getEncodedByteStream());
					c.decoding();
					causes.add(c);
				}else if(id==6){
					if(this.advices==null){
						this.advices=new ArrayList<Advice>();
					}
					Advice a=new Advice();
					a.setEncodedStream(this.getEncodedByteStream());
					a.decoding();
					advices.add(a);
				}
				id=this.getID();
			}
			
		} catch (IOException e) {
			throw new TPEGDecodeException("解码Cause/Advice异常",e);
		}
		
	}

	public NumberField getAverageSpeedAbsolute() {
		return averageSpeedAbsolute;
	}

	public void setAverageSpeedAbsolute(int averageSpeedAbsolute) {
		NumberField numberField = (NumberField) this.newInstanceItem(TECData.AVERAGESPEEDABSOLUTE,false);
		numberField.setNumber(averageSpeedAbsolute);
	}

	public List<Cause> getCauses() {
		return causes;
	}

	public void setCauses(List<Cause> causes) {
		this.causes = causes;
	}

	public List<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(List<Advice> advices) {
		this.advices = advices;
	}

	public List<VehicleRestriction> getVehicleRestrictions() {
		return vehicleRestrictions;
	}

	public void setVehicleRestrictions(List<VehicleRestriction> vehicleRestrictions) {
		this.vehicleRestrictions = vehicleRestrictions;
	}

	
}
