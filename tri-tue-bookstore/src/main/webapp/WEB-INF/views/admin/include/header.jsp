<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
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
<link
	href="${ctxPath}/resources/static/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="${ctxPath}/resources/static/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="${ctxPath}/resources/static/vendors/nprogress/nprogress.css"
	rel="stylesheet">
<!-- bootstrap-progressbar -->
<link
	href="${ctxPath}/resources/static/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link
	href="${ctxPath}/resources/static/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
	<!-- Datatables -->
<link
	href="${ctxPath}/resources/static/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${ctxPath}/resources/static/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${ctxPath}/resources/static/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${ctxPath}/resources/static/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${ctxPath}/resources/static/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">
<!-- PNotify -->
    <link href="${ctxPath}/resources/static/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="${ctxPath}/resources/static/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="${ctxPath}/resources/static/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
   
<!-- Custom Theme Style -->
<link href="${ctxPath}/resources/static/build/css/custom.min.css"
	rel="stylesheet">
	<style type="text/css">
		.btn-default.active{
			background-color: #337ab7;
			color: white;
		}
	</style>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="${ctxPath}" class="site_title"><i class="fa fa-paw"></i>
							<span>Trí Tuệ Việt!</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="/data/${avartar}" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Xin chào,</span>
							<h2>${fullName}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />