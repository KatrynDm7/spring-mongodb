'use strict';

require.config({
    paths: {
        'jquery': 'libs/jquery',
        'jquery.cookie': 'libs/jquery.cookie',
        'moment': 'libs/moment',
        'functions': 'functions'
    }
});

require(['functions', 'jquery']);