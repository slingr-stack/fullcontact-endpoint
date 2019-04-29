/////////////////////
// Public API
/////////////////////

endpoint.person = {};

endpoint.person.findByEmail = function(email, options, callbackData, callback) {
    var params = options || {};
    params.email = email;
    params.webhook = true;
    return endpoint.get('/person.json', {params: params}, callbackData, {webhook: callback});
};

endpoint.person.findByPhone = function(phone, options, callbackData, callback) {
    var params = options || {};
    params.phone = phone;
    params.webhook = true;
    return endpoint.get('/person.json', {params: params}, callbackData, {webhook: callback});
};

endpoint.person.findByTwitter = function(twitter, options, callbackData, callback) {
    var params = options || {};
    params.twitter = twitter;
    params.webhook = true;
    return endpoint.get('/person.json', {params: params}, callbackData, {webhook: callback});
};

endpoint.company = {};

endpoint.company.findByDomain = function(domain, options, callbackData, callback) {
    var params = options || {};
    params.domain = domain;
    params.webhook = true;
    return endpoint.get('/company/lookup.json', {params: params}, callbackData, {webhook: callback});
};

endpoint.company.findByName = function(name, options, callbackData, callback) {
    var params = options || {};
    params.companyName = name;
    params.webhook = true;
    return endpoint.get('/company/search.json', {params: params}, callbackData, {webhook: callback});
};

///////////////////////////////////
// Public API - Generic Functions
///////////////////////////////////

endpoint.get = function(url, options, callbackData, callback) {
    options = checkHttpOptions(url, options);
    return endpoint._get(options, callbackData, callback);
};

endpoint.post = function(url, options, callbackData, callback) {
    options = checkHttpOptions(url, options);
    return endpoint._post(options, callbackData, callback)
};

/////////////////////////////////////
//  Private helpers
////////////////////////////////////

var checkHttpOptions = function (url, options) {
    options = options || {};
    if (!!url) {
        if (isObject(url)) {
            // take the 'url' parameter as the options
            options = url || {};
        } else {
            if (!!options.path || !!options.params || !!options.body) {
                // options contains the http package format
                options.path = url;
            } else {
                // create html package
                options = {
                    path: url,
                    body: options
                }
            }
        }
    }
    return options;
};

var isObject = function (obj) {
    return !!obj && stringType(obj) === '[object Object]'
};

var stringType = Function.prototype.call.bind(Object.prototype.toString);
