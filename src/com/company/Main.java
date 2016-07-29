package com.company;

import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;

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
