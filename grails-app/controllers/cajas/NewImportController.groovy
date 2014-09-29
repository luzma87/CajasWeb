package cajas

import groovy.io.FileType

class NewImportController {

    def import1() {
        if (Lugar.count() == 0) {
            def lugar = new Lugar()
            lugar.nombre = "Páramo del Cajas, Ecuador"
            lugar.path = "Cajas.jpg"
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
//        def setts = Settings.list().first()

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
            if (!files[num]) {
                files[num] = []
            }
            files[num] += name
        }

        new File(pathCsv + file).splitEachLine(";") { fields ->
            //0 Species# guide;
            //1 Family;
            //2 Genus;
            //3 species;
            //4 Scientific Name;
            //5 Link Scientific name to Description in Cajas Flora;
            //6 Flora base link;
            //7 flora key;
            //8 Author;
            //9 ;
            //10 flower color1;
            //11 flower color2;
            //12 Life form1/forma de vida;
            //13 Life form2/forma de vida;
            //14 3. Photographer;
            //15 4. Copyright;
            //16 5. Photo date;
            //17 8. Photo local    ;
            //18 Link Scientif Name to Names in Tropicos;
            //19 tropicos id;
            //20 tropicos link
            if (!firstLine) {
                def fotoNum = fields[0].toInteger()
                def familiaNom = fields[1].toString().trim()
                def generoNom = fields[2].toString().trim()
                def especieNom = fields[3].toString().trim()
                def linkCajas = fields[7].toString().trim()
                def color1Nom = fields[10].toString().trim()
                def color2Nom = fields[11].toString().trim()
                def formaVida1Nom = fields[12].toString().trim()
                def formaVida2Nom = fields[13].toString().trim()
                def linkTropicos = fields[19].toString().trim()

                def color1 = Color.findOrSaveByNombre(color1Nom)

                def color2 = null
                if (color2Nom && color2Nom != "") {
                    color2 = Color.findOrSaveByNombre(color2Nom)
                }

                def formaVida1 = FormaVida.findOrSaveByNombre(formaVida1Nom)

                def formaVida2
                if (formaVida2Nom && formaVida2Nom != "") {
                    formaVida2 = FormaVida.findOrSaveByNombre(formaVida2Nom)
                } else {
                    if (formaVida1Nom == "cushion") {
                        formaVida2 = FormaVida.findOrSaveByNombre("herb")
                    }
                }

                def familia = Familia.findOrSaveByNombre(familiaNom)

                def genero = Genero.findOrSaveByNombreAndFamilia(generoNom, familia)

                def especie = Especie.findOrSaveByNombreAndGenero(especieNom, genero)
                especie.color1 = color1
                if (color2) {
                    especie.color2 = color2
                }

                especie.formaVida1 = formaVida1
                if (formaVida2) {
                    especie.formaVida2 = formaVida2
                }

                especie.linkCajas = linkCajas
                especie.linkTropicos = linkTropicos

                if (!especie.save(flush: true)) {
                    println especie.errors
                }

                def paths = files[fotoNum]
                paths.each { pathFoto ->
                    def foto = Foto.findOrSaveByEspecieAndPath(especie, pathFoto)
                    foto.lugar = lugar
                    if (!foto.save(flush: true)) {
                        println foto.errors
                    }
                }
            }
            firstLine = false
        }
    }

    def import2() {
        def webapp = servletContext.getRealPath("/")
        def pathCsv = webapp + "csv/"

        def file = 'Cajas_collections.txt'
        def firstLine = true

        new File(pathCsv + file).splitEachLine("\t") { fields ->
            //0 NameID;
            //1 ContinentName;
            //2 CountryName;
            //3 UpperName;
            //4 LowerName
            //5 ;Locality;
            //6 ElevationDisplay;
            //7 Coordinate Display;
            //8 LatitudeDecimal;
            //9 LongitudeDecimal;
            //10 Description
            if (!firstLine) {
                def id = fields[0].toString().trim()
                def alts = fields[6].toString().split("-")
                alts = alts*.replaceAll("m", "")
                alts = alts*.trim()
                def lat = fields[8].toString().trim()
                def lon = fields[9].toString().trim()
                def desc = fields[10].toString().trim()

                def especie = Especie.findByLinkTropicos(id)
                if (especie) {
                    especie.descripcion = (especie.descripcion ?: "") + "; " + desc
                    if (!especie.save(flush: true)) {
                        println especie.errors
                    }
                    Foto.findAllByEspecie(especie).eachWithIndex { Foto foto, int i ->
                        if (lat != "" && lon != "" && lat != "null" && lon != "null") {
                            lat = lat.replaceAll(",", ".")
                            lon = lon.replaceAll(",", ".")
                            def alt
                            if (i < alts.size()) {
                                alt = alts[i]
                            } else {
                                alt = alts[0]
                            }
                            def altFoto = alt.toDouble()
                            def latFoto = lat.toDouble()
                            def lonFoto = lon.toDouble()

                            def coord = new Coordenada()
                            def coords = Coordenada.withCriteria {
                                eq("latitud", latFoto)
                                eq("longitud", lonFoto)
                                eq("altitud", altFoto)
                            }
                            if (coords.size() > 0) {
                                coord = coords.first()
                            } else {
                                coord.latitud = latFoto
                                coord.longitud = lonFoto
                                coord.altitud = altFoto
                                if (!coord.save(flush: true)) {
                                    println "ERROR CREANDO COORD: " + coord.errors
                                }
                            }
                            foto.coordenada = coord
//                            foto.latitud = lat
//                            foto.longitud = lon
//                            foto.altitud = alt
                            if (!foto.save(flush: true)) {
                                println foto.errors
                            }
                        }
                    }
                } else {
                    println "no hay especie con ID ${id}"
                }
            }
            firstLine = false
        }
    }

