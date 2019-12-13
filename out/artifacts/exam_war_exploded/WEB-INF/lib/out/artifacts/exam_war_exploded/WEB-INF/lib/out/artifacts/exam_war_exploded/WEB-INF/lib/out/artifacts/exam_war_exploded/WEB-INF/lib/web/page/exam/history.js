layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    
    var id = JSON.parse(window.sessionStorage.getItem("user")).id;
    var classid = JSON.parse(window.sessionStorage.getItem("user")).classid;
    //新闻列表
    var tableIns = table.render({
        elem: '#historyList',
        url: '/getStudentExam.action',
        where: {id: id, classid: classid},
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "newsListTable",
        cols: [[
            {type: "checkbox"},
            {field: 'id', title: 'ID', align: "center"},
            {
                field: 'examName', title: '试卷名称',
                align: 'center'
            },
            {
                field: 'examDecript', title: '试卷详情',
                align: 'center'
            },
            {
                field: 'mark',
                title: '分数',
                templet: '<div>{{(d.marklist==null)?"":d.marklist.mark}}</div>',
                align: 'center'
            },
            {title: '操作', templet: '#newsListBar', align: "center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function (data) {
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                layer.msg("置顶成功！");
            } else {
                layer.msg("取消置顶成功！");
            }
        }, 500);
    })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("newsListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });


    //列表操作
    table.on('tool(historyList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addNews(data);
        } else if (layEvent === 'exam') { //删除
            var index = layui.layer.open({
                title: "开始考试",
                type: 2,
                content: "examInit.html",
                success: function (layero, index) {
                    window.sessionStorage.setItem("examid", data.id);
                    console.log(JSON.parse(window.sessionStorage.getItem("examid")))
                }
            })
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize", function () {
                layui.layer.full(index);
            })

        } else if (layEvent === 'look') { //预览
            layer.open({
                area: ['60%', '40%'],
                title: "考试结果",
                content:
                    '<div class="layui-btn-container">' +
                    '<button type="button" class="layui-btn">A</button> ' +
                    '<button type="button" class="layui-btn layui-btn-danger">D</button> ' +
                    '<button type="button" class="layui-btn">C</button> ' +
                    '<button type="button" class="layui-btn layui-btn-danger">B</button> ' +
                    '</div>'
            })
        }
    });


})