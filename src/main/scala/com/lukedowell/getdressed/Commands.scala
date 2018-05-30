package com.lukedowell.getdressed

import com.lukedowell.getdressed.Clothing.Shirt
import com.lukedowell.getdressed.Clothing.Socks
import com.lukedowell.getdressed.Clothing.Pants
import com.lukedowell.getdressed.Clothing.Jacket
import com.lukedowell.getdressed.Clothing.Footwear
import com.lukedowell.getdressed.Clothing.Headwear
import com.lukedowell.getdressed.Clothing.Slot
import com.lukedowell.getdressed.Temp._

object Commands {
  sealed trait Command {
    val tempToDescription: Map[Temp, String]
  }

  /**
    * The very first command that must be received in order to dress correctly
    */
  case object RemovePajamasCommand extends Command {
    override val tempToDescription = Map(HOT → "Removing PJs", COLD → "Removing PJs")
  }

  /**
    * The last command that must be received in order to leave the house correctly
    */
  case object LeaveHouseCommand extends Command {
    override val tempToDescription = Map(HOT → "leaving house", COLD → "leaving house")
  }

  /**
    * A command
    * @param slot The slot this operation is related to
    * @param tempToDescription A mapping of temperatures to pieces of clothing
    */
  case class ClothingCommand(slot: Slot, tempToDescription: Map[Temp, String]) extends Command

  val codeToCommand: Map[Int, Command] = Map(
    1 → ClothingCommand(slot = Footwear, tempToDescription = Map(HOT → "sandals", COLD → "boots")),
    2 → ClothingCommand(slot = Headwear, tempToDescription = Map(HOT → "sunglasses", COLD → "hat")),
    3 → ClothingCommand(slot = Socks, tempToDescription = Map(COLD → "socks")),
    4 → ClothingCommand(slot = Shirt, tempToDescription = Map(HOT → "shirt", COLD → "shirt")),
    5 → ClothingCommand(slot = Jacket, tempToDescription = Map(COLD → "jacket")),
    6 → ClothingCommand(slot = Pants, tempToDescription = Map(HOT → "shorts", COLD → "pants")),
    7 → LeaveHouseCommand,
    8 → RemovePajamasCommand
  )
}
