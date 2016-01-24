package action;

import java.util.List;

import model.PPT;
import service.PPTService;

import com.opensymphony.xwork2.ActionSupport;

public class LastAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String[] pos = new String[3];
	private String[] des = new String[3];
	
	public String last(){
		List<PPT> pptlist = PPTService.findAllPPTS();
		for(int i=0;i<pptlist.size();i++){
			getPos()[i]=pptlist.get(i).getFirPicPosition();
			getDes()[i]=pptlist.get(i).getName()+"\n"+pptlist.get(i).getCreTime();
			String ts[] = des[i].split("\\.");
			des[i]=(i+1)+"."+ts[0];
		}
		
		return SUCCESS;
	}

	public String[] getPos() {
		return pos;
	}

	public void setPos(String[] pos) {
		this.pos = pos;
	}

	public String[] getDes() {
		return des;
	}

	public void setDes(String[] des) {
		this.des = des;
	}
	
	
}
