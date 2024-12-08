<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
//鼠标点击搜索框时执行
function my_click(obj, myid){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
	  document.getElementById(myid).value = '';
	  obj.style.color='#000';
	}
}
//鼠标不聚焦在搜索框时执行
function my_blur(obj, myid){
	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == ''){
	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
	 obj.style.color='#999';
 }
}

/**
 * 点击搜索按钮执行的函数
 */
function search(){
	document.getElementById("searchform").submit();
}
</script>
<div id="divmenu">
		<a href="${pageContext.request.contextPath}/showProductByPage?category=日用品">日用品</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=服饰">服饰</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=数码产品">数码产品</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=图书">图书</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=食品">食品</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=公仔玩具">公仔玩具</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=家用电器">家用电器</a> 
		
		<a href="${pageContext.request.contextPath}/ShowIndexServlet" style="color:#FFA500">首页</a>
	
</div>
<div id="divsearch">
<form action="${pageContext.request.contextPath }/MenuSearchServlet" id="searchform">
	<table width="100%" border="0" cellspacing="0">
		<tr>
			<td style="text-align:right; padding-right:220px">
				Search 
				<input type="text" name="textfield" class="inputtable" id="textfield" value="请输入商品"
				onmouseover="this.focus();"
				onclick="my_click(this, 'textfield');"
				onBlur="my_blur(this, 'textfield');"/> 
				<button id="searchButton" onclick="search()">搜索</button>

			</td>
		</tr>
	</table>
</form>
</div>