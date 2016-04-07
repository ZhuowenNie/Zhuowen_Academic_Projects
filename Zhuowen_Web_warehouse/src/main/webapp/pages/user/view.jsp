<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
</head>
<body>
	<form name="form1" action="" method="post" class="form-horizontal" role="form">
		<!-- 数据列表 -->
		<table class="table">
			<%  // 列表头部 %>
				<tr>
		<td>登陆名</td>
		<td>${model.username}</td>
	</tr>
	<tr>
		<td>真实姓名</td>
		<td>${model.realname}</td>
	<tr>
		<td>密码</td>
		<td>${model.password}</td>
	</tr>
	<tr>
		<td>Email</td>
		<td>${model.email}</td>
	</tr>
	<tr>
		<td>手机号</td>
		<td>${model.tel}</td>
	</tr>

			
			<tr>
				<td colspan="2" align="center">
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>

</body>
