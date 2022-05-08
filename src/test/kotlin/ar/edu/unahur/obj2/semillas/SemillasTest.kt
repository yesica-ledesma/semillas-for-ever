package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
//class parcelaTest : DescribeSpec({
// hay una clase Parcela que tiene como atributos
// ancho, largo, horasDelSol, listaPlantas
//    describe("Creación de las parcelas") {
//        val parcela = Parcela
class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.3, 2009)
        val sojaMedia = Soja(0.8, 2009)
        val sojaAlta = Soja(1.0, 2009)
        val quinoa = Quinoa(0.2, 2010)
        val quinoata = Quinoa(0.9, 2006)
        val sojaT = SojaTransgenica(1.6,2006)




        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()

            soja.daSemillas().shouldBeFalse()
            sojaMedia.daSemillas().shouldBeTrue()
            sojaAlta.daSemillas().shouldBeFalse()

            quinoa.daSemillas().shouldBeTrue()
            quinoata.daSemillas().shouldBeTrue()

        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            sojaMedia.esFuerte().shouldBeFalse()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            sojaMedia.espacio().shouldBe(0.4)


        }
        it("horas que toleran"){
            soja.horasDeSolToleradas.shouldBe(6)
            sojaMedia.horasDeSolToleradas.shouldBe(8)
            sojaAlta.horasDeSolToleradas.shouldBe(12)
            quinoa.horasDeSolToleradas.shouldBe(10)
            quinoata.horasDeSolToleradas.shouldBe(7)
        }

        it("Cada planta define ciertas condiciones para saber si una parcela le resulta ideal"){
            val parcela= Parcela(20.00, 1.0, 10)
            val parcela1= Parcela(5.0, 1.0, 10)

            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)
            parcela.plantarLa_(sojaAlta)


            parcela1.plantarLa_(sojaT)

            menta.esIdealLa(parcela).shouldBeTrue()

            quinoa.esIdealLa(parcela).shouldBeTrue()
            quinoata.esIdealLa(parcela1).shouldBeFalse()

            soja.esIdealLa(parcela).shouldBeFalse()

            sojaT.esIdealLa(parcela1).shouldBeTrue()
            sojaT.esIdealLa(parcela).shouldBeFalse()

        }

    }
})

