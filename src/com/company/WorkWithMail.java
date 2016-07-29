package com.company;

import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by operator6 on 28.07.16.
 */
public class WorkWithMail {
    final static int day=24*60*60*1000;
    private static String userName;
    private static String password;
    private static int countMessages=0;
    private static String receivingHost;


    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String user) {
        userName = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String pass) {
        password = pass;
    }


    public static String getReceivingHost() {
        return receivingHost;
    }

    public static void setReceivingHost(String Host) {
        receivingHost = Host;
    }

    public static void loadFirstData(){
      //  receivingHost = "imap.gmail.com";//for imap protocol

        Properties props2 = System.getProperties();

        props2.setProperty("mail.store.protocol", "imaps");
        // I used imaps protocol here

        Session session2 = Session.getDefaultInstance(props2, null);

        try {

            Store store = session2.getStore("imaps");

            store.connect(receivingHost, userName, password);

            Folder folder = store.getFolder("INBOX");//get inbox

            folder.open(Folder.READ_ONLY);//open folder only to read

            countMessages=folder.getMessageCount();

            SimpleDateFormat df1 = new SimpleDateFormat( "MM/dd/yy" );
            Date today=new Date();
            String firstDate=df1.format(new Date(today.getTime()-day));//"07/27/16";
            String secondDate=df1.format(new Date(today.getTime()+day));//;"07/29/16";
            java.util.Date dFirst = df1.parse(firstDate);
            java.util.Date dSecond = df1.parse(secondDate);

            SearchTerm olderThan = new ReceivedDateTerm(ReceivedDateTerm.LT,dSecond );
            SearchTerm newerThan = new ReceivedDateTerm(ReceivedDateTerm.GT,dFirst );
            SearchTerm andTerm = new AndTerm(olderThan, newerThan);
            SearchTerm and2term=new AndTerm(andTerm, new FlagTerm(new Flags(
                    Flags.Flag.SEEN), false));
            Message[] messages = folder.search(andTerm);
            System.out.println("filter finished!");
            SimpleDateFormat df2 = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );

            for (Message mess:messages){
                String textMess=new String(mess.getContent().toString().getBytes("iso-8859-1"),"UTF-8");
          //      System.out.println(textMess);
                WorkWithMail.parseData(textMess,df2.format(mess.getSentDate()));
            }
            WorkWithBrokenHosts.printArray();
            folder.close(true);
            store.close();


        } catch (Exception e) {

            System.out.println(e.toString());

        }

    }

    public static boolean checkNewMail(){
        receivingHost = "imap.gmail.com";//for imap protocol
        Properties props2 = System.getProperties();
        props2.setProperty("mail.store.protocol", "imaps");
        Session session2 = Session.getDefaultInstance(props2, null);
        try {

            Store store = session2.getStore("imaps");
            store.connect(receivingHost, userName, password);
            Folder folder = store.getFolder("INBOX");//get inbox
            folder.open(Folder.READ_ONLY);//open folder only to read
            int count=folder.getMessageCount();
            if (count>countMessages) {
                countMessages=count;
                folder.close(true);
                store.close();
                return true;
            }
            folder.close(true);
            store.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }



    public static void parseData(String rawMessage,String sendDate){
        if (rawMessage.contains("Service")) {
            int indexOn=rawMessage.indexOf("on");
           // int indexSk=address.indexOf("(")

            String address =rawMessage.substring(indexOn);
            address=address.substring(address.indexOf("(")+1,address.indexOf(",")).trim();
            String serviceName = rawMessage.substring(rawMessage.indexOf("Service") + 8, rawMessage.indexOf("on")).trim();
            rawMessage=rawMessage.substring(rawMessage.indexOf("on"),rawMessage.length());
            String ipAddress = rawMessage.substring(rawMessage.indexOf("on") + 3, rawMessage.indexOf("(")).trim();
            String status = "";
            if (rawMessage.contains("down")) {
                status = "down";
            } else if (rawMessage.contains("ok")) {
                status = "ok";
            } else if (rawMessage.contains("таймаут")) {
                status = "таймаут";
            }

            WorkWithBrokenHosts.addBrokenHost(new BrokenHost(sendDate, ipAddress, address, serviceName), status);
        }
    }
    //обработка добавления в массив
    //поиск в массиве
    //добавление и удаление из массива
    //нить для проверки новой почты
    //загрузка данных за день(только при старте)
    //сохранение и загрузка id письма в файл

    /*
    * ул. Цимлянская, 15 Service Port26 on 10.250.0.220 (ул. Цимлянская 15, Упр. комм. DES-3200-28F (24SFP) на ул. Цимлянская, 15
SN: R3MT1CB000274) is now не работает ( down)
    * */
    public static void main(String[] args) {

    }


}
