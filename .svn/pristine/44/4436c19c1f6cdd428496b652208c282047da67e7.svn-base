
//写cookies函数 两个参数，一个是cookie的名子，一个是值
	function SetCookie(name,value){
    var Days = 14; //此 cookie 将被保存 14天
    var exp  = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
//    alert(value.replace("@","#"));

    document.cookie = name + "="+ escape(value)+ ";expires=" + exp.toGMTString()+";path=/";
  }
  //取cookies函数
	function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null){
    	return unescape(arr[2]); 
    }
    return null;
  }