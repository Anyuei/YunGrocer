package interceptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * ForceLoginInterceptor.java
 * @author anyunpei
 *2018��9��25������6:39:23
 * ǿ�Ƶ�¼Structs2������
 */
public class ForceLoginInterceptor extends DestoryAndInitInterceptor{
	/**
	 * ��鱾�λỰ���û����Ƿ�Ϊ��
	 */
	public String intercept(ActionInvocation ai) throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		Object usernameLog = vs.findValue("#session.usernameLog");
		if (usernameLog == null) {
			return "notLogin";//δ��¼
		} else {
			ai.invoke();
			return "isLogin";//�Ѿ���¼
		}
	}
}
