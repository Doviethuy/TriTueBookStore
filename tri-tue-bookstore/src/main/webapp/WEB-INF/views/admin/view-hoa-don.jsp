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
						<h2>Chi tiết hóa đơn</h2><div class="clearfix"></div>
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
									<td>
										<div class="btn-action-table">
											<label onclick="showInvoiceDetailModal('${item.ivId}')"><i
												class="fa fa-edit"></i></label>
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
<%@ include file="include/invoice-detail-modal.jsp"%>
<script type="text/javascript">
	function showInvoiceDetailModal(ivId) {
		$.ajax({
			url : "${ctxPath}/admin/find-invoice",
			method : "POST",
			data : {
				id : ivId,
			},
			success : function(lst) {
				$("#tblDetail tbody").empty();
				$.each(lst, function(i) {
					var cDate = new Date(lst[i].createDate);
					var strDate = cDate.getDate()+'/'+(cDate.getMonth()+1)+'/'+cDate.getFullYear();
			        $('#tblDetail tbody').append('<tr><td>'+lst[i].ivId+'</td><td>'+lst[i].proId+'</td><td>'+(lst[i].amount/lst[i].quantity)+'</td><td contenteditable="true">'+lst[i].quantity+'</td><td>'+strDate+'</td></tr>');
			    });
				$("#invoiceDetail").modal("show");
			}
		});
	}
	
</script>
<%@ include file="include/footer.jsp"%>
<%try{%>
<c:if test="<%=session.getAttribute(Constant.EDIT_INVOICE_SUCCESS).toString().equals(Constant.EDIT_INVOICE_SUCCESS) %>">
	<script>
		show_notify();
		
		function show_notify(){
			new PNotify({
	               title: 'Thành công',
	               text: 'Sửa hoá đơn thành công!',
	               type: 'success',
	               styling: 'bootstrap3'
	           });
		}
	</script>
	<%session.removeAttribute(Constant.EDIT_INVOICE_SUCCESS); %>
</c:if>
<%}catch(Exception e){}%>