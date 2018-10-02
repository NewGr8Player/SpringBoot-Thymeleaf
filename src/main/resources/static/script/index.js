'use strict'

/**
 * 首页JS
 *
 * @author NewGr8Player
 */
layui.config({
    base: '/static/theme/'
}).extend({
    theme: 'theme'
}).use(['layer', 'jquery', 'element', 'theme', 'laytpl'], function () {
    var layer = layui.layer
        , $ = layui.$
        , element = layui.element
        , theme = layui.theme;

    /**
     * 打开tab标签
     *
     * @param layId
     */
    function switchTab(layId) {
        $('#LAY_app_tabsheader li').each(function (index, element) {
            $(element).attr('class', '');
        });
        $('[lay-id="' + layId + '"]').attr('class', 'layui-this');
        /* 选中样式 */
        $('#mainContent').attr('src', $('[lay-id="' + layId + '"]').attr('link'));
    }

    /**
     * 关闭除Home外所有Tab
     *
     * @param layId
     */
    function tabDeleteAllExceptDefaultAndActive() {
        $('#LAY_app_tabsheader li').each(function (index, element) {
            if ($(element).attr('lay-id') !== 'default' && $(element).attr('class') !== 'layui-this') {
                $(element).remove();
            }
        });
    }

    /**
     * tab页方法
     *
     * @type {{tabAdd: tabAdd, tabChange: tabChange, tabDelete: tabDelete}}
     */
    var tabEvent = {
        tabAdd: function (id, name, url) {
            if ($('[lay-id="' + id + '"]').length === 0) { /* 表示已存在 */
                var $tabBlock = $(
                    '<li lay-id="' + id + '" link="' + url + '">'
                    + '<span>' + name + '</span>'
                    + '<i class="layui-icon layui-unselect layui-tab-close">ဆ</i>'
                    + '</li>'
                );
                $tabBlock.bind('click', function () {
                    switchTab(id);
                });
                $tabBlock.find('i[class*="layui-tab-close"]').bind('click', function () {
                    tabEvent.tabDelete();
                });
                $('#LAY_app_tabsheader').append($tabBlock);
            }
            switchTab(id);
        },
        tabChange: function (layId) {
            switchTab(layId);
        },
        tabDelete: function () {
            if ($('#LAY_app_tabsheader li').length !== 1) {
                var layId = $('#LAY_app_tabsheader > [class="layui-this"]').attr('lay-id');
                switchTab($('[lay-id="' + layId + '"]').prev().attr('lay-id'));
                $('[lay-id="' + layId + '"]').remove();
            }

        },
        tabDeleteAll: function () {
            switchTab('default');
            tabDeleteAllExceptDefaultAndActive();
        },
        tabDeleteOthers: function () {
            tabDeleteAllExceptDefaultAndActive();
        }
    };

    /**
     * 导航点击事件
     */
    element.on('nav(layadmin-system-side-menu)', function (elem) {
        var $elem = $(elem),
            link = $elem.attr('link'),
            layId = link.replace(/\//g, '').replace(/:/g, '');
        if (!!link) {
            tabEvent.tabAdd(layId, $elem.text(), link);
        }
    });

    /**
     * 切换主题按钮点击
     */
    $('#userInfo').bind('click', function () {

    });


    /**
     * 用户按钮点击
     */
    $('#userInfo').bind('click', function () {
        layer.msg('用户信息', {icon: 3});
    });

    /**
     * 修改密码点击
     */
    $('#editPasswd').bind('click', function () {
        layer.msg('修改密码', {icon: 3});
    })

    /**
     * 关闭当前Tab
     */
    $('#closeThisTabs').bind('click', function () {
        tabEvent.tabDelete();
    });

    /**
     * 关闭其它Tab
     */
    $('#closeOtherTabs').bind('click', function () {
        tabEvent.tabDeleteOthers();
    });

    /**
     * 关闭所有Tab
     */
    $('#closeAllTabs').bind('click', function () {
        tabEvent.tabDeleteAll();
    });

    /**
     * 折叠/展开侧边导航
     */
    $('#LAY_app_flexible').bind('click', function () {
        var $menuContainer = $('#LAY_app'),
            $sideMenu = $('#LAY_app_flexible'),
            e = $(window).width(),
            l = (e >= 1200 ? 3 : e >= 992 ? 2 : e >= 768 ? 1 : 0);
        $("#LAY_app_flexible").hasClass('layui-icon-spread-left')
            ? ($sideMenu.removeClass('layui-icon-spread-left').addClass('layui-icon-shrink-right'),
                l < 2
                    ? $menuContainer.addClass('layadmin-side-spread-sm')
                    : $menuContainer.removeClass('layadmin-side-spread-sm'), $menuContainer.removeClass('layadmin-side-shrink'))
            : ($sideMenu.removeClass('layui-icon-shrink-right').addClass('layui-icon-spread-left'),
                l < 2
                    ? $menuContainer.removeClass('layadmin-side-shrink')
                    : $menuContainer.addClass('layadmin-side-shrink'), $menuContainer.removeClass('layadmin-side-spread-sm'));
    });

    /**
     * 刷新后台
     */
    $('#refresh').bind('click', function () {
        $('#mainContent').attr('src', $('#mainContent').attr('src'));
    });

    /**
     * 渲染菜单
     */
    function menuRender() {
        $.ajax({
            url: basePath + '/modelMenu?menuType=model&visiable=show'
            , type: 'post'
            , success: function (data) {
                menuElementRender(data);
                element.render('nav', 'layadmin-system-side-menu');
            }
            , error: function (res) {
                layer.msg('菜单渲染失败，原因：' + res, {icon: 2});
            }
        });
    }

    /**
     * 渲染菜单元素
     *
     * @param menuList
     */
    function menuElementRender(menuList) {
        $("#LAY-system-side-menu").empty();
        var $parentELement = $('<li class="layui-nav-item"></li>');
        var $childContainer = $('<dl class="layui-nav-child"></dl>');
        var $childElement = $('<dd class="layui-this"></dd>');
        var $result = $("#LAY-system-side-menu");
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
        $link.append($('<cite>' + currentElement['menuName'] + '</cite>'));
        return $link;
    }

    /**
     * 页面加载完毕
     */
    $(function () {
        menuRender();
        theme.initTheme(10);
        $('[lay-id=default]').attr('link', basePath + '/default');
        $('[lay-id=default]').bind('click',function(){
            switchTab('default');
        });
    });
});