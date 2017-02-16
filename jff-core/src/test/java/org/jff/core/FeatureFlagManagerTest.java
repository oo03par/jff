package org.jff.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import org.junit.Test;

public class FeatureFlagManagerTest {
	@Test
	public void testAllFeatureFlags() {
		Properties properties = new Properties();
		
		properties.setProperty("one", "true");
		properties.setProperty("two", "false");
		properties.setProperty("three", "true");
		
		FeatureFlagManager manager = new FeatureFlagManager(properties);
		
		System.out.println("Running feature one");
		manager.runFeature("one");
		
		System.out.println("Running feature two");
		manager.runFeature("two");
		
		System.out.println("Running feature three");
		manager.runFeature("three");
		
		System.out.println("Running feature four");
		manager.runFeature("four");

		System.out.println("Running feature five");
		manager.runFeature("five");
	}
	
	@Test
	public void testAllFeatureFlagsWithRunnable()  {
		Properties properties = new Properties();
		
		properties.setProperty("a", "true");
		properties.setProperty("b", "false");
		properties.setProperty("c", "true");
		properties.setProperty("d", "false");
		
		FeatureFlagManager manager = new FeatureFlagManager(properties);
		
		Runnable aFunction = mock(Runnable.class);
		Runnable bFunction = mock(Runnable.class);
		Runnable cFunction1 = mock(Runnable.class);
		Runnable cFunction2 = mock(Runnable.class);
		Runnable dFunction1 = mock(Runnable.class);
		Runnable dFunction2 = mock(Runnable.class);
		Runnable eFunction = mock(Runnable.class);

		manager.ifEnabled("a", aFunction);
		manager.ifEnabled("b", bFunction);
		manager.ifEnabled("c", cFunction1, cFunction2);
		manager.ifEnabled("d", dFunction1, dFunction2);
		manager.ifEnabled("e", eFunction);
		
		verify(aFunction).run();
		verify(bFunction, never()).run();
		verify(cFunction1).run();
		verify(cFunction2, never()).run();
		verify(dFunction1, never()).run();
		verify(dFunction2).run();
		verify(eFunction, never()).run();
	}
}
