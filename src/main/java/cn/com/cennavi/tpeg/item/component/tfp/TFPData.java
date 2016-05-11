package cn.com.cennavi.tpeg.item.component.tfp;

import java.io.ByteArrayInputStream;
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
import cn.com.cennavi.tpeg.converter.TimestampConverter;
import cn.com.cennavi.tpeg.exception.TPEGBaseException;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * 组合TFP 具体数据的结构体 注:id fix value=6
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={BaseComponent.ID,BaseComponent.STARTTIME,TFPData.DURATION,BaseComponent.SELECTOR,TFPData.SPATIALRESOLUTION,TFPData.FLOWVECTORS,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
		buildOrder=@EncoderBuildOrder(order={BaseComponent.ID,BaseComponent.LENGTHCOMP,BaseComponent.LENGTHATTR,BaseComponent.STARTTIME,BaseComponent.SELECTOR,TFPData.DURATION,TFPData.SPATIALRESOLUTION,TFPData.FLOWVECTORS}))
public class TFPData extends BaseComponent {
	
	{
		this.setId(6);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}
	
	private static final long serialVersionUID = -5189418231246508381L;
	
	public static final String DURATION="duration";
	
	public static final String SPATIALRESOLUTION="spatialResolution";
	
	public static final String FLOWVECTORS="flowVectors";
	
	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id=BaseComponent.LENGTHATTR, type=CoderItemType.LENGTH,length=-5,
			dependent=@Dependent(dependentField={BaseComponent.STARTTIME,BaseComponent.SELECTOR,TFPData.DURATION,TFPData.SPATIALRESOLUTION}))
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id=BaseComponent.LENGTHCOMP, type=CoderItemType.LENGTH,length=-5,
			dependent=@Dependent(dependentField={BaseComponent.LENGTHATTR,BaseComponent.STARTTIME,BaseComponent.SELECTOR,TFPData.DURATION,TFPData.SPATIALRESOLUTION,TFPData.FLOWVECTORS}))
	private NumberField lengthComp;
	
	/**
	 * 如果selector有效，分钟.我们的有效时间是1分钟（可以不考虑） byte1~5
	 */
	@CoderItem(id=TFPData.DURATION, type=CoderItemType.ITEM,length=-5)
	private NumberField duration;
	/**
	 * flowVectors 交通流对象。第一个为当前时间，预测则按照时间从小到大依次排序放入list
	 */
	@CoderItem(id=TFPData.FLOWVECTORS, type=CoderItemType.LIST,length=CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<FlowVector> flowVectors;
	/**
	 * (bit 0设置是否有duration) 1 byte
	 */
	@CoderItem(id=BaseComponent.SELECTOR, type=CoderItemType.FLAGITEM_SECLECTOR,length=1
			,dependent=@Dependent(
					dependentField={TFPData.DURATION},
					dependentFieldDigit={0}))
	private ByteFlag selector;
	/**
	 * 秒，UTC 4bytes
	 */
	@CoderItem(id=BaseComponent.STARTTIME, type=CoderItemType.ITEM,length=4,toMapperClass=TimestampConverter.class)
	private NumberField startTIme;

	/**
	 * 1 byte
	 */
	@CoderItem(id=TFPData.SPATIALRESOLUTION, type=CoderItemType.ITEM,length=1)
	private NumberField spatialResolution;

	public NumberField getSpatialResolution() {
		return spatialResolution;
	}

	public void setSpatialResolution(int spatialResolution) {
		NumberField numberField = (NumberField) this.newInstanceItem(TFPData.SPATIALRESOLUTION,false);
		numberField.setNumber(spatialResolution);
	}

	public NumberField getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		NumberField numberField = (NumberField) this.newInstanceItem(TFPData.DURATION,false);
		numberField.setNumber(duration);
	}

	public List<FlowVector> getFlowVectors() {
		return flowVectors;
	}

	public void setFlowVectors(List<FlowVector> flowVectors) {
		this.flowVectors = flowVectors;
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


	@Override
	public void decoding() {
		//自动解码"id","lengthComp","lengthAttr","startTIme","selector","duration","spatialResolution",
		super.decoding();
		//手动解码"flowVectors"
		int flowVectorsLength=this.getLengthComp().getNumber()-this.getLengthAttr().getNumber()-this.getLengthAttr().getEncodedArray().length;
		byte[] flowVectorByte=new byte[flowVectorsLength];
		ByteArrayInputStream stream=null;
		try {
			this.getEncodedByteStream().read(flowVectorByte);
			stream=new ByteArrayInputStream(flowVectorByte);
			this.flowVectors=new ArrayList<FlowVector>();
			while(stream.available()>0){
				FlowVector fv=new FlowVector();
				fv.setEncodedStream(stream);
				fv.decoding();
				this.flowVectors.add(fv);
			}
		} catch (IOException e) {
			throw new TPEGDecodeException("decoding flowVectors error",e);
		}finally{
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					throw new TPEGBaseException("close flowVectors stream error",e);
				}
			}
		}
		
	}

}
