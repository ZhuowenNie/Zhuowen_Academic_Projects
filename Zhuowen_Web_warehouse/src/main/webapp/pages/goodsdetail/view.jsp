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
		<td>物资</td>
		<td>${goodsName}</td>
	</tr>
	<tr>
		<td>${model.type==2?"收货":"供货"}单位</td>
		<td>${companyName}</td>
	</tr>
	<tr>
		<td>${model.type==2?"出库":"入库"}数量</td>
		<td>${model.operCount}</td>
	</tr>
	<tr>
		<td>经手人</td>
		<td>${operName}</td>
	</tr>
	<tr>
		<td>说明</td>
		<td title="${model.content}">
			<c:choose>
				<c:when test="${fn:length(model.content) > 6}">
						${fn:substring(model.content, 0, 6)}...
				</c:when>
				<c:otherwise>
						${model.content}
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
			<tr>
				<td colspan="2" align="center">
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>

</body>
