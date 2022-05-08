package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeBetween

import io.kotest.matchers.shouldBe

class IntaTest: DescribeSpec( {
  describe(" realizar una serie de estadísticas sobre todas las parcelas del país") {

    it("Promedio de 0 parcelas"){
          Inta.promedioDePlantasPorParcela().shouldBe(0)

    }
    val soja = Soja(0.6, 2009)
    val sojaAlta = Soja(1.0, 2009)
    val sojaMedia = Soja(1.1,2006)
    val quinoaPrimavera = Quinoa(0.2, 2010)

    val parcela= ParcelaIndustrial(20.00, 1.0, 10)
    val parcela1= ParcelaEcologica(20.00, 1.0, 10)

    parcela1.plantarLa_(soja)
    parcela.plantarLa_(sojaMedia)
    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)

    Inta.agregarPacela(parcela)
    Inta.agregarPacela(parcela1)

    it("Promedio aproximado de parcelas"){
      Inta.promedioDePlantasPorParcela().shouldBeBetween(1.0,2.0,0.1)

    }

    parcela.plantarLa_(quinoaPrimavera)




    it("cantidad de parcelas inscriptas en el Inta "){
      Inta.parcelas.size.shouldBe(2.0)
    }
    it("cantidad de plantas de todas las parcelas inscriptas"){
      Inta.parcelas.sumOf { it.cantidadPlantas() }.shouldBe(4)
    }
    it("Promedio de dos parcelas"){
      Inta.promedioDePlantasPorParcela().shouldBe(2)
    }
  }
  describe("La parcela mas sustentable"){
    Inta.parcelas.clear()
    it("Si no hay ninguna parcela sustentable"){
     // Inta.promedioDePlantasPorParcela().shouldBe(0)
     // Inta.masSustentable().shouldBe(0.0)
    }
    it("Varias parcelas con mas de cuatro plantas"){
      val soja = Soja(0.6, 2009)
      val sojaAlta = Soja(1.0, 2009)
      val sojaMedia = Soja(1.1,2006)
      val quinoaPrimavera = Quinoa(0.2, 2010)
      val peperina = Peperina(1.0, 2021)

      val parcela = ParcelaEcologica(20.00, 10.00, 8)
      val parcela1 = ParcelaEcologica(20.0, 1.0, 10)

      parcela.plantarLa_(soja)
      parcela.plantarLa_(peperina)
      parcela.plantarLa_(sojaMedia)
      parcela.plantarLa_(quinoaPrimavera)
      parcela.plantarLa_(sojaAlta)
      parcela.plantarLa_(sojaAlta)

      parcela1.plantarLa_(sojaAlta)

      Inta.agregarPacela(parcela)

     // Inta.masSustentable().shouldBe(100.0)

    }
  }
})
