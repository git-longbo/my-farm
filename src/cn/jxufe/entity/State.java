package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import cn.jxufe.bean.EntityID;
/*
 * 
 * ���������׶α���
 */
@Entity
@Table(name="T_State")
public class State extends EntityID {

	/**
	 * ���ӳ���׶Σ������ӽ׶Ρ������׶Ρ�����׶Ρ��ݲ���
	 */
	private static final long serialVersionUID = 1L;
	//���������׶α��
	private int code;
	//���������׶�����
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
