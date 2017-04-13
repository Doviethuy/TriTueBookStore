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
							Quản lý <small>Sản phẩm</small>
						</h2>						
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
			url : "${ctxPath}/admin/find-product",
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
				url : "${ctxPath}/admin/delete-product",
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
<%try{%>
<c:if test="<%=session.getAttribute(Constant.ADD_PRODUCT_SUCCESS).toString().equals(Constant.ADD_PRODUCT_SUCCESS) %>">
	<script>
		show_notify();
		
		function show_notify(){
			new PNotify({
	               title: 'Thành công',
	               text: 'Thêm sản phẩm thành công!',
	               type: 'success',
	               styling: 'bootstrap3'
	           });
		}
	</script>
	<%session.removeAttribute(Constant.ADD_PRODUCT_SUCCESS); %>
</c:if>
<%}catch(Exception e){}%>
<%try{%>
<c:if test="<%=session.getAttribute(Constant.EDIT_PRODUCT_SUCCESS).toString().equals(Constant.EDIT_PRODUCT_SUCCESS) %>">
	<script>
		show_notify();
		
		function show_notify(){
			new PNotify({
	               title: 'Thành công',
	               text: 'Chỉnh sửa sản phẩm thành công!',
	               type: 'success',
	               styling: 'bootstrap3'
	           });
		}
	</script>
	<%session.removeAttribute(Constant.EDIT_PRODUCT_SUCCESS); %>
</c:if>
<%}catch(Exception e){}%>
<%try{%>
<c:if test="<%=session.getAttribute(Constant.DELETE_PRODUCT_SUCCESS).toString().equals(Constant.DELETE_PRODUCT_SUCCESS) %>">
	<script>
		show_notify();
		
		function show_notify(){
			new PNotify({
	               title: 'Thành công',
	               text: 'Xoá sản phẩm thành công!',
	               type: 'success',
	               styling: 'bootstrap3'
	           });
		}
	</script>
	<%session.removeAttribute(Constant.DELETE_PRODUCT_SUCCESS); %>
</c:if>
<%}catch(Exception e){}%>