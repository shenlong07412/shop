<link rel="stylesheet" href="${base}/resources/admin/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/resources/admin/zTree/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="${base}/resources/admin/zTree/js/jquery.ztree.excheck-3.4.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/json2.js"></script>

<SCRIPT type="text/javascript">		
		
        var cTree;
        var czNodes=[];  
        var czNodes =[     
            { treeId:0, pId:0, name:"选择商品类别", isParent:true}   
        ];  
 
 
        function   refreshParent(t){
        		
        		var pn = t.getParentNode();
        		if(pn==null){
        			return;
        		}else{
        			
        			if(!pn.checked){
        				pn.halfCheck = false;
        			}else{
        				pn.halfCheck = true;
        			}
        			refreshParent(pn);
        		}
 		         
        }
 
 		function onCheck1(e, treeId, treeNode) {
 				treeNode.halfCheck = false;
 		        refreshParent(treeNode);

 		        
		     	var _nodes = cTree.getCheckedNodes(true);
				 $("#cids").val(JSON.stringify(_nodes));
					 
		 }
		function initCTree(){
			$.ajax({
				type: 'POST',
				dataType : "json",
				url:  "${base}/admin/productMerchantCategory/getCategroy.jhtml",
				error: function () {//请求失败处理函数
					alert('数据异常，请联系管理员');
				},
				success:function(data){ //请求成功后处理函数。 
					 cTree.addNodes(null, data);
					 initValue();
				}
			});
		}
	   var cArray= null;
	   function initValue(){
			//传入初值
			[#if jsonStr??]
			    cArray = ${jsonStr};
			[/#if]
			 
			if(cArray!=null){
				var _nodes = cTree.transformToArray(cTree.getNodes());
				for(var i=0;i<cArray.length;i++){
					for(var j = 0;j<_nodes.length;j++){
						 
				       if(cArray[i].id == _nodes[j].treeId){
				        if(cArray[i].status==2){
				        	_nodes[j].halfCheck = true;
				        }else{
				           _nodes[j].halfCheck = false;
				        }
					    cTree.checkNode(_nodes[j], true, false);
				       }
			        }
				}
			}
		    
			var _nodes = cTree.getCheckedNodes(true);
			$("#cids").val(JSON.stringify(_nodes));
		
		}
		
        $(document).ready(function(){       
        
          var csetting = {    
            data: {    
                simpleData: {    
                    enable: true ,
                    idKey:"treeId",  
                  pIdKey:"parentId"  
                }    
            }, 
			callback: {
				onCheck: onCheck1
			},
			view: {
				dblClickExpand: false
			},
			check: {
				enable: true,
				chkboxType: {"Y":"ps", "N":"ps"}
			}
          };    
        
 
 
		   cTree = $.fn.zTree.init($("#categoryTree"), csetting);
		   initCTree();
 
        });
	</SCRIPT>
