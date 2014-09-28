<%@ page import="cajas.Foto" %>



<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'especie', 'error')} required">
	<label for="especie">
		<g:message code="foto.especie.label" default="Especie" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="especie" name="especie.id" from="${cajas.Especie.list()}" optionKey="id" required="" value="${fotoInstance?.especie?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'latitud', 'error')} required">
	<label for="latitud">
		<g:message code="foto.latitud.label" default="Latitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitud" value="${fieldValue(bean: fotoInstance, field: 'latitud')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'longitud', 'error')} required">
	<label for="longitud">
		<g:message code="foto.longitud.label" default="Longitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longitud" value="${fieldValue(bean: fotoInstance, field: 'longitud')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'lugar', 'error')} required">
	<label for="lugar">
		<g:message code="foto.lugar.label" default="Lugar" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lugar" name="lugar.id" from="${cajas.Lugar.list()}" optionKey="id" required="" value="${fotoInstance?.lugar?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'path', 'error')} required">
	<label for="path">
		<g:message code="foto.path.label" default="Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="path" required="" value="${fotoInstance?.path}"/>

</div>

