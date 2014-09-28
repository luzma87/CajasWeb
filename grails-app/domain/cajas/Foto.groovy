package cajas

class Foto {

    Especie especie

    Double latitud
    Double longitud

    Lugar lugar

    String path

    static constraints = {

        path blank: true, nullable: true

    }

    String toString() {
        return path
    }
}
