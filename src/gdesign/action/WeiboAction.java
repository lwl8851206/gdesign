package gdesign.action;

import gdesign.helper.WeiboHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class WeiboAction extends ActionSupport{
	private HttpServletRequest req;
	private HttpServletResponse res;
	private WeiboHelper weiboHelper = null;
	private String result = null;
	private String token;
	
	public WeiboAction() {
		req = ServletActionContext.getRequest();
		res = ServletActionContext.getResponse();
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public HttpServletRequest getReq() {
		return this.req;
	}
	
	public HttpServletResponse getRes() {
		return this.res;
	}
	
/*	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return this.user;
	}*/
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void initWeiboHelper() {
		
		this.weiboHelper = new WeiboHelper(this.token);
		
	}
	
	public WeiboHelper getWeiboHelper() {
		if (this.weiboHelper == null)
			this.initWeiboHelper();
		return this.weiboHelper;
	}
	
	
//	public String showTweets() {
//		WeiboHelper wh = new WeiboHelper(this.token);
//		result = wh.showAllTweets(1, 10);
//		return SUCCESS;
//	}
	
//	public String execute() {
//		return SUCCESS;
//	}
}
