/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmixxx;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class Server {
    public static void main(String[] args) throws RemoteException, Exception {
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://192.168.1.16/tinhtoan", new Tinhtoan());
         
        
    }
}
