package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 交通流信息
 * @author Kevin Feng
 *
 */
public class TrafficMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6100195254982429187L;
	private Long messageID;
	private int versionID;
	/**
	 * 数据起始时间
	 */
	private Timestamp startTime;
	
	/**
	 * 数据预计结束时间
	 */
	private Timestamp expiryTime;
	
	/**
	 * 
	 */
	private Timestamp generationTime;
	
	// 道路的有序位置点列表
	private List<LPInfo>  sortedLpInfoList;
	// 位置点的当前交通流信息表(key为位置点ID)
	private Map<Integer,TrafficFlowInfo> currentTrafficFlowInfoMap;
	// 位置点的预测交通流信息表(key为位置点ID)
	private Map<Integer,List<TrafficFlowInfo>>  predictiveTrafficFlowInfoMap;
	
    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public List<LPInfo> getSortedLpInfoList() {
		return sortedLpInfoList;
	}

	public void setSortedLpInfoList(List<LPInfo> sortedLpInfoList) {
		this.sortedLpInfoList = sortedLpInfoList;
	}

	public Map<Integer, TrafficFlowInfo> getCurrentTrafficFlowInfoMap() {
		return currentTrafficFlowInfoMap;
	}

	public void setCurrentTrafficFlowInfoMap(Map<Integer, TrafficFlowInfo> currentTrafficFlowInfoMap) {
		this.currentTrafficFlowInfoMap = currentTrafficFlowInfoMap;
	}
	public Map<Integer, List<TrafficFlowInfo>> getPredictiveTrafficFlowInfoMap() {
		return predictiveTrafficFlowInfoMap;
	}

	public void setPredictiveTrafficFlowInfoMap(Map<Integer, List<TrafficFlowInfo>> predictiveTrafficFlowInfoMap) {
		this.predictiveTrafficFlowInfoMap = predictiveTrafficFlowInfoMap;
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
}
