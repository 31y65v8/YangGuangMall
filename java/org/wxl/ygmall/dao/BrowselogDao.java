package org.wxl.ygmall.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.wxl.ygmall.domain.Browselog;
import org.wxl.ygmall.domain.User;
import org.wxl.ygmall.utils.DataSourceUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 浏览记录 DAO

 */
public class BrowselogDao {

    //添加浏览记录
    public void addBrowseLog(Browselog browseLog) throws SQLException {
        // SQL 插入语句
        String sql = "INSERT INTO user_browse_log ( user_id, product_id, browse_time) VALUES (?,?,?)";
     // 这里应该确保传递的参数与SQL语句的占位符一致
        Object[] params = new Object[]{
            browseLog.getuser_id(),        // 用户ID
            browseLog.getproduct_id(),    // 商品ID
            browseLog.getbrowse_time()    // 浏览时间
        };
        // 创建 QueryRunner 对象
        QueryRunner runner = new QueryRunner();
        // 执行插入操作
        runner.update(DataSourceUtils.getConnection(), sql,  browseLog.getuser_id(),
                browseLog.getproduct_id(), browseLog.getbrowse_time());
    }

  
}
