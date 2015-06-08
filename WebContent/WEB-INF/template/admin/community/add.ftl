<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>社区  - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>

<SCRIPT type="text/javascript">	
function saveContent(){
	getPoints();
	$('#inputForm').submit();
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
        });
        
function getAdress(){
	var areaId=$("#areaId").val();
	if($("#address").val()==null||$("#address").val()==""){
		if(areaId!=null&&areaId!=""){
			$.ajax({
				url: "${base}/admin/area/getAjax.jhtml",
				type: "GET",
				data: {id:areaId},
				dataType: "json",
				cache: false,
				success: function(message) {
					$("#address").val(message);
				}
			});
		}
	}
}
 function secBoard(name,n) {
	$("."+name).each(function(index, element) {
		if (index == n) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
  }
  
function openArea(){
    var content='<iframe style="overflow:scroll;overflow-x:hidden"  name="iframe" src="tree.jhtml" frameborder="0" height="800"></iframe>';
	var $dialog = $.dialog({
		title:"选择地区",
		content:content,
		width: 200,
		height: 400,
		modal: true,
		ok: null,
		cancel: null
	});
}
	</SCRIPT>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/community/list.jhtml">社区</a> &raquo;添加社区
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="社区基本信息" onclick="javascript:secBoard(tabContent,0);"/>
			</li>
			<li>
				<input type="button" value="社区简介"  onclick="javascript:secBoard(tabContent,1);"/>
			</li>
			<li>
				<input type="button" value="社区范围"  onclick="javascript:secBoard(tabContent,2);"/>
			</li>
		</ul>
		
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>选择地区:
				</th>
				<td>
					<input type="text" name="areaName" class="text" maxlength="200" id="areaName" readonly onclick="openArea()" value="${(area.name)!}"/>
					<input type="hidden" name="areaId" class="text" maxlength="200" id="areaId" value="${(area.id)!}"/>										
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Tag.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" id="communityName"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>经度
				</th>
				<td>
					<input type="text" name="longitude" id="longitude1" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>维度
				</th>
				<td>
					<input type="text" name="latitude" id="latitude1" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>覆盖半径
				</th>
				<td>
					<input type="text" name="radius" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>地址
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="50" id="address" onclick="getAdress();"/>
					<input type="button" class="button" onclick="searchMap();" value="搜索地图" />
				</td>
			</tr>	
		</table>
		
		<table class="input tabContent">
			<tr>
				<th>
					社区简介
				</th>
				<td>
					<textarea name="introduction" style="width:70%;height:100px"></textarea>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
			    <th>
				绘制选项:
				</th>
				<td >
					<label><input type="radio" name="openClose1" onclick="javascript:drawingManager.open();drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);"/>开始绘图</label>  <label><input type="radio" name="openClose1" onclick="drawingManager.close()" checked="checked"/>关闭绘图</label>
						   <input type="button"class="button"  onclick="clearAll()" value="清空绘图内容">
				</td>
			</tr>
			<tr>
			    <th>
				社区范围:
				</th>
				<td >
				<input type="hidden" name="recruitMap" id="recruitMap" value="" />
					<div id="map" style="height:400px;width:600px "></div>
				</td>
			</tr>
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" onclick="saveContent();" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml?areaId=${(area.id)!}'" />
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
    var poi = new BMap.Point(118.03928,24.490744);
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
	    polygons.push( overlay)
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
		  
		var onePoint= eval(polygons[0].getPath())[0];
		if($("#longitude1").val()==""||$("#longitude1").val()==null){
			$("#longitude1").val(onePoint.lng);
		} 
		 if($("#latitude1").val()==""||$("#latitude1").val()==null){
			$("#latitude1").val(onePoint.lat);
		}
		$("#recruitMap").val(JSON.stringify(points));
 

	}
function searchMap() {
	 map.setCenter($("#address").val());   //设置地图中心点。center除了可以为坐标点以外，还支持城市名
     map.setZoom(16);
}

</script>
</body>
</html>