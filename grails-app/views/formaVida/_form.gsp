<%@ page import="cajas.FormaVida" %>



<div class="fieldcontain ${hasErrors(bean: formaVidaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="formaVida.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${formaVidaInstance?.nombre}"/>

</div>

