package com.example.study.common.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfig {

	@Bean
	public MapperFactoryBean loadFactory(){
		return new MapperFactoryBean();
	}

	@Bean
	public MapperFacade loadMapperFacade(MapperFactory factory){
		return factory.getMapperFacade();
	}


	
}
