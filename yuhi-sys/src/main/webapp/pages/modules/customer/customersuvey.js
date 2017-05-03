 		var citynodes = [];  
 		
        $(function () {
            var zTreeDemo = $.fn.zTree.init($("#treeDemo"),setting, citynodes);  
        });

		/**初始化树参数**/
        var setting = {  
        		 check: { /**复选框**/  
        		  enable: true,  
        		  chkboxType: {"Y":"", "N":""},
        		  nocheckInherit:true,
        		  
        		 },  
        		 view: {                                    
        		  //dblClickExpand: false,  
        		  fontCss: function(treeId, treeNode) {
        				return treeNode.level == 0 ? {color:"#BD2222"} : {};
        		  },
        		  expandSpeed: 300 //设置树展开的动画速度，IE6下面没效果，  
        		 },                            
        		 data: {                                    
        		  simpleData: {   //简单的数据源，一般开发中都是从数据库里读取，API有介绍，这里只是本地的                           
        		   enable: true,  
        		   idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别  
        		   pIdKey: "pid",  
        		   rootPId: 0   //根节点  
        		  }                            
        		 },async: { 
        			 enable: true, 
        			 url:"wyroom/allroom.json", 
        			 autoParam:["id", "name"], 
        			 otherParam:{"otherParam":"zTreeAsyncTest"}, 
        			 // dataType: "text",//默认text 
        			 type:"get",//默认post 
        			 dataFilter: function(treeId, parentNode, data){
        				 for (var int = 0; int < data.length; int++) {
							if(data[int].pid==0){
								data[int].icon="resource/images/house.png";
							}
						}
        				 return data;
        			 } 
        		},
        		 callback: {     
        			 //onClick:resetGrid
        		 }  
        		};  
        /**激活元素基本交互**/
        layui.use(['element'], function() {
			var element = layui.element();
			
			  element.on('tab(fromtab)', function(data){
			    console.log(data);
			  });
        });
        