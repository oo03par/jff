package org.jff.core;

import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.jff.TestHelpers.randomise;

public class FeatureSwitchTest {
	private String name = randomise(String.class);
	
	private FeatureSwitch unit;
	
	@Before
	public void setup() {
		this.unit = new FeatureSwitch(name);
	}
	
	@Test
	public void shouldStoreTheFeatureSwitchName() {
		assertThat(unit.getName(), is(name));
	}
	
	@Test
	public void featureShouldBeOffByDefault() {
		assertThat(unit.isOn(), is(false));
	}
	
	@Test
	public void shouldBeAbleToToggleTheSwitch() {
		assertThat(unit.isOn(), is(false));

		unit.toggle();
		assertThat(unit.isOn(), is(true));

		unit.toggle();
		assertThat(unit.isOn(), is(false));
	}
	
	@Test
	public void shouldBeAbleToSwitchOn() {
		unit.switchOn();
		assertThat(unit.isOn(), is(true));

		unit.switchOn();
		assertThat(unit.isOn(), is(true));
	}
	
	
	@Test
	public void shouldBeAbleToSwitchOff() {
		unit.switchOn();
		
		unit.switchOff();
		assertThat(unit.isOn(), is(false));

		unit.switchOff();
		assertThat(unit.isOn(), is(false));
	}
}
