package action.student;

import java.util.ArrayList;
import java.util.List;

import model.PPT;
import model.StuPpt;
import model.Student;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.StuPptService;
import service.StudentService;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StuPptAction extends ActionSupport implements Preparable,ModelDriven<StuPpt>{

	private static final long serialVersionUID = -5796339533329384413L;

	private StuPptService stupptService = new StuPptService();
	
	private Long id;
	private List<StuPpt> list;
	private List<PPT> followlist;
	private StuPpt stuppt;
	private Message message;
	
	@SkipValidation
	public String list(){
		this.list = this.stupptService.findAll();
		
		return "list";
	}
	
	@SkipValidation
	public String followList(){
		Student student = StudentService.findStudent((String)ActionContext.getContext().getSession().get("currentUser"));
		this.followlist = new ArrayList<PPT>(student.getPpts());
		return "followList";
	}
	
	public StuPpt getStuppt() {
		return stuppt;
	}

	public void setStuppt(StuPpt stuppt) {
		this.stuppt = stuppt;
	}

	
//	@Validations(
//		requiredStrings=@RequiredStringValidator(fieldName="name",message="教师名称不能为空"),
//		requiredFields=@RequiredFieldValidator(fieldName="school",message="所属学院不能为空"),
//		stringLengthFields=@StringLengthFieldValidator(fieldName="name",message="教师名称长度必须介于2到10之间",
//			maxLength="10",minLength="2")
//	)
	public String save(){
		this.stupptService.save(this.stuppt);
		
		this.message = new Message("成功保存了课件信息","/student/stuppt_list.action",MessageType.success);
		
		return this.list();
	}

	@SkipValidation
	public String delete(){
		if(this.id != null && this.id != -1){
			super.addActionError("输入参数不正确");
			return this.list();
		}
		this.message = new Message("成功删除了课件",
				"/student/stuppt_list.action",MessageType.success);
		this.stupptService.delete(this.id);
		return this.list();
	}
	
	public String cancelFollow() {
		StudentService.cancelFollowPpt(((String)ActionContext.getContext().getSession().get("currentUser")),this.id);
		return this.followList();
	}

	/**
	 * @return the id
	 */
	@SkipValidation
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
	public List<StuPpt> getList() {
		return list;
	}

	/**
	 * @return the teacher
	 */
	public StuPpt getTeacher() {
		return stuppt;
	}

	public Message getMessage() {
		return message;
	}
	
	private void prepareModel(){
		if(this.id != null && this.id != -1)
			this.stuppt = this.stupptService.getStuPpt(this.id);
		else
			this.stuppt = new StuPpt();
	}
	
	public void prepareEdit(){
		if(id == null)
			stuppt = new StuPpt();
		else
			stuppt = this.stupptService.getStuPpt(id);
	}
	
	public void prepareSave(){
		this.prepareModel();
	}
	
	public void prepare() throws Exception {
		
	}

	public StuPpt getModel() {
		return this.stuppt;
	}

	public List<PPT> getFollowlist() {
		return followlist;
	}

	public void setFollowlist(List<PPT> followlist) {
		this.followlist = followlist;
	}

}
