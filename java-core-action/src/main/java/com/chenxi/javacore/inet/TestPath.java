package com.chenxi.javacore.inet;

public class TestPath {
	public static void main(String[] args) {
		System.out.println(TestPath.class.getResource(""));
		// 得到的是当前类class文件的URI目录。不包括自己！
		// 如：file：/D：/workspace/jbpmtest3/bin/com/test/

		System.out.println(TestPath.class.getResource("/"));
		// 得到的是当前的classpath的绝对URI路径 。
		// 如：file：/D：/workspace/jbpmtest3/bin/

		System.out.println(TestPath.class.getClassLoader().getResource(""));
		// 得到的也是当前ClassPath的绝对URI路径 。
		// 如：file：/D：/workspace/jbpmtest3/bin/

		System.out.println(ClassLoader.getSystemResource(""));
		// 得到的也是当前ClassPath的绝对URI路径 。
		// 如：file：/D：/workspace/jbpmtest3/bin/

		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		// 得到的也是当前ClassPath的绝对URI路径 。
		// 如：file：/D：/workspace/jbpmtest3/bin/

		// ServletActionContext.getServletContext().getRealPath(“/”) ;
		// Web应用程序
		// 中，得到Web应用程序的根目录的绝对路径。这样，我们只需要提供相对于Web应用程序根目录的路径，就可以构建出定位资源的绝对路径。
		// 如：file：/D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebProject
	}
}
