package cajas


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LugarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Lugar.list(params), model: [lugarInstanceCount: Lugar.count()]
    }

    def show(Lugar lugarInstance) {
        respond lugarInstance
    }

    def create() {
        respond new Lugar(params)
    }

    @Transactional
    def save(Lugar lugarInstance) {
        if (lugarInstance == null) {
            notFound()
            return
        }

        if (lugarInstance.hasErrors()) {
            respond lugarInstance.errors, view: 'create'
            return
        }

        lugarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lugar.label', default: 'Lugar'), lugarInstance.id])
                redirect lugarInstance
            }
            '*' { respond lugarInstance, [status: CREATED] }
        }
    }

    def edit(Lugar lugarInstance) {
        respond lugarInstance
    }

    @Transactional
    def update(Lugar lugarInstance) {
        if (lugarInstance == null) {
            notFound()
            return
        }

        if (lugarInstance.hasErrors()) {
            respond lugarInstance.errors, view: 'edit'
            return
        }

        lugarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Lugar.label', default: 'Lugar'), lugarInstance.id])
                redirect lugarInstance
            }
            '*' { respond lugarInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Lugar lugarInstance) {

        if (lugarInstance == null) {
            notFound()
            return
        }

        lugarInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Lugar.label', default: 'Lugar'), lugarInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lugar.label', default: 'Lugar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
