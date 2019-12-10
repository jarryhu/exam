var form, $, areaData;
layui.config({
    base: "../../js/"
}).extend({
    "address": "address"
})
layui.use(['form', 'layer', 'upload', 'laydate', "address"], function () {
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;


    ;


    //上传头像
    //普通图片上传
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        , url: '/uploadHead.action' //上传接口
        , auto: false
        , data: {id: $("#uid").val()}
        , bindAction: '#uploadImg'

        //选择文件后的回调
        , choose: function (obj) {
            obj.preview(function (index, file, result) {
                $('#preview').attr('src', result);
            })
        },

        done: function (res) {
            //如果上传失败
            if (!res) {
                return layer.msg('上传失败');
                $('.layui-btn-img').text("重新上传");
            }
            //上传成功
            if (res) {
                //layui 关闭当前窗口，刷新父级页面
                //   console.log(JSON.parse(res.user));
                window.sessionStorage.setItem("user", JSON.stringify(res));
                console.log("sessionStorage>>>>>>" + window.sessionStorage.getItem("user"));
                layer.close(layer.index);
                parent.location.reload();

                return layer.msg('上传成功', {time: 700});
            }
        }
    });

    //添加验证规则
    form.verify({
        userBirthday: function (value) {
            if (!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)) {
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        value: new Date(),
        // done: function (value, date) {
        //     value: new Date()// 指定值
        //
        // }
    });

    //获取省信息
    address.provinces();

    //提交个人资料
    form.on("submit(changeUser)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //将填写的用户信息存到session以便下次调取
        var key, userInfoHtml = '';
        userInfoHtml = {
            'id': $("#uid").val(),
            'nickname': $(".nickname").val(),
            'sex': data.field.sex,
            'userPhone': $(".userPhone").val(),
            'userBirthday': $(".userBirthday").val(),
            'province': data.field.province,
            'city': data.field.city,
            'area': data.field.area,
            'userEmail': $(".userEmail").val(),
            'headPath': $(".headPath").val(),
            'myself': $(".myself").val()
        };
        var hobby = "";
        for (key in data.field) {
            if (key.indexOf("like") != -1) {

                hobby += key + ",";
            }
        }
        userInfoHtml.userHobby = hobby.substring(0, hobby.length - 1)

        $.ajax({
            url: "/modifyUserSelf.action",
            type: 'post',
            contentType: "application/json",
            data: JSON.stringify(userInfoHtml),
            dataType: "json",

            success: function (d) {
                console.log(d);
                if (d) {
                    window.sessionStorage.setItem("user", JSON.stringify(d));
                    $("#uploadImg").trigger("click");

                } else {
                    layer.msg("修改失败")
                }

            }

        })


        //      window.sessionStorage.setItem("userInfo", JSON.stringify(userInfoHtml));
        setTimeout(function () {
            layer.close(index);
            layer.msg("提交成功！");
        }, 2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //修改密码
    form.on("submit(changePwd)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        }, 2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})