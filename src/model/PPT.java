package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="PPT")
public class PPT {
	
	private Long id;
	private String name;
	private String nameInDisk;
	private String pdfIndisk;
	private String creTime;
	private Course course;
	private String Type;
	private String position;
	private int clickCount;
	private int price;
	private String firPicPosition;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "course_id")
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
	
	public String getNameInDisk() {
		return nameInDisk;
	}
	public void setNameInDisk(String nameInDisk) {
		this.nameInDisk = nameInDisk;
	}
	
	@Override
	public boolean equals(Object ppt) {
		if(ppt.getClass()!=this.getClass())
			return false;
		PPT p = (PPT)ppt;
		if(p.getCreTime().equals(this.getCreTime()) && p.getName().equals(this.getName()))
			return true;
		return false;
	}
	public String getPdfIndisk() {
		return pdfIndisk;
	}
	public void setPdfIndisk(String pdfIndisk) {
		this.pdfIndisk = pdfIndisk;
	}
	public String getFirPicPosition() {
		return firPicPosition;
	}
	public void setFirPicPosition(String firPicPosition) {
		this.firPicPosition = firPicPosition;
	}
	
	
}
