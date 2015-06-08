<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.biz.merchant.list")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}
</style>
<script type="text/javascript">

	$().ready(function()
	{	
		var $listForm = $("#listForm");
		var $moreButton = $("#moreButton");
		var $filterSelect = $("#filterSelect");
		var $filterOption = $("#filterOption a");
		
		[@flash_message /]
		
		
		
		var $selectAll = $("#selectAll");
		var $checkButton = $("#checkButton");
		var $recommendButton = $("#recommendButton");
		var $unfreezeButton = $("#unfreezeButton");
		var $ids = $("#listTable input[name='ids']");
		var $auditSwitch = $("#auditSwitch");
		var $recommend = $("#recommend");
		
		var $listForm = $("#listForm");
		
		$selectAll.click( function() {
			var $this = $(this);
			var $enabledIds = $("#listTable input[name='ids']:enabled");
			if ($this.prop("checked")) {
				$enabledIds.prop("checked", true);
				if ($enabledIds.filter(":checked").size() > 0) {
					$checkButton.removeClass("disabled");
					$recommendButton.removeClass("disabled");
					$unfreezeButton.removeClass("disabled");
				} else {
					$checkButton.removeClass("disabled");
					$recommendButton.removeClass("disabled");
					$unfreezeButton.removeClass("disabled");
				}
			} else {
				$enabledIds.prop("checked", false);
				$checkButton.addClass("disabled");
				$recommendButton.addClass("disabled");
				$unfreezeButton.addClass("disabled");
			}
		});
		
				
		//审核
		$checkButton.click(function(){
			var $this = $(this);
			if ($this.hasClass("disabled")) {
				return false;
			}
			var $checkedIds=$("#listTable input[name='ids']:enabled:checked");
			var content='请从以下选择以下审核选项！<br>'+
			   '<input name="status" type="radio" value="0" checked>未审核'+
			  '<input name="status" type="radio" value="1">审核通过<br>'+
			  '<input name="status" type="radio" value="2">审核不通过';	 
			$.dialog({
				type: "warn",
				content: content,
				onOk: function() {
					$.ajax({
						url: "check.jhtml",
						type: "GET",
						data:$checkedIds.serialize()+"&checkStatus="+$("input[name='status']:checked").val(),
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
		//推荐
		$recommendButton.click( function() {
			var $this = $(this);
			if ($this.hasClass("disabled")) {
				return false;
			}
			var $recommendIds=$("#listTable input[name='ids']:enabled:checked");
			var content='请从以下选择以下推荐选项！<br>'+
			   '<input name="recommend" type="radio" value="0" checked>未推荐'+
			  '<input name="recommend" type="radio" value="1">推荐';	 
			$.dialog({
				type: "warn",
				content: content,
				onOk: function() {
					$.ajax({
						url: "recommend.jhtml",
						type: "GET",
						data:$recommendIds.serialize()+"&recommend="+$("input[name='recommend']:checked").val(),
						dataType: "json",
						cache: false,
						success: function(message) {
						   $.message(message);
							$recommendButton.addClass("disabled");
							$selectAll.prop("checked", false);
							$recommendIds.prop("checked", false);
							location.reload(true);
						}
					});
				}
			});
		});
		
		//解冻
		$unfreezeButton.click( function() {
			var $this = $(this);
			if ($this.hasClass("disabled")) {
				return false;
			}
			var $unfreezeIds=$("#listTable input[name='ids']:enabled:checked");
			var content='请从以下选择以下解冻选项！<br>'+
			   '<input name="unfreeze" type="radio" value="0" checked>解冻'+
			  '<input name="unfreeze" type="radio" value="1">冻结';	 
			$.dialog({
				type: "warn",
				content: content,
				onOk: function() {
					$.ajax({
						url: "unfreeze.jhtml",
						type: "GET",
						data:$unfreezeIds.serialize()+"&unfreeze="+$("input[name='unfreeze']:checked").val(),
						dataType: "json",
						cache: false,
						success: function(message) {
						   $.message(message);
							$unfreezeButton.addClass("disabled");
							$selectAll.prop("checked", false);
							$unfreezeIds.prop("checked", false);
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
				$recommendButton.removeClass("disabled");
				$unfreezeButton.removeClass("disabled");
			} else {
				$this.closest("tr").removeClass("selected");
				if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
					$checkButton.removeClass("disabled");
					$recommendButton.removeClass("disabled");
					$unfreezeButton.removeClass("disabled");
				} else {
					$checkButton.addClass("disabled");
					$recommendButton.addClass("disabled");
					$unfreezeButton.addClass("disabled");
				}
			}
		});
		
		// 商户筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if($this.attr("name")=='auditSwitch')
		{
			if ($this.hasClass("checked"))
			{
				$dest.val("");
			}else {
			
				$auditSwitch.val($this.attr("name",'auditSwitch').attr("val"));
			}
			
		}
		if($this.attr("name")=='recommend')
		{
			if ($this.hasClass("checked"))
			{
				$dest.val("");
			}else {
			
				$recommend.val($this.attr("name",'recommend').attr("val"));
			}
			
		}
		$listForm.submit();
		return false;
		
		
	});
		
		
	});
	
	
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.biz.merchant.list")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="checkButton" class="iconButton disabled">
					<span class="checkIcon">&nbsp;</span>${message("admin.biz.merchant.check")}
				</a>
				<a href="javascript:;" id="recommendButton" class="iconButton disabled">
					<span class="recommendIcon">&nbsp;</span>${message("admin.biz.merchant.recommended")}
				</a>
				<a href="javascript:;" id="unfreezeButton" class="iconButton disabled">
					<span class="unfreezeIcon">&nbsp;</span>${message("admin.biz.merchant.unfreeze")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						${message("admin.biz.merchant.checkStatus")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;"  name="auditSwitch" [#if   auditSwitch =='3' ]  class="checked" [/#if] val="3">${message("admin.biz.merchant.all")}</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="auditSwitch" [#if  auditSwitch =='0' ]  class="checked" [/#if] val="0">${message("admin.biz.merchant.unchecked")}</a>
							</li>
							<li>
								<a href="javascript:;" name="auditSwitch"  [#if   auditSwitch =='1' ]  class="checked" [/#if] val="1">${message("admin.biz.merchant.checked")}</a>
							</li>
							<li>
								<a href="javascript:;" name="auditSwitch" [#if   auditSwitch =='2' ]  class="checked" [/#if] val="2">${message("admin.biz.merchant.checkedFail")}</a>
							</li>
							
							<li class="separator">
								<a href="javascript:;" name="recommend" [#if recommend?? && recommend =='1'] class="checked"[/#if] val="1">${message("admin.biz.merchant.recommended")}</a>
							</li>
							<li>
								<a href="javascript:;"  name="recommend" [#if recommend?? && recommend =='0'] class="checked"[/#if] val="0">${message("admin.biz.merchant.unrecommended")}</a>
							</li>
						</ul>
						<input type="hidden" id="auditSwitch" name="auditSwitch" value="${auditSwitch}" />
						<input type="hidden" id="recommend" name="recommend" value="${recommend}" />
					</div>
				</div>
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
							<a href="javascript:;"[#if page.searchProperty =="name"] class="current"[/#if] val="name">${message("admin.biz.merchant.name")}</a>
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
				<th>
					<a href="javascript:;" class="sort" name="name">${message("admin.biz.merchant.name")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="auditSwitch">${message("admin.biz.merchant.auditSwitch")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="recommend">${message("admin.biz.merchant.recommendStatus")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="account">${message("admin.biz.account.status")}</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as mer]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${(mer.id)!}" />
					</td>
					<td>
						${(mer.name)!}
					</td>
					<td>
						[#if mer.auditSwitch??&&mer.auditSwitch=='1']
							<span class="trueIcon"></span>
						[#elseif mer.auditSwitch??&&mer.auditSwitch=='2']
							<span class="falseIcon"></span>
						[#else]
							${message("admin.biz.merchant.unchecked")}	
						[/#if]
					</td>
					<td>
						[#if mer.recommend??&&mer.recommend=='1']
							<font color="#00FFFF">${message("admin.biz.merchant.recommended")}</font>
						[#else]
							${message("admin.biz.merchant.unrecommended")}	
						[/#if]
					</td>
					<td>
						[#if mer??]
							[#if mer.account??]
								[#if mer.account.status??&&mer.account.status=='0']
									<font color="#FF0000">${message("admin.biz.account.freeze")}</font>
								[#else]
									${message("admin.biz.account.unfreeze")}
								[/#if]
							[/#if]
						[/#if]
					</td>
					<td>
						<a href="edit.jhtml?id=${(mer.id)!}" >[${message("admin.common.edit")}]</a>
						<a href="view.jhtml?id=${(mer.id)!}" >[${message("admin.common.view")}]</a>
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