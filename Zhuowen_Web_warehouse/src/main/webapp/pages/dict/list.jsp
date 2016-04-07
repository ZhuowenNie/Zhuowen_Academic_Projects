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
	form1.action = 'dict/list.htm';
	form1.submit();
}

function oper_view(pid){
	Iframe('dict/view.htm?id='+pid,300,340,'查看',false,false,false,EmptyFunc);
}

function oper_add(){
	var url = 'dict/add.htm?dict_type='+form1["attr['dict_type']"].value;
	Iframe(url,350,350,'添加');
}

function oper_edit(pid){
	var url = 'dict/edit.htm?id='+pid+'&dict_type='+form1["attr['dict_type']"].value;
	Iframe(url,350,350,'修改');
}

function oper_del(pid){
	Confirm("确认要删除该信息？",function(){
		form1.action = 'dict/delete.htm?id='+pid;
		form1.submit();
	});
}

function oper_edit_dict(){
	var url = 'dict/edit_dict,htm?id='+form1["attr['dict_type']"].value;
	Iframe(url,350,250,'类型操作');
}

$(function(){
	//显示Menu索引
	showMenu('page_dict');
});
</script>
</head>

<body>
	<form name="form1" action="" method="post" class="form-inline" >
		<%@include file="/static/include/menu.jsp" %>
		
		<div class="tableSearch">
			<div class="form-group">
					类型 
						<select name="attr['dict_type']"  class="form-control">
								<option value="">--请选择--</option> ${optionList }
						</select> 
			</div>
			<button type="button" class="btn btn-default" onclick="oper_list();" name="search">
			 		<span class="glyphicon glyphicon-search"></span> 查 询
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
						<th>类型</th>
						<th>名称</th>
						<th>排序号</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach items="${requestScope.page.list}" var="item"
					varStatus="row">
					<tbody>
						<tr>
							<td width="50">${row.count }</td>
							<td>${item.dict_name}</td>
							<td>${item.detail_name}</td>
							<td>${item.detail_sort}</td>
							<td align="center">
								<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.detail_id});">查看</a> 
								<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.detail_id});">修改</a> 
								<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_del(${item.detail_id});">删除</a>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<%@include file="/static/include/paginator.jsp" %>
	</form>
</body>