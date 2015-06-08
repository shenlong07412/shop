<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>业主缴费日志 - Powered By SHOP++</title>
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
		<a href="${base}/admin/owner/paymentLogList.jhtml">业主缴费日志</a> &raquo;查看
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="业主缴费日志" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				流水单号:
			</th>
			<td>
				${paymentLog.batch_no}
			</td>
		</tr>
		<tr>
			<th>
				业主ID:
			</th>
			<td>
				${paymentLog.ownerInfo.id}
			</td>
		</tr>		
		<tr>
			<th>
				业主名称:
			</th>
			<td>
				${paymentLog.ownerInfo.name}
			</td>
		</tr>
		<tr>
			<th>
				水费(单位：元):
			</th>
			<td>
				${paymentLog.water_rate}
			</td>
		</tr>
		<tr>
			<th>
				电费(单位：元):
			</th>
			<td>
				${paymentLog.power_rate}
			</td>
		</tr>
			<th>
				煤费(单位：元):
			</th>
			<td>
				${paymentLog.gas_rate}
			</td>
		<tr>
			<th>
				其他维护费(单位：元):
			</th>
			<td>
				${paymentLog.other_rate}
			</td>
		</tr>
		<tr>
			<th>
				缴费时间:
			</th>
			<td>
				${(paymentLog.createDate)?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>		
		<tr>
			<th>
				总额(单位：元):
			</th>
			<td>
				${paymentLog.total_rate}
			</td>
		</tr>
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='paymentLogList.jhtml'" />
			</td>
		</tr>
	</table>
</body>
</html>