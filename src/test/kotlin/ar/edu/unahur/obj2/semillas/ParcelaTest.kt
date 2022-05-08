package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.shouldBeBetween
import io.kotest.matchers.shouldBe


class ParcelasTest : DescribeSpec({
  describe("La cantidad máxima de plantas que tolera una parcela"){
    val soja = Soja(0.6, 2009)
    val sojaAlta = Soja(1.0, 2009)

    val parcela= Parcela(20.00, 1.0, 10)
    val parcela1= Parcela(20.0, 1.0, 10)

    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)
    parcela.plantarLa_(sojaAlta)

    parcela1.plantarLa_(soja)

    parcela.superficieDeParcelas.shouldBe(20)

    parcela.cantidadDePlantasQueTolera().shouldBe(4)
    parcela1.cantidadDePlantasQueTolera().shouldBe(4)

    it("si alguna de sus plantas tolera menos sol del que recibe la parcela"){
      parcela.tieneComplicaciones().shouldBeFalse()
      parcela1.tieneComplicaciones().shouldBeTrue()

    }
    it("plantamos 4 plantas de soja de más de 1 metro y toleran 12 horas de sol") {
      parcela.plantasEnParcela.size.shouldBe(4)
      parcela1.plantasEnParcela.size.shouldBe(1)
      parcela.plantarLa_(soja)
      parcela.plantasEnParcela.size.shouldBe(4)
    }


  }


})
class parcelasTipo: DescribeSpec({
  describe("tipo de parcela ecologica") {
    val soja = Soja(0.6, 2009)
    val sojaAlta = Soja(1.0, 2009)

    val parcela = ParcelaEcologica(20.0, 1.0, 8)
    val parcela1 = ParcelaEcologica(20.0, 1.0, 10)

    parcela.plantarLa_(soja)
    parcela.plantarLa_(soja)
    parcela.plantarLa_(soja)
    parcela.plantarLa_(soja)

    parcela1.plantarLa_(sojaAlta)
    parcela1.plantarLa_(sojaAlta)

    parcela.seAsociaA_(soja).shouldBeTrue()
    parcela1.seAsociaA_(sojaAlta).shouldBeFalse()


    it("tipo de parcela industrial") {

      val parcela2=ParcelaIndustrial(10.00,2.00,10)
      val parcela3=ParcelaIndustrial(10.00,2.00,10)

      val menta = Menta(1.0, 2021)

      val quinoa = Quinoa(0.2, 2010)

      val sojaMedia = Soja(1.1,2006)
      val quinoaPrimavera = Quinoa(0.2, 2010)

      parcela2.plantarLa_(sojaAlta)
      parcela2.plantarLa_(sojaAlta)

      parcela2.seAsociaA_(menta).shouldBeFalse()
      parcela2.seAsociaA_(quinoa).shouldBeTrue()

      parcela3.plantarLa_(quinoa)
      parcela3.plantarLa_(sojaAlta)

      parcela3.seAsociaA_(sojaMedia).shouldBeTrue()

      parcela3.plantarLa_(sojaMedia)

      parcela3.seAsociaA_(quinoaPrimavera).shouldBeFalse()

      parcela3.cantidadPlantas().shouldBe(3)


    }
    it ("Porcentaje de asociacion a una parcelas de "){


      val soja = Soja(0.6, 2009)
      val sojaAlta = Soja(0.7, 2009)
      val sojaMedia = Soja(0.8,2006)
      val quinoaPrimavera = Quinoa(0.2, 2010)
      val peperina = Peperina(1.0, 2021)

      val parcela = ParcelaEcologica(20.00, 10.00, 8)
      val parcela1 = ParcelaEcologica(20.00, 1.00, 10)

      parcela.plantarLa_(soja)
      parcela.plantarLa_(peperina)
      parcela.plantarLa_(sojaMedia)
      parcela.plantarLa_(quinoaPrimavera)
      parcela.plantarLa_(sojaAlta)
      parcela.plantarLa_(sojaAlta)

      parcela1.plantarLa_(sojaAlta)
      parcela.plantasEnParcela.count().shouldBe(6)
      parcela.cantidadPlantas().shouldBe(5)
      parcela.seAsociaA_(soja).shouldBeFalse()
      parcela.seAsociaA_(peperina).shouldBeFalse()
    }
  }
})
