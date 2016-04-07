<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	<%@include file="/static/include/my97.jsp"%>
	
<script type="text/javascript">
	function oper_save() {
		form1.action = "dict/save_dict.htm?id="+form1["model.dict_id"].value;
		form1.submit();
	}
	
	function oper_del() {
		form1.action = "dict/delete_dict.htm?id="+form1["model.dict_id"].value;
		form1.submit();
	}
</script>
</head>

<body>
	<form name="form1" action="" method="post" class="form-horizontal"
		role="form">
		<input type="hidden" name="model.dict_id" value="${item.dict_id}" />
		<div id="tools">
			<span class="new_page">${item.dict_id>0?'类型修改':'类型添加'}</span>
		</div>
		<!-- 数据列表 -->
		<div class="table">
			<table>
				<tr>
					<td>类型</td>
					<td>
						<input class="form-control" type="text" name="model.dict_type"  ${item.dict_id>0?'readonly="readonly"':''} value="${item.dict_type}" />
					</td>
				</tr>
				<tr>
					<td>名称</td>
					<td><input class="form-control" type="text" name="model.dict_name" value="${item.dict_name}" /></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><input class="form-control" type="text" name="model.dict_remark" value="${item.dict_remark}" /></td>
				</tr>
			</table>
		</div>
		<div id="bottom">
			<input type="button" class="btn1" value="保 存" onclick="oper_save();" />
			<c:if test="${item.dict_id>0 }">
				<input type="button" class="btn1" value="删 除" onclick="oper_del();" />
			</c:if>
			<input type="button" class="btn1" value="关 闭" onclick="closeIframe();" />
		</div>
	</form>
</body>