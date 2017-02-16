package org.jff.core;

public class FeatureSwitch {
	public static final FeatureSwitch DISABLED_SWITCH = new FeatureSwitch("disabled", false);
	public static final FeatureSwitch ENABLED_SWITCH = new FeatureSwitch("enabled", true);
	
	private final String name;
	private boolean enabled;

	public FeatureSwitch(final String name) {
		this(name, false);
	}
	
	private FeatureSwitch(final String name, final boolean enabled) {
		this.name = name;
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void toggle() {
		this.enabled = !this.enabled;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public void ifEnabled(Runnable function) {
		if (this.enabled) {
			function.run();
		}
	}

	public void ifEnabled(Runnable whenOn, Runnable whenOff) {
		if (this.enabled) {
			whenOn.run();
		} else {
			whenOff.run();
		}
	}
}
