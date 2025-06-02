import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey
    private int id;
    private String title;
    private String author;
    private String thumbnailUrl;
    private String description;
    private double rating;
    private boolean isFavorite;

    // Getters and Setters
}
