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
						<%@ include file="include/add-user-modal.jsp" %>
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
												<label onclick="showEditUserModal('${item.userName}')"><i class="fa fa-edit"></i></label>
											</div>
											<div class="btn-action-table">
												<label onclick="deleteUser('${item.userName}')"><i class="fa fa-remove"></i></label>
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
	function showEditUserModal(userName){
		$.ajax({
			url: "${ctxPath}/admin/find-user",
			method: "POST",
			data:{
				id: userName,
			},
			success: function(user){
				$("#editUserForm #userNameHidden").val(user.userName);
				$("#editUserForm #userName").val(user.userName);
				$("#editUserForm #password").val(user.password);
				$("#editUserForm #name").val(user.name);
				$("#editUserForm #address").val(user.address);
				$("#editUserForm #phone").val(user.phone);
				$("#editUserForm #description").val(user.description);
				var dob = new Date(user.dob);
				$("#editUserForm #dob").val(dob.getDate() + '/' + (dob.getMonth() + 1) + '/' +  dob.getFullYear());
				if(user.gender == 0){
					$("#editUserForm #rdoMale").attr("checked", "checked");
				} else {
					$("#editUserForm #rdoFemale").attr("checked", "checked");
				}
				if(user.role == 0){
					$("#editUserForm #rdoStaff").attr("checked", "checked");
				} else {
					$("#editUserForm #rdoAdmin").attr("checked", "checked");
				}
				$("#editUser").modal("show");
			}
		});
	}
	
	function deleteUser(id){
		
	}
</script>
<%@ include file="include/footer.jsp"%>