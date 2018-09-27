'use strict'

/**
 * 首页JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery'], function () {
    var layer = layui.layer,
        $ = layui.$

    /**
     * 用户按钮点击
     */
    $("#userInfo").bind('click', function () {
        layer.msg('用户信息', {icon: 3});
    });

    /**
     * 修改密码点击
     */
    $("#editPasswd").bind('click', function () {
        layer.msg('修改密码', {icon: 3});
    })

    $(function () {
        menuRender($("#currentMenuCode").val());
    });

    /**
     * 渲染菜单
     */
    function menuRender(currentMenuCode) {
        $.ajax({
            url: basePath + '/modelMenu?menuType=model&menuCode=' + currentMenuCode,
            type: 'post',
            async:false,
            success: function (data) {
                menuElementRender(data);
            },
            error: function (info) {
                layer.error(info);
            }
        });
    }

    /**
     * 渲染菜单元素
     */
    function menuElementRender(menuList) {
        var $parentELement = $('<li class="layui-nav-item"></li>');
        var $childContainer = $('<dl class="layui-nav-child"></dl>');
        var $childElement = $('<dd></dd>');
        console.log(menuList)
    }
});