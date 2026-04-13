package com.source.lunina.common.config;

import com.source.lunina.common.plugin.ICrudPlugin;
import com.source.lunina.common.plugin.IMapperPlugin;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration
@EnablePluginRegistries({IMapperPlugin.class, ICrudPlugin.class})
public class CommonPluginConfig {
}
