'use strict';

define([
        'jquery',
        'components/i18n/i18n'
    ],
    function($, I18n) {

        var Settings = function(options) {
            this._init();
        };

        Settings.prototype = {
            _init: function () {
                var i18n = new I18n();
            }
        }

        Settings.prototype = $.extend(Object.create(Settings.prototype), Object.create({}));
        new Settings({});
    }
)
