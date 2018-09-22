'use strict'

/**
 * 登录模块JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element', 'form'], function () {
    var layer = layui.layer,
        $ = layui.$,
        form = layui.form;

    /**
     * 登录失败
     */
    $(function () {
        var message = $("#message").val();
        if (!!message) {
            layer.msg(message, {icon: 2, timeout: 1000, shade: [0.8, '#393D49'], shadeClose: true});
        }
    });

    /**
     * 登录页表单提交
     */
    form.on('submit(login)', function (data) {
        layer.msg('登录中请稍后', {icon: 4, shade: [0.8, '#393D49'], shadeClose: false});
        $('#loginForm').submit();
        return false;
    });

    /**
     * QQ快捷登录点击事件
     */
    $("#qqLogin").bind('click', function () {
        layer.msg('API尚未接入。', {icon: 3, timeout: 1000, shade: [0.8, '#393D49'], shadeClose: true});
    });
    /**
     * 微信快捷登录点击事件
     */
    $("#wechatLogin").bind('click', function () {
        layer.msg('API尚未接入。', {icon: 3, timeout: 1000, shade: [0.8, '#393D49'], shadeClose: true});
    });
});