package org.jff.core.flags;

import org.jff.core.FeatureFlag;
import org.jff.core.FlaggableFeature;

@FeatureFlag(featureName = "two", description = "This is flag two")
public class FlagTwo implements FlaggableFeature {

	@Override
	public void onEnabled() {
		System.out.println("Flag two is enabled");
	}

	@Override
	public void onDisabled() {
		System.out.println("Flag two is disabled");
	}

}
