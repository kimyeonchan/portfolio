<%@ page contentType="text/html;charset=EUC-KR"%>

<%
    String id="";
    if(id == null)
    {
        response.sendRedirect("./login.jsp");
    }
    
    id = (String)session.getAttribute("id");
    
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
					<li><a href="logout.jsp">�α׾ƿ�</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.jsp">(<%=id%>)�� �ȳ��ϼ���</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<article>
		<h3> ī�����ȸ</h3>
		<form class="navbar-form navbar-left" role="search" method="post" action="cardInquiryResult.jsp">
			<div class="form-group">
				�����Ⱓ  : <input type="text" class="form-control" placeholder="YYYYMMDD" name="date1"> ~ 
				<input type="text" class="form-control" placeholder="YYYYMMDD" name="date2"><p><p>
				���ɴ� : <input type="text" class="form-control" placeholder="age" name="age" >ex)20,30,40 ... <p><p>
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
			
			
		</form>
	</article>
	
</body>
</html>