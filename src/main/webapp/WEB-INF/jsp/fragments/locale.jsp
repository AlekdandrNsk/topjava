<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="dropdown">
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
        ${pageContext.response.locale}
    </button>
    <div class="dropdown-menu">
        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?language=en">English</a>
        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?language=ru">Русский</a>
    </div>
</div>
