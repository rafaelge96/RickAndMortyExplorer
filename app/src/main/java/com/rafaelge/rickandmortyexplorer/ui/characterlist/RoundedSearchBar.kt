import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rafaelge.rickandmortyexplorer.utils.Constants

@Composable
fun AppBarSearchBar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(Constants.EMPTY) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, shape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = contentColor
            )

            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textStyle = TextStyle(color = contentColor),
                singleLine = true,
            )
        }
    }
}