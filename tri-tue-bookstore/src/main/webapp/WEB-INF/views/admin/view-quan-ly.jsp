<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>
<script type="text/javascript" src="${ctxPath}/resources/static/vendors/highchart/highcharts.js"></script>
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
					<div class="col-md-12">
						<div class="demo-container" style="height: 100%">
							<div id="chart-container"></div>
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
	init_chart();
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
	$('#reportrange2').on('apply.daterangepicker', function(ev, picker) {
	  console.log("apply event fired, start/end dates are " + picker.startDate.format('DD/MM/YYYY') + " to " + picker.endDate.format('DD/MM/YYYY'));
	  init_chart(picker.startDate.format('DD/MM/YYYY'), picker.endDate.format('DD/MM/YYYY'));	  
	});	   
}

function init_chart(start, end){
	Highcharts.setOptions({
		lang: {
			shortMonths: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
		}
	});
	$.getJSON('${ctxPath}/admin/report/get-data-report?start='+start+"&end="+end, function (data) {
	    Highcharts.chart('chart-container', {	    	
	    	chart: {
	            zoomType: 'x',
	            style: {
		            fontFamily: 'Helvetica Neue,Roboto,Arial,sans-serif'
	            }
	        },
	        title: {
	            text: 'Số tiền thu vào của cửa hàng theo thời gian'
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                    'Kéo thả để zoom' : 'Kéo thả để zoom'
	        },
	        xAxis: {
	            type: 'datetime',
	            dateTimeLabelFormats: {
	                day: '%e/%b/%Y'
	            }
	        },
	        yAxis: {
	            title: {
	                text: 'Số tiền'
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            area: {
	                fillColor: {
	                    linearGradient: {
	                        x1: 0,
	                        y1: 0,
	                        x2: 0,
	                        y2: 1
	                    },
	                    stops: [
	                        [0, Highcharts.getOptions().colors[0]],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                marker: {
	                    radius: 2
	                },
	                lineWidth: 1,
	                states: {
	                    hover: {
	                        lineWidth: 1
	                    }
	                },
	                threshold: null
	            }
	        },

	        series: [{
	            type: 'area',
	            name: 'VNĐ',
	            data: data
	        }]
	    });
	});
}
</script>
<%try{%>
<c:if test="<%=session.getAttribute(Constant.LOGIN_SUCCESS).toString().equals(Constant.LOGIN_SUCCESS) %>">
	<script>
		show_notify();
		
		function show_notify(){
			new PNotify({
	               title: 'Thành công',
	               text: 'Đăng nhập thành công!',
	               type: 'success',
	               styling: 'bootstrap3'
	           });
		}
	</script>
	<%session.removeAttribute(Constant.LOGIN_SUCCESS); %>
</c:if>
<%}catch(Exception e){}%>