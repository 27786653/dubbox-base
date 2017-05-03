 		var gridObj;
        $(function () {
            gridObj = $.fn.bsgrid.init('searchTable', {
                url: 'aaa.json',
                // autoLoad: false,
                ajaxType: 'GET',
                stripeRows: true,
                pageSizeSelect: true,
                multiSort: true, // multi column sort support, default false
                pageSize: 15
            });
        
        });

        function operate(record, rowIndex, colIndex, options) {
            return '<a href="#" onclick="alert(\'ID=' + gridObj.getRecordIndexValue(record, 'ID') + '\');">Operate</a>';
        }			
			
        function idoperate(record, rowIndex, colIndex, options) {
            return '<a href="#" onclick="parent.layer.alert(\'ID=' + gridObj.getRecordIndexValue(record, 'ID') + '\');">'+record.ID+'</a>';
        }
        
		function deleteObj(){
			var indexary=gridObj.getCheckedRowsIndexs();
			debugger
			if(indexary&&indexary.length==1){
				var objary=gridObj.getCheckedValues(0);
				
			}else{
				parent.layer.alert("请选择一条数据！");
			}
		}	

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
			
		layui.use(['icheck', 'laypage','layer'], function() {
			var $ = layui.jquery,
				laypage = layui.laypage,
				layer = parent.layer === undefined ? layui.layer : parent.layer;
			$('input').iCheck({
				checkboxClass: 'icheckbox_flat-green'
			});

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