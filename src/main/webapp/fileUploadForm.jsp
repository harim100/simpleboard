<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>단일 파일 업로드</h3>
<form action="singleFileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select File</td>
            <td><input type="file" name="mediaFile"></td>
            <td>
                <button type="submit">Upload</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>