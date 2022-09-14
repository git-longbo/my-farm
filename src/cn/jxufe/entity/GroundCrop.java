package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * 土地中的作物
 * @author 86173
 *
 */
@Entity
@Table(name="T_GroundCrop")
public class GroundCrop  extends EntityID{
	private static final long serialVersionUID = 1L;
	private long uId;//用户id
    private int groundId;//土地id
    private int groundStatus;//土地状态，0表示未开垦
    private int sId;//种子id
    private int sStatus;//种子状态
    private int nowSeason;//当前季数
    private int hasInsect;//是否有虫，0没有，1有
    private long plantTime;//植物播种起始时间
    private int trueHarvestNum;//收货数量
	
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
