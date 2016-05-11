package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.AbsoluteGeoCoordinate;
import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common.Shape;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, Shape.POINTS, Shape.LENGTHATTR, Shape.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, Shape.POINTS}))
public class Shape extends BaseComponent {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String POINTS = "points";
	
	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { Shape.POINTS}), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长 
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,Shape.POINTS}), length = -5)
	private NumberField lengthComp;

	/**
	 * 1byte长度，版本号，固定为30
	 */
	@CoderItem(id = Shape.POINTS, type = CoderItemType.ITEM, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<AbsoluteGeoCoordinate> points;

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
