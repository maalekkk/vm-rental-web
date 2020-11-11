package pl.vmrent.web.exceptions;

public class NullArgumentException extends IllegalArgumentException
{
    public NullArgumentException()
    {
        this("Null argument is not allowed here!");
    }

    public NullArgumentException(String s)
    {
        super(s);
    }

}
