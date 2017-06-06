package __ScoreGame__.generated;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleRegistry;
import ej.wadapps.management.ActivitiesScheduler;
import ej.wadapps.registry.SharedRegistryFactory;

public class MainActivityStandalone {

	public static void main(String[] args) {
		SharedRegistryFactory.getSharedRegistry().register(BundleRegistry.class, new StandaloneRegistry());

		// Start entry point.
		new ScoreGameEntryPoint().start();

		// MainActivity
		ActivitiesScheduler activitiesScheduler = ServiceLoaderFactory.getServiceLoader()
				.getService(ActivitiesScheduler.class);
		com.esgi.scoregame.MainActivity activity = new com.esgi.scoregame.MainActivity();
		activitiesScheduler.show(activity);
	}

}