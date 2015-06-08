<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>社区审核 - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<SCRIPT type="text/javascript">
 function secBoard(name,n) {
	$("."+name).each(function(index, element) {
		if (index == n) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
  }  
        $(document).ready(function(){
            var $inputForm = $("#inputForm");
        	$inputForm.validate({
				rules: {
					areaName: "required",
					name:"required",
					longitude:{required: true,number:true},
					latitude:{required:true,number:true},
					radius:{required: true,number:true},
					address:"required"
				}
			});
			
			if(trim($('#recruitMap').val())==''){//地图没构建范围
				return;
			}else{
				[#if community.json??||community.json!='']
					var o_points = ${(community.json)!''};
				[#else]
					var o_points = [];
				[/#if]
				
				for(var i = 0; i < o_points.length; i++){
	 
					 var polygon = new BMap.Polygon(o_points[i],styleOptions);
					 map.addOverlay(polygon);
					 overlays.push(polygon);
					 polygons.push(polygon);
				}
			}  
        });
	</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/community/listAudit.jhtml">社区审核</a> &raquo;详情
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" onclick="javascript:secBoard(tabContent,0);"/>
			</li>
			<li>
				<input type="button" value="社区地图"  onclick="javascript:secBoard(tabContent,1);"/>
			</li>			
		</ul>
		<table class="input tabContent">
		<input type="hidden" name="id" value="${(community.id)!}">
		<input type="hidden" name="type" value="Audit">
			<tr>
			    <th width="15%">
					${message("Tag.name")}
				</th>
				<td width="35%">
					${(community.name)!}
					<input type="hidden" value="${(community.name)!}" name="name" class="text" maxlength="200" />
				</td>
				<th>
					地区
				</th>
				<td>
					${(community.area.fullName)!}
					<input type="hidden" value="${(community.area.id)!}" name="areaId" class="text" maxlength="200" id="areaId"/>
				</td>
				
			</tr>
			
			<tr>
				<th>
					经度
				</th>
				<td>
					${(community.longitude)!}
					<input type="hidden" value="${(community.longitude)!}" name="longitude" class="text" maxlength="200" />
				</td>
				<th>
					维度
				</th>
				<td>${(community.latitude)!}
					<input type="hidden" value="${(community.latitude)!}" name="latitude" class="text" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					覆盖半径
				</th>
				<td>${(community.radius)!}
					<input type="hidden" value="${(community.radius)!}" name="radius" class="text" maxlength="200" />
				</td>
				<th>
					地址
				</th>
				<td>${(community.address)!}
					<input type="hidden"  value="${(community.address)!}" name="address" class="text" maxlength="9" />
				</td>
			</tr>
			
			<tr>
				<th>
					社区简介
				</th>
				<td colspan="3">${(community.introduction)!}
					<textarea name="introduction" style="display:none">${(community.introduction)!}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					创建时间
				</th>
				<td colspan="3">${(community.submittedDate)!}
					<!--<textarea name="introduction" style="width:30%;height:100px">${(community.introduction)!}</textarea>-->
				</td>
			</tr>
			<tr>
				<th>
					审核
				</th>
				<td>
					<select name="status">
						<option value=1 [#if (community.status)==1]selected[/#if]>待审</option>
						<option value=2 [#if (community.status)==2]selected[/#if]>审核通过</option>
						<option value=3 [#if (community.status)==3]selected[/#if]>审核驳回</option>
						<option value=4 [#if (community.status)==4]selected[/#if]>草稿</option>
					</select>
				</td>
				
				<th>
					审核时间
				</th>
				<td>
					${(community.auditedDate)!}
				</td>
			</tr>
			<tr>
				<th>
					审核意见
				</th>
				<td colspan="3">
					<textarea name="opinion" style="width:30%;height:100px">${(community.opinion)!}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="3">
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='listAudit.jhtml'" />
				</td>
			</tr>
			
		</table>
		<table class="input tabContent">
			<tr>
			    <th>
				社区范围:
				</th>
				<td >
				<input type="hidden" name="recruitMap" id="recruitMap" value="${(community.json)!}" />
					<div id="map" style="height:400px;width:600px "></div>
				</td>
			</tr>
		</table>
	</form>
	
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=STHe7B2Hplddni6kBUVLAeka"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />

<!-- 加载百度地图end -->
<script type="text/javascript" src="${base}/resources/admin/js/json2.js"></script>	
	<script type="text/javascript">

// 百度地图API功能
    var map = new BMap.Map('map');
    var poi = new BMap.Point(${(community.longitude)!},${(community.latitude)!});
    map.centerAndZoom(poi, 16);
    map.enableScrollWheelZoom();
    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
  
    var overlays = [];
    //回调获得覆盖物信息
    var overlaycomplete = function(e){
        overlays.push(e.overlay);
         
       // $("showOverlayInfo").style.display = "none";
       // $("panel").innerHTML += result; //将绘制的覆盖物信息结果输出到结果面板
    };
	var polygons = [];
    var polygoncomplete = function(overlay){
	    polygons.push( overlay);
	}
    

    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
	 
        enableDrawingTool: false, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
            scale: 0.8, //工具栏缩放比例
			drawingTypes:[
 
            BMAP_DRAWING_POLYGON
 
           ]
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });

    
    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    drawingManager.addEventListener('polygoncomplete', polygoncomplete);

 

    function clearAll() {
        for(var i = 0; i < overlays.length; i++){
            map.removeOverlay(overlays[i]);
        }
		overlays = [];
		polygons = [];
        overlays.length = 0;
        polygons.length = 0;

    }


	function getPoints(){
		if(polygons.length==0){
			$("#recruitMap").val("");
			return;
		}
		var points =[];		
		for(var i = 0; i < polygons.length; i++){
			points.push(polygons[i].getPath());
			
        }	  		
		 $("#recruitMap").val(JSON.stringify(points));
 

	}
	
	function trim(str){ 
	  return str.replace(/^\s+|\s+$/g,''); 
} 

</script>
</body>
</html>