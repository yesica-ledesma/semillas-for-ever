package ar.edu.unahur.obj2.semillas

object Inta {
  var parcelas= mutableListOf<Parcela>()

  fun promedioDePlantasPorParcela(): Double {
    if(parcelas.size != 0){
      return (parcelas.sumOf { it.cantidadPlantas() } / parcelas.size).toDouble()
    }
    return 0.0
  }

  fun masSustentable(): Parcela? {
    val parcelaDeMasDeCuatroPlantas = parcelas.filter{ it.cantidadPlantas()>4 }
    if(parcelaDeMasDeCuatroPlantas.isEmpty()){
      throw java.lang.RuntimeException("No hay parcela sustentable")
    }
    return parcelaDeMasDeCuatroPlantas.maxByOrNull { it.porcentajeDeAsociacion() }

  }
  fun agregarPacela(parcela: Parcela) {
    parcelas.add(parcela)
  }

}
