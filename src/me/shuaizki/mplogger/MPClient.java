package me.shuaizki.mplogger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MPClient {
	String svc_name;
	MPInterface mpservice;
	
	public MPClient(String svc_name) {
		try {
			mpservice = (MPInterface) Naming.lookup("rmi://localhost:8889/MP");
			this.svc_name = svc_name;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String key, String msg)
	{
		try {
			mpservice.log(svc_name, key, msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
