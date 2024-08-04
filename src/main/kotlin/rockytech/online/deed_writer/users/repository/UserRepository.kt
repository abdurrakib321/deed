package rockytech.online.deed_writer.users.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import rockytech.online.deed_writer.users.model.User
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByPhoneNo(phoneNo: String): Optional<User>
}
