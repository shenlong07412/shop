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
	var $mobileCaptcha = $("#mobileCaptcha");
	var $submit = $(":submit");
	
	// 表单验证
	$certificationMobile.validate({
		rules: {
			mobileCaptcha: {
				required: true
			}
		},
		submitHandler: function(form) {
			$.ajax({
				url: $certificationMobile.attr("action"),
				type: "POST",
				data: {
					mobileCaptcha: $mobileCaptcha.val()
				},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
					if (message.type == "success") {
						$submit.prop("disabled", false);
						location.href = "${base}/member/certification/mobile.jhtml";
					} else {
						$submit.prop("disabled", false);
					}
				}
			});
		}
	});
});
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
                                    <span class=curr>
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
                      <form id="certificationMobile" action="${base}/member/certification/check_captcha.jhtml" method="post">
                        	<table width="650" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width=120>&nbsp;</td>
								<td aligh="right">
									<span class="requiredField">*</span>请输入您手机收到的验证码：
								</th>
								<td  colspan="2">
										<input type="text" id="mobileCaptcha" name="mobileCaptcha" class="text"/>
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
                                <td>
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