package rockytech.online.deed_writer.deed.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.LocalDate
import java.util.*

class DeedDto (
    var deedNo: String,
    var volNo: String?="NA",
    var pageNo: String?="NA",
    var deedWriterName: String?,
    var createdAt: LocalDate? = null,
    @LastModifiedDate
    var updatedAt: LocalDate? = null
)