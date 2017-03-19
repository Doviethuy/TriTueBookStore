<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-user-form">Thêm nhân viên</button>

<div class="modal fade bs-example-modal-lg" id="add-user-form" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form method="POST" action="${ctxPath}/admin/add-staff" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" enctype="multipart/form-data">
				<input type="hidden" name="redirect" value="/admin/nhan-vien"/>
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
							<input type="password" id="password" name="password" required="required" class="form-control col-md-7 col-xs-12">
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
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Giới tính</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
                          <div id="gender" class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                              <input type="radio" name="gender" value="0">Nam</label>
                            <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                              <input type="radio" name="gender" value="1">Nữ</label>
                          </div>
                        </div>
					</div>
					<div class="form-group">
						<label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Địa chỉ</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="address" class="form-control col-md-7 col-xs-12" name="address"></textarea>
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
							<input id="img" name="files" class="form-control col-md-7 col-xs-12" type="file">
						</div>
					</div>
					<div class="form-group">
						<label for="middle-name" class="control-label col-md-3 col-sm-3 col-xs-12">Mô tả</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="description" class="form-control col-md-7 col-xs-12" name="description"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Vai trò</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
                          <div id="role" class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                              <input type="radio" name="role" value="0">Nhân viên</label>
                            <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                              <input type="radio" name="role" value="1">Quản lý</label>
                          </div>
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