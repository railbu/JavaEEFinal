package action.uploadFilePro;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

public class MyRequestParseWrapper extends JakartaMultiPartRequest {

	@Override
	public void parse(HttpServletRequest servletRequest, String saveDir)
			throws IOException {
		// 什么也不做---
	}
}