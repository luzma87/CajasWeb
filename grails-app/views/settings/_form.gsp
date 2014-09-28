<%@ page import="cajas.Settings" %>



<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'floraBseLink', 'error')} required">
	<label for="floraBseLink">
		<g:message code="settings.floraBseLink.label" default="Flora Bse Link" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="floraBseLink" required="" value="${settingsInstance?.floraBseLink}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'tropicosBaseLink', 'error')} required">
	<label for="tropicosBaseLink">
		<g:message code="settings.tropicosBaseLink.label" default="Tropicos Base Link" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tropicosBaseLink" required="" value="${settingsInstance?.tropicosBaseLink}"/>

</div>

