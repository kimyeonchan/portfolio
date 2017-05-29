

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
	res.render('joinForm2', {title : '회원가입'});
};
exports.join = function(req, res){
	var id = req.body.id;
	var password = req.body.password;
	var name = req.body.name;
	var tel = req.body.tel;
	var email = req.body.email;
	
	console.log(id)
	var datas = [id, password, name, tel, email];
	var checkid = [id];
	
	console.log('insert user ' + id + ' , ' + password + ' , ' + name + ' , ' +
			tel + ' , ' + email);
	//res.json(req.body);
	res.setHeader('Access-Control-Allow-Origin','*')
	pool.getConnection(function (err, connection){
		var checkquery = "select * from user where user_id = ?" ;
		connection.query(checkquery, checkid, function(err, rows){
			if(err){
				console.error('err : ' + err);
			}
			if(rows == ""){
				var insertquery = "insert into user (user_id, user_password ,user_name, user_tel, user_email) values ( ? , ? , ?, ? , ?)";
				connection.query(insertquery, datas, function(err, rows){
					if(err){
						console.error('err : ' + err);
					}
					console.log('rows : ' + JSON.stringify(rows));

					res.send("true");
				});
			}
			else{
				res.send("false");
			}
			connection.release();
		});

	});
};