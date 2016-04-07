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
		form1.action = 'user/list';
		form1.submit();
	}
	
	function oper_view(pid){
		Iframe('user/view/'+pid,300,340,'用户查看',false,false,false,EmptyFunc);
	}
	
	function oper_add(){
		var url = 'user/add';
		Iframe(url,350,350,'用户添加');
	}
	
	function oper_edit(pid){
		var url = 'user/edit/'+pid;
		Iframe(url,350,350,'用户修改');
	}
	
	function oper_del(pid){
		Confirm("确认要删除该用户信息？",function(){
			form1.action = 'user/delete/'+pid;
			form1.submit();
		});
	}
	
	function oper_pwd(pid){
		var url = 'user/pwd/'+pid;
		Iframe(url,350,350,'密码修改');
	}
	
	
	$(function(){
		//显示Menu索引
		showMenu('page_user');
	});
	</script>
</head>
<body>

	<form name="form1" action="" method="post" class="form-inline" >
		<%@include file="/static/include/menu.jsp" %>
		
		<div class="tableSearch">
			<%  //查询列表 %>
			<div class="form-group">
					<input class="form-control" type="text" name="attr.username" value="${attr.username}" 
						placeholder="请输入登陆名" required='required' />
			</div>
				<div class="form-group">
					<input class="form-control" type="text" name="attr.realname" value="${attr.realname}" 
						placeholder="请输入真实姓名"  />
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
										<th>登陆名</th> 
					<th>真实姓名</th> 
					<th>Email</th> 
					<th>手机号</th> 
 
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.page.list}" var="item" varStatus="row">
				<tbody>
					<tr>
						<td width="50">${row.count }</td>
						<%  // 列表内容 %>
											<td>${item.username}</td> 
					<td>${item.realname}</td> 
					<td>${item.email}</td> 
					<td>${item.tel}</td> 
	 
						<td>
						<a href="javascript:void(0);" class="btn btn-sm btn-success" onclick="oper_view(${item.userid});">查看</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_edit(${item.userid});">修改</a> 
						<a href="javascript:void(0);" class="btn btn-sm btn-danger" onclick="oper_del(${item.userid});">删除</a>
						<!-- 
						<a href="javascript:void(0);" class="btn btn-sm btn-primary" onclick="oper_pwd(${item.userid});">修改密码</a>
						 -->
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		<%@include file="/static/include/paginator.jsp" %>
			
	</form>
</body>
</html>