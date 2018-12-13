<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="/resources/css/basic.css" rel="stylesheet">

    <script src="<c:url value="${pageContext.request.contextPath}/webjars/jquery/1.9.0/jquery.min.js"  />"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/webjars/bootstrap/3.1.0/js/bootstrap.js"  />"></script>

    <title>CRUD - ${title}</title>
</head>
</tab>
<c:import url="page_components/header.jsp"></c:import>
<div class="container" >
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="text-center">
                        <h1>${title}<small> crud operations</small></h1>
                    </div>
                </div>
                <div class="alert alert-info" role="alert">
                    <a class="btn btn-primary" role="button" href="/${instrument}/pdfReport?view=pdfView" target="_blank">Download PDF report</a>
                    <a class="btn btn-primary" role="button" href="/${instrument}/xlsxReport.xlsx?view=excelView" target="_blank">Download Excel report</a>
                </div>
                <div class="panel-body">
                    <div class="panel panel-info">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <div class="text-center"><h3>Brands</h3></div>
                        </div>
                        <table class="table table-striped table-condensed" id="car-brands">
                            <thead>
                            <th><button class="sort" data-sort="Autor-name">autor</button></th>
                            <th><button class="sort" data-sort="publisher">publisher</button></th>
                            <th>action</th>
                            </thead>
                            <tbody align="center" class="list">
                            <c:forEach var="autor" items="${listBookAutor}" varStatus="status">
                                <tr>
                                    <td class="autor-name">${autor.name}</td>
                                    <td class="publisher">${autor.publisher}</td>
                                    <td class="action">
                                        <a href="/${instrument}/edit-autor/${autor.idAutor}">Edit</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="/${instrument}/delete-autor/${autor.idAutor}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="panel-footer"><a class="btn btn-info" role="button" href="/${instrument}/newAutor">Add new autor &raquo</a></div>
                    </div>

                    <div class="panel panel-info">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <div class="text-center"><h3>Title</h3></div>
                        </div>
                        <table class="table table-striped table-condensed" id="car-models">
                            <thead>
                            <th><button class="sort" data-sort="autor-name">autor</button></th>
                            <th><button class="sort" data-sort="title-name">title</button></th>
                            <th><button class="sort" data-sort="genre">genre</button></th>
                            <th><button class="sort" data-sort="numberofpages">number of pages</button></th>
                            <th>action</th>
                            </thead>
                            <tbody align="center" class="list">
                            <c:forEach var="title" items="${listBookTitle}" varStatus="status">
                                <tr>
                                    <td class="autor-name">${title.bookAutor.name}</td>
                                    <td class="title-name">${title.TitleName}</td>
                                    <td class="genre">${title.genre}</td>
                                    <td class="numberofpages">${title.numberofpages}</td>
                                    <td>
                                        <a href="/${instrument}/edit-title/${title.idTitle}">Edit</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="/${instrument}/delete-title/${title.idTitle}">Delete</a>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="panel-footer"><a class="btn btn-info" role="button" href="/${instrument}/newTitle">Add new title &raquo</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/list.min.js"></script>
<script src="/resources/js/content-list.js"></script>
</body>
</html>