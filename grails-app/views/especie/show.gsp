<%@ page import="cajas.Especie" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'especie.label', default: 'Especie')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>

        <g:set var="setts" value="${cajas.Settings.list().first()}"/>

        <a href="#show-especie" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
            </ul>
        </div>

        <div id="show-especie" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list especie">

                <g:if test="${especieInstance?.color2}">
                    <li class="fieldcontain">
                        <span id="color2-label" class="property-label"><g:message code="especie.color2.label" default="Color2"/></span>

                        <span class="property-value" aria-labelledby="color2-label"><g:link controller="color" action="show" id="${especieInstance?.color2?.id}">${especieInstance?.color2?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.formaVida2}">
                    <li class="fieldcontain">
                        <span id="formaVida2-label" class="property-label"><g:message code="especie.formaVida2.label" default="Forma Vida2"/></span>

                        <span class="property-value" aria-labelledby="formaVida2-label"><g:link controller="formaVida" action="show" id="${especieInstance?.formaVida2?.id}">${especieInstance?.formaVida2?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.nombreComun}">
                    <li class="fieldcontain">
                        <span id="nombreComun-label" class="property-label"><g:message code="especie.nombreComun.label" default="Nombre Comun"/></span>

                        <span class="property-value" aria-labelledby="nombreComun-label"><g:fieldValue bean="${especieInstance}" field="nombreComun"/></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.descripcion}">
                    <li class="fieldcontain">
                        <span id="descripcion-label" class="property-label"><g:message code="especie.descripcion.label" default="Descripcion"/></span>

                        <span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${especieInstance}" field="descripcion"/></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.descripcionEs}">
                    <li class="fieldcontain">
                        <span id="descripcionEs-label" class="property-label"><g:message code="especie.descripcionEs.label" default="Descripcion ES"/></span>

                        <span class="property-value" aria-labelledby="descripcionEs-label"><g:fieldValue bean="${especieInstance}" field="descripcionEs"/></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.descripcionEn}">
                    <li class="fieldcontain">
                        <span id="descripcionEn-label" class="property-label"><g:message code="especie.descripcionEn.label" default="Descripcion EN"/></span>

                        <span class="property-value" aria-labelledby="descripcionEn-label"><g:fieldValue bean="${especieInstance}" field="descripcionEn"/></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.autor}">
                    <li class="fieldcontain">
                        <span id="autor-label" class="property-label"><g:message code="especie.autor.label" default="Autor"/></span>

                        <span class="property-value" aria-labelledby="autor-label"><g:fieldValue bean="${especieInstance}" field="autor"/></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.color1}">
                    <li class="fieldcontain">
                        <span id="color1-label" class="property-label"><g:message code="especie.color1.label" default="Color1"/></span>

                        <span class="property-value" aria-labelledby="color1-label"><g:link controller="color" action="show" id="${especieInstance?.color1?.id}">${especieInstance?.color1?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.formaVida1}">
                    <li class="fieldcontain">
                        <span id="formaVida1-label" class="property-label"><g:message code="especie.formaVida1.label" default="Forma Vida1"/></span>

                        <span class="property-value" aria-labelledby="formaVida1-label"><g:link controller="formaVida" action="show" id="${especieInstance?.formaVida1?.id}">${especieInstance?.formaVida1?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.genero?.familia}">
                    <li class="fieldcontain">
                        <span id="familia-label" class="property-label"><g:message code="especie.familia.label" default="Familia"/></span>

                        <span class="property-value" aria-labelledby="familia-label">
                            <g:link controller="familia" action="show" id="${especieInstance?.genero?.familia?.id}">
                                ${especieInstance?.genero?.familia?.encodeAsHTML()}
                            </g:link>
                        </span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.genero}">
                    <li class="fieldcontain">
                        <span id="genero-label" class="property-label"><g:message code="especie.genero.label" default="Genero"/></span>

                        <span class="property-value" aria-labelledby="genero-label"><g:link controller="genero" action="show" id="${especieInstance?.genero?.id}">${especieInstance?.genero?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.linkCajas}">
                    <li class="fieldcontain">
                        <span id="linkCajas-label" class="property-label"><g:message code="especie.linkCajas.label" default="Link Cajas"/></span>

                        <span class="property-value" aria-labelledby="linkCajas-label">
                            <g:set var="linkCajas" value="${setts.floraBseLink + especieInstance.linkCajas}"/>
                            <a href="${linkCajas}" target="_blank">${linkCajas}</a>
                        </span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.linkTropicos}">
                    <li class="fieldcontain">
                        <span id="linkTropicos-label" class="property-label"><g:message code="especie.linkTropicos.label" default="Link Tropicos"/></span>

                        <span class="property-value" aria-labelledby="linkTropicos-label">
                            <g:set var="linkTropicos" value="${setts.tropicosBaseLink + especieInstance.linkTropicos}"/>
                            <a href="${linkTropicos}" target="_blank">${linkTropicos}</a>
                        </span>

                    </li>
                </g:if>

                <g:if test="${especieInstance?.nombre}">
                    <li class="fieldcontain">
                        <span id="nombre-label" class="property-label"><g:message code="especie.nombre.label" default="Nombre"/></span>

                        <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${especieInstance}" field="nombre"/></span>

                    </li>
                </g:if>

            </ol>
            <g:form url="[resource: especieInstance, action: 'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${especieInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
