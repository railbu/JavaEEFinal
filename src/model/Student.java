package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student")
public class Student {
	
	private Long id;
	private String username;
	private String password;
	private String regTime;
	private String realName;
	private String NickName;
	private String stuNo;
	private String school;
	private String className;
	private String email;
	private String type;
	private byte[] photo;
	private String photoId;
	private int downLoadTime = 0;
	private int ev = 0;
	private String grade;
	private Set<PPT> ppts = new HashSet<PPT>();
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDownLoadTime() {
		return downLoadTime;
	}
	public void setDownLoadTime(int downLoadTime) {
		this.downLoadTime = downLoadTime;
	}
	public int getEv() {
		return ev;
	}
	public void setEv(int ev) {
		this.ev = ev;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="student_ppt",
		joinColumns=@JoinColumn(name="student_id"),
		inverseJoinColumns=@JoinColumn(name="ppt_id"))
	@OrderBy("id asc")
	public Set<PPT> getPpts() {
		return ppts;
	}

	public void setPpts(Set<PPT> ppts) {
		this.ppts = ppts;
	}
	
	public void addPpt(PPT ppt) {
		this.ppts.add(ppt);
	}
	
	
}
