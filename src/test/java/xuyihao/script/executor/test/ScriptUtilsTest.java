package xuyihao.script.executor.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

	private static final String scriptHead = "def execute(name1, name2, name3, name4){\r";
	private static final String scriptTail = "\r}";

	@Test
	public void test() throws Exception {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/TestScript4.groovy");
		String fileContent = readFileIntoString(file);
		String script = scriptHead + fileContent + scriptTail;
		System.out.println(ScriptUtils.invokeScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				script,
				"execute",
				"Johnson ", "Leo ", "Mack ", "Mike "));
	}

	public static String readFileIntoString(File file) throws Exception {
		String content = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		char[] buffer = new char[32];
		int length;
		while ((length = bufferedReader.read(buffer)) > 0) {
			for (int i = 0; i < length; i++) {
				content += buffer[i];
			}
		}
		bufferedReader.close();
		return content;
	}

}
