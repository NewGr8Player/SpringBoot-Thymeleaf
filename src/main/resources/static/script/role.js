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
    function tableRender() {
        table.render({
            elem: '#roleTable'
            , url: basePath + '/role/queryList'
            , method: 'post'
            , page: true
            , height: 'full-100'
            , skin: 'line'
            , even: true
            , where: {
                current: 1
                , size: 10
                , roleName: ''
            }
            , request: {
                pageName: 'current'
                , limitName: 'size'
            }
            , response: {
                statusCode: 0
                , statusName: 'status'
                , msgName: 'msg'
                , countName: 'total'
                , dataName: 'records'
            }
            , cols: [[
                {field: 'id', title: 'ID', width: 20, sort: true, fixed: 'left'}
                , {field: 'roleName', title: '角色名称', width: 80}
            ]]
        });
    }

});