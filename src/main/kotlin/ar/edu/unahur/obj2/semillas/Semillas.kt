package ar.edu.unahur.obj2.semillas

open class Planta(var altura: Double, val anioSemilla: Int) {
    //Una planta es fuerte si tolera más de 9 horas de sol al día
    object constanteDeHorasDesol{
        const val horas= 9
    }
    //las plantas toleran 7 horas de sol por día, esto cambia para algunos tipos de planta
    open val horasDeSolToleradas: Int = 7

    open fun esFuerte() = horasDeSolToleradas > constanteDeHorasDesol.horas

    open fun daSemillas()= false

    open fun espacio()= 0.0

    open fun esIdealLa(parcela: Parcela)= false

}
open class Menta(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {

    override fun esIdealLa(parcela: Parcela)= parcela.superficieDeParcelas > 6

    override fun daSemillas()= espacio() >= 1.4

    override fun espacio()= altura+1
}

open class Soja(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla)  {
    override var horasDeSolToleradas= horasDeSolToleradas()
    private fun horasDeSolToleradas() =
        if (altura < 0.5) {
            6
        }
        else if(altura < 1){
            8
        }
        else {
            12
        }
    override fun esIdealLa(parcela: Parcela)= parcela.horasDeSolQueRecibe == horasDeSolToleradas

    override fun espacio()= altura/2

    override fun daSemillas()= anioSemilla > 2007 && altura > 0.75 && altura < 0.9

    override fun esFuerte() = horasDeSolToleradas > constanteDeHorasDesol.horas
}
class Quinoa (altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {
    override var horasDeSolToleradas= horasDeSolToleradas()
    private fun horasDeSolToleradas() =
        if (espacio() < 0.3) {10}
        else {super.horasDeSolToleradas}

    override fun esIdealLa(parcela: Parcela)= parcela.plantasEnParcela.all{ it.altura < 1.5}

    override fun esFuerte()= horasDeSolToleradas() > constanteDeHorasDesol.horas

    override fun daSemillas()= anioSemilla in 2002..2008 || esFuerte()

    override fun espacio()= altura
}
class Peperina(altura: Double, anioSemilla: Int): Menta(altura , anioSemilla) {
    override fun espacio()= super.espacio() * 2
    override fun esIdealLa(parcela: Parcela)= parcela.superficieDeParcelas > 6
}
class SojaTransgenica(altura: Double, anioSemilla: Int): Soja(altura , anioSemilla) {
    override fun daSemillas()= false
    override fun esIdealLa(parcela: Parcela)= parcela.cantidadDePlantasQueTolera()== 1.00
}
