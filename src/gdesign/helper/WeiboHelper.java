package gdesign.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Mmap;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import weibo4j.Account;
import weibo4j.Comments;
import weibo4j.Favorite;
import weibo4j.Friendships;
import weibo4j.ShortUrl;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.http.ImageItem;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Favorites;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;

public class WeiboHelper{
	

	private String token = null;
	
	private Account account = null;
	
	private Timeline timeline = null;
	
	private Comments comments = null;
	
	private Friendships friendships = null;
	
	private Users users = null;
	
	private ShortUrl shorturl = null;
	
	private Favorite favorite = null;
	
	public static Log LOG = LogFactory.getLog("gdesign.helper.WeiboHelper");
	
	/**
	 * 初始化模块
	 * @param modules
	 */
	private void initModules(String...modules) {
		
		for (String module : modules) {
			switch (module) {
				case "account" : 
					if (account == null) {
						account = new Account();
						account.setToken(token);
					};
				case "timeline" :
					if (timeline == null) {
						timeline = new Timeline();
						timeline.setToken(token);						
					};
				case "comments" :
					if (comments == null) {
						comments = new Comments();
						comments.setToken(token);						
					};				
				case "friendships" :
					if (friendships == null) {
						friendships = new Friendships();
						friendships.setToken(token);						
					};		
				case "shorturl" :
					if (shorturl == null) {
						shorturl = new ShortUrl();
						shorturl.setToken(token);						
					};		
					
				case "favorite" :
					if (favorite == null) {
						favorite = new Favorite();
						favorite.setToken(token);						
					};							
					
				case "users" :
					if (users == null) {
						users = new Users();
						users.setToken(token);						
					};	break;	
				
			}
		}
	}
	
	/**
	 * 添加必要模块,总共有'friendships' 'account' 'timeline' 'users' 'comments' 'shorurl'
	 * @param modules
	 */
	public void addModules(String...modules) {
		initModules(modules);
	}
	
	public WeiboHelper (String token) {
		this.token = token;
		this.addModules("account", "timeline", "comments", "friendships", "users", "shorturl", "favorite");
	}
	
