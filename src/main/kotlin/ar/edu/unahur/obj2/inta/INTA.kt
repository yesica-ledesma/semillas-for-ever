package ar.edu.unahur.obj2.semillas


object Inta {
    val parcelas = mutableListOf<Parcelas>()

    fun promedio() {
        val cantidadPlantas = parcelas.sumOf {it.plantas.size}
        val cantidadParcelas = parcelas.size

        return (cantidadParcelas == 0) {0} else {cantidadPlantas/cantidadParcelas}

    }
}