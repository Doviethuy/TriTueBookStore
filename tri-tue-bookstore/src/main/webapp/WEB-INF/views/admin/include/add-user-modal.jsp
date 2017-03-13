<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Thêm nhân viên</button>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form method="POST" action="${ctxPath}/admin/add-product" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" enctype="multipart/form-data">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Thêm nhân viên</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Tên tài khoản <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="userName" name="userName" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Mật khẩu <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="passWord" name="passWord" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Tên nhân viên <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="name" name="name" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Ngày sinh <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="dob" name="dob" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Giới tính <span class="required">*</span>
						</label>
						<div class="col-md-1 col-sm-1 col-xs-2">
							<input type="radio" id="nam" name="gender" checked="checked" value="0" class="form-control">
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4">
							<label class="form-control">Nam</label>							
						</div>
						<div class="col-md-1 col-sm-1 col-xs-2">
							<input type="radio" id="nu" name="gender" value="1" class="form-control">
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4">
							<label class="form-control">Nữ</label>
						</div>
					</div>
					<div class="form-group">
						<label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Địa chỉ</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="address" required="required" class="form-control parsley-error" name="address" data-parsley-trigger="keyup" data-parsley-minlength="10" data-parsley-maxlength="100" data-parsley-error-message="Mô tả phải lớn hơn 10 và ít hơn 100 ký tự" data-parsley-validation-threshold="10" data-parsley-id="40"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Số điện thoại <span class="required">*</span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="phone" name="phone" required="required" class="form-control col-md-7 col-xs-12">
						</div>
					</div>
					<div class="form-group">
						<label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Ảnh</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input id="img" name="img" class="form-control col-md-7 col-xs-12" type="file" name="middle-name">
						</div>
					</div>
					<div class="form-group">
						<label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Mô tả</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="description" required="required" class="form-control parsley-error" name="description" data-parsley-trigger="keyup" data-parsley-minlength="10" data-parsley-maxlength="100" data-parsley-error-message="Mô tả phải lớn hơn 10 và ít hơn 100 ký tự" data-parsley-validation-threshold="10" data-parsley-id="40"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Vai trò <span class="required">*</span>
						</label>
						<div class="col-md-1 col-sm-1 col-xs-2">
							<input type="radio" id="nhanVien" name="role" checked="checked" value="0" class="form-control">
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4">
							<label class="form-control">Nhân viên</label>							
						</div>
						<div class="col-md-1 col-sm-1 col-xs-2">
							<input type="radio" id="quanLy" name="role" value="1" class="form-control">
						</div>
						<div class="col-md-2 col-sm-2 col-xs-4">
							<label class="form-control">Quản lý</label>
						</div>
					</div>				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Lưu lại</button>
				</div>
		</div>
		</form>
	</div>
</div>