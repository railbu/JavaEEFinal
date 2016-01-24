package action.teacher;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import service.CourseService;
import service.PPTService;
import model.Course;
import model.PPT;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class PPTMaAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -1046523495398563790L;

	private List<Course> clist = null;
	private List<PPT> pptlist = null;
	private Long id;
	//记录ppt对应的地址，用于删除操作
	private String position = null;
	//要删除的id 用did表示
	private Long did;
	private String name;
	private String price;
	private String tName;
	private Message message = null;
	private PPT ppt = null;

	public String list() {
		clist = CourseService.findAll(tName);
		return "list";
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

	public String delete() {
		try {
			new File(position).delete();
			PPTService.delete(did);
			CourseService.addPPTCount(id, -1);
			message = new Message("删除PPT成功", null, MessageType.success);
		} catch (Exception e) {
			message = new Message("删除PPT失败,服务器内部错误", null, MessageType.warning);
			e.printStackTrace();
		}
		return moreinfo();
	}

	public String save() {
		doValidation();
		if (super.getFieldErrors().size() > 0)
			return "edit";
		ppt = PPTService.findPPT(id);
		ppt.setName(name);
		ppt.setPrice(Integer.valueOf(price));
		try {
			PPTService.save(ppt);
		} catch (Exception e) {
			e.printStackTrace();
			return "edit";
		}
		message = new Message("编辑PPT成功", null, MessageType.success);
		return moreinfo();
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
		tName = (String) ActionContext.getContext().getSession().get("teaUser");
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

}
