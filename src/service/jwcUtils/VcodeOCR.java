package service.jwcUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

class VcodeOCR{
	public List<BufferedImage> imglist = new ArrayList<BufferedImage>();
	
	public BufferedImage iniImg(BufferedImage img){
		BufferedImage resImg = new BufferedImage(13, 20, BufferedImage.TYPE_INT_RGB);
		int width = img.getWidth();
		int height = img.getHeight();
		Color bac = new Color(0,0,0);
		Color fro = new Color(255,255,255);
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				Color tcolor = new Color(img.getRGB(i, j));
				if(tcolor.getGreen()+tcolor.getBlue()+tcolor.getRed()<=350)
					resImg.setRGB(i, j, bac.getRGB());
				else
					resImg.setRGB(i, j, fro.getRGB());
			}
		}
		return resImg;
	}
	
	public int isBlack(int colorInt){
		Color c = new Color(colorInt);
		if(c.getRed()+c.getBlue()+c.getGreen()<100){
			return 1;
		}
		return 0;
	}
	
	public List<BufferedImage> disImage(BufferedImage img){
		List<BufferedImage> subimgs = new ArrayList<BufferedImage>();
		for(int i=0;i<4;i++){
			subimgs.add(img.getSubimage(6+i*13, 0, 13,20));
		}
		return subimgs;
	}
	
	public int cpImg(BufferedImage img1,BufferedImage img2){
		int count=0;
		for(int i=0;i<img1.getWidth();i++){
			for(int j=0;j<img2.getHeight();j++){
				if(isBlack(img1.getRGB(i, j)) == isBlack(img2.getRGB(i,j))){
					count++;
				}
			}
		}
		
		return count;
	}
	
	public void outputIMG(BufferedImage img,int i){
		try {
			OutputStream out = new FileOutputStream("C:/java1/debug"+i+".jpg");
			ImageIO.write(img, "jpg", out);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String Vcode(BufferedImage img){
		List<BufferedImage> subImgs = null; 
		BufferedImage res = null;
//		BufferedImage cp = null;
		String resStr = "";
		File dir = new File("C:/java1/train");
		File[] files = dir.listFiles();
		
		
		try {
			
			/*URL url = new URL("http://202.205.99.130/validateCodeAction.do");
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			InputStream in = con.getInputStream();
			img = ImageIO.read(in);
			*/
			
			subImgs = disImage(img);
			
			for(int i=0;i<subImgs.size();i++){
				res = iniImg(subImgs.get(i));
				int max = 0;
				char tcode = ' ';
				for(int j=0;j<files.length;j++){
					BufferedImage timg = ImageIO.read(files[j]);
					
					int ct = cpImg(res, timg);
					
					if(ct > max){
						max = ct;
						tcode = files[j].getName().charAt(0);
						if(files[j].getName().charAt(1) != '.'){
							tcode = (char) (tcode + 'A'-'a');
						}
					}
				}
				resStr += String.valueOf(tcode);
			}
			
			return resStr;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return "";
	}
}



















