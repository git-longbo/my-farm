package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * ���Ӳֿ�
 * @author 86173
 *
 */
@Entity
@Table(name="T_SeedBag")
public class SeedBag extends EntityID {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�û�Id
	private String username;
	//����Id
	private int seedId;
	//��������
	private int num;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSeedId() {
		return seedId;
	}
	public void setSeedId(int seedId) {
		this.seedId = seedId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
