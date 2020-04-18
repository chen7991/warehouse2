<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <link rel="stylesheet" href="${request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
</head>
<body style="background-color: #F2F2F2;" >
<div style="padding: 20px; ">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" method="post" style="margin-top: 10px">
                        <input type="text" name="id"   autocomplete="off" class="layui-input" value="${(customer.id)!}" style="display: none">
                        <div class="layui-form-item">
                            <label class="layui-form-label">客户名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="customername" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.customername)!}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">客户邮编</label>
                            <div class="layui-input-block">
                                <input type="text" name="zip" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.zip)!}" >
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">客户地址</label>
                            <div class="layui-input-block">
                                <textarea name="address" placeholder="" class="layui-textarea">${(customer.address)!}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">客户电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="telephone" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.telephone)!}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">联系人</label>
                            <div class="layui-input-block">
                                <input type="text" name="connectionperson" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.connectionperson)!}" >
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">联系人电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="phone" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.phone)!}" >
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">开户行</label>
                            <div class="layui-input-block">
                                <input type="text" name="bank" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.bank)!}" >
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">账户</label>
                            <div class="layui-input-block">
                                <input type="text" name="account" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input" value="${(customer.account)!}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" name="email" required  lay-verify="required|email" placeholder="" autocomplete="off" class="layui-input" value="${(customer.email)!}">
                            </div>
                        </div>
                        <button id="add" class="layui-btn" lay-filter="submit" lay-submit style="display: none">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${request.contextPath}/static/layuiadmin/layui/layui.js"></script>


<script>
    layui.use(['form','jquery','layer'], function(){
        var form=layui.form;
        var $=layui.$;
        var layer=layui.layer;

        form.on('submit(submit)', function(data){
            console.log(data.field);
            $.ajax({
                type:"post",
                url: "${request.contextPath}/customer/addOrUpdateCustomer",
                contentType:'application/json; charset=UTF-8',
                data: JSON.stringify(data.field),
                success: function(res) {
                    console.log("=====success=====")
                    console.log(res)

                        layer.alert(res.msg,{icon:1},function () {
                            //调用父页面的关闭方法。
                            parent.closeCustomerLayer();
                            parent.reloadCustomerList();
                        })

                },
                error: function(res) {
                    layer.alert(res.msg,{icon:2})
                }
            })
            return false;
        });
        window.closeLayer=function(index){
            layer.close(index)
        }

    });
</script>
</body>
</html>