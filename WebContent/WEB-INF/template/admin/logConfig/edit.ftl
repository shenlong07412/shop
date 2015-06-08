<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>业主信息 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<SCRIPT type="text/javascript">	
        $(document).ready(function(){
            var $inputForm = $("#inputForm");
            var $browserButton = $("#browserButton");
            
			[@flash_message /]
			$browserButton.browser();	
			
        	$inputForm.validate({
				rules: {
					operation:"required",
					urlPattern: "required"
				}
			});           
        });     
</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;
		<a href="${base}/admin/logConfig/list.jhtml">业主列表</a> &raquo; 编辑
	</div>
	<form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">	
	<input type="hidden" value="${(logConfig.id)!}" name="id"/>
	<input type="hidden" value="${(logConfig.dataflg)!}" name="dataflg"/>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="日志信息" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				<span class="requiredField">*</span>操作名称:
			</th>
			<td>
				<input type="text" name="operation" class="text" maxlength="100" value="${(logConfig.operation)!}"/>			
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>请求URL:
			</th>
			<td>
				<input type="text" name="urlPattern" class="text" maxlength="100" value="${(logConfig.urlPattern)!}"/>	
			</td>
		</tr>	
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="submit" class="button" value="${message("admin.common.submit")}" />
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>