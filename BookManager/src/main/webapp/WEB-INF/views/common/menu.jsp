<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2022/12/13
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'home'}">active</c:if>"
                   href="${pageContext.request.contextPath}/">
                    <span data-feather="home"></span>
                    网站详情
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'ebook'}">active</c:if>"
                   href="${pageContext.request.contextPath}/view/ebook">
                    <span data-feather="book"></span>
                    图书管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'attachment'}">active</c:if>"
                   href="${pageContext.request.contextPath}/view/attachment">
                    <span data-feather="paperclip"></span>
                    附件管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'image'}">active</c:if>"
                   href="${pageContext.request.contextPath}/view/image">
                    <span data-feather="image"></span>
                    图片管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'category'}">active</c:if>"
                   href="${pageContext.request.contextPath}/view/category">
                    <span data-feather="layers"></span>
                    类别管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link <c:if test="${menuActive eq 'user'}">active</c:if>"
                   href="${pageContext.request.contextPath}/view/user">
                    <span data-feather="users"></span>
                    用户管理
                </a>
            </li>
        </ul>
    </div>
</nav>
