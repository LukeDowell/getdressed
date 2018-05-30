package com.lukedowell.getdressed

import org.scalatest.FunSuite
import org.scalatest.Matchers

import scala.util.Success
import scala.util.Failure
import Commands._

class EntryPointTests extends FunSuite with Matchers {

  test("Args correctly parsed") {
    val argsToExpected = List(
      Array("HOT", "1,", "2,") → (Temp.HOT, Seq(codeToCommand(1), codeToCommand(2))),
      Array("COLD", "3,", "4,") → (Temp.COLD, Seq(codeToCommand(3), codeToCommand(4))),
      Array("COLD", "5", "6", "7", "8") → (Temp.COLD, Seq(codeToCommand(5), codeToCommand(6), codeToCommand(7), codeToCommand(8)))
    )

    argsToExpected foreach { testCase ⇒
      assert(EntryPoint.parseArgs(testCase._1) == Success(testCase._2))
    }
  }

  test("Bad input") {
    val badInput = List(
      Array("LUKEWARM", "7,", "1"),
      Array("HOT", "!,", "1"),
      Array("LUKEWARM", "9,")
    )

    badInput foreach { testCase ⇒
      EntryPoint.parseArgs(testCase) should matchPattern { case Failure(_) ⇒ }
    }
  }
}
