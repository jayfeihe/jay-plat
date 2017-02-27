<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Generator在线</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="css/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/flat-ui.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
	<div class="container">
		<h3 style="text-align: center;">JayHeGenerator</h3>
		<h6 style="text-align: center;color: green;">---hetiewei</h6>
		<form class="form-horizontal" role="form" id="form">

			<div class="form-group">
				<label for="connection" class="col-lg-2 control-label">数据库连接</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" placeholder="192.168.0.90" id="connection" name="connection" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="port" class="col-lg-2 control-label">端口号</label>
				<div class="col-xs-4">
					<input type="text" placeholder="3306" id="port" name="port" class="form-control" required/>
				</div>
				<label for="dataBase" class="col-lg-2 control-label">数据库</label>
				<div class="col-xs-4">
					<input type="text" placeholder="databaseName" id="dataBase" name="dataBase" class="form-control" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userId" class="col-lg-2 control-label">用户名</label>
				<div class="col-xs-4">
					<input type="text" placeholder="root" id="userId" name="userId" class="form-control" required/>
				</div>
				<label for="userPass" class="col-lg-2 control-label">密码</label>
				<div class="col-xs-4">
					<input type="text" placeholder="password" id="userPass" name="userPass" class="form-control" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="modelPath" class="col-lg-2 control-label">模型的包名路径</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" placeholder="com.yourCompany.core.entity" id="modelPath" name="modelPath" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="daoPath" class="col-lg-2 control-label">DAO的包名路径</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" placeholder="com.yourCompany.core.dao" id="daoPath" name="daoPath" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="mappingPath" class="col-lg-2 control-label">映射文件的路径</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" placeholder="mybatis.mapper" id="mappingPath" name="mappingPath" required/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="tableNames" class="col-lg-2 control-label">表名</label>
				<div class="col-xs-3">
					<input type="text" id="tableNames" name="tableNames" class="form-control" required/>
				</div>
				<label for="modelNames" class="col-lg-2 control-label">模型名</label>
				<div class="col-xs-3">
					<input type="text" id="modelNames" name="modelNames" class="form-control" required/>
				</div>
				<a class="btn btn-success btn-xs" onclick="addItem()" title="增加">
					<span class="fui-check">&nbsp;增加</span>
				</a>
			</div>

		</form>
		
		<div class="form-group">
			<div class="col-md-12" style="text-align: center;">
	          <a id="submitBtn" href="javascript:void(0);" onclick="doSum();" style="width: 200px;" class="btn btn-lg btn-primary "><span class="fui-check-circle"></span>&nbsp;生成并下载</a>
	        </div>
		</div>
	
	</div>
	<!-- /container -->
	<footer> </footer>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/flat-ui.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script>
		var basePath = '<%=path%>';
		$(function() {
			$("#form").validate();// 加载配置
		});
		function addItem(){
			var item = "";
			item += "<div class='form-group'>"
	     		+"<label class='col-lg-2 control-label'>表名</label>"
	     		+"<div class='col-xs-3'><input type='text' name='tableNames' class='form-control' /></div>"
	     		+"<label class='col-lg-2 control-label'>模型名</label>"
	     		+"<div class='col-xs-3'><input type='text' name='modelNames' class='form-control' /></div>"
	     		+"<a class='btn btn-success btn-xs' onclick='addItem()' title='增加'><span class='fui-check'>&nbsp;增加</span></a>&nbsp;"
	     		+"<a class='btn btn-danger btn-xs' onclick='redItem(this)' title='删除'><span class='fui-cross'>&nbsp;增加</span></a>"
	     		+"</div>";
			$("#form").append(item);
		}
		
		function redItem(para){
			$(para).parent().remove();
		}
		
		function doSum(){
			if($("#form").valid(this,'填写信息不完整。') == false){
				return;
			}
			if (typeof($("#submitBtn")) != "undefined") { 
				$("#submitBtn").attr("disabled", "disabled");
			}
			var url =  basePath +'/generatorServlet.do?' + Math.random();
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'text',
				data : $("#form").serialize(),
				success : function (data, textStatus) {
					$("#submitBtn").removeAttr("disabled");
					data = JSON.parse(data);
					if(data.rspCode == "000001"){
						alert("数据库连接失败，请检查您的数据库地址和数据库名");
					}else if(data.rspCode == "000002"){
						alert("数据库连接错误");
					}else if(data.rspCode == "000003"){
						alert("数据库连接错误");
					}else if(data.rspCode == "000004"){
						alert("发生错误，请检查您的用户名或密码");
					}else if(data.rspCode == "000005"){
						alert("操作失败");
					}else{
						//alert("操作成功");
						window.open(basePath + "/tmp" +data.zipName);
					}
			    },
				error: function(data, textStatus){
					alert('操作失败');
					$("#submitBtn").removeAttr("disabled");
				}
			});
		}
	</script>
</body>
</html>
