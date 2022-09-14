package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * ������Ϣ
 * @author 86173
 *
 */
@Entity
@Table(name="T_Seed")
public class Seed extends EntityID {
	
	private static final long serialVersionUID = 1L;
	
	//����Id
	private int seedId;
	//��������
	private String seedName;
	//��������
	private int seasons;
	//���ӵȼ�
	private int seedLevel;
	//��������
	private int seedType;
	//�ɻ�þ���
	private int experience;
	//����ʱ��
	private int growTime;
	//�ջ�ɻ�ù�ʵ����
	private int receiveNum;
	//����۸�
	private int price;
	//ÿ����ʵ�����ɻ�ý��
	private int eachPrice;
	//������������
	private int ground;
	//�ɻ�û���
	private int credit;
	//������Ϣ
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
