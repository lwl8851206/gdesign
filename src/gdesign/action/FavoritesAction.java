package gdesign.action;

import gdesign.helper.WeiboHelper;

public class FavoritesAction extends WeiboAction{

	private String uid;
	private String mid;
	private String page;
	
	public String getMid() {
		return this.mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}	
	
	public String getPage() {
		return this.page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}	
	
	/**
	 * 收藏微博
	 * @return
	 */
	public String createFavorite() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.createFavorite(mid);
		this.setResult(result);
		return SUCCESS;				
	}
	
	/**
	 * 删除收藏的微博
	 * @return
	 */
	public String destroyFavorite() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.destroyFavorite(mid);
		this.setResult(result);
		return SUCCESS;		
	}
	
	/**
	 * 显示该用户的收藏微博
	 * @return
	 */
	public String showFavorites() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showMyFavorites(page);
		this.setResult(result);
		return SUCCESS;		
	}
	
	public FavoritesAction() {
		super();
	}
}
