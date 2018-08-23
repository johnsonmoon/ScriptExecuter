package xuyihao.script.executor.test;

import org.junit.Test;
import xuyihao.script.executor.util.ScriptUtils;

import javax.script.ScriptEngine;
import java.io.File;
import java.io.FileReader;

/**
 * Create by johnsonmoon at 2018/8/23 15:47.
 */
public class ScriptTest4Groovy {
	@Test
	public void test() throws Exception {
		ScriptEngine engine = ScriptUtils.getEngine(ScriptUtils.SCRIPT_GROOVY);
		Object object = engine
				.eval(new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/Script4Groovy.groovy")));
		System.out.println(object);
	}
}
