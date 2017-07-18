package xuyihao.script.executor.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import xuyihao.script.executor.util.ScriptUtils;

public class ScriptUtilsTest {
	@Test
	public void testInvokeScriptFunctionStringStringStringMapOfStringObject() throws Exception {
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("name", "Johnson");
		System.out.println(ScriptUtils.invokeScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				"def getName(){return \"name is :\" + name;}",
				"getName",
				bindings));
	}

	@Test
	public void testInvokeScriptFunctionStringStringStringObjectArray() throws Exception {
		System.out.println(ScriptUtils.invokeScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				"def getName(name1, name2){return name1 + name2;}",
				"getName",
				"Johnson", "Leo"));
	}

	@Test
	public void testInvokeScriptFunctionStringMapOfStringObjectStringStringObjectArray() throws Exception {
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("name", "Johnson");
		System.out.println(ScriptUtils.invokeScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				bindings,
				"def getName(name1){return name + name1;}",
				"getName",
				"Leo"));
	}

	@Test
	public void testInvokeFileScriptFunctionStringReaderStringMapOfStringObject() throws Exception {
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("name", "Johnson");
		System.out.println(ScriptUtils.invokeFileScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				new InputStreamReader(
						new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/TestScript.groovy"))),
				"getName",
				bindings));
	}

	@Test
	public void testInvokeFileScriptFunctionStringReaderStringObjectArray() throws Exception {
		System.out.println(ScriptUtils.invokeFileScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				new InputStreamReader(
						new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/TestScript2.groovy"))),
				"getName",
				"Johnson", "Leo"));
	}

	@Test
	public void testInvokeFileScriptFunctionStringMapOfStringObjectReaderStringObjectArray() throws Exception {
		Map<String, Object> bindings = new HashMap<>();
		bindings.put("name", "Johnson");
		System.out.println(ScriptUtils.invokeFileScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				bindings,
				new InputStreamReader(
						new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/TestScript3.groovy"))),
				"getName",
				"Leo"));
	}

}
