package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.linear_lr;

import java.lang.reflect.Field;
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
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.FirstLocationReferencePoint;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LastLocationReferencePoint;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.Shape;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, LinearLocationReference.FIRSTLOCATIONREFERENCEPOINT, LinearLocationReference.LASTLOCATIONREFERENCEPOINT,LinearLocationReference.N, LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS, LinearLocationReference.POSITIVEOFFSET,LinearLocationReference.NEGATIVEOFFSET,LinearLocationReference.SELECTOR,LinearLocationReference.SHAPE ,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
                         buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, LinearLocationReference.FIRSTLOCATIONREFERENCEPOINT, LinearLocationReference.LASTLOCATIONREFERENCEPOINT,LinearLocationReference.SELECTOR,LinearLocationReference.N,LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS,LinearLocationReference.POSITIVEOFFSET,LinearLocationReference.NEGATIVEOFFSET,LinearLocationReference.SHAPE }))
public class LinearLocationReference extends AbstractLocationReference {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String FIRSTLOCATIONREFERENCEPOINT = "firstLocationReferencePoint";
	
	public static final String LASTLOCATIONREFERENCEPOINT = "lastLocationReferencePoint";
	
	public static final String INTERMEDIATELOCATIONREFERENCEPOINTS = "intermediateLocationReferencePoints";
	
	public static final String POSITIVEOFFSET = "positiveOffset";
	
	public static final String NEGATIVEOFFSET = "negativeOffset";
	
	public static final String N="n";
	
	public static final String SHAPE = "shape";
	
	{
		this.setId(0);
		this.setLengthComp(0);
		this.setLengthAttr(0);
		this.setSelector();
	}

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = {LinearLocationReference.FIRSTLOCATIONREFERENCEPOINT, LinearLocationReference.LASTLOCATIONREFERENCEPOINT,LinearLocationReference.N,LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS,LinearLocationReference.POSITIVEOFFSET,LinearLocationReference.NEGATIVEOFFSET,LinearLocationReference.SELECTOR }), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长 
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { LinearLocationReference.LENGTHATTR, LinearLocationReference.FIRSTLOCATIONREFERENCEPOINT, LinearLocationReference.LASTLOCATIONREFERENCEPOINT,LinearLocationReference.N,LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS,LinearLocationReference.POSITIVEOFFSET,LinearLocationReference.NEGATIVEOFFSET,LinearLocationReference.SELECTOR,LinearLocationReference.SHAPE }), length = -5)
	private NumberField lengthComp;

	/**
	 * 
	 */
	@CoderItem(id = LinearLocationReference.FIRSTLOCATIONREFERENCEPOINT, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private FirstLocationReferencePoint firstLocationReferencePoint;
	
	/**
	 * 
	 */
	@CoderItem(id = LinearLocationReference.LASTLOCATIONREFERENCEPOINT, type = CoderItemType.ITEM, length = 0,toMapper=true)
	private LastLocationReferencePoint lastLocationReferencePoint;


	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS,LinearLocationReference.POSITIVEOFFSET,LinearLocationReference.NEGATIVEOFFSET }, dependentFieldDigit = {0,1,2}))
	private ByteFlag selector;
	
	@CoderItem(id = LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH,toMapper=true)
	private List<IntermediateLocationReferencePoints> intermediateLocationReferencePoints;
	
	@CoderItem(id = LinearLocationReference.N, type = CoderItemType.ITEM, length = -5)
	private NumberField n;
	
	@CoderItem(id = LinearLocationReference.POSITIVEOFFSET, type = CoderItemType.ITEM, length = -4)
	private NumberField positiveOffset;
	
	@CoderItem(id = LinearLocationReference.NEGATIVEOFFSET, type = CoderItemType.ITEM, length = -4)
	private NumberField negativeOffset;
	
	@CoderItem(id = LinearLocationReference.SHAPE, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private Shape shape;//暂时不用shape字段，不解析

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public FirstLocationReferencePoint getFirstLocationReferencePoint() {
		return firstLocationReferencePoint;
	}

	public void setFirstLocationReferencePoint(
			FirstLocationReferencePoint firstLocationReferencePoint) {
		this.firstLocationReferencePoint = firstLocationReferencePoint;
	}

	public LastLocationReferencePoint getLastLocationReferencePoint() {
		return lastLocationReferencePoint;
	}

	public void setLastLocationReferencePoint(
			LastLocationReferencePoint lastLocationReferencePoint) {
		this.lastLocationReferencePoint = lastLocationReferencePoint;
	}

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR,false);
	}

	public List<IntermediateLocationReferencePoints> getIntermediateLocationReference() {
		return intermediateLocationReferencePoints;
	}
	
	public void addIntermediateLocationReference(IntermediateLocationReferencePoints ilr) {
		if(intermediateLocationReferencePoints==null){
			intermediateLocationReferencePoints=new ArrayList<IntermediateLocationReferencePoints>();
		}
		intermediateLocationReferencePoints.add(ilr);
	}

	public void setIntermediateLocationReference(
			List<IntermediateLocationReferencePoints> intermediateLocationReference) {
		this.intermediateLocationReferencePoints = intermediateLocationReference;
	}

	public NumberField getPositiveOffset() {
		return positiveOffset;
	}

	public void setPositiveOffset(int positiveOffset) {
		NumberField numberField = (NumberField) this.newInstanceItem(LinearLocationReference.POSITIVEOFFSET, false);
		numberField.setNumber(positiveOffset);
	}

	public NumberField getNegativeOffset() {
		return negativeOffset;
	}

	public void setNegativeOffset(int negativeOffset) {
		NumberField numberField = (NumberField) this.newInstanceItem(LinearLocationReference.NEGATIVEOFFSET, false);
		numberField.setNumber(negativeOffset);
	}

	public NumberField getN() {
		return this.n;
	}
	
	public void setN(int n) {
		NumberField numberField = (NumberField) this.newInstanceItem(LinearLocationReference.N, false);
		numberField.setNumber(n);
	}

	@Override
	public void decoding() {
		Field selector=ReflectUtil.getClassField(LinearLocationReference.SELECTOR,this.getClass());
		super.decoding(null, selector);
		if(this.getSelector().getLocationValue(0)){
			super.decoding(selector, ReflectUtil.getClassField(LinearLocationReference.N, this.getClass()));
			int count=this.getN().getNumber();
			this.intermediateLocationReferencePoints=new ArrayList<IntermediateLocationReferencePoints>(count);
			for(int i=0;i<count;i++){
				IntermediateLocationReferencePoints ilr=new IntermediateLocationReferencePoints();
				intermediateLocationReferencePoints.add(ilr);
				ilr.setEncodedStream(this.getEncodedByteStream());
				ilr.decoding();
			}
		}
		Field intermediateLocationReference=ReflectUtil.getClassField(LinearLocationReference.INTERMEDIATELOCATIONREFERENCEPOINTS,this.getClass());
		super.decoding(intermediateLocationReference, null);
	}

	

}
