package action.student;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import service.CourseService;
import service.CourseStuService;
import service.HomeworkService;
import service.StudentService;
import service.TSMessageService;
import model.Course;
import model.CourseStu;
import model.Homework;
import model.TSMessage;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class HworkAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 3416911735846669775L;
	private List<Homework> unhlist = null;
	private List<Homework> hlist = null;
	private List<TSMessage> tslist = null;
	private List<Course> clist = null;
	private List<CourseStu> cslist = null;
	private Long id = null;
	private Long cid = null;
	private String stuNo = null;
	private String sName = null;
	
	//先请求这个函数
	public String listUnRead(){
		
		if(ActionContext.getContext().getSession().get("tips5") == null)
			return listAll();
		stuNo = StudentService.findStudent(sName).getStuNo();
		tslist = TSMessageService.findAll(stuNo);
		unhlist = new ArrayList<Homework>();
		for(int i=0;i<tslist.size();i++){
			if(tslist.get(i).getType() == 5){
				Homework hwork = HomeworkService.getHomework(tslist.get(i).getTid());
				//设置一下课程名称
				hwork.setCourseName(CourseService.getCourse(hwork.getCid()).getName());
				hwork.setId(tslist.get(i).getId());
				unhlist.add(hwork);
			}
		}
		return "unread";
	}
	
	public String listAll(){
		stuNo = StudentService.findStudent(sName).getStuNo();
		clist = new ArrayList<Course>();
		cslist = CourseStuService.findAllInStuNo(stuNo);
		for(int i=0;i<cslist.size();i++){
			Course c = CourseService.getCourse(cslist.get(i).getCid());
			clist.add(c);
		}
		
		return "list";
	}
	
	//为安卓请求的函数
	public void listAllAn(){
		
		try {
			sName = new String(ServletActionContext.getRequest().getParameter("username").getBytes("ISO8859-1"),"UTF-8");
			System.out.println("sName="+sName);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		stuNo = StudentService.findStudent(sName).getStuNo();
		clist = new ArrayList<Course>();
		cslist = CourseStuService.findAllInStuNo(stuNo);
		for(int i=0;i<cslist.size();i++){
			Course c = CourseService.getCourse(cslist.get(i).getCid());
			clist.add(c);
		}
		
		Gson gson = new Gson();
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Course>>(){  
        }.getType();  
        String res = gson.toJson(clist,type);
        try {
        	System.out.println("正在发送");
			ServletActionContext.getResponse().getWriter().write(res);
			System.out.println("发送成功"+res);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public void moreinfoAn(){
		String cName = null;
		try {
			cName = new String(ServletActionContext.getRequest().getParameter("cName").getBytes("ISO8859-1"),"UTF-8");
			System.out.println("cName="+cName);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		hlist = HomeworkService.findAll(CourseService.getCourseFromCname(cName).getId());
		Gson gson = new Gson();
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<Homework>>(){  
        }.getType();  
        String res = gson.toJson(hlist,type);
        try {
        	System.out.println("正在发送");
			ServletActionContext.getResponse().getWriter().write(res);
			System.out.println("发送成功"+res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String read(){
		TSMessageService.delete(id);
		int tc = (int) ActionContext.getContext().getSession().get("tips5");
		tc --;
		ActionContext.getContext().getSession().put("tips5",tc>0?tc:null);
		
		return listUnRead();
	}
	
	public String moreinfo(){
		hlist = HomeworkService.findAll(cid);
		return "moreinfo";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		sName = (String) ActionContext.getContext().getSession().get("stuUser");
		
	}

	public List<Homework> getUnhlist() {
		return unhlist;
	}

	public void setUnhlist(List<Homework> unhlist) {
		this.unhlist = unhlist;
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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public List<Homework> getHlist() {
		return hlist;
	}

	public void setHlist(List<Homework> hlist) {
		this.hlist = hlist;
	}
	
}
