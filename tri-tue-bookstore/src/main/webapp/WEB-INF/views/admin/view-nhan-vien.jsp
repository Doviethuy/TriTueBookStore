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
						Quản lý <small>Nhân viên</small>
					</h2>					
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<%@ include file="include/add-user-modal.jsp"%>
					<table id="datatable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Họ và tên</th>
								<th>Tên tài khoản</th>
								<th>Điện thoại</th>
								<th>Ngày sinh</th>
								<th>Ngày tạo</th>
								<th>Vai trò</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${lstUser}">
								<tr>
									<td>${item.name}</td>
									<td>${item.userName}</td>
									<td>${item.phone}</td>
									<td>${item.dob}</td>
									<td>${item.createDate}</td>
									<td>${item.role}</td>
									<td>
										<div class="btn-action-table">
											<label onclick="showEditUserModal('${item.userName}')"><i
												class="fa fa-edit"></i></label>
										</div>
										<div class="btn-action-table">
											<label onclick="deleteUser('${item.userName}')"><i
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
<%@ include file="include/edit-user-modal.jsp"%>
<script type="text/javascript">
	function showEditUserModal(userName) {
		$.ajax({
			url : "${ctxPath}/admin/find-user",
			method : "POST",
			data : {
				id : userName,
			},
			success : function(user) {
				$("#editUserForm #userNameHidden").val(user.userName);
				$("#editUserForm #userName").val(user.userName);
				$("#editUserForm #password").val(user.password);
				$("#editUserForm #name").val(user.name);
				$("#editUserForm #address").val(user.address);
				$("#editUserForm #phone").val(user.phone);
				$("#editUserForm #description").val(user.description);
				var dob = new Date(user.dob);
				$("#editUserForm #dob").val(
						dob.getDate() + '/' + (dob.getMonth() + 1) + '/'
								+ dob.getFullYear());
				if (user.gender == 0) {
					$("#editUserForm #rdoMale").attr("checked", "checked");
				} else {
					$("#editUserForm #rdoFemale").attr("checked", "checked");
				}
				if (user.role == 0) {
					$("#editUserForm #rdoStaff").attr("checked", "checked");
				} else {
					$("#editUserForm #rdoAdmin").attr("checked", "checked");
				}
				$("#editUser").modal("show");
			}
		});
	}

	function deleteUser(id) {
		var r = confirm("Bạn có chắc chắn muốn xoá User: " + id);
		if (r == true) {
			$.ajax({
				url : "${ctxPath}/admin/delete-staff",
				method : "POST",
				data : {
					userName : id
				},
				success : function() {
					window.location.reload();
				}
			});
		}
	}
</script>
<%@ include file="include/footer.jsp"%>