<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.oAuthPlugin.list")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listTable = $("#listTable");
	var $install = $("#listTable a.install");
	var $uninstall = $("#listTable a.uninstall");
	
	[@flash_message /]

	// 安装
	$install.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.oAuthPlugin.installConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
	});
	
	// 卸载
	$uninstall.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.oAuthPlugin.uninstallConfirm")}",
			onOk: function() {
				$.ajax({
					url: $this.attr("href"),
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(message) {
						if (message.type == "success") {
							location.reload(true);
						} else {
							$.message(message);
						}
					}
				});
			}
		});
		return false;
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.oAuthPlugin.list")} <span>(${message("admin.page.total", oAuthPlugins?size)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>${message("OAuthPlugin.name")}</span>
				</th>
				<th>
					<span>${message("OAuthPlugin.version")}</span>
				</th>
				<th>
					<span>${message("OAuthPlugin.author")}</span>
				</th>
				<th>
					<span>${message("OAuthPlugin.isEnabled")}</span>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list oAuthPlugins as oAuthPlugin]
				<tr>
					<td>
						[#if oAuthPlugin.siteUrl??]
							<a href="${oAuthPlugin.siteUrl}" target="_blank">${oAuthPlugin.name}</a>
						[#else]
							${oAuthPlugin.name}
						[/#if]
					</td>
					<td>
						${oAuthPlugin.version!'-'}
					</td>
					<td>
						${oAuthPlugin.author!'-'}
					</td>
					<td>
						<span class="${oAuthPlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
					</td>
					<td>
						[#if oAuthPlugin.isInstalled]
							[#if oAuthPlugin.settingUrl??]
								<a href="${oAuthPlugin.settingUrl}">[${message("admin.oAuthPlugin.setting")}]</a>
							[/#if]
							[#if oAuthPlugin.uninstallUrl??]
								<a href="${oAuthPlugin.uninstallUrl}" class="uninstall">[${message("admin.oAuthPlugin.uninstall")}]</a>
							[/#if]
						[#else]
							[#if oAuthPlugin.installUrl??]
								<a href="${oAuthPlugin.installUrl}" class="install">[${message("admin.oAuthPlugin.install")}]</a>
							[/#if]
						[/#if]
					</td>
				</tr>
			[/#list]
		</table>
	</form>
</body>
</html>