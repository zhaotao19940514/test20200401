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
    debugger;
    var uploader = WebUploader.create({
    	formData: {
     		pathid : '${pageModel.id}',
     		pathStatus : '${pageModel.status}'
         },
        // 自动上传。
        auto: false,

    // swf文件路径
    swf:'/plugins/webuploader/Uploader.swf',

    // 文件接收服务端。
    server: rootUrl+'app/customerservice/finance/expenserefundapplication/saveFile',

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

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });
    

    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).find('p.state').text('已上传');
        $("#closeBtn").click();
        $(".taiji_search_submit").click();
        $.Taiji.showNote("相关材料上传成功!");
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
        $("#closeBtn").click();
        $.Taiji.showWarn("相关材料上传失败!");
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
       });
});

</script>

<style>  
    #picker div:nth-child(2){width:100%!important;height:100%!important;}  
</style>  
<body>

<div id="uploader"class="modal-body" style="text-align:center" >
	<h4 class="modal-title" style=" margin:auto">相关材料上传</h4>
    <div id="thelist" class="uploader-list"></div>
    <div id="picker" style="margin: auto;">选择文件（多个文件请使用压缩包！）</div>
    <div class="modal-body" >
   	<form:form modelAttribute="pageModel" id="inForm" name="inForm" cssClass="form-horizontal"></form:form>
		<table   class="table table-bordered">
			<tr>
				<td style="display: none;">
					${pageModel.id}
				</td>
			</tr>
		</table>
	</div>
	 	
    <button id="btnSave"  class="btn btn-md btn-default"   type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>开始上传</button>
    <button id="closeBtn" class="btn btn-md btn-default" data-dismiss="modal"  type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>关闭</button> 
</div>
 
</body>
</html>