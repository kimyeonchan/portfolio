exports.main = function(req, res){
    
	res.render('main');
};

//exports.get('/openapi', function(req, res){
//    var search = req.query.search;
//    var queryOption = {'query':search, 'display':10, 'start':1, 'sort':'sim'};
//    var query = querystring.stringify(queryOption);
//    var client_id = 'Vqke1aAbesyM3VondrvO';
//    var host = 'openapi.naver.com';
//    var uri = '/openapi/v3/maps.js?';
//    var options = {
//        host: host,
//        path: uri + query,
//        method: 'GET',
//        
//        headers: {'X-Naver-Client-Id':client_id}
//    };
//    req = https.request(options, function(response) {
//        console.log('STATUS: ' + res.statusCode);
//        console.log('HEADERS: ' + JSON.stringify(res.headers));
//        response.setEncoding('utf8');
//        response.on('data', function (xml) {
//            parseString(xml, function(err, result){
//                var data = JSON.stringify(result);
//                res.send(data);
//            });
//        });
//    });
//
//    req.end();
//});