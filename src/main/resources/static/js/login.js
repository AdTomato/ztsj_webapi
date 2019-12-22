(function (window, document, $) {
    'use strict';

    $("#loginForm").formValidation({
        locale: 'zh_CN',
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'icon wb-check',
            invalid: 'icon wb-close',
            validating: 'icon wb-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    regexp: {
                        regexp: /^admin$/,
                        message: '只支持超管登陆'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码必须大于6且小于30个字符'
                    }
                }
            }/*,
            afs: {
                validators: {
                    notEmpty: {
                        message: '请拖动验证'
                    },
                    stringLength:{
                    		min: 1,
                    		max: 4
                    }
                }
            }*/
        }
    });
    var app_key = 'FFFF00000000017ADC15';
    var nc_scene = 'nc_login';
    var nc_token = [app_key, (new Date()).getTime(), Math.random()].join(':');
    var NC_Opt =
        {
            renderTo: "#dom_id",
            appkey: app_key,
            scene: nc_scene,
            token: nc_token,
            customWidth: 300,
            trans: {"key1": "code0"},
            elementID: ["username"],
            is_Opt: 0,
            language: "cn",
            isEnabled: true,
            timeout: 3000,
            times: 5,
            apimap: {
                // 'analyze': '//a.com/nocaptcha/analyze.jsonp',
                // 'get_captcha': '//b.com/get_captcha/ver3',
                // 'get_captcha': '//pin3.aliyun.com/get_captcha/ver3'
                // 'get_img': '//c.com/get_img',
                // 'checkcode': '//d.com/captcha/checkcode.jsonp',
                // 'umid_Url': '//e.com/security/umscript/3.2.1/um.js',
                // 'uab_Url': '//aeu.alicdn.com/js/uac/909.js',
                // 'umid_serUrl': 'https://g.com/service/um.json'
            },
            callback: function (data) {
                var $form = $('#loginForm').data('formValidation');
                $form.updateStatus('afs', 'VALID', 'notEmpty');
                $.ajax({
                    url: '/public/login/afs',
                    type: 'GET',
                    data: {
                        session: data.csessionid,
                        sig: data.sig,
                        token: nc_token,
                        scene: nc_scene
                    },
                    success: function (result) {
                        if (result.errcode == 0) {
                            $form.updateStatus('afs', 'VALID', 'stringLength');
                        } else {
                            $form.updateStatus('afs', 'INVALID', 'stringLength');
                            $form.updateMessage('afs', 'stringLength', result.message);
                        }
                    }
                })
            }
        }
    /*var nc = new noCaptcha(NC_Opt)
    nc.upLang('cn', {
        _startTEXT: "请按住滑块，拖动到最右边",
        _yesTEXT: "验证通过",
        _error300: "哎呀，出错了，点击<a href=\"javascript:__nc.reset()\">刷新</a>再来一次",
        _errorNetwork: "网络不给力，请<a href=\"javascript:__nc.reset()\">点击刷新</a>",
    })*/

})(window, document, jQuery);
