package com.company;



public class Main {






    public static void main(String args[]) throws Exception {
        WorkWithMail.setUserName("");
        WorkWithMail.setPassword("");
        WorkWithMail.setReceivingHost("imap.gmail.com");
        WorkWithMail.loadFirstData();
        MailCheckerThread thread=new MailCheckerThread();
        new Thread(thread).start();

    }
}
