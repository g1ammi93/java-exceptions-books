package exercises.books;

public class Book {

    //Attributi classe Book
    private String title;
    private int pages;
    private String author;
    private String editor;

    // Costruttore
    public Book(String title, int pages, String author, String editor) throws IllegalArgumentException {
        isStringValid(title, "Titolo");
        isStringValid(author, "Autore");
        isStringValid(editor, "Editore");
        isIntValid(pages, "Numero di pagine");
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.editor = editor;
    }

    // Validation
    private void isStringValid(String value, String type) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(type + " non valido");
        }
    }

    private void isIntValid(int value, String type) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException(type + " non valido");
        }
    }

    // Getter e Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        isStringValid(title, "Titolo");
        this.title = title;
    }

    public int getNumberOfPages() {
        return pages;
    }

    public void setNumberOfPages(int pages) throws IllegalArgumentException {
        isIntValid(pages, "Numero di pagine");
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws IllegalArgumentException {
        isStringValid(author, "Autore");
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) throws IllegalArgumentException {
        isStringValid(editor, "Editore");
        this.editor = editor;
    }

    public String bookInfo() {
        return String.format("Titolo del libro: %s; Autore: %s; Editore: %s; Numero di pagine: %d.", title, author, editor, pages);
    }


    @Override
    public String toString() {
        return title + "," + pages + "," + author + "," + editor;
    }

    public static Book fromString(String bookString) throws IllegalArgumentException {
        String[] parts = bookString.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Formato del libro non valido");
        }
        String title = parts[0];
        int pages = Integer.parseInt(parts[1]);
        String author = parts[2];
        String editor = parts[3];
        return new Book(title, pages, author, editor);
    }
}