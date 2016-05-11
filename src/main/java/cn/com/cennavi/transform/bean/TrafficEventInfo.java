package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 事件对象
 * 
 * @author Kevin Feng
 * 
 */
public class TrafficEventInfo implements Serializable {

    private int eventVersionId;

    private Timestamp eventGenerationTime;

    private Timestamp eventExpiryTime;

    private Timestamp eventStartTime;

    public static final int STATUS_ADD = 1;

    public static final int STATUS_EDIT = 2;

    public static final int STATUS_EDIT_21 = 21;

    public static final int STATUS_CONSTANT = 3;

    public static final int STATUS_DEL = 4;

    /**
     * 1:新增（发布），2：修改（发布）,21:修改（lplist有变更，同时更新lp2tec表），3：不变（不发布），4：无更新（删除）（旧数据中存在新数据中不存在的messageID）
     */
    private int status = 1;

    /**
     * 
     */
    private static final long serialVersionUID = 5624171122378245496L;

    private long id;

    /**
     * 当前信息时间戳
     */
    private Timestamp timestamp;

    /**
     * 事件开始时间
     */
    private Timestamp startTime;

    /**
     * 事件结束时间
     */
    private Timestamp stopTime;

    /**
     * 对交通流的影响代码
     */
    private int effectCode;

    /**
     * 主要原因
     */
    private int mainCause;

    /**
     * 次要原因
     */
    private int subCause;

    /**
     * 警告等级
     */
    private int warningLevel;

    /**
     * 影响长度
     */
    private int length;

    /**
     * 车道管制类型
     */
    private int laneRestrictionType;

    /**
     * 车道数量
     */
    private int laneNumber;

    /**
     * 位置点id，primary location point，即该事件方向的最后一个位置点
     */
    private int lpID;

    /**
     * 国家代码
     */
    private int lpCountryCode;

    /**
     * 扩展国家代码
     */
    private int lpExtendedCountryCode;

    /**
     * 位置表编号
     */
    private int lpLocationTableNumber;

    /**
     * 位置表版本号
     */
    private String lpLocationTableVersion;

    /**
     * 位置点方向
     */
    private int lpDirection;

    /**
     * 位置点偏移量
     */
    private int lpExtent;
    
    private int startLen;

    /**
     * 该条事件对应的全部位置点，以‘|’分隔
     */
    private Integer[] lpList;

    private int eventSource;

    public int getStartLen() {
		return startLen;
	}

	public void setStartLen(int startLen) {
		this.startLen = startLen;
	}

	public int getEventSource() {
        return eventSource;
    }

    public void setEventSource(int eventSource) {
        this.eventSource = eventSource;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getStopTime() {
        return stopTime;
    }

    public void setStopTime(Timestamp stopTime) {
        this.stopTime = stopTime;
    }

    public int getEffectCode() {
        return effectCode;
    }

    public void setEffectCode(int effectCode) {
        this.effectCode = effectCode;
    }

    public int getMainCause() {
        return mainCause;
    }

    public void setMainCause(int mainCause) {
        this.mainCause = mainCause;
    }

    public int getSubCause() {
        return subCause;
    }

    public void setSubCause(int subCause) {
        this.subCause = subCause;
    }

    public int getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(int warningLevel) {
        this.warningLevel = warningLevel;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLaneRestrictionType() {
        return laneRestrictionType;
    }

    public void setLaneRestrictionType(int laneRestrictionType) {
        this.laneRestrictionType = laneRestrictionType;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public int getLpID() {
        return lpID;
    }

    public void setLpID(int lpID) {
        this.lpID = lpID;
    }

    public int getLpCountryCode() {
        return lpCountryCode;
    }

    public void setLpCountryCode(int lpCountryCode) {
        this.lpCountryCode = lpCountryCode;
    }

    public int getLpExtendedCountryCode() {
        return lpExtendedCountryCode;
    }

    public void setLpExtendedCountryCode(int lpExtendedCountryCode) {
        this.lpExtendedCountryCode = lpExtendedCountryCode;
    }

    public int getLpLocationTableNumber() {
        return lpLocationTableNumber;
    }

    public void setLpLocationTableNumber(int lpLocationTableNumber) {
        this.lpLocationTableNumber = lpLocationTableNumber;
    }

    public String getLpLocationTableVersion() {
        return lpLocationTableVersion;
    }

    public void setLpLocationTableVersion(String lpLocationTableVersion) {
        this.lpLocationTableVersion = lpLocationTableVersion;
    }

    public int getLpDirection() {
        return lpDirection;
    }

    public void setLpDirection(int lpDirection) {
        this.lpDirection = lpDirection;
    }

    public int getLpExtent() {
        return lpExtent;
    }

    public void setLpExtent(int lpExtent) {
        this.lpExtent = lpExtent;
    }

    public Integer[] getLpList() {
        return lpList;
    }

    public void setLpList(Integer[] lpList) {
        this.lpList = lpList;
    }

    public int getEventVersionId() {
        return eventVersionId;
    }

    public Timestamp getEventGenerationTime() {
        return eventGenerationTime;
    }

    public Timestamp getEventExpiryTime() {
        return eventExpiryTime;
    }

    public Timestamp getEventStartTime() {
        return eventStartTime;
    }

    public void setEventVersionId(int eventVersionId) {
        this.eventVersionId = eventVersionId;
    }

    public void setEventGenerationTime(Timestamp eventGenerationTime) {
        this.eventGenerationTime = eventGenerationTime;
    }

    public void setEventExpiryTime(Timestamp eventExpiryTime) {
        this.eventExpiryTime = eventExpiryTime;
    }

    public void setEventStartTime(Timestamp eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}