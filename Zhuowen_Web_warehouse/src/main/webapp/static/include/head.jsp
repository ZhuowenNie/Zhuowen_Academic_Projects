<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<title>仓储管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- //favicon.ico小图标名称 --%>
<link rel="icon" href="favicon.ico"/>
<link rel="shortcut icon" href="favicon.ico"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="仓储管理">
<meta http-equiv="description" content="仓储管理">
<%-- jquery --%>
<script type="text/javascript" src="static/jquery/jquery-1.8.2.min.js"></script>
<%-- 弹出框 --%>
<link rel="stylesheet" id='skin' type="text/css" href="static/ymPrompt/skin/simple/ymPrompt.css" />
<script type="text/javascript" src="static/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript" src="static/ymPrompt/common.js"></script>
<%-- 分页 --%>
<link rel="stylesheet" id='skin' type="text/css" href="static/smartpaginator/smartpaginator.css" />
<script type="text/javascript" src="static/smartpaginator/smartpaginator.js"></script>
<%-- bootstrap --%>
<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="static/bootstrap/css/bootstrap-theme.min.css">
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/bootstrap-common.js"></script>

<link rel="stylesheet" href="static/bootstrap3-dialog/css/bootstrap-dialog.min.css">
<script src="static/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>

<%-- 主JS --%>
<script type="text/javascript" src="static/common/main.js"></script>
<%-- 验证工具 --%>
<script type="text/javascript" src="static/common/valid.js"></script>
<%-- 初始化JS --%>
<script type="text/javascript" src="static/common/initJs.js"></script>
<!-- 主样式 -->
<link href="static/common/main.css" rel="stylesheet" type="text/css" />