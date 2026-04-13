package com.source.lunina.common.config;

import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class CommonHelperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setCollectionsMergeEnabled(true);

        Provider<Date> dateProvider = new AbstractProvider<Date>() {
            @Override
            protected Date get() {
                return new Date();
            }
        };

        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                if (source == null || source.isEmpty()) {
                    return null;
                }
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    return format.parse(source);
                } catch (ParseException e) {
                    return null;
                }
            }
        };

        mapper.createTypeMap(String.class, Date.class);
        mapper.addConverter(toStringDate);
        mapper.getTypeMap(String.class, Date.class).setProvider(dateProvider);

        return mapper;
    }
}
