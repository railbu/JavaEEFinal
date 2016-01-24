package action.teacher;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import service.CourseService;
import model.Course;
import model.Week;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CourseAction extends ActionSupport implements Preparable,ModelDriven<Course>{

	private static final long serialVersionUID = 5073457571752268123L;
	private Long id = null;
	private Course course = null;
	private List<Course> list = null;
	private Message message = null;
	private String tName = null;
	private Week[] weeks = Week.values();
	private String[] tableList = {"1","2","3","4","5","6","7"};
	
	public String list(){
		list = CourseService.findAll(tName);
		return "list";
	}
	
	public void prepareEdit(){
		if(id == null)
			course = new Course();
		else
			course = CourseService.getCourse(id);
	}
	public String edit(){
		
		return INPUT;
	}
	
	public void doValidation(){
		if(StringUtils.isBlank(course.getName())){
			super.addFieldError("name", "课程名不能为空");
			return;
		}
		if(StringUtils.isBlank(course.getSnumber())){
			super.addFieldError("snumber", "课程号不能为空");
			return;
		}
		
	}
	
	public void prepareSave(){
		if(id == null || "".equals(id)){
			course = new Course();
			tName = (String) ActionContext.getContext().getSession().get("teaUser");
			course.setTName(tName);
		}
		else
			course = CourseService.getCourse(id);
	}
	
	public String save(){
		doValidation();
		if(super.getFieldErrors().size()>0)
			return INPUT;
		try{
			CourseService.save(course);
			//message = new Message("更新课程成功",null,MessageType.success);
		}catch(Exception e){
			super.addFieldError("exception",e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return list();
	}
	
	public String delete(){
		try{
			CourseService.delete(id);
			message = new Message("删除课程成功",null,MessageType.success);
		}catch(Exception e){
			message = new Message("删除课程失败",null,MessageType.danger);
			e.printStackTrace();
		}
		return list();
	}
	
	public Course getModel() {
		return course;
	}

	@Override
	public void prepare() throws Exception {
		tName = (String) ActionContext.getContext().getSession().get("teaUser");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getList() {
		return list;
	}

	public void setList(List<Course> list) {
		this.list = list;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Week[] getWeeks() {
		return weeks;
	}

	public void setWeeks(Week[] weeks) {
		this.weeks = weeks;
	}

	public String[] getTableList() {
		return tableList;
	}

	public void setTableList(String[] tableList) {
		this.tableList = tableList;
	}
	
	
}
