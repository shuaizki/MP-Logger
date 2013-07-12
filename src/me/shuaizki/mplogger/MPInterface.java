package me.shuaizki.mplogger;

import java.rmi.*;

public interface MPInterface extends Remote {
	
	public void log(String service_name, String key, String msg);
}

