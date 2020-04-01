/*
Template Name: Color Admin Responsive Admin Template
Author: Sean Ngu
Author URL: http://www.sean-theme.com/pixel-admin/
Version: 1.0
*/
var handleSlimScroll=function(){
	"use strict";
	$("[data-scrollbar=true]").each(function(){
		 generateSlimScroll($(this));
	})
};
var generateSlimScroll = function (e) {
    var a = $(e).attr('data-height');
    a = a ? a : $(e).height();
    $(e).slimScroll({height:a,alwaysVisible:true});
};	
var handleSidebarMenu=function(){
		"use strict";
		$(".left_menu>.left_menu_bg > ul  li.has-sub > a").click(function(){
			var e=$(this).next(".sub");
			var t=".left_menu > ul > li.has-sub > .sub";
			$(t).not(e).slideUp(250);
			$(e).slideToggle(250)
		});
//			$(".sidebar .nav > .has-sub .sub-menu li.has-sub > a").click(function(){
//				var e=$(this).next(".sub-menu");$(e).slideToggle(250)
//			});
		$(".sidebar .nav > .has-sub > a").click(function(){
			var e=$(this).next(".sub-menu");
			var t=".sidebar .nav > li.has-sub > .sub-menu";
			$(t).not(e).slideUp(250);
			$(e).slideToggle(250)
		});
		$(".sidebar .nav > .has-sub .sub-menu li.has-sub > a").click(function(){
			var e=$(this).next(".sub-menu");$(e).slideToggle(250)
		});
	};
var handleMobileSidebarToggle=function(){
	$(".sidebar").click(function(e){e.stopPropagation()});
	$(document).click(function(e){
		if(!e.isPropagationStopped()){
			if($("#page-container").hasClass("sidebar-toggled")){
				$("#page-container").removeClass("sidebar-toggled")}
		}
	});
	$("[data-click=sidebar-toggled]").click(function(e){
		e.stopPropagation();
		var t="sidebar-toggled";
		var n="#page-container";if($(n).hasClass(t)){$(n).removeClass(t)}else{$(n).addClass(t)}
	})
};

var handleSidebarMinify=function(){
	$("[data-click=sidebar-minify]").click(function(e){
		e.preventDefault();
		var t="sidebar-minified";
		var n="#page-container";
		if($(n).hasClass(t)){
			$(n).removeClass(t)
			generateSlimScroll($('#sidebar [data-scrollbar="true"]'));
		}else{
			$(n).addClass(t)
			$('#sidebar [data-scrollbar="true"]').slimScroll({
        					destroy: !0
        				});
			$('#sidebar [data-scrollbar=true]').trigger('mouseover');
		}
		$(window).trigger("resize")})
};
var handlePageContentView=function(){
	"use strict";
	$.when($("#page-loader").addClass("hide")).done(function(){
		$("#page-container").addClass("in")
		})
};
var handlePanelAction=function(){
	"use strict";
	$(document).on("hover","[data-click=panel-remove]",function(){
		$(this).tooltip({title:"Remove",placement:"bottom",trigger:"hover",container:"body"});
		$(this).tooltip("show")
		});
	$(document).on("click","[data-click=panel-remove]",function(e){e.preventDefault();$(this).closest(".panel").remove()});
	$(document).on("hover","[data-click=panel-collapse]",function(){$(this).tooltip({title:"Collapse / Expand",placement:"bottom",trigger:"hover",container:"body"});$(this).tooltip("show")});
	$(document).on("click","[data-click=panel-collapse]",function(e){
		e.preventDefault();$(this).closest(".panel").children().not(".panel-heading").slideToggle()
		});
	$(document).on("hover","[data-click=panel-reload]",function(){$(this).tooltip({title:"Reload",placement:"bottom",trigger:"hover",container:"body"});$(this).tooltip("show")});
	$(document).on("click","[data-click=panel-reload]",function(e){
		e.preventDefault();
		var t=$(this).closest(".panel");
		t.find(".taiji_search_submit").click();
		});
	$(document).on("hover","[data-click=panel-expand]",function(){$(this).tooltip({title:"Expand / Compress",placement:"bottom",trigger:"hover",container:"body"});$(this).tooltip("show")});
	$(document).on("click","[data-click=panel-expand]",function(e){
		e.preventDefault();
		var t=$(this).closest(".panel");
		if($("body").hasClass("panel-expand")&&$(t).hasClass("panel-expand")){
			$("body, .panel").removeClass("panel-expand");
			$(".panel").removeAttr("style");
			$("[class*=col]").sortable("enable")
		}else{
			$("body").addClass("panel-expand");
			$(this).closest(".panel").addClass("panel-expand");
			$("[class*=col]").sortable("disable")
		}
		$(window).trigger("resize")})
};
var handleDraggablePanel=function(){
	"use strict";
	var e=".row>[class*=col]";
	var t=".panel-heading";
	var n=".row > [class*=col]";
	$(e).sortable({handle:t,connectWith:n})
};
var handelTooltipPopoverActivation=function(){
	"use strict";
	$("[data-toggle=tooltip]").tooltip();
	$("[data-toggle=popover]").popover()};
