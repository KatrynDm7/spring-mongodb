define(['jquery'], function($) {
    return function($form, data, event, param) {
        var settings = {
            type: $form.attr('method'),
            url: $form.attr('action'),
            data: data,
            success: function(data) {
                if (event == 'delete') {
                    $('tr.'+data).hide();
                }
            },
            error: function(data) {
            }
        };

        if (param !== undefined) $.extend(settings, param);

        $.ajax(settings);
    };
});