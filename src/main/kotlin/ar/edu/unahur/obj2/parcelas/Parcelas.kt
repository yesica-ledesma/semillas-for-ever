package ar.edu.unahur.obj2.semillas

open class Parcela(var ancho:Double, var largo:Double, var horasDeSolQueRecibe:Int) {
    var plantasEnParcela= mutableListOf<Planta>()
    var superficieDeParcelas = ancho*largo

    fun cantidadDePlantasQueTolera() =
        if (ancho > largo){ superficieDeParcelas/5 }
        else{(superficieDeParcelas/3)+largo}

    //Sí si alguna de sus plantas tolera menos sol del que recibe la parcela;
    fun tieneComplicaciones()=
        plantasEnParcela.any{ it.horasDeSolToleradas < horasDeSolQueRecibe }

    fun plantarLa_(planta:Planta) {
        if(cantidadDePlantasQueTolera() > plantasEnParcela.size && planta.horasDeSolToleradas+2 >= horasDeSolQueRecibe ) {
            plantasEnParcela.add(planta)
        }
        else{
            println("Error no se puede agregar Planta, por no haber espacio o no cumple con las especificaciones")
        }
    }
    // devuelve cantidad de plantas diferentes que hay
    fun cantidadPlantas(): Int {
        val cantidad = mutableSetOf<Planta>()
        for (i in this.plantasEnParcela){
            cantidad.add(i)
        }
        return cantidad.size
    }
    open fun seAsociaA_(planta : Planta) = false
    open fun porcentajeDeAsociacion(): Double =0.0
}

class ParcelaEcologica(ancho : Double, largo : Double, horasDeSolQueRecibe : Int) : Parcela(ancho, largo ,horasDeSolQueRecibe) {
    override fun seAsociaA_(planta : Planta) = !this.tieneComplicaciones() && planta.esIdealLa(this)
    override fun porcentajeDeAsociacion():Double =
        100.0*plantasEnParcela.count { this.seAsociaA_(it) } / plantasEnParcela.size
}

class ParcelaIndustrial(ancho : Double, largo : Double, horasDeSolQueRecibe : Int) : Parcela(ancho, largo, horasDeSolQueRecibe) {
    override fun seAsociaA_(planta:Planta) = (cantidadPlantas() <= 2) and (planta.esFuerte())

}

  open fun porcentajeDeAsociacion(): Double =0.0




}
class ParcelaEcologica(ancho : Double, largo : Double, horasDeSolQueRecibe : Int) : Parcela(ancho, largo ,horasDeSolQueRecibe) {

  override fun seAsociaA_(planta : Planta) = !this.tieneComplicaciones() && planta.esIdealLa(this)

  override fun porcentajeDeAsociacion():Double =
    100.0*plantasEnParcela.count { this.seAsociaA_(it) } / plantasEnParcela.size
}

class ParcelaIndustrial(ancho : Double, largo : Double, horasDeSolQueRecibe : Int) : Parcela(ancho, largo, horasDeSolQueRecibe) {

  override fun seAsociaA_(planta:Planta) = (cantidadPlantas() <= 2) and (planta.esFuerte())

}

