---
title: FullContact endpoint
keywords: 
last_updated: November 6, 2017
tags: []
summary: "Detailed description of the API of the FullContact endpoint."
---

## Overview

The FullContact endpoint has the following features:
 
- Authorization of requests
- Full access to the REST API
- Shortcuts for the Person API and Company API
- Automatic handling for webhooks for Person API and Company API

Please make sure you take a look at the documentation from FullContact as features are based on its API:

- [FullContact Developer API](https://www.fullcontact.com/developer/docs/)

## Quick start

Once you configured the endpoint, you can find information about a person like this:

```js
app.endpoints.fullcontact.person.findByEmail('john.doe@test.com', {confidence: 'low'}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

You can find information about a company like this:

```js
app.endpoints.fullcontact.company.findByDomain('testcompany.com', {}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

## Configuration

Before configuring the endpoint you will need to create a developer account in FullContact:

https://www.fullcontact.com/

Once you have your account you will see an API key that you need to use during the configuration
of the endpoint.

### API key

This is the API key provided by FullContact.

## Javascript API

The Javascript API of the endpoint is based on the [REST API of FullContact](https://www.fullcontact.com/developer/docs/),
so you should see their documentation for details on parameters and data formats.

### Find person by email

```js
app.endpoints.fullcontact.person.findByEmail(email, options, callbackData, callback);
```

Finds a person by email. You can process the result in the callback, for example:

```js
app.endpoints.fullcontact.person.findByEmail('john.doe@test.com', {confidence: 'low'}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

Parameters:

- `email`: the email used to lookup the person
- `options`: additional parameters allowed by the API
- `callbackData`: this parameter will be sent as the second parameter of the callback
- `callback`: this function will process the result of the request

Keep in mind that the callback could take up to a few minutes to be called.

Check [REST API of FullContact](https://www.fullcontact.com/developer/docs/) for more details about the request and response.

### Find person by phone

```js
app.endpoints.fullcontact.person.findByPhone(phone, options, callbackData, callback);
```

Finds a person by phone. You can process the result in the callback, for example:

```js
app.endpoints.fullcontact.person.findByPhone('+18329547846', {confidence: 'low'}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

Parameters:

- `phone`: the phone number used to lookup the person
- `options`: additional parameters allowed by the API
- `callbackData`: this parameter will be sent as the second parameter of the callback
- `callback`: this function will process the result of the request

Keep in mind that the callback could take up to a few minutes to be called.

Check [REST API of FullContact](https://www.fullcontact.com/developer/docs/) for more details about the request and response.

### Find person by Twitter

```js
app.endpoints.fullcontact.person.findByTwitter(twitter, options, callbackData, callback);
```

Finds a person by Twitter username. You can process the result in the callback, for example:

```js
app.endpoints.fullcontact.person.findByTwitter('john', {confidence: 'low'}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

Parameters:

- `twitter`: the Twitter account used to lookup the person
- `options`: additional parameters allowed by the API
- `callbackData`: this parameter will be sent as the second parameter of the callback
- `callback`: this function will process the result of the request

Keep in mind that the callback could take up to a few minutes to be called.

Check [REST API of FullContact](https://www.fullcontact.com/developer/docs/) for more details about the request and response.

### Find company by domain

```js
app.endpoints.fullcontact.company.findByDomain(domain, options, callbackData, callback);
```

Finds a company by domain. You can process the result in the callback, for example:

```js
app.endpoints.fullcontact.company.findByDomain('slingr.io', {}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

Parameters:

- `domain`: the domain of the company
- `options`: additional parameters allowed by the API
- `callbackData`: this parameter will be sent as the second parameter of the callback
- `callback`: this function will process the result of the request

Keep in mind that the callback could take up to a few minutes to be called.

Check [REST API of FullContact](https://www.fullcontact.com/developer/docs/) for more details about the request and response.

### Find company by name

```js
app.endpoints.fullcontact.company.findByName(name, options, callbackData, callback);
```

Finds a company by name. You can process the result in the callback, for example:

```js
app.endpoints.fullcontact.company.findByName('SLINGR', {}, {}, function(event, callbackData) {
    sys.logs.info('result: '+JSON.stringify(event.data));
});
```

Parameters:

- `name`: the name of the company
- `options`: additional parameters allowed by the API
- `callbackData`: this parameter will be sent as the second parameter of the callback
- `callback`: this function will process the result of the request

Keep in mind that the callback could take up to a few minutes to be called.

Check [REST API of FullContact](https://www.fullcontact.com/developer/docs/) for more details about the request and response.

## Events

### Webhook

These are the response sent by FullContact to requests made. You shouldn't handle these events manually, instead use
the callbacks in the Javascript API.

{% include links.html %}


## About SLINGR

SLINGR is a low-code rapid application development platform that accelerates development, with robust architecture for integrations and executing custom workflows and automation.

[More info about SLINGR](https://slingr.io)

## License

This endpoint is licensed under the Apache License 2.0. See the `LICENSE` file for more details.


