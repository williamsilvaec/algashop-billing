package com.williamsilva.algashop.billing.infrastructure.utility.mapper;

import com.williamsilva.algashop.billing.application.utility.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public Mapper mapper() {
        var modelMapper = new ModelMapper();
        configuration(modelMapper);

        return new Mapper() {
            @Override
            public <T> T convert(Object source, Class<T> targetClass) {
                return modelMapper.map(source, targetClass);
            }
        };
    }

    private void configuration(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
