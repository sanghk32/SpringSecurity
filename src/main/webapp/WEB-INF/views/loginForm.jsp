<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
</head>
<body>
    <h1>지이페홈 인그로</h1>
<hr>
<form action="/login" method="post">
    <input type="text" name="username" placeholder="username"><br>
    <input type="password" name="password" placeholder="password"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button>로그인뿌</button>

</form>
<a href="/joinForm">회원가입</a>
</body>
</html>