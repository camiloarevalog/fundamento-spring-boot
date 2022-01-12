package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
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


	Log LOGGER= LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanDependency myBeanDependency;

	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;
	private UserRepository userRepository;

	private UserService userService;

	public FundamentosApplication( @Qualifier(value="componentTwoImplement") ComponentDependency componentDependency,
								   MyBean myBean,MyBeanDependency myBeanDependency,
								   MyBeanWithProperties myBeanWithProperties,UserPojo userPojo,
								   UserRepository userRepository,UserService userService){

		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanDependency=myBeanDependency;
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
		//clasesAnteriores();
		//saveUserInDataBase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void getInformationJpqlFromUser(){
	LOGGER.info("Usuario buscado por email"+userRepository.
			findByUserEmail("camilo@gmail.com").
			orElseThrow(()->new RuntimeException("No se encontro el user")));

	userRepository.findAndSort8("cam", Sort.by("id").descending())
			.stream()
			.forEach(user -> LOGGER.info("Usuario metodo ordenamiento"+user));

	userRepository.findByName("camilo")
			.stream()
			.forEach(user -> LOGGER.info("Usuario"+user));

	LOGGER.info("Usuario con query method"+ userRepository.findByEmailAndName("Daniela@gmail.com","Daniela")
			.orElseThrow(()->new RuntimeException("Usuario no existe")));

	userRepository.findByNameLike("%ca%")
			.stream()
			.forEach(user -> LOGGER.info("Usuario like"+user));

	//LOGGER.info("Usuario con dos parametros"+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,03,22),"Daniela@gmail.com")
	//		.orElseThrow(()->new RuntimeException("No se encontro la persona")));



	}

	private void saveWithErrorTransactional(){
		User test1= new User("Test1","Test1@gmail.com", LocalDate.of(2021,03,22));
		User test2= new User("test2","test2@gmail.com", LocalDate.of(2021,03,22));
		User test3= new User("test3","test4@gmail.com", LocalDate.of(2021,03,22));
		User test4= new User("test4","test3@gmail.com", LocalDate.of(2021,03,22));

		List<User>users=Arrays.asList(test1,test2,test3,test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una excepcion"+e);
		}

		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Este es el usuario del transactional"+user));

	}

	public void saveUserInDataBase(){
		System.out.println("SaveUserInDataBase");
		User user1=new User("camilo","camilo@gmail.com", LocalDate.of(2021,03,22));
		User user2=new User("camilo","Cristian@gmail.com", LocalDate.of(2021,03,22));
		User user3=new User("carla","key@gmail.com", LocalDate.of(2021,03,22));
		User user4=new User("Andres","an@gmail.com", LocalDate.of(2021,03,22));
		User user5=new User("Daniela","Daniela@gmail.com", LocalDate.of(2021,03,22));
		User user6=new User("pepe","cao@gmail.com", LocalDate.of(2021,03,22));
		List<User>list= Arrays.asList(user1,user2,user3,user4,user5,user6);
		list.stream().forEach(userRepository::save);
		System.out.println("SaveUserInDataBase-Guardo");
	}
	public void clasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanDependency.printDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword());
		LOGGER.error("Esto es un error");
	}
}
