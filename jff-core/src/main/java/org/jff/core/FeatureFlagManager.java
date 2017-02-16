package org.jff.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

public class FeatureFlagManager {
	private final Map<String, Class<?>> features;
	private final Map<String, FeatureSwitch> switches;
	
	public FeatureFlagManager(final Properties properties) {
		features = new HashMap<>();
		switches = new HashMap<>();
		
		Reflections reflections = new Reflections("");
		
		Set<Class<?>> flaggedClasses = reflections.getTypesAnnotatedWith(FeatureFlag.class);
		
		flaggedClasses.forEach((clazz) -> {
			FeatureFlag featureFlag = clazz.getAnnotation(FeatureFlag.class);
			String featureName = featureFlag.featureName();
			
			features.put(featureName, clazz);
		});
		
		properties.forEach((key, value) -> {
			FeatureSwitch sw = new FeatureSwitch(key.toString());
			if (Boolean.parseBoolean(value.toString())) {
				sw.enable();
			} else {
				sw.disable();
			}
			
			switches.put(sw.getName(), sw);
		});
	}

	public void runFeature(String featureName) {
		if (features.containsKey(featureName)) {
			Class<?> feature = features.get(featureName);
			
			boolean isFlaggableFeature = false;
			
			for (Class<?> theInterface : feature.getInterfaces()) {
				if (!isFlaggableFeature && theInterface.equals(FlaggableFeature.class)) {
					isFlaggableFeature = true;
				}
			}
				
			if (isFlaggableFeature) {
				try {
					FlaggableFeature ff = (FlaggableFeature)feature.newInstance();
					
					if (switches.getOrDefault(featureName, FeatureSwitch.DISABLED_SWITCH).isEnabled()) {
						ff.onEnabled();
					} else {
						ff.onDisabled();
					}
					
				} catch (InstantiationException  | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void ifEnabled(String featureName, Runnable function) {
		switches.getOrDefault(featureName, FeatureSwitch.DISABLED_SWITCH).ifEnabled(function);
	}
	
	public void ifEnabled(String featureName, Runnable enabledFunction, Runnable disabledFunction) {
		switches.getOrDefault(featureName, FeatureSwitch.DISABLED_SWITCH).ifEnabled(enabledFunction, disabledFunction);
	}
}
