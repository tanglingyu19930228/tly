package spring.ch3.topic1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:aop.xml")
public class ChiefTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testChief(){
//      Chief jack = (Chief)applicationContext.getBean("jack");
//      jack.makeOneCake();
        Cake cake = (Cake)applicationContext.getBean("cake");
        System.out.println(cake);
        cake.sayHello();
    	
    	
    }
}
