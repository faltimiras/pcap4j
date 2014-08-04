/*_##########################################################################
  _##
  _##  Copyright (C) 2012-2014  Kaito Yamada
  _##
  _##########################################################################
*/

package org.pcap4j.packet.factory;

import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.Packet;

interface PacketInstantiater {

  public Packet newInstance(byte [] rawData) throws IllegalRawDataException;

  public Class<? extends Packet> getTargetClass();

}