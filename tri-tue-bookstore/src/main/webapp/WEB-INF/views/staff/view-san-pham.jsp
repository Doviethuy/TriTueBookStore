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
									<th>Mô tả</th>
									<th>Ngày tạo</th>
									<th>Chức năng</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${lstPro}">	
										<tr>
											<td>${item.proName}</td>
											<c:forEach var="category" items="${lstCate}">
												<c:if test="${item.cateId==category.cateId}">
													<td>${category.cateName}</td>
												</c:if>
											</c:forEach>											
											<td>${item.price}</td>
											<td>${item.quantity}</td>
											<td>${item.description}</td>
											<td>${item.createDate}</td>
											<td>
												<div class="btn-action-table">
													<label onclick="showEditProductModal('${item.proId}')"><i
														class="fa fa-edit"></i></label>
												</div>
												<div class="btn-action-table">
												<label onclick="deleteProduct('${item.proId}')"><i
													class="fa fa-remove"></i></label>
												</div>
											</td>
										</tr>
								</c:forEach>								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
<%@ include file="include/edit-product-modal.jsp"%>
<script type="text/javascript">
	function showEditProductModal(proId) {	
		
		$.ajax({
			url : "${ctxPath}/staff/find-product",
			method : "POST",
			data : {
				id : proId,
			},
			success : function(product) {
				
				$("#editProductForm #proIdHidden").val(product.proId);
				$("#editProductForm #proId").val(product.proId);
				$("#editProductForm #proName").val(product.proName);
				$("#editProductForm #cateId").val(product.cateId);
				$("#editProductForm #price").val(product.price);
				$("#editProductForm #quantity").val(product.quantity);
				$("#editProductForm #description").val(product.description);				
				$("#editProduct").modal("show");
			}
		});
	}

	function deleteProduct(id) {
		var r = confirm("Bạn có chắc chắn muốn xoá Product: " + id);
		if (r == true) {
			$.ajax({
				url : "${ctxPath}/staff/delete-product",
				method : "POST",
				data : {
					proId : id
				},
				success : function() {
					window.location.reload();
				}
			});
		}
	}
</script>

<%@ include file="include/footer.jsp"%>