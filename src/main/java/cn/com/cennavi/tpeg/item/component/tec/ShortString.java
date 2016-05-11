package cn.com.cennavi.tpeg.item.component.tec;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.core.base.StringItem;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.codec.util.StrHelper;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {ShortString.N, ShortString.BYTES}), buildOrder = @EncoderBuildOrder(order = {ShortString.N, ShortString.BYTES}))
public class ShortString extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = -7795395723986634994L;

	public static final String N = "n";
	public static final String BYTES = "bytes";

	@CoderItem(id = ShortString.N, type = CoderItemType.ITEM, length = 1)
	private NumberField n;

	@CoderItem(id = ShortString.BYTES, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private StringItem bytes;

	public NumberField getN() {
		return n;
	}

	// public void setN(int n) {
	// NumberField numberField = (NumberField)
	// this.newInstanceItem(ShortString.N, false);
	// numberField.setNumber(n);
	// }

	public StringItem getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		StringItem str = (StringItem) this.newInstanceItem(ShortString.BYTES, false);
		str.setStr(bytes);

		NumberField numberField = (NumberField) this.newInstanceItem(ShortString.N, false);
		numberField.setNumber(StrHelper.getWordCount(bytes));
	}
	
	@Override
	public void decoding() {
			super.decoding(null, ReflectUtil.getClassField(ShortString.N, this.getClass()));
			int count=this.n.getNumber();
			byte[] bs=new byte[count];
			try {
				this.getEncodedByteStream().read(bs);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.setBytes(new String(bs));

	}

}
