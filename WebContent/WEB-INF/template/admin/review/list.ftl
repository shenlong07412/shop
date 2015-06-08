<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.review.list")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">

var content='请从以下选择一个审核选项！<br>'+
					   '<input name="status" type="radio" value="true" checked>显示'+
					   '<input name="status" type="radio" value="">不显示<br>';	
$().ready(function() {
	
	var $listForm = $("#listForm");
	var $type = $("#type");
	var $typeSelect = $("#typeSelect");
	var $typeOption = $("#typeOption a");
	var $selectAll = $("#selectAll");
	var $checkButton = $("#checkButton");
	var $ids = $("#listTable input[name='ids']");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	[@flash_message /]
	
	$typeSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$typeOption.click(function() {
		var $this = $(this);
		$type.val($this.attr("val"));
		$listForm.submit();
		return false;
	});	
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
					data:$checkedIds.serialize()+"&isShow="+$("input[name='status']:checked").val(),
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
	
	$filterSelect.mouseover( function() {
			var $this = $(this);
			var offset = $this.offset();
			var $menuWrap = $this.closest("div.menuWrap");
			var $popupMenu = $menuWrap.children("div.popupMenu");
			$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
			$menuWrap.mouseleave(function() {
				$popupMenu.hide();
			});
		});
		
		$filterOption.click( function() {
			var $this = $(this);		
			$("#isShow").val($this.attr("val"));
			document.getElementById("listForm").action="${base}/admin/review/list.jhtml"; 
			document.getElementById("listForm").submit();				
		});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.review.list")} <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" id="type" name="type" value="${type}" />
		<div class="bar">
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
					<a href="javascript:;" id="typeSelect" class="button">
						${message("admin.review.type")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="typeOption">
							<li>
								<a href="javascript:;"[#if type == null] class="current"[/#if] val="">${message("admin.review.allType")}</a>
							</li>
							[#assign currentType = type]
							[#list types as type]
								<li>
									<a href="javascript:;"[#if type == currentType] class="current"[/#if] val="${type}">${message("Review.Type." + type)}</a>
								</li>
							[/#list]
						</ul>
					</div>
				</div>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						是否显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption">
							<li>
								<a href="javascript:;" [#if !isShow??]  class="current" [/#if] val="">全部</a>
							</li>
							<li>
								<a href="javascript:;" [#if isShow??&&isShow=='true']  class="current" [/#if] val="true">显示</a>
							</li>
							<li>
								<a href="javascript:;" [#if isShow??&&isShow!='true']  class="current" [/#if] val="false">不显示</a>
							</li>
						</ul>
					</div>
					<input type="hidden" id="isShow" name="isShow" value="${isShow!}"/>
					
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
							<a href="javascript:;"[#if page.searchProperty == "content"] class="current"[/#if] val="content">${message("Review.content")}</a>
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
					<a href="javascript:;" class="sort" name="product">${message("Review.product")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="score">${message("Review.score")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="content">${message("Review.content")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">${message("Review.member")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isShow">${message("Review.isShow")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as review]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${review.id}" />
					</td>
					<td>
						<a href="${base}${review.product.path}" title="${review.product.name}" target="_blank">${abbreviate(review.product.name, 50, "...")}</a>
					</td>
					<td>
						${review.score}
					</td>
					<td>
						<span title="${review.content}">${abbreviate(review.content, 50, "...")}</span>
					</td>
					<td>
						[#if review.member??]
							${review.member.username}
						[#else]
							${message("admin.review.anonymous")}
						[/#if]
					</td>
					<td>
						<span class="${review.isShow?string("true", "false")}Icon">&nbsp;</span>
					</td>
					<td>
						<span title="${review.createDate?string("yyyy-MM-dd HH:mm:ss")}">${review.createDate}</span>
					</td>
					<td>
						<a href="edit.jhtml?id=${review.id}">[${message("admin.common.edit")}]</a>
						<a href="${base}${review.path}" target="_blank">[${message("admin.common.view")}]</a>
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