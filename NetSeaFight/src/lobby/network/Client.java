/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby.network;

import java.io.*;
import java.net.*;

public class Client {
    
  public static void main(String[] args) throws IOException {
    System.out.println("Клиент стартовал");
    Socket server = null;
    String address = "localhost";
    //  адрес (имя) сервера должны передаваться как параметр
    //if (args.length==0) {
    //  System.out.println("Использование: java Client hostname");
    //  System.exit(-1);
    //}
    
System.out.println("Соединяемся с сервером "+address);

server = new Socket(address,6666);
BufferedReader in  = new BufferedReader(
   new  InputStreamReader(server.getInputStream()));
PrintWriter out =
   new PrintWriter(server.getOutputStream(),true);
BufferedReader inu =
   new BufferedReader(new InputStreamReader(System.in));

String fuser,fserver;

while ((fuser = inu.readLine())!=null) {
   out.println(fuser);
   fserver = in.readLine();
   System.out.println(fserver);
   if (fuser.equalsIgnoreCase("close"))
     break;
   if (fuser.equalsIgnoreCase("exit"))
     break;
 }
    out.close();
    in.close();
    inu.close();
    server.close();
  }
}