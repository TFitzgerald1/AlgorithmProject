import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Server {
	public static void main(String args[]) {
		try {
			javaRMI obj = new Algorithm();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900"+"/geeksforgeeks",obj);
				
//			javaRMI stub = (javaRMI) UnicastRemoteObject.exportObject(obj, 0);
//			Registry registry = LocateRegistry.getRegistry();
//			registry.bind("javaRMI", stub);
		} catch (Exception E) {
			System.err.println("Server exception: " + E.toString()); 
	        E.printStackTrace(); 
		}
	}
}
