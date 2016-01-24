package service.PPTToPDF;

public class TestMain {
	public static void main(String[] args){
		String filePath = "C:/Users/MrZhang/Desktop/2.ppt";
		String folderPath = "C:/Users/MrZhang/Desktop/ppt";
		
		try {
			//JacobPptUtil jac = new JacobPptUtil(filePath, true);
			JacobPptUtil.ppt2img(filePath, folderPath, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
