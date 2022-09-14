package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * �������
 * @author 86173
 *
 */
@Entity
@Table(name="T_Ground")

public class Ground extends EntityID {
	/**
	 * 土地类型编号
	 */
	private static final long serialVersionUID = 1L;
	//�������ͱ��
	private int code;
	//������������
	private String groundName;

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
