
var mysql = require('mysql');
var pool = mysql.createPool({

	  host     : 'localhost',
	  user     : 'root',
	  password : 'root',
	  port     : 3306,
	  database : 'test'
});

exports.form = function(req, res){

	//res.render('joinForm', {title : '회원가입'});
	res.render('login', {title : '회원가입'});
};

exports.login = function(req, res) {
	var id = req.body.userId;
	var password = req.body.userPassword;

	console.log(id)
	console.log(password)
	pool.getConnection(function(err, connection) {
		var selectquery = "select user_id, user_password from user where user_id='"+ id + "' and user_password ='"+password +"'";
		connection.query(selectquery, function(err, rows) {
			if (err) {
				res.setHeader('Access-Control-Allow-Origin','*')
				console.error('err : ' + err);
				}
			if(rows == ""){
				res.setHeader('Access-Control-Allow-Origin','*')
				res.send("false");
			}
			else{
				//세션추가  
				res.setHeader('Access-Control-Allow-Origin','*')
				res.send("true");
			}
		});
	});
};