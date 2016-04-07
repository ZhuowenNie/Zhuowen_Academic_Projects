<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	
	<script type="text/javascript">
		function oper_save(id) {
			form1.action = "user/save_pwd/"+id;
			form1.submit();
		}
	</script>

</head>
<body>

	<form name="form1" action="" method="post" class="form-horizontal"
		role="form" onsubmit="oper_save(${model.userid});">
		<table class="table">
			<% // 列表头部 %>
		<tr>
			<td>原始密码</td>
			<td>
			<input class="form-control" type="password" name="old_password" value="" required='required' />
			</td>
		</tr>
		<tr>
			<td>新密码</td>
			<td>
			<input class="form-control" type="password" name="new_password" value="" required='required'  />
			</td>
		</tr>
		<tr>
			<td>重复密码</td>
			<td>
			<input class="form-control" type="password" name="new_password2" value="" required='required'  />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<input type="submit" value="保 存" class="btn btn-default">
			<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
			</td>
		</tr>
		</table>
	</form>

</body>
