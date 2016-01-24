package model;

public class PPTAn {
	
	private String name;
	private String pdfIndisk;
	private String creTime;
	private Course course;

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

	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getPdfIndisk() {
		return pdfIndisk;
	}
	public void setPdfIndisk(String pdfIndisk) {
		this.pdfIndisk = pdfIndisk;
	}
	
}
