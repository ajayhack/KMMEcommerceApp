import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import viewmodal.AppViewModal

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        AppContent(appViewModal = AppViewModal())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(appViewModal: AppViewModal){
    val products = appViewModal.products.collectAsState()

    BoxWithConstraints {
        val scope = this
        val maxWidth = scope.maxWidth

        var cols = 2
        var modifier = Modifier.fillMaxWidth()

        if(maxWidth > 840.dp){
            cols = 3
            modifier = Modifier.widthIn(max = 1080.dp)
        }
        val scrollState = rememberLazyGridState()
        Column(verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyVerticalGrid(columns = GridCells.Fixed(cols) ,
                state = scrollState ,
                contentPadding = PaddingValues(16.dp)
            ) {
                item(span = {GridItemSpan(cols)}) {
                    Column {
                     SearchBar(
                         modifier = Modifier.fillMaxSize() ,
                         query = "" ,
                         active = false ,
                         onActiveChange = {} ,
                         onQueryChange = {} ,
                         onSearch = {} ,
                         leadingIcon = { Icon(imageVector = Icons.Default.Search ,
                             contentDescription = "Search Icon") },
                         placeholder = { Text("Search Products...") }
                     ){}
                        Spacer(Modifier.size(16.dp))
                    }
                }
                items(
                    items = products.value,
                    key = { product -> product.id.toString() }) { productItem ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        elevation = 2.dp
                    ) {

                        Column(verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            KamelImage(
                                asyncPainterResource(data = productItem.image.toString()),
                                contentDescription = productItem.title,
                                modifier = Modifier.height(130.dp)
                            )

                            Text(
                                text = productItem.title ?: "",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis ,
                                modifier = Modifier.padding(horizontal = 8.dp).heightIn(40.dp)
                            )

                            Spacer(modifier = Modifier.size(8.dp))

                            Box(modifier = Modifier.fillMaxWidth() ,
                                contentAlignment = Alignment.CenterStart){
                                Text(
                                    text = "${productItem.price.toString() ?: ""} INR" ,
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis ,
                                    modifier = Modifier.padding(horizontal = 8.dp).heightIn(40.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}