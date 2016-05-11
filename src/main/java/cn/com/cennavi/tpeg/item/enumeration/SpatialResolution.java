package cn.com.cennavi.tpeg.item.enumeration;

/**
 * 偏移单位 TMC 米 等等
 * 
 * 000:TMCLocations:(Resolution of related offset value is in TMC locations
 * (extents); this type shall be used only if the LRC container is a TMC location
 * )
 * 
 * 001:10-meter-resolution (resolution of related offset is in 10-Meter steps as
 * absolute offsets to the spatial reference point, offset = value*10 [m] )
 * 
 * 002:50-meter-resolution (resolution of related offset is in 50-Meter steps as
 * absolute offsets to the spatial reference point, offset = value*50 [m])
 * 
 * 003:100m-resolution (resolution of related offset is in 100-Meter steps as
 * absolute offsets to the spatial reference point, offset = value*100 [m] )
 * 
 * 004:500m-resolution (resolution of related offset is in 500-Meter steps as
 * absolute offsets to the spatial reference point, offset = value*500 [m] )
 * 
 * 005:relative-10-meter-resolution(spatial offset is delivered in 10m steps
 * upstream to the begin of the following section; this value may be used only by
 * the spatialResolutionSection of the 'flowVectorSection' datastructure; it shall
 * not be used by other TFP attributes; offset = value*10 [m] )
 * 
 * 006:relative-100-meter-resolution(spatial offset is delivered in 100m steps
 * upstream to the begin of the following section; this value may be used only by
 * the spatialResolutionSection of the 'flowVectorSection' datastructure; it shall
 * not be used by other TFP attributes; offset = value*100 [m] )
 * @author 冯贺亮
 * @version 1.0
 * @since 2011-03-09 00:50:00
 */
public enum SpatialResolution {
	
	TMCLocations(0,0),AbsoluteL10M(1,10),AbsoluteL50M(2,50),AbsoluteL100M(3,100),AbsoluteL500M(4,500),RELATIVE10M(5,10),RELATIVE100M(6,100);
	
	private final int acode;
	
	private final int value;
	
	private SpatialResolution(int code,int value){
		this.acode=code;
		this.value=value;
	}

	public int toInt() {
		return acode;
	}
	
	public int getValue(){
		return value;
	}
	
	public static SpatialResolution getResolution(int code){
		if(code==0){
			return TMCLocations;
		}else if(code==1){
			return AbsoluteL10M;
		}else if(code==2){
			return AbsoluteL50M;
		}else if(code==3){
			return AbsoluteL100M;
		}else if(code==4){
			return AbsoluteL500M;
		}else if(code==5){
			return RELATIVE10M;
		}else if(code==6){
			return RELATIVE100M;
		}
		return TMCLocations;
	}
}

