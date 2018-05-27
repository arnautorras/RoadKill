
public class Life extends Heart
{
    int x;
    int y;
    public Life(int xVal, int yVal)
    {
        super(xVal,yVal);
        x = xVal;
        y = yVal;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    
}
