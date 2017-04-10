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
							Thông tin cá nhân
						</h2>						
						<div class="clearfix"></div>
					</div>
					<table>
						<tbody>
							<tr>
								<td>Tài khoản : </td>
								<td>${user.getUserName()}</td>
							</tr>
							<tr>
								<td>Mật khẩu : </td>
								<td><input type="password" readonly style="border:none;background-color: transparent" value="${user.getPassword()}"/></td>
							</tr>
							<tr>
								<td>Họ và tên : </td>
								<td>${user.getName()}</td>
							</tr>
							<tr>
								<td>Ngày sinh : </td>
								<td>${user.getDob()}</td>
							</tr>
							<tr>
								<td>Giới tính : </td>
								<td><c:if test="${user.getGender()==0}">Nam</c:if><c:if test="${user.getGender()==1}">Nữ</c:if></td>
							</tr>
							<tr>
								<td>Địa chỉ : </td>
								<td>${user.getAddress()}</td>
							</tr>
							<tr>
								<td>Điện thoại : </td>
								<td>${user.getPhone()}</td>
							</tr>
							<tr>
								<td>Mô tả : </td>
								<td>${user.getDescription()}</td>
							</tr>
							<tr>
								<td>Ngày tạo : </td>
								<td>${user.getCreateDate()}</td>
							</tr>
							<tr>
								<td>Ngày sửa : </td>
								<td>${user.getModifyDate()}</td>
							</tr>
							<tr>
								<td>Chức vụ : </td>
								<td><c:if test="${user.getRole()==0}">Nhân viên</c:if><c:if test="${user.getRole()==1}">Admin</c:if></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</div>
<%@ include file="include/footer.jsp"%>