package cn.com.cennavi.tpeg.item.component;

import java.awt.geom.Point2D;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.core.base.NumberField;

/**
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
public abstract class BaseComponent extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = 1594580513164585312L;
	
	public static final String ID="id";
	
	public static final String TPEG_FIELDID_MMC="MMC";
	
	public static final String TPEG_FIELD_LRC="LRC";
	
	public static final String LENGTHATTR="lengthAttr";
	
	public static final String LENGTHCOMP="lengthComp";
	
	public static final String SELECTOR="selector";
	
	public static final String STARTTIME="startTIme";
	
	public static final String STOPTIME="stopTIme";
	/**
	 * id值，不同于sid
	 */
	@CoderItem(id=BaseComponent.ID, type=CoderItemType.ITEM,length=1)
	protected NumberField id;
	
	/**
	 * 该数据的左下右上坐标
	 */
	protected Point2D.Double rect[];
	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	//protected NumberField lengthAttr;
	/**
	 * 1~5bytes变长
	 */
	//protected NumberField lengthComp;

	public NumberField getId() {
		return id;
	}

	public void setId(int id) {
		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.ID,false);
		numberField.setNumber(id);
	}

	public Point2D.Double[] getRect() {
		return rect;
	}

	public void setRect(Point2D.Double[] rect) {
		this.rect = rect;
	}

//	public NumberField getLengthAttr() {
//		return lengthAttr;
//	}
//
//	public void setLengthAttr(NumberField lengthAttr) {
//		this.lengthAttr = lengthAttr;
//	}
//
//	public NumberField getLengthComp() {
//		return lengthComp;
//	}
//
//	public void setLengthComp(NumberField lengthComp) {
//		this.lengthComp = lengthComp;
//	}

}
