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
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.core.base.StringItem;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

/**
 * @author fengheliang
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, AttributeList.ATTRIBUTES, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, AttributeList.ATTRIBUTES }))
public class AttributeList extends BaseComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4220505801304649534L;

	public static final String ATTRIBUTES = "attributes";

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.ITEM, dependent = @Dependent(dependentField = {}), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR }), length = -5)
	private NumberField lengthComp;

	@CoderItem(id = AttributeList.ATTRIBUTES, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<Attribute> attributes = new ArrayList<Attribute>();

	public NumberField getLengthAttr() {
		return lengthAttr;
	}

	public NumberField getLengthComp() {
		return lengthComp;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setLengthAttr(int lengthAttr) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
		numberField.setNumber(lengthAttr);
	}

	public void setLengthComp(int lengthComp) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
		numberField.setNumber(lengthComp);
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}

	@Override
	public void decoding() {
		// 自动解码 BaseComponent.ID, BaseComponent.LENGTHCOMP,
		// BaseComponent.LENGTHATTR
		super.decoding();
		int length = this.getLengthAttr().getNumber();
		ByteArrayInputStream bis = null;
		try {
			if (length > this.getEncodedByteStream().available()) {
				throw new TPEGDecodeException("Stream don't have enough bytes for AttributeList. need Length:[" + length + "]");
			}
			byte[] allAttributes = new byte[length];
			this.getEncodedByteStream().read(allAttributes);
			bis = new ByteArrayInputStream(allAttributes);
			while (bis.available() > 0) {
				Attribute attribute = new Attribute();
				attribute.setEncodedStream(bis);
				attribute.decoding();
				this.addAttribute(attribute);

			}
		} catch (IOException e) {
			throw new TPEGDecodeException("Decoding IPSignature error", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					throw new TPEGDecodeException("Close AttributeList Stream error", e);
				}
			}
		}

	}

	@CoderLinks(compileOrder = @EncoderBuildOrder(order = { Attribute.ATTRNUM, Attribute.INTSILOMB, Attribute.INTUNLOMB, Attribute.SHORTSTRING, Attribute.SHORTSTRINGLENGTH, Attribute.SELECTOR }), buildOrder = @EncoderBuildOrder(order = {
			Attribute.ATTRNUM, Attribute.SELECTOR, Attribute.INTSILOMB, Attribute.INTUNLOMB, Attribute.SHORTSTRINGLENGTH, Attribute.SHORTSTRING }))
	public final static class Attribute extends AbstractCoderItem {

		private static final long serialVersionUID = -3348341114376782184L;

		public static final String ATTRNUM = "attrNum";

		public static final String SELECTOR = "selector";

		public static final String INTSILOMB = "intSiLoMB";

		public static final String INTUNLOMB = "intUnLoMB";

		public static final String SHORTSTRINGLENGTH = "shortStringLength";

		public static final String SHORTSTRING = "shortString";

		/**
		 * Selector (bit 0: intSiLoMB /bit 1: intUnLoMB /Bit 2: shortString) 1
		 * byte
		 */
		@CoderItem(id = Attribute.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = { Attribute.INTSILOMB, Attribute.INTUNLOMB,
				Attribute.SHORTSTRINGLENGTH }, dependentFieldDigit = { 0, 1, 2 }))
		private ByteFlag selector;

		/**
		 * 1~5bbyte
		 */
		@CoderItem(id = Attribute.INTSILOMB, type = CoderItemType.ITEM, length = -5)
		private NumberField intSiLoMB;

		/**
		 * 1~5bbyte
		 */
		@CoderItem(id = Attribute.INTUNLOMB, type = CoderItemType.ITEM, length = -5)
		private NumberField intUnLoMB;

		/**
		 * 1byte
		 */
		@CoderItem(id = Attribute.SHORTSTRINGLENGTH, type = CoderItemType.ITEM, length = 1, dependent = @Dependent(dependentField = { Attribute.SHORTSTRING }))
		private NumberField shortStringLength;

		/**
		 * nbyte
		 */
		@CoderItem(id = Attribute.SHORTSTRING, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
		private StringItem shortString;

		/**
		 * 1 byte
		 */
		@CoderItem(id = Attribute.ATTRNUM, type = CoderItemType.ITEM, length = 1)
		private NumberField attrNum;

		public ByteFlag getSelector() {
			return selector;
		}

		public NumberField getIntSiLoMB() {
			return intSiLoMB;
		}

		public NumberField getIntUnLoMB() {
			return intUnLoMB;
		}

		public NumberField getShortStringLength() {
			return shortStringLength;
		}

		public StringItem getShortString() {
			return shortString;
		}

		public NumberField getAttrNum() {
			return attrNum;
		}

		public void setSelector(ByteFlag selector) {
			this.newInstanceItem(Attribute.SELECTOR, false);
		}

		public void setIntSiLoMB(int intSiLoMB) {
			NumberField numberField = (NumberField) this.newInstanceItem(Attribute.INTSILOMB, false);
			numberField.setNumber(intSiLoMB);
		}

		public void setIntUnLoMB(int intUnLoMB) {
			NumberField numberField = (NumberField) this.newInstanceItem(Attribute.INTUNLOMB, false);
			numberField.setNumber(intUnLoMB);
		}

		public void setShortStringLength(int shortStringLength) {
			NumberField numberField = (NumberField) this.newInstanceItem(Attribute.SHORTSTRINGLENGTH, false);
			numberField.setNumber(shortStringLength);
		}

		public void setShortString(String shortString) {
			StringItem stringItem = (StringItem) this.newInstanceItem(Attribute.SHORTSTRING, false);
			stringItem.setStr(shortString);
		}

		public void setAttrNum(int attrNum) {
			NumberField numberField = (NumberField) this.newInstanceItem(Attribute.ATTRNUM, false);
			numberField.setNumber(attrNum);
		}
		
		@Override
		public void decoding() {
			super.decoding();
			//Attribute.ATTRNUM, Attribute.SELECTOR, Attribute.INTSILOMB, Attribute.INTUNLOMB, Attribute.SHORTSTRINGLENGTH
			int length=this.getShortStringLength().getNumber();
			try {
				if(length>this.getEncodedByteStream().available()){
					throw new TPEGDecodeException("Stream don't have enough bytes for Attribute's 'ShortString'. need Length:[" + length + "]");
				}
				byte[] strbytes=new byte[length];
				this.getEncodedByteStream().read(strbytes);
				StringItem si=new StringItem(null,length);
				si.setEncodedArray(strbytes);
				this.shortString=si;
			} catch (IOException e) {
				throw new TPEGDecodeException("Decoding Attribute error", e);
			}
		}

	}

}
