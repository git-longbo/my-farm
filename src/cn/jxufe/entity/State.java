package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import cn.jxufe.bean.EntityID;
/*
 * 
 * 作物生长阶段标题
 */
@Entity
@Table(name="T_State")
public class State extends EntityID {

	/**
	 * 种子成熟阶段：如种子阶段、生长阶段、成熟阶段、枯草期
	 */
	private static final long serialVersionUID = 1L;
	//作物生长阶段编号
	private int code;
	//作物生长阶段名称
	private String state;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
