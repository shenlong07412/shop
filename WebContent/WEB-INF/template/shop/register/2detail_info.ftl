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
		
		<script type="text/javascript" src="${base}/resources/shop/js/ajaxupload.js"></script>
		
       
<script type="text/javascript">
$(function() {
	var $registerForm = $("#registerForm");
	var $areaId = $("#areaId");
	var $userId = $("#userId");
	var $userImg = $("#userImg");
	var $userPhoto = $("#userPhoto");
	var $userPhoto2 = $("#userPhoto2");
	var $registStep = $("#registStep");
	var $submit = $(":submit");
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/common/area.jhtml"
	});
	
	// 表单验证
	$registerForm.validate({
		rules: {
			userId:{
			required: true
			}
			[@member_attribute_list]
				[#list memberAttributes as memberAttribute]
					[#if memberAttribute.isRequired]
						,memberAttribute_${memberAttribute.id}: {
							required: true
						}
					[/#if]
				[/#list]
			[/@member_attribute_list]
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
							"userImg":$userImg.val(),
							"registStep":$registStep.val()
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
								location.href = "${base}/register/regist_step3.jhtml";
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
						} 
					},
					onComplete : function(file, response, statusText) {
						jsonData = eval("(" + response + ")");
						if (jsonData.rtnKey == 0) {
							alert(jsonData.rtnMsg);
						} else {
							$("#userImg").val(jsonData.path);
							$("#userPhoto").attr("src",jsonData.fullPath);
							$("#userPhoto2").attr("src",jsonData.fullPath);
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
                <div class="new">
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
                        <input type="hidden" id="userId" name="userId" value="${(currentMemberModel.id)!}"></input>
                        <input type="hidden" id="registStep" name="registStep" value="2"></input>
                        <input type="hidden" id="userImg" name="userImg" value=""></input>
                        <table width="870" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th rowspan="2" style="height:150px;">
                                    <span class="requiredField">
                                        *
                                    </span>
                                    头像：
                                </th>
                                <td rowspan="2">
                                    <img id="userPhoto" name="userPhoto" src="${base}/resources/shop/images/ewabdfbz.png" width="129" height="128" />
                                </td>
                                <td width="92" height="99">
                                    <img id="userPhoto2" name="userPhoto2" src="${base}/resources/shop/images/rqwrwfr.png" width="71" height="70" />
                                </td>
                                <td width="468" rowspan="3">
                                    &nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                <INPUT id="uploadImg" class=button value=上&nbsp;&nbsp;传 type=button>
                                </td>
                            </tr>
                            [@member_attribute_list]
								[#list memberAttributes as memberAttribute]
									<tr>
										<th width="100">
											[#if memberAttribute.isRequired]<span class="requiredField">*</span>[/#if]${memberAttribute.name}：
										</th>
										<td colspan="4">
											[#if memberAttribute.type == "area"]
												<span class="fieldSet">
												 	
													<input type="hidden" id="areaId" name="memberAttribute_${memberAttribute.id}" />
													<br/>
													<a href="#"> 没有你的小区？ </a>
												</span>
											[#elseif memberAttribute.type == "name"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[#elseif memberAttribute.type == "birth"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" onfocus="WdatePicker();" />
											[#elseif memberAttribute.type == "address"]
												<input type="text" name="memberAttribute_${memberAttribute.id}" class="text" maxlength="200" />
											[/#if]
										</td>
									</tr>
								[/#list]
							[/@member_attribute_list]
                            <tr>
                                <td>
                                    &nbsp;
                                </td>
                                <td width="150">
                                    <INPUT class=button value=保存 type=submit>
                                </td>
                                <td colspan="2">
                                    
                                </td>
                                <td>
                                    &nbsp;
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