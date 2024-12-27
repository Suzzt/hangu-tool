package com.hangu.tool.mybatis.secret;

import com.hangu.tool.mybatis.secret.interceptor.FieldDecryptInterceptor;
import com.hangu.tool.mybatis.secret.interceptor.FieldEncryptAfterInterceptor;
import com.hangu.tool.mybatis.secret.interceptor.FieldEncryptBeforeInterceptor;
import java.util.Map;
import java.util.Objects;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 当加解密插件是通过jar包利用springboot的自动装配的提供方式时，不修改原项目中的 SqlSessionFactory 配置，
 * 那么可以通过以下方式动态加入
 *
 * @author wuzhenhong
 * @date 2024/12/27 10:28
 */
@AutoConfiguration
public class AutoMybatisInterceptorSpringConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, SqlSessionFactory> sqlSessionFactoryMap = applicationContext.getBeansOfType(
            SqlSessionFactory.class);
        if (Objects.nonNull(sqlSessionFactoryMap) && !sqlSessionFactoryMap.isEmpty()) {
            FieldEncryptBeforeInterceptor fieldEncryptBeforeInterceptor = new FieldEncryptBeforeInterceptor();
            FieldEncryptAfterInterceptor fieldEncryptAfterInterceptor = new FieldEncryptAfterInterceptor();
            FieldDecryptInterceptor fieldDecryptInterceptor = new FieldDecryptInterceptor();
            sqlSessionFactoryMap.values()
                .stream().forEach(sqlSessionFactory -> {
                    org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
                    configuration.addInterceptor(fieldEncryptBeforeInterceptor);
                    configuration.addInterceptor(fieldEncryptAfterInterceptor);
                    configuration.addInterceptor(fieldDecryptInterceptor);
                });
        }
    }
}
