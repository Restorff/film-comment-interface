package cn.lstf666.filmcomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("cn.lstf666.filmcomment.mapper")
@ComponentScan(basePackages = {"cn.lstf666","cn.lstf666.filmcomment.config"})
public class FilmcommentApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FilmcommentApplication.class, args);
    }

}
