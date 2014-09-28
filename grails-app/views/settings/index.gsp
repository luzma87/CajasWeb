
<%@ page import="cajas.Settings" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'settings.label', default: 'Settings')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-settings" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-settings" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="floraBseLink" title="${message(code: 'settings.floraBseLink.label', default: 'Flora Bse Link')}" />
					
						<g:sortableColumn property="tropicosBaseLink" title="${message(code: 'settings.tropicosBaseLink.label', default: 'Tropicos Base Link')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${settingsInstanceList}" status="i" var="settingsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${settingsInstance.id}">${fieldValue(bean: settingsInstance, field: "floraBseLink")}</g:link></td>
					
						<td>${fieldValue(bean: settingsInstance, field: "tropicosBaseLink")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${settingsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
