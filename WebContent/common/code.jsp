<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%!
	// ç»å®èå´è·å¾éæºé¢è²
	Color getRandColor(int fc,int bc) {
	    Random random = new Random();
	    if(fc > 255) {
	        fc = 255;
	    }
	    if(bc > 255) {
	        bc = 255;
	    }
	    int r = fc + random.nextInt(bc - fc);
	    int g = fc + random.nextInt(bc - fc);
	    int b = fc + random.nextInt(bc - fc);
	    return new Color(r, g, b);
	}
%>
<%
	//è®¾ç½®é¡µé¢ä¸ç¼å­
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	// å¨åå­ä¸­åå»ºå¾è±¡
	int width = 65, height = 22;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	// è·åå¾å½¢ä¸ä¸æ
	Graphics g = image.getGraphics();
	
	//çæéæºç±»
	Random random = new Random();
	
	// è®¾å®èæ¯è²
	g.setColor(getRandColor(200,250));
	g.fillRect(0, 0, width, height);
	
	//è®¾å®å­ä½
	g.setFont(new Font("Consolas", Font.PLAIN, 22));
	
	//ç»è¾¹æ¡
	//g.setColor(new Color());
	//g.drawRect(0,0,width-1,height-1);
	
	
	// éæºäº§ç155æ¡å¹²æ°çº¿ï¼ä½¿å¾è±¡ä¸­çè®¤è¯ç ä¸æè¢«å¶å®ç¨åºæ¢æµå°
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
	    int x = random.nextInt(width);
	    int y = random.nextInt(height);
	    int xl = random.nextInt(12);
	    int yl = random.nextInt(12);
	    g.drawLine(x,y,x+xl,y+yl);
	}

	// åéæºäº§ççè®¤è¯ç (4ä½æ°å­)
	String sRand = "";
	for (int i = 0;i < 4; i++) {
		int se = random.nextInt(2);
		
		String rand;
		if(se == 0){
		     rand = String.valueOf(random.nextInt(10));
		    sRand += rand;
		}else{
			int t = random.nextInt(26);
			int st = random.nextInt(2);
			t = (t+32*st+'A');
			rand = String.valueOf((char)t);
			
			sRand+=rand;
		}
		
	    // å°è®¤è¯ç æ¾ç¤ºå°å¾è±¡ä¸­
	    // è°ç¨å½æ°åºæ¥çé¢è²ç¸åï¼å¯è½æ¯å ä¸ºç§å­å¤ªæ¥è¿ï¼æä»¥åªè½ç´æ¥çæ
	    g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 
	20 + random.nextInt(110)));
	    g.drawString(rand, 13 * i + 6, 16);
	}
	
	// å°è®¤è¯ç å­å¥SESSION
	session.setAttribute("code", sRand);
	// å¾è±¡çæ
	g.dispose();
	
	// è¾åºå¾è±¡å°é¡µé¢
	ImageIO.write(image, "JPEG", response.getOutputStream());
	
	out.clear();
	out = pageContext.pushBody();
%>
