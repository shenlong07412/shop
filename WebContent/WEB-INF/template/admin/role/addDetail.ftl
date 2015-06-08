<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.role.add")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: block;
	float: left;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");
	
	[@flash_message /]
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find(":checkbox :gt(0)");
		if ($(this).attr("checked")) {
			$thisCheckbox.prop("checked", true);
		} else {
			$thisCheckbox.prop("checked", false);
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.role.edit")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
	
		<input type="hidden" name="name" class="text" value="${role.name}" maxlength="200" />
		<input type="hidden" name="description" class="text" value="${role.description}" maxlength="200" />
		
		<div style="display:none">
		
		<table class="input">
 
			[#list menuList as m]
			<tr >
				<th>
					<input type="checkbox" name="authorities" value="${m.code}"[#if role.authorities?seq_contains(m.code)] checked="checked"[/#if] class="selectAll" />
${message(m.i18Code)}
				</th>
				<td>
					<span class="fieldSet">
						[#list m.children as c]
						<label>
							<input type="checkbox" name="authorities" value="${c.code}"[#if role.authorities?seq_contains(c.code)] checked="checked"[/#if] />${message(c.i18Code)}
						</label>
						[/#list]
					</span>
				</td>
				</tr>
			[/#list]
			
			[#if role.isSystem]
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<span class="tips">${message("admin.role.editSystemNotAllowed")}</span>
					</td>
				</tr>
			[/#if]
			
		</table>
		</div>
		<table class="input">
		 [#if detailMenuList?? ||detailMenuList?size gt 0]
			[#list detailMenuList as m]
			<tr class="authorities">
				<th>
				${message(m.i18Code)}
				</th>
				<td>
					 
						[#list detailCodesMap.get(m.id) as d]
						<label>
						
							 <input type="checkbox" name="authorities" value="${d}" />${d}
						</label>
						[/#list]
					 
				</td>
				</tr>
			[/#list]
		  [#else]
		  	<tr class="authorities">
		  		<td>
		  			暂无需要配置方法级的权限，请直接保存。
		  		</td>
		  	</tr>
		  [/#if]
		
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
					<input type="button" class="button" value="上一步" onclick="location.href='edit.jhtml?id=${role.id}'" />
	 
				</td>
			</tr>
		</table>
		
	</form>
 
	</script>
</body>
</html>