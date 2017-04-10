<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="row">
		<div class="col-md-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						Tiền hàng bán được
					</h2>
					<div class="filter">
						<div id="reportrange2" class="pull-right"
							style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
							<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <span>December
								30, 2014 - January 28, 2015</span> <b class="caret"></b>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<div class="col-md-9 col-sm-12 col-xs-12">
						<div class="demo-container" style="height: 280px">
							<div id="chart_plot_namtbb" class="demo-placeholder"></div>
						</div>
						<div class="tiles">
							<div class="col-md-4 tile">
								<span>Total Sessions</span>
								<h2>231,809</h2>
								<span class="sparkline11 graph" style="height: 160px;">
									<canvas width="200" height="60"
										style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
								</span>
							</div>
							<div class="col-md-4 tile">
								<span>Total Revenue</span>
								<h2>$231,809</h2>
								<span class="sparkline22 graph" style="height: 160px;">
									<canvas width="200" height="60"
										style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
								</span>
							</div>
							<div class="col-md-4 tile">
								<span>Total Sessions</span>
								<h2>231,809</h2>
								<span class="sparkline11 graph" style="height: 160px;">
									<canvas width="200" height="60"
										style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
								</span>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-12 col-xs-12">
                      <div>
                        <div class="x_title">
                          <h2>Nhân viên bán hàng nhiều nhất</h2>
                          <div class="clearfix"></div>
                        </div>
                        <ul class="list-unstyled top_profiles scroll-view">
                        	<c:forEach items="${users}" var="item">
								<li class="media event">
									<a class="pull-left border-aero profile_thumb"><i class="fa fa-user aero"></i></a>
									<div class="media-body">
										<a class="title" href="#">${item.name}</a>
										<p>
											<strong>$2300. </strong> Tổng số
										</p>
										<p>
											<small>12 Hoá đơn ngày hôm nay</small>
										</p>
									</div>
								</li>
						 	</c:forEach>
                        </ul>
                      </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->
<%@ include file="include/footer.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var chart_plot_namtbb_data = [];
	for (var i = 0; i < 30; i++) {
	  chart_plot_namtbb_data.push([new Date(Date.today().add(i).days()).getTime(), ${invoices.get(i).amount}]);
	}
	console.log(chart_plot_namtbb_data);
	var chart_plot_namtbb_settings = {
		grid: {
			show: true,
			aboveData: true,
			color: "#3f3f3f",
			labelMargin: 10,
			axisMargin: 0,
			borderWidth: 0,
			borderColor: null,
			minBorderMargin: 5,
			clickable: true,
			hoverable: true,
			autoHighlight: true,
			mouseActiveRadius: 100
		},
		series: {
			lines: {
				show: true,
				fill: true,
				lineWidth: 2,
				steps: false
			},
			points: {
				show: true,
				radius: 4.5,
				symbol: "circle",
				lineWidth: 3.0
			}
		},
		legend: {
			position: "ne",
			margin: [0, -25],
			noColumns: 0,
			labelBoxBorderColor: null,
			labelFormatter: function(label, series) {
				return label + '&nbsp;&nbsp;';
			},
			width: 40,
			height: 1
		},
		colors: ['#96CA59', '#3F97EB', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'],
		shadowSize: 0,
		tooltip: true,
		tooltipOpts: {
			content: "%s: %y.0",
			xDateFormat: "%d/%m",
		shifts: {
			x: -30,
			y: -50
		},
		defaultTheme: false
		},
		yaxis: {
			min: 0
		},
		xaxis: {
			mode: "time",
			minTickSize: [1, "day"],
			timeformat: "%d/%m/%y",
			min: chart_plot_namtbb_data[0][0],
			max: chart_plot_namtbb_data[20][0]
		}
	};
	$("<div id='tooltip'></div>").css({
		position: "absolute",
		display: "none",
		border: "1px solid #fdd",
		padding: "2px",
		"background-color": "#fee",
		opacity: 0.80
	}).appendTo("body");
	
	if ($("#chart_plot_namtbb").length){
		$.plot( $("#chart_plot_namtbb"), 
		[{ 
			label: "VNĐ", 
			data: chart_plot_namtbb_data, 
			lines: { 
				fillColor: "rgba(150, 202, 89, 0.12)" 
			}, 
			points: { 
				fillColor: "#fff" } 
		}], chart_plot_namtbb_settings);
	}
	
	$("#chart_plot_namtbb").bind("plothover", function (event, pos, item) {
		if (item) {
			var x = item.datapoint[0].toFixed(2),
				y = item.datapoint[1].toFixed(2);
			var today = new Date(parseInt(x));
			var dd = today.getDate();
			var mm = today.getMonth()+1;
			var yyyy = today.getFullYear();

			if(dd<10) {
			    dd='0'+dd
			} 

			if(mm<10) {
			    mm='0'+mm
			} 

			today = dd+'/'+mm+'/'+yyyy;
			$("#tooltip").html(today + ": " + parseInt(y))
				.css({top: item.pageY+5, left: item.pageX+5})
				.fadeIn(200);
		} else {
			$("#tooltip").hide();
		}
	});
	
	init_daterangepicker();
});

