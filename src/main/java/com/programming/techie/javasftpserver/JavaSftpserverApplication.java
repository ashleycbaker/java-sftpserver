package com.programming.techie.javasftpserver;

import com.programming.techie.javasftpserver.module.JavaSftpserverModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JavaSftpserverModule.class)
public class JavaSftpserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSftpserverApplication.class, args);
	}

}
