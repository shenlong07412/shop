<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>
            ${message("shop.register.title")}[#if systemShowPowered] - Powered By
            SHOP++[/#if]
        </title>
        <meta name="author" content="SHOP++ Team" />
        <meta name="copyright" content="SHOP++" />
        <link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
        <link href="${base}/resources/shop/css/register.css" rel="stylesheet" type="text/css" />
        <link href="${base}/resources/shop/css/index_style.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
		
		<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${base}/resources/shop/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="${base}/resources/shop/js/ajaxupload.js"></script>

<script type="text/javascript">
			
$().ready(function() {
	var $registerForm = $("#registerForm");
	
	var $userId = $("#userId");
	var $registStep = $("#registStep");
	var $trueName = $("#trueName");
	var $idCardImg = $("#idCardImg");
	
	var $submit = $(":submit");
	
	// 表单验证
	$registerForm.validate({
		rules: {
			userId:{
				required: true
			},
			trueName:{
				required: true
			}
		},
		submitHandler: function(form) {
		$.ajax({
				url: "${base}/common/public_key.jhtml",
				type: "GET",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$submit.prop("disabled", true);
				},
				success: function(data) {
					$.ajax({
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							"userId":$userId.val(),
							"registStep":$registStep.val(),
							"trueName":$trueName.val(),
							"idCardImg":$idCardImg.val()
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								setTimeout(function() {
									$submit.prop("disabled", false);
									location.href = "${base}/";
								}, 3000);
							} else {
								$submit.prop("disabled", false);
							}
						}
					});
				}
			});
		}
	});
		var button = $("#uploadImg");
		var exts = "jpg|png|jpeg|gif", paths = "|";
		new AjaxUpload(
				button,
				{
					action : '${base}/payment/upload.jhtml',
					name : 'uploadFile',
					onSubmit : function(file, ext) {
						if (!RegExp("\.(?:" + exts + ")$$", "i").test(file)) {
							alert("只能上传以下类型：" + exts);
							return false;
						} else {
							button.text('上传中');
						}
					},
					onComplete : function(file, response, statusText) {
						jsonData = eval("(" + response + ")");
						if (jsonData.rtnKey == 0) {
							alert(jsonData.rtnMsg);
						} else {
							document.getElementById("idCardImg").value = jsonData.path;
							document.getElementById("userPhoto").src = jsonData.fullPath;
							alert(jsonData.rtnMsg);
						}
					}
				});
	
	
});
</script>
		
    </head>
    
    <body>
    [#include "/shop/include/header.ftl" /]
        <div id="mid">
            <div id="mid-top">
                管理中心
            </div>
            <div id="mid-right">
                <div class="new" style="position:relative;">
                    <div class="new-top">
                        <img src="${base}/resources/shop/images/new-top.png" width="947" height="12" />
                    </div>
                    <div class="new-mid">
                        <div class="V35_tips clearfix">
                            <ul class=width1>
                                <li class=first>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        1.填写并验证账号信息
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        2.注册成功并填写资料
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        3.激活账号
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="V35_tips clearfix" style="display:none;">
                            <ul class=width1>
                                <li class=first>
                                    <span>
                                        <em class=l>
                                        </em>
                                        1.填写开发者资料
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        2.验证邮箱
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span>
                                        <em class=l>
                                        </em>
                                        3.创建应用/添加网站
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="V35_tips clearfix" style="display:none;">
                            <ul class=width1>
                                <li class=first>
                                    <span>
                                        <em class=l>
                                        </em>
                                        1.填写开发者资料
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span>
                                        <em class=l>
                                        </em>
                                        2.验证邮箱
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        3.创建应用/添加网站
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <form id="registerForm" action="${base}/register/submit_email.jhtml" method="post">
                        <input type="hidden" id="userId" name="userId" value="${(memberModel.id)!}"></input>
                        <input type="hidden" id="registStep" name="registStep" value="3"></input>
                        <table width="860" border="0" cellspacing="0" cellpadding="0" align="center">
                       
                            <tr align="center">
                                <td width="155">
                                     <h4>请在24小时内点击邮件中的链接继续完成注册</h4>
                                </td>
                            </tr>
                            <tr align="center">
                                <td>
                                	邮件已发送到邮箱<a class="f-blue" href="#">[#if currentMemberModel??]${(currentMemberModel.email)!}[/#if]</a>
                                </td>
                            </tr>
                            <tr align="center">
                                <td>
                                   当前状态：[#if currentMemberModel.activateEmail?? && currentMemberModel.activateEmail==true]<span style="color: green;">已激活！</span>[#else]<span style="color: red;">未激活！</span>[/#if]
                                </td>
                            </tr>
                            <tr align="center">
                                <td>
                                    <a class="f-blue no-email" href="#" target="_blank">没收到邮件？</a>
                                </td>
                            </tr>
                            <tr align="center">
                                <td >
                                   <span>请先检查是否在垃圾邮件中</span>
				                	<span data-mod="popu_31" class="tracking-ad">如果还未收到，
				                		<a class="reSent" href="${domain!}/register/send_activate_mail.jhtml?username=${currentMemberModel.username}" target="_blank">重新发送邮件</a>
				                	</span>
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div>
                    <div class="new-foot">
                        <img src="${base}/resources/shop/images/uer-foot.png" width="947" height="12" />
                    </div>
                </div>
            </div>
        </div>
        <!--[if IE 6]>
            <script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript">
            </script>
            <script type="text/javascript">
                DD_belatedPNG.fix('*');
            </script>
        <![endif]-->
        [#include "/shop/include/footer.ftl" /]
    </body>

</html>