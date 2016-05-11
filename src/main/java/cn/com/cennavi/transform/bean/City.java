package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市对象
 * @author Kevin Feng
 *
 */
public class City implements Serializable{
	
	/**
	 * 当前数据版本时间戳
	 */
	private Timestamp dataTimestamp;
	
	/**
	 * 城市编码 
	 */
	private int locationTableNumber;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5959854853988867154L;
	/**
	 * 城市内所有的tmc道路:MessageID,Road
	 */
	private Map<Integer, Road> cityTMCRoad=new HashMap<Integer, Road>();
	
	private CityEvent events;
	/**
	 * 城市内所有道路的GroupID:LOCID+LpLocationTableNumber,groupid
	 */
	private Map<Integer,Integer> cityGroupIDs=new HashMap<Integer, Integer>();
	
	/**
	 * 当前有交通流的道路MessageID、或当前有事件的道路MessageID
	 */
	private List<Integer> currutCityMessageid=new ArrayList<Integer>();
	
	public Map<Integer, Road> getCityTMCRoad() {
		return cityTMCRoad;
	}
	public Map<Integer, Integer> getCityGroupIDs() {
		return cityGroupIDs;
	}
	public void setCityTMCRoad(Map<Integer, Road> cityTMCRoad) {
		this.cityTMCRoad = cityTMCRoad;
	}
	public void setCityGroupIDs(Map<Integer, Integer> cityGroupIDs) {
		this.cityGroupIDs = cityGroupIDs;
	}
	public List<Integer> getCurrutCityMessageid() {
		return currutCityMessageid;
	}
	public void setCurrutCityMessageid(List<Integer> currutCityMessageid) {
		this.currutCityMessageid = currutCityMessageid;
	}
	public Timestamp getDataTimestamp() {
		return dataTimestamp;
	}
	public void setDataTimestamp(Timestamp dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}
	public CityEvent getEvents() {
		return events;
	}
	public void setEvents(CityEvent events) {
		this.events = events;
	}
	public int getLocationTableNumber() {
		return locationTableNumber;
	}
	public void setLocationTableNumber(int locationTableNumber) {
		this.locationTableNumber = locationTableNumber;
	}
}
