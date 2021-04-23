package br.gov.pa.prodepa.audit.autoconfigure;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import br.com.voxdatati.auditlib.ActiveMQService;
import br.com.voxdatati.auditlib.providers.ActionProvider;
import br.com.voxdatati.auditlib.providers.NetworkProvider;
import br.com.voxdatati.auditlib.providers.UserProvider;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "br.com.voxdatati.auditlib")
@ConditionalOnClass(ActiveMQService.class)
@EnableConfigurationProperties(AuditProperties.class)
public class AuditAutoConfiguration {

    @Autowired
    private AuditProperties auditProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditAutoConfiguration.class);
    
    @PostConstruct
    public void init() {
    	LOGGER.info("Iniciando Auditoria");
    }
    
    @Bean
    @ConditionalOnMissingBean
    public ActiveMQService activeMQService() {
        ActiveMQService amq = new ActiveMQService();
        amq.setBrokerURL(auditProperties.getHost());
        amq.setQueue(auditProperties.getQueue());
        return amq;
    }

    // Configura o Provider de informações do usuário
    @Bean
    @ConditionalOnMissingBean
    public UserProvider userProvider() {
    	
        return new UserProvider() {

            public String accessKey() {
                // ...
                // Lógica para obter este atribudo
                return "accessKey";
            }

            public String login() {
                // ...
                // Lógica para obter este atribudo
                return "login";
            }

            public String userExternalKey() {
                // ...
                // Lógica para obter este atribudo
                return "userExternalKey";
            }

            public String username() {
                // ...
                // Lógica para obter este atribudo
                return "username";
            }

        };
    }

    // Configura o Provider de informações de rede
    @Bean
    @ConditionalOnMissingBean
    public NetworkProvider networkProvider() {
        return new NetworkProvider(){

            public String geoLocalization() {
                // ...
                // Lógica para obter este atribudo
                return "geoLocalization";
            }

            public String ip() {
                // ...
                // Lógica para obter este atribudo
                return "ip";
            }

            public String logicDoor() {
                // ...
                // Lógica para obter este atribudo
                return "logicDoor";
            }

            public String macAddress() {
                // ...
                // Lógica para obter este atribudo
                return "macAddress";
            }

            public String user() {
                // ...
                // Lógica para obter este atribudo
                return "user";
            }

            public String workstation() {
                // ...
                // Lógica para obter este atribudo
                return "workstation";
            }

        };
    }

    // Configura o Provider de informações da ação
    @Bean
    @ConditionalOnMissingBean
    public ActionProvider actionProvider(){
        return new ActionProvider(){

            public String key() {
                // ...
                // Lógica para obter este atribudo
                return "key";
            }

            public String name() {
                // ...
                // Lógica para obter este atribudo
                return "name";
            }

            public String tree() {
                // ...
                // Lógica para obter este atribudo
                return "tree";
            }

            public String type() {
                // ...
                // Lógica para obter este atribudo
                return "type";
            }

        };
    }

}