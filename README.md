# ScriptExecuter
Using Java script engine to run script language program like Groovy, JavaScript, etc.

## How to use
1. Clone the project.
```
git clone https://github.com/johnsonmoon/ScriptExecuter.git
```

2. Using maven to build a jar file.
```
mvn install -Dmaven.test.skip=true
```

3. Include the jar file into your project classPath or add dependency code below to the .pom file, like this:
```
<dependency>
	<groupId>xuyihao</groupId>
	<artifactId>script-executor</artifactId>
	<version>1.0</version>
	<scope>system</scope>
	<systemPath>${basedir}/src/main/lib/script-executor-1.0.jar</systemPath>
</dependency>
``` 

4. Writing your code like this:
```
Map<String, Object> bindings = new HashMap<>();
bindings.put("name", "Johnson");
System.out.println(ScriptUtils.invokeScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				"def getName(){return \"name is :\" + name;}",
				"getName",
				bindings));
```

or like this:
```
System.out.println(ScriptUtils.invokeFileScriptFunction(
				ScriptUtils.SCRIPT_GROOVY,
				new InputStreamReader(
						new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/TestScript2.groovy"))),
				"getName",
				"Johnson", "Leo"));
```

then you will find the result like this:
```
name is :Johnson
JohnsonLeo
```

## version changes information

### 1.1.0
NUM | DESCRIPTION | DATE
--- | --- | ---
1 | Add script running time out judgement, while script running time out, force stopping the script thread. | 2018-02-05 