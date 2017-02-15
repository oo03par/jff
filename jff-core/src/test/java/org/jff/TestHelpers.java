package org.jff;

import java.util.Random;

public class TestHelpers {
	private static final Random randomiser = new Random();
	
	@SuppressWarnings("unchecked")
	public static <S> S randomise(Class<S> classToRandomise) {
		if (classToRandomise.equals(Long.class) || classToRandomise.equals(long.class)) {
			return (S) (Long) randomiser.nextLong();
		} else if (classToRandomise.equals(Integer.class) || classToRandomise.equals(int.class)) {
			return (S) (Integer) randomiser.nextInt();
		} else if (classToRandomise.equals(Boolean.class) || classToRandomise.equals(boolean.class)) {
			return (S) (Boolean) randomiser.nextBoolean();
		} else if (classToRandomise.equals(Double.class) || classToRandomise.equals(double.class)) {
			return (S) (Double) randomiser.nextDouble();
		} else if (classToRandomise.equals(Float.class) || classToRandomise.equals(float.class)) {
			return (S) (Float) randomiser.nextFloat();
		} else if (classToRandomise.equals(Byte.class) || classToRandomise.equals(byte.class)) {
			return (S) (Byte)((Integer)randomiser.nextInt(Byte.MAX_VALUE)).byteValue();
		} else if (classToRandomise.equals(String.class)) {
			return (S) String.format("%h", randomiser.nextLong());
		} else if (classToRandomise.isEnum() ) {
			S[] enumConstants = classToRandomise.getEnumConstants();
			return enumConstants[randomiser.nextInt( enumConstants.length )];
		} else {
			throw new IllegalArgumentException("Unsupported Class " + classToRandomise);
		}
	}
}
