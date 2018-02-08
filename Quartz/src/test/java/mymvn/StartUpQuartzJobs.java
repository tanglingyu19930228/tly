package mymvn;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUpQuartzJobs {
	static final Log log=LogFactory.getLog(StartUpQuartzJobs.class);
	public static void main(String[] args){
		log.info("开启定时任务");
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		log.info("定时任务启动成功...");
	}

}
