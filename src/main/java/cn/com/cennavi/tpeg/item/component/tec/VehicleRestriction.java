package cn.com.cennavi.tpeg.item.component.tec;

import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID,VehicleRestriction.VEHICLETYPE,VehicleRestriction.N ,VehicleRestriction.RESTRICTIONTYPES,BaseComponent.SELECTOR, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = { BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,
       BaseComponent.SELECTOR,VehicleRestriction.VEHICLETYPE,VehicleRestriction.N,VehicleRestriction.RESTRICTIONTYPES}))
public class VehicleRestriction extends BaseComponent {
    
    private static final long serialVersionUID = -7795395723986634994L;
    
    public static final String VEHICLETYPE = "vehicleType";
    
    public static final String RESTRICTIONTYPES = "restrictionTypes";
    
    public static final String N = "n";
    
    {
    	this.setId(7);
    	this.setLengthAttr(0);
    	this.setLengthComp(0);
    	this.setSelector();
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = {BaseComponent.SELECTOR,VehicleRestriction.VEHICLETYPE,VehicleRestriction.N,VehicleRestriction.RESTRICTIONTYPES}))
    private NumberField lengthAttr;
    
    /**
     * 1~5bytes变长
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, length = -5, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,BaseComponent.SELECTOR,VehicleRestriction.VEHICLETYPE,VehicleRestriction.N,VehicleRestriction.RESTRICTIONTYPES}))
    private NumberField lengthComp;
    
    @CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {VehicleRestriction.VEHICLETYPE,VehicleRestriction.RESTRICTIONTYPES}, dependentFieldDigit = {0,1}))
    private ByteFlag selector;
    
    @CoderItem(id=VehicleRestriction.VEHICLETYPE, type = CoderItemType.ITEM, length = 1)
    private NumberField vehicleType;
    
    @CoderItem(id=VehicleRestriction.N, type = CoderItemType.ITEM, length = -5)
    private NumberField n;
    
    @CoderItem(id=VehicleRestriction.RESTRICTIONTYPES, type = CoderItemType.LIST, length=CoderItem.ITEMLENGTH_NOMAXLENGTH)
    private List<RestrictionType> restrictionTypes;

    public NumberField getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        NumberField numberField = (NumberField) this.newInstanceItem(VehicleRestriction.VEHICLETYPE, false);
        numberField.setNumber(vehicleType);
    }

    public List<RestrictionType> getRestrictionType() {
        return restrictionTypes;
    }

    public void setRestrictionTypes(List<RestrictionType> restrictionTypes) {
    	NumberField numberField = (NumberField) this.newInstanceItem(VehicleRestriction.N, false);
        numberField.setNumber(restrictionTypes.size());
        this.restrictionTypes = restrictionTypes;
    }

    public ByteFlag getSelector() {
        return selector;
    }

    public void setSelector() {
        this.newInstanceItem(BaseComponent.SELECTOR, false);
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
