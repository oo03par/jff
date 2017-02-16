package org.jff.core.flags;

import org.jff.core.FlaggableFeature;
import org.jff.core.FeatureFlag;

@FeatureFlag(featureName = "one", description="This is for feature ABC123")
public class FlagOne implements FlaggableFeature {

	@Override
	public void onEnabled() {
		System.out.println("Flag one is enabled");
	}

	@Override
	public void onDisabled() {
		System.out.println("Flag one is disabled");
	}

}
