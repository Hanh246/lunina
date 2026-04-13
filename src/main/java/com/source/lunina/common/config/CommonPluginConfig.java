package com.source.lunina.common.config;

import com.exe201.tutorlink.common.plugin.ICrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration
@EnablePluginRegistries({IMapperPlugin.class, ICrudPlugin.class})
public class CommonPluginConfig {
}