function init_daterangepicker() {
	if( typeof ($.fn.daterangepicker) === 'undefined'){ return; }
	console.log('init_daterangepicker2');

	var cb = function(start, end, label) {
	  console.log(start.toISOString(), end.toISOString(), label);
	  $('#reportrange2 span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
	};

	var optionSet1 = {
	  startDate: moment().subtract(29, 'days'),
	  endDate: moment(),
	  minDate: '01/04/2017',
	  maxDate: '30/04/2017',
	  dateLimit: {
		days: 60
	  },
	  showDropdowns: true,
	  showWeekNumbers: true,
	  timePicker: false,
	  timePickerIncrement: 1,
	  timePicker12Hour: true,
	  ranges: {
		'Hôm nay': [moment(), moment()],
		'Hôm qua': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		'7 ngày trước': [moment().subtract(6, 'days'), moment()],
		'30 ngày trước': [moment().subtract(29, 'days'), moment()],
		'Tháng này': [moment().startOf('month'), moment().endOf('month')],
		'Tháng trước': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	  },
	  opens: 'left',
	  buttonClasses: ['btn btn-default'],
	  applyClass: 'btn-small btn-primary',
	  cancelClass: 'btn-small',
	  format: 'dd/MM/yyyy',
	  separator: ' to ',
	  locale: {
		applyLabel: 'Tìm kiếm',
		cancelLabel: 'Xoá',
		fromLabel: 'Từ',
		toLabel: 'Đến',
		customRangeLabel: 'Tuỳ chỉnh',
		//daysOfWeek: ['Chủ nhật', 'Thứ hai', 'Thứ ba', 'Thứ tư', 'Thứ năm', 'Thứ sáu', 'Thứ bảy'],
		//monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
		firstDay: 1
	  }
	};
	
	$('#reportrange2 span').html(moment().subtract(29, 'days').format('DD/MM/YYYY') + ' - ' + moment().format('DD/MM/YYYY'));
	$('#reportrange2').daterangepicker(optionSet1, cb);
	$('#reportrange2').on('show.daterangepicker', function() {
	  console.log("show event fired");
	});
	$('#reportrange2').on('hide.daterangepicker', function() {
	  console.log("hide event fired");
	});
	$('#reportrange2').on('apply.daterangepicker', function(ev, picker) {
	  console.log("apply event fired, start/end dates are " + picker.startDate.format('DD/MM/YYYY') + " to " + picker.endDate.format('DD/MM/YYYY'));
	});
	$('#reportrange2').on('cancel.daterangepicker', function(ev, picker) {
	  console.log("cancel event fired");
	});
	$('#options1').click(function() {
	  $('#reportrange2').data('daterangepicker').setOptions(optionSet1, cb);
	});
	$('#options2').click(function() {
	  $('#reportrange2').data('daterangepicker').setOptions(optionSet2, cb);
	});
	$('#destroy').click(function() {
	  $('#reportrange2').data('daterangepicker').remove();
	});   
}
</script>
