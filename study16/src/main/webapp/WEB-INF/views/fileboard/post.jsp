<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path2" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>새 글 작성</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #drop-area {
            border: 2px dashed #ccc;
            border-radius: 20px;
            width: 300px;
            height: 200px;
            margin: 20px auto;
            text-align: center;
            font-size: 24px;
            line-height: 200px;
        }
        .highlight {
            border-color: #000;
        }
    </style>
</head>
<body>
<h2>새 글 작성</h2>
<form id="postForm" enctype="multipart/form-data">
    <p>제목: <input type="text" name="title"></p>
    <p>내용: <textarea name="content"></textarea></p>
    <p>파일:</p>
    <div id="drop-area">Drag & Drop files here</div>
    <input type="file" id="file" name="file" style="display: none;">
    <input type="submit" value="작성">
</form>

<script>
    $(document).ready(function () {
        var dropArea = $("#drop-area");

        // 드래그 앤 드롭 이벤트 처리
        dropArea.on("dragenter", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.addClass("highlight");
        });

        dropArea.on("dragleave", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");
        });

        dropArea.on("dragover", function (e) {
            e.preventDefault();
            e.stopPropagation();
        });

        dropArea.on("drop", function (e) {
            e.preventDefault();
            e.stopPropagation();
            dropArea.removeClass("highlight");

            var files = e.originalEvent.dataTransfer.files;
            if (files.length > 0) {
                $("#file")[0].files = files;
                handleFiles(files);
            }
        });

        // 파일 선택 시 파일 이름 표시
        $("#file").on("change", function () {
            var files = this.files;
            handleFiles(files);
        });

        // 파일 처리
        function handleFiles(files) {
            var fileList = $("<ul></ul>");
            $.each(files, function (index, file) {
                var listItem = $("<li></li>").text(file.name);
                fileList.append(listItem);
            });
            dropArea.html(fileList);
        }

        // 폼 제출 처리
        $("#postForm").on("submit", function (e) {
            e.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url: "${path2}/fileboard/postPro",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    window.location.href = "${path2}/fileboard/list";
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });
</script>
</body>
</html>