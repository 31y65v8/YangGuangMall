<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ygmall</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<table width="900px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px"><div style="height:470px">
							<div>
								<table width="85%" border="0" cellspacing="0">
									<tr>
										<td>
											<div id="logindiv">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td style="text-align:center; padding-top:20px">
															<b>用户登录</b>
															
														</td>
													</tr>
													<tr>
														<td style="text-align:center;padding-top:20px;"><font
															color="#ff0000">${requestScope["register_message"]}</font>
														</td>
													</tr>
													<tr>
														<td style="text-align:center">
															<table width="80%" border="0" cellspacing="0"
																style="margin-top:15px ;margin-left:auto; margin-right:auto">
																<tr>
																	<td
																		style="text-align:right; padding-top:5px; width:25%">邮箱：</td>
																	<td style="text-align:left"><input name="email"
																		type="text" class="textinput" />
																	</td>
																</tr>
																<tr>
																	<td style="text-align:right; padding-top:5px">密码：</td>
																	<td style="text-align:left"><input name="password"
																		type="password" class="textinput" />
																	</td>
																</tr>
											
																<tr>
                                                                      <td colspan="2" style="padding-top:10px; text-align:center">
                                                                        <input name="image" type="submit" onclick="return formcheck()" value="登录"
                                                                       style="background-color: #FFA500; color: white; border: none; padding: 10px 20px; font-size: 16px; cursor: pointer;" />
                                                                        </td>
                                                                    </tr>


																
																
															</table>
														</td>
													</tr>
												</table>
											</div></td>
										
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
