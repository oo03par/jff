package org.jff.core;

public class FeatureSwitch {
	private final String name;
	private boolean switchState;

	public FeatureSwitch(final String name) {
		this.name = name;
		this.switchState = false;
	}

	public String getName() {
		return this.name;
	}

	public boolean isOn() {
		return this.switchState;
	}

	public void toggle() {
		this.switchState = !this.switchState;
	}

	public void switchOn() {
		this.switchState = true;
	}

	public void switchOff() {
		this.switchState = false;
	}

	public void ifOn(Runnable function) {
		if (this.switchState) {
			function.run();
		}
	}

	public void ifOn(Runnable whenOn, Runnable whenOff) {
		if (this.switchState) {
			whenOn.run();
		} else {
			whenOff.run();
		}
	}
}
