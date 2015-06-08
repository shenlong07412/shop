<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>
           商户注册
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
        
        <script type="text/javascript">
$().ready(function() {

	var $registerForm = $("#registerForm");
	var $username = $("#username");
	var $password = $("#password");
	var $rePassword = $("#rePassword");
	var $communityId_select = $("input[name='communityId_select']");
	var $communityId = $("#communityId");
	var $channelId=$("#channelId");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 地区选择
	$communityId.lSelect({
		url: "${base}/common/area.jhtml"
	});
	
	// 表单验证
	$registerForm.validate({
		rules: {
			username: {
				required: true,
				email: true,
				remote: {
					url: "${base}/shop/merchant/register.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength}
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			communityId_select:{
				required: true,
			},
			channelId:{
				required: true,
			},
			captcha:{
				required: true,
			},
			channelId:{
				required: true,
			}
		},
		messages: {
			username: {
				remote: "该邮箱已存在"
			},
			password: {
				pattern: "${message("shop.register.passwordIllegal")}"
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
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$.ajax({
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword,
							communityId:$communityId.val(),
							channelId:$channelId.val(),
							captchaId: "${captchaId}",
							captcha: $captcha.val()
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								$submit.prop("disabled", false);
								location.href = "${base}/shop/merchant/register.jhtml";
							} else {
								$submit.prop("disabled", false);
								$captcha.val("");
								$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
							}
						}
					});
				}
			});
		}
	});

});
function getCaptcha(){
	var mobileClass=$("#mobile").attr("class");
	var mobileValue=$("#mobile").val();
	if(mobileValue=="" || mobileClass.indexOf("fieldError")>0){
		return false;
	}else{
		$.ajax({
				url: "${base}/register/send_captcha.jhtml",
				type: "GET",
				data: {
					mobile: $("#mobile").val()
				},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
				}
			});
	}
}
</script>
        
    </head>
    
    <body>
        [#include "/shop/include/header.ftl" /]
        <div id="mid">
            <div id="mid-top">
                商户注册
            </div>
            <div id="mid-right">
                <div class="new" style="position:relative;">
                    <div class="yjdl">
                        <p>
                            已经有有账号？
                        </p>
                        <p>
                            <a href="/login.jhtml">
                                登录
                            </a>
                        </p>
                    </div>
                    <div class="new-top">
                        <img src="${base}/resources/shop/images/new-top.png" width="947" height="12"
                        />
                    </div>
                    <div class="new-mid">
                        <div class="V35_tips clearfix">
                            <ul class=width1>
                                <li class=first>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        1.填写账号信息
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        2.激活账号
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span class=curr>
                                        <em class=l>
                                        </em>
                                        3.注册成功
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <table width="650" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="200">
                                </th>
                                <td colspan="3">
                                <h3>恭喜你注册成功！</h3>
                                </td>
                            </tr>
                            </table>
                        
                        
                    </div>
                    <div class="new-foot">
                        <img src="${base}/resources/shop/images/uer-foot.png" width="947" height="12"
                        />
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