package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	private Long id;
	private String name;
	private Date CreateTime;
	private String creTime;
	private String TName;
	private String week;
	private String no;
	private String snumber;
	private int stuCount;
	private int pptCount;
	private int hworkCount;
	
	public Course(){
		CreateTime = new Date();
		
		stuCount = 0;
		pptCount = 0;
		hworkCount = 0;
	}
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public int getStuCount() {
		return stuCount;
	}
	public void setStuCount(int stuCount) {
		this.stuCount = stuCount;
	}
	public int getPptCount() {
		return pptCount;
	}
	public void setPptCount(int pptCount) {
		this.pptCount = pptCount;
	}


	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public int getHworkCount() {
		return hworkCount;
	}

	public void setHworkCount(int hworkCount) {
		this.hworkCount = hworkCount;
	}
		
	public String getTName() {
		return TName;
	}

	public void setTName(String tName) {
		TName = tName;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
