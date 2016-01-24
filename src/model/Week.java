package model;

public enum Week {

	Monday("星期一"),Tuesday("星期二"),Wednsday("星期三"),Thursday("星期四"),
	Friday("星期五"),Saturday("星期六"),Sunday("星期天");
	
	private String week;
	
	private Week(String week) {
		this.week = week;
	}

	public String getWeek() {
		return week;
	}

}
