package org.wxl.ygmall.service;

import java.sql.SQLException;
import java.util.List;
import org.wxl.ygmall.dao.ProductDao;
import org.wxl.ygmall.domain.PageBean;
import org.wxl.ygmall.domain.Product;
import org.wxl.ygmall.exception.AddProductException;
import org.wxl.ygmall.exception.FindProductByIdException;
import org.wxl.ygmall.exception.ListProductException;
public class ProductService {
	private ProductDao dao = new ProductDao();
	// 添加商品
	public void addProduct(Product p) throws AddProductException {
		try {
			dao.addProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddProductException("添加商品失败");
		}
	}
	// 查找所有商品信息
	public List<Product> listAll() throws ListProductException {
		try {
			return dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("查询商品失败");
		}
	}
	// 分页操作
	public PageBean findProductByPage(int currentPage, int currentCount,
			String category) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);
		// 封装当前查找类别
		bean.setCategory(category);
		try {
			// 获取总条数
			int totalCount = dao.findAllCount(category);
			bean.setTotalCount(totalCount);
			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);
			// 获取当前页数据
			List<Product> ps = dao.findByPage(currentPage, currentCount,
					category);
			bean.setPs(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	// 根据id查找商品
	public Product findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindProductByIdException("根据ID查找商品失败");
		}
	}
	
	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice) {
		List<Product> ps = null;
		try {
			ps = dao.findProductByManyCondition(id, name, category, minprice,
					maxprice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	// 修改商品信息
	public void editProduct(Product p) {
		try {
			dao.editProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//前台，用于搜索框根据商品名来模糊查询相应的商品
	public PageBean findgoodsByName(int currentPage, int currentCount,
			String searchfield) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);
		// 封装模糊查询的商品名
		bean.setSearchfield(searchfield);
		try {
			// 获取总条数
			int totalCount = dao.findgoodsByNameAllCount(searchfield);
			bean.setTotalCount(totalCount);

			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);

			//满足条件的商品
			List<Product> ps = dao.findgoodsByName(currentPage,currentCount,searchfield);
			bean.setPs(ps);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("前台搜索框：根据商品名查询商品失败");
		}
	}
	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) {
		try {
			dao.deleteProduct(id);
		} catch (SQLException e) {
			throw new RuntimeException("后台系统：根据id删除商品信息失败！");
		}
	}
}
