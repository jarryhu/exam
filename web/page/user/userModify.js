layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



        form.on("select(userGrade)",function(data){
          var val=data.value;
        if(val==2)
        {
             $(".classes").show();
        }else
        {
         $(".classes").hide();
        }
        })

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //   实际使用时的提交信息
        //    $.post("上传路径",{
        //        userName : $(".userName").val(),  //登录名
        //        userEmail : $(".userEmail").val(),  //邮箱
        //        userSex : data.field.sex,  //性别
        //        userGrade : data.field.userGrade,  //会员等级
        //        userStatus : data.field.userStatus,    //用户状态
        //        newsTime : submitTime,    //添加时间
        //        userDesc : $(".userDesc").text(),    //用户简介
        //    },function(res){
        //
        //    })

        $.ajax({
            url: "/modifyUser.action",
            data: data.field,
            success: function (d) {
                if (d > 0) {
                    layer.msg("修改成功");
                    layer.close(layer.index);
                    parent.location.reload();
                }

            }
        })
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("用户添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());

    $.ajax({
        url: "/getClasses.action",
        success: function (d) {
            $(".classesgrade").empty();
            for (var i = 0; i < d.length; i++) {
                // console.log("d" + JSON.stringify(d[i]));
                $('.classesgrade').append('<option value = "' + d[i].id + '">' + d[i].className + '</option>');
            }
            var id = window.sessionStorage.getItem("id");
            $.ajax({
                url: "/getUserByid.action",
                data: {"id": id},
                type: "post",
                success: function (d) {
                    $(".id").val(d.id);  //登录名
                    $(".userName").val(d.nickname);  //登录名
                    $(".userEmail").val(d.userEmail);  //邮箱
                    $(".userSex input[value=" + d.sex + "]").prop("checked", "checked");  //性别
                    $(".userGrade").val(d.right);  //会员等级
                    $(".classes").show();
                    if (d.right != 2) {

                        $(".classes").hide();
                        $(".classes").val(0);
                    }
                    $(".classesgrade").val(d.classid);
                    $(".userStatus").val(d.stauts);    //用户状态
                    $(".userDesc").text(d.myself);    //用户简介
                    form.render();
                }
            })

        }
    })


})