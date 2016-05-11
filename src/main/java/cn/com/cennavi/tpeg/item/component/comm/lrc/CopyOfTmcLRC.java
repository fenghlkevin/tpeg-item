//package cn.com.cennavi.tpeg.item.component.comm.lrc;
//
//import cn.com.cennavi.codec.core.annotation.CoderItem;
//import cn.com.cennavi.codec.core.annotation.CoderLinks;
//import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
//import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
//import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
//import cn.com.cennavi.codec.core.base.NumberField;
//import cn.com.cennavi.tpeg.item.component.BaseComponent;
//import cn.com.cennavi.tpeg.item.component.comm.lrc.tmc.TMCLocationReference;
//
///**
// * Location Reference 位置参考，与交通信息匹配
// * 
// * @author 冯贺亮
// * @version 1.0
// * @since 2011-03-09 00:50:00
// */
//@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, CopyOfTmcLRC.TMCLOCATIONREFERENCE, BaseComponent.LENGTHATTR, BaseComponent.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
//		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR, CopyOfTmcLRC.TMCLOCATIONREFERENCE }))
//public class CopyOfTmcLRC extends LRC {
//
//	private static final long serialVersionUID = -8581553647362995131L;
//
//	public static final String TMCLOCATIONREFERENCE = "tmcLocationReference";
//
//	@CoderItem(id = CopyOfTmcLRC.TMCLOCATIONREFERENCE, type = CoderItemType.ITEM, length = 0)
//	private TMCLocationReference tmcLocationReference;
//
//	/**
//	 * 1~5bytes 变长字节 ，该字段后所有字节
//	 */
//	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.ITEM, length = -5)
//	private NumberField lengthAttr;
//	/**
//	 * 1~5bytes变长
//	 */
//	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR, CopyOfTmcLRC.TMCLOCATIONREFERENCE }), length = -5)
//	private NumberField lengthComp;
//
//	public TMCLocationReference getTmcLocationReference() {
//		return tmcLocationReference;
//	}
//
//	public void setTmcLocationReference(TMCLocationReference tmcLocationReference) {
//		this.tmcLocationReference = tmcLocationReference;
//	}
//
//	public NumberField getLengthAttr() {
//		return lengthAttr;
//	}
//
//	public void setLengthAttr(int lengthAttr) {
//		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHATTR, false);
//		numberField.setNumber(lengthAttr);
//	}
//
//	public NumberField getLengthComp() {
//		return lengthComp;
//	}
//
//	public void setLengthComp(int lengthComp) {
//		NumberField numberField = (NumberField) this.newInstanceItem(BaseComponent.LENGTHCOMP, false);
//		numberField.setNumber(lengthComp);
//	}
//}
