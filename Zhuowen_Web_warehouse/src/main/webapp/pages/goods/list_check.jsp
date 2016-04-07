<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>

	<script type="text/javascript">
		
	paginator = function(page){
		oper_list();
	};
	
	function oper_list(){
		form1.action = 'goods/check.htm';
		form1.submit();
	}
	
	function oper_detail(pid){
		Iframe('goodsdetail/check.htm?id='+pid,700,500,'明细',false,false,false,EmptyFunc);
	}
	
	$(function(){
		//显示Menu索引
		showMenu('page_goods_check');
	});
	</script>
</head>
<body>

	<form name="form1" action="" method="post" class="form-inline" >
		<%@include file="/static/include/menu.jsp" %>
		
		<div class="tableSearch">
			<%  //查询列表 %>
			<div class="form-group">
					<input class="form-control" type="text" name="attr['name']" value="${attr.name}" 
						placeholder="请输入名称" required='required' />
			</div>
			<div class="form-group">
					<select name="attr['type']" class="form-control" >
							<option value="-1">--请选择--</option>
							${goodsType}
					</select>
			</div>
	 
			
			<button type="button" class="btn btn-default" onclick="oper_list();" name="search">
			 		<span class="glyphicon glyphicon-search"></span> 查 询
			</button>
			<button type="button" class="btn btn-default" onclick="resetForm();">
			 		<span class="glyphicon glyphicon-refresh"></span> 重 置
			</button>

		</div>

		<!-- 数据列表 -->
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<%  // 列表头部 %>
					<th>名称</th> 
					<th>种类</th> 
					<th>说明</th> 
					<th>库存量</th> 
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tbody>
					<tr>
						<td width="50">${row.count }</td>
						<%  // 列表内容 %>
											<td>${item.name}</td> 
					<td>${item.typeName}</td> 
					<td title="${item.content}">
						<c:choose>
							<c:when test="${fn:length(item.content) > 6}">
									${fn:substring(item.content, 0, 6)}...
							</c:when>
							<c:otherwise>
									${item.content}
							</c:otherwise>
						</c:choose>
					</td> 
					<td>${item.goods_count_in-item.goods_count_out}</td> 
					<td>
						<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_detail(${item.id});">明细</a> 
					</td> 
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		<%@include file="/static/include/paginator.jsp" %>
			
	</form>
</body>
</html>