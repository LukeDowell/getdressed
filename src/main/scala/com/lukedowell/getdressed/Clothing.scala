package com.lukedowell.getdressed

import com.lukedowell.getdressed.Temp._

object Clothing {
  sealed abstract class Slot
  case object Footwear extends Slot
  case object Headwear extends Slot
  case object Socks extends Slot
  case object Shirt extends Slot
  case object Jacket extends Slot
  case object Pants extends Slot

  /**
    * @return Every object that extends Slot
    */
  def getAllSlots: Set[Slot] = Set(Footwear, Headwear, Socks, Shirt, Jacket, Pants)

  /**
    * Some equipment requires other pieces of equipment to be worn prior to being worn itself. Those dependents can
    * change based on the weather. This function checks for the dependents of a given EquipmentSlot and takes weather
    * into consideration.
    *
    * @param slot The slot whose dependents we are checking
    * @param temp The temperature
    * @return A list of EquipmentSlots that must be equipped before equipping anything in the passed in slot
    */
  def getDependencies(slot: Slot, temp: Temp): Set[Slot] = (slot, temp) match {
    case (Footwear, HOT) => Set(Pants)
    case (Footwear, _) => Set(Socks, Pants)
    case (Headwear, _) => Set(Shirt)
    case (Jacket, _) => Set(Shirt)
    case _ ⇒ Set.empty
  }

  /**
    * Different temperatures call for different outfits to achieve maximum comfortableness. This function
    * takes in a temp and outputs the requisite outfit for that temp
    * @param temp The temperature to dress for
    * @return A set of Clothing.Slots
    */
  def getRequiredEquipmentForTemp(temp: Temp): Set[Slot] = temp match {
    case HOT ⇒ Set(Footwear, Headwear, Pants, Shirt)
    case COLD ⇒ Set(Footwear, Headwear, Pants, Shirt, Jacket, Socks)
  }
}