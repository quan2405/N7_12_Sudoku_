/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmixxx;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Administrator
 */
public interface Myremote extends Remote {
    int Tong(int a, int b) throws RemoteException;
    int Hieu(int a, int b) throws RemoteException;
}
