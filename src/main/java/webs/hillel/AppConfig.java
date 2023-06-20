package webs.hillel;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Cart shoppingCart() {
        return new Cart();
    }

    @Bean
    public ProductRepository appProductRepository() {
        return new ProductRepository();
    }
}
