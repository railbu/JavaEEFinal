package action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import service.SearchService;
import service.StudentService;
import model.PPtPic;
import model.SResult;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class SearchAction extends ActionSupport implements Preparable{
	private static final long serialVersionUID = 1L;
	
	private String input;
	private List<SResult> srlist;
	private List<PPtPic> piclist;
	private String position;
	private String nameIndisk;
	private String pdfNameIndisk;
	private String price;
	private Long pptId;
	private String iflogin;
	private int type;
	private String message;
	private Message mess;
	private String content;
	private String dcPicPostion;
	
	public String search(){
		try{
			ServletActionContext.getRequest().getSession().setAttribute("search",input);
			srlist = SearchService.search(input);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String search(String s){
		try{
			srlist = SearchService.search(s);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String loginSearch(){
		
		System.out.println("走到这里了");
		if(getMessage() != null)
		System.out.println(getMessage());
		System.out.println("iflogin="+iflogin);
		String inp = ((String)ActionContext.getContext().getSession().get("search"));
		if(!StringUtils.isBlank(message)){
			mess = new Message(message,null,MessageType.danger);
		}
		return this.search(inp);
	
	}
	
	public String present(){
		if(StringUtils.isBlank(position)){
			System.out.println("我找了");
			position = (String)ActionContext.getContext().getSession().get("nameIndisk");
			System.out.println("postion="+position);
		}
		if(StringUtils.isBlank(content)){
			content = (String)ActionContext.getContext().getSession().get("content");
		}
		setNameIndisk(position);
		piclist = new ArrayList<PPtPic>();
		String ps[] = position.split("\\.");
		position = ps[0]+"/";
		dcPicPostion = ps[0]+".JPG";
		this.pdfNameIndisk = ps[0]+".pdf";
		
		for(int i=1;i<=5;i++){
			PPtPic p = new PPtPic();
			p.setUrl(position+i+".JPG");
			p.setIndex(String.valueOf(i));
			System.out.println(p.getUrl());
			piclist.add(p);
		}
		return "present";
	}
	
	public String follow(){
			
		StudentService.followPpt(((String)ActionContext.getContext().getSession().get("currentUser")),this.pptId);
		super.addFieldError("success", "收藏成功！");
		return this.search(((String)ActionContext.getContext().getSession().get("search")));
	}
	
	public String cancelFollow() {
		StudentService.cancelFollowPpt(((String)ActionContext.getContext().getSession().get("currentUser")),this.pptId);
		super.addFieldError("warning", "已取消收藏！");
		return this.search(((String)ActionContext.getContext().getSession().get("search")));
	}
	
	@Override
	public void prepare() throws Exception {
	}

	public Long getPptId() {
		return pptId;
	}

	public void setPptId(Long pptid) {
		this.pptId = pptid;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public List<SResult> getSrlist() {
		return srlist;
	}

	public void setSrlist(List<SResult> srlist) {
		this.srlist = srlist;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<PPtPic> getPiclist() {
		return piclist;
	}

	public void setPiclist(List<PPtPic> piclist) {
		this.piclist = piclist;
	}

	public String getNameIndisk() {
		return nameIndisk;
	}

	public void setNameIndisk(String nameIndisk) {
		this.nameIndisk = nameIndisk;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIflogin() {
		return iflogin;
	}

	public void setIflogin(String iflogin) {
		this.iflogin = iflogin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message getMess() {
		return mess;
	}

	public void setMess(Message mess) {
		this.mess = mess;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPdfNameIndisk() {
		return pdfNameIndisk;
	}

	public void setPdfNameIndisk(String pdfNameIndisk) {
		this.pdfNameIndisk = pdfNameIndisk;
	}

	public String getDcPicPostion() {
		return dcPicPostion;
	}

	public void setDcPicPostion(String dcPicPostion) {
		this.dcPicPostion = dcPicPostion;
	}



}
