<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.examen.forge.config.AppConfig" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalle de la canción</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
  <div class="container mt-5">
    <div class="card">
      <div class="card-body">
        <h1 class="card-title text-center mb-4"><strong><c:out value="${table.name.toUpperCase()}" /></strong></h1>
        <h4 class="card-subtitle text-muted mb-3">
          Mesa de : <c:choose>
            <c:when test="${empty table.waiter.name}">DISPONIBLE</c:when>
            <c:otherwise><c:out value="${table.waiter.name}" /></c:otherwise>
          </c:choose>
        </h4>
        <p class="card-text"><strong>Notas:</strong> <c:out value="${table.notes}" /></p>
        <p class="card-text"><strong>Numero de invitados:</strong> <c:out value="${table.number}" /></p>
        <div class="mt-4 d-flex justify-content-between">
          <a href="/${AppConfig.ROUTE_HOME}" class="btn btn-outline-primary">Regresar a inicio</a>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
