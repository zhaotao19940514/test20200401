<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript">
			$(function(){
				$("#myManage").taiji({
					enableAclCheck:true,
					search:{
						autoSearch:true
					}
				});
			 
				$("#readCard").click(function(){
					var cardReader = new CardReader();
					var name  = cardReader.getUserName();
					$("#customerName").text(name);
					var cardinfo = cardReader.getCardInfoAll();
					var cardinfo2 = cardReader.getCardInfo();
// 					console.log(cardinfo);
					console.log(cardinfo2);
					var h=cardinfo.indexOf("核载:");
					console.log(h);
					var c=cardinfo.indexOf("车牌颜色:");
					console.log(c);
					var hezai=cardinfo.substring(h+3,c-1);
					$("#hezai").text(hezai+"");
					var idType =cardReader.getUserIdType();
					if(idType==00){
						$("#customerIdType").text("身份证");
					}else if(idType==01){
						$("#customerIdType").text("军官证");
					}else if(idType==02){
						$("#customerIdType").text("护照");
					}else if(idType==03){
						$("#customerIdType").text("入境证（限港台居民）");
					}else if(idType==04){
						$("#customerIdType").text("临时身份证");
					}else if(idType==05){
						$("#customerIdType").text("营业执照");
					}else if(idType==06){
						$("#customerIdType").text("营业执照");
					}
					
					var idNum  =cardReader.getUserIdNum();
					$("#customerIdNum").text(idNum);
					
					var card  = cardReader.getCardId();
					$("#cardId").text(card);
					
					var cardType=cardReader.getIssuerTypeIdentifier();
					if(cardType==21){
						$("#cardType").text("年/月票卡");
					}else if(cardType==22){
						$("#cardType").text("储值卡");
					}else if(cardType==23){
						$("#cardType").text("记账卡");
					}else if(cardType==24){
						$("#cardType").text("测试用年/月票卡");
					}else if(cardType==25){
						$("#cardType").text("测试用储值卡");
					}else if(cardType==26){
						$("#cardType").text("测试用记账卡");
					}
					
					var vehiclePlate= cardReader.getCardPlateNo();
					$("#vehiclePlate").text(vehiclePlate);
					
					var vehiclePlateColor=cardReader.getCardPlateColor();
					if(vehiclePlateColor!=undefined && vehiclePlateColor!=null && vehiclePlateColor!=''){
						vehiclePlateColor = parseInt(vehiclePlateColor);
					}
					if(vehiclePlateColor!=undefined && vehiclePlateColor!=null){
						if(vehiclePlateColor==0){
							$("#vehiclePlateColor").text("蓝色");
						}else if(vehiclePlateColor==1){
							$("#vehiclePlateColor").text("黄色");
						}else if(vehiclePlateColor==2){
							$("#vehiclePlateColor").text("黑色");
						}else if(vehiclePlateColor==3){
							$("#vehiclePlateColor").text("白色");
						}else if(vehiclePlateColor==4){
							$("#vehiclePlateColor").text("渐变绿色");
						}else if(vehiclePlateColor==5){
							$("#vehiclePlateColor").text("黄绿双拼色");
						}else if(vehiclePlateColor==6){
							$("#vehiclePlateColor").text("蓝白渐变色");
						}else if(vehiclePlateColor==9){
							$("#vehiclePlateColor").text("未确定");
						}
					}
					
					var enableTime =cardReader.getCardEnableTime();
					$("#enableTime").text(enableTime);
					
					var expireTime =cardReader.getCardExpireTime();
					$("#expireTime").text(expireTime);
					
					var preBalance =cardReader.getBalance();
					$("#preBalance").text(preBalance/100);
					
					var bindingFlag =cardReader.getBindingFlag();
					if(bindingFlag==0){
						$("#bindingFlag").text("未绑定");
					}
					if(bindingFlag==1){
						$("#bindingFlag").text("已绑定");
					}
				});
				
			});
</script>

</head>
<body>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
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
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">卡信息读取</h4>
                        </div>
                        <div class="panel-body">
								<div class="form-group" >
									
									<%-- <form:input id="comd"  path="com" maxlength="100" placeholder="端口号:如 COM4" cssClass="form-control"  />
									<button id="init" class="btn btn-md btn-default m-r-5" type="button" ><i class="fa fa-circle-thin  m-r-5 "></i>初始化</button> --%>
									<button id="readCard" class=" btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-credit-card   m-r-10 "></i>读卡</button>
									<br>
									<div class="panel-body">
										<div class="row"> 
											<div class="col-md-1">
												<span>单位/用户名称</span>
											</div>
											<div class="col-md-2">
												<span id="customerName"></span>
												</div>
											<div class="col-md-1">
												<span>证件类型</span>
											</div>
											<div class="col-md-2">
												<span id="customerIdType"></span>
											</div>
											<div class="col-md-1">
												<span>证件号</span>
											</div>
											<div class="col-md-2">
												<span id="customerIdNum"></span>
											</div>
										</div>
									<div class="row"> 
											<div class="col-md-1">
												<span>卡号</span>
											</div>
											<div class="col-md-2">
												<span id="cardId"></span>
											</div>
											<div class="col-md-1">
												<span>卡类型</span>
											</div>
											<div class="col-md-2">
												<span id="cardType"></span>
											</div>
											<div class="col-md-1">
												<span>车牌</span>
											</div>
											<div class="col-md-2">
												<span id="vehiclePlate"></span>
											</div>
									</div>
									<div class="row"> 
											<div class="col-md-1">
												<span>车牌颜色</span>
											</div>
											<div class="col-md-2">
												<span id="vehiclePlateColor"></span>
											</div>
											<div class="col-md-1">
												<span>启用时间</span>
											</div>
											<div class="col-md-2">
												<span id="enableTime"></span>
											</div>
											<div class="col-md-1">
												<span>失效时间</span>
											</div>
											<div class="col-md-2">
												<span id="expireTime"></span>
											</div>
									</div>
									<div class="row"> 
											<div class="col-md-1">
												<span  style="color:#F00; font-weight:bold">核载/载重(t)</span>
											</div>
											<div class="col-md-2">
												<span id="hezai"></span>
											</div>
											<div class="col-md-1">
												<span  style="color:#F00; font-weight:bold">卡片余额(元)</span>
											</div>
											<div class="col-md-2">
												<span id="preBalance"></span>
											</div>
											<div class="col-md-1">
												<span  style="color:#F00; font-weight:bold">OBU绑定状态</span>
											</div>
											<div class="col-md-2">
												<span id="bindingFlag"></span>
											</div>
											<!-- <div class="col-md-2">
												<span>用户信息</span>
											</div>
											<div class="col-md-2">
												<span id="user"></span>
											</div> -->
									</div>
								
                        			</div>
                        		</div>
                        	</div>
						
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

</body>
</html>