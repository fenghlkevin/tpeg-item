package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 道路数据
 * @author Kevin Feng
 *
 */
public class Road implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5823439286900772395L;
	
	private boolean isLoopRoad=false;
	
	/**
	 * 道路方向
	 */
	private int direction;

	/**
	 * TMC
	 */
	private List<LPInfo> tmcs;
	
	/**
	 * tmc交通流数据,tfp
	 */
	private List<TrafficFlowInfo> tmcflows;
	
	/**
	 * 该道路的所有事件
	 */
//	private CityEvent events;
	
	/**
	 * 城市码,tfp
	 */
	private int lpLocationTableNumber;
	
	/**
	 * MessageID,tfp 一条路一个方向ID
	 */
	private int messageID;
	
	/**
	 * 数据版本,tfp
	 */
	private int versionID;
	
	/**
	 * 数据起始时间,tfp
	 */
	private Timestamp startTime;
	
	/**
	 * 数据预计结束时间,tfp
	 */
	private Timestamp expiryTime;
	
	/**
	 * ,tfp
	 */
	private Timestamp generationTime;
	
	public List<LPInfo> getTmcs() {
		return tmcs;
	}

	public void setTmcs(List<LPInfo> tmcs) {
		this.tmcs = tmcs;
	}

	public boolean hasTmcflow() {
		return tmcflows!=null&&!tmcflows.isEmpty();
	}

	public int getLpLocationTableNumber() {
		return lpLocationTableNumber;
	}

	public void setLpLocationTableNumber(int lpLocationTableNumber) {
		this.lpLocationTableNumber = lpLocationTableNumber;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public List<TrafficFlowInfo> getTmcflows() {
		return tmcflows;
	}

	public void setTmcflows(List<TrafficFlowInfo> tmcflows) {
		this.tmcflows = tmcflows;
	}

	public int getVersionID() {
		return versionID;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getExpiryTime() {
		return expiryTime;
	}

	public Timestamp getGenerationTime() {
		return generationTime;
	}

	public void setVersionID(int versionID) {
		this.versionID = versionID;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public void setExpiryTime(Timestamp expiryTime) {
		this.expiryTime = expiryTime;
	}

	public void setGenerationTime(Timestamp generationTime) {
		this.generationTime = generationTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

//	public CityEvent getEvents() {
//		return events;
//	}
//
//	public void setEvents(CityEvent events) {
//		this.events = events;
//	}

	public boolean isLoopRoad() {
		return isLoopRoad;
	}

	public void setLoopRoad(boolean isLoopRoad) {
		this.isLoopRoad = isLoopRoad;
	}
}
