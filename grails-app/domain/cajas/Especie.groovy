package cajas

class Especie {

    Color color1
    Color color2

    String descripcion

    FormaVida formaVida1
    FormaVida formaVida2

    Genero genero

    String linkCajas
    String linkTropicos

    String nombre
    String nombreComun

    static constraints = {
        color2 blank: true, nullable: true
        formaVida2 blank: true, nullable: true
        nombreComun blank: true, nullable: true

        descripcion blank: true, nullable: true, maxSize: 10239
    }

    String toString() {
        return nombre
    }
}
