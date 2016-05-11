package cn.com.cennavi.tpeg.item.component.tec;

import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID,Advice.ADVICECODE,Advice.SUBADVICECODE,Advice.N,Advice.FREETEXTS, BaseComponent.SELECTOR,Advice.VEHICLERESTRICTIONS, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,
        BaseComponent.SELECTOR,Advice.ADVICECODE,Advice.SUBADVICECODE,Advice.N,Advice.FREETEXTS,Advice.VEHICLERESTRICTIONS}))
public class Advice extends BaseComponent {
    private static final long serialVersionUID = -7795395723986634994L;
    
    public static final String ADVICECODE = "adviceCode";
    
    public static final String SUBADVICECODE = "subAdviceCode";
    
    public static final String N = "n";
    
    public static final String FREETEXTS = "freeTexts";
    
    public static final String VEHICLERESTRICTIONS = "vehicleRestrictions";
    
    {
    	this.setId(6);
    	this.setSelector();
    	this.setLengthAttr(0);
    	this.setLengthComp(0);
    }
    
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = {BaseComponent.SELECTOR,
            Advice.ADVICECODE, Advice.SUBADVICECODE,Advice.N,Advice.FREETEXTS}))
    private NumberField lengthAttr;
    
    /**
     * 1~5bytes变长
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,BaseComponent.SELECTOR,
            Advice.ADVICECODE, Advice.SUBADVICECODE,Advice.N,Advice.FREETEXTS,Advice.VEHICLERESTRICTIONS}))
    private NumberField lengthComp;
    
    @CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {Advice.ADVICECODE,Advice.SUBADVICECODE,Advice.FREETEXTS }, dependentFieldDigit = {0,1,2}))
    private ByteFlag selector;
    
    @CoderItem(id=Advice.ADVICECODE, type = CoderItemType.ITEM, length = 1)
    private NumberField adviceCode;
    
    @CoderItem(id=Advice.SUBADVICECODE, type = CoderItemType.ITEM, length = 1)
    private NumberField subAdviceCode;
    
    @CoderItem(id=Advice.N, type = CoderItemType.ITEM, length = -5)
    private NumberField n;
    
    @CoderItem(id=Advice.FREETEXTS, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
    private List<LocalisedShortString> freeTexts;
    
    @CoderItem(id=Advice.VEHICLERESTRICTIONS, type = CoderItemType.LIST, length =CoderItem.ITEMLENGTH_NOMAXLENGTH)
    private List<VehicleRestriction> vehicleRestrictions;
    
    public List<VehicleRestriction> getVehicleRestrictions() {
        return vehicleRestrictions;
    }

    public void setVehicleRestrictions(List<VehicleRestriction> vehicleRestrictions) {
        this.vehicleRestrictions = vehicleRestrictions;
    }

    public ByteFlag getSelector() {
        return selector;
    }

    public void setSelector() {
        this.newInstanceItem(BaseComponent.SELECTOR, false);
    }

    public List<LocalisedShortString> getFreeTexts() {
        return freeTexts;
    }

    public void setFreeTexts(List<LocalisedShortString> freeTexts) {
    	 NumberField numberField = (NumberField) this.newInstanceItem(Advice.N, false);
         numberField.setNumber(freeTexts.size());
        this.freeTexts = freeTexts;
    }

    public NumberField getSubAdviceCode() {
        return subAdviceCode;
    }

    public void setSubAdviceCode(int subAdviceCode) {
        NumberField numberField = (NumberField) this.newInstanceItem(Advice.SUBADVICECODE, false);
        numberField.setNumber(subAdviceCode);
    }

    public NumberField getAdviceCode() {
        return adviceCode;
    }

    public void setAdviceCode(int adviceCode) {
        NumberField numberField = (NumberField) this.newInstanceItem(Advice.ADVICECODE, false);
        numberField.setNumber(adviceCode);
    }

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

}   
