package rockytech.online.deed_writer.deed.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDate

@Document
class Deed_Details_Model (
    var id:String ?=null,
    var deedDto: DeedDto,
    var buyerDto: BuyerDto,
    var sellerDto: SellerDto,
)