package cajas

class Especie {

    Color color1
    Color color2

    String descripcion
    String descripcionEs
    String descripcionEn

    FormaVida formaVida1
    FormaVida formaVida2

    Genero genero

    String linkCajas
    String linkTropicos

    String nombre
    String nombreComun

    String autor

    static constraints = {
        color1 blank: true, nullable: true
        color2 blank: true, nullable: true
        descripcion blank: true, nullable: true, maxSize: 10239
        descripcionEs blank: true, nullable: true, maxSize: 10239
        descripcionEn blank: true, nullable: true, maxSize: 10239
        formaVida1 blank: true, nullable: true
        formaVida2 blank: true, nullable: true
        linkCajas blank: true, nullable: true
        linkTropicos blank: true, nullable: true
        nombreComun blank: true, nullable: true
        autor blank: true, nullable: true
    }

    String toString() {
        return nombre
    }
}
