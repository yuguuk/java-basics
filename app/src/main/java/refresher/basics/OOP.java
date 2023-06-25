package refresher.basics;

public class OOP {
    
}

abstract class Animal{
    abstract void makeNoise();
    abstract void move();
}

abstract class Canine extends Animal{
    @Override
    void makeNoise(){
        System.out.println("bark!");
    }
    
    public void hello() throws Exception{
        System.out.println("Hello from the Canine class");
    }
}

class Dog extends Canine{

    public void hello(){
        System.out.println("Hello from the DOG class");
    }

    @Override
    void move() {
        System.out.println("Canine moves!");
    }
    
}
