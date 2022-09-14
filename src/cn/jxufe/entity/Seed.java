package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * 种子信息
 * @author 86173
 *
 */
@Entity
@Table(name="T_Seed")
public class Seed extends EntityID {
	
	private static final long serialVersionUID = 1L;
	
	//种子Id
	private int seedId;
	//种子名称
	private String seedName;
	//生长季度
	private int seasons;
	//种子等级
	private int seedLevel;
	//种子类型
	private int seedType;
	//可获得经验
	private int experience;
	//生长时间
	private int growTime;
	//收获可获得果实数量
	private int receiveNum;
	//购买价格
	private int price;
	//每个果实卖出可获得金币
	private int eachPrice;
	//所需土地类型
	private int ground;
	//可获得积分
	private int credit;
	//种子信息
	private String message;
	public int getSeedId() {
		return seedId;
	}
	public void setSeedId(int seedId) {
		this.seedId = seedId;
	}
	public String getSeedName() {
		return seedName;
	}
	public void setSeedName(String seedName) {
		this.seedName = seedName;
	}
	public int getSeasons() {
		return seasons;
	}
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	public int getSeedLevel() {
		return seedLevel;
	}
	public void setSeedLevel(int seedLevel) {
		this.seedLevel = seedLevel;
	}
	public int getSeedType() {
		return seedType;
	}
	public void setSeedType(int seedType) {
		this.seedType = seedType;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getGrowTime() {
		return growTime;
	}
	public void setGrowTime(int growTime) {
		this.growTime = growTime;
	}
	public int getReceiveNum() {
		return receiveNum;
	}
	public void setReceiveNum(int receiveNum) {
		this.receiveNum = receiveNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEachPrice() {
		return eachPrice;
	}
	public void setEachPrice(int eachPrice) {
		this.eachPrice = eachPrice;
	}
	public int getGround() {
		return ground;
	}
	public void setGround(int ground) {
		this.ground = ground;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	


}
