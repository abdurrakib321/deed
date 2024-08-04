package rockytech.online.deed_writer.deed.controller

import org.springframework.web.bind.annotation.*
import rockytech.online.deed_writer.common.AuthHelperDetails
import rockytech.online.deed_writer.deed.model.*
import rockytech.online.deed_writer.deed.service.DeedDetailsService
import java.time.LocalDate

@RestController
@RequestMapping("/api/deed")
class DeedDetailsController(private val deedDetailsService: DeedDetailsService) :AuthHelperDetails{
    @PostMapping()
    fun addRecord(@RequestBody deedDetailsModel: Deed_Details_Model):Deed_Details_Model{
        val deedWriterName=getUserId()
        deedDetailsModel.deedDto.deedWriterName=deedWriterName!!
        deedDetailsModel.buyerDto.modifierName=deedWriterName
        deedDetailsModel.sellerDto.modifierName=deedWriterName
        return deedDetailsService.addDeedDetails(deedDetailsModel)
    }

    @GetMapping("/filter")
    fun getDeedDetails(
        @RequestParam deedNo: String?,
        @RequestParam volNo: String?,
        @RequestParam pageNo: String?,
        @RequestParam deedWriterName: String?,
        @RequestParam buyerName: String?,
        @RequestParam sellerName: String?,
        @RequestParam createdAtStart: LocalDate?,
        @RequestParam createdAtEnd: LocalDate?,
        @RequestParam updatedAtStart: LocalDate?,
        @RequestParam updatedAtEnd: LocalDate?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): List<DeedResponse> {
        return deedDetailsService.getDeedDetails(
            deedNo,
            volNo,
            pageNo,
            deedWriterName,
            buyerName,
            sellerName,
            createdAtStart,
            createdAtEnd,
            page,
            size
        )
    }

    @PatchMapping("/")
    fun updateDeedDto(
        @RequestParam deedId: String,
        @RequestBody updatedDeedDto: DeedDto
    ): Deed_Details_Model {
        val deedWriterName=getUserId()
        updatedDeedDto.deedWriterName=deedWriterName!!
        return deedDetailsService.updateDeedDto(deedId, updatedDeedDto)
    }

    @PatchMapping("/buyer")
    fun updateBuyer(
        @RequestParam deedId: String,
        @RequestBody updatedBuyerDto: BuyerDto
    ): Deed_Details_Model {
        val deedWriterName=getUserId()
        return deedDetailsService.updateBuyer(deedId, updatedBuyerDto)
    }

    @PatchMapping("/seller")
    fun updateSeller(
        @RequestParam deedId: String,
        @RequestBody updatedSellerDto: SellerDto
    ): Deed_Details_Model {
        return deedDetailsService.updateSeller(deedId, updatedSellerDto)
    }

    @GetMapping("/{id}")
    fun getDeedById(@PathVariable id:String):Deed_Details_Model{
        return deedDetailsService.findById(id)
    }
}