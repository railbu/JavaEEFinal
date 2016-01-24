package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stuppt")
public class StuPpt {
	private Long id;
	private String sname;
	private String url;
	private String pptname;
	private String tname;
	private String nameindisk;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPptname() {
		return pptname;
	}
	public void setPptname(String pptname) {
		this.pptname = pptname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNameindisk() {
		return nameindisk;
	}
	public void setNameindisk(String nameindisk) {
		this.nameindisk = nameindisk;
	}

}
