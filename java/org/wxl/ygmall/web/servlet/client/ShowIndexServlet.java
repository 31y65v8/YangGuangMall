package org.wxl.ygmall.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wxl.ygmall.domain.Product;
import org.wxl.ygmall.exception.ListProductException;
import org.wxl.ygmall.service.ProductService;

/**展示全部商品*/
public class ShowIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 处理 GET 请求
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    // 处理 POST 请求
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 创建 ProductService 实例
        ProductService productService = new ProductService();
        List<Product> products = null;

        try {
            // 获取所有商品列表
            products = productService.listAll();
        } catch (ListProductException e) {
            // 捕获 ListProductException 异常，记录日志并设置错误信息
            e.printStackTrace();
            req.setAttribute("errorMessage", "查询商品失败，请稍后重试！");
            // 转发到错误页面（例如 error.jsp）
            req.getRequestDispatcher("/client/error.jsp").forward(req, resp);
            return;  // 终止执行后续代码，避免错误数据被传递
        }

        // 将商品列表添加到请求属性中
        req.setAttribute("products", products);

        // 请求转发到 index.jsp 页面
        req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
    }
}
