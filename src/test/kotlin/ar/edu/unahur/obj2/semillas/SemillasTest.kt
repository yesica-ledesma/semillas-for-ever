package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val quinoa = Quinoa(0.2, 2010)
        val quinoata = Quinoa(0.9, 2006)
        val peperina = Peperina(1.0, 2021)
        val sojaT = SojaTransgénica(1.0, 2021)


        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            quinoa.daSemillas().shouldBeTrue()
            quinoata.daSemillas().shouldBeTrue()
            sojaT.daSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
            peperina.espacio().shouldBe(4.0)

        }
        it("horas que toleran"){
            soja.horasDeSolToleradas.shouldBe(6)
            sojaMedia.horasDeSolToleradas.shouldBe(8)
            sojaAlta.horasDeSolToleradas.shouldBe(12)
        }

        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            Math.ceil(superficie).shouldBe(4)
        }
        it("Cada planta define ciertas condiciones para saber si una parcela le resulta ideal"){
            val parcela= Parcela(20, 1, 10)
            val parcela1= Parcela(5, 1, 10)

            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)
            parcela1.plantarLa_(sojaT)

            menta.esIdealLa_(parcela).shouldBeTrue()

            quinoa.esIdealLa_(parcela).shouldBeTrue()
            quinoata.esIdealLa_(parcela1).shouldBeFalse()

            soja.esIdealLa_(parcela).shouldBeFalse()

            sojaT.esIdealLa_(parcela1).shouldBeTrue()
            sojaT.esIdealLa_(parcela).shouldBeFalse()

        }
    }
})

class VariedadesSpec: DescribeSpec ({
    describe("La soja transgénica nunca da semillas"){
        val sojaT = SojaTransgénica(0.75, 2020)
        sojaT.daSemillas().shouldBeFalse()
    }
    describe("La peperina debería ocupar el doble de lo que una planta de menta ocupa"){
        val peperina = Peperina(1.0, 2021)
        peperina.espacio().shouldBe(4.0)
    }
})

class ParcelasTest : DescribeSpec({
    describe("La cantidad máxima de plantas que tolera una parcela"){
        val soja = Soja(0.6, 2009)
        val sojaAlta = Soja(1.0, 2009)
        val parcela= Parcela(20, 1, 10)
        val parcela1= Parcela(20, 1, 10)

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

class ParcelaIdealTest: DescribeSpec ({
    describre ("Parcela ideal para x planta")

    val parce1 = Parcela (20.0, 1.0, 10)
    val parce2 = Parcela (10.0, 5.0, 15)
    val parce3 = Parcela (5.0, 1, 8)
    val menta = Menta (1.0, 2020)
    val soja = Soja (0.6, 2010)
    val quinoa = Quinoa (0.2,2010,0.2)
    val peperina = Peperina (1.0,2021)
    val sojaTransgenica = SojaTransgenica (1.0, 2021)

    it("Parcela ideal para Menta") {
        menta.esIdealLa_(parce1).shouldBeTrue()
        menta.esIdealLa_(parce2).shouldBeTrue()
        menta.esIdealLa_(parce3).shouldBeFalse()
    }

    it("Parcela ideal para Soja") {
        soja.esIdealLa_(parce1).shouldBeFalse()
        soja.esIdealLa_(parce2).shouldBeFalse()
        soja.esIdealLa_(parce3).shouldBeTrue()
    }

    it("Parcela ideal para Quinoa") {
        quinoa.esIdealLa_(parce1).shouldBeTrue()
        quinoa.esIdealLa_(parce2).shouldBeTrue()
        quinoa.esIdealLa_(parce3).shouldBeTrue()
    }

    it("Parcela ideal para Peperina") {
        peperina.esIdealLa_(parce1).shouldBeTrue()
        peperina.esIdealLa_(parce2).shouldBeTrue()
        peperina.esIdealLa_(parce3).shouldBeFalse()
    }
    it("Parcela ideal para Soja Transgenica") {
        sojaTransgenica.esIdealLa_(parce1).shouldBeFalse()
        sojaTransgenica.esIdealLa_(parce2).shouldBeFalse()
        sojaTransgenica.esIdealLa_(parce3).shouldBeTrue()
    }
})
