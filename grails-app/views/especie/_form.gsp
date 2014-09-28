<%@ page import="cajas.Especie" %>



<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'color2', 'error')} ">
	<label for="color2">
		<g:message code="especie.color2.label" default="Color2" />
		
	</label>
	<g:select id="color2" name="color2.id" from="${cajas.Color.list()}" optionKey="id" value="${especieInstance?.color2?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'formaVida2', 'error')} ">
	<label for="formaVida2">
		<g:message code="especie.formaVida2.label" default="Forma Vida2" />
		
	</label>
	<g:select id="formaVida2" name="formaVida2.id" from="${cajas.FormaVida.list()}" optionKey="id" value="${especieInstance?.formaVida2?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'nombreComun', 'error')} ">
	<label for="nombreComun">
		<g:message code="especie.nombreComun.label" default="Nombre Comun" />
		
	</label>
	<g:textField name="nombreComun" value="${especieInstance?.nombreComun}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="especie.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${especieInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'color1', 'error')} required">
	<label for="color1">
		<g:message code="especie.color1.label" default="Color1" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="color1" name="color1.id" from="${cajas.Color.list()}" optionKey="id" required="" value="${especieInstance?.color1?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'formaVida1', 'error')} required">
	<label for="formaVida1">
		<g:message code="especie.formaVida1.label" default="Forma Vida1" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formaVida1" name="formaVida1.id" from="${cajas.FormaVida.list()}" optionKey="id" required="" value="${especieInstance?.formaVida1?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'genero', 'error')} required">
	<label for="genero">
		<g:message code="especie.genero.label" default="Genero" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genero" name="genero.id" from="${cajas.Genero.list()}" optionKey="id" required="" value="${especieInstance?.genero?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'linkCajas', 'error')} required">
	<label for="linkCajas">
		<g:message code="especie.linkCajas.label" default="Link Cajas" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="linkCajas" required="" value="${especieInstance?.linkCajas}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'linkTropicos', 'error')} required">
	<label for="linkTropicos">
		<g:message code="especie.linkTropicos.label" default="Link Tropicos" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="linkTropicos" required="" value="${especieInstance?.linkTropicos}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: especieInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="especie.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${especieInstance?.nombre}"/>

</div>

