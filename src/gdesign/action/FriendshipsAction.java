package gdesign.action;

import gdesign.helper.WeiboHelper;

public class FriendshipsAction extends WeiboAction{

	private String uid;
	private String page;
	
	public String getUid() {
		return this.uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}	
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public String getPage() {
		return this.page;
	}
	
	/**
	 * 关注某人
	 * @return
	 */
	public String follow() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.createFriend(uid);
		this.setResult(result);
		return SUCCESS;			
	}
	
	/**
	 * 取消关注某人
	 * @return
	 */
	public String destroyFriend() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.destroyFriend(uid);
		this.setResult(result);
		return SUCCESS;			
	}
	
	/**
	 * 获得当前用户的关注列表，单页20,默认50
	 * @return
	 */
	public String showFriends() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showFriends(uid, "20", new Integer((Integer.parseInt(page)-1) * 20).toString());
		this.setResult(result);
		return SUCCESS;			
	}
	
	/**
	 * 获得当前用户的粉丝列表，单页20,默认50
	 * @return
	 */
	public String showFollowers() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showFollowers(uid, "20", new Integer((Integer.parseInt(page)-1) * 20).toString());
		this.setResult(result);
		return SUCCESS;			
	}
	

	
	public FriendshipsAction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
