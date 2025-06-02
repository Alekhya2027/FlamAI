import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookreviewapp.R;
import com.example.bookreviewapp.data.model.Book;
import com.example.bookreviewapp.ui.viewmodel.BookViewModel;



public class BookDetailActivity extends AppCompatActivity {
    private BookViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        TextView desc = findViewById(R.id.description);
        TextView rating = findViewById(R.id.rating);
        Button favoriteBtn = findViewById(R.id.favoriteButton);

        // Assume book is passed via intent
        Book book = (Book) getIntent().getSerializableExtra("book");

        desc.setText(book.getDescription());
        rating.setText(String.valueOf(book.getRating()));

        viewModel = new ViewModelProvider(this).get(BookViewModel.class);
        favoriteBtn.setOnClickListener(v -> {
            viewModel.addToFavorites(book);
            Toast.makeText(this, "Saved to favorites!", Toast.LENGTH_SHORT).show();
        });
    }
}
