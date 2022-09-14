package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;
/**
 * 种子成长阶段
 * @author 86173
 *
 */
@Entity
@Table(name="T_SeedState")
public class SeedState extends EntityID  {

	/**
	 * 妞嶇墿姣忎釜鐢熼暱闃舵
	 */
	private static final long serialVersionUID = 1L;
	//种子Id
	private int seedId;
	//生长阶段
	private int growState;
	//生长阶段标题
	private String title;
	//阶段生长时间
	private int stateTime;
	//生虫概率
	private float pest;
	//图片宽度
	private int imgWidth;	
	//图片高度
	private int imgHeight;
	//图片位置X
	private int offsetX;
	//图片位置Y
	private int offsetY;
	//成长阶段
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
