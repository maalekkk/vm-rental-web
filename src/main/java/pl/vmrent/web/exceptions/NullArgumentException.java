package pl.vmrent.web.exceptions;

public class NullArgumentException extends IllegalArgumentException
{
    public NullArgumentException()
    {
    }

    public NullArgumentException(String s)
    {
        super(s);
    }
}