	/**
	 * 获得当前用户和其所关注的用户的最新微博
	 * @param page
	 * @param count
	 * @return
	 */
	public String showAllTweets(int page, int count) {
		String result = null;
		Status st = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();	
		LOG.debug("you are requesting info of page" + page);
		try {
			StatusWapper sw = this.timeline.getFriendsTimeline(0, 0, new Paging(page, count));
			Iterator<Status> ite = sw.getStatuses().iterator();
			
			while (ite.hasNext()) {
				LOG.debug("getting status");
				st = ite.next();
				if (st == null) 
					continue;
				JSONObject tempSt = statusToJson(st, true);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			int len = jsonList.size(); 
			if (len != 0){
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", sw.getTotalNumber());
				jo.accumulate("next_cursor", sw.getNextCursor());
				jo.accumulate("previous_cursor", sw.getPreviousCursor());
				jo.accumulate("statuses", jsonArr);
			
				result = jo.toString();
				
				LOG.debug("result is" + result);
			}

			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * 获取某人的微博
	 * @param uid
	 * @param page
	 * @return
	 */
	public String showOnesTweets(String uid, int page) {
		String result = null;
		Status st = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();	
		
		try {
			StatusWapper sw = this.timeline.getUserTimelineByUid(uid, new Paging(page), 0, 0);
			Iterator<Status> ite = sw.getStatuses().iterator();
			
			while (ite.hasNext()) {
				st = ite.next();
				if (st == null) 
					continue;
				
				JSONObject tempSt = statusToJson(st, true);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			int len = jsonList.size(); 
			if (len != 0){
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", sw.getTotalNumber());
				jo.accumulate("next_cursor", sw.getNextCursor());
				jo.accumulate("previous_cursor", sw.getPreviousCursor());
				jo.accumulate("statuses", jsonArr);
			
				result = jo.toString();			
			}

		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取单条微博的内容
	 * @param mid
	 * @return
	 */
	public String showTweet(String mid) {
		String result = null;
		try {
			result = statusToJson(this.timeline.showStatus(mid), true).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除微博
	 * @param mid
	 * @return String
	 */
	public String destroyTweet(String mid) {
		Status st = null;
		try {
			st = this.timeline.Destroy(mid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st.toString();
	}
	
	/**
	 * 发布只带文字的微博
	 * @param text
	 * @return String
	 */
	public String publishTweet(String text) {
		Status st = null;
		try {
			st = this.timeline.UpdateStatus(text);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statusToJson(st, false).toString();
	}
	
	/**
	 * 发布带有图片和文字的微博
	 * @param text
	 * @param picPath
	 * @return
	 */
	public String publishTweet(String text, String picPath) {
		Status st = null;
		try {
			try {
				byte[] content = readFileImage(picPath);
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(text, "utf-8");
				st = this.timeline.UploadStatus(s, pic);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception ioe) {
			System.out.println("Failed to read the system input.");
		}		
		
		return statusToJson(st, false).toString();
	}
	
	
	public String publishTweet(String text, File f) {
		Status st = null;
		try {
			try {
				byte[] content = readFileImage(f);
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(text, "utf-8");
				st = this.timeline.UploadStatus(s, pic);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception ioe) {
			System.out.println("Failed to read the system input.");
		}		
		
		return statusToJson(st, false).toString();
	}
	
	/**
	 * 转发微博
	 * @param mid
	 * @param text
	 * @return
	 */
	public String repostTweet(String mid, String text) {
		String result = null;
		try {
			result = statusToJson(this.timeline.Repost(mid, text, 0), true).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 收藏某条微博
	 * @param mid
	 * @return
	 */
	public String createFavorite(String mid) {
		String result = null;
		try {
			Favorites favorites = this.favorite.createFavorites(mid);
			result = this.statusToJson(favorites.getStatus(), true).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 取消收藏微博
	 * @param mid
	 * @return
	 */
	public String destroyFavorite(String mid) {
		String result = null;
		try {
			Favorites favorites = this.favorite.destroyFavorites(mid);
			result = this.statusToJson(favorites.getStatus(), true).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;	
	}
	
	/**
	 * 显示改用户的收藏微博
	 * @param page 页数，单页条数默认50
	 * @return
	 */
	public String showMyFavorites(String page) {
		String result = null;
		List<Favorites> arr = new ArrayList<Favorites>();
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		Favorites fav = null;
		int totalNumber = 0;
		
		try {
			
			arr = this.favorite.getFavorites(new Paging(Integer.parseInt(page)));
			Iterator<Favorites> ite = arr.iterator();
			
			while (ite.hasNext()) {
				fav = ite.next();
				totalNumber = fav.getTotalNumber();
				if (fav == null) 
					continue;
				
				JSONObject tempST = statusToJson(fav.getStatus(), true);
//				tempST.accumulate("total_number", fav.getTotalNumber());
				jsonList.add(tempST);
			}
			
			int len = jsonList.size(); 
			if (len != 0) {
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", totalNumber);
				jo.accumulate("statuses", jsonArr);
			
				result = jo.toString();	
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;			
	}
	
	
	
	/**
	 * 关注某人
	 * @param screenName
	 * @return String
	 */
	public String createFriend(String uid) {
		User user = null;
		try {
			user = this.friendships.createFriendshipsById(uid);
			LOG.debug("following : " + user.isFollowing());
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userToJson(user).toString();
	}
	
	/**
	 * 取消关注某人
	 * @param screenName
	 * @return String
	 */
	public String destroyFriend(String uid) {
		User user = null;
		try {
			user = this.friendships.destroyFriendshipsDestroyById(uid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userToJson(user).toString();
	}
	
	
	/**
	 * 获得当前登陆用户的关注列表
	 * @param uid
	 * @param count
	 * @param cursor
	 * @return
	 */
	public String showFriends(String uid, String count, String cursor) {
		String result = null;
		Object arr = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();	
		User user = null;
		try {
//			UserWapper uw= this.friendships.getFriendsIdsByUid(uid, new Integer(count), new Integer(cursor));
			UserWapper uw = this.friendships.getFriendsByID(uid, new Integer(count), new Integer(cursor));
			
			Iterator<User> ite = uw.getUsers().iterator();
			
			while (ite.hasNext()) {
				user = ite.next();
				if (user == null) 
					continue;
				
				JSONObject tempSt = userToJson(user);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			
			int len = jsonList.size(); 
			if (len != 0){
				
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", uw.getTotalNumber());
				jo.accumulate("next_cursor", uw.getNextCursor());
				jo.accumulate("previous_cursor", uw.getPreviousCursor());
				jo.accumulate("users", jsonArr);
				
				result = jo.toString();
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;			
	}
	
	/**
	 * 获得当前登陆用户的粉丝列表
	 * @param uid
	 * @param count
	 * @param cursor
	 * @return
	 */
	public String showFollowers(String uid, String count, String cursor) {
		String result = null;
		Object arr = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();	
		User user = null;
		try {
			UserWapper uw= this.friendships.getFollowersById(uid, new Integer(count), new Integer(cursor));
			Iterator<User> ite = uw.getUsers().iterator();
			
			while (ite.hasNext()) {
				user = ite.next();
				if (user == null) 
					continue;
				
				JSONObject tempSt = userToJson(user);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			
			int len = jsonList.size(); 
			if (len != 0){
				
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", uw.getTotalNumber());
				jo.accumulate("next_cursor", uw.getNextCursor());
				jo.accumulate("previous_cursor", uw.getPreviousCursor());
				jo.accumulate("users", jsonArr);
				
				result = jo.toString();
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
		
		
	}
	
	
	/**
	 * 返回某篇微博的评论列表
	 * @param mid
	 * @param page
	 * @param count
	 * @return String
	 */
	public String showComments(String mid, int page, int count) {
		String result = null;
		Comment c = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		try {
			CommentWapper cw = this.comments.getCommentById(mid, new Paging(page, count), 0);
			Iterator<Comment> ite = cw.getComments().iterator();
			
			while (ite.hasNext()) {
				c = ite.next();
				if (c == null) 
					continue;
				JSONObject tempSt = commentToJson(c, true);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			int len = jsonList.size(); 
			if (len != 0){
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", cw.getTotalNumber());
				jo.accumulate("next_cursor", cw.getNextCursor());
				jo.accumulate("previous_cursor", cw.getPreviousCursor());
				
				jo.accumulate("comments", jsonArr);
			
				result = jo.toString();
				
			}

			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * 评论某微博
	 * @param mid
	 * @param comment
	 * @return string
	 */
	public String createComment(String mid, String comment) {
		Comment c = null;
		try {
			c = this.comments.createComment(comment, mid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentToJson(c, true).toString();
	}
	
	/**
	 * 回复评论
	 * @param cid
	 * @param mid
	 * @param comment
	 * @return String
	 */
	public String replyComment(String cid, String mid, String comment) {
		Comment c = null;
		try {
			c = this.comments.replyComment(cid, mid, comment);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentToJson(c, true).toString();
	}
	
	
	/**
	 * 删除评论
	 * @param mid
	 * @return String
	 */
	public String destroyComment(String cid) {
		Comment c = null;
		try {
			c = this.comments.destroyComment(cid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentToJson(c, false).toString();
	}
	
	/**
	 * 显示用户发出的评论
	 * @param page
	 * @param count
	 * @return
	 */
	public String showIncomments(String page, String count) {
		String result = null;
		Comment c = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		try {
			CommentWapper cw = this.comments.getCommentByMe(new Paging(Integer.parseInt(page), Integer.parseInt(count)), 0);
			Iterator<Comment> ite = cw.getComments().iterator();
			
			while (ite.hasNext()) {
				c = ite.next();
				if (c == null) 
					continue;
				JSONObject tempSt = commentToJson(c, true);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			int len = jsonList.size(); 
			if (len != 0){
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", cw.getTotalNumber());
				jo.accumulate("next_cursor", cw.getNextCursor());
				jo.accumulate("previous_cursor", cw.getPreviousCursor());
				
				jo.accumulate("comments", jsonArr);
			
				result = jo.toString();
				
			}

			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;		
	}
	
	/**
	 * 显示用户收到的评论
	 * @param page
	 * @param count
	 * @return
	 */
	public String showOutcomments(String page, String count) {
		String result = null;
		Comment c = null;
		ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		try {
			CommentWapper cw = this.comments.getCommentToMe(new Paging(Integer.parseInt(page), Integer.parseInt(count)), 0, 0);
			Iterator<Comment> ite = cw.getComments().iterator();
			
			while (ite.hasNext()) {
				c = ite.next();
				if (c == null) 
					continue;
				JSONObject tempSt = commentToJson(c, true);
//				tempSt.accumulate("total_number", sw.getTotalNumber());
				jsonList.add(tempSt);
			}
			
			int len = jsonList.size(); 
			if (len != 0){
				net.sf.json.JSONArray jsonArr = new net.sf.json.JSONArray();
				
				for (int i = 0; i < len; i++)
					jsonArr.add(jsonList.get(i));
				
				JSONObject jo = new JSONObject();
				jo.accumulate("total_number", cw.getTotalNumber());
				jo.accumulate("next_cursor", cw.getNextCursor());
				jo.accumulate("previous_cursor", cw.getPreviousCursor());
				
				jo.accumulate("comments", jsonArr);
			
				result = jo.toString();
				
			}

			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;		
	}	
	
	
	/**
	 * 获取某个用户的信息
	 * @param uid
	 * @return
	 */
	public String getUserInfo(String uid) {
		String result = null;
		try {
			result = this.userToJson(this.users.showUserById(uid)).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * 短链接转长链接
	 * @param shortUrl
	 * @return
	 */
	public String urlShortToLong(String shortUrl) {
		String result = null;
		try {
			result = this.shorturl.shortToLongUrl(shortUrl).toString();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 * 将status转成json格式
	 * @param st
	 * @param ifFirstTime
	 * @return JSONObject
	 */
	public static JSONObject statusToJson(Status st, boolean isFirstTime) {
		JSONObject jsonObj = null;
		HashMap map = new HashMap();
		Date d = st.getCreatedAt();
        java.text.DateFormat format1 = new java.text.SimpleDateFormat(  
                "yyyy-MM-dd hh:mm:ss");  
        String created_at = format1.format(d);  
		map.put("created_at", created_at);		
		map.put("screen_name", st.getUser().getScreenName());
		map.put("text", new LinkDeal(st.getText()).dealLinksInText());
		map.put("mid", st.getMid());
		map.put("reposts_count", st.getRepostsCount());
		map.put("comments_count", st.getCommentsCount());
		map.put("attitudes_count", "1");
		map.put("favorited", st.isFavorited());
		map.put("geo", st.getGeo());
		
		map.put("user", userToJson(st.getUser()));
		JSONArray jArr = st.getPicUrls();

		if(jArr.length() != 0)
			map.put("pic_urls", jArr.toString());
		
		Status stt = st.getRetweetedStatus();
		if (isFirstTime)
			if (stt != null) {
				if (st.getRetweetedStatus().getUser() != null) {
					LOG.debug("starting to get retweet");
					map.put("retweeted_status", statusToJson(stt, false));
					LOG.debug("got retweet");
				}
					
				
			}
		jsonObj = JSONObject.fromObject(map);
		
		return jsonObj;
	}
	
	/**
	 * 将user转成json格式
	 * @param u
	 * @return JSONObject
	 */
	public static JSONObject userToJson(User u) {
		JSONObject jsonObj = null;
		HashMap map = new HashMap();
		Date d = u.getCreatedAt();
        java.text.DateFormat format1 = new java.text.SimpleDateFormat(  
                "yyyy-MM-dd hh:mm:ss");  
        String created_at = format1.format(d);  
		map.put("created_at", created_at);
		map.put("id", u.getId());
		map.put("screen_name", u.getScreenName());
		map.put("gender", u.getGender());
		map.put("profile_image_url", u.getProfileImageUrl());
		map.put("province", u.getProvince());
		map.put("city", u.getCity());
		map.put("description", u.getDescription());
		map.put("followers_count", u.getFollowersCount());
		map.put("friends_count", u.getFriendsCount());
		map.put("statuses_count", u.getStatusesCount());
		map.put("follow_me", u.isfollowMe());
		map.put("online_status", u.getonlineStatus());
		map.put("following", u.isFollowing());
		jsonObj =JSONObject.fromObject(map);
		return jsonObj;
	}
	
	/**
	 * 将comment转换成json格式
	 * @param c
	 * @param isFirstTime
	 * @return
	 */
	public static JSONObject commentToJson(Comment c, boolean isFirstTime) {
		JSONObject jsonObj = null;
		HashMap map = new HashMap();
		Date d = c.getCreatedAt();
        java.text.DateFormat format1 = new java.text.SimpleDateFormat(  
                "yyyy-MM-dd hh:mm:ss");  
        String created_at = format1.format(d);  
		map.put("created_at", created_at);
		Status st = c.getStatus();
		if (st != null) {
			map.put("status", statusToJson(c.getStatus(), false));
		}
		map.put("id", c.getId());
		map.put("text", c.getText());
		map.put("source", c.getSource());
//		LOG.debug("status in comment is" + c.getStatus());
		map.put("user", userToJson(c.getUser()));
		map.put("mid", c.getMid());
		if (isFirstTime)
			if (c.getReplycomment() != null)
				map.put("reply_comment", commentToJson(c.getReplycomment(), false));
		jsonObj = JSONObject.fromObject(map);
		return jsonObj;		
	}
	
	
	static class LinkDeal {
		/**
		 * 对文本中的http链接文本和@生成对应的链接
		 * 
		 * 
		 */
		private String text;
		
		public LinkDeal(String text) {
			this.text = text;
		}
		
		public String dealLinksInText() {
			String temp =  this.text.replaceAll("(http://t\\.cn/\\w+)", "<a href=\"$1\" target=\"_blank\">$1</a>");
			this.text = temp;
			return this.text;
		}
	
	}
	

	
	/**
	 * 将图片转成二进制流
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}	
	
	public static byte[] readFileImage(File f) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(f));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;		
	}
	
	
	
	

	
	
	

	
}