var handleScrollToTopButton=function(){
	"use strict";
	$(document).scroll(function(){
		 var e=$(document).scrollTop();
		 if(e>=200){$("[data-click=scroll-top]").addClass("in")}else{$("[data-click=scroll-top]").removeClass("in")}
	});
	$("[data-click=scroll-top]").click(function(e){e.preventDefault();$("html, body").animate({scrollTop:$("body").offset().top},500)})
};
var initTaijiDefault=function(){
	if(!$.Taiji){
		return;
	}
	if($.Taiji.defaults["search"]){
		$.Taiji.defaults["search"].tableFloatTop=0;
	}
	if(!jconfirm){
		return;
	}
	$.Taiji.confirm=function(options){
		var defaultSetting={
			    title: '确认提示',
			    content: '你确认要操作吗?',
			    confirmButton: '确认',
			    cancelButton: '取消',
			    confirmButtonClass: 'btn-success',
			    cancelButtonClass: 'btn-default',
			    theme: 'white',
			    icon:'fa fa-question-circle',
			    animationBounce: 2,
			    confirm: function () {
			    	return false;
			    }
		};
		return jconfirm($.extend(defaultSetting,options));
	};
	$.Taiji.alert=function(options){
		var defaultSetting={
				title: '提示',
				cancelButton:false,
				confirmButton: '确定',
				confirm: function () {
					return true;
		    	}
			};
		return jconfirm($.extend(defaultSetting,options));
	};
};

var initClock=function(){
	if($(".clock").size()===0){
		return;
	}
	var myClock={timeOffset:0};
	var updateTime=function() {
		var nowTime=new Date();
		nowTime.setTime(nowTime.getTime()+myClock.timeOffset);
		var seconds =nowTime.getSeconds();
		$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
		var minutes = nowTime.getMinutes();
		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
		var hours = nowTime.getHours();
		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
	};
	
	var correctTime=function(update){
		$.get(rootUrl+"app/baseTime",function(result){
			var nowTime=new Date();
			myClock.timeOffset=result.time-nowTime.getTime();
			//和服务端误差-200毫秒内啊
//	 		console&&console.log("timeOffset:"+myClock.timeOffset); 
			$('#Date').html(result.humanStr);
			if(update)updateTime();
		},"json");
	};
	correctTime();
	setInterval(updateTime,1000);
	var interval=$(".clock").data("correctInterval");
	if(interval)
		setInterval(correctTime,interval);
};
var App=function(){
	"use strict";
	return{
		init:function(){
			initClock();
			handleSlimScroll();
			handleSidebarMenu();
			handleMobileSidebarToggle();
			handleSidebarMinify();
			handlePageContentView();
			handlePanelAction();
			handleDraggablePanel();
			handelTooltipPopoverActivation();
			handleScrollToTopButton();
			initTaijiDefault();
			}}
}();