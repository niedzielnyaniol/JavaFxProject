// Accepts a url and a callback function to run.
function requestCrossDomain(site, values, callback) {

    // If no url was passed, exit.
    if ( !site ) {
        alert('No site was passed.');
        return false;
    }

    // Take the provided url, and add it to a YQL query. Make sure you encode it!
    var yql = 'http://query.yahooapis.com/v1/public/yql?q=' + encodeURIComponent('select * from html where url="' + site + '?' + values + '"') + '&format=xml&callback=?';

    // Request that YSQL string, and run a callback function.
    // Pass a defined function to prevent cache-busting.
    $.getJSON(yql, values, cbFunc);

    function cbFunc(data) {
        // If we have something to work with...
        if ( data.results[0] ) {
            // Strip out all script tags, for security reasons.
            // BE VERY CAREFUL. This helps, but we should do more.
            data = filterData(data.results[0]);

            // If the user passed a callback, and it
            // is a function, call it, and send through the data var.
            if ( typeof callback === 'function') {
                callback(data);
            }
        }
        // Else, Maybe we requested a site that doesn't exist, and nothing returned.
        else throw new Error('Nothing returned from getJSON.');
    }

    function filterData(data){
        data = data.replace(/<?\/body[^>]*>/gi,'');
        data = data.replace(/<?\/p[^>]*>/gi,'');
        data = data.replace(/[\r|\n]+/gi,'');
        data = data.replace(/<--[\S\s]*?-->/gi,'');
        data = data.replace(/<noscript[^>]*>[\S\s]*?<\/noscript>/gi,'');
        data = data.replace(/<script[^>]*>[\S\s]*?<\/script>/gi,'');
        data = data.replace(/<script.*\/>/,'');
        return data;
    }
}
