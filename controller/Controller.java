package controller;

import geometryObject.Cube;
import geometryObject.TetraHedron;
import model.Solid;
import model.Vertex;
import rasterOperation.DepthBuffer;
import rasterOperation.ImageBuffer;
import rasterOperation.VisibilityBuffer;
import rendererOperation.Rasterizer;
import rendererOperation.Renderer;
import transforms.*;
import view.Raster;

import java.awt.*;
import java.awt.event.*;


public class Controller {
    private Mat4 model, view, projection;
    private Camera camera;


    private Solid solid;
    public int butt;
    public int firstX;
    public int firstY;
    public int movementX;
    public int movementY;
    private Raster raster;
    private Renderer renderer;
    public int finalX;
    public int finalY;
    public int whichOne;


    public Controller(Raster raster) {
        initObjects(raster);
        initListeners(raster);
        display();


    }


    private void display() {

        renderer.clear();

        renderer.setModel(new Mat4Identity());
        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);


        renderer.renderer(new TetraHedron());
        renderer.renderer(new Cube());
        renderer.setModel(new Mat4Transl(5, 0, 0));
    }


    public void initObjects(Raster raster) {
        DepthBuffer depthBuffer = new DepthBuffer(raster.HEIGHT, raster.WIDTH);
        ImageBuffer imageBuffer = new ImageBuffer(raster.getImg());

        VisibilityBuffer visibilityBuffer = new VisibilityBuffer(imageBuffer);
        Rasterizer rasterizer = new Rasterizer(visibilityBuffer, Vertex::getColor);


        renderer = new Renderer(raster, rasterizer);

        model = new Mat4Identity();

        view = new Mat4ViewRH(
                new Vec3D(0.5, -6, 2), // pozice pozorovatele
                new Vec3D(-0.5, 6, -2), // směr pohledu
                new Vec3D(0, 0, 1) // up vektor
        );

        camera = new Camera()
                .withPosition(new Vec3D(0, -5, 2))
                .withAzimuth(Math.toRadians(90))
                .withZenith(Math.toRadians(-20));


        projection = new Mat4PerspRH(
                Math.PI / 3,
                Raster.HEIGHT / (float) Raster.WIDTH,
                0.5,
                50
        );


    }

    private void initListeners(Raster raster) {

        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'w') camera = camera.forward(0.5);
                if (e.getKeyChar() == 's') camera = camera.backward(0.5);
                if (e.getKeyChar() == 'a') camera = camera.left(0.5);
                if (e.getKeyChar() == 'd') camera = camera.right(0.5);
                display();
            }
        });

        raster.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_O) {
                    projection = new Mat4OrthoRH(15, 15, 100, 0.1);
                    display();

                    System.out.println("Ortogonal");
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    projection = new Mat4PerspRH(
                            Math.PI / 3,
                            Raster.HEIGHT / (float) Raster.WIDTH,
                            0.5,
                            50
                    );
                    display();
                    System.out.println("Perspective");

                }


            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    butt = 1;

                } else if (e.getButton() == MouseEvent.BUTTON1) {
                    butt = 2;
                }
                firstX = e.getX();
                firstY = e.getY();


            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                movementY = firstY;
                movementX = firstX;

                firstX = e.getX();
                firstY = e.getY();


                finalX = firstX - movementX;
                finalY = firstY - movementY;
                if (butt == 1) {


                    Mat4 rotation = new Mat4RotXYZ(0, -finalY * Math.PI / 180, finalX * Math.PI / 180);
                    model = model.mul(rotation);


                } else if (butt == 2) {
                    camera = camera.addAzimuth((float) -finalX * Math.PI / 720);
                    camera = camera.addZenith((float) -finalY * Math.PI / 720);

                }
                display();

            }


        });
        raster.addMouseWheelListener(new MouseAdapter() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) { //přiblížení
                    camera = camera.forward(1.09);


                } else {
                    camera = camera.backward(1.09); //oddaleni

                }
                display();
            }
        });
    }
}


