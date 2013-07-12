package me.shuaizki.mplogger;

import java.rmi.*;
import java.rmi.server.*;

public class MPServer extends UnicastRemoteObject implements MPInterface {

	protected MPServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log(String service_name, String key, String msg) {
	}

}
