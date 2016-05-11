package cn.com.cennavi.tpeg.item.component.tec;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { RestrictionType.RESTRICTIONTYPE,RestrictionType.RESTRICTIONVALUE, BaseComponent.SELECTOR }), buildOrder = @EncoderBuildOrder(order = {
        RestrictionType.RESTRICTIONTYPE,BaseComponent.SELECTOR,RestrictionType.RESTRICTIONVALUE}))
public class RestrictionType extends BaseComponent {
    
    private static final long serialVersionUID = -7795395723986634994L;
    
    public static final String RESTRICTIONTYPE = "restrictionType";
    
    public static final String RESTRICTIONVALUE = "restrictionValue";
    
    {
    	this.setSelector();
    }
    
    @CoderItem(id=RestrictionType.RESTRICTIONTYPE, type = CoderItemType.ITEM, length = 1)
    private NumberField restrictionType;
    
    @CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {RestrictionType.RESTRICTIONVALUE}, dependentFieldDigit = {0}))
    private ByteFlag selector;
    
    @CoderItem(id=RestrictionType.RESTRICTIONVALUE, type = CoderItemType.ITEM, length = -5)
    private NumberField restrictionValue;
    
    
    public NumberField getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(int restrictionType) {
        NumberField numberField = (NumberField) this.newInstanceItem(RestrictionType.RESTRICTIONTYPE, false);
        numberField.setNumber(restrictionType);
    }

    public NumberField getRestrictionValue() {
        return restrictionValue;
    }

    public void setRestrictionValue(int restrictionValue) {
        NumberField numberField = (NumberField) this.newInstanceItem(RestrictionType.RESTRICTIONVALUE, false);
        numberField.setNumber(restrictionValue);
    }

    public ByteFlag getSelector() {
        return selector;
    }

    public void setSelector() {
        this.newInstanceItem(BaseComponent.SELECTOR, false);
    }
    
}
