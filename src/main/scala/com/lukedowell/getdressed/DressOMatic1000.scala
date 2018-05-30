package com.lukedowell.getdressed

import com.lukedowell.getdressed.Clothing.Slot
import com.lukedowell.getdressed.Commands._
import com.lukedowell.getdressed.Temp.Temp

object DressOMatic1000 extends DressOMatic {
  override def dressForTemp(temp: Temp, commands: Seq[Command]): String = {

    def process(commandsToProcess: Seq[Command], processedCommands: Seq[Command]): String = {

      def getWornSlots: Set[Slot] = processedCommands.filter(c ⇒ c.isInstanceOf[ClothingCommand])
        .map(c ⇒ c.asInstanceOf[ClothingCommand])
        .map(c ⇒ c.slot)
        .toSet

      def commandsToDescription = if (processedCommands.nonEmpty) processedCommands.map(c ⇒ c.tempToDescription(temp)).mkString("", ", ", ", ") else ""
      def success: String = s"${commandsToDescription}leaving house"
      def fail: String = s"${commandsToDescription}fail"

      commandsToProcess.head match {
        case RemovePajamasCommand ⇒
          if (processedCommands.nonEmpty) fail
          else process(commandsToProcess.tail, processedCommands :+ RemovePajamasCommand)

        case LeaveHouseCommand ⇒
          if (Clothing.getRequiredEquipmentForTemp(temp).subsetOf(getWornSlots)) success
          else fail

        case ClothingCommand(slot, tempToDescription) ⇒
          if (!processedCommands.contains(RemovePajamasCommand)) fail
          else if (tempToDescription.get(temp).isEmpty) fail
          else if (getWornSlots.contains(slot)) fail
          else if (Clothing.getDependencies(slot, temp).nonEmpty && !Clothing.getDependencies(slot, temp).subsetOf(getWornSlots)) fail
          else process(commandsToProcess.tail, processedCommands :+ commandsToProcess.head)
      }
    }

    process(commands, Seq.empty)
  }
}
