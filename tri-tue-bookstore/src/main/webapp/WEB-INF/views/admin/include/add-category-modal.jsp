<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-category-form">Thêm danh mục</button>

<div class="modal fade bs-example-modal-lg" id="add-category-form" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form method="POST" action="${ctxPath}/admin/add-category" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" enctype="multipart/form-data">
				<input type="hidden" name="redirect" value="/admin/report/danh-muc"/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Thêm danh mục</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Tên danh mục <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="cateName" name="cateName" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>			
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Lưu lại</button>
				</div>
			</form>
		</div>
	</div>
</div>