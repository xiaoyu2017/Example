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

    <%--标签输入框样式--%>
    <link href="${pageContext.request.contextPath}/static/css/tagsinput.css" rel="stylesheet">

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
                <h1 class="h2">图书管理</h1>
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
                    <label class="visually-hidden" for="ebookName">模糊名称</label>
                    <input type="text" class="form-control" id="ebookName" name="ebookName" value="${categoryVo.name}"
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
                        <th scope="col">书名</th>
                        <th scope="col">作者</th>
                        <th scope="col">出版社</th>
                        <th scope="col">第几版</th>
                        <th scope="col">发行年份</th>
                        <th scope="col">语言</th>
                        <th scope="col">页数</th>
                        <th scope="col">是否带书签</th>
                        <th scope="col">状态</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">修改时间</th>
                        <th scope="col" style="width: 110px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${ebooksPageInfo.list}" var="ebook" varStatus="item">
                        <tr>
                            <td>
                                <input type="checkbox" id="select${item.index}">
                            </td>
                            <td>${ebook.id}</td>
                            <td>${ebook.bookName}</td>
                            <td>${ebook.author.name}</td>
                            <td>${ebook.publisher.name}</td>
                            <td>${ebook.edition}</td>
                            <td>${ebook.year}</td>
                            <td>${ebook.language}</td>
                            <td>${ebook.pages}</td>
                            <td>${ebook.bookmark}</td>
                            <td>${ebook.status}</td>
                            <td><fmt:formatDate value="${ebook.createTime}" type="both"/></td>
                            <td><fmt:formatDate value="${ebook.updateTime}" type="both"/></td>
                            <td>
                                <div class="btn-group btn-group-sm" role="group"
                                     aria-label="Basic mixed styles example">
                                    <button type="button" class="btn btn-danger"
                                            onclick="deleteEbook('${ebook.id}')">删除
                                    </button>
                                    <button type="button" class="btn btn-success"
                                            onclick="updateEbook('${ebook.id}')">修改
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
                    显示第${ebooksPageInfo.pageNum}页记录，总共${ebooksPageInfo.total}行${ebooksPageInfo.pages}页记录。
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
                            <c:forEach items="${ebooksPageInfo.navigatepageNums}" var="navigatepageNum">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/api/category/page/${navigatepageNum}${searchParam}">${navigatepageNum}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" aria-label="Next"
                                   href="${pageContext.request.contextPath}/api/category/page/${ebooksPageInfo.nextPage}${searchParam}">
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
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <form id="editModelForm" method="post" enctype="multipart/form-data">
                <input type="text" id="eid" name="eid" hidden>
                <input type="text" id="fid" name="fid" hidden>
                <input type="text" id="iid" name="iid" hidden>
                <div class="modal-header">
                    <h5 class="modal-title" id="editModelTitle"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <input type="text" class="form-control" id="bookName" name="bookName" placeholder="书名"
                                   required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" id="edition" name="edition" placeholder="第几版"
                                   required>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" id="pages" name="pages" placeholder="页数" required>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 10px;">
                        <div class="col">
                            <label class="mr-sm-2 sr-only" for="year">发行年份</label>
                            <select class="custom-select mr-sm-2" id="year" name="year" required>
                                <option selected>请选择</option>
                                <c:forEach items="${years}" var="year">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label class="mr-sm-2 sr-only" for="language">语言</label>
                            <select class="custom-select mr-sm-2" id="language" name="language" required>
                                <option selected>请选择</option>
                                <c:forEach items="${languages}" var="language">
                                    <option value="${language}">${language}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <input class="form-check-input" type="checkbox" id="bookmark" name="bookmark">
                            <label class="form-check-label" for="bookmark">
                                是否带书签
                            </label>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="summary">简介</label>
                        <textarea class="form-control" id="summary" name="summary" rows="3" required></textarea>
                    </div>

                    <div class="tagsinput-primary form-group" style="margin-top: 10px;">
                        <label for="publisher">出版社</label>
                        <input id="publisher" name="publisher" class="tagsinput" data-role="tagsinput"
                               placeholder="输入后回车">
                    </div>

                    <div class="tagsinput-primary form-group" style="margin-top: 10px;">
                        <label for="author">作者</label>
                        <input id="author" name="author" class="tagsinput" data-role="tagsinput" placeholder="输入后回车">
                    </div>

                    <div class="tagsinput-primary form-group" style="margin-top: 10px;">
                        <label for="isbn">ISBN</label>
                        <input id="isbn" name="isbn" class="tagsinput" data-role="tagsinput" placeholder="输入后回车">
                    </div>

                    <div class="tagsinput-primary form-group" style="margin-top: 10px;">
                        <label for="categories">类别</label>
                        <input id="categories" name="categories" class="tagsinput" data-role="tagsinput"
                               placeholder="输入后回车">
                    </div>

                    <div class="form-group" style="margin: 10px 0;">
                        <label id="fileText" for="file">电子书文件</label>
                        <input type="file" id="file" name="file" class="form-control-file">
                    </div>

                    <div class="form-group" style="margin: 10px 0;">
                        <label id="imageText" for="image">电子书封面</label>
                        <input type="file" class="form-control-file" id="image" name="image">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" id="editModelAddBtn">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/message.jsp"/>

