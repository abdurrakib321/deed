package rockytech.online.deed_writer.deed.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDate
import java.util.Date

class SellerDto (
    var name: String,
    var fatherName: String,
    var motherName: String,
    var villageName: String,
    var postOffice: String,
    var policeStation: String,
    var district: String,
    var pin: String,
    var state: String,
    var modifierName: String?
)