package action.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import service.CourseService;
import service.CourseStuService;
import service.StuCourseService;
import service.StudentService;
import model.Course;
import model.CourseStu;
import model.StuCourse;
import model.Student;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class CourStuAction extends ActionSupport implements Preparable{
	
	private static final long serialVersionUID = -8212373781876281302L;
	private String tName = null;
	private Message message = null;
	private Long id = null;
	private Long cid = null;
	private String stulist = null;
	
	//slist 用在了两个地方，一个是add，一个是moreinfo
	private String[] slist = null;
	
	private List<Student> mslist = null;
	private List<CourseStu> cslist; 
	private List<Course> clist = null;
	
	public String list(){
		clist = CourseService.findAll(tName);
		return "list";
	}
	
	public String moreinfo(){
		Student st;
		cslist = CourseStuService.findAllInCid(cid);
		mslist = new ArrayList<Student>();
		try{
			for(int i=0;i<cslist.size();i++){
				st = StudentService.findStudentAcStuNo(cslist.get(i).getStuNo());
				//如果此学生还为注册，即Student表中没有与之对应的项
				if(st == null){
					st = new Student();
					st.setRealName("该学生未注册");
					st.setStuNo(cslist.get(i).getStuNo());
				}
				//在这个地方更改学生的id，使其成为stucourse 的对应id，方便后续的删除操作！
				st.setId(cslist.get(i).getId());
				mslist.add(st);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "moreinfo";
	}
	
	public String delete(){
		try{
			CourseStuService.delete(id);
			CourseService.addStuCount(cid,-1);
			message = new Message("删除学生成功",null,MessageType.success);
		}catch(Exception e){
			message = new Message("删除学生失败",null,MessageType.danger);
		}
		return moreinfo();
	}
	
	public String add(){
		clist = CourseService.findAll(tName);
		return "add";
	}
	
	public String submit(){
		doValidation();
		if(super.getFieldErrors().size()>0){
			clist = CourseService.findAll(tName);
			return "add";
		}
		try{
			CourseStuService.Insert(cslist);
			CourseService.addStuCount(cid, cslist.size());
			//可能有潜在问题
			Course c = CourseService.getCourse(cslist.get(0).getCid());
			StuCourseService scs = new StuCourseService();
			for(int i=0;i<cslist.size();i++){
				StuCourse sc = new StuCourse();
				Student st = StudentService.findStudentAcStuNo(cslist.get(i).getStuNo());
				sc.setCname(c.getName());
				sc.setCourseid(c.getId());
				sc.setNo(c.getNo());
				sc.setSname(st.getUsername());
				sc.setType("1");
				sc.setWeek(c.getWeek());
				scs.save(sc);
			}
			
		}catch(Exception e){
			super.addFieldError("insertError","服务器内部错误");
			e.printStackTrace();
			
			clist = CourseService.findAll(tName);
			return "add";
		}
		
		message = new Message("添加学生成功",null,MessageType.success);
		return list();
	}
	public void doValidation(){
		
		System.out.println("学生号为:"+stulist);
		
		if(cid == null){
			super.addFieldError("cname","请选择一门课程");
			return;
		}
		if(StringUtils.isBlank(stulist)){
			super.addFieldError("stulist","学号不能为空");
			return;
		}
		slist = stulist.split("\n");
		
		
		cslist = new ArrayList<CourseStu>();
		for(int i=0;i<slist.length;i++){
			if(!StringUtils.isNumeric(slist[i].trim())){
				super.addFieldError("stulist","该学号格式不正确"+slist[i]);
			}else{
				cslist.add(new CourseStu(cid,slist[i].trim()));
			}
		}
	}
	
	
	@Override
	public void prepare() throws Exception {
		tName = (String) ActionContext.getContext().getSession().get("teaUser");
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public List<Course> getClist() {
		return clist;
	}

	public void setClist(List<Course> clist) {
		this.clist = clist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStulist(){
		return stulist;
	}

	public void setStulist(String stulist) {
		this.stulist = stulist;
	}


	public String[] getSlist() {
		return slist;
	}

	public void setSlist(String[] slist) {
		this.slist = slist;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public List<CourseStu> getCslist() {
		return cslist;
	}

	public void setCslist(List<CourseStu> cslist) {
		this.cslist = cslist;
	}

	public List<Student> getMslist() {
		return mslist;
	}

	public void setMslist(List<Student> mslist) {
		this.mslist = mslist;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
