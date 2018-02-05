package xuyihao.script.executor.util;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Tool for executing script.
 * <p>
 * Created by xuyh at 2017年7月18日 上午11:05:49.
 */
public class ScriptUtils {
	/**
	 * script language groovy
	 */
	public static final String SCRIPT_GROOVY = "groovy";
	/**
	 * script language javascript
	 */
	public static final String SCRIPT_JAVASCRIPT = "javascript";

	private static ScriptEngineManager engineManager = new ScriptEngineManager();

	private static Map<String, ScriptEngine> scriptEngineMap = new HashMap<>();

	/**
	 * Time for wait script running. Stop running script while over time.
	 */
	private static int waitTime = 90 * 1000;

	/**
	 * Set wait time for script running.
	 * <p>
	 * <pre>
	 * 		default 90 000 milliseconds
	 * 	    Script running over time, force stopping the script thread.
	 * 	</pre>
	 *
	 * @param waitTime unit: milliseconds
	 */
	public static void setWaitTime(int waitTime) {
		ScriptUtils.waitTime = waitTime;
	}

	/**
	 * get script engine
	 *
	 * @param scriptLang (groovy, javascript, etc.)
	 *                   {@link ScriptUtils#SCRIPT_GROOVY}
	 *                   {@link ScriptUtils#SCRIPT_JAVASCRIPT}
	 * @return scriptEngine instance
	 */
	public static ScriptEngine getEngine(String scriptLang) {
		ScriptEngine engine = scriptEngineMap.get(scriptLang);
		if (engine == null) {
			engine = engineManager.getEngineByName(scriptLang);
			scriptEngineMap.put(scriptLang, engine);
		}
		return engine;
	}

	/**
	 * Execute function in script text.
	 *
	 * @param scriptLang   script language
	 * @param script       script text
	 * @param bindings     variables for script program
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeScriptFunction(String scriptLang, String script, String functionName,
			Map<String, Object> bindings)
			throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					Bindings data = engine.createBindings();
					for (Map.Entry<String, Object> entry : bindings.entrySet()) {
						data.put(entry.getKey(), entry.getValue());
					}
					engine.eval(script, data);
					map.put("value", ((Invocable) engine).invokeFunction(functionName));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Execute function in script text.
	 *
	 * @param scriptLang   script language
	 * @param script       script text
	 * @param args         parameters for script invoking function
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeScriptFunction(String scriptLang, String script, String functionName, Object... args)
			throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					engine.eval(script);
					map.put("value", ((Invocable) engine).invokeFunction(functionName, args));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Execute function in script text.
	 *
	 * @param scriptLang   script language
	 * @param bindings     variables for script program
	 * @param script       script text
	 * @param args         parameters for script invoking function
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeScriptFunction(String scriptLang, Map<String, Object> bindings, String script,
			String functionName,
			Object... args) throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					Bindings data = engine.createBindings();
					for (Map.Entry<String, Object> entry : bindings.entrySet()) {
						data.put(entry.getKey(), entry.getValue());
					}
					engine.eval(script, data);
					map.put("value", ((Invocable) engine).invokeFunction(functionName, args));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Execute function in script file.
	 *
	 * @param scriptLang   script language
	 * @param reader       script file
	 * @param bindings     variables for script program
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName,
			Map<String, Object> bindings)
			throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					Bindings data = engine.createBindings();
					for (Map.Entry<String, Object> entry : bindings.entrySet()) {
						data.put(entry.getKey(), entry.getValue());
					}
					engine.eval(reader, data);
					map.put("value", ((Invocable) engine).invokeFunction(functionName));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Execute function in script file.
	 *
	 * @param scriptLang   script language
	 * @param reader       script file
	 * @param args         parameters for script invoking function
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName, Object... args)
			throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					engine.eval(reader);
					map.put("value", ((Invocable) engine).invokeFunction(functionName, args));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Execute function in script file.
	 *
	 * @param scriptLang   script language
	 * @param bindings     variables for script program
	 * @param reader       script file
	 * @param args         parameters for script invoking function
	 * @param functionName function to invoke
	 * @throws Exception exception
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Map<String, Object> bindings, Reader reader,
			String functionName,
			Object... args) throws Exception {
		final Map<String, Object> map = new HashMap<>();

		ScriptThread scriptThread = new ScriptThread() {
			@Override
			public void execute() {
				try {
					ScriptEngine engine = getEngine(scriptLang);
					if (engine == null)
						throw new Exception(String.format("Script engine not get! No support for script [%s].", scriptLang));
					Bindings data = engine.createBindings();
					for (Map.Entry<String, Object> entry : bindings.entrySet()) {
						data.put(entry.getKey(), entry.getValue());
					}
					engine.eval(reader, data);
					map.put("value", ((Invocable) engine).invokeFunction(functionName, args));
				} catch (Exception e) {
					map.put("exception", e);
				}
			}
		};
		scriptThread.start();

		int result = waitScriptRunning(scriptThread);
		if (result == 2) {
			throw new ScriptTimeoutException(waitTime);
		}

		Object o = map.get("exception");
		if (o != null) {
			throw (Exception) o;
		}
		return map.get("value");
	}

	/**
	 * Script running thread.
	 */
	private static abstract class ScriptThread extends Thread {
		private boolean done = false;

		boolean isDone() {
			return done;
		}

		@Override
		public void run() {
			execute();
			this.done = true;
		}

		public abstract void execute();
	}

	/**
	 * Block and wait for script thread running until thread end or time out. {@link ScriptUtils#waitTime}
	 * <p>
	 * <pre>
	 *     Script thread running over wait time, force stopping thread.
	 * </pre>
	 *
	 * @param task script running thread
	 * @return 1:thread end normally 2:force stopping thread running 0:else situation
	 */
	@SuppressWarnings("deprecation")
	private static int waitScriptRunning(ScriptThread task) {
		int result = 0;
		long start = System.currentTimeMillis();
		while (true) {
			if (task.isDone()) {
				result = 1;
				break;
			}
			long current = System.currentTimeMillis();
			if (current - start >= waitTime) {
				if (!task.isDone()) {
					result = 2;
					task.stop();
				}
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Exception class for script running time out.
	 */
	public static class ScriptTimeoutException extends Exception {
		private static final long serialVersionUID = 1L;
		private int timeout;

		public ScriptTimeoutException() {
			super("Script execute timeout.");
		}

		public ScriptTimeoutException(int timeout) {
			super("Script execute timeout.");
			this.timeout = timeout;
		}

		public int getTimeout() {
			return timeout;
		}

		public void setTimeout(int timeout) {
			this.timeout = timeout;
		}
	}
}
