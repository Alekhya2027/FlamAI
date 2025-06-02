import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookreviewapp.data.model.Book;
import com.example.bookreviewapp.data.repository.BookRepository;

import java.util.List;


public class BookViewModel extends AndroidViewModel {
    private BookRepository repository;
    private MutableLiveData<List<Book>> bookList = new MutableLiveData<>();
    private LiveData<List<Book>> favoriteBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        favoriteBooks = repository.getFavoriteBooks();
        repository.getBooksFromApi(bookList);
    }

    public LiveData<List<Book>> getBooks() {
        return bookList;
    }

    public LiveData<List<Book>> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void addToFavorites(Book book) {
        repository.saveFavorite(book);
    }
}
