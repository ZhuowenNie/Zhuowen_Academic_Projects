<!DOCTYPE html>
<html>
<head>
	<base href="/system/">
	<title>仓储管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="favicon.ico"/>
	<link rel="shortcut icon" href="favicon.ico"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="仓储管理">
	<meta http-equiv="description" content="仓储管理">
	<script type="text/javascript" src="static/jquery/jquery-1.8.2.min.js"></script>
	<link rel="stylesheet" id='skin' type="text/css" href="static/ymPrompt/skin/simple/ymPrompt.css" />
	<script type="text/javascript" src="static/ymPrompt/ymPrompt.js"></script>
	<script type="text/javascript" src="static/ymPrompt/common.js"></script>
	<link rel="stylesheet" id='skin' type="text/css" href="static/smartpaginator/smartpaginator.css" />
	<script type="text/javascript" src="static/smartpaginator/smartpaginator.js"></script>
	<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/bootstrap/css/bootstrap-theme.min.css">
	<script src="static/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/bootstrap/bootstrap-common.js"></script>
	<link rel="stylesheet" href="static/bootstrap3-dialog/css/bootstrap-dialog.min.css">
	<script src="static/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
	<script type="text/javascript" src="static/common/main.js"></script>
	<script type="text/javascript" src="static/common/valid.js"></script>
	<script type="text/javascript" src="static/common/initJs.js"></script>
	<link href="static/common/main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
		function oper_save() {
			if($('[name="old_password"]').val()=='') {
				Alert('请输入原始密码');
				return false;
			}
			if($('[name="new_password"]').val()=='') {
				Alert('请输入新密码');
				return false;
			}
			if($('[name="new_password2"]').val()=='') {
				Alert('请输入重复密码');
				return false;
			}
			form1.action = "user/save_pwd.htm";
			form1.submit();
			return true;
		}
	</script>

</head>
<body>

	<form name="form1" action="" method="post" class="form-horizontal" role="form" >
		<input type="hidden" name="id" value="${model.userid}" />
		<table class="table">
		<tr>
			<td>原始密码</td>
			<td>
			<input class="form-control" type="password" name="old_password" value="" required='required' />
			</td>
		</tr>
		<tr>
			<td>新密码</td>
			<td>
			<input class="form-control" type="password" name="new_password" value="" required='required'  />
			</td>
		</tr>
		<tr>
			<td>重复密码</td>
			<td>
			<input class="form-control" type="password" name="new_password2" value="" required='required'  />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
			<button class="btn btn-default" onclick="return oper_save();">保 存</button>
			<button class="btn btn-default" onclick="closeIframe();return false;">关 闭</button>
			</td>
		</tr>
		</table>
	</form>

</body>
