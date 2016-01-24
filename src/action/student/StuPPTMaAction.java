package action.student;

import java.util.ArrayList;
import java.util.List;
import service.CourseService;
import service.CourseStuService;
import service.PPTService;
import service.StudentService;
import model.Course;
import model.CourseStu;
import model.PPT;
import action.support.Message;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class StuPPTMaAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -1046523495398563790L;
	private List<CourseStu> cs = null;
	private List<Course> clist = null;
	private List<PPT> pptlist = null;
	private Long id;
	
	private String name;
	private String stuName;
	private String stuNo;
	
	private Message message = null;
	private PPT ppt = null;

	public String list() {
		cs = CourseStuService.findAllInStuNo(stuNo);
		clist = new ArrayList<Course>();
		
		for(int i =0;i<cs.size();i++){
			Course tc = CourseService.getCourse(cs.get(i).getCid());
			clist.add(tc);
		}
		
		return "list";
	}

	public String moreinfo() {
		pptlist = PPTService.findAll(id);
		//通过这个方法将课程号传过去...有点奇怪
		ActionContext.getContext().put("cid",id);
		return "moreinfo";
	}


	public void prepare() throws Exception {
		stuName = (String) ActionContext.getContext().getSession().get("stuUser");
		stuNo = StudentService.findStudent(stuName).getStuNo();
	}

	public List<Course> getClist() {
		return clist;
	}

	public void setClist(List<Course> clist) {
		this.clist = clist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PPT> getPptlist() {
		return pptlist;
	}

	public void setPptlist(List<PPT> pptlist) {
		this.pptlist = pptlist;
	}

	public PPT getPpt() {
		return ppt;
	}

	public void setPpt(PPT ppt) {
		this.ppt = ppt;
	}

	public List<CourseStu> getCs() {
		return cs;
	}

	public void setCs(List<CourseStu> cs) {
		this.cs = cs;
	}

}
