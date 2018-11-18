/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmixxx;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Administrator
 */
public class Tinhtoan extends UnicastRemoteObject implements Myremote{
    public Tinhtoan() throws Exception{
        super();
    } 

    @Override
    public int Tong(int a, int b) throws RemoteException {
        return a+b;
    }

    @Override
    public int Hieu(int a, int b) throws RemoteException {
       return a-b;
    }
    
}
