<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2022/12/17
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/signin.css" rel="stylesheet">
</head>
<body>
<main class="form-signin">
    <form action="${pageContext.request.contextPath}/api/user/login" method="post">
        <h1 class="h3 mb-3 fw-normal">${result.message}</h1>
        <div class="form-floating">
            <input type="text" class="form-control" id="name" name="name" placeholder="用户名">
            <label for="name">用户名</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            <label for="password">密码</label>
        </div>
        <div class="input-group input-group-lg">
            <span class="input-group-text" id="inputGroup-sizing-default">
                <img src="${pageContext.request.contextPath}/api/kaptcha">
            </span>
            <input type="text" class="form-control" aria-label="Sizing example input"
                   id="code" name="code" aria-describedby="inputGroup-sizing-default">
        </div>
        <div class="checkbox mb-3" style="margin-top: 20px;">
            <label><input type="checkbox" id="remember" name="remember" checked>&nbsp;记住</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">登录</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
    </form>
</main>
</body>
</html>
