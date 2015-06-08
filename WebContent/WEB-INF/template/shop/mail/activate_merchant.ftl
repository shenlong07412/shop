<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.setting.mailTestTitle")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
</head>
<body>
亲爱的用户${merchant.username}：
	<br/>
	您好！
 	<br/>
    感谢您注册智慧社区，您只需要点击下面链接，激活您的帐户，您便可以享受智慧社区的各项服务。
 	<br/>
	<a href="#">http://www.baidu.com</a>
 	<br/>
    (如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。该链接使用后将立即失效。)
 	<br/>
 	<br/>
    注意:重复发送激活码，则历史激活码失效。请您在收到邮件24小时(${member.safeKey.expire?string("yyyy-MM-dd HH:mm:ss")}前)进行激活，否则该激活码将会失效。7天后您的帐户将会失效。
 	<br/>
	重新发送激活码：<a href="#"></a>
 	<br/>

</body>
</html>