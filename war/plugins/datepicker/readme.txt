注意:此版本为 4.2 正式版 build 20081203

在线演示和使用说明,大部分问题都可以通过这里解决,请细看
http://www.my97.net/dp/demo/


如果有问题请访问(问问题时,一定要附上相关的HTML代码和详细的错误信息):
www.my97.net/dp/support.asp


博客
http://my97.cnblogs.com
http://blog.csdn.net/my97/

医学会项目修改项目如下：
1、增加域定义
config.js文件里增加：

//可以指定域
try{
	document.domain="cma.org.cn";
}
catch (e) {
	//域设置出错
}

2、增加跳转函数
WdatePicker.js文件里增加：
function showCalendar(id, format) {
 WdatePicker({el:$dp.$(id)});
}
3、更改了默认皮肤文件：
WdatePicker.js文件
skin:"whyGreen"


