package viewmodal

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import modal.dto.ProductDTO
import modal.repository.DataRepository

class AppViewModal : ViewModel() {

    private val product = MutableStateFlow<List<ProductDTO>>(listOf())
    val products = product.asStateFlow()

    private val repository = DataRepository()

    init {
        viewModelScope.launch {
            repository.getProductFlow().collect { productList ->
                product.update { it + productList }
            }
        }
    }
}