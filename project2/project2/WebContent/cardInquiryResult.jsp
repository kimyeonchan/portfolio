<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="com.*" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.math.BigInteger" %>
<%@page import="java.math.RoundingMode" %>
<%@page import="com.mongodb.client.FindIterable"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.client.MongoDatabase"%>
<%@page import="org.bson.Document"%>
<%@page import="java.util.*"%>
<%
String id="";
if(id == null)
{
    response.sendRedirect("./login.jsp");
}

id = (String)session.getAttribute("id");

String date1 = request.getParameter("date1");
String date2 = request.getParameter("date2");
String age = request.getParameter("age");


OPE o=OPE.getOPE();
int age1=116-Integer.parseInt(age)+1;
int age2=age1-9 ;
String tempage1 = String.valueOf(age1)+"0000";
String tempage2 = String.valueOf(age2)+"9999";
age1=Integer.parseInt(tempage1);
age2=Integer.parseInt(tempage2);
BigInteger tage1=o.encrypt(BigInteger.valueOf(age1));
BigInteger tage2=o.encrypt(BigInteger.valueOf(age2));
int rage1=tage1.intValue();
int rage2=tage2.intValue();


System.out.println("date1 : "+date1);
System.out.println("date2 : "+date2);
System.out.println("age1 : "+age1);
System.out.println("age2 : "+age2);


BigInteger tbdate1=o.encrypt(new BigInteger(date1));
BigInteger tbdate2=o.encrypt(new BigInteger(date2));
int rdate1=tbdate1.intValue();
int rdate2=tbdate2.intValue();



MongoDatabase db = MongoDBCreate.getMongoDatabase();
/*
FindIterable<Document> iterable = db.getCollection("information")
.find(new Document("late",new Document("$gt",rdate1))
      .append("late",new Document("$lt",rdate2))
      .append("rrn1",new Document("$gt",rage1))
      .append("rrn1",new Document("$lt",rage2))
      );
*/
//FindIterable<Document> iterable = db.getCollection("information")
//.find(new Document("late",new Document("$gt",rdate1).append("$lt",rdate2))
      //.append("rrn1",new Document("$gt",rage2).append("$lt",rage1))  //이부분만 지우면 잘실행됨 테스트 가능 데이터부족으로 인한 미실행
 //     );
FindIterable<Document> iterable = db.getCollection("information")
.find();
for(Document document:iterable){
	//document.getInteger("rrn1");
	//document.getInteger("rrn2");
	//document.getInteger("late");
	BigInteger tbdate10=o.decrypt(new BigInteger(document.getDouble("rrn1").toString()));
	BigInteger tbdate20=o.decrypt(new BigInteger(document.getDouble("rrn2").toString()));
	BigInteger tbdate30=o.decrypt(new BigInteger(document.getDouble("late").toString()));
	System.out.println(tbdate10+" "+tbdate20+" "+tbdate30);
}
ArrayList<String> result=KAnonymity.getIdentifier(iterable);
%>

<html>
<head>
   <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
   <nav class="navbar navbar-default">
      <div class="container-fluid">
         <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
               data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
               <span class="sr-only">Toggle navigation</span> <span
                  class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">ParkingLot</a>
         </div>

         <div class="collapse navbar-collapse"
            id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
               <li><a href="search.jsp">Search</a></li>
               <li class="active"><a href="cardInquiry.jsp">CardInquiry<span class="sr-only">(current)</span></a></li>
               
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
               <li><a href="logout.jsp">로그아웃</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
               <li><a href="logout.jsp">(<%=id%>)님 안녕하세요</a></li>
            </ul>
         </div>
      </div>
   </nav>
   <article>
      <h3> 조회결과</h3>
      
      <table class="table table-striped table-hover ">
         <thead>
            <tr>
               <th>성별</th>
               <th>나이</th>
               <th>최근거래일</th>
               <th>카드</th>
            </tr>
         </thead>
         
         <tbody>
         
            <%
            for (String document : result) {
                 %>
               <tr>
                 <% 
                StringTokenizer st=new StringTokenizer(document," ");
                int n=st.countTokens();
                for(int i=0;i<n;i++){%>
                  <td><%=st.nextToken()%></td>
                <%
                   
                }
                %>
               </tr>
               <%
            }
         %>
         </tbody>
      </table>
      
   </article>
   
</body>
</html>