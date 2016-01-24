package action.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.CourseTable;
import model.StuCourse;
import model.Week;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import service.StuCourseService;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StuCourseAction extends ActionSupport implements Preparable,ModelDriven<StuCourse>{

	private static final long serialVersionUID = -5796339533329384413L;

	private StuCourseService stuCourseService = new StuCourseService();
	
	private Long id;
	private List<StuCourse> list;
	private StuCourse stuCourse;
	private Message message;
	private String week;
	private String no;
	private String cname;
	private String sname;
	private List<CourseTable> tableList = new ArrayList<CourseTable>();
	private Week[] weeks = Week.values();
	
	@SkipValidation
	public String list(){
		list = stuCourseService.findAll(sname);
		
		int i = 1;
		String s = String.valueOf(i);
		while (i <= 7){
			CourseTable ct = new CourseTable();
			ct.setNo(s);
			Iterator<StuCourse> it = list.iterator();
			while (it.hasNext()){
				StuCourse sc = it.next();
				if (sc.getNo().equals(s)){
					if(sc.getWeek().equals("Monday")){
						ct.setMon(sc.getCname());
						ct.type[0] = sc.getType();
						ct.courseid[0] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Tuesday")){
						ct.setTue(sc.getCname());
						ct.type[1] = sc.getType();
						ct.courseid[1] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Wednsday")){
						ct.setWed(sc.getCname());
						ct.type[2] = sc.getType();
						ct.courseid[2] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Thursday")){
						ct.setThu(sc.getCname());
						ct.type[3] = sc.getType();
						ct.courseid[3] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Friday")){
						ct.setFri(sc.getCname());
						ct.type[4] = sc.getType();
						ct.courseid[4] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Saturday")){
						ct.setSat(sc.getCname());
						ct.type[5] = sc.getType();
						ct.courseid[5] = sc.getCourseid();
					}
					if(sc.getWeek().equals("Sunday")){
						ct.setSun(sc.getCname());
						ct.type[6] = sc.getType();
						ct.courseid[6] = sc.getCourseid();
					}
				}
			}
			this.tableList.add(ct);
			i++;
			s = String.valueOf(i);
		}
		

		return "list";
	}

	@Override
	public void prepare() throws Exception {
		sname = (String) ActionContext.getContext().getSession().get("stuUser");
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void prepareEdit(){
		list = stuCourseService.findAll(sname);
		
		int i = 1;
		String s = String.valueOf(i);
		while (i <= 7){
			CourseTable ct = new CourseTable();
			ct.setNo("第 "+s+" 节");
			Iterator<StuCourse> it = list.iterator();
			while (it.hasNext()){
				StuCourse sc = it.next();
				if (sc.getNo().equals(s)){
					if(sc.getWeek().equals("Monday")){
						ct.setMon(sc.getCname());
					}
					if(sc.getWeek().equals("Tuesday")){
						ct.setTue(sc.getCname());
					}
					if(sc.getWeek().equals("Wednsday")){
						ct.setWed(sc.getCname());
					}
					if(sc.getWeek().equals("Thursday")){
						ct.setThu(sc.getCname());
					}
					if(sc.getWeek().equals("Friday")){
						ct.setFri(sc.getCname());
					}
					if(sc.getWeek().equals("Saturday")){
						ct.setSat(sc.getCname());
					}
					if(sc.getWeek().equals("Sunday")){
						ct.setSun(sc.getCname());
					}
				}
			}
			this.tableList.add(ct);
			i++;
			s = String.valueOf(i);
		}
		
		if(id == null)
			stuCourse = new StuCourse();
		else
			stuCourse = this.stuCourseService.getStuCourse(id);
	}

	public String edit(){
		//this.teacher = this.teacherService.getTeacher(this.id);
		return INPUT;
	}

	public void doValidation(){
		if(StringUtils.isBlank(stuCourse.getCname())){
			super.addFieldError("cname", "课程名不能为空");
			return;
		}
	}
	
	public void prepareSave(){
		if(id == null || "".equals(id)){
			System.out.println("sdaf");
			stuCourse = new StuCourse();
			stuCourse.setType("0");
			sname = (String) ActionContext.getContext().getSession().get("stuUser");
			stuCourse.setSname(sname);
		}
		else
		{
			System.out.println("fds");
			stuCourse.setType("0");
			stuCourse = new StuCourse();
			stuCourse.setCname(this.cname);
			stuCourse.setNo(this.no);
			stuCourse.setWeek(week);
		}
	}
	
	public String save(){
		if(super.getFieldErrors().size()>0)
			return INPUT;
		try{
			stuCourse.setCname(this.cname);
			stuCourse.setWeek(week);
			if(this.no.equals("第 1 节")){
				this.stuCourse.setNo("1");
			}
			if(this.no.equals("第 2 节")){
				this.stuCourse.setNo("2");
			}
			if(this.no.equals("第 3 节")){
				this.stuCourse.setNo("3");
			}
			if(this.no.equals("第 4 节")){
				this.stuCourse.setNo("4");
			}
			if(this.no.equals("第 5 节")){
				this.stuCourse.setNo("5");
			}
			if(this.no.equals("第 6 节")){
				this.stuCourse.setNo("6");
			}
			if(this.no.equals("第 7 节")){
				this.stuCourse.setNo("7");
			}
			
			if (stuCourseService.getStuCourseByOther(stuCourse.getSname(), stuCourse.getNo(), stuCourse.getWeek()) != null){
				stuCourse.setType(stuCourseService.getStuCourseByOther(stuCourse.getSname(), stuCourse.getNo(), stuCourse.getWeek()).getType());
			}
			
			if (stuCourse.getType().equals("1")) {
				message = new Message("你选择的课程不能更新","/student/stuCourse_edit.action",MessageType.danger);
				return INPUT;
			} else {
				if (stuCourse.getCname().equals("")||stuCourse.getCname() == null){
					message = new Message("课程信息不能为空","/student/stuCourse_edit.action",MessageType.danger);
					return INPUT;
				} else {
					this.stuCourseService.save(this.stuCourse);
					message = new Message("更新课程成功","/student/stuCourse_list.action",MessageType.success);
				}
			}
		}catch(Exception e){
			super.addFieldError("exception",e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return list();

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the list
	 */
	public List<StuCourse> getList() {
		return list;
	}
	
	public void setList(List<StuCourse> list) {
		this.list = list;
	}

	/**
	 * @return the teacher
	 */
	public StuCourse getModel() {
		return this.stuCourse;
	}
	
	public StuCourse getStuCourse() {
		return this.stuCourse;
	}
	
	public void setStuCourse(StuCourse stuCourse) {
		this.stuCourse = stuCourse;
	}

	public Message getMessage() {
		return message;
	}
	
//	private void prepareModel(){
//		if(StringUtils.isBlank(this.sname))
//			this.stuCourse = this.stuCourseService.getStuCourse(this.sname);
//		else
//			this.stuCourse = new StuCourse();
//	}


	public List<CourseTable> getTableList(){
		return tableList;
	}
	
	public Week[] getWeeks() {
		return weeks;
	}
}
