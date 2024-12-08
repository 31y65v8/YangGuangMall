package org.wxl.ygmall.web.servlet.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.wxl.ygmall.domain.Product;
import org.wxl.ygmall.exception.FindProductByIdException;
import org.wxl.ygmall.service.ProductService;

public class AddCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 

        // 设置响应类型为 JSON
        response.setContentType("application/json;charset=UTF-8");
        
        // 获取商品ID
        String id = request.getParameter("id");
        
        // 创建响应的Map
        Map<String, Object> result = new HashMap<>();
        
        
        
        // 调用service层方法，根据id查找商品
        ProductService service = new ProductService();
        try {
            Product p = service.findProductById(id);
            // 获取session对象
            HttpSession session = request.getSession();

            // 检查用户是否已登录
            if (session.getAttribute("user") == null) {
                // 用户未登录
                result.put("success", false);
                result.put("message", "请先登录！");
                // 返回JSON响应
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(result));
                return;
            }
            
            // 从session中获取购物车对象
            Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            // 如果购物车为null,说明没有商品存储在购物车中，创建一个购物车
            if (cart == null) {
                cart = new HashMap<Product, Integer>();
            }
            // 向购物车中添加商品
            Integer count = cart.put(p, 1);
            // 如果商品数量不为空，则商品数量+1，否则添加新的商品信息
            if (count != null) {
                cart.put(p, count + 1);
            }
            session.setAttribute("cart", cart);

            // 设置成功响应
            result.put("success", true);
            result.put("message", "商品已成功加入购物车！");
        } catch (FindProductByIdException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "添加商品到购物车失败，请稍后重试！");
        }

        // 返回JSON响应
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
