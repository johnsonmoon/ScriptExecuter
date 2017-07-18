package xuyihao.script.executor.util;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Tool for executing script.
 * 
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
	public static final String SCRIPT_JAVASCRIPT = "javaScript";

	private static ScriptEngineManager engineManager = new ScriptEngineManager();

	private static Map<String, ScriptEngine> scriptEngineMap = new HashMap<>();

	/**
	 * get script engine
	 * 
	 * @param scriptLang (groovy, javaScript, etc.)
	 * @return
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
	 * @param scriptLang script language 
	 * @param script script text
	 * @param bindings variables for script program
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeScriptFunction(String scriptLang, String script, String functionName,
			Map<String, Object> bindings)
			throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		Bindings data = engine.createBindings();
		for (String key : bindings.keySet()) {
			data.put(key, bindings.get(key));
		}
		engine.eval(script, data);
		return ((Invocable) engine).invokeFunction(functionName);
	}

	/**
	 * Execute function in script text.
	 * 
	 * @param scriptLang script language 
	 * @param script script text
	 * @param args parameters for script invoking function
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeScriptFunction(String scriptLang, String script, String functionName, Object... args)
			throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		engine.eval(script);
		return ((Invocable) engine).invokeFunction(functionName, args);
	}

	/**
	 * Execute function in script text.
	 * 
	 * @param scriptLang script language 
	 * @param bindings variables for script program
	 * @param script script text
	 * @param args parameters for script invoking function
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeScriptFunction(String scriptLang, Map<String, Object> bindings, String script,
			String functionName,
			Object... args) throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		Bindings data = engine.createBindings();
		for (String key : bindings.keySet()) {
			data.put(key, bindings.get(key));
		}
		engine.eval(script, data);
		return ((Invocable) engine).invokeFunction(functionName, args);
	}

	/**
	 * Execute function in script file.
	 * 
	 * @param scriptLang script language 
	 * @param reader script file
	 * @param bindings variables for script program
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName,
			Map<String, Object> bindings)
			throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		Bindings data = engine.createBindings();
		for (String key : bindings.keySet()) {
			data.put(key, bindings.get(key));
		}
		engine.eval(reader, data);
		return ((Invocable) engine).invokeFunction(functionName);
	}

	/**
	 * Execute function in script file.
	 * 
	 * @param scriptLang script language 
	 * @param reader script file
	 * @param args parameters for script invoking function
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Reader reader, String functionName, Object... args)
			throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		engine.eval(reader);
		return ((Invocable) engine).invokeFunction(functionName, args);
	}

	/**
	 * Execute function in script file.
	 * 
	 * @param scriptLang script language 
	 * @param bindings variables for script program
	 * @param reader script file
	 * @param args parameters for script invoking function
	 * @param functionName function to invoke
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeFileScriptFunction(String scriptLang, Map<String, Object> bindings, Reader reader,
			String functionName,
			Object... args) throws ScriptException, NoSuchMethodException {
		ScriptEngine engine = getEngine(scriptLang);
		Bindings data = engine.createBindings();
		for (String key : bindings.keySet()) {
			data.put(key, bindings.get(key));
		}
		engine.eval(reader, data);
		return ((Invocable) engine).invokeFunction(functionName, args);
	}
}
