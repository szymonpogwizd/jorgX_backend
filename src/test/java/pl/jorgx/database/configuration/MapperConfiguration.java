package pl.jorgx.database.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jorgX.database.city.CityMapper;
import pl.jorgX.database.city.CityMapperImpl;
import pl.jorgX.database.opinion.OpinionMapper;
import pl.jorgX.database.opinion.OpinionMapperImpl;
import pl.jorgX.database.place.PlaceMapper;
import pl.jorgX.database.place.PlaceMapperImpl;
import pl.jorgX.database.user.UserMapper;
import pl.jorgX.database.user.UserMapperImpl;
import pl.jorgX.utils.password.PasswordEncoderMapper;

@TestConfiguration
public class MapperConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoderMapper passwordEncoderMapper(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderMapper(passwordEncoder);
    }

    @Bean
    public UserMapper userMapper()
    {
        return new UserMapperImpl();
    }

    @Bean
    public PlaceMapper placeMapper()
    {
        return new PlaceMapperImpl();
    }

    @Bean
    public OpinionMapper opinionMapper()
    {
        return new OpinionMapperImpl();
    }

    @Bean
    public CityMapper cityMapper()
    {
        return new CityMapperImpl();
    }

}
