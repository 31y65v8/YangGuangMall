package org.wxl.ygmall.service;
import java.sql.SQLException;
import javax.security.auth.login.LoginException;
import org.wxl.ygmall.dao.UserDao;
import org.wxl.ygmall.domain.User;
import org.wxl.ygmall.exception.RegisterException;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
			try {
				dao.addUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RegisterException("注册失败");
			}	
	}
	
	// 登录操作
	public User login(String email, String password) throws LoginException {
		try {
			//根据登录时表单输入的邮箱和密码，查找用户
			User user = dao.findUserByEmailAndPassword(email, password);
			if (user != null) {				
					return user;
			}
			throw new LoginException("邮箱或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
}
