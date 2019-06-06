/*
 * MandelbrotFX.java
 *
 * Version: 1.0
 *
 * Revisions: 1
 *
 * @author      Aditya Landge
 * @author      Rupa Karumanchi
 *
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * This class draws a Mandelbrot using Multithreading
 */

public class MandelbrotFX extends Application {

    WritableImage mandelBrotSetImage;
    final int IMG_WIDTH     = 800;
    final int IMG_HEIGHT    = 800;
    long milliSeconds;

    public void init()  {
        milliSeconds = System.currentTimeMillis();
    }
    public void end(String s)   {
        System.err.println(s + ":       " + ( System.currentTimeMillis() - milliSeconds) + "ms" );
        System.err.println(" # of cores" +   ":       " +
                Runtime.getRuntime().availableProcessors() );
    }


    public void start(Stage theStage) {
        int numThreads = MandelbrotSet.n;

        // Taking user input for desired number of threads

        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            numThreads = scanner.nextInt();
        }
        int change = IMG_HEIGHT/numThreads;
        int height_start = 0;
        int height_end = change;

        // Creating a pool of threads according to numThreads

        MandelbrotSet[] pool = new MandelbrotSet[numThreads];
        for(int i=0; i<numThreads;i++ ){
            pool[i] = new MandelbrotSet(IMG_WIDTH,IMG_HEIGHT);
            pool[i].start();
        }

        StackPane root = new StackPane();
        ImageView[] aImage = new ImageView[numThreads];

        // dividing the calculation up and running it over a for-loop

        for(int i = 0; i < numThreads-1; i++){
            height_start += 0;
            height_end += change;
            init();

            // creating image with pool of threads to run faster

            mandelBrotSetImage = pool[i].createImage3(0, IMG_WIDTH,
                    height_start, height_end );
            end("Multiple Thread MandelbrotSet Test : Thread no " + i );
            aImage[i] = new ImageView();
            aImage[i].setImage(mandelBrotSetImage);
            root.getChildren().add(aImage[i]);
        }

        Scene scene = new Scene(root, IMG_WIDTH, IMG_HEIGHT);

        theStage.setTitle("Mandelbrot Set");
        theStage.setResizable(false);
        theStage.setScene(scene);
        theStage.show();
    }

    public static void main(String[] args) {
        if(args.length == 1){
            MandelbrotSet.n = Integer.valueOf(args[0]);
        }
        launch(args);
    }

}


class MandelbrotSet extends Thread {

    private static final int    MAX_COLORS  = 256;
    private static final double BOUNDERY = 1000;
    private static int    width;
    private static int    height;
    private static WritableImage mandelBrotSetImage;

    private static PixelWriter aPixelWriter;
    private static final Color[] colors = new Color[MAX_COLORS];
    private static double minR  = -2.4;
    private static double maxR  = 0.9;
    private static double minI  = -1.3;
    private static double maxI  = 1.28;
    static int n = Runtime.getRuntime().availableProcessors();
    private static MandelbrotSet[] allThreads;

    static {
        for (int index = 0; index < colors.length; index++) {
            colors[index] = Color.RED.interpolate(Color.BLUE,
                    (( 1.0 / colors.length) * index) );
        }
    }

    public MandelbrotSet() {
    }

    /**
     * run method
     */

    public synchronized void run(){
//        this.mandelBrotSetImage = this.createImage2(800,800);
    }

    public MandelbrotSet(int width,int height) {
        this.width = width;
        this.height = height;
        mandelBrotSetImage = new WritableImage(width, height);
        if ( allThreads == null )
            allThreads = new MandelbrotSet[width * height ];
    }

    public MandelbrotSet(int width,int height, int index) {
        this.width = width;
        this.height = height;
        mandelBrotSetImage = new WritableImage(width, height);
        if ( allThreads == null )
            allThreads = new MandelbrotSet[width * height ];
    }

    private Color getColor(int count) {
        return count >= colors.length ?  Color.BLACK : colors[count];
    }

    private int calc(double re, double img ) {
        int    counter = 0;
        double length;
        double aComplexNumberRe = 0;
        double aComplexNumberImg = 0;
        double real = 0;
        double imaginary = 0;

        do {
            real       =  aComplexNumberRe * aComplexNumberRe -
                    aComplexNumberImg * aComplexNumberImg;
            imaginary  = aComplexNumberRe *  aComplexNumberImg +
                    aComplexNumberImg *  aComplexNumberRe;
            aComplexNumberRe   = real;
            aComplexNumberImg  = imaginary;
            aComplexNumberRe   += re;
            aComplexNumberImg  += img;
            length = aComplexNumberImg * aComplexNumberImg +
                    aComplexNumberRe * aComplexNumberRe;
            counter++;
        } while (counter < MAX_COLORS && ( length < BOUNDERY ) );
        return counter;
    }

    public Color determineColor(int x, int y)   {
        double re = (minR * (width - x) + x * maxR) / width;
        double img = (minI * (height - y) + y * maxI) / height;
        return getColor(calc(re, img));
    }

    public WritableImage createImage()  {
        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                aPixelWriter.setColor(x, y, determineColor(x, y));
            }
        }

        return mandelBrotSetImage;
    }

    /**
     *
     * This method has been modified from the original code to take additional
     * parameters of starting width and height and ending width and height.
     *
     * @param width_start Starting width
     * @param width_end Ending width
     * @param height_start Starting height
     * @param height_end Ending height
     * @return mandelBrotSetImage final image
     *
     */

    public WritableImage createImage3(int width_start, int width_end,
                                      int height_start, int height_end ) {
        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();

        for (int x = width_start; x < width_end; x++) {
            for (int y = height_start; y < height_end; y++) {
                aPixelWriter.setColor(x, y, determineColor(x, y));
            }
        }
        return mandelBrotSetImage;
    }
}

