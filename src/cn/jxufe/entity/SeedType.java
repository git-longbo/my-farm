package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/*
 * 
 * 种子类别
 */
@Entity
@Table(name="T_SeedType")
public class SeedType extends EntityID {
	/**
	 * 绉嶅瓙绫诲瀷缂栧彿
	 */
	private static final long serialVersionUID = 1L;
	//种子类别编号
	private int code;
	//类别名称
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
