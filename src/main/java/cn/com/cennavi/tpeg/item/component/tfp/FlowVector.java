package cn.com.cennavi.tpeg.item.component.tfp;

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
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * FlowVector一段TMC的路况存储结构图，可存储多个(实时，预测，历史等) 注：id：1byte，fix value=7
 * 
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
@CoderLinks(
		compileOrder=@EncoderBuildOrder(order={BaseComponent.ID,FlowVector.TIMEOFFSET,FlowVector.SECTIONCOUNT,FlowVector.SECTIONS,TFPData.SPATIALRESOLUTION,BaseComponent.SELECTOR,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
		buildOrder=@EncoderBuildOrder(order={BaseComponent.ID,BaseComponent.LENGTHCOMP,BaseComponent.LENGTHATTR,FlowVector.TIMEOFFSET,FlowVector.SECTIONCOUNT,FlowVector.SECTIONS,BaseComponent.SELECTOR,TFPData.SPATIALRESOLUTION}))
public class FlowVector extends BaseComponent {
	
	{
		this.setId(7);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}

	private static final long serialVersionUID = 8438284461502077241L;
	
	public static final String TIMEOFFSET="timeOffset";
	
	public static final String SECTIONCOUNT="sectionCOunt";
	
	public static final String SECTIONS="sections";

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id=BaseComponent.LENGTHATTR, type=CoderItemType.LENGTH,length=-5,
			dependent=@Dependent(dependentField={FlowVector.TIMEOFFSET,FlowVector.SECTIONCOUNT,FlowVector.SECTIONS,BaseComponent.SELECTOR,TFPData.SPATIALRESOLUTION}))
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id=BaseComponent.LENGTHCOMP, type=CoderItemType.LENGTH,length=-5,
			dependent=@Dependent(dependentField={BaseComponent.LENGTHATTR,FlowVector.TIMEOFFSET,FlowVector.SECTIONCOUNT,FlowVector.SECTIONS,BaseComponent.SELECTOR,TFPData.SPATIALRESOLUTION}))
	private NumberField lengthComp;
	
	/**
	 * section个数 1~5 bytes
	 */
	@CoderItem(id=FlowVector.SECTIONCOUNT, type=CoderItemType.ITEM,length=-5)
	private NumberField sectionCOunt;

	/**
	 * 1 byte
	 */
	@CoderItem(id=TFPData.SPATIALRESOLUTION, type=CoderItemType.ITEM,length=1)
	private NumberField spatialResolution;

	/**
	 * FlowVectorSection的集合，可以存储同一道路多个路口数据
	 */
	@CoderItem(id=FlowVector.SECTIONS, type=CoderItemType.LIST,length=-99999)
	private List<FlowVectorSection> sections;
	/**
	 * bit 0设置是否有另外的 spatialResolution,
	 * 如Section里面有使用定义好的spatialResolution以外的，则需要在section中指定
	 */
	@CoderItem(id=BaseComponent.SELECTOR, type=CoderItemType.FLAGITEM_SECLECTOR,length=1
			,dependent=@Dependent(
					dependentField={TFPData.SPATIALRESOLUTION},
					dependentFieldDigit={0}))
	private ByteFlag selector;
	/**
	 * 分钟，实时为0 ,1~5 bytes
	 * 
	 */
	@CoderItem(id=FlowVector.TIMEOFFSET, type=CoderItemType.ITEM,length=-5)
	private NumberField timeOffset;

	public NumberField getSpatialResolution() {
		return spatialResolution;
	}

	public void setSpatialResolution(int spatialResolution) {
		NumberField numberField = (NumberField) this.newInstanceItem(TFPData.SPATIALRESOLUTION,false);
		numberField.setNumber(spatialResolution);
	}

	public NumberField getSectionCOunt() {
		return sectionCOunt;
	}

	public void setSectionCOunt(int sectionCOunt) {
		NumberField numberField = (NumberField) this.newInstanceItem(FlowVector.SECTIONCOUNT,false);
		numberField.setNumber(sectionCOunt);
	}

	public List<FlowVectorSection> getSections() {
		return sections;
	}

	public void setSections(List<FlowVectorSection> sections) {
		this.sections = sections;
		if(sections!=null){
			this.setSectionCOunt(sections.size());
		}
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public NumberField getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(int timeOffset) {
		NumberField numberField = (NumberField) this.newInstanceItem(FlowVector.TIMEOFFSET,false);
		numberField.setNumber(timeOffset);
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
		//自动编译"id","lengthComp","lengthAttr","timeOffset","sectionCOunt",
		super.decoding();
		//手动编译"sections"
		this.sections=new ArrayList<FlowVectorSection>(this.getSectionCOunt().getNumber());
		for(int i=0;i<this.getSectionCOunt().getNumber();i++){
			FlowVectorSection fvs=new FlowVectorSection();
			fvs.setEncodedStream(this.getEncodedByteStream());
			fvs.decoding();
			sections.add(fvs);
		}
		//自动编译,"selector","spatialResolution"
		super.decoding(ReflectUtil.getClassField(FlowVector.SECTIONS, this.getClass()));
		//super.decoding(ReflectUtil.getClassField("selector", this.getClass()));
	}
}
