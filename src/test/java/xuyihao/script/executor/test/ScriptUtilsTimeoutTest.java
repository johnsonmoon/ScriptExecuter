package xuyihao.script.executor.test;

import org.junit.Test;
import xuyihao.script.executor.util.ScriptUtils;

/**
 * Created by xuyh at 2018/2/5 16:11.
 */
public class ScriptUtilsTimeoutTest {
	@Test
	public void test() {
		try {
			ScriptUtils.setWaitTime(10_000);
			System.out.println(ScriptUtils.invokeScriptFunction(
					ScriptUtils.SCRIPT_GROOVY,
					"def execute(){ " +
							"while(true){ " +
							"sleep(1000) " +
							"}" +
							"}",
					"execute"));
		} catch (Exception e) {
			if (e instanceof ScriptUtils.ScriptTimeoutException) {
				System.out.println(e.getMessage() + "  wait time: " + ((ScriptUtils.ScriptTimeoutException) e).getTimeout());
			} else {
				System.out.println(e.getMessage());
			}
		}
	}
}
