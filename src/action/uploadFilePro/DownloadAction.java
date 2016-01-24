package action.uploadFilePro;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = 3350848163772547204L;

	public InputStream getDownloadFile() {
		return ServletActionContext.getServletContext().getResourceAsStream(
				"/file/aa.txt");
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}