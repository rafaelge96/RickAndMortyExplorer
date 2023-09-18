import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rafaelge.rickandmortyexplorer.ui.theme.PaginationBackground
import com.rafaelge.rickandmortyexplorer.ui.theme.PaginationSelectedBackground
import com.rafaelge.rickandmortyexplorer.utils.Constants

@Composable
fun PaginationRow(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit
) {
    val visiblePages = Constants.VISIBLE_PAGES
    val startPage = maxOf(1, minOf(currentPage, totalPages - visiblePages + 1))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        RoundedPaginationButton(
            text = Constants.BACK_IC,
            selected = false,
            onClick = {
                val newPage = maxOf(1, currentPage - 1)
                onPageSelected(newPage)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))

        repeat(visiblePages) { index ->
            val page = startPage + index
            val isSelected = page == currentPage
            RoundedPaginationButton(
                text = page.toString(),
                selected = isSelected,
                onClick = { onPageSelected(page) }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        RoundedPaginationButton(
            text = Constants.NEXT_IC,
            selected = false,
            onClick = {
                val newPage = minOf(totalPages, currentPage + 1)
                onPageSelected(newPage)
            }
        )
    }
}

@Composable
fun RoundedPaginationButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .width(70.dp),

        shape = CircleShape,
        colors = if (selected) {
            ButtonDefaults.buttonColors(containerColor = PaginationSelectedBackground)
        } else {
            ButtonDefaults.buttonColors(containerColor = PaginationBackground)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White)
        }
    }
}