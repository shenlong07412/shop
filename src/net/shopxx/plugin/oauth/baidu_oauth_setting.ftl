<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.plugin.alipayBank.setting")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("#browserButton");
	
	[@flash_message /]
	
	$browserButton.browser();
	
	// 表单验证
	$inputForm.validate({
		errorClass: "fieldError",
		ignoreTitle: true,
		rules: {
			appid: "required",
			appkey: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.plugin.baiduOAuth.setting")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("OAuthBaiduPlugin.OAuthName")}:
				</th>
				<td>
					<input type="text" name="oAuthName" class="text" value="${pluginConfig.attributes['oAuthName']}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("OAuthBaiduPlugin.AppID")}:
				</th>
				<td>
					<input type="text" name="openid" class="text" value="${pluginConfig.attributes['openid']}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("OAuthBaiduPlugin.AppKey")}:
				</th>
				<td>
					<input type="text" name="openkey" class="text" value="${pluginConfig.attributes['openkey']}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("OAuthBaiduPlugin.RedirectUri")}:
				</th>
				<td>
					<input type="text" name="redirectUri" class="text" value="${pluginConfig.attributes['redirectUri']}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("OAuthBaiduPlugin.logo")}:
				</th>
				<td>
					<input type="text" name="logo" class="text" value="${pluginConfig.attributes['logo']}" maxlength="200" />
					<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					[#if pluginConfig.attributes['logo']??]
						<a href="${pluginConfig.attributes['logo']}" target="_blank">${message("admin.common.view")}</a>
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("OAuthBaiduPlugin.description")}:
				</th>
				<td>
					<textarea name="description" class="text">${pluginConfig.attributes['description']?html}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${pluginConfig.order}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					${message("OAuthBaiduPlugin.isEnabled")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true"[#if pluginConfig.isEnabled] checked[/#if] />
					</label>
				</td>
			</tr>			
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='../list.jhtml'" />
				</td>
			</tr>			
		</table>
	</form>
</body>
</html>