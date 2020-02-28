/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE TIME_ESTIMATE_TO_TARGET PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
        
/**
* Time/duration estimates for various events and actions given the current vehicle state and position.
*/
public class msg_time_estimate_to_target extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET = 380;
    public static final int MAVLINK_MSG_LENGTH = 20;
    private static final long serialVersionUID = MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET;


      
    /**
    * Estimated time to complete the vehicle's configured "safe return" action from its current position (e.g. RTL, Smart RTL, etc.). -1 indicates that the vehicle is landed, or that no time estimate available.
    */
    public int safe_return;
      
    /**
    * Estimated time for vehicle to complete the LAND action from its current position. -1 indicates that the vehicle is landed, or that no time estimate available.
    */
    public int land;
      
    /**
    * Estimated time for reaching/completing the currently active mission item. -1 means no time estimate available.
    */
    public int mission_next_item;
      
    /**
    * Estimated time for completing the current mission. -1 means no mission active and/or no estimate available.
    */
    public int mission_end;
      
    /**
    * Estimated time for completing the current commanded action (i.e. Go To, Takeoff, Land, etc.). -1 means no action active and/or no estimate available.
    */
    public int commanded_action;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET;
              
        packet.payload.putInt(safe_return);
              
        packet.payload.putInt(land);
              
        packet.payload.putInt(mission_next_item);
              
        packet.payload.putInt(mission_end);
              
        packet.payload.putInt(commanded_action);
        
        return packet;
    }

    /**
    * Decode a time_estimate_to_target message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
              
        this.safe_return = payload.getInt();
              
        this.land = payload.getInt();
              
        this.mission_next_item = payload.getInt();
              
        this.mission_end = payload.getInt();
              
        this.commanded_action = payload.getInt();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_time_estimate_to_target(){
        msgid = MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_time_estimate_to_target(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET;
        unpack(mavLinkPacket.payload);        
    }

              
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_TIME_ESTIMATE_TO_TARGET - sysid:"+sysid+" compid:"+compid+" safe_return:"+safe_return+" land:"+land+" mission_next_item:"+mission_next_item+" mission_end:"+mission_end+" commanded_action:"+commanded_action+"";
    }
}
        