package rockytech.online.deed_writer.deed.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import rockytech.online.deed_writer.deed.model.Deed_Details_Model
@Repository
interface DeedDetailsRepository:MongoRepository<Deed_Details_Model,String> {
}