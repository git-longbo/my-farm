package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * ���ӳɳ��׶�
 * @author 86173
 *
 */
@Entity
@Table(name="T_SeedState")
public class SeedState extends EntityID  {

	/**
	 * 植物每个生长阶段
	 */
	private static final long serialVersionUID = 1L;
	//����Id
	private int seedId;
	//�����׶�
	private int growState;
	//�����׶α���
	private String title;
	//�׶�����ʱ��
	private int stateTime;
	//�������
	private float pest;
	//ͼƬ���
	private int imgWidth;	
	//ͼƬ�߶�
	private int imgHeight;
	//ͼƬλ��X
	private int offsetX;
	//ͼƬλ��Y
	private int offsetY;
	//�ɳ��׶�
	private int state;
	public int getSeedId() {
		return seedId;
	}
	public void setSeedId(int seedId) {
		this.seedId = seedId;
	}
	public int getGrowState() {
		return growState;
	}
	public void setGrowState(int growState) {
		this.growState = growState;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStateTime() {
		return stateTime;
	}
	public void setStateTime(int stateTime) {
		this.stateTime = stateTime;
	}
	public float getPest() {
		return pest;
	}
	public void setPest(float pest) {
		this.pest = pest;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public int getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	public int getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
