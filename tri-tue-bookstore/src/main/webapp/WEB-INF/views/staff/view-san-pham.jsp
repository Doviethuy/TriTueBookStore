<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>
<div class="right_col" role="main">
	<div class="row">
		<div class="col-md-6 col-sm-6 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<i class="fa fa-square-o"></i> Modals
					</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"><i
								class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Settings 1</a></li>
								<li><a href="#">Settings 2</a></li>
							</ul></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<!-- modals -->
					<!-- Large modal -->
										
				</div>
			</div>
		</div>
	</div>
	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							Default Example <small>Users</small>
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false"><i
									class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Settings 1</a></li>
									<li><a href="#">Settings 2</a></li>
								</ul></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<%@ include file="include/add-product-modal.jsp" %>
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Danh mục</th>
									<th>Giá</th>
									<th>Số lượng</th>
									<th>Ngày tạo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${lstPro}">	
										<tr>
											<td>${item.proName}</td>
											<td>${item.cateId}</td>
											<td>${item.price}</td>
											<td>${item.quantity}</td>
											<td>${item.createDate}</td>
										</tr>
								</c:forEach>								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
<%@ include file="include/footer.jsp"%>