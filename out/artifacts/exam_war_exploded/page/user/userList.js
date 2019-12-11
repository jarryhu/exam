layui.use(['form', 'layer', 'table', 'util', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        util = layui.util,
        table = layui.table,
        $ = layui.jquery;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: '/userList.action',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [2, 3, 20, 25],
        limit: 20,
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'id', title: 'id', minWidth: 100, align: "center"},
            {field: 'nickname', title: '用户名', minWidth: 100, align: "center"},
            {
                field: 'userEmail', title: '用户邮箱', minWidth: 200, align: 'center', templet: function (d) {
                    if (d.userEmail) {
                        return '<a class="layui-blue" href="mailto:' + d.userEmail + '">' + d.userEmail + '</a>';
                    } else {
                        return ""
                    }
                }
            },
            {
                field: 'stauts', title: '用户状态', align: 'center', templet: function (d) {
                    return d.stauts == "0" ? "正常使用" : "限制使用";
                }
            },
            {
                field: 'right', title: '用户权限', align: 'center', templet: function (d) {
                        if (d.right == "1") {
                            return "教师";
                        } else if (d.right == "2") {
                            return "学生";
                        } else if (d.right == "3") {
                            return "管理员";
                        }
                }
            },
            {
                field: 'userEndTime', title: '最后登录时间', minWidth: 100, align: "center"
            },
            // {
            //     field: 'userEndTime',
            //     title: '最后登录时间',
            //     templet: function (d) {
            //         return util.toDateString(d.userEndTime * 1000, "yyyy-MM-dd HH:mm:ss");
            //     }
            // },


            {title: '操作', minWidth: 175, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("userListTable", {
                url: '/userList.action',

                where: {
                    nickname: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });


    //添加用户
    function ModifyUser(edit) {
        var index = layui.layer.open({
            title: "添加用户",
            type: 2,
            content: "userModify.html",
            success: function (layero, index) {
                window.sessionStorage.setItem("id", edit.id);

                //
                // var body = layui.layer.getChildFrame('body', index);
                // if (edit) {
                //     body.find(".userName").val(edit.nickname);  //登录名
                //     body.find(".userEmail").val(edit.userEmail);  //邮箱
                //     body.find(".userSex input[value=" + edit.sex + "]").prop("checked", "checked");  //性别
                //     body.find(".userGrade").val(edit.right);  //会员等级
                //      body.find(".classesgrade").hide();
                //     // if (edit.right == 2) {
                //     //     body.find(".layui-col-xs12").show();
                //     // }
                //     body.find(".classesgrade").val(edit.classid);
                //     body.find(".userStatus").val(edit.stauts);    //用户状态
                //     body.find(".userDesc").text(edit.userDesc);    //用户简介
                //
                //
                //     form.render();//重新渲染

            }
            //    setTimeout(function () {
            //        layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
            //            tips: 3
            //        });
            //    }, 500)
            // }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    $(".addNews_btn").click(function () {
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            ModifyUser(data);
        } else if (layEvent === 'usable') { //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？!!",
                btnText = "已禁用";
            if (_this.text() == "已禁用") {
                usableText = "是否确定启用此用户？",
                    btnText = "已启用";
            }
            layer.confirm(usableText, {
                icon: 3,
                title: '系统提示',
                cancel: function (index) {
                    layer.close(index);
                }
            }, function (index) {
                _this.text(btnText);
                layer.close(index);
            }, function (index) {
                layer.close(index);
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            });
        }
    });

})
