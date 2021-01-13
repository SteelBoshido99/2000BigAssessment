package KioskClasses;

import com.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class receiptThreader extends Thread{
    public CustomerKiosk currentPayment;


    public void SwingLoader(){

        new SwingWorker<Object, Object>(){

            String cardReceipt;

            @Override
            protected Object doInBackground() throws Exception {

                final String payTotal = "Shopping total: " + "£"
                        + String.format("%.02f", currentPayment.finalTotal);

                System.out.println("The current thread is: " + Thread.currentThread().getName());

                currentPayment.txtReceipt.setText("Printing Receipt");

                Thread.sleep(1000);

                currentPayment.txtReceipt.setText("");

                cardReceipt = "Piff Tings" + "\n" + " \n" + "Store no: 4203869 " + "\n" +
                        "Contact: 01935 653245" + "\n" + "\n" + CustomerKiosk.currentReceipt
                        + "\n" + "\n" + "\n"  + payTotal + "\n" + "\n";

                /*+ payTotal + "\n" + "\n" + "\n" + "Card Payment of: "
                        + "£" + String.format("%.02f", currentPayment.finalTotal)
                        + "\n" + "No Change Given";*/

                currentPayment.setTxtReceipt(cardReceipt);

                if(currentPayment.cashOrCard == 1) {

                    cardReceipt = "Card Payment of: £" + String.format("%.02f", currentPayment.finalTotal)
                    + "\n" + "\n" + "No Change Given";

                    currentPayment.setTxtReceipt(cardReceipt);
                }

                if(currentPayment.cashOrCard == 2){

                    cardReceipt = "Cash payment of: £" + String.format("%.02f",currentPayment.payment)
                            + "\n" + "\n" + "Change Given: £" + currentPayment.cashChange;

                    currentPayment.setTxtReceipt(cardReceipt);
                }

              /* SimpleDateFormat activeTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date now = new Date();

                final String receiptDate = (activeTime.format(now));

                cardReceipt = receiptDate;*/






                return null;
            }
        }.execute();
    }



    /*public void cashSwingLoader(){

        new SwingWorker<Object, Object>(){

            String cashPayment;

            @Override
            protected Object doInBackground() throws Exception {

                final String cashTotal = "Shopping total: " + "£ "
                        + String.format("%.02f", currentPayment.finalTotal);

               String Change = (String.format("%.02f",currentPayment.cashChange));

                System.out.println("The current thread is: " + Thread.currentThread().getName());

                currentPayment.txtReceipt.setText("Printing cash Receipt");

                //System.out.println("THIS WORKs");

                Thread.sleep(1000);

                currentPayment.txtReceipt.setText("THis is working now boi");

                cashPayment = "Store Name: Piff Tings" + "\n" + " \n" + "Store no: 4203869 " + "\n" +
                        "Contact: 01935 653245" + "\n" + "\n" + CustomerKiosk.currentReceipt
                        + "\n" + "\n" + "\n" + cashTotal + "\n" + "\n" + "\n" + "Cash Payment of: "
                        + "£" + currentPayment.txtPayment.getText()
                        + "\n" + "Change given: " + "\n" + Change;

                currentPayment.txtReceipt.setText(String.valueOf(cashPayment));
                currentPayment.setVisible(true);

                return null;
            }

        }.execute();
    }*/

}
