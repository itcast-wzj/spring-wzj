package com.autowire;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @runWith是junit库里面的,所以记得项目右击添加junit5
 * 而SpringJUnit4ClassRunner是spring-test.jar中的
 * @author 王志坚
 *
 */

//这里创建了容器
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:com/autowire/person.xml"}) 绝对路径,指明在classpath下，就是有时候是/有时候是.分不清
@ContextConfiguration(locations = {"person.xml"}) //相对路径,相对于当前有@ContextConfiguration的这个类 
public class PersonTest {
	
	 
	 @Autowired
	 @Qualifier("p2")
//	 @Resource(name = "p2")
	private Person person;
	
	
	@Test
	public void fun1() {
		System.out.println(person);
	}
	
	/**
	 * 场景一: 我在容器中没有注入person,但我通过@autowired自动装配的时候,会发生如下异常(没有找到bean)
	 * org.springframework.beans.factory.NoSuchBeanDefinitionException:
	 *  No qualifying bean of type [com.instance_beans.Person] found for dependency:
	 *   expected at least 1 bean which qualifies as autowire candidate for this dependency.
	 *    Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
	 *    
	 *    然后我可以在@AutoWired(required = false)时,让他不是必须的,他就输出了null,而不是上面的异常
	 * 	   意思就是如果容器中有person这个对象,就给我引用,没有的话,就返回一个null给我 ,不要抛出异常
	 */
	
	/* 场景二: 我在容器中注入两个类型的person,但我通过@autowired自动装配的时候,会发生如下异常 
	 * 
	 * NoUniqueBeanDefinitionException: No qualifying bean of type
	 * [com.instance_beans.Person] is defined: expected single matching bean but
	 * found 2
	 * (看异常名:不是唯一的bean),这是为什么呢? 因为@AutoWired注解是根据类型来装配的,你容器中
	 * 放两个类型的person,他不知道找哪个person,所以报错
	 * 
	 * 怎么解决?在@@Autowired的基础上加上一个@Qualifier("类的name 或者 id")
	 * 意思就是如果类型一致,我就通过name或者id来区分
	 * 
	 * 还有一种就是只加一个@Resource就行
	 * 意思就是手动注入,指定注入哪个名称的对象
	 */
}
