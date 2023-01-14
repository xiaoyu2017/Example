<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2023/1/14
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--设置el有效--%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>Ebook Info</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/Buttons/2.0.0/css/buttons.min.css" rel="stylesheet">
    <script src="https://unpkg.com/feather-icons"></script>
    <style>
        p {
            margin: 0;
        }
    </style>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/static/img/image.jpg')">
<div style="border-radius:5px;position: absolute;transform: translate(-50%, -50%);top: 25%;left: 50%;background-color: white;height: 300px;width: 1000px;">
    <div style="width: 100%;background-color: #337ab7;height: 40px;border-radius:5px 5px 0 0;">
        <p style="padding: 0 10px;line-height: 40px;font-size: 20px;color: white;">资源信息</p>
    </div>
    <div style="width:100%;height: 260px;">
        <div style="width:800px;height: 260px;float: left;">
            <div style="width: 100%;height: 200px;">
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    文件名称 ：${ebookVo.bookName}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    文件作者 ：${ebookVo.author}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    出版机构 ：${ebookVo.publisher}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    出版年份 ：${ebookVo.year}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    文件大小 ：${ebookVo.size}${ebookVo.sizeUnit}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    文件类型 ：${ebookVo.extension}</p>
                <p style="margin-left: 10px;padding-top: 10px;font-size: 15px; font-family: Times New Roman,Arial,STKaiti,KaiTi,sans-serif,Segoe UI,Microsoft Yahei!important;">
                    文件语言 ：${ebookVo.language}</p>
            </div>
            <div style="width: 100%;height: 60px;line-height: 60px;">
                <a href="/" class="button button-royal button-rounded" style="margin-left: 10px;">
                    <i data-feather="download-cloud"></i>下载
                </a>
                <a href="/" class="button button-primary button-rounded" style="margin-left: 10px;"><i
                        data-feather="download-cloud"></i>下载
                </a>
                <a href="/" class="button button-caution button-rounded" style="margin-left: 10px;"><i
                        data-feather="download-cloud"></i>下载
                </a>
                <a href="/" class="button button-highlight button-rounded" style="margin-left: 10px;"><i
                        data-feather="download-cloud"></i>下载
                </a>
            </div>
        </div>
        <div style="width:200px;height: 260px;float: left;">
            <img src="${pageContext.request.contextPath}/ebook/api/img/${ebookVo.imageId}"
                 style="width: 160px;height: 240px;margin: 10px 20px;"/>
        </div>
    </div>
</div>

<script src="lib/jquery/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        // 启动图标
        feather.replace({'aria-hidden': 'true'})
    });
</script>
</body>
</html>