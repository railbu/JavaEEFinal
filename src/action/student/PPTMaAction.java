package action.student;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import service.CourseService;
import service.CourseStuService;
import service.PPTService;
import service.StudentService;
import model.Course;
import model.CourseStu;
import model.PPT;
import model.PPTAn;
import action.support.Message;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class PPTMaAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -1046523495398563790L;

	private List<Course> clist = new ArrayList<Course>();
	private List<CourseStu> cslist = null;
	private List<PPT> pptlist = null;
	private Long id;
	//记录ppt对应的地址，用于删除操作
	private String position = null;
	//要删除的id 用did表示
	private Long did;
	private String name;
	private String sName;
	private String stuNo;
	private String price;
	private Message message = null;
	private PPT ppt = null;

	public String list() {
		this.stuNo = StudentService.findStudent(sName).getStuNo();
		cslist = CourseStuService.findAllInStuNo(this.stuNo);
		
		Iterator<CourseStu> it = cslist.iterator();
		
		while (it.hasNext()){
			CourseStu cs = it.next();
			Course c = CourseService.getCourse(cs.getCid());
			
			this.clist.add(c);
		}
		
		return "list";
	}
	
	//为安卓准备的方法
	public void listAn(){
		System.out.println("收到课件请求来自Android");
		String username = ServletActionContext.getRequest().getParameter("username");
		System.out.println("用户名为="+username);
		this.stuNo = StudentService.findStudent(username).getStuNo();
		cslist = CourseStuService.findAllInStuNo(this.stuNo);
		
		Iterator<CourseStu> it = cslist.iterator();
		
		while (it.hasNext()){
			CourseStu cs = it.next();
			Course c = CourseService.getCourse(cs.getCid());
			
			this.clist.add(c);
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
	//安卓的方法
	public void moreinfoAn(){
		System.out.println("收到课件详情请求   来自Android");
		String cName = null;
		try {
			cName = new String(ServletActionContext.getRequest().getParameter("cName").getBytes("ISO8859-1"),"UTF-8");
			System.out.println("cName="+cName);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Course course = CourseService.getCourseFromCname(cName);
		pptlist = PPTService.findAll(course.getId());
		
        List<PPTAn> palist = new ArrayList<PPTAn>();
        for(int i=0;i<pptlist.size();i++){
        	PPTAn pta = new PPTAn();
        	PPT p = pptlist.get(i);
        	pta.setName(p.getName());
        	pta.setCreTime("2012");
        	pta.setPdfIndisk(p.getPdfIndisk());
        	palist.add(pta);
        }
        
        
        Gson gson = new Gson();
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<PPTAn>>(){  
        }.getType();
        String res = gson.toJson(palist,type);
        
        try {
        	System.out.println("正在发送PPT数据");
			ServletActionContext.getResponse().getWriter().write(res);
			System.out.println("发送成功"+res);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String moreinfo() {
		pptlist = PPTService.findAll(id);
		//通过这个方法将课程号传过去...有点奇怪
		ActionContext.getContext().put("cid",id);
		return "moreinfo";
	}
	
	
	
	public String edit() {
		ppt = PPTService.findPPT(id);
		name = ppt.getName();
		price = String.valueOf(ppt.getPrice());
		return "edit";
	}

	public void doValidation() {
		if (StringUtils.isBlank(name)) {
			super.addFieldError("name", "课件名不能为空");
			return;
		}
		if (StringUtils.isBlank(price)) {
			super.addFieldError("price", "价格不能为空");
			return;
		}
		if (!StringUtils.isNumeric(price)) {
			super.addFieldError("priceNum", "价格必须为数字");
			return;
		}
		if (price.length() > 6) {
			super.addFieldError("priceLen", "价格不能超过999999");
			return;
		}
	}

	public void prepare() throws Exception {
		sName = ((String) ActionContext.getContext().getSession().get("stuUser"));
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	

	public List<CourseStu> getCslist() {
		return cslist;
	}

	public void setCslist(List<CourseStu> cslist) {
		this.cslist = cslist;
	}

}
