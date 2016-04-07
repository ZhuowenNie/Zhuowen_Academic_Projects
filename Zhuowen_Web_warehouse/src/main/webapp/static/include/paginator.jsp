<%@page language="java" pageEncoding="UTF-8"%>

<div id="paginator" style="margin: auto;">
<input type="hidden" id="paginator_totalrecords" name="paginator.totalRecords" value="${page.totalRecords }"/>
<input type="hidden" id="paginator_pageno" name="paginator.pageNo" value="${page.pageNo }"/>
<input type="hidden" id="paginator_recordsperpage" name="paginator.pageSize" value="${page.pageSize }" />
<input type="hidden" id="paginator_length" name="length" value="10" />
