layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl;
    var examid = window.sessionStorage.getItem("examid");
    $.ajax({
        url: '../../json/examTest.json',
        data: {"examid": examid},
        success: function (d) {
            console.log(JSON.stringify(d));
            var o = new Array();
            for (var i in d) {
                console.log(d[i].options_)

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
        }

    })
    form.on("submit", function (data) {
        var ra = $(".form-group").find("input[type='radio']").filter(':checked')

        var ra1 = $('input[name="radio"]').filter(':checked');

        // $('input[type="radio"]:checked').each(function () {
        //     console.log(this.value);
        // })
        for (let i in ra) {
            console.log(ra[i].val())
            
        }
        // $(".form-group").find("input[type='radio']").each(function(){
        //
        //         console.log((this).cheked.val());
        //        // map.push(choicCheckOption);
        //
        // });


        $.ajax({
            url: "/advance.action",
            data: data.field,
            type: "post",
            success: function (d) {
                layer.msg("交卷完成");

            }
        })
        return false;
    })

})