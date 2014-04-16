package gdesign.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

public class OauthAction extends WeiboAction{
	private String code;
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}

	public OauthAction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 认证
	 * @return
	 */
	public String beginOauth() {
      Oauth oauth = new Oauth();
    
      String accessToken = null;
      HttpSession session = this.getReq().getSession();
      try {
    	  accessToken = oauth.getAccessTokenByCode(code).getAccessToken();
    	  System.out.println("========================accessToken:" + accessToken);
    	  this.setToken(accessToken);
    	  HashMap account = this.getWeiboHelper().getAccount();
    	  session.setAttribute("uid", account.get("uid"));
    	  session.setAttribute("screen_name", account.get("screen_name"));
    	  session.setAttribute("token", accessToken);
    	  
      } catch (WeiboException e) {
		// TODO Auto-generated catch block
    	  e.printStackTrace();
      }
      
      return SUCCESS;
	}
	
	/**
	 * 销毁session
	 * @return
	 */
	public String destroyOauth() {
		HttpSession session = this.getReq().getSession();
/*		Oauth oauth = new Oauth();
		try {
			oauth.revokeOauth(session.getAttribute("token").toString());
		} catch (JSONException | WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String preUrl = "https://api.weibo.com/oauth2/revokeoauth2?access_token=";
		URLConnection conn = null;
		InputStream in = null;
		byte[] bt = new byte[1024];
		try {
			conn = new URL(preUrl + session.getAttribute("token").toString()).openConnection();
			in = conn.getInputStream();
			int len = in.read(bt);
			String str = new String(bt, 0, len);
			if (str.contains("true"))
				session.invalidate();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		
		
		return SUCCESS;
	}
	
	

}
