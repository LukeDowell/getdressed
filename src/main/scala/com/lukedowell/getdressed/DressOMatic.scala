package com.lukedowell.getdressed

import com.lukedowell.getdressed.Commands.Command
import com.lukedowell.getdressed.Temp.Temp

trait DressOMatic {

  /**
    * Let the Dress-O-Matic take the stress and worry out of your day!
    * Just tell it a temperature and sequence of commands to
    * perform, and away you go!
    *
    * @param temp The temp to dress for
    * @param operations The sequence of commands to attempt for dressing
    * @return A string representing the outfit assembled for the given temp,
    *         or "fail" if the sequence of operations is invalid
    */
  def dressForTemp(temp: Temp, operations: Seq[Command]): String
}
