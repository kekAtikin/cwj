<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link href="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="/resources/css/basic.css" rel="stylesheet">

    <script src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/1.9.0/jquery.min.js"  />"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/js/bootstrap.js"  />"></script>

    <title>${title}</title>
</head>
<body>
<c:import url="page_components/header.jsp"></c:import>
<div class="container" >
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="text-center">
                        <h1>${action} title <small>using ${title}</small></h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form:form method="POST" modelAttribute="bookTitle" class="form-horizontal">
                        <form:hidden path="idTitle"/>
                        <div class="form-group">
                            <label for="idAutor" class="col-sm-3 control-label" >Autor:</label>
                            <div class="col-sm-9">
                                <form:select path="idAutor" multiple="false" class="form-control">
                                    <c:forEach var="autor" items="${listBookAutor}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${autor.idAutor == bookTitle.idAutor}">
                                                <option selected value="${autor.idAutor}">${autor.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${autor.idAutor}">${autor.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="titleName" class="col-sm-3 control-label">Title:</label>
                            <div class="col-sm-9">
                                <form:input path="titleName" class="form-control" required="required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="genre" class="col-sm-3 control-label">Genre:</label>
                            <div class="col-sm-9">
                                <form:input path="genre" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="numberofpages" class="col-sm-3 control-label">Number of pages:</label>
                            <div class="col-sm-9">
                                <form:input path="numberofpages" class="form-control" type="number" min="1800" max="2050"  />
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>