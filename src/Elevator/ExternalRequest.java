package Elevator;

public class ExternalRequest extends Request{
    public Direction direction;

    public ExternalRequest(int l, Direction dir) {
        super(l);
        direction = dir;
    }
}
