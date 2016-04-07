<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	
	<script type="text/javascript">
		function oper_save(id) {
			form1.action = "user/save/"+id;
			form1.submit();
		}
	</script>

</head>
<body>

	<form name="form1" action="" method="post" class="form-horizontal"
		role="form" onsubmit="oper_save(${model.userid});">
		<input type="hidden" name="model.userid" value="${model.userid}" />
		<table class="table">
			<% // 列表头部 %>
						<tr>
				<td>登陆名</td>
				<td>
						<input class="form-control" type="text" name="model.username" value="${model.username}" required='required' />
				</td>
			</tr>
					<tr>
				<td>真实姓名</td>
				<td>
						<input class="form-control" type="text" name="model.realname" value="${model.realname}"  />
				</td>
			</tr>
					<tr>
				<td>Email</td>
				<td>
						<input class="form-control" type="text" name="model.email" value="${model.email}"  />
				</td>
			</tr>
					<tr>
				<td>手机号</td>
				<td>
						<input class="form-control" type="text" name="model.tel" value="${model.tel}"  />
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

