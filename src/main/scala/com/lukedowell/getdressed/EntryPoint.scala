package com.lukedowell.getdressed

import com.lukedowell.getdressed.Commands.Command
import com.lukedowell.getdressed.Temp.Temp

import scala.util.Try
import scala.util.Success
import scala.util.Failure

object EntryPoint extends App {

  override def main(args: Array[String]): Unit = {
    val result = parseArgs(args) match {
      case Success((temp, commands)) ⇒ DressOMatic1000.dressForTemp(temp, commands)
      case Failure(_) ⇒ "fail"
    }

    println(result)
  }

  def parseArgs(args: Array[String]): Try[(Temp, Seq[Command])] = {
    Try({
      val trimmedArgs: Array[String] = args.map(arg ⇒ arg.replace(",", ""))
      val temp: Temp = Temp.withName(trimmedArgs(0).toUpperCase)

      val commands: Seq[Command] = trimmedArgs.drop(1)
        .map(codeString ⇒ Integer.parseInt(codeString))
        .map(codeInt ⇒ Commands.codeToCommand(codeInt))

      (temp, commands)
    })
  }
}
