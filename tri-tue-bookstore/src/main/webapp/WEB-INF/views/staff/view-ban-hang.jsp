<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>
<div class="right_col" role="main">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							Hóa đơn
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
						
						<table  id="datatable2" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Mã sản phẩm</th>
									<th>Danh mục</th>
									<th>Số lượng</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>1</th>
									<th>2</th>
									<th>2</th>
									<th>2</th>
									<th>2</th>
								</tr>							
							</tbody>
						</table>
						<button>Modal hóa đơn</button>
					</div>
				</div>
		</div>
		<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							Chọn sản phẩm
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
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Mã sản phẩm</th>
									<th>Danh mục</th>
									<th>Số lượng</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${lstPro}">									
									<tr onclick="addProductToInvoice()">
										<td>${product.proName}</td>
										<td>${product.proId}</td>
										<c:forEach var="category" items="${lstCate}">
											<c:if test="${product.cateId==category.cateId}">
												<td>${category.cateName}</td>
											</c:if>
										</c:forEach>										
										<td>${product.quantity}</td>
										<td>${product.price}</td>
									</tr>
								</c:forEach>								
							</tbody>
						</table>
					</div>
				</div>
		</div>			
	</div>
</div>
<script type="text/javascript">
	function addProductToInvoice() {
		confirm("Phần này chưa code, đẩy sản phẩm lên hóa đơn xong click vào nút hiện ra Modal hóa đơn, sau đó thay đổi số lượng từng sản phẩm và tính tổng");
	}
</script>
<%@ include file="include/footer.jsp"%>