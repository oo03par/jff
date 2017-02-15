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
		assertThat(unit.isEnabled(), is(false));
	}
	
	@Test
	public void shouldBeAbleToToggleTheSwitch() {
		assertThat(unit.isEnabled(), is(false));

		unit.toggle();
		assertThat(unit.isEnabled(), is(true));

		unit.toggle();
		assertThat(unit.isEnabled(), is(false));
	}
	
	@Test
	public void shouldBeAbleToSwitchOn() {
		unit.enable();
		assertThat(unit.isEnabled(), is(true));

		unit.enable();
		assertThat(unit.isEnabled(), is(true));
	}
	
	
	@Test
	public void shouldBeAbleToSwitchOff() {
		unit.enable();
		
		unit.disable();
		assertThat(unit.isEnabled(), is(false));

		unit.disable();
		assertThat(unit.isEnabled(), is(false));
	}
	
	@Test
	public void shouldRunFunctionalCodeIfSwitchIsOn() {
		Runnable runnable = mock(Runnable.class);
		
		unit.enable();
		
		unit.ifEnabled(runnable);
		
		verify(runnable).run();
	}
	
	@Test
	public void shouldNotRunFunctionalCodeIfSwitchIsOff() {
		Runnable runnable = mock(Runnable.class);
		
		unit.disable();
		
		unit.ifEnabled(runnable);
		
		verify(runnable, never()).run();
	}
	
	@Test
	public void shouldRunFunctionalCodeIfSwitchIsOnAndNotRunOffCode() {
		Runnable whenOn = mock(Runnable.class);
		Runnable whenOff = mock(Runnable.class);
		
		unit.enable();
		
		unit.ifEnabled(whenOn, whenOff);
		
		verify(whenOn).run();
		verify(whenOff, never()).run();
	}
	
	
	@Test
	public void shouldNotRunFunctionalCodeIfSwitchIsOnAndDoRunOffCode() {
		Runnable whenOn = mock(Runnable.class);
		Runnable whenOff = mock(Runnable.class);
		
		unit.disable();
		
		unit.ifEnabled(whenOn, whenOff);
		
		verify(whenOn, never()).run();
		verify(whenOff).run();
	}
}
