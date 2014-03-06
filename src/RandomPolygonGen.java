import javafx.scene.control.RadioMenuItem;
import shape.ExtendedPolygon;
import shape.ExtendedPolygonBuilder;
import shape.RectangleContainer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rye
 * Date: 3/4/14
 * Time: 2:16 PM
 */
public class RandomPolygonGen extends JComponent {

    public static int CONTAINER_WIDTH = 500;
    public static int CONTAINER_HEIGHT = 500;

    public static RectangleContainer container = new RectangleContainer(0, 0, CONTAINER_WIDTH, CONTAINER_HEIGHT);

    public static ExtendedPolygon randPolygon(RectangleContainer box, int maxEdgeNum, int minRadius, int maxRadius) {
        Random rand = new Random(Double.doubleToLongBits(Math.random()));
        int edgeNum = 3 + rand.nextInt(maxEdgeNum - 2);

        ExtendedPolygonBuilder pgBuilder = new ExtendedPolygonBuilder(box);

        return pgBuilder.buildPolygon(edgeNum, minRadius, maxRadius);
    }

    @Override
    public void paint(Graphics g) {

        for (Polygon p : container.getPolygonsInside()) {
            g.setColor(Color.BLUE);
//            g.fillPolygon(p);
            g.drawPolygon(p);
        }
        g.drawRect(container.x, container.y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
    }

    public static void awesomeFillTheRest() {
        // TODO(Rye): 1.   Randomly generate points, pick those that are not in any of the polygons in container
        //              2.    For each point, change it into random boxes with a small unit bound.
        //              2.1.  Then increase there bounds by a small random step independently,
        //              3.    Go through the small boxes in 2.1, throw out those intersect with exist polygons
        //              3.1   For each of the rest boxes, randomly generate polygons within
        //              3.2   Put the generated polygons into container
        //              4     Repeat for reasonable times
    }

    public static void fillTheRest() {
        System.out.println("Fill the rest.");
        int boxEdgeLen = 4;
        int boxNum = (int)container.getArea() / (boxEdgeLen * boxEdgeLen);
        for(int i = 0; i < boxNum; i++) {
            int rectX = i % (container.width / boxEdgeLen);
            int rectY = (i / boxEdgeLen) * boxEdgeLen;
            RectangleContainer rect = new RectangleContainer(rectX, rectY, boxEdgeLen, boxEdgeLen);
            for(ExtendedPolygon p : container.getPolygonsInside()) {
                if(p.intersects(rect)) {
                    break;
                } else {
                    boolean result = container.safePut(randPolygon(rect, 5,(int)0.5 * boxEdgeLen, (int)0.5 * boxEdgeLen));
                    assert result;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Random Polygon");
//        frame.setLayout(new BorderLayout());
        frame.setSize(CONTAINER_WIDTH, CONTAINER_HEIGHT);
        frame.setBackground(Color.black);
        frame.setLocation(500, 150);

        int count = 0;
        int maxEdgeNum = 5;
        int minRadius = 10;
        int maxRadius = 50;
        int stepX = -1;
        int stepY = 1;
        double minCoverageRatio = 0.60;
        boolean[] notOrgnized = {true, true, true, true, true, true, true, true};

        long beginTime = System.currentTimeMillis();

        while(true) {
            boolean result = false;
            ExtendedPolygon polygon = RandomPolygonGen.randPolygon(container, maxEdgeNum, minRadius, maxRadius);
            result = container.safePut(polygon);
            if(!result) {
                for(int i = 0; i < 20; i++) {
                    Random r = new Random(Double.doubleToLongBits(Math.random()));
                    int deltX = stepX + r.nextInt(3);
//                    int deltY = stepY * r.nextInt(2);
                    int deltY = stepY;
                    polygon.translate(deltX, deltY);
                    result = container.safePut(polygon);
                    if(result) {
                        break;
                    }
                }
            }
            if(notOrgnized[0] && container.getCoverageRatio() >= 0.3) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[0] = false;
            }
            if(notOrgnized[1] && container.getCoverageRatio() >= 0.4) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[1] = false;
            }
            if(notOrgnized[2] && container.getCoverageRatio() >= 0.5) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[2] = false;
            }
            if(notOrgnized[3] && container.getCoverageRatio() >= 0.6) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[3] = false;
            }
            if(notOrgnized[4] && container.getCoverageRatio() >= 0.7) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[4] = false;
            }
            if(notOrgnized[5] && container.getCoverageRatio() >= 0.8) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[5] = false;
            }
            if(notOrgnized[6] && container.getCoverageRatio() >= 0.9) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[6] = false;
            }
            if(notOrgnized[7] && container.getCoverageRatio() >= minCoverageRatio) {
                for(;container.getPolygonsInside().size() > 0;) {
                    ExtendedPolygon p = container.getPolygonsInside().get(0);
                    container.remove(p);

                    for(int i = 0; i < 20; i++) {
                        Random r = new Random(Double.doubleToLongBits(Math.random()));
                        int deltX = stepX + r.nextInt(3);
                        int deltY = stepY * r.nextInt(2);
                        p.translate(deltX, deltY);
                        result = container.safePut(p);
                        if(result) {
                            System.out.println("move done.");
                            break;
                        }
                    }
                }
                notOrgnized[7] = false;
            }
//                System.out.println("Put again " + count++);
            if(container.getCoverageRatio() > minCoverageRatio ) {
//                fillTheRest();
                break;
            }
        }
        long timeUsedMillis = System.currentTimeMillis() - beginTime;
        System.out.format("%ds used\n", timeUsedMillis/1000);
        System.out.format("Coverage Ratio: %.2f%%\n", container.getCoverageRatio() * 100);

        frame.getContentPane().add(new RandomPolygonGen());
        frame.setVisible(true);
    }
}
