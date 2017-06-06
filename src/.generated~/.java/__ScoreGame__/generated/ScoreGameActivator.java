package __ScoreGame__.generated;

import ej.wadapps.management.ActivitiesList;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.components.registry.BundleActivator;

public class ScoreGameActivator implements BundleActivator {

	com.esgi.scoregame.MainActivity com__esgi__scoregame__MainActivity;

	@Override
	public void initialize() {
		this.com__esgi__scoregame__MainActivity = new com.esgi.scoregame.MainActivity();
	}

	@Override
	public void link() {
		ActivitiesList activitieslist = ServiceLoaderFactory.getServiceLoader().getService(ActivitiesList.class);
		activitieslist.add(this.com__esgi__scoregame__MainActivity);

	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		ActivitiesList activitieslist = ServiceLoaderFactory.getServiceLoader().getService(ActivitiesList.class);
		activitieslist.remove(this.com__esgi__scoregame__MainActivity);

	}

}