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
		form1.action = 'goodsdetail/list.htm';
		form1.submit();
	}
	
	function oper_view(pid){
		Iframe('goodsdetail/view.htm?id='+pid,340,390,'${attr.type==2?"出库":"入库"}查看',false,false,false,EmptyFunc);
	}
	
	function oper_add(){
		var url = 'goodsdetail/add.htm?type=${attr.type}';
		Iframe(url,400,450,'${attr.type==2?"出库":"入库"}添加');
	}
	
	function oper_edit(pid){
		var url = 'goodsdetail/edit.htm?id='+pid;
		Iframe(url,400,450,'${attr.type==2?"出库":"入库"}修改');
	}
	
	function oper_del(pid){
		Confirm('确认要删除该${attr.type==2?"出库":"入库"}信息？',function(){
			form1.action = 'goodsdetail/delete.htm?id='+pid;
			form1.submit();
		});
	}
	
	$(function(){
		//显示Menu索引
		showMenu('page_goodsdetail_${attr.type==2?"out":"in"}');
	});
	</script>
</head>
<body>

	<form name="form1" action="" method="post" class="form-inline" >
		<input class="form-control" type="hidden" name="type" value="${attr.type}"  />
		<input class="form-control" type="hidden" name="attr['type']" value="${attr.type}"  />
		
		<%@include file="/static/include/menu.jsp" %>
		
		<div class="tableSearch">
			<%  //查询列表 %>
			<div class="form-group">
					<input class="form-control" type="text" name="attr['name']" value="${attr.name}" 
						placeholder="请输入名称" required='required' />
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
					<th>物资</th> 
					<th>${attr.type==2?"收货":"供货单位"}</th> 
					<th>${attr.type==2?"出库":"入库"}数量</th> 
					<th>经手人</th> 
					<th>说明</th> 
 
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tbody>
					<tr>
						<td width="50">${row.count }</td>
						<%  // 列表内容 %>
					<td>${item.goodsName}</td> 
					<td>${item.companyName}</td> 
					<td>${item.oper_count}</td> 
					<td>${item.operName}</td> 
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