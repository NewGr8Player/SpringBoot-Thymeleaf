'use strict'

/**
 * 角色JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element', 'table', 'laypage', 'form'], function () {
    var $ = layui.jquery,
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
                {field: 'sequence', title: '序号', width: '8%', type: 'numbers'}
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
     * 查询
     */
    form.on('submit(queryRoleReset)', function (data) {
        $('#roleQueryForm').reset();
        reloadTable(data);
        return false;
    });

    /**
     * 新增页面弹窗
     */
    $('#add').bind('click', function () {
        parent.layer.open({
            title: '新增'
            , type: 2
            , content: basePath + '/sys/role/form'
            , btn: ['保存', '重置', '取消']
            , yes: function (index, layero) {
                var obj = parent.layer.getChildFrame('body', index);
                obj.find('#saveBtn').trigger('click');
                //obj.find('#saveBtn').attr('disable','disable');
            }
            , btn2: function (index, layero) {
                parent.layer.getChildFrame('body', index).find('#resetBtn').trigger('click');
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
        console.log(form.field);
        $.ajax({
            url: basePath + '/sys/role/save'
            , data: form.field
            , type: 'post'
            , success: function (data) {
                console.log(data);
                var result = data['code'] === 'success';
                layer.msg(data['msg'], {icon: result ? 1 : 2});
                if (result) {
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                } else {
                    $('#saveBtn').attr('disable', '');
                }
                console.log(result);
            }
        });
        return false;
    });
});