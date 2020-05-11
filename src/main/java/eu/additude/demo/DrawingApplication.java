package eu.additude.demo;

public class DrawingApplication {

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        drawing.setShape(new Rectangle());
        drawing.drawShape();
    }
}

class Drawing {

    //Circle c = new Circle();
    //Rectangle r = new Rectangle();
    Shape shape;

    Drawing() {
    }

    Drawing(Shape shape) {
        this.shape = shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void drawShape() {
        //c.draw();
        shape.draw();
    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("I am a circle");
    }

}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("I am a reactangle");
    }

}
