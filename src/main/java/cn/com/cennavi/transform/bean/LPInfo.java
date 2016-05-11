package cn.com.cennavi.transform.bean;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class LPInfo extends Rectangle2D.Double implements Serializable {
	/**
	 */
	private static final long serialVersionUID = -595982732953530780L;
	
	public LPInfo(){}
	
	/**
	 * 墨卡托投影坐标
	 * @param x
	 * @param y
	 */
	public LPInfo(double x,double y){
		this.setRect(x, y, 0, 0);
	}
	
	private long id;
	private int locId;
	private double xp;
	private double yp;
	private int prevPt;
	private int nextPt;
	/**
	 * 正方向，到下一个位置点的距离
	 */
	private int posLen;
	/**
	 * 负方向，到下一个位置点的距离
	 */
	private int negLen;
	
	/**
	 * 正方向限速值
	 */
	private int posSpeed;
	/**
	 * 负方向限速值
	 */
	private int negSpeed;
	
	private int posFrc;
	private int negFrc;
	private int groupID;
	private int lpCountryCode;
	private int  lpExtendedCountryCode;
	private int lpLocationTableNumber;
	private String  lpLocationTableVersion;
	private String cityname;
	private int admincode;
	
//	public int createMessageID(){
//		
//	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	
	public int getPrevPt() {
		return prevPt;
	}
	public void setPrevPt(int prevPt) {
		this.prevPt = prevPt;
	}
	public int getNextPt() {
		return nextPt;
	}
	public void setNextPt(int nextPt) {
		this.nextPt = nextPt;
	}
	public int getPosFrc() {
		return posFrc;
	}
	public void setPosFrc(int posFrc) {
		this.posFrc = posFrc;
	}
	public int getNegFrc() {
		return negFrc;
	}
	public void setNegFrc(int negFrc) {
		this.negFrc = negFrc;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
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
	public int getPosLen() {
		return posLen;
	}
	public int getNegLen() {
		return negLen;
	}
	public void setPosLen(int posLen) {
		this.posLen = posLen;
	}
	public void setNegLen(int negLen) {
		this.negLen = negLen;
	}
    public int getPosSpeed() {
        return posSpeed;
    }
    public void setPosSpeed(int posSpeed) {
        this.posSpeed = posSpeed;
    }
    public int getNegSpeed() {
        return negSpeed;
    }
    public void setNegSpeed(int negSpeed) {
        this.negSpeed = negSpeed;
    }

	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public double getYp() {
		return yp;
	}

	public void setYp(double yp) {
		this.yp = yp;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public int getAdmincode() {
		return admincode;
	}

	public void setAdmincode(int admincode) {
		this.admincode = admincode;
	}
    
//    @Override
//	public boolean equals(Object obj) {
//		if(obj==this){
//			return true;
//		}
//		if(obj instanceof LPInfo){
//			LPInfo out=(LPInfo)obj;
//			if(this.locId==out.getLocId()&&this.lpLocationTableVersion.equalsIgnoreCase(out.getLpLocationTableVersion())&&this.lpLocationTableNumber==out.getLpLocationTableNumber()){
//				return true;
//			}
//		}
//		return false;
//	}
//    
//    int hashcode=-1;
//	@Override
//	public int hashCode() {
//		if(hashcode==-1){
//			long bits = this.getLocId()*1000;
//	        bits += this.getLpLocationTableNumber() ;
//	        bits += this.getLpLocationTableVersion().hashCode() * 47;
//	        hashcode= (((int) bits) ^ ((int) (bits >> 32)));
//		}
//		return hashcode;
//		 
//	}
    
}
