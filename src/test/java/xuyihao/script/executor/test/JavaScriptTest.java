package xuyihao.script.executor.test;

import org.junit.Test;
import xuyihao.script.executor.test.entity.Car;
import xuyihao.script.executor.util.ScriptUtils;

import java.io.File;
import java.io.FileReader;

/**
 * Create by johnsonmoon at 2018/8/22 17:13.
 */
public class JavaScriptTest {
	@Test
	public void testSimple() throws Exception {
		Car car = Car.create("two-wheel-car-smart", "smart", 2);

		System.out.println(ScriptUtils.invokeFileScriptFunction(
				ScriptUtils.SCRIPT_JAVASCRIPT,
				new FileReader(new File(System.getProperty("user.dir") + "/src/test/resources/CreateCar.js")),
				"execute",
				car));
	}
}
