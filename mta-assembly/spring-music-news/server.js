var http = require('http');
var url = require('url');
var fs = require('fs');

var host = "0.0.0.0";
var port = process.env.PORT || 3000;

http.createServer(
		function(req, res) {
			if (req.method === 'GET' && req.url === '/news') {
				res.statusCode = 200;
				res.end(JSON.stringify(JSON.parse(fs.readFileSync('news.json',
						'utf8'))), encoding = 'utf8');
			} else {
				res.statusCode = 404;
				res.end();
			}
		}).listen(port, null);

console.log('Server running at http://' + host + ':' + port + '/');
