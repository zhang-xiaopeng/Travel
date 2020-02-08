<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写博客页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body style="margin: 1px">
<div id="p" class="easyui-panel" title="编写博客" style="padding:5px;">
<table cellpadding="5px">
	<tr>
		<td width="80px">博客标题:</td>
		<td><input type="text" id="title" name="name" style="width:400px;" /></td>
	</tr>
	<tr>
		<td width="80px">所属类别:</td>
		<td>
			<select class="esayui-combobox" id="blogTypeId" name="blogType.id" style="width:400px;" editable="false" panelHeight="auto">
				<option value="">请选择博客类别</option>
				<c:forEach items="${ blogTypeCountList }" var="blogType">
					<option value="${ blogType.id }">${ blogType.typeName }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td width="80px">博客关键字:</td>
		<td><input type="text" id="keyword" name="keyword" style="width:400px;" />&nbsp;&nbsp;(多个关键字之间用空格隔开)</td>
	</tr>
	<tr>
		<td width="80px">博客摘要:</td>
		<td><input type="text" id="summary" name="summary" style="width:400px;" /></td>
	</tr>
	<tr>
		<td width="80px">博客内容:</td>
		<td>
			<script type="text/plain" id="editor" style="width:100%;height:500px;"></script>
		</td>
	</tr>
	<tr>
		<td width="80px"></td>
		<td><a href="javascript:submitData();" class="easyui-linkbutton" iconCls="icon-submit"  plain="true">发布博客</a></td>
	</tr>
</table>
</div>
<script type="text/javascript">
	//实例化编辑器
	var ue = UE.getEditor("editor") ;
</script>
<script type="text/javascript">
	//发布博客
	function submitData(){
		//1.获取元素的值
		var title = $("#title").val() ;
		var blogTypeId = $("#blogTypeId").val()  ;
		var keyword = $("#keyword").val()  ;
		var summary = $("#summary").val()  ;
		var content = UE.getEditor("editor").getContent() ;
		//2.验证信息
		if(title == null || title == ''){
			$.messager.alert("系统提示","博客标题不能为空") ;
			return ;
		}else if(blogTypeId == null || blogTypeId == ''){
			$.messager.alert("系统提示","博客类别不能为空") ;
		}else if(keyword == null || keyword == ''){
			$.messager.alert("系统提示","博客关键字不能为空") ;
		}else if(summary == null || summary == ''){
			$.messager.alert("系统提示","博客摘要不能为空") ;
		}else if(content == null || content == ''){
			$.messager.alert("系统提示","博客内容不能为空") ;
		}else{
			//3.提交信息
			$.post("${pageContext.request.contextPath}/admin/blog/save.do",
					{title:title,'blogType.id':blogTypeId,keyword:keyword,
					summary:summary,content:content},function(result){
				if(result.success){
					$.messager.alert("系统提示","博客发布成功！") ;
				}else{
					$.messager.alert("系统提示","博客发布失败！") ;
				}
			},"json") ;
		}
	}
</script>
</body>
</html>