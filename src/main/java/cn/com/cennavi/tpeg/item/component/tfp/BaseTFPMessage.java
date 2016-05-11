package cn.com.cennavi.tpeg.item.component.tfp;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.cennavi.tpeg.item.component.tec.TECMessage;

public class BaseTFPMessage implements Serializable {

	private static final long serialVersionUID = -2096986547225620991L;

	private Point2D.Double[] shape;

	private Integer ltn;

	private Integer frc;

	private TFPMessage tfpMsg;

	private TECMessage tecMsg;

	private TFPMessage freeFlowTFPMsg;

	private Timestamp timeStamp;

	private String cityCode;

	private String messagId;

	private Integer versionId;

	private String dataVersion;

	private String points;

	public TFPMessage getFreeFlowTFPMsg() {
		return freeFlowTFPMsg;
	}

	public void setFreeFlowTFPMsg(TFPMessage freeFlowTFPMsg) {
		this.freeFlowTFPMsg = freeFlowTFPMsg;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(String dataVersion) {
		this.dataVersion = dataVersion;
	}

	public String getMessagId() {
		return messagId;
	}

	public void setMessagId(String messagId) {
		this.messagId = messagId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public TECMessage getTecMsg() {
		return tecMsg;
	}

	public void setTecMsg(TECMessage tecMsg) {
		this.tecMsg = tecMsg;
	}

	public Integer getFrc() {
		return frc;
	}

	public void setFrc(Integer frc) {
		this.frc = frc;
	}

	public Integer getLtn() {
		return ltn;
	}

	public void setLtn(Integer ltn) {
		this.ltn = ltn;
	}

	public TFPMessage getTfpMsg() {
		return tfpMsg;
	}

	public void setTfpMsg(TFPMessage tfpMsg) {
		this.tfpMsg = tfpMsg;
	}

	public Point2D.Double[] getShape() {
		return shape;
	}

	public void setShape(Point2D.Double[] shape) {
		this.shape = shape;
	}
}
