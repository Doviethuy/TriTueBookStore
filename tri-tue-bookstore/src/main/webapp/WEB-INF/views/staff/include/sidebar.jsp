<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>General</h3>
		<ul class="nav side-menu">
			<li><a><i class="fa fa-edit"></i>Nhân viên<span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><a href="${ctxPath}/staff/ban-hang">Bán hàng</a></li>
					<li><a href="${ctxPath}/staff/san-pham">Sản phẩm</a></li>
					<li><a href="${ctxPath}/staff/hoa-don">Hoá đơn</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- /sidebar menu -->

<!-- /menu footer buttons -->
<div class="sidebar-footer hidden-small">
	<a data-toggle="tooltip" data-placement="top" title="Settings"> <span
		class="glyphicon glyphicon-cog" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="Full Screen">
		<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
		class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
	</a> <a data-toggle="tooltip" data-placement="top" title="Logout"
		href="${ctxPath}/logout"> <span class="glyphicon glyphicon-off"
		aria-hidden="true"></span>
	</a>
</div>
<!-- /menu footer buttons -->
</div>
</div>

<!-- top navigation -->
<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <img
						src="${ctxPath}/resources/images/img.jpg" alt="">John Doe <span
						class=" fa fa-angle-down"></span>
				</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right">
						<li><a href="javascript:;"> Thông tin</a></li>
						<li><a href="${ctxPath}/logout"><i
								class="fa fa-sign-out pull-right"></i> Đăng xuất</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>
</div>
<!-- /top navigation -->