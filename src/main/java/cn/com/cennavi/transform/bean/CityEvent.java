package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CityEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5448203120756251185L;
	/**
	 * Map<(((lpid*100+ltn)*10+dir)*100+maincause)*100+subcause,TrafficEventInfo>
	 * 增加默认值
	 */
	private Map<Long, TrafficEventInfo> events=new HashMap<Long, TrafficEventInfo>();
	
	public Map<Long, TrafficEventInfo> getEvents() {
		return events;
	}

	public void setEvents(Map<Long, TrafficEventInfo> events) {
		this.events = events;
	}
}
