package pl.softwareplant.swapireports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.softwareplant.swapireports.mapper.ReportMapper;
import pl.softwareplant.swapireports.request.SwapiRequester;

@Configuration
public class AppConfig {

    @Bean
    public SwapiRequester swapiRequester() {
        return new SwapiRequester();
    }

    @Bean
    public ReportMapper reportMapper() {
        return new ReportMapper();
    }

}
