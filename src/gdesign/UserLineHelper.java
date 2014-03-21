package gdesign;

import java.util.Iterator;
import java.util.List;

import weibo4j.Timeline;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import com.opensymphony.xwork2.ActionSupport;

public class UserLineHelper extends ActionSupport{
	
	private String name;
	private String result;
	private String token;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
	
	
	public String execute() throws WeiboException {
//		Timeline tm = new Timeline();
//		tm.setToken(this.token);
//		StatusWapper sw = tm.getFriendsTimeline(0, 0, new Paging(1, 50));
//		List<Status> sts = sw.getStatuses();
//		Iterator<Status> ite = sts.iterator();
//		while (ite.hasNext()) {
//			Status st = ite.next();
//			this.result += st.toString();
//		}
//		this.result += "<br>totol num: " + sts.size(); 

		return SUCCESS;
		
	}
	
	public String getResult() {
		return this.result;
	}
	
//	public String getData() {
//		WeiboHelper wh = new WeiboHelper(this.token);
//		result = wh.showAllTweets(1, 10);
//		return SUCCESS;
//	}
	

}
