package action.teacher;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import service.TeacherService;
import model.TSMessage;
import model.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class HomeAction extends ActionSupport implements Preparable{
	
	private static final long serialVersionUID = -6264481743537378514L;
//	private String[] tips = {"","","","","",""};
	private List<TSMessage> mlist = null;
	private String id;
	private String email;
	private String school;
	private String sName;
	private String photoId;
	private String code;
	private String realName;
	
	public String input() {
		return INPUT;
	}
	
	public String teahome() {
		return "home";
	}
	
	public String edit() {
		String code = (String) ActionContext.getContext().getSession().get("code");
		
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();
		
		if (!this.code.equals(code)) {
			super.addFieldError("code", "验证码输入错误!");
			return this.input();
		}
		
		Teacher teacher = TeacherService.findTeacher((String)ActionContext.getContext().getSession().get("currentUser"));
		teacher.setEmail(email);
		teacher.setRealName(realName);
		teacher.setPhotoId(photoId);
		teacher.setSchool(school);
		ServletActionContext.getRequest().getSession().setAttribute("teacher",teacher);
		TeacherService.save(teacher);
		
		return "home";
	}
	
	@Override
	
	public void prepare() throws Exception {
		setsName(((String) ActionContext.getContext().getSession().get("teaUser")));
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	

//	public String[] getTips() {
//		return tips;
//	}
//
//	public void setTips(String[] tips) {
//		this.tips = tips;
//	}


}
