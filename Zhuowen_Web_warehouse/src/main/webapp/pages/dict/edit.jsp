<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	<%@include file="/static/include/my97.jsp"%>
<script type="text/javascript">
	function oper_save(id) {
		id = id || '0';
		form1.action = "dict/save.htm?detailId="+id;
		form1.submit();
	}
</script>
</head>

<body>
	<form name="form1" action="" method="post" class="form-horizontal"
		role="form" >
		
		<!-- 数据列表 -->
			<table class="table">
				<tr>
					<td>类型</td>
					<td><c:choose>
							<c:when test="${item.detailId>0 }">
								<input  class="form-control" type="hidden" name="dictType" value="${item.dictType}" />
								<select  class="form-control" disabled="disabled" >
									${optionList }
								</select>
							</c:when>
							<c:otherwise>
								<select  class="form-control" name="dictType" >
									${optionList }
								</select>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>名称</td>
					<td><input  class="form-control" type="text" name="detailName"
						value="${item.detailName}" /></td>
				</tr>
				<tr>
					<td>编号</td>
					<td><input  class="form-control" type="text" name="detailCode"
						value="${item.detailCode}" /></td>
				</tr>
				<tr>
					<td>排序号</td>
					<td><input  class="form-control" type="text" name="detailSort"
						value="${empty item.detailSort?'0':item.detailSort}" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
					<button class="btn btn-default" onclick="oper_save(${item.detailId});">保 存</button>
					<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
					</td>
				</tr>
			</table>
	</form>
</body>