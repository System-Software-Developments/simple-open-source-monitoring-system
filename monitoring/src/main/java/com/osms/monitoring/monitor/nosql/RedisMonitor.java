package com.osms.monitoring.monitor.nosql;

import com.osms.monitoring.monitor.db.MysqlMonitor;
import com.osms.monitoring.util.OsmsProperties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisMonitor {

    private static final Logger logger = LoggerFactory.getLogger(RedisMonitor.class);

    public static void runDBMonitor() throws Exception {

        String redisHost = (String) OsmsProperties.getProperty("redis.host");
        int redisPort = Integer.parseInt((String)OsmsProperties.getProperty("redis.port"));
        String redisPassword= (String) OsmsProperties.getProperty("redis.password");

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool pool = new JedisPool(jedisPoolConfig, redisHost, redisPort, 1000, redisPassword);

        final Jedis jedis = pool.getResource();

        long period = OsmsProperties.getPeriod();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            try {

                String value = (String)jedis.info("Clients");
                //System.out.println("===>"+value);
                logger.info("Client Value : {}", value);

            } catch (Throwable e) {
                logger.error("Redis monitor has an error", e);
            } finally {
            }


            }
        }, 0, period);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if(jedis != null) {
                    jedis.close();
                }
            }
        });
    }

}