    def norm(String s) {
        def replace = [
                "á": "a",
                "é": "e",
                "ë": "e",
                "í": "i",
                "ó": "o",
                "ú": "u",
                "ñ": "n"
        ]
        replace.each { k, v ->
            s = s.replaceAll(k, v)
        }
        return s
    }

    def export() {
        def l = Lugar.list().first()
        def s = Settings.list().first()

        def insertLugar = "db.execSQL(\"INSERT INTO lugares (id, nombre, nombre_norm, path) VALUES "
        insertLugar += "(\\\"${l.id}\\\", \\\"${l.nombre}\\\", \\\"${norm(l.nombre)}\\\", \\\"${l.path}\\\");\");"

        def insertSettings = "db.execSQL(\"INSERT INTO settings (id, floraBase, tropicosBase) VALUES "
        insertSettings += "(\\\"${s.id}\\\", \\\"${s.floraBseLink}\\\", \\\"${s.tropicosBaseLink}\\\");\");"

        def insertColor = "db.execSQL(\"INSERT INTO colores(id, nombre) VALUES "
        def insertColores = ""
        Color.list().each { c ->
            insertColores += insertColor + "(\\\"${c.id}\\\", \\\"${c.nombre}\\\");"
            insertColores += "\");<br/>"
        }

        def insertFormaVida = "db.execSQL(\"INSERT INTO formas_vida(id, nombre) VALUES "
        def insertFormasVida = ""
        FormaVida.list().each { fv ->
            insertFormasVida += insertFormaVida + "(\\\"${fv.id}\\\", \\\"${fv.nombre}\\\");"
            insertFormasVida += "\");<br/>"
        }

        def insertFamilia = "db.execSQL(\"INSERT INTO familias (id, nombre, nombre_norm) VALUES "
        def insertFamilias = ""
        Familia.list().each { f ->
            insertFamilias += insertFamilia + "(\\\"${f.id}\\\", \\\"${f.nombre}\\\", \\\"${norm(f.nombre)}\\\");"
            insertFamilias += "\");<br/>"
        }

        def insertGenero = "db.execSQL(\"INSERT INTO generos (id, nombre, nombre_norm, familia_id) VALUES "
        def insertGeneros = ""
        Genero.list().each { g ->
            insertGeneros += insertGenero + "(\\\"${g.id}\\\", \\\"${g.nombre}\\\", \\\"${norm(g.nombre)}\\\", \\\"${g.familiaId}\\\");"
            insertGeneros += "\");<br/>"
        }

        def insertEspecie = "db.execSQL(\"INSERT INTO especies (id, nombre, nombre_norm, genero_id, color1_id, " +
                "color2_id, forma_vida1_id, forma_vida2_id, id_tropicos) VALUES "
        def insertEspecies = ""
        Especie.list().each { e ->
            insertEspecies += insertEspecie + "(\\\"${e.id}\\\", \\\"${e.nombre}\\\", \\\"${norm(e.nombre)}\\\", \\\"${e.generoId}\\\", \\\"${e.color1Id}\\\"," +
                    " \\\"${e.color2Id}\\\", \\\"${e.formaVida1Id}\\\", \\\"${e.formaVida2Id}\\\", \\\"${e.linkTropicos}\\\");"
            insertEspecies += "\");<br/>"
        }

        def insertCoord = "db.execSQL(\"INSERT INTO coordenadas (id, latitud, longitud, altitud) VALUES "
        def insertCoords = ""
        Coordenada.list().each { c ->
            insertCoords += insertCoord + "(\\\"${c.id}\\\", \\\"${c.latitud}\\\", \\\"${c.longitud}\\\", \\\"${c.altitud}\\\");"
            insertCoords += "\");<br/>"
        }

        def insertFoto = "db.execSQL(\"INSERT INTO fotos (id, especie_id, lugar_id, coordenada_id, path) VALUES "
        def insertFotos = ""
        Foto.list().each { f ->
            def path = f.path.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_")
            def n2 = path[2]
            if (n2.isNumber()) {
                path = path[3..path.size() - 1]
            } else {
                path = path[2..path.size() - 1]
            }
            def parts = path.split("\\.")
            def nom = parts[0]
            if ((nom[nom.size() - 1]).isNumber()) {
                def ext = parts[1]
                def cant = (nom[nom.size() - 1]).toInteger()
                nom = nom[0..nom.size() - 2]
                cant.times {
                    nom += "_"
                }
                path = nom + "." + ext
            }

            insertFotos += insertFoto + "(\\\"${f.id}\\\", \\\"${f.especieId}\\\", \\\"${f.lugarId}\\\", \\\"${f.coordenadaId}\\\", " +
                    "\\\"${path}\\\");"
            insertFotos += "\");<br/>"
        }

        render insertSettings + "<br/><br/>" + insertLugar + "<br/><br/>" + insertColores + "<br/><br/>" + insertFormasVida + "<br/><br/>" +
                insertFamilias + "<br/><br/>" + insertGeneros + "<br/><br/>" + insertCoords + "<br/><br/>" + insertEspecies + "<br/><br/>" + insertFotos
    }
}
