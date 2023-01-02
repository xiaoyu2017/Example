<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2022/12/13
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <span data-feather="home"></span>
                    网站详情
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="book"></span>
                    图书管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="user"></span>
                    个人信息
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/view/category">
                    <span data-feather="layers"></span>
                    类别管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="#">
                    <span data-feather="menu"></span>
                    菜单管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/view/user">
                    <span data-feather="users"></span>
                    用户管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="settings"></span>
                    系统管理
                </a>
            </li>
        </ul>
    </div>
</nav>