<jsp:include page="../common/foot.jsp"/>

<script src="${pageContext.request.contextPath}/static/js/tagsinput.js"></script>

<script>
    var editModal = new bootstrap.Modal($('#editModel'));
    var messageModel = new bootstrap.Modal($('#messageModel'));

    // 新增电子书
    function showAddModel() {
        $('#editModelTitle').text('新增图书');
        $('#editModelForm').prop("action", "${pageContext.request.contextPath}/api/ebook/add");
        editModal.show();
    }

    // 删除电子书
    function deleteEbook(eid) {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/ebook/delete",
            data: {
                eid: eid
            },
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.error === 0) {
                    $(window).attr('location', data.to);
                } else {
                    setMessageModel('删除电子书提示', data.message);
                    messageModel.show();
                    location.reload();
                }
            }
        });
    }

    // 删除电子书
    function updateEbook(eid) {
        // 获得电子书信息
        $.ajax({
            url: "${pageContext.request.contextPath}/api/ebook/get/" + eid,
            type: "GET",
            dataType: "json",
            success: function (data) {
                if (data.error === 0) {
                    // 赋值表单
                    const ebook = data.data;
                    if (ebook == null) {
                        return;
                    }
                    const fileAtt = ebook.file;
                    const imageAtt = ebook.image;
                    // 书名
                    $('#eid').val(ebook.id);
                    if (fileAtt != null) {
                        $('#fid').val(ebook.file.id);
                    }
                    if (imageAtt != null) {
                        $('#iid').val(ebook.image.id);
                    }
                    $('#bookName').val(ebook.bookName);
                    $('#edition').val(ebook.edition);
                    $('#year').val(ebook.year);
                    $('#language').val(ebook.language);
                    $('#pages').val(ebook.pages);
                    $('#bookmark').val(ebook.bookmark);
                    $('#summary').val(ebook.summary);
                    $('#publisher').tagsinput('add', ebook.publisher.name);
                    $('#author').tagsinput('add', ebook.author.name);
                    var isbnStr = '';
                    $.each(ebook.isbn, function (index, item) {
                        isbnStr += item.name + ",";
                    })
                    $('#isbn').tagsinput('add', isbnStr);

                    var categoryStr = '';
                    $.each(ebook.categories, function (index, item) {
                        categoryStr += item.name + ",";
                    })
                    $('#categories').tagsinput('add', categoryStr);

                    $('#editModelTitle').text('修改电子书');
                    $('#editModelForm').prop("action", "${pageContext.request.contextPath}/api/ebook/update");
                    editModal.show();
                } else {
                    setMessageModel('修改电子书提示', data.message);
                    messageModel.show();
                    location.reload();
                }
            }
        });
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