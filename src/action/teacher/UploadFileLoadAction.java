package action.teacher;

import java.util.List;

import service.CourseService;
import model.Course;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class UploadFileLoadAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 7954128559718621343L;
	private List<Course> clist = null;
	private String tName = null;
	
	public String load(){
		clist = CourseService.findAll(tName);
		return "load";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		tName = (String) ActionContext.getContext().getSession().get("teaUser");
	}

	public List<Course> getClist() {
		return clist;
	}

	public void setClist(List<Course> clist) {
		this.clist = clist;
	}
	
}
