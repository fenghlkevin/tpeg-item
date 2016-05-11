package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.poi_with_access_point_lr;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.PointLocationLineReferenceData;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.RelativeGeoCoordinate;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {  BaseComponent.ID , POIWithAccessPointLocationReference.POINTLOCATIONLINEREFERENCEDATA,POIWithAccessPointLocationReference.RELATIVEGEOCOORDINATE ,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP,BaseComponent.LENGTHATTR, POIWithAccessPointLocationReference.POINTLOCATIONLINEREFERENCEDATA,POIWithAccessPointLocationReference.RELATIVEGEOCOORDINATE}))
public class POIWithAccessPointLocationReference extends AbstractLocationReference 
{
    private static final long serialVersionUID = -919225101980803767L;
    public static final String POINTLOCATIONLINEREFERENCEDATA = "POIWithAccessPoint";
    public static final String RELATIVEGEOCOORDINATE = "poi";
    {
        this.setId(3);
        this.setLengthComp(0);
        this.setLengthAttr(0);
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = {POIWithAccessPointLocationReference.POINTLOCATIONLINEREFERENCEDATA,POIWithAccessPointLocationReference.RELATIVEGEOCOORDINATE }), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,POIWithAccessPointLocationReference.POINTLOCATIONLINEREFERENCEDATA,POIWithAccessPointLocationReference.RELATIVEGEOCOORDINATE }), length = -5)
    private NumberField lengthComp;
    
    @CoderItem(id = POIWithAccessPointLocationReference.POINTLOCATIONLINEREFERENCEDATA, type = CoderItemType.ITEM, length = 0)
    private PointLocationLineReferenceData POIWithAccessPoint;

    @CoderItem(id = POIWithAccessPointLocationReference.RELATIVEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private RelativeGeoCoordinate poi;
    

	public PointLocationLineReferenceData getPOIWithAccessPoint() {
		return POIWithAccessPoint;
	}

	public void setPOIWithAccessPoint(
			PointLocationLineReferenceData pOIWithAccessPoint) {
		POIWithAccessPoint = pOIWithAccessPoint;
	}

	public RelativeGeoCoordinate getPoi() {
		return poi;
	}

	public void setPoi(RelativeGeoCoordinate poi) {
		this.poi = poi;
	}

	public NumberField getLengthAttr() {
        return lengthAttr;
    }

    public NumberField getLengthComp() {
        return lengthComp;
    }

    public void setLengthAttr(int lengthAttr) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
        numberField.setNumber(lengthAttr);
    }

    public void setLengthComp(int lengthComp) {
        NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
        numberField.setNumber(lengthComp);
    }
}
