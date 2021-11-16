package reactive.webflux.userservice.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;



@Service
public class DataSetupService implements CommandLineRunner{

	@Value("classpath:h2/init.sql")//going to acess file content
	private Resource initSql;
	
	@Autowired
	private R2dbcEntityTemplate entityTemplate;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String query= StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
		System.out.println(query);
		this.entityTemplate
		.getDatabaseClient()
		.sql(query)
		.then()
		.subscribe();
		
	}

}
