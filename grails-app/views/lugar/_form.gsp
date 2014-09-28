<%@ page import="cajas.Lugar" %>



<div class="fieldcontain ${hasErrors(bean: lugarInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="lugar.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${lugarInstance?.nombre}"/>

</div>

