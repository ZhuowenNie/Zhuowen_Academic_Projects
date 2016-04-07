<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	
	<script type="text/javascript">
		function oper_save(id) {
			if($('[name="model.name"]').val()=='') {
				Alert('请输入名称');
				return false;
			}
			
			form1.action = "goods/save.htm?id="+id;
			form1.submit();
			return true;
		}
		
	</script>

</head>
<body>

	<form name="form1" action="" method="post" class="form-horizontal"
		role="form" onsubmit="return oper_save(${model.id});">
		<input type="hidden" name="model.id" value="${model.id}" />
		<table class="table">
			<% // 列表头部 %>
						<tr>
				<td>名称</td>
				<td>
						<input class="form-control" type="text" name="name" value="${model.name}" required='required' />
				</td>
			</tr>
			<tr>
				<td>种类</td>
				<td>
					<select name="type" class="form-control" >
						${goodsType}
					</select>
				</td>
			</tr>
					<tr>
				<td>说明</td>
				<td>
					<textarea class="form-control" rows="3" cols="30" name="content">${model.content}</textarea>
				</td>
			</tr>
		 
			 
			<tr>
				<td></td>
				<td>
				<input type="submit" value="保 存" class="btn btn-default">
				<button class="btn btn-default" onclick="closeIframe();return false;">关 闭</button>
				</td>
			</tr>
		</table>
	</form>

</body>

