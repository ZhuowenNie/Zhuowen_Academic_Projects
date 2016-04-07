<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/static/include/head.jsp"%>
	
	<script type="text/javascript">
		function oper_save(id) {
			form1.action = "goodsdetail/save.htm?id="+id;
			form1.submit();
		}
		
		// 获取物资数量
		function getCount(id) {
			jQuery.ajax({
				type:'POST',
				url:"goods/count.htm?id="+id,
				data:'',
				success:function(data){
					$('#goodsCount').text(data);
				},
				error:function(html){
					var flag = (typeof console != 'undefined');
					if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
				}
			});
		}
		
		$(function(){
			// 初始化物资数量
			var goods_id = $('[name="goodsId"]').val();
			if(goods_id > 0) getCount(goods_id);
			
			$('[name="goodsId"]').change(function(){
				if($(this).val() > 0) getCount($(this).val());
			});
		});
	</script>

</head>
<body>

	<form name="form1" action="" method="post" class="form-horizontal"
		role="form" onsubmit="oper_save(${model.id});">
		<input type="hidden" name="id" value="${model.id}" />
		<input type="hidden" name="type" value="${model.type}" />
		<table class="table">
			<% // 列表头部 %>
			<tr>
				<td>物资</td>
				<td>
					<select name="goodsId" class="form-control" >
						${goodsType}
					</select>
				</td>
			</tr>
			<tr>
				<td>剩余数量</td>
				<td>
					<span id="goodsCount">
						0
					</span>
				</td>
			</tr>
			<tr>
				<td>${model.type==2?"收货":"供货"}单位</td>
				<td>
					<select name="company" class="form-control" >
						${companyType}
					</select>
				</td>
			</tr>
					<tr>
				<td>${model.type==2?"出库":"入库"}数量</td>
				<td>
						<input class="form-control" type="text" name="operCount" value="${model.operCount}"  />
				</td>
			</tr>
					<tr>
				<td>经手人</td>
				<td>
					<select name="operId" class="form-control" >
						${operIdType}
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
				<button class="btn btn-default" onclick="closeIframe();">关 闭</button>
				</td>
			</tr>
		</table>
	</form>

</body>

