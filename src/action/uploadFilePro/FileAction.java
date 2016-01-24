package action.uploadFilePro;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.PPT;

import org.apache.struts2.ServletActionContext;

import service.CourseService;
import service.PPTService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import action.support.Message;
import action.support.MessageType;
import action.uploadFilePro.UploadFile;
import action.uploadFilePro.UploadFile.Progress;

public class FileAction extends ActionSupport implements Preparable,ModelDriven<PPT>{

	private static final long serialVersionUID = 6649027352616232244L;
	private List<Course> clist = null;
	private FileService fileService;
	private Long id;
	private Message message;
	private PPT ppt;
	
	public String preupload() {
		return SUCCESS;
	}
	
	public String uploadfile() {
		try {
			System.out.println("文件上传操作开始");
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			Course tc = new Course();
			Long cid = Long.valueOf(request.getParameter("cid"));
			tc.setId(cid);
			ppt.setCourse(tc);
			//中文解码
			String tpname = new String(request.getParameter("pname").getBytes("ISO8859-1"), "UTF-8");

//			String tpname = URLDecoder.decode(request.getParameter("pname"),"UTF-8");
//			System.out.println("-------码------"+tpname);
//			tpname = URLDecoder.decode(tpname,"UTF-8");
			System.out.println("解码结果"+tpname);
			
			ppt.setName(tpname);
			ppt.setPrice(Integer.valueOf(request.getParameter("price")));
			
			//position可以获得路径
			String position = UploadFile.upload(request, response);
			
			//ts中包含了路径信息
			String[] ts = position.split("\\\\");
			String nameIndisk = "allPPt/"+ts[ts.length-2]+"/"+ts[ts.length-1];
			ppt.setNameInDisk(nameIndisk);
			String[] pdfts = nameIndisk.split("\\.");
			ppt.setPdfIndisk(pdfts[pdfts.length-2]+".pdf");
			ppt.setPosition(position);
			ppt.setFirPicPosition(pdfts[pdfts.length-2]+"/1.JPG");
			
			//设置ppt创建时间
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
			ppt.setCreTime(dateFormat.format(now));
			
			PPTService.save(ppt);
			
			//为课程增加课件数量
			CourseService.addPPTCount(cid,1);
			message = new Message("上传PPT成功",null,MessageType.success);
		} catch (IOException e) {
			System.out.println("上传文件发生异常,错误原因 : " + e.getMessage());
			e.printStackTrace();
		}
		//返回列表
		return null;
	}

	public String progress() {
		System.out.println("begin");
		System.out.println("文件上传进度监测开始");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String callback1 = request.getParameter("callback1");
		String callback2 = request.getParameter("callback2");
		// 缓存progress对象的key值
		String key = Integer.toString(request.hashCode());
		// 新建当前上传文件的进度信息对象
		Progress p = new Progress();
		// 缓存progress对象
		request.getSession().setAttribute(key, p);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		try {
			System.out.println("文件上传进度监测开始--------");
			UploadFile.execClientScript(response, callback1 + "(" + key + ")");
			long temp = 0l;
			while (!p.isComplete()) {
				if (temp != p.getCurrentLength()) {
					temp = p.getCurrentLength();
					// 向客户端显示进度
					UploadFile.execClientScript(
							response,
							callback2 + "(" + p.getCurrentLength() + ","
									+ p.getLength() + ")");
					// System.out.println("progress的状态 ：" + p.isComplete());
				} else {
					// System.out.println("progress的状态 ：" + p.isComplete());
					// System.out.println("progress上传的数据量 ：+ " + p.getCurrentLength());
					// 上传进度没有变化时候,不向客户端写数据,写数据过于频繁会让chrome没响应
					Thread.sleep(100);
				}
			}
		} catch (Exception e) {
			System.out.println("调用客户端脚本错误,原因 ：" + e.getMessage());
			p.setComplete(true);
		}
		try {
			UploadFile.execClientScript(
					response,
					callback2 + "(" + p.getCurrentLength() + ","
							+ p.getLength() + ")");
		} catch (Exception e) {
			p.setComplete(true);
		}
		request.getSession().removeAttribute(key);
		System.out.println("删除progress对象的session key");
		
		return null;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Override
	public PPT getModel() {
		return this.ppt;
	}

	@Override
	public void prepare() throws Exception {
		this.ppt = new PPT();
		
	}

	public PPT getPpt() {
		return ppt;
	}

	public void setPpt(PPT ppt) {
		this.ppt = ppt;
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

	public List<Course> getClist() {
		return clist;
	}

	public void setClist(List<Course> clist) {
		this.clist = clist;
	}
}