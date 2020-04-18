<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
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
                    <form class="layui-form" id="searchFrm">
                        <div class="layui-form-item" style="margin-top: 5px">
                            <div class="layui-inline">
                                <label class="layui-form-label">客户名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="customername" lay-verify="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">客户电话</label>
                                <div class="layui-input-inline">
                                    <input type="tel" name="telephone" lay-verify="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">联系人</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="connectionperson" lay-verify="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <button type="button" id="search" class="layui-btn layui-btn-normal">查询</button>
                            <button type="button" id="reset" class="layui-btn layui-btn-warm">清空</button>
                            <button type="button" id="export" class="layui-btn"><span class="layui-icon layui-icon-download-circle"></span>导出</button>
                        </div>
                    </form>
                    <table class="layui-hide" id="customer" lay-filter="customer"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
        <button class="layui-btn layui-btn-sm" lay-event="multiDelete">批量删除</button>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['table','form','jquery','layer'], function(){
        var table = layui.table;
        var form=layui.form;
        var $=layui.$;
        var layerIndex='';
        var layer=layui.layer;

        table.render({
            elem: '#customer'
            ,url:'${request.contextPath}/customer/getPageList'
            ,method:'post'
            ,contentType:'application/json; charset=UTF-8'
            ,data:'json'
            ,height:'full-230'
            ,toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID'}
                ,{field:'customername', title:'客户名称'}
                ,{field:'zip', title:'客户邮编'}
                ,{field:'address', title:'客户地址'}
                ,{field:'telephone', title:'客户电话'}
                ,{field:'connectionperson', title:'联系人'}
                ,{field:'phone', title:'联系电话'}
                ,{field:'bank', title:'开户行'}
                ,{field:'account', title:'账户'}
                ,{field:'email', title:'邮箱', width:150, edit: 'text', templet: function(res){
                        return '<em>'+ res.email +'</em>'
                    }}

                ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150,align:'center'}
            ]]
            ,page: true
        });
        //导出
          $("#export").click(function () {
            var params=$("#searchFrm").serialize();
            window.location.href="${request.contextPath}/customer/exportCustomer?"+params;
          })
        //头工具栏事件
        table.on('toolbar(customer)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
                case 'add':
                    layer.open({
                        title:'新增客户信息',
                        type: 2,
                        area: ['400px', '600px'],
                        btn: ['确认', '取消'],
                        content: '${request.contextPath}/customer/customerLayer' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                        ,yes: function(index, layero){
                            //按钮【按钮一】的回调
                            layerIndex=index;
                            var submitFrom = layer.getChildFrame('form',index);
                            console.log(submitFrom)
                            var submitButton=submitFrom.find("button");
                            console.log(submitButton)
                            submitButton.click();
                        }
                    });
                    break;
                case 'multiDelete':
                    layer.confirm('真的删除这些数据吗', function(index){
                        var idAry=[];
                        var data=checkStatus.data;
                        for (var i = 0; i < checkStatus.data.length; i++) {
                            var id=data[i].id;
                            idAry.push(id);
                        }
                        $.ajax({
                            type:"post",
                            url: "${request.contextPath}/customer/deleteCustomerByIds",
                            //contentType:'application/json; charset=UTF-8',
                            data: 'ids='+idAry,
                            success: function(res) {
                                console.log("=====success=====")
                                console.log(res)
                                layer.msg(res.msg);
                                layer.close(index);
                                table.reload("customer");
                            },
                            error: function(res) {
                                layer.alert(res.msg,{icon:2})
                            }
                        })
                        layer.close(index);
                    });

            };
        });

        //监听行工具事件
        table.on('tool(customer)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除这条数据吗', function(index){
                    $.ajax({
                        type:"post",
                        url: "${request.contextPath}/customer/deleteCustomerByIds",
                        //contentType:'application/json; charset=UTF-8',
                        data: 'ids='+data.id,
                        success: function(res) {
                            console.log("=====success=====")
                            console.log(res)
                            layer.msg(res.msg);
                            layer.close(index);
                            table.reload("customer");
                        },
                        error: function(res) {
                            layer.alert(res.msg,{icon:2})
                        }
                    })
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    title:'修改客户信息',
                    type: 2,
                    area: ['400px', '600px'],
                    btn: ['确认', '取消'],
                    content: '${request.contextPath}/customer/customerLayer?id='+data.id //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                    ,yes: function(index, layero){
                        //按钮【按钮一】的回调
                        layerIndex=index;
                        var submitFrom = layer.getChildFrame('form',index);
                        console.log(submitFrom)
                        var submitButton=submitFrom.find("button");
                        console.log(submitButton)
                        submitButton.click();
                    }
                });
            }
        });
        //清空
        $('#reset').click(function () {
            $("input[name='customername']").val("");
            $("input[name='telephone']").val("");
            $("input[name='connectionperson']").val("");
        })
        //查询按钮
        $('#search').click(function () {
            var customername=$("input[name='customername']").val();
            var telephone=$("input[name='telephone']").val();
            var connectionperson=$("input[name='connectionperson']").val();
            table.reload("customer",{where:{customername:customername,telephone:telephone,connectionperson:connectionperson}});

        })

        //关闭弹窗
        window.closeCustomerLayer=function () {
            layer.close(layerIndex);
        }
        //刷新客户列表
        window.reloadCustomerList=function () {
            table.reload('customer');
        }

    });



</script>
</body>
</html>