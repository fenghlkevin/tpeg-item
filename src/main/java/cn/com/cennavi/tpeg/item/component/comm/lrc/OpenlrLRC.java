//package cn.com.cennavi.tpeg.item.component.comm.lrc;
//
//import cn.com.cennavi.codec.core.annotation.CoderItem;
//import cn.com.cennavi.codec.core.annotation.CoderLinks;
//import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
//import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
//import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
//import cn.com.cennavi.codec.core.base.NumberField;
//import cn.com.cennavi.tpeg.item.component.BaseComponent;
//import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.LinearLocationReference;
//import cn.com.cennavi.tpeg.item.component.comm.lrc.olr.LocationDescription;
//
///**
// * 支持动态位置参考点的 LRC对象
// * 
// * id:8
// * 
// * @author Administrator
// * 
// */
//@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, OpenlrLRC.VERSION, OpenlrLRC.LINEARLOCATIONREFERENCE,OpenlrLRC.LOCATIONDESCRIPTION, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
//		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, OpenlrLRC.VERSION, OpenlrLRC.LINEARLOCATIONREFERENCE,OpenlrLRC.LOCATIONDESCRIPTION }))
//public class OpenlrLRC extends LRC {
//
//	private static final long serialVersionUID = -919225101980803767L;
//
//	public static final String VERSION = "version";
//
//	public static final String LINEARLOCATIONREFERENCE = "linearLocationReference";
//	
//	public static final String LOCATIONDESCRIPTION = "locationDescription";
//
//	/**
//	 * 1~5bytes 变长字节 ，该字段后所有字节
//	 */
//	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH,dependent = @Dependent(dependentField = {  OpenlrLRC.VERSION}), length = -5)
//	private NumberField lengthAttr;
//	/**
//	 * 1~5bytes变长 
//	 */
//	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, OpenlrLRC.VERSION, OpenlrLRC.LINEARLOCATIONREFERENCE,OpenlrLRC.LOCATIONDESCRIPTION }), length = -5)
//	private NumberField lengthComp;
//
//	/**
//	 * 1byte长度，版本号，固定为30
//	 */
//	@CoderItem(id = OpenlrLRC.VERSION, type = CoderItemType.ITEM, length = 1)
//	private NumberField version;
//
//	/**
//	 * 子Component
//	 */
//	@CoderItem(id = OpenlrLRC.LINEARLOCATIONREFERENCE, type = CoderItemType.ITEM, length = 0)
//	private LinearLocationReference linearLocationReference;
//	
//	/**
//	 * 子Component
//	 */
//	@CoderItem(id = OpenlrLRC.LOCATIONDESCRIPTION, type = CoderItemType.ITEM, length = 0)
//	private LocationDescription locationDescription;
//
//	public NumberField getLengthAttr() {
//		return lengthAttr;
//	}
//
//	public NumberField getLengthComp() {
//		return lengthComp;
//	}
//
//	public NumberField getVersion() {
//		return version;
//	}
//
//	public LinearLocationReference getLinearLocation() {
//		return linearLocationReference;
//	}
//
//	public void setLengthAttr(int lengthAttr) {
//		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
//		numberField.setNumber(lengthAttr);
//	}
//
//	public void setLengthComp(int lengthComp) {
//		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
//		numberField.setNumber(lengthComp);
//	}
//
//	public void setVersion(int version) {
//		NumberField numberField = (NumberField) this.newInstanceItem(OpenlrLRC.VERSION, false);
//		numberField.setNumber(version);
//	}
//
//	public void setLinearLocation(LinearLocationReference linearLocationReference) {
//		this.linearLocationReference = linearLocationReference;
//	}
//
//	@Override
//	public void reEncoding() {
//		// TODO Auto-generated method stub
////		super.reEncoding();
//	}
//
//}
