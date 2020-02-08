package edu.uestc.blog.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.utils.Const;

public class MyRealm extends AuthorizingRealm {
	
	@Resource
	private BloggerService bloggerService ;
	
	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}
	
	/**
	 * 登录验证
	 * token:令牌，基于用户名和密码的令牌
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.从令牌中取出用户名
		String username = (String) token.getPrincipal() ;
		//2.让Shiro去验证账号密码
		Blogger blogger = bloggerService.login(username) ;
		if(blogger != null) {
			//如果查询到了用户，将其放到session中
			SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
			AuthenticationInfo authenInfo = new SimpleAuthenticationInfo(blogger.getUsername(),blogger.getPassword(),getName()) ;
			return authenInfo ;
		}
		return null;
	}

}
