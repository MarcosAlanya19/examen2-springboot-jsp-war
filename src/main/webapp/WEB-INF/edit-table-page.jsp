<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java"
contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.examen.forge.config.AppConfig" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Examen - Contribución</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
  <div class="container mt-5">
    <a href="/${AppConfig.ROUTE_LOGOUT_USER}" class="btn btn-outline-primary">Logout</a>

    <h1 class="display-4">Edicion de mesa:</h1>

    <form:form
      method="POST"
      action="${pageContext.request.contextPath}/${AppConfig.POST_INDEX_TABLE}/${table.id}/edit"
      modelAttribute="${AppConfig.MA_TABLE}"
      class="mt-4"
    >
        <div class="mb-3">
          <form:label path="name">Nombre:</form:label>
          <form:input type="text" path="name" class="form-control" />
          <form:errors path="name" class="text-danger" />
          <c:if test="${not empty errorMessage}">
            <p style="color: red">${errorMessage}</p>
          </c:if>
        </div>

        <div class="mb-3">
          <form:label path="number">Numero:</form:label>
          <form:select path="number" class="form-select">
            <c:forEach var="i" begin="1" end="10">
              <form:option value="${i}">${i}</form:option>
            </c:forEach>
          </form:select>
          <form:errors path="number" class="text-danger" />
        </div>

        <div class="mb-3">
          <form:label path="notes">Notas:</form:label>
          <form:input type="text" path="notes" class="form-control" />
          <form:errors path="notes" class="text-danger" />
        </div>

      <button type="submit" class="btn btn-primary">Actualizar cación</button>
    </form:form>

    <a
      href="${pageContext.request.contextPath}/${AppConfig.ROUTE_HOME}"
      class="btn btn-secondary mt-3"
    >
      Cancelar
    </a>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
  <script>
    function deleteSong() {
      var result = confirm('¿Está seguro de que desea eliminar esta canción?');
      if (result) {
        document.getElementById('deleteForm').submit();
      }
    }
  </script>
</body>
</html>
