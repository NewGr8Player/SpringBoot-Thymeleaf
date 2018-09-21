'use strict'

/**
 * 登录模块JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element','form'], function () {
    var layer = layui.layer,
        $ = layui.$,
        form = layui.form;

    /**
     * 登录失败
     */
    $(function () {
        var message = $("#message").val();
        if (!!message) {
            layer.alert(message, {icon: 2});
        }
    });

    /**
     *
     */
    form.on('submit(login)', function(data){
        $('#login').submit();
        return false;
    });
});