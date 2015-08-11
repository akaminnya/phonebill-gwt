package edu.pdx.cs410J.whitlock.client;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {
  private String callerNumber;
  private String calleeNumber;
  private Date startTime;
  private Date endTime;

  /**
   * @param callerNumber phone number of caller
   * @param calleeNumber phone number of callee
   * @param startTime  start time
   * @param endTime   end time
   */
  public PhoneCall(String callerNumber, String calleeNumber, Date startTime, Date endTime) {

    super();
    this.callerNumber = callerNumber;
    this.calleeNumber = calleeNumber;
    this.startTime = startTime;
    this.endTime = endTime;
  }
  /**
   * Constructor
   */
  public PhoneCall() {
  }


  /**
   * Compare the PhoneCalls
   * @param other PhoneCall
   * @return
   */
  public int compareTo(PhoneCall other) {

    int cmp = startTime.compareTo(other.startTime);

    if(cmp < 0)
      return -1;
    else if(cmp == 0){
      int comp = callerNumber.compareTo(other.callerNumber);
      if(comp <0)
        return -1;
      else
        return 1;
    }

    else
      return 1;
  }

  /**
   * Returns the phone number of the person who originated this phone
   * call.
   * @return caller number
   */
  @Override
  public String getCaller() {

    return callerNumber;
  }

  /**
   * Returns the phone number of the person who originated this phone
   * call.
   * @return callee number
   */
  @Override
  public String getCallee() {
    return calleeNumber;
  }

  /**
   *  Returns StartTime in Date
   * @return startTime
   */
  @Override
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Returns endtime in Date
   * @return EndTime Date
   */
  @Override
  public Date getEndTime() {


    return endTime;
  }



  /**
   * Returns a textual representation of the time that this phone call
   * was originated.
   * @return start time
   */
  @Override
  public String getStartTimeString() {
    // System.out.println("SDSD");
    //System.out.println(startTime);
/*
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    formatter.setLenient(false);
    String start = formatter.format(getStartTime());
    */
    return startTime.toString();
  }

  /**
   * Returns a textual representation of the time that this phone call
   * was completed.
   * @return end time
   */
  @Override
  public String getEndTimeString() {
/*
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    formatter.setLenient(false);
    String end = formatter.format(getEndTime());
        /*
        String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT).format(endTime);
        return formattedDate;
        */

    return endTime.toString();
  }

  /**
   * Function to calc the duration of phone call
   * @return Long result
   */
  public long getDuration() {
    long result = endTime.getTime() - startTime.getTime();


    return result/60000;
  }
}
