<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
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
<div id="p" class="easyui-panel" title="修改个人信息" style="padding:5px;">
<form action="${pageContext.request.contextPath}/admin/blogger/save.do" id="form1" method="post" enctype="multipart/form-data">
<!-- 隐藏框，将要修改的用户id传过去 -->
<input type="hidden" id="id" name="id" value="${ currentUser.id }" />
<input type="hidden" id="proFile" name="proFile" />
<table cellpadding="5px">
	<!-- 用户信息在登录时放到session中了 -->
	<tr>
		<td width="80px">用户名:</td>
		<td><input type="text" id="username" name="username" style="width:200px;" value="${ currentUser.username }" readonly="readonly" /></td>
	</tr>
	<tr>
		<td width="80px">用户昵称:</td>
		<td><input type="text" id="nickname" name="nickname" style="width:200px;" value="${ currentUser.nickname }" /></td>
	</tr>
	<tr>
		<td width="80px">个性签名:</td>
		<td><input type="text" id="sign" name="sign" style="width:400px;" value="${ currentUser.sign }" /></td>
	</tr>
	<tr>
		<td width="80px">个人头像:</td>
		<td><input type="file" id="imageFile" name="imageFile" style="width:400px;"/></td>
	</tr>
	<tr>
		<td width="80px">个人简介:</td>
		<td>
			<script type="text/plain" id="editor" style="width:100%;height:500px;"></script>
		</td>
	</tr>
	<tr>
		<td width="80px"></td>
		<td><a href="javascript:submitData();" class="easyui-linkbutton" iconCls="icon-submit"  plain="true">提交个人信息</a></td>
	</tr>
</table>
</form>
</div>
<script type="text/javascript">
	//实例化编辑器
	var ue = UE.getEditor("editor") ;
	ue.addListener("ready",function(){
		
		//通过Ajax获取数据
		UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/find.do",{
			method:"post",
			async:false,
			data:{},
			onsuccess:function(result){
				result = eval("(" + result.responseText + ")") ;
				UE.getEditor("editor").setContent(result.proFile) ;
			} 
		}) ;
	}) ;
</script>
<script type="text/javascript">
	//提交个人信息
	function submitData(){
		//1.获取元素的值
		var nickname = $("#nickname").val() ;
		var sign = $("#sign").val() ;
		var proFile = UE.getEditor("editor").getContent() ;
		//2.提交验证
		if(nickname == null || nickname == ""){
			$.messager.alert("系统提示","请输入昵称！") ;
		}else if(sign == null || sign == ""){
			$.messager.alert("系统提示","请输入个性签名！") ;
		}else if(proFile == null || proFile == ""){
			$.messager.alert("系统提示","请输入个人简介！") ;
		}else{
			//更新proFile的值
			$("#proFile").val(proFile) ;
			//3.提交个人信息
			$("#form1").submit() ;
		}

	}
</script>
</body>
</html>