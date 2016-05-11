package cn.com.cennavi.tpeg.item.component.comm.lrc.agorac;

import java.io.IOException;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.core.base.StringItem;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;

/**
 * @author fengheliang
 *
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { IPSignature.FUNCTIONALROADCLASS, IPSignature.INTERSECTIONTYPE, IPSignature.NUMOFINTERINTERSECT, IPSignature.FORMOFWAY,
		IPSignature.ROADDESCRIPTOR, IPSignature.ROADDESCRIPTORLENGTH, IPSignature.SELECTOR }), buildOrder = @EncoderBuildOrder(order = { IPSignature.SELECTOR, IPSignature.FUNCTIONALROADCLASS,
		IPSignature.INTERSECTIONTYPE, IPSignature.NUMOFINTERINTERSECT, IPSignature.FORMOFWAY, IPSignature.ROADDESCRIPTORLENGTH, IPSignature.ROADDESCRIPTOR }))
public class IPSignature extends AbstractCoderItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6792166491069102933L;

	public static final String SELECTOR = "selector";

	public static final String FUNCTIONALROADCLASS = "functionalRoadClass";

	public static final String INTERSECTIONTYPE = "intersectionType";

	public static final String NUMOFINTERINTERSECT = "numOfInterIntersect";

	public static final String FORMOFWAY = "formOfWay";

	public static final String ROADDESCRIPTORLENGTH = "roadDescriptorLength";

	public static final String ROADDESCRIPTOR = "roadDescriptor";

	@CoderItem(id = IPSignature.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = -2, dependent = @Dependent(dependentField = { IPSignature.FUNCTIONALROADCLASS, IPSignature.INTERSECTIONTYPE,
			IPSignature.NUMOFINTERINTERSECT, IPSignature.FORMOFWAY, IPSignature.ROADDESCRIPTORLENGTH }, dependentFieldDigit = { 3, 4, 5, 6, 7 }))
	private ByteFlag selector;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.FUNCTIONALROADCLASS, type = CoderItemType.ITEM, length = 1)
	private NumberField functionalRoadClass;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.INTERSECTIONTYPE, type = CoderItemType.ITEM, length = 1)
	private NumberField intersectionType;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.NUMOFINTERINTERSECT, type = CoderItemType.ITEM, length = 1)
	private NumberField numOfInterIntersect;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.FORMOFWAY, type = CoderItemType.ITEM, length = 1)
	private NumberField formOfWay;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.ROADDESCRIPTORLENGTH, type = CoderItemType.LENGTH, length = 1, dependent = @Dependent(dependentField = { IPSignature.ROADDESCRIPTOR, }))
	private NumberField roadDescriptorLength;

	/**
	 * 1 byte
	 */
	@CoderItem(id = IPSignature.ROADDESCRIPTOR, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private StringItem roadDescriptor;

	public ByteFlag getSelector() {
		return selector;
	}

	public NumberField getFunctionalRoadClass() {
		return functionalRoadClass;
	}

	public NumberField getIntersectionType() {
		return intersectionType;
	}

	public NumberField getNumOfInterIntersect() {
		return numOfInterIntersect;
	}

	public NumberField getFormOfWay() {
		return formOfWay;
	}

	public NumberField getRoadDescriptorLength() {
		return roadDescriptorLength;
	}

	public StringItem getRoadDescriptor() {
		return roadDescriptor;
	}

	public void setSelector() {
		this.newInstanceItem(IPSignature.SELECTOR, false);
	}

	public void setFunctionalRoadClass(int functionalRoadClass) {
		NumberField numberField = (NumberField) this.newInstanceItem(IPSignature.FUNCTIONALROADCLASS, false);
		numberField.setNumber(functionalRoadClass);
	}

	public void setIntersectionType(int intersectionType) {
		NumberField numberField = (NumberField) this.newInstanceItem(IPSignature.INTERSECTIONTYPE, false);
		numberField.setNumber(intersectionType);
	}

	public void setNumOfInterIntersect(int numOfInterIntersect) {
		NumberField numberField = (NumberField) this.newInstanceItem(IPSignature.NUMOFINTERINTERSECT, false);
		numberField.setNumber(numOfInterIntersect);
	}

	public void setFormOfWay(int formOfWay) {
		NumberField numberField = (NumberField) this.newInstanceItem(IPSignature.FORMOFWAY, false);
		numberField.setNumber(formOfWay);
	}

	public void setRoadDescriptorLength(int roadDescriptorCount) {
		NumberField numberField = (NumberField) this.newInstanceItem(IPSignature.ROADDESCRIPTORLENGTH, false);
		numberField.setNumber(roadDescriptorCount);
	}

	public void setRoadDescriptor(String roadDescriptor) {
		StringItem stringItem = (StringItem) this.newInstanceItem(IPSignature.ROADDESCRIPTOR, false);
		stringItem.setStr(roadDescriptor);
	}

	@Override
	public void decoding() {
		super.decoding();
		//IPSignature.SELECTOR, IPSignature.FUNCTIONALROADCLASS,IPSignature.INTERSECTIONTYPE, IPSignature.NUMOFINTERINTERSECT, IPSignature.FORMOFWAY, IPSignature.ROADDESCRIPTORLength,
		if(this.getRoadDescriptorLength()==null){
			return;
		}
		int length=this.getRoadDescriptorLength().getNumber();
		try {
			if(length>this.getEncodedByteStream().available()){
				throw new TPEGDecodeException("Stream don't have enough bytes for IPSignature's 'RoadDescriptor'. need Length:[" + length + "]");
			}
			byte[] strbytes=new byte[length];
			this.getEncodedByteStream().read(strbytes);
			StringItem si=new StringItem(null,length);
			si.setEncodedArray(strbytes);
			this.roadDescriptor=si;
		} catch (IOException e) {
			throw new TPEGDecodeException("Decoding IPSignature error", e);
		}
	}

}
