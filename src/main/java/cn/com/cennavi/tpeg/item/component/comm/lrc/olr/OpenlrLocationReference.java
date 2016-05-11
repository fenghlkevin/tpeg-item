package cn.com.cennavi.tpeg.item.component.comm.lrc.olr;

import java.io.IOException;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.LocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.LocationDescription;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.linear_lr.LinearLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.point_along_line_lr.PointAlongLineLocationReference;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * id:8
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, OpenlrLocationReference.VERSION, OpenlrLocationReference.LOCATIONREFERENCE,
		OpenlrLocationReference.LOCATIONDESCRIPTION, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID,
		BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, OpenlrLocationReference.VERSION, OpenlrLocationReference.LOCATIONREFERENCE, OpenlrLocationReference.LOCATIONDESCRIPTION}))
public class OpenlrLocationReference extends LocationReference {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String VERSION = "version";

	public static final String LOCATIONREFERENCE = "locationReference";

	public static final String LOCATIONDESCRIPTION = "locationDescription";

	{
		this.setId(8);
		this.setLengthComp(0);
		this.setLengthAttr(0);
	}

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { OpenlrLocationReference.VERSION }), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, OpenlrLocationReference.VERSION,
			OpenlrLocationReference.LOCATIONREFERENCE,OpenlrLocationReference.LOCATIONDESCRIPTION }), length = -5)
	private NumberField lengthComp;

	/**
	 * 1byte长度，版本号，固定为30
	 */
	@CoderItem(id = OpenlrLocationReference.VERSION, type = CoderItemType.ITEM, length = 1)
	private NumberField version;

	/**
	 * 子Component
	 */
	@CoderItem(id = OpenlrLocationReference.LOCATIONREFERENCE, type = CoderItemType.ITEM, length = 0)
	private AbstractLocationReference locationReference;

	/**
	 * 子Component
	 */
	@CoderItem(id = OpenlrLocationReference.LOCATIONDESCRIPTION, type = CoderItemType.ITEM, length = 0)
	private LocationDescription locationDescription;

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public NumberField getVersion() {
		return version;
	}

	public AbstractLocationReference getLocationReference() {
		return locationReference;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public void setVersion(int version) {
		NumberField numberField = (NumberField) this.newInstanceItem(OpenlrLocationReference.VERSION, false);
		numberField.setNumber(version);
	}

	public void setLocationReference(AbstractLocationReference locationReference) {
		this.locationReference = locationReference;
	}

	public LocationDescription getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(LocationDescription locationDescription) {
		this.locationDescription = locationDescription;
	}

	@Override
	public void decoding() {
		super.decoding(null, ReflectUtil.getClassField(OpenlrLocationReference.VERSION, this.getClass()));
		//super.decoding(null, ReflectUtil.getClassField(OpenlrLocationReference.LOCATIONDESCRIPTION, this.getClass()));
		int abstractLRID;
		try {
			getEncodedByteStream().mark(0);
			abstractLRID = getEncodedByteStream().read();
			getEncodedByteStream().reset();
			
			if (abstractLRID == 0) {
				this.locationReference = new LinearLocationReference();
			}else if(abstractLRID==2){
				this.locationReference=new PointAlongLineLocationReference();
			}else{
				System.err.println("unknown OpenLR Location Reference ID");
				return;
			}
//			this.locationReference.setId(abstractLRID);
//			this.locationReference.encoding();
			this.locationReference.setEncodedStream(this.getEncodedByteStream());
//			this.locationReference.decoding(ReflectUtil.getClassField(AbstractLocationReference.ID, this.locationReference.getClass()));
			this.locationReference.decoding();
			
			//this.locationDescription.setEncodedStream(this.getEncodedByteStream());
			//this.locationDescription.decoding();
			getEncodedByteStream().mark(0);
			abstractLRID = getEncodedByteStream().read();
			getEncodedByteStream().reset();
			if(abstractLRID == 11){
				this.locationDescription = new LocationDescription();
				this.locationDescription.setEncodedStream(this.getEncodedByteStream());
				this.locationDescription.decoding();
				//super.decoding(null,ReflectUtil.getClassField(OpenlrLocationReference.LOCATIONDESCRIPTION, this.getClass()));
			}
		} catch (IOException e) {
			throw new TPEGDecodeException("create locationReference error", e);
		}
	}

}
