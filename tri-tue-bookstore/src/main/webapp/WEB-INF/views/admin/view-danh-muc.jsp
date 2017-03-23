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
							Quản lý <small>Danh mục</small>
						</h2>						
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<%@ include file="include/add-category-modal.jsp" %>
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Mã danh mục</th>
									<th>Tên danh mục</th>
									<th>Chức năng</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${lstCate}">	
										<tr>
											<td>${item.cateId}</td>										
											<td>${item.cateName}</td>
											<td>
												<div class="btn-action-table">
													<label onclick="showEditCategoryModal('${item.cateId}')"><i
														class="fa fa-edit"></i></label>
												</div>
												<div class="btn-action-table">
												<label onclick="deleteCategory('${item.cateId}')"><i
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
<%@ include file="include/edit-category-modal.jsp"%>
<script type="text/javascript">
	function showEditCategoryModal(cateId) {		
		$.ajax({
			url : "${ctxPath}/admin/find-category",
			method : "POST",
			data : {
				id : cateId,
			},
			success : function(category) {				
				$("#editCategoryForm #cateIdHidden").val(category.cateId);
				$("#editCategoryForm #cateId").val(category.cateId);
				$("#editCategoryForm #cateName").val(category.cateName);			
				$("#editCategory").modal("show");
			}
		});
	}

	function deleteCategory(id) {
		var r = confirm("Bạn có chắc chắn muốn xoá Category: " + id);
		if (r == true) {
			$.ajax({
				url : "${ctxPath}/admin/delete-category",
				method : "POST",
				data : {
					cateId : id
				},
				success : function() {
					window.location.reload();
				}
			});
		}
	}
</script>

<%@ include file="include/footer.jsp"%>