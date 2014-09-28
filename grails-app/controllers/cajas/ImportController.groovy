package cajas

import groovy.io.FileType

/**
 *
 */
class ImportController {

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
//        def setts = Settings.list().first()

        def webapp = servletContext.getRealPath("/")
        def pathCsv = webapp + "csv/"
        def pathImages = webapp + "images/plants/"

        def file = 'Cajas_Luzma.csv'

        def firstLine = true

//        def files = []
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
//            files[num] = name
            if (files[num]) {
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

                def color1 = Color.findAllByNombre(color1Nom)
                if (color1.size() == 1) {
                    color1 = color1.first()
                } else if (color1.size() > 1) {
                    println "Hay ${color1.size()} colores ${color1Nom}"
                    color1 = color1.first()
                } else {
                    color1 = new Color()
                    color1.nombre = color1Nom
                    if (!color1.save(flush: true)) {
                        println color1.errors
                    }
                }

                def color2 = null
                if (color2Nom && color2Nom != "") {
                    color2 = Color.findAllByNombre(color2Nom)
                    if (color2.size() == 1) {
                        color2 = color2.first()
                    } else if (color2.size() > 1) {
                        println "Hay ${color2.size()} colores ${color2Nom}"
                        color2 = color2.first()
                    } else {
                        color2 = new Color()
                        color2.nombre = color2Nom
                        if (!color2.save(flush: true)) {
                            println color2.errors
                        }
                    }
                }

                def formaVida1 = FormaVida.findAllByNombre(formaVida1Nom)
                if (formaVida1.size() == 1) {
                    formaVida1 = formaVida1.first()
                } else if (formaVida1.size() > 1) {
                    println "Hay ${formaVida1.size()} forms de vida ${formaVida1Nom}"
                    formaVida1 = formaVida1.first()
                } else {
                    formaVida1 = new FormaVida()
                    formaVida1.nombre = formaVida1Nom
                    if (!formaVida1.save(flush: true)) {
                        println formaVida1.errors
                    }
                }

                def formaVida2
                if (formaVida2Nom && formaVida2Nom != "") {
                    formaVida2 = FormaVida.findAllByNombre(formaVida2Nom)
                    if (formaVida2.size() == 1) {
                        formaVida2 = formaVida2.first()
                    } else if (formaVida2.size() > 1) {
                        println "Hay ${formaVida2.size()} formas de vida ${formaVida2Nom}"
                        formaVida2 = formaVida2.first()
                    } else {
                        formaVida2 = new FormaVida()
                        formaVida2.nombre = formaVida2Nom
                        if (!formaVida2.save(flush: true)) {
                            println formaVida2.errors
                        }
                    }
                }

                if (formaVida1Nom == "cushion" && (!formaVida2Nom || formaVida2Nom == "")) {
                    formaVida2 = FormaVida.findAllByNombre("herb")
                    if (formaVida2.size() == 1) {
                        formaVida2 = formaVida2.first()
                    } else if (formaVida2.size() > 1) {
                        println "Hay ${formaVida2.size()} formas de vida ${formaVida2Nom}"
                        formaVida2 = formaVida2.first()
                    } else {
                        formaVida2 = new FormaVida()
                        formaVida2.nombre = formaVida2Nom
                        if (!formaVida2.save(flush: true)) {
                            println formaVida2.errors
                        }
                    }
                }

                def familia = Familia.findAllByNombre(familiaNom)
                if (familia.size() == 1) {
                    familia = familia.first()
                } else if (familia.size() > 1) {
                    println "Hay ${familia.size()} familias ${familiaNom}"
                    familia = familia.first()
                } else {
                    familia = new Familia()
                    familia.nombre = familiaNom
                    if (!familia.save(flush: true)) {
                        println familia.errors
                    }
                }

                def genero = Genero.findAllByNombreAndFamilia(generoNom, familia)
                if (genero.size() == 1) {
                    genero = genero.first()
                } else if (genero.size() > 1) {
                    println "Hay ${genero.size()} generos ${generoNom}"
                    genero = genero.first()
                } else {
                    genero = new Genero()
                    genero.nombre = generoNom
                    genero.familia = familia
                    if (!genero.save(flush: true)) {
                        println genero.errors
                    }
                }

                def especie = Especie.findAllByNombreAndGenero(especieNom, genero)
                if (especie.size() == 1) {
                    especie = especie.first()
                } else if (especie.size() > 1) {
                    println "Hay ${especie.size()} especies ${especieNom}"
                    especie = especie.first()
                } else {
                    especie = new Especie()
                    especie.nombre = especieNom
                    especie.genero = genero

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
                }

                def foto = new Foto()
                foto.especie = especie
                foto.latitud = 0
                foto.longitud = 0
                foto.lugar = lugar
                foto.path = files[fotoNum]
                if (!foto.save(flush: true)) {
                    println foto.errors
                }

            }
            firstLine = false
        }
    }

    def import2() {

        def lugar = Lugar.list().first()

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
//                fields.eachWithIndex { ff, i ->
//                    println i + "   " + ff
//                }
                def id = fields[0].toString().trim()
//                def alts1 = fields[6].toString().split("-")
//                def alts = []
//                alts1.each { alt ->
//                    def a = alt.replaceAll("m", "")
//                    a = a.trim()
//                    alts += a
//                }
                def alts = fields[6].toString().split("-")
                alts = alts*.replaceAll("m", "")
                alts = alts*.trim()
                def lat = fields[8].toString().trim()
                def lon = fields[9].toString().trim()
                def desc = fields[10].toString().trim()

//                println desc.encodeAsURL().decodeURL()
//                println desc.decodeURL()

//                if (lat != "" && lon != "" && lat != "null" && lon != "null") {
                lat = lat.replaceAll(",", ".").toDouble()
                lon = lon.replaceAll(",", ".").toDouble()
                def especie = Especie.findByLinkTropicos(id)
                if (especie) {
                    especie.descripcion = (especie.descripcion ?: "") + "; " + desc
                    if (!especie.save(flush: true)) {
                        println especie.errors
                    }
                    def foto = Foto.findAllByEspecie(especie)
                    if (foto.size() == 1) {
                        foto = foto.first()
                    } else if (foto.size() > 1) {
                        foto = new Foto()
                        foto.lugar = lugar
                        foto.especie = especie
                    } else {
                        println "no hay fotos para la especie ${especie.nombre}"
                    }
                    if (lat != "" && lon != "" && lat != "null" && lon != "null") {
                        foto.latitud = lat
                        foto.longitud = lon
                    }
                    if (!foto.save(flush: true)) {
                        println foto.errors
                    }
                } else {
                    println "no hay especie con ID ${id}"
                }
//                }
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

    def export2() {
        def l = Lugar.list().first()
        def s = Settings.list().first()

        def insertLugar = "Lugar lugar = new Lugar(context);<br/>" +
                "lugar.nombre = \\\"${l.nombre}\\\";<br/>" +
                "lugar.save();<br/>"

        def insertSett = "Settings sett = new Settings(context);<br/>" +
                "sett.floraBase = \\\"${s.floraBseLink}\\\";<br/>" +
                "sett.tropicosBase = \\\"${s.tropicosBaseLink}\\\";<br/>" +
                "sett.save();<br/>"

        def insertColores = ""
        Color.list().eachWithIndex { c, i ->
            insertColores += "Color c${i} = new Color(context);<br/>" +
                    "c${i}.nombre = \\\"${c.nombre}\\\";<br/>" +
                    "c${i}.save();<br/>"
        }

        def insertFormaVida = ""
        FormaVida.list().eachWithIndex { f, i ->
            insertFormaVida += "FormaVida f${i} = new FormaVida(context);<br/>" +
                    "f${i}.nombre = \\\"${f.nombre}\\\";<br/>" +
                    "f${i}.save();<br/>"
        }

        def insertFamilias = ""
        Familia.list().eachWithIndex { f, i ->
            insertFamilias += "Familia f${i} = new Familia(context);<br/>" +
                    "f${i}.nombre = \\\"${f.nombre}\\\";<br/>" +
                    "f${i}.nombreNorm = \\\"${norm(f.nombre)}\\\";<br/>"
        }

    }

    def export() {
        def l = Lugar.list().first()
        def s = Settings.list().first()

        def insertLugar = "db.execSQL(\"INSERT INTO lugares (id, nombre, nombre_norm) VALUES "
        insertLugar += "(\\\"${l.id}\\\", \\\"${l.nombre}\\\", \\\"${norm(l.nombre)}\\\");\");"

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

        def insertFoto = "db.execSQL(\"INSERT INTO fotos (id, latitud, longitud, especie_id, lugar_id, path) VALUES "
        def insertFotos = ""
        Foto.list().each { f ->
            def path = f.path.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_")
            path = path.replaceAll("0", "")
            path = path.replaceAll("1", "")
            path = path.replaceAll("2", "")
            path = path.replaceAll("3", "")
            path = path.replaceAll("4", "")
            path = path.replaceAll("5", "")
            path = path.replaceAll("6", "")
            path = path.replaceAll("7", "")
            path = path.replaceAll("8", "")
            path = path.replaceAll("9", "")
            insertFotos += insertFoto + "(\\\"${f.id}\\\", \\\"${f.latitud}\\\", \\\"${f.longitud}\\\", \\\"${f.especieId}\\\", \\\"${f.lugarId}\\\", \\\"${path}\\\");"
            insertFotos += "\");<br/>"
        }

        render insertSettings + "<br/><br/>" + insertLugar + "<br/><br/>" + insertColores + "<br/><br/>" + insertFormasVida + "<br/><br/>" +
                insertFamilias + "<br/><br/>" + insertGeneros + "<br/><br/>" + insertEspecies + "<br/><br/>" + insertFotos
    }
}
