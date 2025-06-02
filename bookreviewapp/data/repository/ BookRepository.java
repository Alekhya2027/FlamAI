import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookreviewapp.data.local.BookDao;
import com.example.bookreviewapp.data.local.BookDatabase;
import com.example.bookreviewapp.data.model.Book;
import com.example.bookreviewapp.network.BookApiService;
import com.example.bookreviewapp.network.RetrofitClient;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookRepository {
    private BookDao bookDao;
    private BookApiService apiService;

    public BookRepository(Application application) {
        BookDatabase db = BookDatabase.getInstance(application);
        bookDao = db.bookDao();
        apiService = RetrofitClient.getClient(application).create(BookApiService.class);
    }

    public LiveData<List<Book>> getFavoriteBooks() {
        return bookDao.getFavoriteBooks();
    }

    public void saveFavorite(Book book) {
        book.setFavorite(true);
        Executors.newSingleThreadExecutor().execute(() -> bookDao.insert(book));
    }

    public void getBooksFromApi(MutableLiveData<List<Book>> booksLiveData) {
        apiService.getBooks().enqueue(new Callback<List<Book>>() {
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                booksLiveData.setValue(response.body());
            }

            public void onFailure(Call<List<Book>> call, Throwable t) {
                booksLiveData.setValue(null);
            }
        });
    }
}
