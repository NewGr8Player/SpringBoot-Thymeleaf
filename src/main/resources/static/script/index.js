'use strict'

/**
 * 首页JS
 *
 * @author NewGr8Player
 */
layui.use(['layer', 'jquery', 'element'], function () {
    var layer = layui.layer,
        $ = layui.$,
        element = layui.element;

    /**
     * tab页控件Id
     *
     * @type {string}
     */
    var panel = 'panel';

    /**
     * tab页方法
     *
     * @type {{tabAdd: tabAdd, tabChange: tabChange, tabDelete: tabDelete}}
     */
    var active = {
        tabAdd: function (id, name, url) {
            element.tabAdd(panel, {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                id: id
            });
        },
        tabChange: function (id) {
            element.tabChange(panel, id);
        },
        tabDelete: function (id) {
            element.tabDelete(panel, id);
        }
    };

    /**
     * 页面加载完毕
     */
    $(function () {
        menuRender($("#currentMenuCode").val());
        element.init();
        /* 初始化页面按钮 */
    });

    /**
     * 导航点击事件
     */
    element.on('nav(menu)', function (elem) {
        var $elem = $(elem),
            link = $elem.attr('link'),
            panelId = link.replace(/\//g, '').replace(/:/g, '');
        if (!!link) {/* 是否为最终节点 */
            if (!$('[lay-id="' + panelId + '"]').length) {/* 是否曾打开过 */
                active.tabAdd(panelId, $elem.text(), link);
            }
            active.tabChange(panelId);
        }
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
        $("#menuBlock").empty();
        var $parentELement = $('<li class="layui-nav-item"></li>');
        var $childContainer = $('<dl class="layui-nav-child"></dl>');
        var $childElement = $('<dd></dd>');
        var $result = $("#menuBlock");
        for (var index in menuList) {
            var $tempParent = $parentELement;
            $tempParent.append(menuElementHandler(menuList[index]['current']));
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
        var url = !!currentElement['menuUrl'] ? basePath + currentElement['menuUrl'] : '';
        var $icon = $('<i class="layui-icon ' + currentElement['menuIcon'] + '"></i>');
        var $link = $('<a href="javascript:;" link="' + url + '"></a>');
        $link.append($icon);
        $link.append($('<span>' + currentElement['menuName'] + '</span>'));
        return $link;
    }
});