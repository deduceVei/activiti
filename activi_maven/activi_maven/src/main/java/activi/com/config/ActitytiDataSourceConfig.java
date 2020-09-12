//package activi.com.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.activiti.engine.*;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ActitytiDataSourceConfig {
//
//    private static final Logger log = LogManager.getLogger(ActitytiDataSourceConfig.class);
//
//
//    @Bean(name = "activitiDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.activiti")
//    public DataSource activitiDataSource(){
//        log.info("activitiDataSource 初始化...");
//        return new DruidDataSource();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(activitiDataSource());
//    }
//
//    @Bean
//    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
//        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
//        configuration.setDataSource(activitiDataSource());
//        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
//        configuration.setJobExecutorActivate(true);
//        configuration.setTransactionManager(transactionManager());
//        return configuration;
//    }
//
//    @Bean
//    public RepositoryService repositoryService(ProcessEngine processEngine) {
//        return processEngine.getRepositoryService();
//    }
//
//    @Bean
//    public RuntimeService runtimeService(ProcessEngine processEngine) {
//        return processEngine.getRuntimeService();
//    }
//
//    @Bean
//    public TaskService taskService(ProcessEngine processEngine) {
//        return processEngine.getTaskService();
//    }
//
//    @Bean
//    public HistoryService historyService(ProcessEngine processEngine) {
//        return processEngine.getHistoryService();
//    }
//
//    @Bean
//    public ManagementService managementService(ProcessEngine processEngine) {
//        return processEngine.getManagementService();
//    }
//
//    @Bean
//    public IdentityService identityService(ProcessEngine processEngine) {
//        return processEngine.getIdentityService();
//    }
//
//
//}
