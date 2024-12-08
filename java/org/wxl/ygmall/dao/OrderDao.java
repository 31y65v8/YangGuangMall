package org.wxl.ygmall.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.wxl.ygmall.domain.Order;
import org.wxl.ygmall.domain.User;
import org.wxl.ygmall.utils.DataSourceUtils;
/**
 * 订单
 * @author admin
 *
 */
public class OrderDao {
	/**
	 *  生成订单
	 * @param order
	 * @throws SQLException
	 */
	public void addOrder(Order order) throws SQLException {
		// 获取当前时间，作为订单创建时间
	    java.util.Date now = new java.util.Date();
		// 生成Sql语句
		//String sql = "insert into orders values(?,?,?,?,?,0,?,?)";
		String sql = "insert into orders (id, price, receiverAddress, receiverName, receiverMail, paystate, ordertime, user_id) values(?,?,?,?,?,0,?,?)";
		// 生成执行sql语句的QueryRunner,不传递参数
		QueryRunner runner = new QueryRunner();
        // 执行update()方法插入数据
		runner.update(DataSourceUtils.getConnection(), sql, order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverMail(),now, order
						.getUser().getId());
	}
	
	public void updatepaytime(String orderId) throws SQLException {
		// 获取当前时间，作为订单支付时间
	    java.util.Date now = new java.util.Date();
	    String sql = "UPDATE orders SET pay_time = ? WHERE id = ?";
	    // 创建 QueryRunner 实例
        QueryRunner qr = new QueryRunner();
        
        // 执行更新操作
        qr.update(DataSourceUtils.getConnection(), sql, now, orderId);
	}
	//根据所属用户id查找订单
	public List<Order> findOrderByUser(final User user) throws SQLException {
		String sql = "select * from orders where user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("id"));
					order.setMoney(rs.getDouble("price"));
					order.setOrdertime(rs.getDate("ordertime"));
					order.setPaystate(rs.getInt("paystate"));
					order.setReceiverAddress(rs.getString("receiverAddress"));
					order.setReceiverName(rs.getString("receiverName"));
					order.setReceiverMail(rs.getString("receiverMail"));
					order.setUser(user);
					orders.add(order);
				}
				return orders;
			}
		}, user.getId());
	}
	//根据订单id查找订单
	public Order findOrderById(String id) throws SQLException {
		String sql = "select * from orders,user where orders.user_id=user.id and orders.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<Order>() {
			public Order handle(ResultSet rs) throws SQLException {
				Order order = new Order();
				while (rs.next()) {
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.price"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverMail(rs.getString("orders.receiverMail"));

					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					
					user.setPassword(rs.getString("user.password"));
					user.setRole(rs.getString("user.role"));
					
					user.setUsername(rs.getString("user.username"));
					order.setUser(user);
				}
				return order;
			}
		}, id);
	}
	//查找所有订单
	public List<Order> findAllOrder() throws SQLException {
		//创建sql
		String sql = "select orders.*,user.* from orders,user where user.id=orders.user_id order by orders.user_id";
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //返回QueryRunner对象query()方法的查询结果
		return runner.query(sql, new ResultSetHandler<List<Order>>() {			
			public List<Order> handle(ResultSet rs) throws SQLException {
				//创建订单集合
				List<Order> orders = new ArrayList<Order>();
                //循环遍历订单和用户信息
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.price"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverMail(rs.getString("orders.receiverMail"));
					orders.add(order);

					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					
					user.setPassword(rs.getString("user.password"));
					user.setRole(rs.getString("user.role"));
					
					user.setUsername(rs.getString("user.username"));
					order.setUser(user);
				}
				return orders;
			}
		});
	}
	//根据订单号修改订单状态
	public void updateOrderState(String id) throws SQLException {
		String sql = "update orders set paystate=1 where id=?";//1为已付款状态
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
		System.out.println(runner.update(sql, id));
	}
	public void updateOrderState1(String id) throws SQLException {
	    String sql = "UPDATE orders SET paystate = 2 WHERE id = ?";//2为已发货状态
	    QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	    
	    // 执行更新操作，返回受影响的行数
	    int rowsAffected = runner.update(sql, id);
	    
	    // 打印受影响的行数
	    System.out.println("受影响的行数: " + rowsAffected);
	    
	    // 如果没有任何行被更新，可以进行额外的检查
	    if (rowsAffected == 0) {
	        System.out.println("没有找到匹配的订单，或者订单状态已是目标状态");
	    }
	}

	public void updateOrderState2(String id) throws SQLException {
		String sql = "update orders set paystate=3 where id=?";//3为已收货状态
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
		System.out.println(runner.update(sql, id));
	}
	//多条件查询订单
	public List<Order> findOrderByManyCondition(String id, String receiverName)
			throws SQLException {
		//创建集合对象
		List<Object> objs = new ArrayList<Object>();
		//定义查询sql
		String sql = "select orders.*,user.* from orders,user where user.id=orders.user_id ";
		//根据参数拼接sql语句
		if (id != null && id.trim().length() > 0) {
			sql += " and orders.id=?";
			objs.add(id);
		}
		if (receiverName != null && receiverName.trim().length() > 0) {
			sql += " and receiverName=?";
			objs.add(receiverName);
		}
		sql += " order by orders.user_id";
		//创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//返回QueryRunner对象query方法的执行结果
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
               //循环遍历出订单和用户信息
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("orders.id"));
					order.setMoney(rs.getDouble("orders.price"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setPaystate(rs.getInt("orders.paystate"));
					order.setReceiverAddress(rs
							.getString("orders.receiverAddress"));
					order.setReceiverName(rs.getString("orders.receiverName"));
					order.setReceiverMail(rs.getString("orders.receiverMail"));
					orders.add(order);
					User user = new User();
					user.setId(rs.getInt("user.id"));
					user.setEmail(rs.getString("user.email"));
					
					user.setPassword(rs.getString("user.password"));
					
					user.setRole(rs.getString("user.role"));
				
					user.setUsername(rs.getString("user.username"));
					order.setUser(user);

				}

				return orders;
			}
		}, objs.toArray());
	}
	//根据id删除订单
	public void delOrderById(String id) throws SQLException {
		String sql="delete from orders where id=?";		
		QueryRunner runner = new QueryRunner();		
		runner.update(DataSourceUtils.getConnection(),sql,id);		
	}
}
