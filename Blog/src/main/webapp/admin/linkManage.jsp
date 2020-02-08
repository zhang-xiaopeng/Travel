<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	//1.添加对话框打开
	var url ;
	function openLinkAddDialog(){
		//1.打开对话框并设置标题
		$("#dlg").dialog("open").dialog("setTitle","添加友情链接信息") ;
		//2.设置提交路径
		url="${pageContext.request.contextPath}/admin/link/save.do" ;
	}
	//2.修改对话框打开
	function openLinkUpdateDialog(){
		//1.判断有没选中数据条目
		var selectedRows = $("#dg").datagrid("getSelections") ;
		if(selectedRows.length != 1){
			$.messager.alert("系统提示","请选择一条要修改的数据！") ;
			return ;
		}
		//2.取得选中条目
		var row = selectedRows[0] ;
		
		//3.打开对话框
		$("#dlg").dialog("open").dialog("setTitle","修改博客类别信息") ;
		//4.重新加载对话框信息
		$("#fm").form("load",row) ;  //重新加载对话框信息
		//5.设置提交路径
		url="${pageContext.request.contextPath}/admin/link/save.do?id=" + row.id ;
	}
	//3.删除链接
	function deleteLink(){
		//1.判断有没有选中数据
		var selectedRows = $("#dg").datagrid("getSelections") ;
		if(selectedRows.length == 0){
			$.messager.alert("系统提示","请选择至少一条要删除的数据！") ;
			return ;
		}
		
		//2.获取到选中条目的id,并将数组型的id转为以,分割的形式
		var strIds = [] ;
		for(var i = 0 ; i < selectedRows.length ; i ++){
			strIds.push(selectedRows[i].id) ;
		}
		
		var ids = strIds.join(",") ;   //将id数组用,分隔开
		
		//3.执行删除
		$.messager.confirm("系统提示","您确定要删除这<font color='red'>" + selectedRows.length +"</font>条数据吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/link/delete.do",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已经成功删除！") ;
						//删除成功后，刷新页面
						$("#dg").datagrid("reload") ;
					}else{
						$.messager.alert("系统提示","数据删除失败！") ;
					}
				},"json") ;
			}
		}) ;
	}
	//4.对话框保存
	function saveLink(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate") ;
			},
			success:function(result){
				var result = eval("(" + result + ")") ;
				if(result.success){
					$.messager.alert("系统提示","保存成功") ;
					resetValue() ;//重置对话框中的内容
					$("#dlg").dialog("close") ;//关闭对话框
					$("#dg").datagrid("reload") ; //刷新表格
				}else{
					$.messager.alert("系统提示","保存失败") ;
					return ;
				}
			}
		}) ;
	}
	//5.关闭对话框
	function closeLinkDialog(){
		$("#dlg").dialog("close") ;
		resetValue() ;
	}
	//重置弹出的对话框
	function resetValue(){
		$("#linkName").val("") ;
		$("#linkUrl").val("") ;
		$("#orderNo").val("") ;
	}

</script>
</head>
<body style="margin: 1px">
<!-- 1.列表展示 -->
<table id="dg" title="友情链接管理" class="easyui-datagrid" fitcolumns="true" pagination="true" rownumbers="true"
	url="${pageContext.request.contextPath}/admin/link/list.do" fit="true" toolbar="#tb">
<thead>
<tr>
	<th field="cb" checkbox="true" align="center"></th>
	<th field="id" width="20" align="center">编号</th>
	<th field="linkName" width="200" align="center">链接名称</th>
	<th field="linkUrl" width="200" align="center">链接地址</th>
	<th field="orderNo" width="100" align="center">排序序号</th>		
</tr>
</thead>
</table>
<!-- 2.添加，修改，删除按钮 -->
<div id="tb">
	<a href="javascript:openLinkAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
	<a href="javascript:openLinkUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
	<a href="javascript:deleteLink()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<!-- 3.添加，修改表单 -->
<div id="dlg" class="easyui-dialog" style="width:520px;height:200px;padding:10px 20px ;"
	 closed="true" buttons="#dlg-buttons">
	 <!-- 可以提交的表单 -->
	 <form id="fm" method="post">
	 	<table cellspacing="8px">
	 		<tr>
	 			<td>链接名称</td>
	 			<td><input type="text" id="linkName" name="linkName" class="easyui-validatebox" required="true" /></td>
	 		</tr>
	 		<tr>
	 			<td>链接地址</td>
	 			<td><input type="text" id="linkUrl" name="linkUrl" class="easyui-validatebox" required="true" /></td>
	 		</tr>
	 		<tr>
	 			<td>链接排序</td>
	 			<td><input type="text" id="orderNo" name="orderNo" class="easyui-validatebox" required="true" />(类别根据排序序号从小到大排序)</td>
	 		</tr>
	 	</table>
	 </form>
</div>
<!-- 4.添加，修改表单的保存和关闭按钮 -->
<div id="dlg-buttons">
	<a href="javascript:saveLink()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeLinkDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>