package me.shuaizki.mplogger;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class MPServer extends UnicastRemoteObject implements MPInterface {

	private Logger logger;
	
	protected MPServer() throws RemoteException {
		super();
		logger = new Logger();
	}

	@Override
	public void log(String service_name, String key, String msg) throws RemoteException {
		logger.log(service_name, key, msg);
	}

	public static void main(String[] arg0) {
		try {
			LocateRegistry.createRegistry(8889);
			MPServer mp = new MPServer();
			Naming.bind("rmi://127.0.0.1:8889/MP", mp);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
