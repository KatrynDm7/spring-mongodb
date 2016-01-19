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
            this.deleteEntryClass = 'delete';
            this.switchLangClass = 'localeSwitch';
            this._init();
        };

        i18n.prototype = {
            _init: function () {
                var self = this;
                var locale = $.cookie('lang');
                if (typeof locale == 'undefined') {
                    locale = $('html').attr('lang');
                }

                var prev = $('.date').html();
                this._setLocale(locale);
                this._translateDate(prev);

                $('.' + this.deleteEntryClass).on('click', function() {
                    self._deleteEntry(this);
                });

                $('.' + this.switchLangClass).on('click', function(e) {
                    e.preventDefault();

                    if ($(this).attr('href')) {
                        var locale = $(this).text();
                        self._setLocale(locale);
                        self._translateDate(prev);
                        self._translate(locale);
                    }
                });
            },
            _setLocale: function(locale) {
                moment.locale(locale);
                $('html').attr('lang', locale);
            },
            _translateDate: function (prev) {
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
            },
            _translate: function(locale) {
                var self = this;
                $.ajax({
                    method: 'GET',
                    url: '/lang=' + locale
                }).complete(function(data) {
                    $.ajax({
                        method: 'GET',
                        url: '/'
                    }).complete(function(msg) {
                        var page = msg.responseText.split('<tbody>')[1].split('</tbody>')[0];
                        $('tbody').html($.trim(page));

                        $.each($('.date'), function(key, value) {
                            var now = moment($(value).html());
                            if (now.isValid()) {
                                $(value).html(now.format(self.dateFormat));
                            }
                        })
                    });
                });
            },
            _deleteEntry: function(e) {
                var data = {
                    id: $(e).closest('tr').attr('class')
                };
                var event = 'delete';
                require(['functions/ajaxSender'], function(ajaxSender) {
                    ajaxSender($('form'), data, event);
                });
            }
        }

        i18n.prototype = $.extend(Object.create(i18n.prototype));

        return i18n;
    }
);