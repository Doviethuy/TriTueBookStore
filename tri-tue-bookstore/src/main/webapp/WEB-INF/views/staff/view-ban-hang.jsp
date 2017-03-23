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
						<h2>Hóa đơn</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">						
						<table id="datatable2" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Mã sản phẩm</th>
									<th>Danh mục</th>
									<th>Số lượng</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
					<button id="btn">Modal hóa đơn</button>
				</div>
		</div>
		<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Chọn sản phẩm</h2>
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
									<tr>
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
<script src="${ctxPath}/resources/static/vendors/jquery/dist/jquery.min.js"></script>
<%@ include file="include/add-invoice-modal.jsp"%>
<script type="text/javascript" >
	$('body').on('click','#datatable tbody tr',function(){
		$('#datatable2 tbody').append($(this).clone());
		this.remove();
	});			
	$('body').on('click','#datatable2 tbody tr',function(){
		$('#datatable tbody').append($(this).clone());
		this.remove();
	});
	$('#btn').click(function(){
		var total=0,stt=0;
		$("#total").empty();		
		$("#datatable3 tbody").empty();
		$('#datatable2 tbody tr').each(function(){
			stt+=1;
			var row = $(this).clone().append('<td>'+1+'</td>').prepend('<td>'+stt+'</td>');
			$('#datatable3 tbody').append(row);
		});
		
		$('#datatable3 tbody tr').each(function(){
			$(this).find('td:eq(6)').attr("contentEditable","true");
			var qty = parseInt($(this).find('td:eq(6)').text());
			var pr = parseFloat($(this).find('td:eq(5)').text());
			total+=qty*pr;
			$(this).append('<td>'+qty*pr+'</td>');
		});
		$('#datatable3 tr td:nth-child(3)').hide();
		$('#datatable3 tr th:nth-child(3)').hide();
		$('#datatable3 tr td:nth-child(4)').hide();
		$('#datatable3 tr th:nth-child(4)').hide();
		$('#datatable3 tr td:nth-child(5)').hide();
		$('#datatable3 tr th:nth-child(5)').hide();
		$('#total').val(total);
		$("#addInvoice").modal("show");
	});
</script>
<%@ include file="include/footer.jsp"%>