package cajas


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FormaVidaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FormaVida.list(params), model: [formaVidaInstanceCount: FormaVida.count()]
    }

    def show(FormaVida formaVidaInstance) {
        respond formaVidaInstance
    }

    def create() {
        respond new FormaVida(params)
    }

    @Transactional
    def save(FormaVida formaVidaInstance) {
        if (formaVidaInstance == null) {
            notFound()
            return
        }

        if (formaVidaInstance.hasErrors()) {
            respond formaVidaInstance.errors, view: 'create'
            return
        }

        formaVidaInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'formaVida.label', default: 'FormaVida'), formaVidaInstance.id])
                redirect formaVidaInstance
            }
            '*' { respond formaVidaInstance, [status: CREATED] }
        }
    }

    def edit(FormaVida formaVidaInstance) {
        respond formaVidaInstance
    }

    @Transactional
    def update(FormaVida formaVidaInstance) {
        if (formaVidaInstance == null) {
            notFound()
            return
        }

        if (formaVidaInstance.hasErrors()) {
            respond formaVidaInstance.errors, view: 'edit'
            return
        }

        formaVidaInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FormaVida.label', default: 'FormaVida'), formaVidaInstance.id])
                redirect formaVidaInstance
            }
            '*' { respond formaVidaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FormaVida formaVidaInstance) {

        if (formaVidaInstance == null) {
            notFound()
            return
        }

        formaVidaInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FormaVida.label', default: 'FormaVida'), formaVidaInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'formaVida.label', default: 'FormaVida'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
