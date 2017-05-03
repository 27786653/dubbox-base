//JS操作cookies方法!
//写cookies
layui.config({
	base: 'resource/plugins/layui/modules/'
});
layui.define([], function(exports) {
	"use strict";
	/**
	 * @constructor begTable 构造函数
	 */
	var Ecookie = function() {
		this.config = {
				days:10
		}
	};
//	$.cookie('the_cookie', 'the_value', {expires: 7, path: '/', domain: 'jquery.com', secure: true});//新建一个cookie 包括有效期 路径 域名等  
	begTable.prototype.setCookie=function(name,value){
		var _config =this.config;
		var Days = this.config.days;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	};
	begTable.prototype.getCookie=function(name)
	{
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	begTable.prototype.delCookie=function(name)
	{
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}

	var ecookie = new Ecookie();

	exports('ecookie', function(options) {
		return ecookie.set(options);
	});
});