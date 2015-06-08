<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; 查看
	</div>
	<table class="input">
		<tr>
			<th>
				表名:
			</th>
			<td>
				${(dataLog.tableName)!}
			</td>
		</tr>
		<tr>
			<th>
				主键id:
			</th>
			<td>
				${(dataLog.dataId)!}
			</td>
		</tr>
		<tr>
			<th>
				操作员:
			</th>
			<td>
				${(dataLog.operator)!}
			</td>
		</tr>
		<tr>
			<th>
				操作类型:
			</th>
			<td>
				${(dataLog.doType)!}
			</td>
		</tr>		
		<tr>
			<th>
				数据信息:
			</th>
			<td>
				<textarea class="textarea" style="width: 98%; height: 300px;" readonly="readonly">${dataLog.newContent?html}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				操作时间
			</th>
			<td>
				[#if dataLog.createDate??]
					${dataLog.createDate?string("yyyy-MM-dd HH:mm:ss")}
				[/#if]	
			</td>
		</tr>
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