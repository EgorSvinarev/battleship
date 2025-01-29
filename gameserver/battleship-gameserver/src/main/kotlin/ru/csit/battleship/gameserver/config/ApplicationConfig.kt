package ru.csit.battleship.gameserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.concurrent.ScheduledThreadPoolExecutor

@EnableScheduling
@Configuration
class ApplicationConfig(
    private val properties: ApplicationProperties
) {

    @Bean
    fun taskManagerService() =
        ScheduledThreadPoolExecutor(properties.server.threads.gameThreads)
}