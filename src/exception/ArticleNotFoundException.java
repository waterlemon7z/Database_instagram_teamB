package exception;

public class ArticleNotFoundException extends Exception
{
    public ArticleNotFoundException()
    {
    }

    public ArticleNotFoundException(String message)
    {
        super(message);
    }
}
