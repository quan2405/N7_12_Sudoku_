/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmixxx;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Client {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        System.setSecurityManager(new RMISecurityManager());
        Myremote rmi = (Myremote) Naming.lookup("rmi://192.168.1.16/tinhtoan");
        System.out.println("Nhap so a:");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println("Nhap so b:");
        int b =sc.nextInt();
        System.out.println("Tong 2 so: " + rmi.Tong(a, b));
        System.out.println("Hieu 2 so: " + rmi.Hieu(a, b));
    }
}
