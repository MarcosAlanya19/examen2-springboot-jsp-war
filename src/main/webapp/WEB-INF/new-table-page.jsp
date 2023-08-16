<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.examen.forge.config.AppConfig" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Examen - Nueva cancion</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div class="container mt-5">
      <h1>Agregar mesa!</h1>
      <form:form
        method="POST"
        action="${pageContext.request.contextPath}/${AppConfig.POST_CREATE_TABLE}"
        modelAttribute="${AppConfig.MA_TABLE}"
        class="needs-validation"
      >
        <div class="mb-3">
          <form:label path="name">Nombre:</form:label>
          <form:input type="text" path="name" class="form-control" />
          <form:errors path="name" cssClass="error" />
          <c:if test="${not empty errorMessage}">
            <p style="color: red">${errorMessage}</p>
          </c:if>
        </div>

        <div class="mb-3">
          <form:label path="number">Numero:</form:label>
          <form:select path="number" class="form-select">
            <form:option value="1">1</form:option>
            <form:option value="2">2</form:option>
            <form:option value="3">3</form:option>
            <form:option value="4">5</form:option>
            <form:option value="4">6</form:option>
            <form:option value="4">7</form:option>
            <form:option value="4">8</form:option>
            <form:option value="4">9</form:option>
            <form:option value="4">10</form:option>
          </form:select>
          <form:errors path="number" cssClass="error" />
        </div>


        <div class="mb-3">
          <form:label path="notes">Notas:</form:label>
          <form:input type="text" path="notes" class="form-control" />
          <form:errors path="notes" cssClass="error" />
        </div>

        <form:errors cssClass="global-error" />

        <input type="submit" value="Agregar!" class="btn btn-primary" />
      </form:form>
      <a href="/${AppConfig.ROUTE_HOME}" class="btn btn-secondary">Cancelar</a>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
