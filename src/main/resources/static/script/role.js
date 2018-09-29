'use strict'

/**
 * 角色JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element', 'table', 'laypage'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        element = layui.element,
        table = layui.table,
        laypage = layui.laypage;

    /**
     * 页面加载完毕
     */
    $(function () {
        tableRender();
    });

    /**
     * 刷新页面表格
     */
    function tableRender(url) {
        table.render({
            elem: '#roleTable'
            , url: url
            , page: true
            , cols: [[
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'username', title: '用户名', width: 80}
                , {field: 'sex', title: '性别', width: 80, sort: true}
                , {field: 'city', title: '城市', width: 80}
            ]]
        });
    }
});