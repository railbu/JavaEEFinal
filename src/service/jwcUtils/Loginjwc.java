package service.jwcUtils;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Loginjwc {
	public List<CourseInfo> getCourse(String stuID,String pword) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"http://202.205.99.130/validateCodeAction.do");
		HttpPost httpPost = new HttpPost("http://202.205.99.130/loginAction.do");
		CloseableHttpResponse response = null;
		BufferedImage img = null;
		String vstr;
		//英语的额怎么说
		List<CourseInfo> clist = new ArrayList<CourseInfo>();
		
		try {
			response = httpClient.execute(httpGet);
			InputStream in = response.getEntity().getContent();
			img = ImageIO.read(in);

			vstr = new VcodeOCR().Vcode(img);
			System.out.println(vstr);

			OutputStream out = new FileOutputStream("C:/java1/jj.jpg");
			ImageIO.write(img, "jpg", out);

			List<NameValuePair> para = new ArrayList<NameValuePair>();
			para.add(new BasicNameValuePair("zjh", stuID));
			para.add(new BasicNameValuePair("mm", pword));
			para.add(new BasicNameValuePair("v_yzm", vstr));
			httpPost.setEntity(new UrlEncodedFormEntity(para));
			System.out.println("Post start");
			response = httpClient.execute(httpPost);
			System.out.println("Post done");
			
			
			httpGet = new HttpGet("http://202.205.99.130/xskbAction.do?actionType=1");
			response = httpClient.execute(httpGet);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "GBK"));
			String resstr = null;
			
			//定位到课程列表，忽略前面的html
			int f1 = 0;
			int f2 = 0;
			while ((resstr = br.readLine()) != null) {
				if(resstr.contains("<!-- 课程列表 -->"))
					f1 = 1;
				if(f1 == 1 && resstr.contains("<tbody>"))
					f2 = 1;
				if(f1 == 1 && f2 ==1)
					break;
			}
			
			int i=-1,j=0;
			String[] tmpinfo = new String[20];
			while ((resstr = br.readLine()) != null) {
				//比较恶心的数据处理
				if(resstr.contains("<td rowspan")){
					j++;
					//先把数据读到临时数组里
					tmpinfo[j] = br.readLine();
				}
				
				else if(resstr.contains("<td align=\"center\">")){
					if(j == 0)
						j = 11;
					j++;
					tmpinfo[j] = br.readLine();
				}
				
				if(resstr.contains("onMouseOver")){
					//在读下一节课之前先对当前课程赋值
					if(i!=-1){
						clist.get(i).name = tmpinfo[4].trim();
						clist.get(i).teacher=tmpinfo[9].trim();
						clist.get(i).zhouci=tmpinfo[12].trim();
						clist.get(i).week = Integer.valueOf(tmpinfo[13].trim());
						clist.get(i).jieci=Integer.valueOf(tmpinfo[14].trim());
						clist.get(i).classroom = tmpinfo[18].trim();
						}
					//创建新的对象
					CourseInfo ci = new CourseInfo();
					clist.add(ci);
					i++;
					j=0;
				}
			}
			if(i!=-1){
				clist.get(i).name = tmpinfo[4].trim();
				clist.get(i).teacher=tmpinfo[9].trim();
				clist.get(i).zhouci=tmpinfo[12].trim();
				clist.get(i).week = Integer.valueOf(tmpinfo[13].trim());
				clist.get(i).jieci=Integer.valueOf(tmpinfo[14].trim());
				clist.get(i).classroom = tmpinfo[18].trim();
			}
			
			for(i=0;i<clist.size();i++){
				CourseInfo tci = clist.get(i);
				System.out.println("CN:"+tci.name+"  TN:"+tci.teacher+"  ZC:"+tci.zhouci+"  星期几:"+tci.week+"  节"+tci.jieci+"  教师:"+tci.classroom);
			}


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return clist;
	}
}
