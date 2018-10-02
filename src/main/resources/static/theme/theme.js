;layui.define(['laytpl', 'layer', 'element', 'util'], function (exports) {
    "use strict"
    var n = {
        tableName: 'theme'
        /* 主题配置 */
        , theme: {
            color: [{
                main: '#20222A'
                , selected: '#009688'
                , alias: 'default'
            }, {
                main: '#03152A'
                , selected: '#3B91FF'
                , alias: 'dark-blue' /* 藏蓝 */
            }, {
                main: '#2E241B'
                , selected: '#A48566'
                , alias: 'coffee' /* 咖啡 */
            }, {
                main: '#50314F'
                , selected: '#7A4D7B'
                , alias: 'purple-red' /* 紫红 */
            }, {
                main: '#344058'
                , logo: '#1E9FFF'
                , selected: '#1E9FFF'
                , alias: 'ocean' /* 海洋 */
            }, {
                main: '#3A3D49'
                , logo: '#2F9688'
                , selected: '#5FB878'
                , alias: 'green' /* 墨绿 */
            }, {
                main: '#20222A'
                , logo: '#F78400'
                , selected: '#F78400'
                , alias: 'red' /* 橙色 */
            }, {
                main: '#28333E'
                , logo: '#AA3130'
                , selected: '#AA3130'
                , alias: 'fashion-red' /* 时尚红 */
            }, {
                main: '#24262F'
                , logo: '#3A3D49'
                , selected: '#009688'
                , alias: 'classic-black' /* 经典黑 */
            }, {
                logo: '#226A62'
                , header: '#2F9688'
                , alias: 'green-header' /* 墨绿头 */
            }, {
                main: '#344058'
                , logo: '#0085E8'
                , selected: '#1E9FFF'
                , header: '#1E9FFF'
                , alias: 'ocean-header' /* 海洋头 */
            }, {
                header: '#393D49'
                , alias: 'classic-black-header' /* 经典黑头 */
            }, {
                main: '#50314F'
                , logo: '#50314F'
                , selected: '#7A4D7B'
                , header: '#50314F'
                , alias: 'purple-red-header' /* 紫红头 */
            }, {
                main: '#28333E'
                , logo: '#28333E'
                , selected: '#AA3130'
                , header: '#AA3130'
                , alias: 'fashion-red-header' /* 时尚红头 */
            }, {
                main: '#28333E'
                , logo: '#009688'
                , selected: '#009688'
                , header: '#009688'
                , alias: 'green-header' /* 墨绿头 */
            }]
            , initColorIndex: 10
        }
    }
        , $ = layui.jquery
        , i = layui.laytpl
        , o = $("body")
        , y = "layui-this"
        , theme = {
        setTheme: function (e) {
            e.siblings(".layui-this").data("index");
            e.hasClass(y) || (e.addClass(y).siblings(".layui-this").removeClass(y), theme.initTheme(e.data("index")))
        }
        , theme: function (e) {
            var t = (n.theme, layui.data(n.tableName)),
                l = "LAY_layadmin_theme",
                s = document.createElement("style"),
                r = i([".layui-side-menu,", ".layadmin-pagetabs .layui-tab-title li:after,", ".layadmin-pagetabs .layui-tab-title li.layui-this:after,", ".layui-layer-admin .layui-layer-title,", ".layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child", "{background-color:{{d.color.main}} !important;}", ".layui-nav-tree .layui-this,", ".layui-nav-tree .layui-this>a,", ".layui-nav-tree .layui-nav-child dd.layui-this,", ".layui-nav-tree .layui-nav-child dd.layui-this a", "{background-color:{{d.color.selected}} !important;}", ".layui-layout-admin .layui-logo{background-color:{{d.color.logo || d.color.main}} !important;}", "{{# if(d.color.header){ }}", ".layui-layout-admin .layui-header{background-color:{{ d.color.header }};}", ".layui-layout-admin .layui-header a,", ".layui-layout-admin .layui-header a cite{color: #f8f8f8;}", ".layui-layout-admin .layui-header a:hover{color: #fff;}", ".layui-layout-admin .layui-header .layui-nav .layui-nav-more{border-top-color: #fbfbfb;}", ".layui-layout-admin .layui-header .layui-nav .layui-nav-mored{border-color: transparent; border-bottom-color: #fbfbfb;}", ".layui-layout-admin .layui-header .layui-nav .layui-this:after, .layui-layout-admin .layui-header .layui-nav-bar{background-color: #fff; background-color: rgba(255,255,255,.5);}", ".layadmin-pagetabs .layui-tab-title li:after{display: none;}", "{{# } }}"].join("")).render(e = $.extend({}, t.theme, e)),
                u = document.getElementById(l);
            "styleSheet" in s
                ? (s.setAttribute("type", "text/css"), s.styleSheet.cssText = r)
                : s.innerHTML = r
                , s.id = l
                , u && o[0].removeChild(u)
                , o[0].appendChild(s)
                , o.attr("layadmin-themealias", e.color.alias)
                , t.theme = t.theme || {}
                , layui.each(e, function (e, a) {
                t.theme[e] = a
            })
                , layui.data(n.tableName, {key: "theme", value: t.theme})
        }
        , initTheme: function (e) {
            var a = n.theme;
            e = e || n.theme.initColorIndex, a.color[e] && (a.color[e].index = e, theme.theme({
                color: a.color[e]
            }));
        }
    };
    exports('theme', theme);
});