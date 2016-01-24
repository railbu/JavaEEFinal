package action.student;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import service.PPTService;
import service.StudentService;
import service.TSMessageService;
import model.PPT;
import model.Student;
import model.TSMessage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class HomeAction extends ActionSupport implements Preparable{
	
	private static final long serialVersionUID = -6264481743537378514L;
	private int[] tipsInt = {0,0,0,0,0,0};
//	private String[] tips = {"","","","","",""};
	private List<TSMessage> mlist = null;
	private String id;
	private String stuNo;
	private String email;
	private String school;
	private String sName;
	private String photoId;
	private String code;
	private String[] pos = new String[3];
	private String[] des = new String[3];
	
	public String input() {
		return INPUT;
	}
	
	public String stuhome(){
		List<PPT> pptlist = PPTService.findAllPPTS();
		for(int i=0;i<pptlist.size();i++){
			getPos()[i]=pptlist.get(i).getFirPicPosition();
			getDes()[i]=pptlist.get(i).getName()+"\n"+pptlist.get(i).getCreTime();
		}
		
		stuNo = (StudentService.findStudent(sName)).getStuNo();
		mlist = TSMessageService.findAll(stuNo);
		for(int i=0;i<mlist.size();i++){
			TSMessage m = mlist.get(i);
			tipsInt[m.getType()]++;
		}
		for(int i=0;i<6;i++)
			if(tipsInt[i] > 0){
				ActionContext.getContext().getSession().put("tips"+String.valueOf(i),tipsInt[i]);
				//ServletActionContext.getRequest().getSession().setAttribute("tips"+String.valueOf(i),tipsInt[i]);
			}
		
		return "home";
	}
	
	public String modify() {
		String code = (String) ActionContext.getContext().getSession().get("code");
		
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();
		
		if (!this.code.equals(code)) {
			super.addFieldError("code", "验证码输入错误!");
			return this.input();
		}
		
		Student student = StudentService.findStudent((String)ActionContext.getContext().getSession().get("currentUser"));
		student.setEmail(email);
		student.setPhotoId(photoId);
		student.setStuNo(stuNo);
		student.setSchool(school);
		ServletActionContext.getRequest().getSession().setAttribute("student",student);
		StudentService.save(student);
		
		return this.stuhome();
	}
	
	@Override
	
	public void prepare() throws Exception {
		sName = ((String) ActionContext.getContext().getSession().get("stuUser"));
	}



	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public List<TSMessage> getMlist() {
		return mlist;
	}

	public void setMlist(List<TSMessage> mlist) {
		this.mlist = mlist;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getPos() {
		return pos;
	}

	public void setPos(String[] pos) {
		this.pos = pos;
	}

	public String[] getDes() {
		return des;
	}

	public void setDes(String[] des) {
		this.des = des;
	}
	

//	public String[] getTips() {
//		return tips;
//	}
//
//	public void setTips(String[] tips) {
//		this.tips = tips;
//	}


}
