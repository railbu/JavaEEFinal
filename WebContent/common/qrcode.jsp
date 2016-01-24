<%@page import="service.utils.TwoDimensionCode"%>
<%@ page contentType="image/jpeg" 
import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,model.*,service.*"
import="java.io.FileInputStream,java.io.InputStream,java.io.OutputStream,java.io.ByteArrayInputStream"%>
<%
	String content = "http://192.168.137.1:8080/JavaEEFinal/login/"+session.getId();

	TwoDimensionCode qrcode = new TwoDimensionCode();
	
	BufferedImage QRCode = qrcode.qRCodeCommon(content,"png",9);
	
//  InputStream in = new FileInputStream(f);
    response.setContentType("image/jpeg");
//	OutputStream os = response.getOutputStream();
	ImageIO.write(QRCode, "JPEG", response.getOutputStream()); 
//	byte[] b=new byte[1024];
//	while(in.read(b)!=-1){
//		os.write(b);
//	}
//	in.close();
//	os.flush();
//	os.close();
	out.clear();
	out = pageContext.pushBody();

%>