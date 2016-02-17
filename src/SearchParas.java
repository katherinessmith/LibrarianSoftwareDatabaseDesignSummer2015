
public class SearchParas
{

	private String bookId;
	private String title;
	private String author;

	public SearchParas(String BookId, String Title, String Author)
	{
		bookId = BookId;
		title = Title;
		author = Author;
	}

	public SearchParas()
	{
	}

	public String getBookId()
	{
		return bookId;
	}

	public String getBookTitle()
	{
		return title;
	}

	public String getBookAuthor()
	{
		return author;
	}
}
