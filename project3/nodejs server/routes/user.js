
/*
 * GET users listing.
 */

var mysql = require('mysql');
var pool = mysql.createPool({

	  host     : 'localhost',
	  user     : 'root',
	  password : 'root',
	  port     : 3306,
	  database : 'test'
});

exports.list = function(req, res){
  res.send("respond with a resource");
};

exports.select = function(req, res){
	var adf= a
	adf = adf + b
	
	res.send('dfsdf')
  //res.send("test respond with a resource");
//  
//  pool.getConnection(function (err, connection){
//		var selectquery = "select user_id, user_password ,user_name, user_tel, user_email from user";
//		connection.query(selectquery, function(err, rows){
//			if(err){
//				console.error('err : ' + err);
//			}
//			console.log('rows : ' + JSON.stringify(rows));
//			
//			res.render('list', { title: '회원 리스트(', rows: rows });
//			//res.writeHead(200, { 'Content-Type': 'text/plain',
//            //    'Trailer': 'Content-MD5',
//            //    'charset' : 'UTF-8' });
//			
//			//res.write(JSON.stringify(rows) +',');
//			//res.addTrailers({'Content-MD5': "7895bf4b8828b55ceaf47747b4bca667"});
//			//res.end();
//			res.write(JSON.stringify(rows));
//			connection.release();
//		});
//	});
};

exports.login= function(req, res){
	var userInfo= req.session.userInfo;
};