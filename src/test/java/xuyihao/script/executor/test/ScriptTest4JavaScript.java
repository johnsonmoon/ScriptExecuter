package xuyihao.script.executor.test;

import org.junit.Test;
import xuyihao.script.executor.util.ScriptUtils;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import java.io.File;
import java.io.FileReader;

/**
 * Create by johnsonmoon at 2018/8/23 15:46.
 */
public class ScriptTest4JavaScript {
	@Test
	public void test() throws Exception {
		ScriptEngine engine = ScriptUtils.getEngine(ScriptUtils.SCRIPT_JAVASCRIPT);
		Bindings bindings = engine.createBindings();
		bindings.put("count", 10);
		Object object = engine.eval(
				new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/Script4JavaScript.js")),
				bindings);
		System.out.println(object);
	}
}
