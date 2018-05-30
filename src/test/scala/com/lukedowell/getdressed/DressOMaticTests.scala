package com.lukedowell.getdressed

import org.scalatest.FunSuite
import Commands._

class DressOMaticTests extends FunSuite {

  val dressOMatic: DressOMatic = DressOMatic1000

  val FootwearCode = 1
  val HeadwearCode = 2
  val SockCode = 3
  val ShirtCode = 4
  val JacketCode = 5
  val PantsCode = 6
  val LeaveCode = 7
  val RemovePJCode = 8

  test("Only 1 piece of each type of clothing may be put on") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(codeToCommand(RemovePJCode), codeToCommand(ShirtCode), codeToCommand(ShirtCode))
    )
    assert(result.contains("fail"))
  }

  test("You cannot put on socks when it is hot") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(codeToCommand(RemovePJCode), codeToCommand(SockCode))
    )
    assert(result.contains("fail"))
  }

  test("You cannot put on a jacket when it is hot") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(codeToCommand(RemovePJCode), codeToCommand(JacketCode))
    )
    assert(result.contains("fail"))
  }

  test("You cannot leave the house until all items of clothing are on") {
    val result = dressOMatic.dressForTemp(
      Temp.COLD,
      Seq(codeToCommand(RemovePJCode), codeToCommand(ShirtCode), codeToCommand(PantsCode), codeToCommand(LeaveCode))
    )
    assert(result.contains("fail"))
  }

  test("Successful output for INPUT: HOT 8, 6, 4, 2, 1, 7") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(
        codeToCommand(RemovePJCode),
        codeToCommand(PantsCode),
        codeToCommand(ShirtCode),
        codeToCommand(HeadwearCode),
        codeToCommand(FootwearCode),
        codeToCommand(LeaveCode)
      )
    )

    assert(result == "Removing PJs, shorts, shirt, sunglasses, sandals, leaving house")
  }

  test("Successful output for INPUT: COLD 8, 6, 3, 4, 2, 5, 1, 7") {
    val result = dressOMatic.dressForTemp(
      Temp.COLD,
      Seq(
        codeToCommand(RemovePJCode),
        codeToCommand(PantsCode),
        codeToCommand(SockCode),
        codeToCommand(ShirtCode),
        codeToCommand(HeadwearCode),
        codeToCommand(JacketCode),
        codeToCommand(FootwearCode),
        codeToCommand(LeaveCode)
      )
    )

    assert(result == "Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house")
  }

  test("Expected output for failure INPUT: HOT 8, 6, 6") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(
        codeToCommand(RemovePJCode),
        codeToCommand(PantsCode),
        codeToCommand(PantsCode)
      )
    )

    assert(result == "Removing PJs, shorts, fail")
  }

  test("Expected output for failure INPUT: HOT 8, 6, 3") {
    val result = dressOMatic.dressForTemp(
      Temp.HOT,
      Seq(
        codeToCommand(RemovePJCode),
        codeToCommand(PantsCode),
        codeToCommand(SockCode)
      )
    )

    assert(result == "Removing PJs, shorts, fail")
  }

  test("Expected output for failure INPUT: COLD 8, 6, 3, 4, 2, 5, 7") {
    val result = dressOMatic.dressForTemp(
      Temp.COLD,
      Seq(
        codeToCommand(RemovePJCode),
        codeToCommand(PantsCode),
        codeToCommand(SockCode),
        codeToCommand(ShirtCode),
        codeToCommand(HeadwearCode),
        codeToCommand(JacketCode),
        codeToCommand(LeaveCode)
      )
    )

    assert(result == "Removing PJs, pants, socks, shirt, hat, jacket, fail")
  }

  test("Expected output for failure INPUT: COLD 6") {
    val result = dressOMatic.dressForTemp(
      Temp.COLD,
      Seq(codeToCommand(PantsCode))
    )

    assert(result == "fail")
  }
}
