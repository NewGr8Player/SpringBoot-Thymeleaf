'use strict'

/**
 * 首页JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery'], function () {
    var layer = layui.layer,
        $ = layui.$

    $("#userInfo").bind('click', function () {
        layer.msg('用户信息', {icon: 3});
    });

    $("#editPasswd").bind('click', function () {
        layer.msg('修改密码', {icon: 3});
    })
});