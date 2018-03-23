package com.chenxi.springiocaop.aop.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Proxy {
	/**
	 * 
	 * @param infce 被代理类的接口
	 * @param handler 代理类 
	 * @return
	 * @throws Exception
	 */
	public static Object newProxyInstance(Class<?> infce, InvocationHandler handler) throws Exception {  
		String methodStr = "";
		String rt = "\r\n";
		String className = "$" + infce.getSimpleName()+"Proxy";
		String packageName = Proxy.class.getPackage().getName();
		String classFullName = packageName + "." + className;
		String fileName =  className + ".java";
		
		try {
			Class.forName(classFullName);
		} catch (ClassNotFoundException e) {
			// 生成代理的重写方法代码
			Method[] methods = infce.getMethods();
			for (Method method : methods) {
				methodStr +="    @Override" + rt +   
	                    	"    public "+method.getReturnType()+" " + method.getName() + "(){" + rt +  
	                    	"        try {" + rt +  
	                    	"        	Method md = " + infce.getName() + ".class.getMethod(\"" + method.getName() + "\");" + rt +  
	                    	"        	handler.invoke(this, md);" + rt +  
	                    	"        }catch(Exception e) {e.printStackTrace();}" + rt +                          
	                    	"    }" + rt ;
				
			}
			
			//生成Java源文件  
	        String srcCode =   
	            "package "+ packageName + ";" +  rt +  
	            "import java.lang.reflect.Method;" + rt +  
	            "public class " + className + " implements " + infce.getName() + "{" + rt +  
	            "    " + handler.getClass().getPackage().getName() + ".InvocationHandler handler;" + rt +                           
	            "    public " + className + "(InvocationHandler handler) {" + rt +  
	            "        this.handler = handler;" + rt +  
	            "    }" + rt +            
	            	 methodStr + rt +  
	            "}";  
	        String filePath = infce.getResource("").getPath() + fileName;
	        File file = new File(filePath);
	        FileWriter fw = new FileWriter(file);
	        fw.write(srcCode);
	        fw.flush();
	        fw.close();
	        
	        // 将Java文件编译成class文件 
	        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
	        Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(filePath);
	        CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
	        t.call();
	        fileManager.close();
		}
		
        return loadObject(infce, handler, classFullName);
	}
	
	public static Object loadObject(Class<?> infce, InvocationHandler handler, String classFullName) throws Exception{
		 // 加载生成的代理类
        URL[] urls = new URL[] {new URL("file:/" + infce.getResource("/").getPath())};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> clazz = ul.loadClass(classFullName);
        ul.close();
        
        // 获取代理类的构造器
        Constructor<?> ctr = clazz.getConstructor(InvocationHandler.class);  
        // 得到代理对象，代理对象执行InvocationHandler的invoke操作
        Object m = ctr.newInstance(handler);  
        return m;
	}
}
