package gdesign.action;

import java.io.UnsupportedEncodingException;

import gdesign.helper.WeiboHelper;

public class TweetsAction extends WeiboAction{

//	private String result = null;
	private String text;
	private String mid;
	private String uid;
	private String imgPath;
	private String page;
	private String count;
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) throws UnsupportedEncodingException {
		this.text = new String(text.getBytes("ISO-8859-1"), "UTF-8");;
	}
	
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
	
	public String getImgPath() {
		return this.imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public String getPage() {
		return this.page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}	
	
	public String getCount() {
		return this.count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}	
	
	
	public TweetsAction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 发布微博
	 * @return
	 */
	public String publishTweet() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.publishTweet(text);
		this.setResult(result);
		return SUCCESS;
	}
	
	/**
	 * 删除微博
	 * @return
	 */
	public String destroyTweet() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.destroyTweet(mid);
//		String result = "{\"sign\":\"success\"}";
		this.setResult(result);
		return SUCCESS;		
	}
	
	/**
	 * 展示当前用户和其关注的用户的微博
	 * @return
	 */
	public String showAllTweets() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showAllTweets(Integer.parseInt(page), 20);
//		String result = "{\"total_number\": 3,\"statuses\":[{\"screen_name\":\"long\",\"text\":\"holy shit,today\",\"created_at\":\"sunday\",\"mid\":\"1\"}"
//				+ ",{\"screen_name\":\"中国人\",\"text\":\"今天天气不错哦\",\"created_at\":\"sunday\",\"mid\":\"2\"}"
//				+ ",{\"screen_name\":\"嘻哈军团\",\"text\":\"果然是你\",\"created_at\":\"monday\",\"mid\":\"3\"}]}";
		this.setResult(result);
		return SUCCESS;		
	}
	
	/**
	 * 展示某人的微博
	 * @return
	 */
	public String showOnesTweets() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showOnesTweets(uid, Integer.parseInt(page));
		this.setResult(result);
		return SUCCESS;			
	}
	
	

}
