 		var gridObj;
 		var citynodes = [];  
 		
        $(function () {
            gridObj = $.fn.bsgrid.init('searchTable', {
                url: 'wybasecustomer/datagird.json',
                // autoLoad: false,
                ajaxType: 'GET',
                stripeRows: true,
                pageSizeSelect: true,
                displayBlankRows: false,
                multiSort: true, // multi column sort support, default false
                pageSize: 15
            });
    		var bW=document.documentElement.offsetWidth;
    		$(".layui-field-box").css({width:bW-35,display:"block"});
        });
        		
        /**grid相关**/		  
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
			var index=gridObj.getCheckedRowsIndexs();
			if(index!=-1){
				var obj= gridObj.getRecord(index);
				debugger
				parent.layer.open({
					title: '完善房间信息',
					maxmin: true,
					type: 2,
					content: 'wybasecustomer.html?update&id='+obj.id,
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
					content: 'wybasecustomer.html?update',
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
