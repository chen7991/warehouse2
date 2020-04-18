<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>供应商管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/style/admin.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body style=" background-color: #F2F2F2;">
<div style="padding: 20px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form action="" method="post" id="searchFrm" lay-filter="searchFrm" class="layui-form layui-form-pane">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">供应商</label>
                                <div class="layui-input-inline">
                                    <select name="providerid" id="searchProviderid">
                                        <option value="0">请选择供应商</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">商品名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="goodsname"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">生产批号</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="productcode"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">批准文号</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="promitcode"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">商品描述</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="description"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">商品规格</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="size"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block" style="text-align: center;">
                                <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="doSearch"><span class="layui-icon layui-icon-search"></span>查询</button>
                                <button type="reset" class="layui-btn layui-btn-warm"><span class="layui-icon layui-icon-refresh-1"></span>重置</button>
                                <button type="button" id="export" class="layui-btn"><span class="layui-icon layui-icon-download-circle"></span>导出</button>
                            </div>
                        </div>
                    </form>
                    <!-- 数据表格开始 -->
                    <div>
                        <table class="layui-hide" id="goodsTable" lay-filter="goodsTable"></table>
                        <div id="goodsToolBar" style="display: none;">
                            <button type="button" lay-event="add" class="layui-btn layui-btn-sm"><span class="layui-icon layui-icon-add-1"></span>添加商品</button>
                        </div>

                        <div id="goodsRowBar" style="display: none;">
                            <button type="button" lay-event="update" class="layui-btn layui-btn-sm"><span class="layui-icon layui-icon-edit"></span>更新</button>
                            <button type="button" lay-event="delete" class="layui-btn layui-btn-sm layui-btn-danger"><span class="layui-icon layui-icon-delete"></span>删除</button>
                        </div>
                    </div>

                    <!-- 数据表格结束 -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 5px" id="addOrUpdateDiv">
    <form action="" method="post" class="layui-form layui-form-pane" id="dataFrm" lay-filter="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <div class="layui-form-item magt3">
                        <label class="layui-form-label">供应商</label>
                        <div class="layui-input-block">
                            <select name="providerid" id="providerid">
                                <option value="0">请选择供应商</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-block">
                            <input type="hidden" name="id">
                            <input type="text" class="layui-input" name="goodsname" lay-verify="required" placeholder="请输入商品名称">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">商品描述</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="description" lay-verify="required" placeholder="请输入商品描述">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3 layui-col-xs5">
                    <div class="layui-upload-list thumbBox mag0 magt3">
                        <input type="hidden" name="goodsimg" id="goodsimg" value="${request.contextPath}/images/default.jpg">
                        <img class="layui-upload-img thumbImg" src="${request.contextPath}/file/PhotoByPath?path=images/default.jpg" width="150" height="150">
                    </div>
                </div>
            </div>
            <div class="layui-form-item magb0">
                <div class="layui-inline">
                    <label class="layui-form-label">产地</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="produceplace" lay-verify="" placeholder="请输入商品产地">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">包装</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="goodspackage" lay-verify="" placeholder="请输入商品包装">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">规格</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="size" lay-verify="" placeholder="请输入商品规格">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">生产批号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="productcode" lay-verify="" placeholder="请输入商品生产批号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">批准文号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="promitcode" lay-verify="" placeholder="请输入商品批准文号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">销售价格</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="price" lay-verify="" placeholder="请输入商品销售价格">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">库存量</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="number" lay-verify="" placeholder="请输入商品库存量">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">预警值</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="dangernum" lay-verify="" placeholder="请输入商品预警值">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">是否有效</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="available" value="1" title="可用" checked="">
                        <input type="radio" name="available" value="0" title="不可用" >
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block" style="text-align: center;">
                    <button type="button" class="layui-btn" lay-submit="" lay-filter="doSubmit" id="doSubmit" ><span class="layui-icon layui-icon-add-1"></span>提交</button>
                    <button type="reset" class="layui-btn layui-btn-warm"><span class="layui-icon layui-icon-refresh-1"></span>重置</button>
                    <button type="reset" class="layui-btn"><span class="layui-icon layui-icon-download-circle"></span>导出</button>

                </div>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->

