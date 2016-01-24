package workers;

import model.User;
import service.UserService;

public class UserActMaTmp extends Thread {

	private int stime;
	private String vid;

	public UserActMaTmp(int stime, String vid) {
		this.stime = stime;
		this.vid = vid;
	}

	public void run() {
		try {
			System.out.println("我醒了，我要干活了");
			Thread.sleep(stime);
			User user = UserService.findUserByVid2(vid);

			if (user.getActived() == 0) {
				UserService.delete2(user);
				System.out.println("取消了一个待激活状态");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
