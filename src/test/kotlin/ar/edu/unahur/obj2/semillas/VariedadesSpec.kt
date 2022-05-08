package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe

class VariedadesSpec: DescribeSpec ({
  describe("La soja transgénica nunca da semillas"){
    val sojaT = SojaTransgenica(0.75, 2020)
    sojaT.daSemillas().shouldBeFalse()
  }
  describe("La peperina debería ocupar el doble de lo que una planta de menta ocupa"){
    val peperina = Peperina(1.0, 2021)
    peperina.espacio().shouldBe(4.0)
  }
})
