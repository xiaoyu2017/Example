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
    <title>Ebook</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/search-form.css">
</head>
<body>
<form id="searchForm" onsubmit="submitFn(this, event);">
    <div class="search-wrapper">
        <div class="input-holder">
            <input type="text" id="search" name="search" class="search-input" style="color: white;" placeholder="请输入书名、作者、出版社、ISBN..."/>
            <button class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
        </div>
        <span class="close" onclick="searchToggle(this, event);"></span>
        <div class="result-container">
            <div style="background: white;width: 100%;height: 500px;border-radius: 5px;">

                <div style="width: 100%;height: 30px;background-color: #f5f8fa; border-bottom: 1px solid #e8e8e8;border-radius:5px 5px 0 0 ;">
                    <a style="float: left;padding-left: 10px;line-height: 30px;">上一页</a>
                    <label style="line-height: 30px;">第1页</label>
                    <a style="float: right;padding-right: 10px;line-height: 30px;">下一页</a>
                </div>
                <div style="width: 100%;height: 470px;overflow-y: auto;">
                    <a style="text-decoration: none;color:#333;">
                        <div style="width: 580px;margin: 10px;height: 40px;border-radius: 3px; border: thin dashed #aaaaaa;">
                            <p style="color: #0000ee;font-size:20px;height: 20px;line-height: 20px;margin:0;padding: 0 5px;text-align: left">
                                asasasasas</p>
                            <p style="height: 20px;line-height: 20px;margin:0;padding: 0 5px;
                        white-space: wrap;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;
-webkit-box-orient: vertical;-webkit-line-clamp: 1;text-align: left">
                                asbhakbvhakbvhdkavhkhdabkdhbjkhbakjcbdhjwwwwwwwwwwwwwkabhcdkabchdkabchdkjbcahdjkbchdjbkachbckajhdb
                            </p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-3.6.1.min.js"></script>

<script type="text/javascript">
    function searchToggle(obj, evt) {
        var container = $(obj).closest('.search-wrapper');

        if (!container.hasClass('active')) {
            container.addClass('active');
            evt.preventDefault();
        } else if (container.hasClass('active') && $(obj).closest('.input-holder').length == 0) {
            container.removeClass('active');
            // clear input
            container.find('.search-input').val('');
            // clear and hide result container when we press close
            container.find('.result-container').fadeOut(100, function () {
                $(this).empty();
            });
        }
    }

    function submitFn(obj, evt) {
        /*value = $(obj).find('.search-input').val().trim();

        _html = "Yup yup! Your search text sounds like this: ";
        if (!value.length) {
            _html = "Yup yup! Add some text friend :D";
        } else {
            _html += "<b>" + value + "</b>";
        }

        $(obj).find('.result-container').html('<span>' + _html + '</span>');
        */
        $.ajax({
            url: "${pageContext.request.contextPath}/ebook/api/search",
            data: $('#searchForm').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                console.log(data)
            }
        });
        $(obj).find('.result-container').fadeIn(100);
        evt.preventDefault();
    }

</script>
</body>
</html>