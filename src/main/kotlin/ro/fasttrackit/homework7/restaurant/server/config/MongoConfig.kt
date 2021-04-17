package ro.fasttrackit.homework7.restaurant.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoConfig {

    @Bean
    fun mappingMongoConverter(factory: MongoDatabaseFactory, context: MongoMappingContext): MappingMongoConverter {
        val defaultDbRefResolver = DefaultDbRefResolver(factory)
        val mappingMongoConverter = MappingMongoConverter(defaultDbRefResolver, context)
        mappingMongoConverter.setTypeMapper(DefaultMongoTypeMapper(null))

        return mappingMongoConverter
    }
}