package com.chenxi.javacore.jvm;

import java.lang.reflect.Field;

public class ClassLoaderTest {
	public static void main(String[] args) {
		testClassLoader();
	}

	/**
	 * 测试反射的方法
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static void getProperty() {

		// 1、得到Class对象
		Class<Person> cls = Person.class;

		Field[] fields = cls.getDeclaredFields();
		// 2、得到Class对象
		Object obj = new Person();
		Class<? extends Object> cls2 = obj.getClass();
		// 3、得到Class对象
		try {
			Class<?> cls3 = (Class<Person>) Class.forName("Person");

			// 获得类实例
			Object obj1 = cls3.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testClassLoader() {

		// 获取一个系统类加载器
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println("系统加载器：" + classLoader);

		// 获取系统类加载器的父类的加载器
		ClassLoader parentLoader = classLoader.getParent();
		System.out.println("系统加载器的父类加载器：" + classLoader);

		// 获取扩展类加载器的父类加载器
		classLoader = parentLoader.getParent();
		System.out.println("扩展类加载器的父类加载器：" + classLoader);

		// 检测当前类是由哪个类加载器加载的
		try {
			ClassLoader classLoader2 = Class.forName("Person").getClassLoader();
			System.out.println(classLoader2);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 检测JDK 提供的Object类由哪个类加载器负责加载的
		ClassLoader classLoader3;
		try {
			classLoader3 = Class.forName("java.lang.Object").getClassLoader();
			System.out.println(classLoader3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 关于类加载器的一个重要方法：
		// InputStream in = null;
		// in = ClassLoaderTest.class.getResourceAsStream("xxxx.txt");
	}

}

/**
 * 反射--被测试类： Created by Jason 2016-7-16 下午2:19:40
 */
class Person {
	String name;
	private int age;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
