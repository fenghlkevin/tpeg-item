package cn.com.cennavi.tpeg.item.component.tec;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {LocalisedLongString.LANGUAGECODE, LocalisedLongString.FREETEXT}), buildOrder = @EncoderBuildOrder(order = {
        LocalisedLongString.LANGUAGECODE, LocalisedLongString.FREETEXT}))
public class LocalisedLongString extends AbstractCoderItem implements Item {
	
	private static final long serialVersionUID = -7795395723986634994L;

	public static final String LANGUAGECODE = "languageCode";

	public static final String FREETEXT = "freeText";

	@CoderItem(id = LocalisedLongString.LANGUAGECODE, type = CoderItemType.ITEM, length = 1)
	private NumberField languageCode;

	@CoderItem(id = LocalisedLongString.FREETEXT, type = CoderItemType.ITEM, length = 0)
	private LongString freeText;

	public NumberField getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(int languageCode) {
		NumberField numberField = (NumberField) this.newInstanceItem(LocalisedLongString.LANGUAGECODE, false);
		numberField.setNumber(languageCode);
	}

	public LongString getFreeText() {
		return freeText;
	}

	public void setFreeText(LongString freeText) {
		this.freeText = freeText;
	}
}
