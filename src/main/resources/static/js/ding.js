(function (window, document, $) {
    var redirect_ip = "http://120.78.66.201:8888";
    var login_success_redirect_url = "http://120.78.66.201";
    var ding_url = 'https://oapi.dingtalk.com/connect/oauth2/sns_authorize?';
    var fail_url = 'http://120.78.66.201:8888/login';
    var appid = 'dingoacj3ga5miiig5nxf2';
    var state = 'STATE';

    var redirect_url = redirect_ip + '/login/dingtalk?redirect_uri=' +
        encodeURIComponent(redirect_ip + "/oauth/authorize?client_id=api&response_type=code&scope=read&redirect_uri=" + login_success_redirect_url + "/oauth")+"&login_fail_redirect_uri="+ encodeURIComponent(fail_url);

    function opending() {
        /*
         * 解释一下goto参数，参考以下例子：
         * var url = encodeURIComponent('http://localhost.me/index.php?test=1&aa=2');
         * var goto = encodeURIComponent('https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=appid&response_type=code&scope=snsapi_login&state=STATE&redirect_uri='+url)
         */
        var obj = DDLogin({
            id: "login_container",//这里需要你在自己的页面定义一个HTML标签并设置id，例如<div id="login_container"></div>或<span id="login_container"></span>
            goto: encodeURIComponent(ding_url + "appid=" + appid + "&response_type=code&scope=snsapi_login&state=" + state + "&redirect_uri=" + encodeURIComponent(redirect_url)),
            style: "border:none;background-color:#FFFFFF;",
            width: "365",

            height: "400"
        });
    }

    var hanndleMessage = function (event) {
        var origin = event.origin;
        console.log("origin", event.origin);
        if (origin == "https://login.dingtalk.com") { //判断是否来自ddLogin扫码事件。
            var tmp_code = event.data; //拿到loginTmpCode后就可以在这里构造跳转链接进行跳转了
            console.log("tmp_code", tmp_code);
            //拿到loginTmpCode后,在这里构造跳转链接进行跳转
            window.parent.postMessage(tmp_code, '*');
            window.location.href = ding_url + "appid=" + appid + "&response_type=code&scope=snsapi_login&state=" + state + "&redirect_uri=" + redirect_url + "&loginTmpCode=" + tmp_code;
        }
    };
    if (typeof window.addEventListener != 'undefined') {
        window.addEventListener('message', hanndleMessage, false);
    } else if (typeof window.attachEvent != 'undefined') {
        window.attachEvent('onmessage', hanndleMessage);
    }
    opending();

})(window, document, jQuery);





