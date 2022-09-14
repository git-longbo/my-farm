package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/*
 * 
 * �������
 */
@Entity
@Table(name="T_SeedType")
public class SeedType extends EntityID {
	/**
	 * 种子类型编号
	 */
	private static final long serialVersionUID = 1L;
	//���������
	private int code;
	//�������
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
