<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade bs-example-modal-lg" id="addInvoice" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form method="POST" action="${ctxPath}/staff/add-invoice" id="frm" data-parsley-validate class="form-horizontal form-label-left" enctype="multipart/form-data">
				<input type="hidden" name="redirect" value="/staff/ban-hang"/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Thêm hóa đơn</h4>
				</div>
				<div class="modal-body">
					<table id="datatable3" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên sản phẩm</th>
								<th>Mã sản phẩm</th>
								<th>Danh mục</th>
								<th>Tổng</th>
								<th>Đơn giá</th>
								<th>Số lượng</th>
								<th>Thành tiền</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td onclick="calClick()" style="background-color: buttonface">CỘNG TIỀN HÀNG</td>
								<td><input type="text" readonly name="total" id="total" style="color: red;border:none"/></td>
							</tr>
						</tfoot>
						<tbody>
							
						</tbody>
					</table>					
				</div>
				<div class="modal-footer">					
					<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					<button type="submit" class="btn btn-primary">Lưu lại</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="${ctxPath}/resources/static/vendors/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
	function calClick(){
		var total = 0;
		$("#total").empty();	
		$('#datatable3 tbody tr').each(function(){
			$(this).find('td:last').remove();
			$(this).find('td:eq(6)').attr("contentEditable","true");
			var qty = parseInt($(this).find('td:eq(6)').text());
			var pr = parseFloat($(this).find('td:eq(5)').text());
			total+=qty*pr;
			$(this).append('<td>'+qty*pr+'</td>');
		});
		$('#total').val(total);
	}
$('#frm').submit(function(e){
    e.preventDefault();
    calClick();
    var proId = [];
    var proQty = [];
    $('#datatable3 tbody tr').each(function(){
    	var id = $(this).find('td:eq(2)').text();
    	proId.push(id);
    	$('#frm').append(
    		$('<input>', {
    		    type: 'hidden',
    		    name: 'id'+proId.length,
    		    val: id
    		})
    	);
    	var qty = $(this).find('td:eq(6)').text();
    	proQty.push(qty);
    	$('#frm').append(
    		$('<input>', {
    		    type: 'hidden',
    		    name: 'qty'+proQty.length,
    		    val: qty
    		})
    	);
	});
    $('#frm').append(
    	$('<input>', {
    	    type: 'hidden',
    	    name: 'arrLength',
    	    val: proQty.length
    	})
    );
    $('#frm').submit();
});
</script>