<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery','form','table','layer','upload'],function(){
        var $=layui.jquery;
        var form=layui.form;
        var table=layui.table;
        var layer=layui.layer;
        var upload=layui.upload;
        //加载 数据
        tableIns=table.render({
            elem: '#goodsTable'
            ,url:'${request.contextPath}/goods/loadAllGoods'
            ,method:'post'
            ,contentType:'application/json; charset=UTF-8'
            ,data:'json'
            ,toolbar: '#goodsToolBar' //开启头部工具栏，并为其绑定左侧模板
            ,title: '商品数据表'
            ,height:'full-230'
            ,page: true
            ,cols: [ [
                {field:'id', title:'ID',align:'center' ,width:'80'}
                ,{field:'goodsname', title:'商品名称',align:'center',width:'150'}
                ,{field:'providername', title:'供应商',align:'center',width:'120'}
                ,{field:'produceplace', title:'产地',align:'center',width:'120'}
                ,{field:'size', title:'商品规格',align:'center',width:'120'}
                ,{field:'goodspackage', title:'商品包装',align:'center',width:'120'}
                ,{field:'productcode', title:'生产批号',align:'center',width:'100'}
                ,{field:'promitcode', title:'批准文号',align:'center',width:'100'}
                ,{field:'description', title:'商品描述',align:'center',width:'120'}
                ,{field:'price', title:'商品价格',align:'center',width:'100'}
                ,{field:'number', title:'库存量',align:'center',width:'100'}
                ,{field:'dangernum', title:'预警库存',align:'center',width:'100'}
                ,{field:'goodsimg', title:'商品图片',align:'center',width:'100', templet:function(d){
                        return '<img width=40 height=40 src=${request.contextPath}/file/PhotoByPath?path='+d.goodsimg+ ' />';
                    }}
                ,{field:'available', title:'是否可用',align:'center',width:'100',templet:function(d){
                        return d.available==1?'<font color=blue>可用</font>':'<font color=red>不可用</font>';
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#goodsRowBar',align:'center',width:'200'}
            ] ]
            ,done: function(res, curr, count){ //处理删除某一页最后一条数据的BUG
                if(res.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{
                            curr:(curr-1)
                        }
                    });
                }
            }
        });
        //导出
        $("#export").click(function () {
            var params=$("#searchFrm").serialize();
            window.location.href="${request.contextPath}/goods/exportGoods?"+params;
        })
        //加载查询条件供应商的下拉列表
        $.get("${request.contextPath}/provider/providerForSelect",function(res){
            var data=res.data;
            var dom=$("#searchProviderid");
            var html='<option value="0">请选择供应商</option>'
            $.each(data,function(index,item){
                html+='<option value="'+item.id+'">'+item.providername+'</option>'
            });
            dom.html(html);
            form.render("select");
        })

        //模糊查询
        form.on("submit(doSearch)",function(data){
            tableIns.reload({
                where:data.field,
                page:{
                    curr:1
                }
            });
            return false;
        });

        //监听工具条的事件
        table.on("toolbar(goodsTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddLayer();
                    break;
                case 'batchDelete':
                    batchDelete();
                    break;
            };
        });

        //监听行工具条的事件
        table.on("tool(goodsTable)",function(obj){
            var data = obj.data; //获得当前行数据
            switch(obj.event){
                case 'update':
                    openUpdateGoodsLayer(data);
                    break;
                case 'delete':
                    deleteGoods(data);
                    break;
            };
        });

        var mainIndex;
        var url;
        //打开添加的弹出层
        function openAddLayer(){
            mainIndex=layer.open({
                type:1,
                content:$("#addOrUpdateDiv"),
                area:['800px','600px'],
                title:'添加商品',
                success:function(){
                    $("#dataFrm")[0].reset();
                    url="${request.contextPath}/goods/addGoods";
                    $.get("${request.contextPath}/provider/providerForSelect",function(res){
                        var data=res.data;
                        var dom=$("#providerid");
                        var html='<option value="0">请选择供应商</option>'
                        $.each(data,function(index,item){
                            html+='<option value="'+item.id+'">'+item.providername+'</option>'
                        });
                        dom.html(html);
                        form.render("select");
                    });
                    //设置默认图片
                    $(".thumbImg").attr("src",'${request.contextPath}/file/PhotoByPath?path=images/default.jpg');
                    $("#goodsimg").val('${request.contextPath}/images/default.jpg');
                }
            });
        }

        //打开修改的弹出层
        function openUpdateGoodsLayer(data){
            mainIndex=layer.open({
                type:1,
                content:$("#addOrUpdateDiv"),
                area:['800px','600px'],
                title:'修改商品',
                success:function(){
                    $("#dataFrm")[0].reset();
                    //装载新的数据
                    form.val("dataFrm",data);
                    url="${request.contextPath}/goods/updateGoods";
                    $.get("${request.contextPath}/provider/providerForSelect",function(res){
                        var redata=res.data;
                        var dom=$("#providerid");
                        var html='<option value="0">请选择供应商</option>'
                        $.each(redata,function(index,item){
                            if(data.providerid===item.id){
                                html+='<option value="'+item.id+'" selected>'+item.providername+'</option>'
                            }else{
                                html+='<option value="'+item.id+'">'+item.providername+'</option>'
                            }
                        });
                        dom.html(html);
                        form.render("select");
                    });
                    $(".thumbImg").attr("src",'${request.contextPath}/file/PhotoByPath?path='+data.goodsimg);
                }
            });
        }

        form.on("submit(doSubmit)",function(data){
            $.post(url,data.field,function(res){
                tableIns.reload();
                layer.msg(res.msg);
                layer.close(mainIndex);
            })
            return false;
        });

        //上传图片
        upload.render({
            elem: '.thumbBox',
            url: '${request.contextPath}/file/upFile',
            acceptMime:'image/*',//设置文件上传所有图片类型
            field:'photofile',//设置文件域的字段名
            method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            done: function(res, index, upload){
                var path=res.path;
                $('.thumbImg').attr('src','${request.contextPath}/file/PhotoByPath?path='+path);
                $('.thumbBox').css("background","#fff");
                $("#goodsimg").val(path);//给隐藏域赋值
            }
        });

        //删除
        function deleteGoods(data){
            layer.confirm('你确定要删除【'+data.goodsname+'】这个商品吗?', {icon: 3, title:'提示'}, function(index){
                $.post("${request.contextPath}/goods/deleteGoods",{id:data.id,goodsimg:data.goodsimg},function(res){
                        tableIns.reload();
                    layer.msg(res.msg);
                })
                layer.close(index);
            });
        }
    });
</script>

</body>
</html>