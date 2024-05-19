package modal.repository

import modal.apiClient.httpClient
import modal.dto.ProductDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class DataRepository {

    private suspend fun getProductsAPI() : List<ProductDTO> {
        val response = httpClient.get("https://fakestoreapi.com/products")
        return response.body()
    }

    fun getProductFlow() = flow {
        emit(getProductsAPI())
    }
}