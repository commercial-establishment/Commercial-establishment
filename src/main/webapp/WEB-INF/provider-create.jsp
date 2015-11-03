<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<t:head title="Добавление поставщика"/>

<t:container providers="active">

  <div id="page-wrapper">

    <div class="container-fluid">

      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">
            Добавление поставщика
          </h1>
          <ol class="breadcrumb">
            <li>
              <i class="fa fa-dashboard"></i> <a href="<c:url value="/home"/>">Главная</a>
            </li>
            <li>
              <i class="fa fa-table"></i> <a href="<c:url value="/admin/providers"/>">Список поставщиков</a>
            </li>
            <li class="active">
              <i class="fa fa-edit"></i> Добавление поставщика
            </li>
          </ol>
        </div>
      </div>
      <div class="col-lg-12">
        <div class="table-responsive">
          <form:form method="post" action="/admin/providers/create" modelAttribute="provider">
            <table class="table table-hover">
              <tbody>
              <tr>
                <td><form:label path="username">Имя пользователя:</form:label></td>
                <td><form:input cssClass="form-control" path="username"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="password">Пароль:</form:label></td>
                <td><form:password cssClass="form-control" path="password"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="email">Email:</form:label></td>
                <td><form:input type="email" cssClass="form-control" path="email"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="city">Город:</form:label></td>
                <td>
                  <select name="city" id="city" class="form-control">
                    <c:forEach items="${cities}" var="city">
                      <option>${city.name}</option>
                    </c:forEach>
                  </select>
                </td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="address">Адрес:</form:label></td>
                <td><form:input cssClass="form-control" path="address"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="companyName">Название компании:</form:label></td>
                <td><form:input cssClass="form-control" path="companyName"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="contactPerson">Контактное лицо:</form:label></td>
                <td><form:input cssClass="form-control" path="contactPerson"/></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="role">Роль:</form:label></td>
                <td><form:label path="role">PROVIDER</form:label></td>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td><form:label path="blocked">Заблокирован:</form:label></td>
                <td>${provider.blocked}</td>
                <form:hidden path="blocked"/>
                <td>Имя пользователя должно состоять из ...</td>
              </tr>
              <tr>
                <td>
                  <form:button type="submit" class="btn btn-lg btn-success">Сохранить</form:button>
                </td>
                <td/>
                <td><a href="<c:url value="/admin/providers/${id}"/>" class="btn btn-lg btn-danger">Отмена</a>
                </td>
              </tr>
              </tbody>
            </table>
          </form:form>
        </div>
      </div>
    </div>
  </div>
  <t:script/>
</t:container>
</html>
