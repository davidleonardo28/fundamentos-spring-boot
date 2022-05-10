package com.fundamentosspringboot.fundamentos;

import ch.qos.logback.classic.Logger;
import com.fundamentosspringboot.fundamentos.bean.MyBean;
import com.fundamentosspringboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosspringboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosspringboot.fundamentos.component.ComponentDependency;
import com.fundamentosspringboot.fundamentos.entity.User;
import com.fundamentosspringboot.fundamentos.pojo.UserPojo;
import com.fundamentosspringboot.fundamentos.repository.UserRepository;
import com.fundamentosspringboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
	private UserRepository userRepository;

	private UserService userService;

	@Autowired
	public FundamentosApplication (@Qualifier("componentTwoImplement")
									   ComponentDependency componentDependency,
								   MyBean myBean,MyBeanWithDependency myBeanWithDependency,
								   MyBeanWithProperties myBeanWithProperties,
								   UserPojo userPojo,
								   UserRepository userRepository,
								   UserService userService){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
		this.userService=userService;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUserInDb();
		//getInformationJpqFromUser();
		saveWithErrorTransactional();
	}

	private  void saveWithErrorTransactional(){
		User test1 = new User("Test1Transactional","Test1Transactional@domain.com",LocalDate.now());
		User test2 = new User("Test2Transactional","Test2Transactional@domain.com",LocalDate.now());
		User test3 = new User("Test3Transactional","Test1Transactional@domain.com",LocalDate.now());
		User test4 = new User("Test4Transactional","Test4Transactional@domain.com",LocalDate.now());
		List<User> users = Arrays.asList(test1,test2,test3,test4);
	try {
		userService.saveTransactional(users);
	}catch (Exception e){
		LOGER.error("Esta es una exception dentro del metodo Transaccional"+e);

	}
		userService.getAllUsers().stream().
				forEach(user -> LOGER.info("Este es el usuario dentro del metodo transcaccional"+user));
	}
	private void getInformationJpqFromUser(){

		LOGER.info("Usuario con el metodo findByUserEmail"+
				userRepository.findByUserEmail("Eimy@domain.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el Usuario")));

		userRepository.findAndSort("David", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGER.info("Usuario con metodo sort"+ user));

		userRepository.findByName("Alejandra")
				.stream()
				.forEach(user -> LOGER.info("Usuario con query method "+ user.toString()));

		LOGER.info("Usuario con query method findByEmailAndName"+userRepository.findByEmailAndName(
				"Kevin@domain.com","Kevin")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%D%")
				.stream()
				.forEach(user -> LOGER.info("Usuario findByNameLike"+user));

		userRepository.findByNameOrEmail
						(null,"Felipe@domain.com")
				.stream()
				.forEach(user -> LOGER.info("Usuario findByNameOrEmail"+user));

		/*
		userRepository.findByBirthDateBetween(
				LocalDate.of(2021,3,20),
				LocalDate.of(2021,12,7))
						.stream()
						.forEach(user -> LOGER.info("Usuario con intervalo de fechas  findByBirthDateBetween "+user));
*/
/*
		LOGER.info("El usuario a patir del named parameter es : "+userRepository.getAllByBirthDatAndEmail(LocalDate.of(2022,02,11),
				"Carlos@domain.com"
						).orElseThrow(()->
				new RuntimeException("No se encontro el usuario a partir del named parameter")));
*/
	}
	private void saveUserInDb(){
		User user1 = new User("Leonardo","Leo@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("Alejandra","Aleja@domain.com", LocalDate.of(2020,8,18));
		User user3 = new User("Eimy","Eimy@domain.com", LocalDate.of(2018,01,22));
		User user4 = new User("David","David@domain.com", LocalDate.of(2022,04,25));
		User user5 = new User("Carlos","Carlos@domain.com", LocalDate.of(2022,02,11));
		User user6 = new User("Kevin","Kevin@domain.com", LocalDate.of(2015,07,26));
		User user7 = new User("Carolina","Carolina@domain.com", LocalDate.of(2021,10,14));
		User user8 = new User("Andrea","Andrea@domain.com", LocalDate.of(2021,12,07));
		User user9 = new User("Sebastian","Sebastian@domain.com", LocalDate.of(2018,11,18));
		User user10 = new User("Felipe","Felipe@domain.com", LocalDate.of(2017,04,11));
		User user11 = new User("Leonel","Leonel@domain.com", LocalDate.of(2020,06,03));
		User user12 = new User("Daniela","Daniela@domain.com", LocalDate.of(2021,9,01));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
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
