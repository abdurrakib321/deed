package rockytech.online.deed_writer.deed.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import rockytech.online.deed_writer.deed.model.*
import rockytech.online.deed_writer.deed.repository.DeedDetailsRepository
import java.time.LocalDate
import java.util.*

@Service
class DeedDetailsService(private val deedDetailsRepository: DeedDetailsRepository, private val mongoTemplate: MongoTemplate) {
    fun addDeedDetails(deedDetailsModel: Deed_Details_Model): Deed_Details_Model {
        deedDetailsModel.deedDto.updatedAt=LocalDate.now()
        return deedDetailsRepository.save(deedDetailsModel)
    }

    fun getDeedDetails(
        deedNo: String?,
        volNo: String?,
        pageNo: String?,
        deedWriterName: String?,
        buyerName: String?,
        sellerName: String?,
        createdAtStart: LocalDate?,
        createdAtEnd: LocalDate?,
        page: Int,
        size: Int
    ): List<DeedResponse> {
        val pageable: Pageable = PageRequest.of(page, size)

        // Fetch all deeds and filter them in memory
        val query = deedDetailsRepository.findAll().filter { deed ->
            (deedNo == null || deed.deedDto.deedNo == deedNo) &&
                    (volNo == null || deed.deedDto.volNo == volNo) &&
                    (pageNo == null || deed.deedDto.pageNo == pageNo) &&
                    (deedWriterName == null || deed.deedDto.deedWriterName!!.startsWith(deedWriterName, ignoreCase = true)) &&
                    (buyerName == null || deed.buyerDto.name.startsWith(buyerName, ignoreCase = true)) &&
                    (sellerName == null || deed.sellerDto.name.startsWith(sellerName, ignoreCase = true)) &&
                    (createdAtStart == null || deed.deedDto.createdAt?.isAfter(createdAtStart.minusDays(1)) == true) &&
                    (createdAtEnd == null || deed.deedDto.createdAt?.isBefore(createdAtEnd.plusDays(1)) == true)
                     }

        // Map filtered results to DeedResponse and paginate them
        return query
            .drop(page * size)
            .take(size)
            .map { deed ->
                DeedResponse(
                    deedId=deed.id!!,
                    deedNo = deed.deedDto.deedNo,
                    volNo = deed.deedDto.volNo,
                    pageNo = deed.deedDto.pageNo,
                    deedWriterName = deed.deedDto.deedWriterName!!,
                    buyerName = deed.buyerDto.name,
                    sellerName = deed.sellerDto.name,
                    createdAt = deed.deedDto.createdAt ?: LocalDate.now(), // Default value if null
                    updatedAt = deed.deedDto.updatedAt ?: LocalDate.now()  // Default value if null
                )
            }
    }

    fun findById(deedId: String):Deed_Details_Model{
        return deedDetailsRepository.findById(deedId).orElseThrow {
            ResourceNotFoundException("Deed Not Found with id: $deedId")
        }
    }

    fun updateDeedDto(deedId: String, updatedDeedDto: DeedDto): Deed_Details_Model {
        val deedDetailsOptional: Optional<Deed_Details_Model> = deedDetailsRepository.findById(deedId)

        if (!deedDetailsOptional.isPresent) {
            throw ResourceNotFoundException("Deed not found")
        }

        val deedDetails = deedDetailsOptional.get()
        deedDetails.deedDto = updatedDeedDto
        deedDetails.deedDto.updatedAt = LocalDate.now()

        return deedDetailsRepository.save(deedDetails)
    }

    fun updateBuyer(deedId: String, updatedBuyerDto: BuyerDto): Deed_Details_Model {
        val deedDetailsOptional: Optional<Deed_Details_Model> = deedDetailsRepository.findById(deedId)

        if (!deedDetailsOptional.isPresent) {
            throw ResourceNotFoundException("Deed not found")
        }

        val deedDetails = deedDetailsOptional.get()
        deedDetails.deedDto.updatedAt=LocalDate.now()
        deedDetails.buyerDto = updatedBuyerDto

        return deedDetailsRepository.save(deedDetails)
    }


    fun updateSeller(deedId: String, updatedSellerDto: SellerDto): Deed_Details_Model {
        val deedDetailsOptional: Optional<Deed_Details_Model> = deedDetailsRepository.findById(deedId)

        if (!deedDetailsOptional.isPresent) {
            throw ResourceNotFoundException("Deed not found")
        }

        val deedDetails = deedDetailsOptional.get()
        deedDetails.deedDto.updatedAt=LocalDate.now()
        deedDetails.sellerDto = updatedSellerDto

        return deedDetailsRepository.save(deedDetails)
    }
}