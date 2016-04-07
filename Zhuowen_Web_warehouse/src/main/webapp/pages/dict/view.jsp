<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
</head>

<body>
	<form name="form1" action="" method="post"  class="form-horizontal"
		role="form">
		<!-- 数据列表 -->
			<table class="table" >
				<tr>
					<td>类型</td>
					<td>
						<input type="hidden" name="dictType" value="${item.dictType}" />
						<select  class="form-control" disabled="disabled">
							${optionList }
						</select>
					</td>
				</tr>
				<tr>
					<td>名称</td>
					<td>${item.detailName}</td>
				</tr>
				<tr>
					<td>编号</td>
					<td>${item.detailCode}</td>
				</tr>
				<tr>
					<td>排序号</td>
					<td>${empty item.detailSort?'0':item.detailSort}</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
					</td>
				</tr>
			</table>
	</form>
</body>
