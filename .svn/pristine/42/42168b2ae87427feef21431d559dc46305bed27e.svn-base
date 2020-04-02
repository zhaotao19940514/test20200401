<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@include file="/WEB-INF/jsp/assets.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	$("#vfjCardReadDriver").click(function(){
		$("#vfjCardReadDriverSubmit").click();
	});
	$("#jlCardReadDriver").click(function(){
		$("#jlCardReadDriverSubmit").click();
	});
	$("#watchObuReadDriver").click(function(){
        $("#watchObuReadDriverSubmit").click();
    });
	$("#juliObuReadDriver").click(function(){
        $("#juliObuReadDriverSubmit").click();
    });
	$("#icbcPosReadDriver").click(function(){
        $("#icbcPosReadDriverSubmit").click();
    });
	$("#ccbCardReadDriver").click(function(){
        $("#ccbCardReadDriverSubmit").click();
    });
	$("#chromeDownload").click(function(){
        $("#chromeDownloadSubmit").click();
    });
	$("#docDownload").click(function(){
        $("#docDownloadSubmit").click();
    });
});
</script>
<style type="text/css">
    .hide{
        display: none;
    }
    .checkSuccess{
        color:green;
        display: none;
    }
    .checkFailed{
        color:red;
        display:none;
    }
    .checkDiv{
        margin-top:-2em;
    }
</style>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			$("#checkVjfReader").click(function(){
				var cardReader = new CardReader();
				var opened = cardReader.openCard();
				if(opened == 0){
					$(this).parent().children(".checkFailed").hide();
					$(this).parent().children(".checkSuccess").show();
				}else{
					$(this).parent().children(".checkFailed").show();
                    $(this).parent().children(".checkSuccess").hide();
				}
				cardReader.closeCard();
			});
			$("#checkObuReader").click(function(){
				var obuReader = new ObuOfflineReader();
				var opened = obuReader.openObuDev();
                if(opened == 0){
                    $(this).parent().children(".checkFailed").hide();
                    $(this).parent().children(".checkSuccess").show();
                }else{
                    $(this).parent().children(".checkFailed").show();
                    $(this).parent().children(".checkSuccess").hide();
                }      
            });
			$("#checkCcbPosReader").click(function(){
				var misposClient = new MisPosClient();
				misposClient.testSignOn();
			});
		});
	</script>
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>

			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div id="myManage" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">设备检测</h4>
						</div>
						<div class="panel-body">
<pre>
当前设备配置： 读卡器:${loginUser.staff.serviceHall.config.cardDeviceType.value }  读签器:${loginUser.staff.serviceHall.config.obuDeviceType.value }  pos机:${loginUser.staff.serviceHall.config.posDeviceType.value }

<span style="color:red;">*首次使用请阅读操作手册进行设备配置调试</span>
<button id="docDownload" type="button" class="btn btn-primary btn-sm">下载黔通卡客服系统操作手册</button>
<span style="color:red;">若页面显示不正常(图标不显示、排版错乱、功能不正常等)，请下载专用浏览器</span>
<button id="chromeDownload" type="button" class="btn btn-primary btn-sm" style="margin-top:2px;">下载chrome浏览器专用绿色版(请勿升级此浏览器)</button>
</pre>
<form action="${rootUrl}app/ocx/dlocx/configDoc" method="post" class="hide">
    <button id="docDownloadSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/chrome" method="post" class="hide">
    <button id="chromeDownloadSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<pre><i><h4><i class="fa fa-navicon "></i> 设备调试步骤</h4></i>
<a href="#driver">1.下载相应设备驱动文件</a>
<a href="#ccbPos">2.工行pos机配置文件生成(若无需调试pos机请跳过)</a>
<a href="#checkDevice">3.检测设备连通性</a>
</pre><div id="driver"></div>
<pre><i><h4><i class="fa fa-download"></i> 设备驱动文件下载</h4></i>
<span style="color:red;">*若本机已有设备驱动(即以前可以正常使用设备)，则可跳过驱动安装</span>
<button id="vfjCardReadDriver" type="button" class="btn btn-primary btn-sm">下载vfj读卡器驱动</button> <button id="jlCardReadDriver" type="button" class="btn btn-primary btn-sm">下载聚利读卡器驱动</button> <button id="ccbCardReadDriver" type="button" class="btn btn-primary btn-sm">下载建行读卡器驱动</button> <button id="watchObuReadDriver" type="button" class="btn btn-primary btn-sm">下载握奇读签器驱动</button> <button id="juliObuReadDriver" type="button" class="btn btn-primary btn-sm">下载聚利读签器驱动</button> <button id="icbcPosReadDriver" type="button" class="btn btn-primary btn-sm">下载工行mispos机驱动</button>
</pre>
<form action="${rootUrl}app/ocx/dlocx/card" method="post" class="hide">
    <button id="vfjCardReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/jlCard" method="post" class="hide">
    <button id="jlCardReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/ccbCard" method="post" class="hide">
    <button id="ccbCardReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/watchObu" method="post" class="hide">
    <button id="watchObuReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/obu" method="post" class="hide">
    <button id="juliObuReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<form action="${rootUrl}app/ocx/dlocx/pos" method="post" class="hide">
    <button id="icbcPosReadDriverSubmit" type="submit" class="btn btn-primary btn-sm"></button>
