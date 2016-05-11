package cn.com.cennavi.tpeg.item.component.tec;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {LocalisedShortString.LANGUAGECODE, LocalisedShortString.FREETEXT}), buildOrder = @EncoderBuildOrder(order = {
		LocalisedShortString.LANGUAGECODE, LocalisedShortString.FREETEXT}))
public class LocalisedShortString extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = -7795395723986634994L;

	public static final String LANGUAGECODE = "languageCode";

	public static final String FREETEXT = "freeText";

	@CoderItem(id = LocalisedShortString.LANGUAGECODE, type = CoderItemType.ITEM, length = 1)
	private NumberField languageCode;

	@CoderItem(id = LocalisedShortString.FREETEXT, type = CoderItemType.ITEM, length = 0)
	private ShortString freeText;

	public NumberField getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(int languageCode) {
		NumberField numberField = (NumberField) this.newInstanceItem(LocalisedShortString.LANGUAGECODE, false);
		numberField.setNumber(languageCode);
	}

	public ShortString getFreeText() {
		return freeText;
	}

	public void setFreeText(ShortString freeText) {
		this.freeText = freeText;
	}

}
