package modal.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(val category : String? = null ,
                      val description : String? = null ,
                      val id : Int? = Int.MIN_VALUE ,
                      val image : String? = null ,
                      val price : Double? = 0.0 ,
                      val rating : RatingDTO? = null ,
                      val title : String? = null)
@Serializable
data class RatingDTO(val count : Int? = Int.MIN_VALUE ,
                  val rate : Double? = 0.0)
