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
						<h2>Đơn hàng</h2><div class="clearfix"></div>
					</div>
					<div class="x_content">	
						<div>
							<div class="col-sm-5">
								<b>Nhân viên bán hàng :</b> ${user.name}<br>
								<b>Điện thoại :</b> ${user.phone}
							</div>
							<div class="col-sm-5">
								<b>Mã hóa đơn :</b> ${invoice.ivId}<br>
								<b>Ngày tạo :</b> ${invoice.createDate}
							</div>
						</div><br><br><br>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Số lượng</th>
									<th>Đơn giá</th>
									<th>Tên sản phẩm</th>
									<th>Mô tả</th>
									<th>Tạm tính</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${lstDetail}">
									<tr>
										<td>${item.quantity}</td>
										<c:forEach var="product" items="${lstPro}">
											<c:if test="${product.proId==item.proId}">
												<td>${product.price}</td>
												<td>${product.proName}</td>
												<td>${product.description}</td>
											</c:if>
										</c:forEach>
										<td>${item.amount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div>
							<div class="col-sm-6">
								<h3><b>Phương thức thanh toán :</b></h3>
								<input type="radio" name="payMethod" value="0" checked>Sau khi nhận hàng<br>
								<input type="radio" name="payMethod" value="1">VISA<br>
								<input type="radio" name="payMethod" value="2">MASTERCARD<br>
								<input type="radio" name="payMethod" value="3">PAYPAL
							</div>
							<div class="col-sm-6">
								<h3><b>Thành tiền :</b></h3>
								<table class="table">
									<tbody>
										<tr>
											<th>Tạm tính :</th>
											<td>${invoice.amount} đ</td>
										</tr>
										<tr>
											<th>Thuế(5%) :</th>
											<td>${invoice.amount*0.05} đ</td>
										</tr>
										<tr>
											<th>Phí vận chuyển :</th>
											<td>20000 đ</td>
										</tr>
										<tr>
											<th>Tổng :</th>
											<td>${invoice.amount*1.05+20000} đ</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-sm-8"><a href="${ctxPath}/staff/hoa-don" style="text-decoration: underline">Quay lại danh sách đơn hàng</a></div>
						<div class="pull-right">
							<button class="btn btn-default">In ra</button>
							<button class="btn btn-primary">ĐẶT HÀNG</button>
						</div>						
					</div>
				</div>
			</div>
		</div>
</div>
<%@ include file="include/footer.jsp"%>