<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2022/12/12
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!--引入标签库-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <style>
        td {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
    </style>
</head>
<body>

<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#" style="text-align: center;">图书管理</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <input class="form-control form-control-dark w-100" type="text" placeholder="搜索..." aria-label="Search">
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="${pageContext.request.contextPath}/api/user/out">登出</a>
        </div>
    </div>
</header>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/menu.jsp"/>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">菜单管理</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group me-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick="showAddModel()">
                            添加
                        </button>
                    </div>
                </div>
            </div>

            <form class="row row-cols-lg-auto g-3 align-items-center" id="searchForm"
                  action="${pageContext.request.contextPath}/api/category/page/1">
                <div class="col-12">
                    <label class="visually-hidden" for="name">模糊名称</label>
                    <input type="text" class="form-control" id="searchName" name="name" value="${categoryVo.name}"
                           placeholder="模糊名称">
                </div>

                <div class="col-12">
                    <label class="visually-hidden" for="searchCreateDateStart">创建时间start</label>
                    <label class="visually-hidden" for="searchCreateDateEnd">创建时间end</label>
                    <div class="input-group">
                        <div class="input-group-text">创建时间</div>
                        <input type="text" class="form-control" id="searchCreateDateStart" name="createStart"
                               value="<fmt:formatDate value="${categoryVo.createStart}" type="both"/>" placeholder="时间">
                        <div class="input-group-text">-</div>
                        <input type="text" class="form-control" id="searchCreateDateEnd" name="createEnd"
                               value="<fmt:formatDate value="${categoryVo.createEnd}" type="both"/>" placeholder="时间">
                    </div>
                </div>
                <div class="col-12">
                    <label class="visually-hidden" for="searchUpdateDateStart">修改时间start</label>
                    <label class="visually-hidden" for="searchUpdateDateEnd">修改时间end</label>
                    <div class="input-group">
                        <div class="input-group-text">修改时间</div>
                        <input type="text" class="form-control" id="searchUpdateDateStart" name="updateStart"
                               value="<fmt:formatDate value="${categoryVo.updateStart}" type="both"/>" placeholder="时间">
                        <div class="input-group-text">-</div>
                        <input type="text" class="form-control" id="searchUpdateDateEnd" name="updateEnd"
                               value="<fmt:formatDate value="${categoryVo.updateEnd}" type="both"/>" placeholder="时间">
                    </div>
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary">查询</button>
                    <button type="button" class="btn btn-primary" onclick="searchClear()">清空</button>
                </div>
            </form>

            <div class="table-responsive" style="margin-top: 30px;">
                <table class="table table-hover table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">
                            <input type="checkbox">
                        </th>
                        <th scope="col">ID</th>
                        <th scope="col">排序</th>
                        <th scope="col">名称</th>
                        <th scope="col">状态</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">修改时间</th>
                        <th scope="col" style="width: 110px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${categoryPageInfo.list}" var="category" varStatus="item">
                        <tr>
                            <td>
                                <input type="checkbox" id="select${item.index}">
                            </td>
                            <td>${category.id}</td>
                            <td>${category.sort}</td>
                            <td>${category.name}</td>
                            <td>${category.status}</td>
                            <td><fmt:formatDate value="${category.createTime}" type="both"/></td>
                            <td><fmt:formatDate value="${category.updateTime}" type="both"/></td>
                            <td>
                                <div class="btn-group btn-group-sm" role="group"
                                     aria-label="Basic mixed styles example">
                                    <button type="button" class="btn btn-danger"
                                            onclick="categoryDelete('${category.id}')">删除
                                    </button>
                                    <button type="button" class="btn btn-success"
                                            onclick="showUpdateModel('${category.id}',
                                                    '${category.sort}', '${category.name}', '${category.status}')">修改
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="row">
                <div class="col align-self-start">
                    显示第${categoryPageInfo.pageNum}页记录，总共${categoryPageInfo.total}行${categoryPageInfo.pages}页记录。
                </div>
                <div class="col align-self-end">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <li class="page-item">
                                <a class="page-link" aria-label="Previous"
                                   href="href="${pageContext.request.contextPath}/api/category/page/${navigatepageNum}${searchParam}"
                                >
                                <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach items="${categoryPageInfo.navigatepageNums}" var="navigatepageNum">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/api/category/page/${navigatepageNum}${searchParam}">${navigatepageNum}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" aria-label="Next"
                                   href="${pageContext.request.contextPath}/api/category/page/${categoryPageInfo.nextPage}${searchParam}">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- editModel -->
<div class="modal fade" id="editModel" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="editModelTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModelTitle"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="row row-cols-lg-auto g-3 align-items-center" id="editModelForm">
                    <input hidden name="id" id="id">
                    <div class="col-12">
                        <label class="visually-hidden" for="name">名称</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="名称">
                    </div>

                    <div class="col-12">
                        <label class="visually-hidden" for="sort">排序</label>
                        <input type="text" class="form-control" name="sort" id="sort" value="0" placeholder="排序">
                    </div>
                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="status" id="status" checked>
                            <label class="form-check-label" for="status">状态</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="add()" id="editModelAddBtn">提交</button>
                <button type="button" class="btn btn-primary" onclick="update()" id="editModelUpdateBtn">提交</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/message.jsp"/>

<jsp:include page="../common/foot.jsp"/>

<script>
    var editModal = new bootstrap.Modal($('#editModel'));
    var messageModel = new bootstrap.Modal($('#messageModel'));

    // 新增类别-start
    function showAddModel() {
        $('#editModelTitle').text('新增类别');
        $('#editModelAddBtn').show();
        $('#editModelUpdateBtn').hide();
        editModal.show();
    }

    function add() {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/category/add",
            data: $('#editModelForm').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.error === 0) {
                    $(window).attr('location', data.to);
                } else {
                    setMessageModel(data.message);
                    messageModel.show();
                    location.reload();
                }
            }
        });
    }
    // 新增类别-end

    // 删除类别
    function categoryDelete(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/category/delete",
            data: {
                id: id
            },
            type: "POST",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.error === 0) {
                    $(window).attr('location', data.to);
                } else {
                    setMessageModel(data.message);
                    messageModel.show();
                    location.reload();
                }
            }
        });
    }

    // 修改类别
    function showUpdateModel(a, b, c, d) {
        $('#id').val(a);
        $('#sort').val(b);
        $('#name').val(c);
        if (d) {
            $("#status").prop("checked", true)
        }
        $('#editModelTitle').text('修改类别');
        $('#editModelAddBtn').hide();
        $('#editModelUpdateBtn').show();
        editModal.show();
    }

    function update() {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/category/update",
            data: $('#editModelForm').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.error === 0) {
                    $(window).attr('location', data.to);
                } else {
                    setMessageModel(data.message);
                    messageModel.show();
                    location.reload();
                }
            }
        });
    }

    // 查询清空
    function searchClear() {
        $('input').each(function () {
            $(this).val("");
        });
        $(window).attr('location', "/BookManager/view/category");
    }

    function setMessageModel(title, message) {
        $('#messageModelTitle').text(title);
        $('#messageModelBody').text(message);
    }

    $(function () {
        // 启动图标
        feather.replace({'aria-hidden': 'true'})
    });

</script>
</body>
</html>