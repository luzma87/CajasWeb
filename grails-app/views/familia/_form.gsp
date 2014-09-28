<%@ page import="cajas.Familia" %>



<div class="fieldcontain ${hasErrors(bean: familiaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="familia.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${familiaInstance?.nombre}"/>

</div>

