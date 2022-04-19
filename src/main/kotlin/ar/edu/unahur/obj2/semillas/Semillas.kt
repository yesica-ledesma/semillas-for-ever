package ar.edu.unahur.obj2.semillas

open class Planta(var altura: Double, val anioSemilla: Int) {
    object Constantes{
        val humbralHorasSol = 9
    }
    open fun esFuerte() = Constantes.humbralHorasSol >= 9

    open fun daSemillas()= false

    open fun espacio()= 0.0

}
open class Menta(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {
    private val horasDeSolToleradas = 7

    override fun daSemillas()= espacio() >= 1.4

    override fun esFuerte() = horasDeSolToleradas > Planta.Constantes.humbralHorasSol

    override fun espacio()= altura+1
}

open class Soja(altura: Double, anioSemilla: Int): Planta(altura , anioSemilla)  {
    private fun horasDeSolToleradas() =
        if (altura < 0.5) {
            6
        }
        else if(altura < 1){
            8
        }
        else {
            6
        }
    override fun espacio()= altura/2


    override fun daSemillas()= anioSemilla > 2007 && altura > 0.75 && altura < 0.9



    override fun esFuerte() = horasDeSolToleradas() > Planta.Constantes.humbralHorasSol
}
class Quinoa (altura: Double, anioSemilla: Int): Planta(altura , anioSemilla) {
    fun horasDeSolToleradas() =
        if (espacio() < 0.3) {10}
        else {9}

    override fun esFuerte()= horasDeSolToleradas() == 10

    override fun daSemillas()= anioSemilla in 2002..2008 || esFuerte()

    override fun espacio()= altura
}
class Peperina(altura: Double, anioSemilla: Int): Menta(altura , anioSemilla) {
    override fun espacio()= super.espacio() * 2
}
class SojaTransgénica(altura: Double, anioSemilla: Int): Soja(altura , anioSemilla) {
    override fun daSemillas()= false
}
class Parcela(var ancho:Int, var largo:Int, var horasDeSolQueRecibe:Int) {

  var plantasEnParcela= mutableListOf<Planta>()

  var superficieDeParcelas = ancho*largo

  fun cantidadDePlantasQueTolera() =
    if (ancho > largo){ superficieDeParcelas/5 }
    else{(superficieDeParcelas/3)+largo}

  //Sí si alguna de sus plantas tolera menos sol del que recibe la parcela;
  fun tieneComplicaciones()=
    plantasEnParcela.any{ it.horasDeSolToleradas <= horasDeSolQueRecibe }

  fun plantarLa_(planta:Planta) {
    if(cantidadDePlantasQueTolera() > plantasEnParcela.size && planta.horasDeSolToleradas+2 >= horasDeSolQueRecibe ) {
      plantasEnParcela.add(planta)
    }
    else{
      println("Error no se puede agregar Planta, por no haber espacio o no cumple con las especificaciones")
    }

  }

