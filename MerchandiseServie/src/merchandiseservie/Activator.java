package merchandiseservie;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {


	public void start(BundleContext arg0) throws Exception {
		System.out.println("Merchandise producer Started");
	}

	public void stop(BundleContext arg0) throws Exception {
		
	}

}
