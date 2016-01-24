package workers;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.User;
import service.UserService;


public class UserActMa extends Thread{
	
	private static UserActMa uam = null;
	private UserAcQueue uaq;
	private int i = 1;
	private long gap = 1000*60*3l;
	
	public UserActMa(){
		uaq = UserAcQueue.getuaq();
	}
	
	public void run(){
		while(true){
			try{
				System.out.println("我醒了，我要干活了");
				
				if(uaq.sleepTime.get(i)-uaq.sleepTime.get(i-1)<gap)
					Thread.sleep(uaq.sleepTime.get(i)-uaq.sleepTime.get(i-1));
				else
					Thread.sleep(gap);
				System.out.println();
				
				System.out.println(uaq.vid.get(i));
				User user = UserService.findUserByVid2(uaq.vid.get(i));
				if(user != null){
					System.out.println(user.getUsername());
				}
				if(user.getActived()==0){
					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
					System.out.println(user.getUsername()+sdf.format(new Date()));
					
					UserService.delete2(user);
					System.out.println("取消了一个待激活状态");
				}
				i++;
				
			}catch(Exception e){
				
				System.out.println("没人了...自己睡");
				//处理超过2000个任务时候清空
				if(i>2000){
					uaq.sleepTime.clear();
					uaq.vid.clear();
					uaq.sleepTime.add(0l);
					uaq.vid.add("zhanwei");
					i=1;
				}
				try {
					
					synchronized(uaq){ 
						uaq.wait();
						System.out.println("来个个客人，我被唤醒了");
					}
					
					
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static UserActMa getUserActMa(){
		if(uam == null)
			uam = new UserActMa();
		return uam;
	}
}
