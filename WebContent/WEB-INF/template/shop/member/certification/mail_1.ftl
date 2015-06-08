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
<link href="${base}/resources/shop/css/register.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>

<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]
	var $certificationMailForm = $("#certificationMailForm");
	var $email = $("#email");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证
	$certificationMailForm.validate({
		rules: {
			email: {
				required: true,
				email: true
			},
			captcha:{
				required: true
			}
		},
		submitHandler: function(form) {
			$.ajax({
				url: $certificationMailForm.attr("action"),
				type: "POST",
				data: {
					email: $email.val(),
					captchaId: "${captchaId}",
					captcha: $captcha.val()
				},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
					if (message.type == "success") {
						$submit.prop("disabled", false);
						location.href = "${base}/member/certification/mail.jhtml";
					} else {
						$submit.prop("disabled", false);
						$captcha.val("");
						$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
					}
				}
			});
		}
	});

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
                                    <span>
                                        <em class=l>
                                        </em>
                                        2.邮箱激活
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li class=end>
                                    <span>
                                        <em class=l>
                                        </em>
                                        3.认证成功
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                            </ul>
                      </div>
                      
                      <form id="certificationMailForm" action="${base}/member/certification/mail_submit.jhtml" method="post">
                        	<table width="650" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                            	<td width="150">
                                    &nbsp;
                                </td>
                                <td >
                                    <span class="requiredField">
                                        *
                                    </span>
                                    邮箱：
                                </td>
                                <td colspan="2">
                                    <input  id="email" name="email" type="text" placeholder="请输入邮箱" size="40" class="text" value="${(member.email)!}" />
                                </td>
                            </tr>
                            <tr>
                            	<td>
                                    &nbsp;
                                </td>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                	&nbsp;
                                </td>
                                <td>
                                   &nbsp;
                                </td>
                            </tr>
							<tr>
								<td>
                                    &nbsp;
                                </td>
								<td >
									<span class="requiredField">*</span>${message("shop.captcha.name")}：
								</td>
								<td colspan="2">
									<span class="fieldSet">
										<input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off" />&nbsp;&nbsp;&nbsp;<img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" />
									</span>
								</td>
							</tr>
							<tr>
								<td>
                                    &nbsp;
                                </td>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                	&nbsp;
                                </td>
                                <td>
                                   &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td >
                                   &nbsp;
                                </td>
                                <td >
                                    <INPUT class=button value=下一步 type=submit>
                                </td>
                                <td>
                                    &nbsp;
                                </td>
                            </tr>
                        	</table>
                        </form>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>