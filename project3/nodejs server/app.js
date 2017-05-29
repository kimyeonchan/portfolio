
/**
 * Module dependencies.
 */

var express = require('express')			// express 모듈 로드
  , routes = require('./routes')
  //, session = require('express-session')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path')
  , signup = require('./routes/signup')
  , main = require('./routes/main')
  , parkData = require('./routes/parkData')
  , login = require('./routes/login');

var app = express();

// all environments
app.set('port', process.env.PORT || 3301);	//서버 포트
app.set('views', __dirname + '/views');		//html 출력 경로
app.set('view engine', 'ejs');				//html 엔진 설정 jade, ejs(embeded js)
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.cookieParser());
app.use(express.session({
	key:'sid',//세션키
	secret: 'secret',//비밀키
	cookie:{
		maxAge: 1000 * 60 * 60 //쿠키유지 1시간
	}
}));
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

//app.use('/joinForm', joinForm);
//app.use('/joinForm', joinForm2);

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get('/', routes.index);					// index.jade(ejs) 파일을 라우팅. 컴파일하면 index.js 내용이 라이트되에 페이지에 풀력
app.get('/users', user.list);			// index.jade(ejs) 파일을 라우팅. 컴파일하면 index.js 내용이 라이트되에 페이지에 풀력
app.get('/usersList', user.select);
app.get('/signup', signup.form);
app.get('/login', login.form);
app.get('/main', main.main);
app.get('/data', parkData.get);
app.post('/signup', signup.join);
app.post('/login', login.login);

app.use(function(req,res,next){
   console.log('custom log :'+req.path);
   next();
});

http.createServer(app).listen(app.get('port'), function(){			//서버 생성 및 포트 연결
  console.log('Express server listening on port ' + app.get('port'));
});
