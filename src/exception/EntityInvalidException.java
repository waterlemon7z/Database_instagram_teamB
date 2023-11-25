package exception;

public class EntityInvalidException extends Exception
{
    public EntityInvalidException()
    {
    }

    public EntityInvalidException(String message)
    {
        super(message);
    }
}
