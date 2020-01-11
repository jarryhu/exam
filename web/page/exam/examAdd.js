layui.use(['form', 'layer', 'layedit', 'laydate', 'upload', 'jquery'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    $("#teacherId").val(JSON.parse(window.sessionStorage.getItem("user")).id)


    //上传试卷
    var uploadInst = upload.render({
        elem: '#test10' //绑定元素
        , url: '/uploadExam.action' //上传接口
        , exts: 'xls|xlsx' //只允许上传Excel文件
        , auto: false
        , bindAction: '#uploadfile',
        before: function () {
            this.data = {'id': examid};//整合上传的参数
        }


        //选择文件后的回调
        , choose: function (obj) {
            obj.preview(function (index, file, result) {
                $("#test10").css('display', "none");
                $('#preview').css('display', "block");
                $('#preview').attr('src', "../../images/xls.jpg");
            })
        },

        done: function (res) {
            console.log("uploadExam>>>>>>" + res);
            //如果上传失败
            if (!res) {
                return layer.msg('上传失败');
            }
            //上传成功
            if (res) {
                console.log(res.path);
                $.ajax({
                    url: "/insertPaper.action",
                    data: {"path": res.path, "examid": examid},
                    success: function (d) {
                        if (d > 0) {
                            layer.close(layer.index);
                            parent.location.reload();
                            return layer.msg('试卷已生成', {time: 700});
                        } else {
                            $.ajax({
                                url: "/deleteExam.action",
                                data: {"id": examid},
                                success: function (d) {
                                    if (d > 0) {
                                        layer.close(layer.index);
                                        return layer.msg('试卷已生成失败', {time: 700});
                                    }

                                }
                            })

                        }
                    },
                    error: function (d) {


                    }
                })
            }
        }
    });

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
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger: "click",
        done: function (value, date, endDate) {
            submitTime = value;
        }
    });
    form.on("radio(release)", function (data) {
        if (data.elem.title == "定时发布") {
            $(".releaseDate").removeClass("layui-hide");
            $(".releaseDate #release").attr("lay-verify", "required");
        } else {
            $(".releaseDate").addClass("layui-hide");
            $(".releaseDate #release").removeAttr("lay-verify");
            submitTime = time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
        }
    });

    form.verify({
        newsName: function (val) {
            if (val == '') {
                return "考试标题不能为空";
            }
        },
        content: function (val) {
            if (val == '') {
                return "考试内容不能为空";
            }
        }
    })
    var examid;
    form.on("submit(addNews)", function (data) {
        //截取文章内容中的一部分文字放入文章摘要
        // var abstract = layedit.getText(editIndex).substring(0, 50);
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});

        $.ajax({
            url: "/publishExam.action",
            data: data.field,
            success: function (d) {
                console.log("publishExam>>>>>>>" + d);
                if (d) {
                    examid = d;
                    $("#uploadfile").trigger("click");
                }

            }
        })

        return false;
    })

    //预览
    form.on("submit(look)", function () {
        layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问");
        return false;
    })

    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 535,
        uploadImage: {
            url: "../../json/newsImg.json"
        }
    });


    $.ajax({
        url: '/getClasses.action',
        type: "post",
        success: function (d) {
            var c = $("#classes")
            var op = "";
            for (var i = 0; i < d.length; i++) {
                console.log(d[i].id);
                console.log(d[i].className);
                op += "<option value='" + d[i].id + "'>" + d[i].className + "</option>"
            }
            c.append(op);
            form.render();
        }
    })

})