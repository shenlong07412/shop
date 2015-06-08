<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>社区审核  - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
});
var content='请从以下选择一个审核选项！<br>'+
					   '<input name="status" type="radio" value="1" checked>待审'+
					  '<input name="status" type="radio" value="2">审核通过<br>'+
					  '<input name="status" type="radio" value="4">草稿'+
					  '<input name="status" type="radio" value="3">审核驳回';	 
function checkinfo(id){
	   
		$.dialog({
			type: "warn",
			content: content,
			onOk: function() {
				$.ajax({
					url: "check.jhtml",
					type: "GET",
					data: {ids:id,status:$("input[name='status']:checked").val()},
					dataType: "json",
					cache: false,
					success: function(message) {
					   $("#refreshButton").click();
					}
				});
			}
		});

}


$().ready(function()
	{	
		var $listForm = $("#listForm");
		var $moreButton = $("#moreButton");
		var $filterSelect = $("#filterSelect");
		var $filterOption = $("#filterOption a");
		
		[@flash_message /]
		
		
		
		var $selectAll = $("#selectAll");
		var $checkButton = $("#checkButton");
		var $ids = $("#listTable input[name='ids']");
		var $listForm = $("#listForm");
		
		$selectAll.click( function() {
			var $this = $(this);
			var $enabledIds = $("#listTable input[name='ids']:enabled");
			if ($this.prop("checked")) {
				$enabledIds.prop("checked", true);
				if ($enabledIds.filter(":checked").size() > 0) {
					$checkButton.removeClass("disabled");
				} else {
					$checkButton.removeClass("disabled");
				}
			} else {
				$enabledIds.prop("checked", false);
				$checkButton.addClass("disabled");
			}
		});
		
				
		//审核
		$checkButton.click(function(){
			var $checkedIds=$("#listTable input[name='ids']:enabled:checked");
			var $this = $(this);
			if ($this.hasClass("disabled")) {
				return false;
			}
			$.dialog({
				type: "warn",
				content: content,
				onOk: function() {
					$.ajax({
						url: "check.jhtml",
						type: "GET",
						data:$checkedIds.serialize()+"&status="+$("input[name='status']:checked").val(),
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							$checkButton.addClass("disabled");
							$selectAll.prop("checked", false);
							$checkedIds.prop("checked", false);
							location.reload(true);
						}
					});
				}
			});
		});
		
		
				
				
		$ids.click( function() {
			var $this = $(this);
			if ($this.prop("checked")) {
				$this.closest("tr").addClass("selected");
				$checkButton.removeClass("disabled");
			} else {
				$this.closest("tr").removeClass("selected");
				if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
					$checkButton.removeClass("disabled");
				} else {
					$checkButton.addClass("disabled");					
				}
			}
		});
	});
	
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/community/listAudit.jhtml">社区审核</a> &raquo; 社区审核列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="listAudit.jhtml" method="get">
		<input type="hidden" name="areaId" value="${areaId!}"/>
		<div class="bar">
			<!--<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>-->
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="checkButton" class="iconButton disabled">
					<span class="checkIcon">&nbsp;</span>${message("admin.biz.merchant.check")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;"[#if page.searchProperty == "name"] class="current"[/#if] val="name">社区名称</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th width="20%">
					<a href="javascript:;" class="sort" name="name">社区名称</a>
				</th>
				<th width="8%">
					<a href="javascript:;" class="sort" name="longitude">经度</a>
				</th>
				<th width="8%">
					<a href="javascript:;" class="sort" name="latitude">纬度</a>
				</th>
				<th  width="8%">
					<a href="javascript:;" class="sort" name="radius">覆盖半径</a>
				</th>
				<th  width="25%">
					<a href="javascript:;" class="sort" name="area">地区</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">处理结果</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as community]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${community.id}" />
					</td>
					<td>
						${(community.name)!}
					</td>
					<td>
						${(community.longitude)!}
					</td>
					<td>
						${(community.latitude)!}
					</td>
					<td>
						${(community.radius)!}
					</td>
					<td>
						${(community.area.fullName)!}
					</td>
					<td>
						<span title="${community.createDate?string("yyyy-MM-dd HH:mm:ss")}">${(community.createDate)!}</span>
					</td>
					<td>
						[#if (community.status)=1]待审[/#if]
						[#if (community.status)=2]审核通过[/#if]
						[#if (community.status)=3]审核驳回[/#if]
						[#if (community.status)=4]草稿[/#if]
						
					</td>
					<td>
						<a href="editAudit.jhtml?id=${community.id}">[详情]</a>
						<a href="javascript:void(0)" id="checkInfo" onclick="checkinfo(${community.id})">[审核]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>