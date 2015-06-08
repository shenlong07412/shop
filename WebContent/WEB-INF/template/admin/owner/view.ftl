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
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo;
		<a href="${base}/admin/owner/list.jhtml">业主列表</a> &raquo; 查看
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="业主信息" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				社区:
			</th>
				<td>
					${(owner.community.name)!}
				</td>
		</tr>	
		<tr>
			<th>
				业主编号:
			</th>
			<td>
				${owner.no}
			</td>
		</tr>
		<tr>
			<th>
				姓名:
			</th>
			<td>
				${owner.name}
			</td>
		</tr>		
		<tr>
			<th>
				性别:
			</th>
			<td>
				${owner.sex}
			</td>
		</tr>
		<tr>
			<th>
				照片:
			</th>
			<td>
				<a href="${(owner.photo)!}" target="_blank">${(owner.photo)!}</a>
			</td>
		</tr>
		<tr>
			<th>
				身份证号:
			</th>
			<td>
				${owner.identity}
			</td>
		</tr>
		<tr>
			<th>
				学历:
			</th>
			<td>
				${owner.education}
			</td>
		</tr>
		<tr>
			<th>
				手机号码:
			</th>
			<td>
				${owner.phone}
			</td>
		</tr>								
		<tr>
			<th>
				水费(单位：元):
			</th>
			<td>
				${owner.water_rate}
			</td>
		</tr>
		<tr>
			<th>
				电费(单位：元):
			</th>
			<td>
				${owner.power_rate}
			</td>
		</tr>
			<th>
				煤费(单位：元):
			</th>
			<td>
				${owner.gas_rate}
			</td>
		<tr>
			<th>
				其他维护费(单位：元):
			</th>
			<td>
				${owner.other_rate}
			</td>
		</tr>	
		<tr>
			<th>
				总额(单位：元):
			</th>
			<td>
				${owner.total_rate}
			</td>
		</tr>
		<tr>
			<th>
				认证状态:
			</th>
			<td>
				${owner.authenticating_state}
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