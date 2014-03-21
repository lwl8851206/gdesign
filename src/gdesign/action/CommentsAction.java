package gdesign.action;

import java.io.UnsupportedEncodingException;

import gdesign.helper.WeiboHelper;

public class CommentsAction extends WeiboAction{
	
	private String comment;
	private String uid;
	private String mid;
	private String cid;
	private String count;
	private String page;
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) throws UnsupportedEncodingException {
		this.comment = new String(comment.getBytes("ISO-8859-1"), "UTF-8");
	}	
	
	public String getCid() {
		return this.cid;
	}
	
	public void setCid(String cid) {
		this.cid = cid;
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
	
	public CommentsAction() {
		super();
	}
	
	/**
	 * 评论微博
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String createComment() throws UnsupportedEncodingException {
		WeiboHelper wh = this.getWeiboHelper();
		
		String result = wh.createComment(mid, comment);
		this.setResult(result);
		return SUCCESS;		
	}
	
	/**
	 * 删除评论
	 * @return
	 */
	public String destroyComment() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.destroyComment(cid);
		this.setResult(result);
		return SUCCESS;			
	}
	
	/**
	 * 展示微博的评论
	 * @return
	 */
	public String showComments() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.showComments(mid, Integer.parseInt(page), Integer.parseInt(count));
		this.setResult(result);
		return SUCCESS;	
	}
	
	/**
	 * 回复评论
	 * @return
	 */
	public String replyComment() {
		WeiboHelper wh = this.getWeiboHelper();
		String result = wh.replyComment(cid, mid, comment);
		this.setResult(result);
		return SUCCESS;	
	}
}
