package action.teacher;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import service.CourseService;
import service.CourseStuService;
import service.HomeworkService;
import service.TSMessageService;
import model.Course;
import model.CourseStu;
import model.Homework;
import model.TSMessage;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class HworkMaAction extends ActionSupport implements Preparable,ModelDriven<Homework>{

	private static final long serialVersionUID = -5091248086119834771L;
	private Long id;
	private Long cid;
	private List<Course> clist = null;
	private List<Homework> hlist = null;
	private List<CourseStu> cslist = null;
	
	private Message message = null;
	private String tName = null;
	private Homework hwork = null;
	
	public String list(){
		clist = CourseService.findAll(gettName());
		return "list";
	}
	
	public String add(){
		clist = CourseService.findAll(tName);
		return "add";
	}
	
	public void prepareSubmit(){
			setHwork(new Homework());
	}
	public String submit(){
		doValidation();
		if(super.getFieldErrors().size()>0)
			return add();
		try{
			HomeworkService.save(getHwork());
			CourseService.addHworkCount(hwork.getCid(), 1);
			
			//想TSmessage 中写入新消息 通知
			cslist = CourseStuService.findAllInCid(hwork.getCid());
			for(int i = 0;i<cslist.size();i++){
				TSMessage tsm = new TSMessage();
				tsm.setStuNo(cslist.get(i).getStuNo());
				tsm.setTid(hwork.getId());
				tsm.setType(5);
				TSMessageService.save(tsm);
			}
			setMessage(new Message("布置作业成功",null,MessageType.success));
			
		}catch(Exception e){
			setMessage(new Message("服务器内部错误",null,MessageType.danger));
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		cid = hwork.getCid();
		return moreinfo(); 
	}
	
	public void prepareEdit(){
		hwork = HomeworkService.getHomework(id);
	}
	public String edit(){
		doValidation();
		if(super.getFieldErrors().size()>0)
			return add();
		try{
			HomeworkService.save(getHwork());
			setMessage(new Message("修改作业成功",null,MessageType.success));
		}catch(Exception e){
			setMessage(new Message("服务器内部错误",null,MessageType.danger));
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		cid = hwork.getCid();
		return moreinfo(); 
	}
	
	public String moreinfo(){
		hlist = HomeworkService.findAll(cid);
		return "moreinfo";
	}
	
	public void doValidation(){
		if(getHwork().getCid() == null){
			super.addFieldError("cid","请选择一门课程");
			return;
		}
		if(StringUtils.isBlank(getHwork().getHworkLabel())){
			super.addFieldError("label","请输入课程标题");
			return;
		}
		if(StringUtils.isBlank(getHwork().getContent())){
			super.addFieldError("label","请输入作业详细内容");
			return;
		}
	}
	
	public String delete(){
		try{
			HomeworkService.delete(id);
			CourseService.addHworkCount(cid, -1);
			message = new Message("删除作业成功",null,MessageType.success);
		}catch(Exception e){
			message = new Message("删除作业失败",null,MessageType.success);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return moreinfo();
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		settName((String) ActionContext.getContext().getSession().get("teaUser"));
	}

	public List<Course> getClist() {
		return clist;
	}

	public void setClist(List<Course> clist) {
		this.clist = clist;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Override
	public Homework getModel() {
		// TODO Auto-generated method stub
		return this.getHwork();
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

	public Homework getHwork() {
		return hwork;
	}

	public void setHwork(Homework hwork) {
		this.hwork = hwork;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Homework> getHlist() {
		return hlist;
	}

	public void setHlist(List<Homework> hlist) {
		this.hlist = hlist;
	}

}
