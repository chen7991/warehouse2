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
                                <label class="layui-form-label">供应商名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="providername"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">供应商电话</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="phone"  autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">联系人</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="connectionperson"  autocomplete="off" class="layui-input">
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
                        <table class="layui-hide" id="providerTable" lay-filter="providerTable"></table>
                        <div id="providerToolBar" style="display: none;">
                            <button type="button" lay-event="add" class="layui-btn layui-btn-sm"><span class="layui-icon layui-icon-add-1"></span>添加</button>
                            <button type="button" lay-event="batchDelete" class="layui-btn layui-btn-sm layui-btn-danger"><span class="layui-icon layui-icon-delete"></span>批量删除</button>
                        </div>

                        <div id="providerRowBar" style="display: none;">
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
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">供应商名称</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="providername" lay-verify="required" autocomplete="off" placeholder="请输入供应商名称" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">供应商电话</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="telephone" lay-verify="required" autocomplete="off" placeholder="请输入供应商电话" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮编</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="zip"  autocomplete="off" placeholder="请输入供应商邮编" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">供应商地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" autocomplete="off" placeholder="请输入供应商地址" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="connectionperson" lay-verify="required" autocomplete="off" placeholder="请输入联系人" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">联系人电话</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="phone" lay-verify="required" autocomplete="off" placeholder="请输入联系人电话" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="email" lay-verify="required" autocomplete="off" placeholder="请输入供应商邮箱" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开户银行</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="bank" lay-verify="required" autocomplete="off" placeholder="请输入开户银行" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">帐号</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="account" lay-verify="required" autocomplete="off" placeholder="请输入供应商帐号" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">传真</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="fax" lay-verify="required" autocomplete="off" placeholder="请输入供应商传真" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否可用</label>
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
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>

<script type="text/javascript">
    var tableIns;
    layui.use(['jquery','form','table','layer'],function(){
        var $=layui.jquery;
        var form=layui.form;
        var table=layui.table;
        var layer=layui.layer;
        //加载 数据
        tableIns=table.render({
            elem: '#providerTable'
            ,url:'${request.contextPath}/provider/loadAllProvider'
            ,method:'post'
            ,contentType:'application/json; charset=UTF-8'
            ,data:'json'
            ,toolbar: '#providerToolBar' //开启头部工具栏，并为其绑定左侧模板
            ,title: '供应商数据表'
            ,height:'full-220'
            ,page: true
            ,cols: [ [
                {type:'checkbox',align:'center'}
                ,{field:'id', title:'ID',align:'center' ,width:'80'}
                ,{field:'providername', title:'供应商名称',align:'center',width:'120'}
                ,{field:'zip', title:'邮编',align:'center',width:'120'}
                ,{field:'address', title:'供应商地址',align:'center',width:'150'}
                ,{field:'telephone', title:'供应商电话',align:'center',width:'150'}
                ,{field:'connectionperson', title:'联系人',align:'center',width:'120'}
                ,{field:'phone', title:'联系人电话',align:'center',width:'150'}
                ,{field:'bank', title:'开户行',align:'center',width:'120'}
                ,{field:'account', title:'账号',align:'center',width:'180'}
                ,{field:'email', title:'邮箱',align:'center',width:'150'}
                ,{field:'fax', title:'传真',align:'center',width:'120'}
                ,{field:'available', title:'是否有效',align:'center',width:'120',templet:function(d){
                        return d.available==1?'<font color=blue>有效</font>':'<font color=red>无效</font>';
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#providerRowBar',align:'center',width:'200'}
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
            window.location.href="${request.contextPath}/provider/exportProvider?"+params;
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
        table.on("toolbar(providerTable)",function(obj){
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
        table.on("tool(providerTable)",function(obj){
            var data = obj.data; //获得当前行数据
            switch(obj.event){
                case 'update':
                    openUpdateProviderLayer(data);
                    break;
                case 'delete':
                    deleteProvider(data);
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
                area:['700px','600px'],
                title:'添加供应商',
                success:function(){
                    $("#dataFrm")[0].reset();
                    url="${request.contextPath}/provider/addProvider";
                }
            });
        }

        //打开修改的弹出层
        function openUpdateProviderLayer(data){
            mainIndex=layer.open({
                type:1,
                content:$("#addOrUpdateDiv"),
                area:['800px','600px'],
                title:'修改供应商',
                success:function(){
                    $("#dataFrm")[0].reset();
                    //装载新的数据
                    form.val("dataFrm",data);
                    url="${request.contextPath}/provider/updateProvider";
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
        //删除
        function deleteProvider(data){
            layer.confirm('你确定要删除【'+data.providername+'】这个供应商吗?', {icon: 3, title:'提示'}, function(index){
                $.post("${request.contextPath}/provider/deleteProvider",{id:data.id},function(res){
                        tableIns.reload();
                    layer.msg(res.msg);
                })
                layer.close(index);
            });
        }
        //批量删除
        function  batchDelete(){
            //得到选中行
            var checkStatus = table.checkStatus('providerTable');
            var dataLength=checkStatus.data.length;
            if(dataLength>0){
                layer.confirm('你确定要删除这些供应商数据吗?', {icon: 3, title:'提示'}, function(index){
                    var data=checkStatus.data; //获取选中行的数据
                    var ids="";
                    $.each(data,function(index,item){
                        if(index==0){
                            ids+="ids="+item.id;
                        }else{
                            ids+="&ids="+item.id;
                        }
                    })
                    $.post("${request.contextPath}/provider/batchDeleteProvider",ids,function(res){
                        tableIns.reload();
                        layer.msg(res.msg);
                    })
                    layer.close(index);
                });
            }else{
                layer.msg("请选中操作行")
            }
        }

    });
</script>
</body>
</html>