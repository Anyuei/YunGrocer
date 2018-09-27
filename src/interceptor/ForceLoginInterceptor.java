package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * ForceLoginInterceptor.java
 * @author anyunpei
 *2018年9月25日下午6:39:23
 * 强制登录Structs2拦截器
 */
public class ForceLoginInterceptor extends DestoryAndInitInterceptor{
	/**
	 * 检查本次会话的用户名是否为空
	 */
	public String intercept(ActionInvocation ai) throws Exception {
		ValueStack vs = ai.getStack();
		Object usernameLog = vs.findValue("#session.username");
		System.out.println(usernameLog);
		System.out.println("拦截器已经运行");
		if (usernameLog == null) {
			return "notLogin";//未登录
		} else {
			ai.invoke();
			return null;//已经登录
		}
	}
}
