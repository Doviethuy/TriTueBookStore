<%@page import="com.aptech.util.Constant"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	request.setAttribute("ctxPath", request.getContextPath());
	request.setAttribute("fullName", session.getAttribute(Constant.FULLNAME));
	request.setAttribute("avartar", session.getAttribute(Constant.AVARTAR));
%>


