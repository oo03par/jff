package org.jff.core.flags;

import org.jff.core.FeatureFlag;
import org.jff.core.FlaggableFeature;

@FeatureFlag(featureName = "four", description = "This will not have a flag in the properties, so will be off")
public class FlagFour implements FlaggableFeature {

	@Override
	public void onEnabled() {
		System.out.println("Flag four is enabled");
	}

	@Override
	public void onDisabled() {
		System.out.println("Flag four is disabled");
	}

}
