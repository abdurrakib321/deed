package rockytech.online.deed_writer.deed.model

import java.time.LocalDate

data class DeedResponse(
    val deedId:String,
    val deedNo: String,
    val volNo: String,
    val pageNo: String,
    val deedWriterName: String,
    val buyerName: String,
    val sellerName: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate
)