</form>
<div id="ccbPos"></div>
<pre><i><h4><i class="fa fa-file-code-o"></i> 工行mispos配置文件生成</h4></i>
调试工行mispos时，请至电脑的设备管理中确认端口号，填入后生成mispos配置文件，并确保带后缀文件名为 KeeperClient.ini，放入以下位置：
    32位Windows系统 : C:\Windows\System32 
    64位Windows系统 : C:\Windows\SysWOW64
<form action="${rootUrl}app/ocx/mispos/configIniCreation" id="configForm" method="post">
<i class="fa fa-hand-o-down"></i><i>生成工行pos配置文件(<span style="color:red;">非工行渠道</span>)</i>
<label for="portNum" style="float: left;line-height: 2.5em;vertical-align: middle;">请填入mispos设备端口号(如：1):</label> <input style="margin-left: 5px;float: left;width: 5em;height: 2.5em;"  type="text" class="form-control" required id="portNum" name="portNum" style="max-width: 5em;" placeholder="端口号"/><button id="posConfigValid" type="submit" style="margin-left: 5px;float: left;width:auto;line-height: 1.5em;vertical-align: middle;" class="btn btn-primary btn-sm">生成工行pos配置文件(<span style="color:red;">非工行渠道</span>)</button>
</form>
<form action="${rootUrl}app/ocx/mispos/configIniCreationIcbc" id="configIcbcForm" method="post">
<i class="fa fa-hand-o-down"></i><i>生成工行pos配置文件(<span style="color:red;">工行渠道</span>)</i>
<label for="portNum" style="float: left;line-height: 2.5em;vertical-align: middle;">请填入mispos设备端口号(如：1):</label> <input style="margin-left: 5px;float: left;width: 5em;height: 2.5em;"  type="text" class="form-control" required id="portNum" name="portNum" style="max-width: 5em;" placeholder="端口号"/><button id="posConfigValid" type="submit" style="margin-left: 5px;float: left;width:auto;line-height: 1.5em;vertical-align: middle;" class="btn btn-primary btn-sm">生成工行pos配置文件(<span style="color:red;">工行渠道</span>)</button>
</form></pre>
<div id="checkDevice"></div>
<pre><i><h4><i class="fa fa-unlink"></i> 设备连通性检测</h4></i>
    操作说明：
    1、将设备连接到电脑，并通电；
    <span style="color:red;">*若本机已有设备驱动(即以前可以正常使用设备)，则可跳过2、3、4、5的驱动安装</span>
    2、点击上方对应的设备控件下载，将控件压缩包下载到本地；
    3、鼠标右键->我的电脑（此电脑）->属性，在属性页面查看电脑的操作系统位数（64位或32位）；
    4、解压控件压缩包，鼠标右键，选择对应系统位数的控件->以管理员身份运行、安装地址（默认安装地址）；
    5、重启专用浏览器(chrome)进入设备检测；
    6、将卡（OBU）放在对应的设备上，点击对应的检测按钮，根据反馈得到对应的检测结果。
    <span style="color: red;">注意：控件在非专用浏览器上无法正常使用！请使用专用浏览器！</span>
    
<i class="fa fa-hand-o-down"></i><i>检测读签器连通性。若第一次失败，可多试几次。</i>

 <div class="checkDiv"><button id="checkObuReader" type="button" class="btn btn-primary btn-sm">检测读签器连通性</button><span class="checkSuccess">连接成功</span><span class="checkFailed">连接失败</span></div>
<i class="fa fa-hand-o-down"></i><i>检测读卡器连通性。</i>

 <div class="checkDiv"><button id="checkVjfReader" type="button" class="btn btn-primary btn-sm">检测读卡器连通性</button><span class="checkSuccess">连接成功</span><span class="checkFailed">连接失败</span></div>
<i class="fa fa-hand-o-down"></i><i>检测工行pos机连通性。点击后请观察工行pos机界面显示结果，若显示"签到成功"则为连通，否则失败。</i>
 
 <div class="checkDiv"><button id="checkCcbPosReader" type="button" class="btn btn-primary btn-sm">检测工行pos机连通性</button></div>
</pre>							
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager"></div>
						</div>
					</div>

				</div>

			</div>

		</div>

		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
	</div>
</body>
</html>
