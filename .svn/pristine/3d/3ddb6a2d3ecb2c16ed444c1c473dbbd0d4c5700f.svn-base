/**
 * 说明：
 * var mychart=$("#chart").mychart(options);
 * 参数：options
 *			url:获取图表数据的url,页面配置有form通过提交form获取图表数据，否则通过get方法获取图表数据。
 *			optionsCallback:通过回调设置图表参数。
 *			chartOptions:图表插件jqplot参数。
 * 返回值：mychart
 * 			mychart.chart();手工调用绘图。
 * 
 * 1、自动根据数字的小数最大位数计算formatString
 * 2、动态传参
 * 
 */
(function($){
	$.fn.extend({     
		mychart:function(options){
			this.settings=$.extend({},$.fn.mychart.defaults,options);
			this.settings.chartOptions= $.extend({},$.fn.mychart.defaults.chartOptions,options.chartOptions);
			this.settings.chartId = this[0].id;
			this.chart=chart;
			this.chart();
			return this;
		}     
	});
	
	var chart=function(){
		 var opts=this.settings.chartOptions;
		 var myThis=this;
		 var options = {
				 success: function submitSuccess(chartModel){
	            	opts.series=$.map( chartModel.legendLabel, function(n){
	            		  return {"label":n};
	            	});
	    			
	    			opts.axes.xaxis.label=chartModel.xlabel;
	    			opts.axes.xaxis.ticks=chartModel.xticks;
	    			opts.axes.yaxis.label=chartModel.ylabel;
	    			if( chartModel.yticks){
	    				opts.axes.yaxis.ticks=chartModel.yticks;
	    			}
	    			opts.axes.yaxis.tickOptions.formatString=getFormatString(chartModel.data);
	    			if(myThis.settings.optionsCallback instanceof Function){
	    				myThis.settings.optionsCallback(chartModel,opts);
	    			}
	    			if(window.console){
	    				console.dir( opts);	    				
	    			}
	    			recurJson(opts,chartModel);
	    			var plot=$.jqplot(myThis.settings.chartId,chartModel.data , opts);
	    			myThis.chart=plot;
	            }
	        };
    	var pform=$(myThis.settings.pagerForm);
    	if(pform.size()>0){
    		var action= pform.attr("action");
    		pform.attr("action",this.settings.url);
    		pform.ajaxSubmit(options);
    		pform.attr("action",action);    		
    	}else{
    		$.getJSON(this.settings.url, options.success);
    	}
	};
	
	//递归遍历JSON所有键值
    function recurJson(json,chartModel) 
    { 
        for(var i in json){         
            if(typeof json[i]=="object"){ 
                recurJson(json[i],chartModel); 
            }else if(/{other\[(\d+)\]}/g.test(json[i])){
        		var index= /{other\[(\d+)\]}/g.exec(json[i])[1];
        		json[i]=chartModel.other[index];
        	}
        } 
    }
    
	function  getFormatString(data){
		var maxLength=0;
		for(var i=0;i<data.length;i++){
			var dt=data[i];
			for(var intValue = 0; intValue < dt.length; intValue++) {
				var a =dt[intValue]+"";
				if(a.indexOf(".")==-1){
					break;
				}
				var l=a.replace(/\d+\./,"").length;
				if(l>maxLength){
					maxLength=l;
				}
			}
		} 
		return '%.'+maxLength+'f';
	}
	
	$.fn.mychart.defaults = {
			pagerForm:'#listForm',
			chartId:"chart",
			url:false,
			optionsCallback:function(){},
			chartOptions:{
				animate: true,
			    seriesDefaults:{
			            renderer:$.jqplot.LineRenderer
			        },
			    legend: {
			    	show: true,
			    	placement: 'outsideGrid'
			    },
		        highlighter: {
		            show: true,
		            sizeAdjust: 7.5,
		            tooltipAxes: 'y'
		        },
		        axes: {
		            xaxis: {
	            	  renderer: $.jqplot.CategoryAxisRenderer,
	                  tickRenderer: $.jqplot.CanvasAxisTickRenderer ,
	                  tickOptions: {
	                    angle: -30,
	                    fontSize: '10pt'
	                  },
		              label:"xlabel"
		            },
		            yaxis: {
		                label:"ylabel",
		                tickOptions: {
		                    formatString: '%.0f'   
		                }
		            }
		        },
			}
		};
})(jQuery);     