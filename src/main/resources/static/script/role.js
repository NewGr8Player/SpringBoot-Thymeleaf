'use strict'

/**
 * 角色JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element', 'table', 'laypage', 'form'], function () {
    var $ = layui.jquery,
        table = layui.table,
        form = layui.form,
        layer = parent.layer;

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
            , url: basePath + '/sys/role/queryList'
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
                {width: '8%', type: 'checkbox'}
                , {field: 'sequence', title: '序号', width: '8%', type: 'numbers'}
                , {field: 'id', title: '唯一标示', width: '25%', sort: true}
                , {field: 'roleCode', title: '角色编码', width: '30%'}
                , {field: 'roleName', title: '角色名称', width: '35%'}
            ]]
        });
    }

    /**
     * 重载数据表格
     *
     * @param data
     */
    function reloadTable(data) {
        tableGrid.reload({
            where: {
                roleCode: data.field.roleCode
                , roleName: data.field.roleName
            }
            , page: {
                curr: 1
            }
        });
    }

    /**
     * 查询
     */
    form.on('submit(queryRole)', function (data) {
        reloadTable(data);
        return false;
    });

    /**
     * 重置
     */
    form.on('submit(queryRoleReset)', function (data) {
        $('#roleQueryForm').get(0).reset();
        for (var i in data.field) {
            data.field[i] = '';
        }
        reloadTable();
        return false;
    });

    $("#export").bind('click', function () {
        var checkStatus = table.checkStatus('roleTable');

        if (checkStatus.data.length || checkStatus.isAll) {
            table.exportFile(tableGrid, checkStatus.data, 'xls');
        }

    });

    /**
     * 新增页面弹窗
     */
    $('#add').bind('click', function () {
        layer.open({
            title: '新增'
            , type: 2
            , content: basePath + '/sys/role/form'
            , btn: ['保存', '重置', '取消']
            , yes: function (index, layero) {
                var obj = layer.getChildFrame('body', index);
                obj.find('#saveBtn').trigger('click');
                obj.find('#saveBtn').attr('disable', 'disable');
            }
            , btn2: function (index, layero) {
                layer.getChildFrame('body', index).find('#resetBtn').trigger('click');
                return false;
            }
            , btn3: function () {
                /* 取消什么都不做，直接关闭 */
                return true;
            }
        });
    });

    /**
     * 保存
     */
    form.on('submit(roleForm)', function (form) {
        $.ajax({
            url: basePath + '/sys/role/save'
            , data: form.field
            , type: 'post'
            , success: function (data) {
                var result = data['code'] === 'success';
                layer.msg(data['msg'], {icon: result ? 1 : 2});
                if (result) {
                    layer.close(layer.getFrameIndex(window.name));
                    $('#roleQueryForm')[0].reset();
                    reloadTable(data);
                } else {
                    $('#saveBtn').attr('disable', '');
                }
            }
        });
        return false;
    });
});