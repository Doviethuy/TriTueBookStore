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
						<h2>Thông tin hàng hóa</h2><div class="clearfix"></div>
					</div>
					<div class="x_content">	
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Mã sản phẩm</th>
									<th>Tên sản phẩm</th>									
									<th>Danh mục</th>
									<th>Giá</th>
									<th>Số lượng</th>	
									<th>Mô tả</th>
									<th>Ảnh</th>								
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${lstPro}">	
									<tr>
										<td>${product.proId}</td>
										<td>${product.proName}</td>										
										<c:forEach var="category" items="${lstCate}">
											<c:if test="${product.cateId==category.cateId}">
												<td>${category.cateName}</td>
											</c:if>
										</c:forEach>	
										<td>${product.price}</td>									
										<td>${product.quantity}</td>	
										<td>${product.description}</td>
										<td><img src="/data/${avartar}" alt=""></td>										
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