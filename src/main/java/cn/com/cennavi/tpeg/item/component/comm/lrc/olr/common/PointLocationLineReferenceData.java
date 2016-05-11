package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.ByteFlag;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {PointLocationLineReferenceData.FIRSTLOCATIONREFERENCEPOINT, PointLocationLineReferenceData.LASTLOCATIONREFERENCEPOINT,PointLocationLineReferenceData.SIDEOFROAD,PointLocationLineReferenceData.ORIENTATION, PointLocationLineReferenceData.POSITIVEOFFSET,BaseComponent.SELECTOR}), 
buildOrder = @EncoderBuildOrder(order = {PointLocationLineReferenceData.FIRSTLOCATIONREFERENCEPOINT, PointLocationLineReferenceData.LASTLOCATIONREFERENCEPOINT,PointLocationLineReferenceData.SIDEOFROAD,PointLocationLineReferenceData.ORIENTATION,BaseComponent.SELECTOR,PointLocationLineReferenceData.POSITIVEOFFSET}))
public class PointLocationLineReferenceData extends BaseComponent {

    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String FIRSTLOCATIONREFERENCEPOINT = "firstLocationReferencePoint";
    
    public static final String LASTLOCATIONREFERENCEPOINT = "lastLocationReferencePoint";
    
    public static final String POSITIVEOFFSET = "positiveOffset";
    
    public static final String SIDEOFROAD="sideOfRoad";
	
	public static final String ORIENTATION="orientation";
    
    @CoderItem(id = PointLocationLineReferenceData.FIRSTLOCATIONREFERENCEPOINT, type = CoderItemType.ITEM, length = 0,toMapper=true)
    private FirstLocationReferencePoint firstLocationReferencePoint;
    
    @CoderItem(id = PointLocationLineReferenceData.LASTLOCATIONREFERENCEPOINT, type = CoderItemType.ITEM, length = 0,toMapper=true)
    private LastLocationReferencePoint lastLocationReferencePoint;
    
    @CoderItem(id = PointLocationLineReferenceData.POSITIVEOFFSET, type = CoderItemType.ITEM, length = -4,toMapper=true)
    private NumberField positiveOffset;
    
    @CoderItem(id = PointLocationLineReferenceData.SIDEOFROAD, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField sideOfRoad;
	
	@CoderItem(id = PointLocationLineReferenceData.ORIENTATION, type = CoderItemType.ITEM, length = 1,toMapper=true)
	private NumberField orientation;

	@CoderItem(id = BaseComponent.SELECTOR, type = CoderItemType.FLAGITEM_SECLECTOR, length = 1, dependent = @Dependent(dependentField = {PointLocationLineReferenceData.POSITIVEOFFSET}, dependentFieldDigit = {0}))
    private ByteFlag selector;
	
	public ByteFlag getSelector() {
        return selector;
    }

    public void setSelector() {
        this.newInstanceItem(BaseComponent.SELECTOR, false);
    }
	
    public FirstLocationReferencePoint getFirstLocationReferencePoint() {
        return firstLocationReferencePoint;
    }

    public void setFirstLocationReferencePoint(FirstLocationReferencePoint firstLocationReferencePoint) {
        this.firstLocationReferencePoint = firstLocationReferencePoint;
    }

    public LastLocationReferencePoint getLastLocationReferencePoint() {
        return lastLocationReferencePoint;
    }

    public void setLastLocationReferencePoint(LastLocationReferencePoint lastLocationReferencePoint) {
        this.lastLocationReferencePoint = lastLocationReferencePoint;
    }

    public NumberField getPositiveOffset() {
        return positiveOffset;
    }

    public void setPositiveOffset(int positiveOffset) {
        NumberField numberField = (NumberField) this.newInstanceItem(PointLocationLineReferenceData.POSITIVEOFFSET, false);
		numberField.setNumber(positiveOffset);
    }
    
    public NumberField getSideOfRoad() {
		return sideOfRoad;
	}

	public void setSideOfRoad(int sideOfRoad) {
		NumberField numberField = (NumberField) this.newInstanceItem(PointLocationLineReferenceData.SIDEOFROAD, false);
		numberField.setNumber(sideOfRoad);
	}

	public NumberField getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		NumberField numberField = (NumberField) this.newInstanceItem(PointLocationLineReferenceData.ORIENTATION, false);
		numberField.setNumber(orientation);
	}
    
}
