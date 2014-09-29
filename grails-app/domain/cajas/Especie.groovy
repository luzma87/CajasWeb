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
        color1 blank: true, nullable: true
        color2 blank: true, nullable: true
        descripcion blank: true, nullable: true, maxSize: 10239
        formaVida1 blank: true, nullable: true
        formaVida2 blank: true, nullable: true
        linkCajas blank: true, nullable: true
        linkTropicos blank: true, nullable: true
        nombreComun blank: true, nullable: true
    }

    String toString() {
        return nombre
    }
}
