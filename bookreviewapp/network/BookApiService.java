import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.bookreviewapp.data.model.Book;



public interface BookApiService {
    @GET("books.json")
    Call<List<Book>> getBooks();
}
