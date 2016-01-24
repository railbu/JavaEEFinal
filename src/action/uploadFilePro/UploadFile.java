package action.uploadFilePro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.PPTToPDF.JacobPptUtil;
import service.utils.MD5Util;
import service.utils.TwoDimensionCode;
import com.opensymphony.xwork2.ActionContext;

public class UploadFile {

	private static String position = null;

	@SuppressWarnings("unchecked")
	public static String upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("客户端提交类型: " + request.getContentType());
		
		// 判断文件类型，为空则报异常
		if (request.getContentType() == null) {
			throw new IOException(
					"the request doesn't contain a multipart/form-data stream");
		}
		String key = request.getParameter("key");
		Progress p = (Progress) request.getSession().getAttribute(key);
		// 设置上传文件总大小
		p.setLength(request.getContentLength());
		System.out.println("上传文件大小为 : " + p.getLength());
		// 上传临时路径
		
		//重要！上传路径问题
		String tName = (String) ActionContext.getContext().getSession().get("teaUser");
		String path = request.getSession().getServletContext().getRealPath("\\allPPt");
		path = path+"\\"+tName;
		File tf = new File(path);
		if(!tf.exists()){
			tf.mkdirs();
		}
		//String path = "C:\\Users\\MrZhang\\workspace\\JavaEEFinal\\WebContent\\allPPT\\";
		System.out.println("上传临时路径 : " + path);
		
		// 设置上传工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		// 阀值,超过这个值才会写到临时目录
		factory.setSizeThreshold(1024 * 1024 * 10);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 最大上传限制 200M
		upload.setSizeMax(1024 * 1024 * 200);
		// 设置监听器监听上传进度
		upload.setProgressListener(p);
		try {
			System.out.println("解析上传文件....");
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("上传数据...");
			
			for (FileItem item : items) {
				// File destFile = new File(path,item.getFieldName());
				// 非表单域
				if (!item.isFormField()) {
					System.out.println("上传路径  : " + path);
					System.out.println("item="+item.getName());
					
					//判断文件上传时的所给路径
//					int ts = item.getName().lastIndexOf("\\");
					String tna = null;
//					if(ts != -1)
//						tna = item.getName().substring(ts,item.getName().length());
//					else
					//取出文件后缀名
					String tnsHZ[] = item.getName().split("\\.");
					tna = MD5Util.MD5(item.getName())+"."+tnsHZ[tnsHZ.length-1];
					
					
					FileOutputStream fos = new FileOutputStream(path+"\\"+tna);
					position = path+"\\"+tna;
					// 文件全在内存中
					if (item.isInMemory()) {
						fos.write(item.get());
						p.setComplete(true);
					} else {
						InputStream is = item.getInputStream();
						byte[] buffer = new byte[1024];
						int len;
						while ((len = is.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						is.close();
					}
					fos.close();
					System.out.println("完成上传文件!");
					item.delete();
					System.out.println("删除临时文件!");
					p.setComplete(true);
					System.out.println("更新progress对象状态为完成状态!");
					
					
					String rePath = position;
					String[] pns = tna.split("\\.");
					String outPath = path+"\\"+pns[0];
					File file = new File(outPath);
					if(file.exists() == false)
						file.mkdir();
					System.out.println("文件路径"+rePath);
					//先转化成图片
					JacobPptUtil.ppt2img(rePath,outPath, true);
					System.out.println("图片转化完成");
					//在转化成PDF
					String pdfout = path+"\\"+pns[0]+".pdf";
					JacobPptUtil.ppt2pdf(rePath,pdfout,true);
					System.out.println("PDF转化完成");
					
					//生成二维码
					//先处理需要保存的
					String[] tss = position.split("\\\\");
					String pdfPath = "allPPt/"+tss[tss.length-2]+"/"+tss[tss.length-1];
					String codeOut = path+"\\"+pns[0]+".JPG";
					TwoDimensionCode.generateTDC(codeOut,pdfPath);
					
					//对生成的图片重命名
					for(int i=1;i<=5;i++){
						File f = new File(outPath+"\\幻灯片"+i+".JPG");
						f.renameTo(new File(outPath+"\\"+i+".JPG"));
					}
					System.out.println("重命名成功");
					
					
				}
			}
		} catch (Exception e) {
			System.out.println("上传文件出现异常, 错误原因 : " + e.getMessage());
			e.printStackTrace();
			// 发生错误,进度信息对象设置为完成状态
			p.setComplete(true);
			request.getSession().removeAttribute(key);
		}
		return position;
	}

	public static void execClientScript(HttpServletResponse resposne,
			String script) throws IOException {
		PrintWriter out = resposne.getWriter();
		out.println("<script type='text/javascript'>" + script + "</script>");
		// fix ie problem
		out.println("------------------------------------------------------");
		out.println("------------------------------------------------------");
		out.println("------------------------------------------------------");
		out.println("------------------------------------------------------");
		out.println("------------------------------------------------------");
		out.flush();
	}

	public static class Progress implements ProgressListener {
		// 文件总长度
		private long length = 0;
		// 已上传的文件长度
		private long currentLength = 0;
		// 上传是否完成
		private boolean isComplete = false;

		@Override
		public void update(long bytesRead, long contentLength, int items) {
			this.currentLength = bytesRead;
		}

		public long getLength() {
			return length;
		}

		public long getCurrentLength() {
			return currentLength;
		}

		public boolean isComplete() {
			return isComplete;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public void setCurrentLength(long currentLength) {
			this.currentLength = currentLength;
		}

		public void setComplete(boolean isComplete) {
			this.isComplete = isComplete;
		}
	}
}