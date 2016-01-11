'use strict';

define([
        'jquery',
        'moment',
        'jquery.cookie'
    ],
    function($, moment) {
        /**
         * i18n
         * @constructor
         */
        var i18n = function() {
            this.dateFormat = 'MMM DD, YYYY';
            this._init();
        };

        i18n.prototype = {
            _init: function () {
                var self = this;
                var locale = $.cookie('lang');
                if (typeof locale == 'undefined') {
                    locale = $('html').attr('lang');
                }

                $('.localeSwitch:contains("'+locale+'")').addClass('active').removeAttr('href');

                var prev = $('.date').html();
                this._setLocale(locale);
                this._translate(prev);

                $('.localeSwitch').on('click', function(e) {
                    if ($(this).attr('href')) {
                        var locale = $(this).text();
                        self._setLocale(locale);
                        self._translate(prev);
                    }
                });
            },
            _setLocale: function(locale) {
                moment.locale(locale);
                $('html').attr('lang', locale);
            },
            _translate: function (prev) {
                var self = this;

                $.each($('.date'), function(key, value) {
                    var now = moment($(value).html());
                    if (now.isValid()) {
                        $(value).html(now.format(self.dateFormat));
                    }
                    else {
                        $(value).html(prev);
                    }
                });
            }
        }

        i18n.prototype = $.extend(Object.create(i18n.prototype));

        return i18n;
    }
);