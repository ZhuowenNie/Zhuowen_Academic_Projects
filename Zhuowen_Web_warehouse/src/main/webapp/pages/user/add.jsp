<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	
	<script type="text/javascript">
		function oper_save() {
			form1.submit();
			return true;
		}
	</script>

</head>
<body>

	<form name="form1" action="user/save.htm" method="post" class="form-horizontal" >
		<input type="hidden" name="userid" value="0" />
		<table class="table">
			<% // 列表头部 %>
			<tr>
				<td>登陆名</td>
				<td>
				<input class="form-control" type="text" name="username" value="" required='required' />
				</td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td>
				<input class="form-control" type="text" name="realname" value=""  />
				</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
				<input class="form-control" type="text" name="email" value="${model.email }"  />
				</td>
			</tr>
			<tr>
				<td>手机号</td>
				<td>
				<input class="form-control" type="text" name="tel" value="${model.tel }"  />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<button class="btn btn-default" onclick="return oper_save();">保 存</button>
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>

</body>
