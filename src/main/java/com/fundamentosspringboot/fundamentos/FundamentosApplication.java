package com.fundamentosspringboot.fundamentos;

import com.fundamentosspringboot.fundamentos.bean.MyBean;
import com.fundamentosspringboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosspringboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosspringboot.fundamentos.component.ComponentDependency;
import com.fundamentosspringboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGER = LogFactory.getLog(FundamentosApplication.class);
	@Autowired
	@Qualifier("componentTwoImplement")
	private ComponentDependency componentDependency;

	@Autowired
	@Qualifier("myBean2")
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	@Autowired
	public FundamentosApplication (@Qualifier("componentTwoImplement") ComponentDependency componentDependency,MyBean myBean,MyBeanWithDependency myBeanWithDependency,MyBeanWithProperties myBeanWithProperties,UserPojo userPojo){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println((userPojo.getEmail()+"-"+userPojo.getPassword()));
		try{
			//Error
			int value = 10 /0;
			LOGER.info("Mi valor : "+ value);
		}catch (Exception e){
		 LOGER.error("Esto es un error del aplicativo dividir por cero"+e.getStackTrace());
	}
	}
}
