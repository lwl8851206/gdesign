package gdesign.action;

import gdesign.helper.WeiboHelper;

public class UsersAction extends WeiboAction{

	private String uid;
	
	public String getUid() {
		return this.uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}	
	
	public UsersAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 显示用户的信息
	 * @return
	 */
	public String showUserInfo() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.getUserInfo(uid);
//		String result = "{\"screen_name\":\"long\",\"description\":\"一个不知道天高敌后的人\",\"province\":\"广东\","
//				+ "\"city\":\"韶关\",\"follow_me\": false, \"following\":true, \"uid\":1}";
		this.setResult(result);
		return SUCCESS;				
	}
	
}
