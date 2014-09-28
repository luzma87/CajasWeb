<%@ page import="cajas.Genero" %>



<div class="fieldcontain ${hasErrors(bean: generoInstance, field: 'familia', 'error')} required">
	<label for="familia">
		<g:message code="genero.familia.label" default="Familia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="familia" name="familia.id" from="${cajas.Familia.list()}" optionKey="id" required="" value="${generoInstance?.familia?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: generoInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="genero.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${generoInstance?.nombre}"/>

</div>

