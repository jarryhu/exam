layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl;
    var examid = window.sessionStorage.getItem("examid");
    var userid = JSON.parse(window.sessionStorage.getItem("user")).id;
    var count;
    var op = "";
    var op1;
    $.ajax({
        // url: '../../json/examTest.json',
        url: "/makePaper.action",
        data: {"examid": examid},
        success: function (d) {
            count = d.length;
            for (var i in d) {
                op += d[i].id + ",";

                //第三步：渲染模版
                var data = { //数据
                    "title": "Layui常用模块"
                    , "list": d
                }
                var getTpl = demo.innerHTML
                    , view = document.getElementById('view');
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
            }
            op = op.substring(0, op.length - 1);
            op1 = op.split(",");

        }

    })
    form.on("submit", function (data) {
        var str = "";
        for (let j = 0; j < op1.length; j++) {
            var v = $("input[name='" + op1[j] + "']:checked").val();
            str += v + ",";
        }
        str = str.substring(0, str.length - 1);
        console.log(str);

        $.ajax({
            url: "/advance.action",
            data: {"choices": str, "examid": examid, "userid": userid},
            type: "post",
            success: function (d) {
                if (d > 0) {
                    layer.msg("交卷完成 ");
                    layer.close(layer.index);
                    parent.window.location.reload()
                }

            }
        })
        return false;
    })

    var nowPage = 0;
    var sumPage = 2;
    var hour = 0;//时
    var minute = 0;// 分
    var second = 0;// 秒

    var mytimeHtml = $("#mytime").html();

    setInterval(function () {
        second += 1;
        if (second >= 60) {
            second = 0;
            minute += 1;
        }
        if (minute >= 60) {
            minute = 0;
            hour += 1;
        }

        $("#mytime").html(mytimeHtml + "<span>" + hour + "时" + minute + "分" + second + "秒</span>");

        if (second == 10) {
            $("#advance").trigger("click");

        }
    }, 1000);


})