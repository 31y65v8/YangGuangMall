package org.wxl.ygmall.dao;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.wxl.ygmall.domain.User;
import org.wxl.ygmall.utils.DataSourceUtils;
//dao:data access object,存放所有与数据库交互的类
public class UserDao {
	// 添加用户
	public void addUser(User user) throws SQLException {
	    // 开始事务
	    DataSourceUtils.startTransaction();
	    
	    try {
	        // 检查邮箱是否已存在
	        if (isEmailExist(user.getEmail())) {
	            throw new RuntimeException("该邮箱已经被注册！");
	        }

	        // 向数据库User表插入用户数据
	        String sql = "INSERT INTO user(username, password, email, state, role, registTime) VALUES(?, ?, ?, ?, ?, ?)";
	        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	        int row = runner.update(sql, 
	                                user.getUsername(), 
	                                user.getPassword(), 
	                                user.getEmail(), 
	                                0,                    // state 默认为 0，可以用来制作用户等级（暂未实现）
	                                "用户",                // role 默认为 "用户"
	                                new java.sql.Timestamp(System.currentTimeMillis()) // registTime 默认为当前时间
	                               );

	        if (row == 0) {
	            throw new RuntimeException("用户添加失败");
	        }

	        // 提交事务
	        DataSourceUtils.releaseAndCloseConnection();
	    } catch (SQLException | RuntimeException e) {
	    	// 发生异常时回滚事务
            try {
                DataSourceUtils.rollback();  // 回滚事务
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();  // 如果回滚失败，记录错误信息
            } finally {
                // 无论如何都释放连接
                DataSourceUtils.releaseAndCloseConnection();
            }
            throw e;  // 重新抛出异常，或者处理异常信息
	    }
	}
	
	public boolean isEmailExist(String email) throws SQLException {
	    String sql = "SELECT count(*) FROM user WHERE email = ?";
	    QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	    long count = runner.query(sql, new ScalarHandler<Long>(), email);
	    return count > 0;  // 如果 count > 0，表示邮箱已经被注册
	}
	
	//根据邮箱与密码查找用户
		public User findUserByEmailAndPassword(String email, String password) throws SQLException {
			String sql="select * from user where email=? and password=?";
			System.out.println("Executing query with email: " + email + " and password: " + password);
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			User user = runner.query(sql, new BeanHandler<User>(User.class), email, password);
			System.out.println("Query result: " + user);
		    return user;
		}


}
