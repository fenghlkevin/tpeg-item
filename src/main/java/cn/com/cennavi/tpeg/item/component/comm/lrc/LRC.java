package cn.com.cennavi.tpeg.item.component.comm.lrc;

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
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.OpenlrLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.tmc.TMCLocationReference;

/**
 * LRC的基础对象
 * 
 * @author Administrator
 *
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = {BaseComponent.ID, LRC.LOCATIONREFERENCE, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP}), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, LRC.LOCATIONREFERENCE}))
public class LRC extends BaseComponent {

	private static final long serialVersionUID = -5155628758453784529L;

	public static final String LOCATIONREFERENCE = "LocationReference";

	{
		this.setId(2);
		this.setLengthComp(0);
		this.setLengthAttr(0);
	}
	/**
	 * id值，不同于sid
	 */
	// @CoderItem(id=BaseComponent.ID,
	// type=CoderItemType.ITEM,length=CoderItem.ITEMLENGTH_NOMAXLENGTH)
	// protected NumberField id;

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.ITEM, length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = {BaseComponent.LENGTHATTR, LRC.LOCATIONREFERENCE}), length = -5)
	private NumberField lengthComp;

	@CoderItem(id = LRC.LOCATIONREFERENCE, type = CoderItemType.ITEM, length =0)
	private LocationReference locationReference;

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public LocationReference getLocationReference() {
		return locationReference;
	}

	public void setLocationReference(LocationReference locationReference) {
		this.locationReference = locationReference;
	}

	@Override
	public void decoding() {
		super.decoding(null,ReflectUtil.getClassField(LRC.LENGTHATTR, this.getClass()));
		int locationReferenceid;
		try {
			getEncodedByteStream().mark(0);
			locationReferenceid = getEncodedByteStream().read();
			getEncodedByteStream().reset();
			if (locationReferenceid == 1) {
				// this.lrc=new AgoracLRC();
			} else if (locationReferenceid == 2) {
				this.locationReference = new TMCLocationReference();
			}else if(locationReferenceid==8){
				this.locationReference=new OpenlrLocationReference();
			}
//			this.locationReference.setId(locationReferenceid);
//			this.locationReference.encoding();
			this.locationReference.setEncodedStream(this.getEncodedByteStream());
//			this.locationReference.decoding(ReflectUtil.getClassField(LRC.ID, this.locationReference.getClass()));
			this.locationReference.decoding();
		} catch (IOException e) {
			throw new TPEGDecodeException("create locationReference error", e);
		}

	}
}
