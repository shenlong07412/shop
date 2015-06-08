<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.branch.add")} - Powered By SHOP++</title>
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
	
	[@flash_message /]
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			adPositionId: "required",
			path: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.branch.name")}:
				</th>
				<td>
					<input type="name" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.branch.branchCode")}:
				</th>
				<td>
					<input type="text"  name="branchCode" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.branch.type")}:
				</th>
				<td>
					<select  name="type">
						[#if branchtype??]
							[#list branchtype as t]
								<option value="${t.codeDetail}">
									 ${t.name}
								</option>
							[/#list]
						[/#if]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					上级菜单:
				</th>
				<td>
					<select name="parentId">
						<option value="">顶层菜单</option>
						[#list brachTree as category]
							[#if category != branch ]
								<option value="${category.id}" [#if category.id == RequestParameters.parentId] selected="selected"[/#if]>
									[#if category.treeLevel != 0]
										[#list 1..category.treeLevel as i]
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
					${message("admin.branch.phone")}:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.branch.fax")}:
				</th>
				<td>
					<input type="text" name="fax" class="text"  maxlength="200" />
				</td>
			</tr>
			<tr id="iconTr">
				<th>
					${message("admin.branch.manager")}:
				</th>
				<td>
					<input type="text" name="manager" class="text"  maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.branch.memo")}:
				</th>
				<td>
					<input type="text" name="memo" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.branch.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text"  maxlength="9" />
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