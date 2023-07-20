package com.commerxo.commerxoopenidserver;

import com.commerxo.commerxoopenidserver.models.UserGroup;
import com.commerxo.commerxoopenidserver.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class CommerxoOpenidServerApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CommerxoOpenidServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}