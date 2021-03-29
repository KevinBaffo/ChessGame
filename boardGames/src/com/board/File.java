package com.board;

import java.util.HashMap;
import java.util.Map;

public enum File {
	A(0),
	B(1),
	C(2),
	D(3),
	E(4),
	F(5),
	G(6),
	H(7);
	
	private int value;
	public static final Map<Integer, File> BY_VALUE = new HashMap<>();
	
	static {
		for (File f : values()) {
			BY_VALUE.put(f.value, f);  
		}
	}

	public static File fileByValue(int value) {
		return BY_VALUE.get(value);
	}

	private File(final int value) {
		this.value = value;
	}

//	public int getValue() {
//		return value;
//	}	
}
