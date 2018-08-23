package xuyihao.script.executor.util;

import java.util.HashMap;

/**
 * Create by johnsonmoon at 2018/8/23 15:07.
 */
public class Bindings extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public Bindings() {
	}

	public Bindings(String key, Object object) {
		this.put(key, object);
	}

	public static Bindings create() {
		return new Bindings();
	}

	public static Bindings create(String key, Object object) {
		return new Bindings(key, object);
	}

	public Bindings add(String key, String object) {
		this.put(key, object);
		return this;
	}
}
