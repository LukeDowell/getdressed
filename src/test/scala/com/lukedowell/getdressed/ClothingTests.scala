package com.lukedowell.getdressed

import org.scalatest.FunSuite
import com.lukedowell.getdressed.Clothing._
import com.lukedowell.getdressed.Temp._

class ClothingTests extends FunSuite {

  test("Socks must be put on before footwear") {
    assert(Clothing.getDependencies(Footwear, COLD).contains(Socks))
  }

  test("Socks don't need to be put on before footwear when it's hot") {
    assert(!Clothing.getDependencies(Footwear, HOT).contains(Socks))
  }

  test("Pants must be put on before footwear always") {
    assert(Clothing.getDependencies(Footwear, HOT).contains(Pants))
    assert(Clothing.getDependencies(Footwear, COLD).contains(Pants))
  }

  test("The shirt must be put on before headwear or jacket") {
    assert(Clothing.getDependencies(Headwear, HOT).contains(Shirt))
    assert(Clothing.getDependencies(Headwear, COLD).contains(Shirt))
  }

  test("The shirt must be put on before jacket") {
    assert(Clothing.getDependencies(Jacket, HOT).contains(Shirt))
  }
}
