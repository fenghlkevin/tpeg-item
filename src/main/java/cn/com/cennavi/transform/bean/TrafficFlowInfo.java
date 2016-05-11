package cn.com.cennavi.transform.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 交通流数据对象
 * @author Kevin Feng
 *
 */
public class TrafficFlowInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6890304759034012611L;
	
	/**
	 * 信息开始时间（当前信息的时间戳）
	 */
	private Timestamp startTime;
	
	/**
	 * 时间偏移，预测数据使用，表示与startTime间隔的时间
	 */
	private int timeOffset;
	
	/**
	 * 位置点id，primary location pointlpId
	 */
	private int lpID;
	
	/**
	 * 国家代码
	 */
	private int lpCountryCode;
	
	/**
	 * 扩展国家代码
	 */
	private int  lpExtendedCountryCode;
	
	/**
	 * 位置表编号
	 */
	private int lpLocationTableNumber;
	
	/**
	 * 位置表版本号
	 */
	private String  lpLocationTableVersion;
	
	/**
	 * 位置点方向
	 */
	private int lpDirection;
	
	/**
	 * 位置点偏移量
	 */
	private int lpExtent;
	
	/**
	 * 该位置点是否为真实数据，默认是真实的
	 */
	private boolean realFlow=true;
	
	/**
	 * 根据不同路况将TMC位置点划分的路段数，如果设置为不提供精细TMC信息，则该路段数为1；
	 */
	private int sectionNumber;
	
	private List<SectionInfo> sections=new ArrayList<SectionInfo>();
	
	
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public int getTimeOffset() {
		return timeOffset;
	}
	public void setTimeOffset(int timeOffset) {
		this.timeOffset = timeOffset;
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
	
	/**
	 * 分段交通流数据
	 * @author Kevin Feng
	 *
	 */
	public static class SectionInfo implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2944314985170586548L;
		/**
		 * Section编号
		 */
		private int sectionSeq;
		/**
		 * 平均速度
		 */
		private int averageSpeed;
		
		/**
		 * 服务等级
		 */
		private int los;
		
		/**
		 * 延迟时间
		 */
		private int delays;
		
		/**
		 * 路段开始位置到该位置点的距离
		 */
		private int startDistance;
		
		/**
		 * 路段长度
		 */
		private int sectionLength;

		public int getSectionSeq() {
			return sectionSeq;
		}

		public int getAverageSpeed() {
			return averageSpeed;
		}

		public int getLos() {
			return los;
		}

		public int getDelays() {
			return delays;
		}

		public int getStartDistance() {
			return startDistance;
		}

		public int getSectionLength() {
			return sectionLength;
		}

		public void setSectionSeq(int sectionSeq) {
			this.sectionSeq = sectionSeq;
		}

		public void setAverageSpeed(int averageSpeed) {
			this.averageSpeed = averageSpeed;
		}

		public void setLos(int los) {
			this.los = los;
		}

		public void setDelays(int delays) {
			this.delays = delays;
		}

		public void setStartDistance(int startDistance) {
			this.startDistance = startDistance;
		}

		public void setSectionLength(int sectionLength) {
			this.sectionLength = sectionLength;
		}
	}

	public int getSectionNumber() {
		return sectionNumber;
	}
	public List<SectionInfo> getSections() {
		return sections;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public void setSections(List<SectionInfo> sections) {
		this.sections = sections;
	}
	
	public void addSections(SectionInfo section) {
		this.sections.add(section);
	}
    public boolean isRealFlow() {
        return realFlow;
    }
    public void setRealFlow(boolean realFlow) {
        this.realFlow = realFlow;
    }

}
