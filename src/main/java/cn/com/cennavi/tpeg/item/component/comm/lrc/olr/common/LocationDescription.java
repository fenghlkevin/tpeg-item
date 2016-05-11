package cn.com.cennavi.tpeg.item.component.comm.lrc.olr.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderItem.Dependent;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.NumberField;
import cn.com.cennavi.codec.util.ReflectUtil;
import cn.com.cennavi.tpeg.item.component.BaseComponent;
import cn.com.cennavi.tpeg.item.component.tec.LocalisedLongString;

/**
 * 支持动态位置参考点的 LRC对象
 * 
 * @author Administrator
 * 
 */
@CoderLinks(compileOrder = @EncoderBuildOrder(order = { BaseComponent.ID, LocationDescription.N,LocationDescription.DESCRIPTION, LocationDescription.LENGTHATTR, LocationDescription.LENGTHCOMP }), buildOrder = @EncoderBuildOrder(order = {
		BaseComponent.ID, BaseComponent.LENGTHCOMP, BaseComponent.LENGTHATTR,LocationDescription.N, LocationDescription.DESCRIPTION}))
public class LocationDescription extends BaseComponent {

	private static final long serialVersionUID = -919225101980803767L;

	public static final String N = "n";
	
	public static final String DESCRIPTION = "description";
	
	 {
	        this.setId(11);
	        this.setLengthComp(0);
	        this.setLengthAttr(0);
	    }

	/**
	 * 1~5bytes 变长字节 ，该字段后所有字节
	 */
	@CoderItem(id = BaseComponent.LENGTHATTR, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { LocationDescription.N,LocationDescription.DESCRIPTION}), length = -5)
	private NumberField lengthAttr;
	/**
	 * 1~5bytes变长 
	 */
	@CoderItem(id = BaseComponent.LENGTHCOMP, type = CoderItemType.LENGTH, dependent = @Dependent(dependentField = { BaseComponent.LENGTHATTR,LocationDescription.N,LocationDescription.DESCRIPTION}), length = -5)
	private NumberField lengthComp;

	/**
	 * 1byte长度
	 */
	@CoderItem(id = LocationDescription.DESCRIPTION, type = CoderItemType.LIST, length = CoderItem.ITEMLENGTH_NOMAXLENGTH)
	private List<LocalisedLongString> description;

	@CoderItem(id=LocationDescription.N, type = CoderItemType.ITEM, length = -5)
    private NumberField n;
	
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

    public List<LocalisedLongString> getDescription() {
        return description;
    }

    public void setDescription(List<LocalisedLongString> description) {
        NumberField numberField = (NumberField) this.newInstanceItem(LocationDescription.N, false);
        numberField.setNumber(description.size());
        this.description = description;
    }
    
    @Override
	public void decoding() {
    	//Field id=ReflectUtil.getClassField(BaseComponent.ID,this.getClass());
    	//super.decoding(null,id);
    	super.decoding(null, ReflectUtil.getClassField(LocationDescription.N, this.getClass()));
    	int count=this.n.getNumber();
    	if(this.description==null){
			this.description=new ArrayList<LocalisedLongString>(count);
		}
    	for(int i = 0;i < count; i++){
    		LocalisedLongString c = new LocalisedLongString();
    		description.add(c);
    		c.setEncodedStream(this.getEncodedByteStream());
    		c.decoding();
    	}
    	
    	Field descs = ReflectUtil.getClassField(LocationDescription.DESCRIPTION,this.getClass());
		super.decoding(descs, null);
    }

}
