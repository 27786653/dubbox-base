 		var gridObj;
 		var citynodes = [];  
 		
        $(function () {
            gridObj = $.fn.bsgrid.init('searchTable', {
                url: 'wyroom/datagird.json',
                // autoLoad: false,
                ajaxType: 'GET',
                stripeRows: true,
                pageSizeSelect: true,
                displayBlankRows: true,
                multiSort: true, // multi column sort support, default false
                pageSize: 15
            });
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
        		  simpleData: {   
        		   enable: true,  
        		   idKey: "id",  
        		   pIdKey: "pid",  
        		   rootPId: 0    
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
        			 onClick:resetGrid
        		 }  
        		};  
        	/**根据项目变化数据（树连动）**/
        		function resetGrid(event,treeId, treeNode) { 
        			var  searchParames = {};
        			switch (treeNode.level) {
					case 0:
						searchParames.managerId=treeNode.id;
						gridObj.search(searchParames);
						break;
					case 1:
						searchParames.buddingId=treeNode.id;
						gridObj.search(searchParames);
						break;
					default:
						break;
					}
        	        
        		}  
        		  
        		
        /**grid相关**/		  
        function fieldoperate(record, rowIndex, colIndex, options) {
        	switch (colIndex) {
			case 14:
				return record.r_flag>0?'<img src="resource/images/success.png" width="16px">':'<img src="resource/images/error.png" width="16px">';
			}
        }	
        
		function deleteObj(){
			var index=gridObj.getCheckedRowsIndexs();
			if(index!=-1){
				parent.layer.alert(index+'');
			}else{
				parent.layer.alert("请选择一条数据！");
			}
			//checkbox
//			var indexary=gridObj.getCheckedRowsIndexs();
//			if(indexary&&indexary.length==1){
//				var objary=gridObj.getCheckedValues(0);
//				parent.layer.alert(objary[0].id);
//			}else{
//				parent.layer.alert("请选择一条数据！");
//			}
		}	

		function updateobj() {
			var index=gridObj.getSelectedRowIndex();
			if(index!=-1){
				var obj= gridObj.getRecord(index);
				debugger
				parent.layer.open({
					title: '完善房间信息',
					maxmin: true,
					type: 2,
					content: 'wyroom.html?update&id='+obj.id,
					area: ['800px', '500px']
				});
			}else{
				parent.layer.alert("请选择一条数据！");
			}
		}
		
		function searchobj() {
//			parent.layer.alert('你点击了搜索按钮');
			parent.layer.msg('加载中', {
				  icon: 16
				  ,shade: 0.01
				});
			 // 提供以下三种带参数方式，可任选其中任意一种
	        var searchParames = $('#searchForm').serializeArray();      // jQuery推荐方式
	        // searchParames = 'param1=val1&param2=val2';
	        // searchParames = {'param1': 'val1', 'param2': 'val2'};
	        gridObj.search(searchParames);
		}
//			
		
		function addobj() {
			parent.layer.open({
					title: '完善房间信息',
					maxmin: true,
					type: 2,
					content: 'wyroom.html?update',
					area: ['800px', '500px']
				});
		}
		
		
		
		/**ui元素初始化**/
		layui.config({
			base: '/yuhi-sys/resource/plugins/layui/modules/'
		});
		layui.use(['form', 'layedit', 'laydate'], function() {
			var form = layui.form(),
				layer = layui.layer,
				layedit = layui.layedit,
				laydate = layui.laydate;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');
			//监听提交
			form.on('submit(demo1)', function(data) {
				layer.alert(JSON.stringify(data.field), {
					title: '最终的提交信息'
				})
				return false;
			});
		});;
			
		layui.use([ 'laypage','layer'], function() {
			var $ = layui.jquery,
				laypage = layui.laypage,
				layer = parent.layer === undefined ? layui.layer : parent.layer;

			$('.site-table tbody tr').on('click', function(event) {
				var $this = $(this);
				var $input = $this.children('td').eq(0).find('input');
				$input.on('ifChecked', function(e) {
					$this.css('background-color', '#EEEEEE');
				});
				$input.on('ifUnchecked', function(e) {
					$this.removeAttr('style');
				});
				$input.iCheck('toggle');
			}).find('input').each(function() {
				var $this = $(this);
				$this.on('ifChecked', function(e) {
					$this.parents('tr').css('background-color', '#EEEEEE');
				});
				$this.on('ifUnchecked', function(e) {
					$this.parents('tr').removeAttr('style');
				});
			});
			$('#selected-all').on('ifChanged', function(event) {
				var $input = $('.site-table tbody tr td').find('input');
				$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
			});
			
		});