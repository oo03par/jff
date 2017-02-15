package org.jff.core;

import static org.hamcrest.CoreMatchers.is;
import static org.jff.TestHelpers.randomise;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void shouldRunFunctionalCodeIfSwitchIsOn() {
		Runnable runnable = mock(Runnable.class);
		
		unit.switchOn();
		
		unit.ifOn(runnable);
		
		verify(runnable).run();
	}
	
	@Test
	public void shouldNotRunFunctionalCodeIfSwitchIsOff() {
		Runnable runnable = mock(Runnable.class);
		
		unit.switchOff();
		
		unit.ifOn(runnable);
		
		verify(runnable, never()).run();
	}
	
	@Test
	public void shouldRunFunctionalCodeIfSwitchIsOnAndNotRunOffCode() {
		Runnable whenOn = mock(Runnable.class);
		Runnable whenOff = mock(Runnable.class);
		
		unit.switchOn();
		
		unit.ifOn(whenOn, whenOff);
		
		verify(whenOn).run();
		verify(whenOff, never()).run();
	}
	
	
	@Test
	public void shouldNotRunFunctionalCodeIfSwitchIsOnAndDoRunOffCode() {
		Runnable whenOn = mock(Runnable.class);
		Runnable whenOff = mock(Runnable.class);
		
		unit.switchOff();
		
		unit.ifOn(whenOn, whenOff);
		
		verify(whenOn, never()).run();
		verify(whenOff).run();
	}
}
