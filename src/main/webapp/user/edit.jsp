<%@ page language="java" import="com.jkmvc.http.Request,com.jkmvc.example.model.UserModel" pageEncoding="UTF-8"%>
<% Request req = (Request) request; %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>todolist</title>
  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
  <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">编辑用户</div>
    <div class="panel-body">
      <p>...</p>
    </div>

    <!-- Form -->
    <% UserModel user = (UserModel) req.getAttribute("user"); %>
    <form action="<%= req.absoluteUrl("user/edit/" + user.getId()) %>" method="post">
      <div class="form-group">
        <label for="name">name</label>
        <input type="text" class="form-control" id="name" placeholder="name" name="name" value="<%= user.getName() %>">
      </div>
      <div class="form-group">
        <label for="age">age</label>
        <input type="text" class="form-control" id="age" placeholder="age" name="age" value="<%= user.getAge() %>">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>

  </div>
  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>