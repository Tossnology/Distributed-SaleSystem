package com.software;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"JedisPoolConfig.xml"})
public class JedisPoolConfig {
}
