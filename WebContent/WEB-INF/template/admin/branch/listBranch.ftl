<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.branch.list")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"  type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/zTree/js/jquery.ztree.core-3.4.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/zTree/js/jquery.ztree.exedit-3.4.min.js"></script>
<script type="text/javascript">
var setting = {
	view: {addHoverDom: addHoverDom,removeHoverDom: removeHoverDom},
	edit: {enable: true,showRemoveBtn: showRemoveBtn,showRenameBtn:false},
	callback: {onClick: zTreeOnDblClick,onRemove: onRemove}
};
function zTreeOnDblClick(event, treeId, treeNode) {
	$('#detail').attr("src",'${base}/admin/branch/edit.jhtml?id='+treeNode.id);
};
function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				$('#detail').attr("src",'${base}/admin/branch/add.jhtml?parentId='+treeNode.id);
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};

function showRemoveBtn(treeId, treeNode) {
	return treeNode.children.length==0;;
}
function onRemove(e, treeId, treeNode) {
			$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {ids: treeNode.id},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
					}
				});
			}
		});
}

var zNodes =${tree};
$().ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	[@flash_message /]

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.branch.list")}
	</div>
	<div class="container">
		<div class="span6 nav">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div></div>
		<div class="span18 last">
		<iframe id="detail" src="${base}/admin/branch/add.jhtml" frameborder="0" scrolling="no" width="100%" height="800px"></frame>
		</div>
	</div>
</body>
</html>