package com.depends_on;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"p.xml"})
public class AnnTest {
	
	@Autowired
	private Person person;
	
	@Test
	public void fun1() {
		System.out.println(person);
		
		/**
		 *  depends-on  与  ref的区别
		 *  
		 *  只用ref的例子
		 	因为Person依赖于Car
		 	所以person调用setter方法之前
		 	先完成car的配置！！！！
		 	
		 	这是我把p.xml中 car放在上面的效果
		 	所以bean的创建顺序是
		 	Car()...
		 	Car.setName()...
		 	Person()...
		 	Person.setName()...
		 	person.setCar()...
		 	最后默认是调用toString()
		 	Person[name=wzj,car=Car[name=宝马]]
		 	
		 	这是我把p.xml中 car放在下面的效果
		 	Person()....
			Car()……
			Car.setName()……
			Person.setName()....
			Person.setCar()...
			Person [name=wzj, car=Car [name=宝马]]
			
			如果A 依赖于  B,则在A setter之前一定完成了B的配置
			无论怎么变,在person调用set方法之前,Car已经完成配置完成了
			
			
			用 了depend-on之后,无论p.xml的位置怎么变,输出的顺序总是一样的
			博客: https://blog.csdn.net/yuanyuanasdf/article/details/22939717 也是参考官网的
			
			依赖Bean 和  被依赖Bean 的概念
			Bean A  依赖 Bean B 
			Bean A 就是依赖Bean,因为他要依赖别人吗
			Bean B 就是被依赖Bean,因为要被别人依赖吗 
			depends-on 强制说明在依赖Bean初始化之前,被依赖Bean必须初始化
			所以顺序如下
			Car()...
			Car.setName()...
			Person()..
			Person.setName()...
			person.setPerson()...
			然后 person的toString()方法
		 */
	}
}
