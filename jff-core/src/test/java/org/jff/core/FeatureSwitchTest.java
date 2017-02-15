package org.jff.core;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.jff.TestHelpers.randomise;

public class FeatureSwitchTest {
	private String name = randomise(String.class);
	
	@Test
	public void shouldStoreTheFeatureSwitchName() {
		FeatureSwitch s = new FeatureSwitch(name);
		
		assertThat(s.getName(), is(name));
	}
}
