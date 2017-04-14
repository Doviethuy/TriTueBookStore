<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>
<style type="text/css">
	#datatable-2 th{
		vertical-align: middle;
	}
</style>
<div class="right_col" role="main">
	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Thông tin hàng hóa</h2><div class="clearfix"></div>
					</div>
					<div class="x_content">	
						<table id="datatable-2" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="width: 3%;">Mã sản phẩm</th>
									<th style="width: 15%;">Tên sản phẩm</th>									
									<th style="width: 15%;">Danh mục</th>
									<th style="width: 5%;">Giá</th>
									<th style="width: 3%;">Số lượng</th>	
									<th style="width: 54%;">Mô tả</th>
									<th style="width: 5%;">Ảnh</th>								
								</tr>
							</thead>							
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
<%@ include file="include/footer.jsp"%>
<script type="text/javascript">
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

$(document).ready(function() {
	 
	$("#datatable-2").DataTable( {
		"language": {
		    "lengthMenu": "Số kết quả trên trang _MENU_",
		    "zeroRecords": "Không có kết quả nào",
		    "emptyTable": "Không có dữ liệu để hiển thị",
		    "processing": "Đang xử lý...",
		    "info": "Đang hiển thị trang _PAGE_ của _PAGES_",
		    "infoEmpty": "Không có dữ liệu để hiển thị",
		    "infoFiltered": "(Đã lọc từ _MAX_ kết quả)",
		    "loadingRecords": "Đang tải..."			    	      	    
		},
		"searching": false,
        "bProcessing": true,
        "bServerSide": true,
        "bStateSave": false,
        "iDisplayLength": 10,
        "iDisplayStart": 0,
        "fnDrawCallback": function () {
        	console.log("Current page number: "+this.fnPagingInfo().iPage);    
        },         
        "sAjaxSource": "${ctxPath}/staff/get-all-product",
        "aoColumns": [
            { "mData": "proId" },
            { "mData": "proName" },
            { "mData": "cateName" },
            { "mData": "price" },
            { "mData": "quantity" },
            { "mData": "description" },
            { "mData": "img", "aTargets": [6],
            	"render": function (data) {
                    return '<img style="max-width: 30px;" src="/data/' +data+ '" />';
                }	
            }, 
        ],
        
    } );
 
} );
</script>