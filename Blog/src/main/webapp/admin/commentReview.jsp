<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	//显示博客标题
	function formatBlogTitle(val,row){
		return val.title ;
	}
	//审核评论
	function commentReview(state){
		//1.判断是否选中行
		var selectedRows = $("#dg").datagrid("getSelections") ;
		if(selectedRows.length == 0){
			$.messager.alert("系统提示","请选择要审核的数据！") ;
			return ;
		}
		//2.存储要删除数据的id
		var strIds = [] ;
		for(var i = 0 ; i < selectedRows.length ; i ++){
			strIds.push(selectedRows[i].id) ;
		}
		var ids = strIds.join(",") ;
		
		//3.删除
		$.messager.confirm("系统提示","您确定要审核这<font color='red'>" + selectedRows.length + "</font>条数据吗？",function(r){
			if(r){
				$.post("${ pageContext.request.contextPath }/admin/comment/review.do",{ids:ids,state:state},function(result){
					if(result.success){
						$.messager.alert("系统提示","提交成功！");
						$("#dg").datagrid("reload") ;
					}else{
						$.messager.alert("系统提示","提价失败！") ;
					}
				},"json") ;
			}
		}) ;
	}
</script>
</head>
<body style="margin: 1px">
<!-- 1.显示未审核评论 -->
<table id="dg" class="easyui-datagrid" title="评论审核" fitColumns="true" fit="true" pagination="true"
	rownumbers="true" url="${ pageContext.request.contextPath }/admin/comment/list.do?state=0" toolbar="#tb">
	<thead>
		<tr>
			<!-- 此处的field对应的实体类中的属性名称 -->
			<th field="cb" checkbox="true" align="center"></th>
			<th field="id" width="20px" align="center">编号</th>
			<th field="blog" width="100px" align="center" formatter="formatBlogTitle">博客标题</th>
			<th field="userIp" width="50px" align="center">用户IP地址</th>
			<th field="content" width="200px" align="center">评论内容</th>
			<th field="commentDate" width="50px" align="center">评论日期</th>
		</tr>
	</thead>
</table>
<!-- 2.工具栏上的审核按钮 -->
<div id="tb">
	<a href="javascript:commentReview(1);" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
	<a href="javascript:commentReview(2);" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
</div>
</body>
</html>