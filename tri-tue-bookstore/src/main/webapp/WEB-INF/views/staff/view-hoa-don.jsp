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
						<h2>Danh sách đơn hàng</h2><div class="clearfix"></div>
					</div>
					<div class="x_content">							
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Mã hóa đơn</th>
									<th>Tổng tiền</th>
									<th>Ngày tạo</th>
									<th>Ngày sửa</th>
									<th>Nhân viên</th>									
									<th>Chi tiết</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${lstInv}">
									<tr>
										<td>${item.ivId}</td>
										<td>${item.amount}</td>
										<td>${item.createDate}</td>
										<td>${item.modifyDate}</td>
										<td>${item.username}</td>
										<td style="background-color: orange;text-align: center">
											<a href="${ctxPath}/staff/dat-hang/${item.ivId}">ĐẶT HÀNG</a>
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
<%@ include file="include/footer.jsp"%>