<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head></head>
<link rel="stylesheet" type="text/css" href="${rootUrl }/plugins/webuploader/webuploader.css">

<!--引入JS-->
<script type="text/javascript" src="${rootUrl }/plugins/webuploader/webuploader.js"></script>
<script>
$(function(){
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				 autoSearch:false
			}
		});
});

jQuery(function() {
    var $ = jQuery,
        $list = $('#thelist'),
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,

        // Web Uploader实例
        uploader;
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 自动上传。
        auto: false,
    	/* application/vnd.ms-excel */
     accept: {// 只允许选择Excel 格式
         title: 'Excel',
         extensions: 'xls,xlsx',
         mimeTypes: 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
     },
    // swf文件路径
    swf:'/plugins/webuploader/Uploader.swf',

    // 文件接收服务端。
    server: rootUrl+'app/customerservice/finance/cardaccountrefund/handleFile',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker',

    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    resize: false
    
	});
    
 // 当有文件被添加进队列的时候
    uploader.on( 'fileQueued', function( file ) {
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>' );
    });
    

 // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('正在处理数据......');

        $percent.css( 'width', percentage * 100 + '%' );
    });
    

    uploader.on( 'uploadSuccess', function( file ,response) {
    	console.log(response);
    	var data={};
    	data.fileName=file.name;
    	data.filePath=response.filePath;
    	importExcel(data);
	   	 
    });
    
   
    function importExcel(data){
			$.ajax({
				url : "importExcel",
				type : "POST",
				data:JSON.stringify(data),
				dataType : "json",
				contentType: "application/json",
				async:true,
				success : function(response) {
					if(response.status==1){
						$.Taiji.hideLoading();
				        $("#closeBtn").click();
				        $.Taiji.showNote(response.message);
					}else{
						$.Taiji.hideLoading();
						$("#closeBtn").click();
						$.Taiji.showWarn(response.message);
					}
				}
			});
		}
    uploader.on( 'uploadError', function( file ,response) {
        $.Taiji.hideLoading();
        $( '#'+file.id ).find('p.state').text('上传出错');
        $("#closeBtn").click();
        $.Taiji.showWarn(response.message);
    });

    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

   /*  // 完成上传完了，成功或者失败，先删除进度条。
       uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });   */
    
       
     $('#btnSave').bind('click', function () {
           uploader.upload();
           $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
       });
});

</script>

<style>  
    #picker div:nth-child(2){width:100%!important;height:100%!important;}  
</style>  
<body>

<div id="uploader"class="modal-body" >
    用来存放文件信息
    <div id="thelist" class="uploader-list"></div>
    <div id="picker">选择文件</div>
    <div class="modal-body" >
	</div>
	 	
    <button id="btnSave"  class="btn btn-md btn-default"   type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>开始上传</button>
    <button id="closeBtn" class="btn btn-md btn-default" data-dismiss="modal"  type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>关闭</button> 
</div>
 
</body>
</html>