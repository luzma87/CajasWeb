
<%@ page import="cajas.Settings" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'settings.label', default: 'Settings')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-settings" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-settings" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list settings">
			
				<g:if test="${settingsInstance?.floraBseLink}">
				<li class="fieldcontain">
					<span id="floraBseLink-label" class="property-label"><g:message code="settings.floraBseLink.label" default="Flora Bse Link" /></span>
					
						<span class="property-value" aria-labelledby="floraBseLink-label"><g:fieldValue bean="${settingsInstance}" field="floraBseLink"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingsInstance?.tropicosBaseLink}">
				<li class="fieldcontain">
					<span id="tropicosBaseLink-label" class="property-label"><g:message code="settings.tropicosBaseLink.label" default="Tropicos Base Link" /></span>
					
						<span class="property-value" aria-labelledby="tropicosBaseLink-label"><g:fieldValue bean="${settingsInstance}" field="tropicosBaseLink"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:settingsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${settingsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
