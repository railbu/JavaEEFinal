package action.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.CourseTable;
import model.StuCourse;
import service.StuCourseService;
import service.jwcUtils.CourseInfo;
import service.jwcUtils.Loginjwc;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class OneKeyLeadin extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	
	private String studentID;
	private String password;
	Loginjwc jwcclient = new Loginjwc();
	private List<CourseInfo> clist = null;
	
	private List<StuCourse> list;
	private List<CourseTable> tableList = new ArrayList<CourseTable>();
	
	StuCourse stuCourse = null;
	StuCourseService scs = new StuCourseService();
	String sname;
	//注意那个Wednsday
	String[] weeks = {"","Monday","Tuesday","Wednsday","Thursday","Friday","Saturday","Sunday"};
	
	public void prepareLeadin(){
		sname = (String) ActionContext.getContext().getSession().get("stuUser");
	}
	
	public String list(){
		setList(scs.findAll(sname));
		
		int i = 1;
		String s = String.valueOf(i);
		while (i <= 7){
			CourseTable ct = new CourseTable();
			ct.setNo(s);
			Iterator<StuCourse> it = getList().iterator();
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
			this.getTableList().add(ct);
			i++;
			s = String.valueOf(i);
		}
		

		return "list";
	}

	
	public String leadin(){
		System.out.println(getStudentID()+"  "+getPassword());
		setClist(jwcclient.getCourse(getStudentID(), getPassword()));
		if(getClist() == null)
			return "wrong";
		for(CourseInfo ci : getClist()){
			stuCourse = new StuCourse();
			stuCourse.setType("0");
			stuCourse.setSname(sname);
			stuCourse.setCname(ci.name+'\n'+ci.teacher+'\n'+ci.classroom);
			stuCourse.setWeek(weeks[ci.week]);
			stuCourse.setNo(String.valueOf(ci.jieci));
			StuCourse tmpstu = scs.getStuCourseByOther(stuCourse.getSname(), stuCourse.getNo(), stuCourse.getWeek());
			if(tmpstu != null && tmpstu.getType().equals("1"))
				continue;
			scs.save(this.stuCourse);
		}
		
		return list();
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CourseTable> getTableList() {
		return tableList;
	}

	public void setTableList(List<CourseTable> tableList) {
		this.tableList = tableList;
	}

	public List<StuCourse> getList() {
		return list;
	}

	public void setList(List<StuCourse> list) {
		this.list = list;
	}

	public List<CourseInfo> getClist() {
		return clist;
	}

	public void setClist(List<CourseInfo> clist) {
		this.clist = clist;
	}
	
	
	
}
