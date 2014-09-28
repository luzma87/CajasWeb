package cajas

import groovy.io.FileType

class NewImportController {

    def import1() {
        if (Lugar.count() == 0) {
            def lugar = new Lugar()
            lugar.nombre = "Páramo del Cajas, Ecuador"
            if (lugar.save(flush: true)) {
                println "lugar creado"
            } else {
                println "error Lugar: " + lugar.errors
            }
        }

        if (Settings.count() == 0) {
            def setts = new Settings()
            setts.floraBseLink = "http://www.mobot.org/MOBOT/paramo/search_paramo.asp?searchFor="
            setts.tropicosBaseLink = "http://www.tropicos.org/Name/"
            if (setts.save(flush: true)) {
                println "setts creado"
            } else {
                println "error setts: " + setts.errors
            }
        }

        def lugar = Lugar.list().first()

        def webapp = servletContext.getRealPath("/")
        def pathCsv = webapp + "csv/"
        def pathImages = webapp + "images/plants/"

        def file = 'Cajas_Luzma.csv'

        def firstLine = true

        def files = [:]

        def dir = new File(pathImages)
        dir.eachFileRecurse(FileType.FILES) { f ->
            def name = f.name
            def num = name[0..1]
            def n2 = name[2]
            if (n2.isNumber()) {
                num = name[0..2]
            }
            num = num.toInteger()
            if (files[num]) {
                files[num] = []
            }
            files[num] += name
        }


    }

    def index() {}
}
