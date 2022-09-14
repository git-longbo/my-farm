package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * 土地类别
 * @author 86173
 *
 */
@Entity
@Table(name="T_Ground")

public class Ground extends EntityID {
	/**
	 * 鍦熷湴绫诲瀷缂栧彿
	 */
	private static final long serialVersionUID = 1L;
	//土地类型编号
	private int code;
	//土地类型名称
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
