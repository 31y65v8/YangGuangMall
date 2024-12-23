package org.wxl.ygmall.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private String id; // 订单编号
	private double money; // 订单总价
	private String receiverAddress; // 送货地址
	private String receiverName; // 收货人姓名
	private String receiverMail; // 收货人邮箱
	private int paystate; // 订单状态
	private Date ordertime; // 下单时间
	private Date paytime; // 付款时间


	private User user; // 订单所属用户id

	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverPhone) {
		this.receiverMail = receiverPhone;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {//返回对象字符串表示形式
		return "Order [id=" + id + ", money=" + money + ", receiverAddress="
				+ receiverAddress + ", receiverName=" + receiverName
				+ ", receiverMail=" + receiverMail + ", paystate=" + paystate
				+ ", ordertime=" + ordertime 
				+ ", paytime=" + paytime 
				+ ", user=" + user
				+ ", orderItems=" + orderItems + "]";
	}

}
