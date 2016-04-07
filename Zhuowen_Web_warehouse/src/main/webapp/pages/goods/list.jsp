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
		form1.action = 'goods/list.htm';
		form1.submit();
	}
	
	function oper_view(pid){
		Iframe('goods/view.htm?id='+pid,300,300,'物资查看',false,false,false,EmptyFunc);
	}
	
	function oper_add(){
		var url = 'goods/add.htm';
		Iframe(url,350,300,'物资添加');
	}
	
	function oper_edit(pid){
		var url = 'goods/edit.htm?id='+pid;
		Iframe(url,350,300,'物资修改');
	}
	
	function oper_del(pid){
		Confirm("确认要删除该物资信息，会删除相应的出库入库记录？",function(){
			form1.action = 'goods/delete.htm?id='+pid;
			form1.submit();
		});
	}
	
	$(function(){
		//显示Menu索引
		showMenu('page_goods');
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
			<button type="button" class="btn btn-default" onclick="oper_add();">
			 		<span class="glyphicon glyphicon-plus"></span> 新 增
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
	 
						<td>
						<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.id});">查看</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.id});">修改</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_del(${item.id});">删除</a>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		<%@include file="/static/include/paginator.jsp" %>
			
	</form>
</body>
</html>