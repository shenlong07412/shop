<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.ad.edit")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $type = $("#type");
	var $contentTr = $("#contentTr");
	var $pathTr = $("#pathTr");
	var $path = $("#path");
	var $browserButton = $("#browserButton");
	
	[@flash_message /]
	
	[#if ad.type != "text"]
		$browserButton.browser({
			type: "${ad.type}"
		});
	[/#if]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			code: "required",
			path: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${ad.id}" />
		<input type="hidden" name="level" value="${ad.level}" />
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Menu.name")}:
				</th>
				<td>
					<input type="name" name="name" class="text" value="${ad.name}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Menu.name.i18Code")}:
				</th>
				<td>
					<input type="text" id="i18Code" name="i18Code" class="text" value="${ad.i18Code}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					上级菜单:
				</th>
				<td>
					<select name="parentId">
						<option value="">顶层菜单</option>
						[#list menuTree as category]
							[#if category != ad ]
								<option value="${category.id}"[#if category == ad.parent] selected="selected"[/#if]>
									[#if category.level != 0]
										[#list 1..category.level as i]
											&nbsp;&nbsp;
										[/#list]
									[/#if]
									${category.name}
								</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("Menu.code")}:
				</th>
				<td>
					<input type="text" id="code" name="code" class="text" value="${ad.code}" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					详细资源code:
				</th>
				<td>
					<input type="text" id="detailCode" name="detailCode" class="text" value="${ad.detailCode}" maxlength="200" />(多个code 就通过,分开)
				</td>
			</tr>
			
			<tr>
				<th>
					${message("Menu.url")}:
				</th>
				<td>
					<input type="text" id="accessUrl" name="accessUrl" class="text" value="${ad.accessUrl}" maxlength="200" />
				</td>
			</tr>
			<tr id="iconTr">
				<th>
					<span class="requiredField">*</span>${message("Menu.icon")}:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="icon" name="icon" class="text" value="${ad.icon}" maxlength="200"/>
						<input type="button" id="browserButton" class="button" value="${message("admin.browser.select")}" />
					</span>
				</td>
			</tr>
			<tr>
				<th>
					${message("Menu.class_name")}:
				</th>
				<td>
					<input type="text" id="className" name="className" class="text" value="${ad.className}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${ad.order}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>