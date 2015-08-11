package edu.pdx.cs410J.whitlock.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.whitlock.client.PhoneBill;
import edu.pdx.cs410J.whitlock.client.PhoneBillService;
import edu.pdx.cs410J.whitlock.client.PhoneCall;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PhoneBillServiceImpl extends RemoteServiceServlet implements PhoneBillService
{

  private final Map<String, PhoneBill> data = new HashMap<String, PhoneBill>();
  @Override
  public AbstractPhoneBill addCall(String customerName,PhoneCall aCall) {

    PhoneBill aBill = data.get(customerName);

    if(aBill == null){
         aBill = new PhoneBill(customerName);
    }
    aBill.addPhoneCall(aCall);

      data.put(customerName,aBill);

   // phonebill.addPhoneCall(new PhoneCall());
  //  phonebill.addPhoneCall(new PhoneCall());
    return aBill;
  }

  @Override
  public AbstractPhoneBill searchCalls(String customerName){
    PhoneBill aBill = data.get(customerName);
    if(aBill ==  null)
      return null;
    else
      return aBill;
        //return printCalls(customerName,start,end);

  }
  public String printCalls(String customer, Date startTime, Date endTime)
  {
    PhoneBill bill = this.data.get(customer);
    //  (time >= starttime and <= End Time)

    String write = "Phone Bill For: " + bill.getCustomer() + "\n"
            + "Caller Number " + "\t" + "Callee Number" + "\t" + "Start Time" + "\t\t" + "End Time" + "\t\t" + "Duration(min)";;


    PhoneCall call = null;


    for (Object o : bill.getPhoneCalls()) {

      call = (PhoneCall) o;
      if (call.getStartTime().after(startTime) || call.getStartTime().equals(startTime)) {

        if(call.getStartTime().before(endTime) || call.getStartTime().equals(endTime))
          write += "\n" + call.getCaller() + "\t" + call.getCallee() + "\t" + call.getStartTimeString()
                  + "\t" + call.getEndTimeString() + "\t" + String.valueOf(call.getDuration() + " minutes");

      }


    }


    return write;
  }





  /**
   * Log unhandled exceptions to standard error
   *
   * @param unhandled
   *        The exception that wasn't handled
   */
  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }
}
