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
                url:"${base}/admin/area/listAjax.jhtml",    
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
            	$("#areaName").val(treeNode.name);
            	$("#areaId").val(treeNode.treeId);
            	$("#treeDemo_1_switch").click();
               // alert("请选择父节点");
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
