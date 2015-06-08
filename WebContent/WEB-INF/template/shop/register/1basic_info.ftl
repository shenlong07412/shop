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
        
        <script type="text/javascript">
$().ready(function() {

	var $registerForm = $("#registerForm");
	var $email = $("#email");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $password = $("#password");
	var $rePassword = $("#rePassword");
	var $submit = $(":submit");
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证
	$registerForm.validate({
		rules: {
			email: {
				required: true,
				email: true,
				remote: {
					url: "${base}/register/check_email.jhtml",
					cache: false
				}
			},
			captcha:{
				required: true,
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
		},
		messages: {
			email: {
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
							email: $email.val(),
							enPassword: enPassword
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
								,captchaId: "${captchaId}",
								captcha: $captcha.val()
							[/#if]
							[@member_attribute_list]
								[#list memberAttributes as memberAttribute]
									,memberAttribute_${memberAttribute.id}: $(":input[name='memberAttribute_${memberAttribute.id}']").val()
								[/#list]
							[/@member_attribute_list]
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								$submit.prop("disabled", false);
								location.href = "${base}/register/regist_step2.jhtml";
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
                用户注册
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
                                        1.填写并验证账号信息
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span>
                                        <em class=l>
                                        </em>
                                        2.注册成功并填写资料
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span>
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
                        <input type="hidden" id="registStep" name="registStep" value="1"></input>
                        <table width="650" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="155">
                                    <span class="requiredField">
                                        *
                                    </span>
                                    登陆邮箱：
                                </th>
                                <td colspan="3">
                                    <input  id="email" name="email" type="text" placeholder="请输入邮箱" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td colspan="3">
                                    <span class="hong">
                                        使用手机号注册可保证您的账号安全，并可接收物业信息等金牌服务内容
                                    </span>
                                </td>
                            </tr>
                            [#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
								<tr>
									<th>
										<span class="requiredField">*</span>${message("shop.captcha.name")}：
									</th>
									<td colspan="3">
										<span class="fieldSet">
											<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" />&nbsp;&nbsp;&nbsp;<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
										</span>
									</td>
								</tr>
							[/#if]
                            
                            <tr>
                                <th>
                                    <span class="requiredField">
                                        *
                                    </span>
                                    创建登录密码：
                                </th>
                                <td colspan="3">
                                    <input type="password" id="password" name="password" type="text" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="requiredField">
                                        *
                                    </span>
                                    重新输入登录密码：
                                </th>
                                <td colspan="3">
                                    <input type="password" name="rePassword" type="text" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td width="139">
                                    <INPUT class=button value=提&nbsp;&nbsp;交 type=submit>
                                </td>
                                <td width="196">
                                   
                                </td>
                                <td>
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    你还可以:
                                </th>
                                <td colspan="3">
                                    <a href="#">
                                        使用手机注册>>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td colspan="3" style="font-size:15px; height:70px;">
                                    其它注册方式
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td colspan="3">
                                    <img src="${base}/resources/shop/images/fx-1.png" width="28" height="28"
                                    />
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <img src="${base}/resources/shop/images/fx-3.png" width="28" height="28"
                                    />
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <img src="${base}/resources/shop/images/fx-5.png" width="28" height="28"
                                    />
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <img src="${base}/resources/shop/images/fx-6.png" width="28" height="28"
                                    />
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <img src="${base}/resources/shop/images/fx-1.png" width="28" height="28"
                                    />
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <img src="${base}/resources/shop/images/fx-3.png" width="28" height="28"
                                    />
                                </td>
                            </tr>
                        </table>
                        </form>
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