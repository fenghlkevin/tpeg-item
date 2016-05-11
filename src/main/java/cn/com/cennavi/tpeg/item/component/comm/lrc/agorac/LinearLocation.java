package cn.com.cennavi.tpeg.item.component.comm.lrc.agorac;

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
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * LinearLocation
 * 
 * @author fengheliang
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, LinearLocation.LOCATIONTYPE, LinearLocation.COREPOINTS, LinearLocation.EXTENDEDLOCATION, LinearLocation.ATTRIBUTELIST,
		BaseComponent.SELECTOR, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,
		BaseComponent.SELECTOR, LinearLocation.LOCATIONTYPE, LinearLocation.COREPOINTS, LinearLocation.EXTENDEDLOCATION, LinearLocation.ATTRIBUTELIST }))
public class LinearLocation extends BaseComponent {

	private static final long serialVersionUID = 2726191406208382911L;

	public static final String LOCATIONTYPE = "locationType";

	public static final String EXTENDEDLOCATION = "extendedLocation";

	public static final String COREPOINTS = "corePoints";

	public static final String ATTRIBUTELIST = "attributeList";

	@CoderItem(id = LinearLocation.COREPOINTS, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<CorePoint> corePoints = new ArrayList<CorePoint>();

	@CoderItem(id = LinearLocation.ATTRIBUTELIST, type = CoderItemType.ITEM, length = 0)
	private AttributeList attributeList;

	/**
	 * (bit 0: lcoationDirection bit 1: locationType) 1 byte
	 */
	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { LinearLocation.LOCATIONTYPE }, dependentFieldDigit = { 1 }))
	private ByteFlag selector;

	@CoderItem(id = LinearLocation.EXTENDEDLOCATION, type = CoderItemType.ITEM, length = 1)
	private NumberField extendedLocation;

	/**
	 * 1 byte
	 */
	@CoderItem(id = LinearLocation.LOCATIONTYPE, type = CoderItemType.ITEM, length = 1)
	private NumberField locationType;

	/**
	 * 1~5bytes 变长字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.SELECTOR, LinearLocation.LOCATIONTYPE, LinearLocation.COREPOINTS,
			LinearLocation.EXTENDEDLOCATION, LinearLocation.ATTRIBUTELIST }), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, BaseComponent.SELECTOR, LinearLocation.LOCATIONTYPE,
			LinearLocation.COREPOINTS, LinearLocation.EXTENDEDLOCATION, LinearLocation.ATTRIBUTELIST }), length = -5)
	private NumberField lengthComp;

	public ByteFlag getSelector() {
		return selector;
	}

	public void setSelector() {
		this.newInstanceItem(BaseComponent.SELECTOR, false);
	}

	public NumberField getLocationType() {
		return locationType;
	}

	public void setLocationType(int locationType) {
		NumberField numberField = (NumberField) this.newInstanceItem(LinearLocation.LOCATIONTYPE, false);
		numberField.setNumber(locationType);
	}

	public NumberField getExtendedLocation() {
		return extendedLocation;
	}

	public void setExtendedLocation(int extendedLocation) {
		NumberField numberField = (NumberField) this.newInstanceItem(LinearLocation.EXTENDEDLOCATION, false);
		numberField.setNumber(extendedLocation);
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public List<CorePoint> getCorePoints() {
		return corePoints;
	}

	public void addCorePoint(CorePoint corePoint) {
		this.corePoints.add(corePoint);
	}

	public AttributeList getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(AttributeList attributeList) {
		this.attributeList = attributeList;
	}

	@Override
	public void decoding() {
		/**
		 * 首先读取LinearLocation中locationType后面所有长度，并从stream中读取数据内容。对读取出的数据内容做解码操作
		 */
		// 自动解析 BaseComponent.ID, BaseComponent.LENGTHCOMP,
		// BaseComponent.LENGTHATTR,BaseComponent.SELECTOR,
		// LinearLocation.LOCATIONTYPE
		super.decoding();
		ByteArrayInputStream bis = null;
		try {
			byte[] afterLocationType = new byte[this.getLengthAttr().getNumber() - 2];
			//byte[] afterLocationType = new byte[this.getLengthComp().getNumber() - 3];
			if (afterLocationType.length > this.getEncodedByteStream().available()) {
				throw new TPEGDecodeException("Stream don't have enough bytes for LinearLocation. need Length:[" + afterLocationType.length + "]");
			}
			this.getEncodedByteStream().read(afterLocationType);
			bis = new ByteArrayInputStream(afterLocationType);

			int id = bis.read();
			do {
				if (id == 4) {
					// CorePoint
					CorePoint cp = new CorePoint();
					cp.setId(id);
					cp.encoding();
					cp.setEncodedStream(bis);
					cp.decoding(ReflectUtil.getClassField(BaseComponent.ID, CorePoint.class));
					//解码到AttributeList前面，
					//手动解码AttributeList
					int next = bis.read();
					if(next==3){
						//TODO
						AttributeList al = new AttributeList();
						al.setId(id);
						al.encoding();
						al.setEncodedStream(bis);
						al.decoding(ReflectUtil.getClassField(BaseComponent.ID, CorePoint.class));
						cp.setAttributeList(al);
						id = bis.read();
					}
//					else if (next == 5) {
//					// ExtendedLocation TODO 暂时不使用
//					this.setExtendedLocation(next);
					//id = bis.read();
//				}
					else{
						id=next;
					}
					
					this.addCorePoint(cp);
				} 
//				else if (id == 5) {
//					// ExtendedLocation
//					this.setExtendedLocation(id);
//				}
				else if (id == 3) {
					// AttributeList
					AttributeList al = new AttributeList();
					al.setId(id);
					al.encoding();
					al.setEncodedStream(bis);
					al.decoding(ReflectUtil.getClassField(BaseComponent.ID, CorePoint.class));
					this.setAttributeList(al);
					id = bis.read();
				}
				//id=-1;
			} while (bis.available() > 0);
		} catch (IOException e) {
			throw new TPEGDecodeException("Decoding LinearLocation error", e);
		}finally{
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					throw new TPEGDecodeException("Close LinearLocation Stream error", e);
				}
			}
		}

	}

}
