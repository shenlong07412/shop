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

<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/shop/lhgdialog/lhgdialog.min.js?self=true"></script>
<script type="text/javascript" src="${base}/resources/shop/js/ajaxupload.js"></script>

<script type="text/javascript">
$().ready(function() {
	
	[@flash_message /]
	
	var $certificationRealnameForm = $("#certificationRealnameForm");
	var $memberId = $("#memberId");
	var $realname = $("#realname");
	var $idCard = $("#idCard");
	var $idCardImg = $("#idCardImg");
	var $submit = $(":submit");
	
	// 表单验证
	$certificationRealnameForm.validate({
		rules: {
			memberId: {
				required: true
			},
			realname:{
				required: true
			},
			idCard:{
				required: true,
				pattern:/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/
			},
			idCardImg:{
				required: true
			}
		},
		messages: {
			idCard: {
				pattern: "格式错误！"
			}
		},
		submitHandler: function(form) {
			$.ajax({
				url: $certificationRealnameForm.attr("action"),
				type: "POST",
				data: {
					memberId: $memberId.val(),
					realname: $realname.val(),
					idCard: $idCard.val(),
					idCardImg: $idCardImg.val()
				},
				dataType: "json",
				cache: false,
				success: function(message) {
					$.message(message);
					if (message.type == "success") {
						$submit.prop("disabled", false);
						location.href = "${base}/member/certification/realname.jhtml";
					} else {
						$submit.prop("disabled", false);
					}
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
	[#assign current = "certificationRealname" /]
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
                                        1.填写个人信息
                                        <em class=r>
                                        </em>
                                    </span>
                                </li>
                                <li>
                                    <span>
                                        <em class=l>
                                        </em>
                                        2.审核资料
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
                      
                        <form id="certificationRealnameForm" action="${base}/member/certification/realname_submit.jhtml" method="post">
                        <input type="hidden" id="memberId" name="memberId" value="${(member.id)!}"></input>
                        
                        <table width="650" border="0" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                            	<td width="100">
                            	</td>
                                <td colspan="2" align="center">
                                    <span class="hong">
                                        1、为保证您的账户安全，及享受互动社区的所有服务，请正确填写下列信息，
                                        <br />
                                        互动社区将保证不会把您的个人信息泄露给第三方。
                                    </span>
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                <td >
                                    <span class="requiredField">
                                        *
                                    </span>
                                    真实姓名：
                                </td>
                                <td>
                                    <input id="realname" name="realname" type="text" value="" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                 <td >
                                    <span class="requiredField">
                                        *
                                    </span>
                                    身份证号码：
                                </td>
                                <td>
                                    <input id="idCard" name="idCard" type="text" value="" size="40" class="text" />
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                <td style="height:300px;" >
                                    <span class="requiredField">
                                        *
                                    </span>
                                    身份证照片：
                                </td>
                                <td>
                                    <img id="userPhoto" src="${base}/resources/shop/images/sfas.png" width="319" height="240" />
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                               		<input type="hidden" id="idCardImg" name="idCardImg" class="text" maxlength="200" value="" /> 
									<input type="button" id="uploadImg" value="上传图片" /> <br /></td>
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                <td>
                                    &nbsp;
                                </td>
                                <td>
                                    请注意：照片中的以下信息必须真实有效且清晰可见。
                                    <br />
                                    1、手持证件人的五官。
                                    <br />
                                    2、身份证上的所有信息。
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                            	<td>
                            	&nbsp;
                            	</td>
                                <td>
                                &nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="100">
                            	</td>
                                <td>
                                </td>
                                <td>
                                   <INPUT class=button value=保存 type=submit>
                                </td>
                            </tr>
                        </table>
                    </form>
		
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>