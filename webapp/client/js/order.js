var receiverAddressObj;
var receiverNameObj;
var receiverPhoneObj;

window.onload = function() {	// 页面加载之后, 获取页面中的对象
	receiverAddressObj = document.getElementById("receiverAddress");
	receiverNameObj = document.getElementById("receiverName");
	receiverPhoneObj = document.getElementById("receiverPhone");
};

function checkOnSubmit() {			// 验证整个表单
	var receiverAddress = checkReceiverAddress();
	var receiverName = checkReceiverName();
	var receiverPhone = checkReceiverPhone();
	if(receiverAddress && receiverName && receiverPhone){
		document.getElementById("orderForm").submit();
	}else{
	   return "";
	}
}

function checkReceiverAddress() {			// 验证收货地址
	var value =receiverAddressObj.value;
	var msg = "";
	if (!value)
		msg = "收获地址必须填写";	
	receiverAddressMsg.innerHTML = msg;
	receiverAddressObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
	return msg == "";
}

function checkReceiverName() {		// 验证收货人
	var value =receiverNameObj.value;
	var msg = "";
	if (!value)
		msg = "收获人必须填写";	
	receiverNameMsg.innerHTML = msg;
	receiverNameObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
	return msg == "";
}

function checkReceiverEmail() {    // 验证邮箱
    var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;    // 邮箱正则表达式
    var value = receiverEmailObj.value;  // 获取邮箱输入框的值
    var msg = "";
    
    if (!value)
        msg = "邮箱地址必须填写";
    else if (!regex.test(value))
        msg = "邮箱地址不合法";
    
    receiverEmailMsg.innerHTML = msg;
    receiverEmailObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
    
    return msg == "";
}

