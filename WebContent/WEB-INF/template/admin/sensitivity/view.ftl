<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>敏感词信息 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;
		<a href="${base}/admin/owner/list.jhtml">敏感词列表</a> &raquo; 查看
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="敏感词信息" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				<span class="requiredField">*</span>关键字:
			</th>
			<td>
				${(sensitivity.search)!}		
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>替换内容:
			</th>
			<td>
				${(sensitivity.replacement)!}
			</td>
		</tr>		
	</table>	
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
			</td>
		</tr>
	</table>
</body>
</html>