'use strict'

/**
 * 首页JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery'], function () {
    var layer = layui.layer,
        $ = layui.$

    $(function () {
        menuRender($("#currentMenuCode").val());
    });

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

    /**
     * 渲染菜单
     *
     * @param currentMenuCode
     */
    function menuRender(currentMenuCode) {
        $.ajax({
            url: basePath + '/modelMenu?menuType=model&menuCode=' + currentMenuCode,
            type: 'post',
            async: false,
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
     *
     * @param menuList
     */
    function menuElementRender(menuList) {
        console.log(menuList);
        $("#menuBlock").empty();
        var $parentELement = $('<li class="layui-nav-item"></li>');
        var $childContainer = $('<dl class="layui-nav-child"></dl>');
        var $childElement = $('<dd></dd>');
        var $result = $("#menuBlock");
        for (var index in menuList) {
            var $tempParent = $parentELement;
            $tempParent.append(menuElementHandler(menuList[index]))
            if (menuList[index]['child'].length > 0) {
                var childList = menuList[index]['child'];
                var $tempChildContainer = $childContainer;
                for (var j in childList) {
                    var $tempChild = $childElement;
                    $tempChild.append(menuElementHandler(childList[j]));
                    $tempChildContainer.append($tempChild);
                }
                $tempParent.append($tempChildContainer);
            }
            $result.append($tempParent);
        }
    }

    /**
     * 处理通用元素
     *
     * @param currentElement
     * @return {pe.fn.init|HTMLElement}
     */
    function menuElementHandler(currentElement) {
        var url = !!currentElement['menuUrl'] ? currentElement['menuUrl'] : 'javascript:;'
        var $icon = $('<i class="layui-icon ' + currentElement['menuIcon'] + '"></i>');
        var $link = $('<a href="' + url + '"></a>');
        $link.append($icon);
        $link.append($('<span>' + currentElement['menuName'] + '</span>'));
        return $link;
    }
});