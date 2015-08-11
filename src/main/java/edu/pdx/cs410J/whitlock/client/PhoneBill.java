package edu.pdx.cs410J.whitlock.client;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class PhoneBill extends AbstractPhoneBill
{
  private String customerName;
  private List<AbstractPhoneCall> myList = new ArrayList<AbstractPhoneCall>();

  public PhoneBill(String customerName) {
    this.customerName = customerName;

  }

  public PhoneBill() {
  }

  @Override
  public String getCustomer() {
    return this.customerName;
  }

  @Override
  public void addPhoneCall(AbstractPhoneCall call) {
    this.myList.add(call);
    Collections.sort(myList,new callComp());

  }

  @Override
  public Collection getPhoneCalls() {
    return this.myList;
  }

  /**
   * Class compare each Phone Call and sort them
   */
  class callComp implements Comparator<AbstractPhoneCall> {

    @Override
    public int compare(AbstractPhoneCall call1, AbstractPhoneCall call2) {
      PhoneCall aCall1 = (PhoneCall) call1;
      PhoneCall aCall2 = (PhoneCall) call2;
      if (aCall1.compareTo(aCall2) == -1)
        return -1;
      else
        return 1;

    }
  }



}
