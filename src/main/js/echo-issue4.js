
var sjsc = require('sockjs-client');

// See:
// https://github.com/rstoyanchev/spring-websocket-test/issues/4

var client = sjsc.create("http://localhost:8080/spring-websocket-test/sockjs/echo-issue4");

client.on('connection', function () {
    console.log("Woohoo, connected");
    for (var i = 0; i < 20000; i++) {
        client.write('Message ' + (i + 1));
    }
});

client.on('data', function (msg) {
    console.log("Got data: " + msg);
});

client.on('error', function (e) {
    console.log("Got error: " + e);
});
