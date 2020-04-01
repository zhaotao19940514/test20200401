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

// 图片上传demo
jQuery(function() {
    var $ = jQuery,
        $list = $('#fileList'),
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,

        // Web Uploader实例
        uploader;

    // 初始化Web Uploader
    uploader = WebUploader.create({
    	formData: {
     		chargeId : '${pageModel.chargeId}'
         },
        // 自动上传。
        auto: false,
        // swf文件路径
        swf:'/plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server:rootUrl+'app/customerservice/finance/halfauditing/savePng',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
    
    });
    

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $list.append( $li );

        // 创建缩略图
        uploader.makeThumb( file, function( error, src ) {	
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }	
            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });
    // 文件上传过程中创建进度条实时显示。
     uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');
	console.log($percent.length);
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    }); 

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
     uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
        $("#closeBtn").click();
        $.Taiji.showNote("照片上传成功!");
        $('#show').css('display', '');
    }); 

    // 文件上传失败，现实上传出错。
     uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }
        $("#closeBtn").click();
        $.Taiji.showWarn("照片上传失败!");
    }); 

    // 完成上传完了，成功或者失败，先删除进度条。
       uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });  
    
       
     $('#btnSave').bind('click', function () {
           uploader.upload();
       });
});

</script>
<style>  
    #filePicker div:nth-child(2){width:100%!important;height:100%!important;}  
</style>  
<body>

<div id="uploader-demo">
    <!--用来存放item-->
    <div id="fileList" class="uploader-list"></div>
    <div id="filePicker">选择图片</div>
   	 <div class="modal-body"  style="display: none;">
   	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal"></form:form>
		<table   class="table table-bordered">
			<tr>
				<th>充值流水号:</th>
				<td>
					${pageModel.chargeId}
				</td>
			</tr>
		</table>
	</div>
	<button id="btnSave"  class="btn btn-md btn-default"   type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>上传</button>
	<button id="closeBtn" class="btn btn-md btn-default" data-dismiss="modal"  type="button"><i class="fa  fa-circle-thin   m-r-10  "></i>关闭</button> 
	 	
	
</div>

</body>
</html>