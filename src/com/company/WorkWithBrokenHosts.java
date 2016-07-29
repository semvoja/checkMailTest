package com.company;

import java.util.ArrayList;

/**
 * Created by operator6 on 28.07.16.
 */
public class WorkWithBrokenHosts {
    private static ArrayList<BrokenHost> listBrokenHosts=new ArrayList<>();
    public static void addBrokenHost(BrokenHost brokenHost,String status){
        int indexHost=findBrokenHost(brokenHost);
       if ((indexHost>-1)&&(status.equals("ok"))){
           //удалить из списка
           listBrokenHosts.remove(indexHost);
        } else
            if ((findBrokenHost(brokenHost)==-1)&&(!status.equals("ok"))){
            //добавить в список
                listBrokenHosts.add(brokenHost);
            }

    }

    public static int findBrokenHost(BrokenHost brokenHost){


        for (int i = 0; i < listBrokenHosts.size(); i++) {
            String currentHostAddr=listBrokenHosts.get(i).getAddress();
            String findHostAddr=brokenHost.getAddress();
            String currentHostServ=listBrokenHosts.get(i).getNameService();
            String findHostServ=brokenHost.getNameService();

            if ((currentHostAddr.equals(findHostAddr))&&(currentHostServ.equals(findHostServ))){
                return i;
            }
        }

        return -1;
    }


    public static void printArray(){
        for (BrokenHost brokenHost:listBrokenHosts){
            System.out.println(brokenHost.getDateBroken()+" "+brokenHost.getAddress()+" "+brokenHost.getIpAddress()+" "+brokenHost.getNameService());
        }
    }

    public static void main(String[] args) {
        WorkWithBrokenHosts.addBrokenHost(new BrokenHost("1","addr1","ip1","port"),"down");
        WorkWithBrokenHosts.addBrokenHost(new BrokenHost("1","addr2","ip2","port"),"down");
        WorkWithBrokenHosts.addBrokenHost(new BrokenHost("1","addr2","ip2","port"),"ok");
        WorkWithBrokenHosts.addBrokenHost(new BrokenHost("1","addr2","ip3","port"),"down");
        printArray();
    }
}
