package workers;

import java.util.Vector;

public class UserAcQueue {
	
	private static UserAcQueue uaq = null;
	public Vector<Long> sleepTime = new Vector<Long>();
	public Vector<String> vid = new Vector<String>();;

	public static UserAcQueue getuaq(){
		if(uaq == null){
			uaq = new UserAcQueue();
			uaq.sleepTime.add(0l);
			uaq.vid.add("zhanwei");
		}
		return uaq;
	}
}
