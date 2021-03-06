<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../init.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Nhà sách Trí Tuệ</title>

    <!-- Bootstrap -->
    <link href="${ctxPath}/resources/static/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${ctxPath}/resources/static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${ctxPath}/resources/static/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${ctxPath}/resources/static/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${ctxPath}/resources/static/build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form method="POST" action="${ctxPath}/changePass" id="changePass">
              <h1>Đổi mật khẩu</h1>
              <div>
                <input type="text" name="userName" class="form-control" placeholder="Tên tài khoản" required />
              </div>
              <div>
                <input type="password" name="passOld" class="form-control" placeholder="Mật khẩu cũ" required />
              </div>
              <div>
                <input type="password" name="passNew" class="form-control" placeholder="Mật khẩu mới" required />
              </div>
              <div>
                <input class="btn btn-default submit" type="submit" value="Đổi mật khẩu">
                <a class="reset_pass" href="${ctxPath}/">Về trang đăng nhập</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> Nhà sách Trí Tuệ!</h1>
                  <p>©2017 All Rights Reserved. Trí Tuệ Books Co., Ltd.</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
