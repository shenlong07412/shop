<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.member.order.list")}[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/member.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/index_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]

});
</script>
</head>
<body>
	[#assign current = "certificationMail" /]
	[#include "/shop/include/header.ftl" /]
	<div class="container member">
		[#include "/shop/member/include/navigation.ftl" /]
		<div class="span18 last">
                        <div class="V35_tips clearfix">
                            <ul class=width1>
                                <li class=first>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        1.填写邮箱地址
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        2.邮箱激活
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        3.认证成功
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                      </div>
                      <h3 align="center">您已经通过智慧社区的邮箱认证！</h3>
                      <h3 align="center">认证的邮箱为：${(member.email)!}</h3>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>