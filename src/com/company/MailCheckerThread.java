package com.company;

/**
 * Created by operator6 on 28.07.16.
 */
public class MailCheckerThread implements Runnable{
    @Override
    public void run(){
        while (true) {
            try {
                if (WorkWithMail.checkNewMail()) {
                    System.out.println("Новое сообщение!");

                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
