package org.jff.core;

public class FeatureSwitch {
	private final String name;
	private boolean enabled;

	public FeatureSwitch(final String name) {
		this.name = name;
		this.enabled = false;
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
