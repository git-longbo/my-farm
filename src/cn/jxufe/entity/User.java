package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import cn.jxufe.bean.EntityID;
/*
 * �û���Ϣ
 */
@Entity
@Table(name="T_User")
public class User extends EntityID{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�û���
	private String username;
	//�ǳ�
	private String nicheng;
	//����ֵ
	private int exp;
	//����
	private int points;
	//���
	private int coins;
	//ͷ��
	private String headImg;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNicheng() {
		return nicheng;
	}
	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
