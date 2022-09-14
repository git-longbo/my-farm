package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * �����е�����
 * @author 86173
 *
 */
@Entity
@Table(name="T_GroundCrop")
public class GroundCrop  extends EntityID{
	private static final long serialVersionUID = 1L;
	private long uId;//�û�id
    private int groundId;//����id
    private int groundStatus;//����״̬��0��ʾδ����
    private int sId;//����id
    private int sStatus;//����״̬
    private int nowSeason;//��ǰ����
    private int hasInsect;//�Ƿ��г棬0û�У�1��
    private long plantTime;//ֲ�ﲥ����ʼʱ��
    private int trueHarvestNum;//�ջ�����
	
	public long getuId() {
		return uId;
	}
	public void setuId(long uId) {
		this.uId = uId;
	}
	public int getGroundId() {
		return groundId;
	}
	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}
	public int getGroundStatus() {
		return groundStatus;
	}
	public void setGroundStatus(int groundStatus) {
		this.groundStatus = groundStatus;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public int getsStatus() {
		return sStatus;
	}
	public void setsStatus(int sStatus) {
		this.sStatus = sStatus;
	}
	
	public int getNowSeason() {
		return nowSeason;
	}
	public void setNowSeason(int nowSeason) {
		this.nowSeason = nowSeason;
	}
	public int getHasInsect() {
		return hasInsect;
	}
	public void setHasInsect(int hasInsect) {
		this.hasInsect = hasInsect;
	}
	public long getPlantTime() {
		return plantTime;
	}
	public void setPlantTime(long plantTime) {
		this.plantTime = plantTime;
	}
	public int getTrueHarvestNum() {
		return trueHarvestNum;
	}
	public void setTrueHarvestNum(int trueHarvestNum) {
		this.trueHarvestNum = trueHarvestNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    

}
