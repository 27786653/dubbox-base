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
			});
			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});

				//page
				/*laypage({
					cont: 'page',
					pages: 25, //总页数,
					groups: 5, //连续显示分页数,
					jump: function(obj, first) {
						parent.layer.alert(obj.curr)
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});*/
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
			
			/* datatable */
			var pagesize=1;
			layui.config({
				base: 'resource/js/'
			});

			layui.use(['begtable','layer'], function() {
				var $ = layui.jquery,
				laypage = layui.laypage,
				layer = parent.layer === undefined ? layui.layer : parent.layer;
				
				var begtable = layui.begtable(),
					layer = layui.layer,
					$ = layui.jquery,
					laypage = layui.laypage;
				var columns = [{
					title: '用户名',
					field: 'name'
				}, {
					title: '年龄',
					field: 'age'
				}, {
					title: '是否启用',
					field: 'isEnable',
					format: function(value) {

					}
				}, {
					title: '性别',
					field: 'sex'
				}, {
					title: '备注',
					field: 'remarks'
				}];
				
				var data=getdata(1);
				
				var table=begtable.set({
					bordered: true,
					hovered: true,
					striped: true,
					elem: '#xx',
					columns: columns,
					total:7,
					data: data,
					checked: true,
					loaddatas:function(i){
						console.log(laypage);
						begtable.regexTable(getdata(i),i,10);
					}
					//paged:false
				}).init();

				$('#update').on('click', function() {
					var elemary=table.getSelectedData(true);
					if(elemary.length!=1){
						parent.layer.alert('请选择一条数据');
						return false;
					}
					layer.open({
						title: '完善房间信息',
						maxmin: true,
						type: 2,
						content: 'wyroom.html?update&id='+elemary[0].id,
						area: ['800px', '500px']
					});
				});
				
				$('#delete').on('click', function() {
					var elemary=table.getSelectedData();
					if(elemary.length!=1){
						parent.layer.alert('请选择一条数据');
						return false;
					}
					parent.layer.confirm('确定该操作？', function(){
							parent.layer.alert(elemary[0].id);
							parent.layer.msg('数据处理中..', {
								  icon: 16
								  ,shade: 0.01
								});
						});
					
					});
					
//					parent.layer.alert(elemary[0].id);
				
				$('#search').on('click', function() {
//					parent.layer.alert('你点击了搜索按钮');
					parent.layer.msg('加载中', {
						  icon: 16
						  ,shade: 0.01
						});
				});
//					
				$('#add').on('click', function() {
						layer.open({
							title: '完善房间信息',
							maxmin: true,
							type: 2,
							content: 'wyroom.html?update',
							area: ['800px', '500px']
						});
				});
				
				$('#import').on('click', function() {
					var that = this;
					var index = layer.tips('只想提示地精准些', that,{tips: [1, 'white']});
					$('#layui-layer'+index).children('div.layui-layer-content').css('color','#000000');
				});
				
				
				//数据获取
				function getdata(page,total){
					var data = [];
					for (var int = 0; int < page; int++) {
						data.push({
							id:int,
							name: '张三',
							age: 19,
							sex: '男',
							isEnable: true,
							remarks: 'aaa'
						});
					}
					return data;
					/*$.ajax({
						url:'wyroom/datagird/'+page+'/'+10+'.json',
						data:{"pageNum":1,"pagesize":2},
						success:function(data){
							debugger
							return data;
						}
					});*/
				}
				
			});
			
			
			
			
			
			