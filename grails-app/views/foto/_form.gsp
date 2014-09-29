<%@ page import="cajas.Foto" %>



<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'lugar', 'error')} ">
	<label for="lugar">
		<g:message code="foto.lugar.label" default="Lugar" />
		
	</label>
	<g:select id="lugar" name="lugar.id" from="${cajas.Lugar.list()}" optionKey="id" value="${fotoInstance?.lugar?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'coordenada', 'error')} ">
	<label for="coordenada">
		<g:message code="foto.coordenada.label" default="Coordenada" />
		
	</label>
	<g:select id="coordenada" name="coordenada.id" from="${cajas.Coordenada.list()}" optionKey="id" value="${fotoInstance?.coordenada?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'path', 'error')} ">
	<label for="path">
		<g:message code="foto.path.label" default="Path" />
		
	</label>
	<g:textField name="path" value="${fotoInstance?.path}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fotoInstance, field: 'especie', 'error')} required">
	<label for="especie">
		<g:message code="foto.especie.label" default="Especie" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="especie" name="especie.id" from="${cajas.Especie.list()}" optionKey="id" required="" value="${fotoInstance?.especie?.id}" class="many-to-one"/>

</div>

