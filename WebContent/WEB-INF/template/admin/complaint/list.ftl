<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>投诉报修  - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	[@flash_message /]
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
	
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
				$("#status").val($this.attr("val"));
				document.getElementById("listForm").action="${base}/admin/complaint/list.jhtml"; 
				document.getElementById("listForm").submit();				
			});
});
</script>
</head>
<body>

	<div class="path">
		<a href="${base}/admin/complaint/list.jhtml">投诉报修 </a> &raquo; 列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<!--<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>-->
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						处理状态<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption">
							<li>
								<a href="javascript:;" [#if  status=='']  class="current" [/#if] val="">全部</a>
							</li>
							<li>
								<a href="javascript:;" [#if  status =='0']  class="current" [/#if] val="0">未处理</a>
							</li>
							<li>
								<a href="javascript:;" [#if  status =='1']  class="current" [/#if] val="1">处理中</a>
							</li>
							<li>
								<a href="javascript:;" [#if  status =='2']  class="current" [/#if] val="2">已结束</a>
							</li>
						</ul>
					</div>
					<input type="hidden" id="status" name="status" value="${status!}"/>
					
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
							<a href="javascript:;"[#if page.searchProperty == "content"] class="current"[/#if] val="content">内容</a>							
						</li>
						<li>
							<a href="javascript:;"[#if page.searchProperty == "person"] class="current"[/#if] val="person">报修人</a>
						</li>
					</ul>
					<input type="hidden" id="SearchProperty" name="SearchProperty" value="${page.searchProperty!}"/>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th width="8%">
					<a href="javascript:;" class="sort" name="type">申请类型</a>
				</th>
				<!--<th width="8%">
					<a href="javascript:;" class="sort" name="systemCodeDetail">报修类型</a>
				</th>-->
				<th width="15%">
					<a href="javascript:;" class="sort" name="content">内容</a>
				</th>
				<th width="13%">
					<a href="javascript:;" class="sort" name="community">所属社区</a>
				</th>
				<th width="10%">
					<a href="javascript:;" class="sort" name="person">报修人</a>
				</th>
				<th width="10%">
					<a href="javascript:;" class="sort" name="phone">联系电话</a>
				</th>
				<th  width="10%">
					<a href="javascript:;" class="sort" name="status">处理状态</a>
				</th>
				<th  width="5%">
					<a href="javascript:;" class="sort" name="isPublic">是否公开</a>
				</th>
				<th  width="10%">
					<a href="javascript:;" class="sort" name="createDate">提交时间</a>
				</th>
				<th  width="10%">
					<a href="javascript:;" class="sort" name="supportNumber">支持人数</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as complaint]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${complaint.id}" />
					</td>
					<td>
						[#if (complaint.type)??&&(complaint.type)==COMPLAINT]
							投诉
     					[#else]
							报修
     					[/#if]
						<!--${(complaint.systemCodeDetail.name)!}-->
					</td>
					<!--<td>
						[#if (complaint.systemCodeDetail.codeDetail)??&&(complaint.systemCodeDetail.codeDetail)==COMPLAINT]
							--
     					[#else]
							${(complaint.systemCodeDetail.name)!}
     					[/#if]
						
					</td>-->
					<td>
						[#if complaint.content?length lt 13]${(complaint.content)!}
     					[#else]${(complaint.content)[0..12]}...
     					[/#if]
					</td>
					<td>
						${(complaint.community.name)!}
					</td>
					<td>
						${(complaint.person)!}
					</td>
					<td>
						${(complaint.phone)!}
					</td>
					<td>
						[#if (complaint.status)??&&(complaint.status)==0]
							<font color="red">未处理 <font>
     					[/#if]
     					[#if (complaint.status)??&&(complaint.status)==1]
							<font color="black">处理中<font>
     					[/#if]
     					[#if (complaint.status)??&&(complaint.status)==2]
							<font color="green">已结束<font>
     					[/#if]
					</td>
					<td>
						[#if complaint.isPublic??&&complaint.isPublic==0]<span class="falseIcon">&nbsp;</span>
     					[#else]<span class="trueIcon">&nbsp;</span>
     					[/#if]
					</td>
					<td>
						${(complaint.createDate)!}
					</td>
					
					<td>
						[#if (complaint.type)??&&(complaint.type)==COMPLAINT]
						${(complaint.supportNumber)!}
						[#else]
							/
     					[/#if]
					</td>
					<td>
						[#if (complaint.status)??&&(complaint.status)==2]
							<a href="edit.jhtml?id=${complaint.id}">[查看]</a>
     					[#else]
							<a href="edit.jhtml?id=${complaint.id}">[处理]</a>
     					[/#if]
						
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