package edu.pdx.cs410J.whitlock.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.Date;

/**
 * The client-side interface to the ping service
 */
public interface PhoneBillServiceAsync {

  /**
   * Returns the a dummy Phone Bill
   */
  void addCall(String customerName,PhoneCall aCall,AsyncCallback<AbstractPhoneBill> async);



  void searchCalls(String customerName,AsyncCallback<AbstractPhoneBill> async);


}
