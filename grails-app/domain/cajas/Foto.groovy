package cajas

class Foto {

    Especie especie
    Coordenada coordenada
    Lugar lugar
    String path

    static constraints = {
        lugar blank: true, nullable: true
        coordenada blank: true, nullable: true
        path blank: true, nullable: true
    }

    String toString() {
        return path
    }
}
