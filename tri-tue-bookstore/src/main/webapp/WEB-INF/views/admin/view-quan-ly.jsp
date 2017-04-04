<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../init.jsp"%>
<%@include file="include/header.jsp"%>
<%@include file="include/sidebar.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="row top_tiles">
		<div>
			<span style="text-decoration: underline">Thống kê tháng ${month} năm ${year}</span>
			<span style="float: right">Hôm nay ${today}</span>
		</div>			
		<div style="margin-top: 30px">				
			<h2>Tổng đơn hàng : ${arrInvoice[0]}</h2>
			<div style="height:100px;background-color: yellow;font-size: 2px;margin-top: 30px">
				<div style="display:inline-block;min-width:7%;border-right: 1px solid green;border-top: 1px solid green">
					<h5 style="text-align:center;font-size:14px;margin-top:-20px" >Đơn hàng</h5>
					<h5 style="text-align:center;font-size:14px;margin-top: 80px" >Ngày</h5>
				</div>
				<c:forEach begin="1" end="31" var="i">
					<div style="display:inline-block;height:${arrInvoice[i]*2}px;min-width:2.9%;background-color:green;border:1px solid green">
						<h5 style="text-align:center;font-size:14px;margin-top:-20px" >${arrInvoice[i]}</h5>
						<h5 style="text-align:center;font-size:14px;margin-top: 80px" >${i}</h5>
					</div>							
				</c:forEach>
			</div>
		</div>
		<div style="margin-top: 30px">				
			<h2>Tổng doanh thu : <fmt:formatNumber type="number" maxFractionDigits="1" value="${arrAmount[0]/1000000}"/> triệu VND</h2>
			<div style="height:100px;background-color: yellow;font-size: 2px;margin-top: 30px">
				<div style="display:inline-block;min-width:7%;border-right: 1px solid green;border-top: 1px solid green">
					<h5 style="text-align:center;font-size:14px;margin-top:-20px" >Triệu VND</h5>
					<h5 style="text-align:center;font-size:14px;margin-top: 80px" >Ngày</h5>
				</div>
				<c:forEach begin="1" end="31" var="i">
					<div style="display:inline-block;height:${arrAmount[i]/1000000*2}px;min-width:2.9%;background-color:green;border:1px solid green">
						<h5 style="text-align:center;font-size:14px;margin-top:-20px" ><fmt:formatNumber type="number" maxFractionDigits="1" value="${arrAmount[i]/1000000}"/></h5>
						<h5 style="text-align:center;font-size:14px;margin-top: 80px" >${i}</h5>
					</div>							
				</c:forEach>
			</div>
		</div>
		<div style="margin-top: 30px">	
			<h2>Doanh thu theo danh mục</h2>				
			<table style="margin-top: 10px;width:100%">
				<thead>
					<tr>
						<th>STT</th>
						<th>Mã danh mục</th>
						<th>Tên danh mục</th>
						<th>Doanh thu(VND)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="${lstCate.size()-1}" var="i">
						<tr>
							<td>${i+1}</td>
							<td>${lstCate.get(i).getCateId()}</td>
							<td>${lstCate.get(i).getCateName()}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1" value="${arrCatAmt[i]}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>				
		</div>			
	</div>
	<div class="row top_tiles" style="margin-top: 30px">
		<div>
			<span style="text-decoration: underline">Thống kê 6 tháng gần nhất</span>
			<span style="float: right">Hôm nay ${today}</span>
		</div>
		<div style="margin-top: 30px">				
			<table style="margin-top: 10px;width:100%">
				<thead>
					<tr>
						<th>STT</th>
						<th>Thời gian</th>
						<th>Tổng đơn hàng</th>
						<th>Doanh thu(VND)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="0" end="5" var="i">
						<tr>
							<td>${i+1}</td>
							<td>Tháng ${arrMonth[i]} năm ${arrYear[i]}</td>
							<td>${arrInvTotal[i]}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="1" value="${arrAmtTotal[i]}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>				
		</div>	
	</div>
	<br>
</div>
<!-- /page content -->
<%@ include file="include/footer.jsp"%>