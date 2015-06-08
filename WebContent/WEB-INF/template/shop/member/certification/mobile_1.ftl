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
	
	var $certificationMobile = $("#certificationMobile");
	var $mobile = $("#mobile");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证
	$certificationMobile.validate({
		rules: {
			mobile: {
				required: true,
				pattern: /^1[3-8][0-9]\d{8}$/
			},
			captcha: {
				required: true
			}
		},
		messages: {
			mobile: {
				pattern: "格式错误！"
			}
		},
		submitHandler: function(form) {
			$.ajax({
				url: $certificationMobile.attr("action"),
				type: "POST",
				data: {
					mobile: $mobile.val(),
					captchaId: "${captchaId}",
					captcha:$captcha.val()
				},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
					if (message.type == "success") {
						$submit.prop("disabled", false);
						location.href = "${base}/member/certification/mobile2.jhtml";
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

function getCaptcha(param){
	var mobileClass=$("#mobile").attr("class");
	var mobileValue=$("#mobile").val();
	if(mobileValue=="" || mobileClass.indexOf("fieldError")>0){
		alert("请输入正确的手机号码！");
		return false;
	}else{
		$.ajax({
				url: "${base}/member/certification/send_captcha.jhtml",
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
		time(param);
	}
}

var wait=60;
function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");			
			o.value="免费获取验证码";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.value="重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
				time(o)
			},
			1000)
		}
	}
</script>
</head>
<body>
	[#assign current = "certificationMobile" /]
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
                                        1.手机验证
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span>
                                        <em class=l>
                                        </em>
                                        2.输入验证码
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
                      <form id="certificationMobile" action="${base}/member/certification/mobile_submit.jhtml" method="post">
                        	<table width="650" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                            	<td width="150">
                                </td>
                                <td >
                                    <span class="requiredField">
                                        *
                                    </span>
                                    您的认证手机：
                                </td>
                                <td colspan="2">
                                    <input  id="mobile" name="mobile" type="text" placeholder="请输入手机"  class="text" value="${(member.mobile)!}" />
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
								<td>
									<span class="requiredField">*</span>
									验证码：
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
                                <td>
                                    &nbsp;
                                <td>
                                   <INPUT class=button value=下一步 type=submit>
                                </td>
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