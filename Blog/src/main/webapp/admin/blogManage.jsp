<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	//返回该行的博客类型名称
	function formatBlogType(val,row){
		return val.typeName ;
	}
	//查询博客
	function searchBlog(){
		$("#dg").datagrid('load',{"title":$("#s_title").val(),"keyword":$("#s_keyword").val(),"blogTypeId":$("#blogTypeId").val()});
	}
	//打开修改博客对话框
	function openBlogModifyTab(){
		//1.判断选中的是否为一条博客
		var selectedRows = $("#dg").datagrid("getSelections") ;
		if(selectedRows.length != 1){
			$.messager.alert("系统提示","必须选中一条博客！") ;
			return ;
		}
		//2.获取到选中的数据
		var row = selectedRows[0] ;
		//3.打开tab
		window.parent.openTab("修改博客","modifyBlog.jsp?id=" + row.id,"icon-writeblog") ; 
	}
	//删除博客信息
	function deleteBlog(){
		//1.判断和是否选中博客
		var selectedRows = $("#dg").datagrid("getSelections") ;
		if(selectedRows.length == 0){
			$.messager.alert("系统提示","请选择要删除的博客！") ;
			return ;
		}
		//2.获得要删除的博客的id
		var strIds = [] ;
		for(var i = 0 ; i < selectedRows.length ; i ++){
			strIds.push(selectedRows[i].id) ;
		}
		var ids = strIds.join(",") ;
		
		//3.执行删除
		$.messager.confirm("系统提示","您确定要删除这<font color='red'>" + selectedRows.length + "</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/blog/delete.do",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","删除成功！") ;
						//删除成功后，刷新页面
						$("#dg").datagrid("reload") ;
					}else{
						$.messager.alert("系统提示","删除失败！") ;
					}
				},"json") ;
			}
		}) ;
	}
	//点击标题弹出用户预览页面
	function formatTitle(val,row){
		return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.id+".html'>"+val+"</a>" ;
	}
</script>
</head>
<body style="margin: 1px">
<!-- 1.显示博客列表 -->
<table  id="dg"  title="博客管理" class="easyui-datagrid"  fitcolumns="true" pagination="true" 
	rownumbers="true" fit="true" toolbar="#tb" url="${pageContext.request.contextPath}/admin/blog/list.do" >
<thead>
	<tr>
		<th field="cb" checkbox="true" align="center"></th>
		<th field="id" width="30px" align="center">编号</th>
		<th field="title" width="200px" align="center" formatter="formatTitle">标题</th>
		<th field="keyword" width="150px" align="center">关键字</th>
		<th field="releaseDate" width="50px" align="center">发布日期</th>
		<th field="blogType" width="100px" align="center" formatter="formatBlogType" >博客类别</th>
	</tr>
</thead>
</table>
<!-- 2.查询框 -->
<div id="tb">
	<div>
		<a href="javascript:openBlogModifyTab()" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteBlog()" class="easyui-linkbutton" id="delete" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;标题：&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode=13){searchBlog()}"/>
		&nbsp;关键字：&nbsp;<input type="text" id="s_keyword" size="20" onkeydown="if(event.keyCode=13){searchBlog()}" />
		&nbsp;博客类别：&nbsp;			
		<select class="esayui-combobox" id="blogTypeId" name="blogType.id" style="width:200px;" editable="false" >
			<option value="">请选择博客类别</option>
			<c:forEach items="${ blogTypeCountList }" var="blogType">
				<option value="${ blogType.id }">${ blogType.typeName }</option>
			</c:forEach>
		</select>
		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>
</body>
</html>