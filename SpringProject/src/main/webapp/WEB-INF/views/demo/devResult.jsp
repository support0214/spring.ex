<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev��ϰ��" name="title"/>
</jsp:include>

<table border="1" align="center">
  <tr>
    <th>�̸�</th>
    <th>${dev.name}</th>
  </tr>
  <tr>
    <th>���</th>
    <th>${dev.career}</th>
  </tr>
  <tr>
    <th>�̸���</th>
    <th>${dev.email}</th>
  </tr>
  <tr>
    <th>����</th>
    <th>${dev.gender }</th>
  </tr>
  <tr>
    <td>���߾��</td>
    <%-- <td>${dev.lang[0]}, ${dev.lang[2]}</td> --%>
    <td>
    	<c:forEach items="${dev.lang}" varStatus="l">
    		${l.current}${not l.last ? ',' : ''}
    	</c:forEach>
    </td>
  </tr>
  <tr>
    <td>�����</td>
    <%-- <td>${dev.createDate }</td> --%>
    	<td>
    		<fmt:parseDate value="${dev.createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="createDate" />
    		<fmt:formatDate value="${createDate}" pattern="yy-MM-dd HH:mm" />
    	</td>
  </tr>
</table>
