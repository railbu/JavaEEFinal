<%@ page contentType="image/jpeg" 
import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,model.*,service.*"
import="java.io.FileInputStream,java.io.InputStream,java.io.OutputStream,java.io.ByteArrayInputStream"%>
<%
	User user = (User)session.getAttribute("currentUser1");
//	File f = new File("C:/Users/Rail/Desktop/1.jpg");
	InputStream in = null;
	
	if (user.getType().equals("1")){
		Student student = StudentService.findStudent(user.getUsername());
		in = new ByteArrayInputStream(new PhotoService().getPhoto(student.getPhotoId()).getPhoto());
	} else if (user.getType().equals("2")) {
		Teacher teacher = TeacherService.findTeacher(user.getUsername());
		in = new ByteArrayInputStream(new PhotoService().getPhoto(teacher.getPhotoId()).getPhoto());
	}
	
//  InputStream in = new FileInputStream(f);
    response.setContentType("image/jpeg");
	OutputStream os = response.getOutputStream();
	//ImageIO.write(image, "JPEG", response.getOutputStream()); 
	byte[] b=new byte[1024];
	while(in.read(b)!=-1){
		os.write(b);
	}
	in.close();
	os.flush();
	os.close();
	out.clear();
	out = pageContext.pushBody();

%>