package com.tuling.tulingmall.config;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
public class CanalProductConfig {
    @Value("${canal.server.ip}")
   private String canalServerIp;

   @Value("${canal.server.port}")
    private int canalServerPort;
   @Value("${canal.server.username:blank}")
    private String userName;

    @Value("${canal.server.password:blank}")
    private String password;

    @Value("${canal.product.destination}")
    private String destination;

    @Bean("productConnector")
    public CanalConnector newSingleConnector(){
        String userNameStr = "blank".equals(userName) ? "" : userName;
        String passwordStr = "blank".equals(password) ? "" : password;
        ArrayList<InetSocketAddress> inetSocketAddresses = new ArrayList<>();
        inetSocketAddresses.add(new InetSocketAddress(canalServerIp,canalServerPort));
        return CanalConnectors.newClusterConnector(inetSocketAddresses,destination, userNameStr, passwordStr);
    }

}
