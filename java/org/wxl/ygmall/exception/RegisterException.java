package org.wxl.ygmall.exception;

//注册用户
public class RegisterException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegisterException() {
		super();//调用Exception的无参构造函数
		// TODO Auto-generated constructor stub
	}

	public RegisterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
		//message-错误消息 cause-根本原因
	}

	public RegisterException(String message) {
		super(message);// 调用父类 Exception 的构造函数，传递错误消息
		// TODO Auto-generated constructor stub
	}

	public RegisterException(Throwable cause) {
		super(cause); // 调用父类 Exception 的构造函数，传递错误消息和根本原因
		// TODO Auto-generated constructor stub
	}

}
/*为什么使用 super()？
初始化父类状态：当创建子类对象时，必须首先初始化父类的状态。通过 super() 调用父类构造函数，可以确保父类的属性被正确设置。
构造函数链：Java 支持构造函数的链式调用。通过 super()，可以在子类构造函数中控制父类的初始化过程。
错误处理：在自定义异常类中使用 super(message) 可以将错误消息传递给标准异常类，使得在捕获和处理异常时可以更容易地获取相关信息。
*/