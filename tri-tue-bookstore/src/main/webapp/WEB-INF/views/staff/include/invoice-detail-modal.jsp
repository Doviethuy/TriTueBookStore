<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade bs-example-modal-lg" id="invoiceDetail" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form method="POST" action="${ctxPath}/staff/edit-invoice" id="invoiceDetailForm" data-parsley-validate class="form-horizontal form-label-left" enctype="multipart/form-data">
				<input type="hidden" name="redirect" value="/staff/hoa-don"/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Chi tiết hóa đơn</h4>
				</div>
				<div class="modal-body">
					<table id="tblDetail" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Mã hóa đơn</th>								
								<th>Mã sản phẩm</th>
								<th>Đơn giá</th>
								<th>Số lượng</th>
								<th>Ngày tạo</th>
							</tr>
						</thead>
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
$('#invoiceDetailForm').submit(function(e){
    e.preventDefault();
    var ivId = "";
    var proId = [];
    var proQty = [];
    $('#tblDetail tbody tr').each(function(){
    	ivId = $(this).find('td:eq(0)').text();
    	var id = $(this).find('td:eq(1)').text();
    	proId.push(id);
    	$('#invoiceDetailForm').append(
    		$('<input>', {
    		    type: 'hidden',
    		    name: 'id'+proId.length,
    		    val: id
    		})
    	);
    	var qty = $(this).find('td:eq(3)').text();
    	proQty.push(qty);
    	$('#invoiceDetailForm').append(
    		$('<input>', {
    		    type: 'hidden',
    		    name: 'qty'+proQty.length,
    		    val: qty
    		})
    	);
	});
    $('#invoiceDetailForm').append(
    	$('<input>', {
    	    type: 'hidden',
    	    name: 'arrLength',
    	    val: proQty.length
    	})
    );
    $('#invoiceDetailForm').append(
        $('<input>', {
        	type: 'hidden',
        	name: 'ivId',
        	val: ivId
       	})
    );
    $('#invoiceDetailForm').submit();
});
</script>