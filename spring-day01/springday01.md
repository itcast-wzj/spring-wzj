# day01_spring

## 一、spring 搭建

创建一个java Project即可,从最基础的接触；不要一上来就整什么maven

了解一下历史更新的过程

使用spring需要导入的最基础的jar包

- core.jar
- beans.jar 和 context.jar (**IOC**)
- expression(**SPEL**)
- 辅助包: commons.logging (**spring底层是使用它作为日志框架**)



## 二 、spring的IOC

之前没有使用spring的时候；我们创建对象都是自己通过new 关键字创建对象 

有了spring之后我们我们就不用管对象创建了；将他交给spring 进行管理；

spring是一个bean 容器（IOC容器）



没使用spring之前；创建对象

```java
User u1 = new User();
u1.setUsername("tom");
u1.setAge(20);
System.out.println(u1);
```

使用spring后**,有三种配置元数据的方式**，**之前虽然知道有这些东西；但脑子其实很不清晰，没有构成一个体系**

### 2.1、XML

**1.bean**

```java
public class User {
	private String username;
	private Integer age;
	
	public void init() {
		System.out.println("User……初始化");
	}
	
	public User() {
		super();
		System.out.println("User()  无参构造……");
	}
	
	public User(String username, Integer age) {
		super();
		this.username = username;
		this.age = age;
	}
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void destory() {
		System.out.println("User……销毁");
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + "]";
	}
}

```



建议全局配置命名为 applicationContext.xml(不是固定的)  和  放在classpath下 (不是必须的)

applicationContext.xml中使用

```xm
<import resource="com/xml/beans.xml"/>   来导入其他xml,以便模块化
```

**2.xml配置文件**

```xm
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
	
    <bean id="u1" class="com.xml.User" scope="singleton" init-method="init" destroy-method="destory">   
    	<property name="username" value="tom"></property>
    	<property name="age" value="20"></property>
    </bean>
</beans>
```

**3、测试**

```java
public class XmlTest {
	public static void main(String[] args) {
		//javase 
//		User u1 = new User();
//		u1.setUsername("tom");
//		u1.setAge(20);
//		System.out.println(u1);
		
		//////////////////////////
		//spring
		//1.默认是在classpath下找，如果你把他放com.xml包下，就这样指定			          com/xml/applicationContext.xml
		//2.是一个bean容器，负责生成bean,管理bean的生命周期,在加载applicationContext.xml就已经创建了bean对象  
		//3.为什么注入bean默认是单列模式? 注入的bean，通常是组件级别的对象(不用频繁创建)
		//4.ClassPathXmlApplicationContext可以加载多个xml文件
        //5.ApplicationContext是beanFactory的子接口
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");
        //测试是不是单例
		User u1 = (User)container.getBean("u1");
		User u2 = (User)container.getBean("u1");
		System.out.println(u1 == u2);
		//调用destory是classPathxmlApplicationContext,不是ApplicationContext
		container.destroy();
	}
}

```



### 2.2、Java

bean还是上面的

```java
@Configuration  //对应xxx.xml,指明他是一个配置类
public class UserConfig {
	
	@Bean(name = "u1")   //相当于xml中的bean标签，name就相当于bean标签中的name属性,这里不写name属性值默认就是方法名
	public User getUser() {
		User user = new User();
		user.setUsername("tom");
		user.setAge(20);
		return user;
	}
}
```

测试

```java
public class AnnoTest {
	public static void main(String[] args) {
		//加载AopConfig.class,相当于xml中加载applicationContext.xml
		ApplicationContext container = new AnnotationConfigApplicationContext(Userconfig.class);
		User u1 = (User)container.getBean("u1");
        //也可以以下这种方式获取user
        //User u1 = (User)container.getBean(User.class);
		System.out.println(u1);
	}
}

```

### 2.3、注解

```java
@Component
@Scope(value = "prototype")
public class User {
	/**
	 *    基本数据类型注入用 @value  (特例String也是用这个) 
	 *    引用数据类型注入用@autoWired
	 *    注解好像都是以set注入为属性赋值
	 */
	@Value("wzj")
	private String name;
	
	@Autowired
	private Car car;
	
	@PostConstruct //出生之后
	public void init() {
		System.out.println("User init()……");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@PreDestroy //死亡之前
	public void destory() {
		System.out.println("User destory()……");
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", car=" + car + "]";
	}
	
}
```

测试

```java
/**
 *	这下面两个注解相当于
 *       在applicationContext.xml中写
 *  <context:component-scan base-package=""/>
 *  @author 王志坚
 *
 */
@Configuration
@ComponentScan(value = "com.anno")
public class AnnoTest {
	public static void main(String[] args) {
		ApplicationContext ioc =  new AnnotationConfigApplicationContext(AnnoTest.class);
		User user = (User)ioc.getBean("user");
		System.out.println(user);
	}
}

```



**案列中出现的错误**

```java
NoSuchBeanDefinitionException: No qualifying bean of type [com.anno.User] is defined
		 *   如果运行代码报这个错；意思就是在容器中没有找到这个bean；就相当于没扫描进去
		  		
```

常见NoSuchBeanDefinitionException：	

参考: https://blog.csdn.net/jiangchao858/article/details/51586515

**提示：案列代码中的笔记更全**