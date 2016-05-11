package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.grid1_lr;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.AbstractLocationReference;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.Rectangle;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {  BaseComponent.ID , Grid1LocationReference.ABSOLUTEGEOCOORDINATE ,Grid1LocationReference.RECTANGLE,Grid1LocationReference.NRROWS,Grid1LocationReference.NRCOLUMNS ,BaseComponent.LENGTHATTR,BaseComponent.LENGTHCOMP}), 
buildOrder = @EncoderBuildOrder(order = {BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,Grid1LocationReference.ABSOLUTEGEOCOORDINATE,Grid1LocationReference.RECTANGLE,Grid1LocationReference.NRROWS,Grid1LocationReference.NRCOLUMNS}))
public class Grid1LocationReference extends AbstractLocationReference {
    
    private static final long serialVersionUID = -919225101980803767L;
    
    public static final String ABSOLUTEGEOCOORDINATE = "absoluteGeoCoordinate";
    public static final String RECTANGLE = "rectangle";
    public static final String NRCOLUMNS = "nrColumns";
    public static final String NRROWS = "nrRows";
    
    {
        this.setId(7);
        this.setLengthComp(0);
        this.setLengthAttr(0);
    }
    /**
     * 1~5bytes 变长字节 ，该字段后所有字节
     */
    @CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = { Grid1LocationReference.ABSOLUTEGEOCOORDINATE ,Grid1LocationReference.RECTANGLE,Grid1LocationReference.NRROWS,Grid1LocationReference.NRCOLUMNS}), length = -5)
    private NumberField lengthAttr;
    /**
     * 1~5bytes变长 
     */
    @CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, Grid1LocationReference.ABSOLUTEGEOCOORDINATE ,Grid1LocationReference.RECTANGLE,Grid1LocationReference.NRROWS,Grid1LocationReference.NRCOLUMNS}), length = -5)
    private NumberField lengthComp;
    
    @CoderItem(id = Grid1LocationReference.ABSOLUTEGEOCOORDINATE, type = CoderItemType.ITEM, length = 0)
    private AbsoluteGeoCoordinate absoluteGeoCoordinate;
    @CoderItem(id = Grid1LocationReference.RECTANGLE, type = CoderItemType.ITEM, length = 0)
    private Rectangle rectangle;
	@CoderItem(id = Grid1LocationReference.NRCOLUMNS, type = CoderItemType.ITEM, length = 2)
	private NumberField nrColumns;
	@CoderItem(id = Grid1LocationReference.NRROWS, type = CoderItemType.ITEM, length = -5)
	private NumberField nrRows;

    public AbsoluteGeoCoordinate getAbsoluteGeoCoordinate() {
        return absoluteGeoCoordinate;
    }

    public void setAbsoluteGeoCoordinate(AbsoluteGeoCoordinate absoluteGeoCoordinate) {
        this.absoluteGeoCoordinate = absoluteGeoCoordinate;
    }

    public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public NumberField getLengthAttr() {
        return lengthAttr;
    }

    public NumberField getLengthComp() {
        return lengthComp;
    }
    
    
    public NumberField getNrColumns() {
		return nrColumns;
	}
	public void setNrColumns(int nrColumns) {
        NumberField numberField = (NumberField) this.newInstanceItem(Grid1LocationReference.NRCOLUMNS, false);
        numberField.setNumber(nrColumns);
		//this.nrColumns = nrColumns;
	}

	public NumberField getNrRows() {
		return nrRows;
	}
	public void setNrRows(int nrRows) {
        NumberField numberField = (NumberField) this.newInstanceItem(Grid1LocationReference.NRROWS, false);
        numberField.setNumber(nrRows);
		//this.nrRows = nrRows;
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
