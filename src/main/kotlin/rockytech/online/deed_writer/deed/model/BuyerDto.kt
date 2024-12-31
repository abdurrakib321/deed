package rockytech.online.deed_writer.deed.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDate

data class BuyerDto (
    var name: String,
    var fatherName: String?="NA",
    var motherName: String?="NA",
    var villageName: String?="NA",
    var postOffice: String?="NA",
    var policeStation: String?="NA",
    var district: String?="NA",
    var pin: String?="NA",
    var state: String?="NA",
    var modifierName: String?="NA"
)