package rockytech.online.deed_writer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class DeedWriterApplication

fun main(args: Array<String>) {
	runApplication<DeedWriterApplication>(*args)
}
