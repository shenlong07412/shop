<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
[@seo type = "index"]
	<title>[#if seo.title??][@seo.title?interpret /][#else]${message("shop.index.title")}[/#if][#if systemShowPowered] - Powered By SHOP++[/#if]</title>
	<meta name="author" content="SHOP++ Team" />
	<meta name="copyright" content="SHOP++" />
	[#if seo.keywords??]
		<meta name="keywords" content="[@seo.keywords?interpret /]" />
	[/#if]
	[#if seo.description??]
		<meta name="description" content="[@seo.description?interpret /]" />
	[/#if]
[/@seo]
<link href="${base}/resources/shop/css/normal.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/index.css" rel="stylesheet" type="text/css" />
<script src="${base}/resources/shop/js/jquery1.42.min.js" type="text/javascript"></script>
<script src="${base}/resources/shop/js/jquery.superslide.2.1.1.js" type="text/javascript"></script>
<SCRIPT src="${base}/resources/shop/js/jquery-1.4.2.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="${base}/resources/shop/js/jquery.SuperSlide.js" type="text/javascript"></SCRIPT>


</head>

<body>
<div class="hdgzheader">
<div class="head-top">
[#if user??]
<span class="top-zuce">欢迎您: ${(user.username)!} &nbsp; &nbsp;<a href="#">退出登录</a></span> 
[#else]
    <span class="top-zuce">游客您好，您还没有登录哦！ <a href="${base}/register.jhtml">免费注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${base}/login.jhtml">会员登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">忘记密码</a></span>  
  [/#if]
    <span class="top-menu"><a href="#">我的账户</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="#">订单查询</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${base}/list.jhtml">购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${base}/article/list/3.jhtml">购物指南</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${base}/article/list/7.jhtml">帮助中心</a></span>
  </div><!---end .head-top--->
</div><!---end .hdgzheader--->
<div style="width:100%; background:#fff; height:100px;">
<div id="logo">
<div class="zxgg">
<ul>

[@article_list articleCategoryId = 1 count = 4]
[#list articles as article]
<li><a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a></li>
[/#list]
[/@article_list]

[@article_list articleCategoryId = 2 count = 4]
[#list articles as article]
<li><a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a></li>
[/#list]
[/@article_list]

</ul>
</div>
<div id="logo-head"><img src="${base}/resources/shop/images/logo.jpg" width="199" height="90" /></div>
<div id="search">

<form id="productSearchForm" action="${base}/product/search.jhtml" method="get">
  <div id="search-left"><input name="keyword" type="text" class="text"  value="${productKeyword!message("shop.header.keyword")}" maxlength="30" /></div>
					<div id="search-right"><button type="submit" style="background:url(${base}/resources/shop/images/wsd.jpg);width:100px;
height:40px;"></button></div>
				</form>

</div><!---end .search--->
<div id="code">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%" height="75" align="center"><img src="${base}/resources/shop/images/wrtw.jpg" width="68" height="67" /></td>
    <td width="33%" align="center"><img src="${base}/resources/shop/images/wrtw.jpg" width="68" height="67" /></td>
    <td width="33%" align="center"><img src="${base}/resources/shop/images/wrtw.jpg" width="68" height="67" /></td>
  </tr>
  <tr>
    <td height="20" align="center" class="zi">微信公众号</td>
    <td align="center" class="zi">易信公众号</td>
    <td align="center" class="zi">APP下载</td>
  </tr>
</table>
</div>
</div>
</div><!---end .logo--->
<div id="link">
<div id="nav">
<ul>
<li><a href="${base}/">首&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
[@navigation_list position = "middle" count=7]
  [#list navigations as navigation]
    <li><a href="${navigation.url!}" target="_blank">${navigation.name!}</a></li>
  [/#list]
[/@navigation_list]
</ul>
</div>
</div><!---end .link--->


  [@ad_position id = 10 /]
       
</div><script>
/*鼠标移过，左右按钮显示*/
$(".wbanner").hover(function(){
	$(this).find(".prev,.next").fadeTo("show",0.1);
},function(){
	$(this).find(".prev,.next").hide();
})
/*鼠标移过某个按钮 高亮显示*/
$(".prev,.next").hover(function(){
	$(this).fadeTo("show",0.7);
},function(){
	$(this).fadeTo("show",0.1);
})
$(".wbanner").slide({ titCell:".num ul" , mainCell:".51buypic" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
</script>
<div id="periphery">
<div id="periphery-top"><span class="texttiele zi">周边商户</span> <span class="textenglish zi">/ Merchant</span></div>
<div id="periphery-con">
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
<div class="structure">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th height="83" colspan="2"><a href="#"><img src="${base}/resources/shop/images/dfgs.jpg" width="268" height="172" /></a></th>
      </tr>
    <tr>
      <td height="28" colspan="2" style="color:#333; font-weight:bold;"><a href="#">王孙家的店</a></td>
      </tr>
    <tr>
      <td height="25" colspan="2">地址：思明区曾厝垵53号之3</td>
      </tr>
    <tr>
      <td height="25">电话：13400638387</td>
      <th width="36%" rowspan="2"><a href="#"><img src="${base}/resources/shop/images/htrj.jpg" width="85" height="35" /></a></th>
      </tr>
    <tr>
      <td height="25" width="64%">人均：<span class="hong">100-200</span></td>
      </tr>
    <tr>
      <td height="25" colspan="2" style="color:#333;">特点：新鲜海鲜，停车方便，环境优雅</td>
      </tr>
  </table>
</div>
</div>
</div><!---end .periphery--->


<div id="recommend">
<div id="periphery-top" style="margin-bottom:10px;"><span class="texttiele zi">推荐商品</span> <span class="textenglish zi">/ Recommend</span></div>
<SCRIPT language="javascript">
                    jQuery(function($){
                        jQuery('.eat-tab dd').mouseover(function(){
                            var i = $(this).index();
                            $(this).addClass('cur').siblings('dd').removeClass('cur');
                            $('.eat-con').find('ul').eq(i).show().siblings('ul').hide();
                        });
                    });
                </SCRIPT>
                 
<DIV class="block-recreation clear " style="margin-bottom: 20px; background:#fff;">
<DIV class="eat-tab fl" style="position:relative;">
<div class="bbj"><img src="${base}/resources/shop/images/dsfad.png" width="40" height="380" /></div>
<DL>
  <DD class="cur"><A href="javascript:void(0)">休闲娱乐<I></I></A></DD>
  <DD><A href="javascript:void(0)">餐饮美食<I></I></A></DD>
  <DD><A href="javascript:void(0)">生活服务<I></I></A></DD>
  <DD><A href="javascript:void(0)">商场超市<I></I></A></DD>
  <DD><A href="javascript:void(0)">教育培训<I></I></A></DD></DL></DIV>
<DIV class="eat-con fl">
<UL class="clear">
[@product_list productCategoryId = 11  tagIds = 2 count = 2]
   [#if products?size>0]
       <li>
        [#list products as product]
	
	[#if product_index==0]
		<a href="${base}${product.path!}" title="${product.name!}" target="_blank">
		<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
		<P>${product.fullName!}<br /><span class="hong">${product.hits!}</span>人浏览  <span class="hong">${product.price!} </span> </p></a>
	[/#if]
	[#if product_index==1]
                <a href="${base}${product.path!}" title="${product.name}" target="_blank">
		<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
		<P>${product.fullName!}<br /><span class="hong">${product.hits!}</span>人浏览  <span class="hong">${product.price!} </span> </p></a>
	[/#if]
	 [/#list]

	</li>
	[/#if]
[/@product_list]

[@product_list productCategoryId = 11  tagIds = 2 count = 3][#list products as product]
	[#if product_index==2]
	<LI class="eat-con-li"><A href="${base}${product.path!}"  title="${product.name!}"
	target="_blank"><IMG  src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" >
	 <P>${product.fullName!}<br /><span class="hong">${product.hits!}</span>人浏览  <span class="hong">${product.price!} </span> </p></A></LI>
	[/#if]
[/#list]
[/@product_list]

[@product_list productCategoryId = 11  tagIds = 2 count = 5]
   [#if products?size>0]
       <li>
        [#list products as product]
	
	[#if product_index==3]
		<a href="${base}${product.path!}" title="${product.name!}" target="_blank">
		<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
		<P>${product.fullName!}<br /><span class="hong">${product.hits!}</span>人浏览  <span class="hong">${product.price!} </span> </p></a>
	[/#if]
	[#if product_index==4]
                <a href="${base}${product.path!}" title="${product.name}" target="_blank">
		<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
		<P>${product.fullName!}<br /><span class="hong">${product.hits!}</span>人浏览  <span class="hong">${product.price!} </span> </p></a>
	[/#if]
	 [/#list]

	</li>
	[/#if]
[/@product_list]
   
</UL>
<UL>
  <LI><A href=#" target="_blank"><IMG alt=" 面筋塞肉" 
  src="${base}/resources/shop/images/file_1405474461.jpg">
  <P> 面筋塞肉<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A><A href="#" 
  target="_blank"><IMG alt=" 咖喱金针菇肥牛" src="${base}/resources/shop/images/file_1405474507.jpg">
  <P> 咖喱金针菇肥牛<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI>
  <LI class="eat-con-li"><A href="#" 
  target="_blank"><IMG alt="小鱼美食公开课——厦门朗豪酒店" src="${base}/resources/shop/images/file_1405474640.jpg">
  <P>小鱼美食公开课——厦门朗豪酒店<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI>
  <LI><A href="#" target="_blank"><IMG alt="《12道锋味》--舒芙蕾" 
  src="${base}/resources/shop/images/file_1405302074.jpg">
  <P>《12道锋味》--舒芙蕾<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A><A href="#" target="_blank"><IMG 
  alt="凉粉" src="${base}/resources/shop/images/file_1405302051.jpg">
  <P>凉粉<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI></UL>
<UL>
  <LI><A href="#" 
  target="_blank"><IMG alt="16号味华香牛肉养生馆" src="${base}/resources/shop/images/file_1405473565.jpg">
  <P>16号味华香牛肉养生馆<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A><A href="#" 
  target="_blank"><IMG alt="16号珑宫海鲜餐厅" src="${base}/resources/shop/images/file_1405473598.jpg">
  <P>16号珑宫海鲜餐厅<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI>
  <LI class="eat-con-li"><A href="#" 
  target="_blank"><IMG alt="16号一麻一辣" src="${base}/resources/shop/images/file_1405473582.jpg">
  <P>16号一麻一辣<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI>
  <LI><A href="#" 
  target="_blank"><IMG alt="16号厦门宾馆" src="${base}/resources/shop/images/file_1405387178.jpg">
  <P>16号厦门宾馆<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A><A href="#" 
  target="_blank"><IMG alt="查看更多五折信息" src="${base}/resources/shop/images/file_1397188863.jpg">
  <P>查看更多五折信息<br /><span class="hong">363</span>人浏览  <span class="hong"> 8</span>人下载</P></A></LI></UL>
<UL>
  <LI><A href="#" target="_blank"><IMG 
  alt="厦门日航酒店弁慶日本餐厅怀石料理" src="${base}/resources/shop/images/file_1402279884.jpg">

  <P>厦门日航酒店弁慶日本餐厅怀石料理</P></A><A href="#" 
  target="_blank"><IMG alt="龙苑中餐厅夏季健康水果美食" src="${base}/resources/shop/images/file_1402280027.jpg">
  <P>龙苑中餐厅夏季健康水果美食</P></A></LI>
  <LI class="eat-con-li"><A href="#" 
  target="_blank"><IMG alt="美食公开课NO.14走进厦门海景千禧大酒店" src="${base}/resources/shop/images/file_1397125422.jpg">
  <P>美食公开课NO.14走进厦门海景千禧大酒店</P></A></LI>
  <LI><A href="#" target="_blank"><IMG 
  alt="厦门喜来登酒店推出地道马来西亚美食" src="${base}/resources/shop/images/file_1402279697_thumb.jpg">
  <P>厦门喜来登酒店推出地道马来西亚美食</P></A><A href="#" 
  target="_blank"><IMG alt="佰翔软件园酒店热情桑巴美食嘉年华" src="${base}/resources/shop/images/file_1402279526.jpg">
  <P>佰翔软件园酒店热情桑巴美食嘉年华</P></A></LI></UL>
<UL>
  <LI><A href="#" target="_blank"><IMG alt="厦门人气最旺排队最长的餐厅名录" 
  src="${base}/resources/shop/images/file_1397122920.jpg">
  <P>厦门人气最旺排队最长的餐厅名录</P></A><A href="#" target="_blank"><IMG 
  alt="那些我吃过且觉得性价比还行的排挡" src="${base}/resources/shop/images/file_1397122880.jpg">
  <P>那些我吃过且觉得性价比还行的排挡</P></A></LI>
  <LI class="eat-con-li"><A href="#" target="_blank"><IMG 
  alt="闺蜜聚会爱去的餐厅" src="${base}/resources/shop/images/file_1397122848.jpg">
  <P>闺蜜聚会爱去的餐厅</P></A></LI>
  <LI><A href="#" target="_blank"><IMG alt="厦门适合情侣约会的餐厅指南" 
  src="${base}/resources/shop/images/file_1397122737.png">
  <P>厦门适合情侣约会的餐厅指南</P></A><A href="#" target="_blank"><IMG 
  alt="我的厦门星级自助餐之旅" src="${base}/resources/shop/images/file_1397122691.jpg">
  <P>我的厦门星级自助餐之旅</P></A></LI></UL></DIV></DIV>
</div>
<div style="width:100%; height:225px; background:#fff;">
<div id="foot">
<div id="contact" class="zi"><table width="100%" height="140" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="42%" height="35"><a href="${base}/article/list/7.jhtml">关于我们</a></td>
    <td width="58%"><a href="#">加入我们</a></td>
  </tr>
  <tr>
    <td height="35"><a href="#">隐私声明</a></td>
    <td><a href="#">商户入驻</a></td>
  </tr>
  <tr>
    <td height="35"><a href="#">服务条款</a></td>
    <td><a href="#">帮助手册</a></td>
  </tr>
</table>
</div>
<div id="weixin"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="18%" height="58" align="center">关注我们</td>
    <td width="16%">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="25%">电脑下载</td>
    <td width="17%">手机下载</td>
  </tr>
  <tr>
    <td rowspan="2" align="center"><a href="#"><img src="${base}/resources/shop/images/223.png" width="93" height="93" /></a></td>
    <td rowspan="2" align="center"><a href="#"><img src="${base}/resources/shop/images/dsg.png" width="93" height="93" /></a></td>
    <td width="16%" rowspan="2" align="center"><a href="#"><img src="${base}/resources/shop/images/gar.png" width="93" height="93" /></a></td>
    <td width="8%" rowspan="2" align="center">&nbsp;</td>
    <td height="60"><a href="#"><img src="${base}/resources/shop/images/aga.png" width="154" height="36" /></a></td>
    <td rowspan="2"><a href="#"><img src="${base}/resources/shop/images/sdaf.png" width="92" height="91" /></a></td>
  </tr>
  <tr>
    <td height="55"><a href="#"><img src="${base}/resources/shop/images/ios.png" width="154" height="36" /></a></td>
    </tr>
  <tr>
    <td height="49" colspan="5" align="center">Copyright@ 2012 - 2014 雅地会 All Rights Reserved    闽ICP备13020953号 <br /></td>
    <td>&nbsp;</td>
    </tr>
</table>
</div>
</div>
</div>
</body>
</html>
