/**
 * ajax发送接收二进制文件
 * 浏览器支持情况：ie10+，chrome，firefox
 * 示例见：${rootUrl}app/sample/binAjax，jsp页面中的访问url按实际情况修改。
 * 		
 * createBinXhr(url,globalOpts)
 * globalOpts参数
 *   withCredentials:跨域是否启用凭证
 *   handleError(status,msg,xhr):服务器端处理出错回调，省去每次调用都写
 * 	 responseError(status,xhr):服务器端处理出错回调，省去每次调用都写
 * 
 * binAjax(opts) 参数
 * 	serviceType:接口类型
 *  json:json类型数据
 *  beforeSend(xhr):在send前回调，xhr为XMLHttpRequest对象，可在此函数里修改xhr
 *  success(json,xhr):服务成功回调，json为服务器返回的结果
 *  handleError(status,msg,xhr):服务器端处理出错回调，status为状态码，msg为服务器范围的错误信息
 *  responseError(status,xhr):http响应出错回调
 *  
 */
function createBinXhr(url,globalOpts){
	function BinXhr(url){
		this.url=url;
		this.globalOpts=globalOpts||{};
	}
	BinXhr.prototype={
		constructor:BinXhr,
		defaultHandleError:function(){
			alert("服务器端处理出错，请检查数据格式并重试！");
		},
		defaultResponseError:function(){
			alert("http响应出错，请检查网络并重试！");
		},
		generateFileName:function(serviceType){
			return serviceType+"_REQ_"+new Date().format("yyyyMMddHHmmssS")+".json";
		},
		binAjax:function(opts){
			var gOpts=this.globalOpts;
			var handleError=opts.handleError||gOpts.handleError||this.defaultHandleError,
				responseError=opts.responseError||gOpts.responseError||this.defaultResponseError;
			var fileName=this.generateFileName(opts.serviceType);
			var myForm = new FormData();
			myForm.append("filename",fileName);
			var jsonStrArr=[JSON.stringify(opts.json||"{}")];
			var BlobBuilder = window.MozBlobBuilder || window.WebKitBlobBuilder || window.BlobBuilder;
		    if(BlobBuilder) {
		        var oBuilder = new BlobBuilder();
		        oBuilder.append(jsonStrArr[0]);
		        var blob = oBuilder.getBlob("text/plain"); // the blob
		        
		     } else {
		        var blob = new Blob(jsonStrArr, { 'type': 'text/json' });    
		     }
//		    var blob = new Blob([JSON.stringify(opts.json||"{}")], { 'type': 'text/json' });    
			    
			myForm.append("binFile",blob,fileName);
			var xhr = new XMLHttpRequest();
			xhr.open("POST", this.url,true);
			xhr.responseType = 'blob';
			xhr.withCredentials = !!gOpts.withCredentials;
			if(opts.beforeSend)opts.beforeSend(xhr);
			xhr.send(myForm);
			xhr.onreadystatechange = function (){
			    if ( xhr.readyState == 4  ) {
			    	if(xhr.status == 200){
			    		var reader = new FileReader();  
				    	reader.readAsText( xhr.response);
				    	reader.onload=function(event){  
				    		opts.success&&opts.success(JSON.parse(event.target.result),xhr);
					    }  
			    	}else if(xhr.status>=700){
			    		// 服务器返回字符串
			    		if(console)console.log(xhr);
			    		var reader = new FileReader();  
				    	reader.readAsText( xhr.response);
				    	reader.onload=function(event){  
				    		handleError(xhr.status,event.target.result,xhr);
					    }  
			    		
			    	}else{
				    	responseError(xhr.status,xhr);
			    	}
			    	
			    } 
			};

		},
		log:function(l){
			if(console){
		  		console.log(l);
		  	}
		}

	}
	if(!Date.prototype.format){
		Date.prototype.format = function(fmt)   
		{   
			var zeroize = function (value, length) {        
			    if (!length) length = 2;        
			    value = String(value);        
			    for (var i = 0, zeros = ''; i < (length - value.length); i++) {        
			    zeros += '0';        
			    }        
			    return zeros + value;        
			};  	
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "H+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  :zeroize(this.getMilliseconds())     //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		} 
	}
	return new BinXhr(url);
}
