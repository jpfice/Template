<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>UPload</title>
</head>
<body>
	<h2>Hello UPload!</h2>
	<h2>JDK 11.0.6</h2>
	<h2>apache-tomcat-8.5.61</h2>
	<a href="">AAA</a>
	<form action="uplodeFile" method="post" enctype="multipart/form-data">
		文件:<input type="file" name="file" /> <input name="uplodetime"
			type="hidden" id="nowDate" /> <input type="submit" value="上传" />
	</form>
</body>
</html>
