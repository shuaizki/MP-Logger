package me.shuaizki.mplogger;

import java.rmi.*;
import java.rmi.server.*;

public class MPServer extends UnicastRemoteObject implements MPInterface {

	private Logger logger;
	
	protected MPServer() throws RemoteException {
		super();
		logger = new Logger();
	}

	@Override
	public void log(String service_name, String key, String msg) {
		logger.log(service_name, key, msg);
	}

}
