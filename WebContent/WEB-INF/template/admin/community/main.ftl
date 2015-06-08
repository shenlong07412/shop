[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.main.title")} - Powered By SHOP++</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<style type="text/css">
*{
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
</style>
<link rel="stylesheet" href="${base}/resources/admin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/resources/admin/zTree/js/jquery.ztree.core-3.4.js"></script>
<SCRIPT type="text/javascript">		
		var setting = {    
            data: {    
                simpleData: {    
                    enable: true  
//                  idKey:"id",  
//                  pIdKey:"pId",  
                }    
            }    
            ,async: {    
                enable: true,    
                url:"${base}/admin/area/listAjax.jhtml?hasCommunity=${hasCommunity!}",    
                autoParam:["treeId", "name"],    
                otherParam:{"otherParam":"zTreeAsyncTest"},    
//              dataType: "text",//默认text  
                type:"get",//默认post  
                dataFilter: filter  //异步返回后经过Filter  
            }  
            ,callback:{  
//              beforeAsync: zTreeBeforeAsync,      // 异步加载事件之前得到相应信息    
                asyncSuccess: zTreeOnAsyncSuccess,//异步加载成功的fun    
                asyncError: zTreeOnAsyncError,   //加载错误的fun    
                beforeClick:beforeClick //捕获单击节点之前的事件回调函数  
            }  
        };    
        //treeId是treeDemo  
        function filter(treeId, parentNode, childNodes) {    
            if (!childNodes) return null;    
            for (var i=0, l=childNodes.length; i<l; i++) {    
                childNodes[i].name = childNodes[i].name.replace('','');    
            }    
            return childNodes;    
        }    
          
        function beforeClick(treeId,treeNode){  
            if(!treeNode.isParent){
            	
            
            	$("#iframe").attr("src","${methodString!}?${IdName!'id'}="+treeNode.treeId);
            	
            
                return false;  
            }else{  
                return true;  
            }  
        }  
          
        function zTreeOnAsyncError(event, treeId, treeNode){    
            alert("异步加载失败!");    
        }    
          
        function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){    
              
        }  
          
        /***********************当你点击父节点是,会异步访问servlet,把id传过去*****************************/  
        var zNodes=[];  
        var zNodes =[     
            { treeId:0, pId:0, name:"选择地区", isParent:true}   
        ];  
    
        $(document).ready(function(){
            
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
	</SCRIPT>

</head>
<body>
	
	<table class="main">
		<tr>
			<td id="menu" style="border-right:1px solid #a3c4d7;background-color:#f5faff;" width="15%">				
				<dl id="product">			
					<dd><ul id="treeDemo" class="ztree"></ul><dd>
				</dl>	
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="${methodString!}" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>