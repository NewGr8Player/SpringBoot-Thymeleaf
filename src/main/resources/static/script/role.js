'use strict'

/**
 * 角色JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element', 'table', 'laypage', 'form'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    /**
     * 页面加载完毕
     */
    $(function () {
        tableRender();
    });

    /**
     * 数据表格对象
     */
    var tableGrid;

    /**
     * 刷新页面表格
     */
    function tableRender() {
        tableGrid = table.render({
            elem: '#roleTable'
            , url: basePath + '/role/queryList'
            , method: 'post'
            , page: true
            , even: true
            , limit: 10
            , height: 'full-100'
            , skin: 'line'
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
                {field: 'sequence', title: '序号', width: '8%', type: 'numbers'}
                , {field: 'id', title: '唯一标示', width: '25%', sort: true}
                , {field: 'roleName', title: '角色名称', width: '65%'}
            ]]
        });
    }

    /**
     * 查询
     */
    form.on('submit(query)', function (data) {
        tableGrid.reload({
            where: {
                roleName: data.field.roleName
            }
            , page: {
                curr: 1
            }
        });
    });

    /**
     * 新增页面弹窗
     */
    $('#add').bind('click', function () {
        parent.layer.open({
            title: '新增'
            , type: 2
            , content: 'http://www.baidu.com'
        });
    });

    /**
     * 保存
     */
    form.on('submit(editForm)', function (data) {
    });
});