package KioskClasses;

import com.*;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class receiptThreader extends Thread{
    public CustomerKiosk activeReceipt;


    public void SwingLoader(){

        new SwingWorker<Object, Object>(){

            String shopReceipt;

            @Override
            protected Object doInBackground() throws Exception {

                final String payTotal = "Shopping total: " + "£"
                        + String.format("%.02f", activeReceipt.finalTotal);

                System.out.println("The current thread is: " + Thread.currentThread().getName());

                activeReceipt.txtReceipt.setText("Printing Receipt");

                Thread.sleep(1000);

                activeReceipt.txtReceipt.setText("");

                shopReceipt = "Piff Tings" + "\n" + " \n" + "Store no: 4203869 " + "\n" +
                        "Contact: 01935 653245" + "\n" + "\n" + CustomerKiosk.currentReceipt
                        + "\n" + "\n" + "\n"  + payTotal + "\n" + "\n";


                activeReceipt.setTxtReceipt(shopReceipt);

                if(activeReceipt.cashOrCard == 1) {

                    shopReceipt = "Card Payment of: £" + String.format("%.02f", activeReceipt.finalTotal)
                    + "\n" + "\n" + "No Change Given";

                    activeReceipt.setTxtReceipt(shopReceipt);
                }

                if(activeReceipt.cashOrCard == 2){

                    shopReceipt = "Cash payment of: £" + String.format("%.02f", activeReceipt.payment)
                            + "\n" + "\n" + "Change Given: £" + activeReceipt.cashChange;

                    activeReceipt.setTxtReceipt(shopReceipt);
                }

                DateTimeFormatter nowTime = DateTimeFormatter.ofPattern("dd/mm/yyy | HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();


                shopReceipt = "\n "+ "\n" + "Transaction Closed"+ "\n" + nowTime.format(now);

                activeReceipt.setTxtReceipt(shopReceipt);






                return null;
            }
        }.execute();
    }
}
