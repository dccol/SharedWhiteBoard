package client;

import Shapes.*;
import Shapes.Rectangle;
import remote.IRemoteWhiteBoard;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;


public class WhiteBoardFrame extends JFrame {

    private IRemoteWhiteBoard remoteWhiteBoard;

    private String username;

    private WhiteBoardPanel whiteBoardPanel;
    private JPanel controlPanel;
    private JButton colourBtn, freelineBtn, straightlineBtn, rectangleBtn, circleBtn, ovalBtn, textBtn;
    private JSlider strokeSizeSlider;

    private String currentShape = "FreeLine";
    private Color currentColour;
    private int currentStrokeSize;

    // Distributed by WhiteBoardAccess on line insertion
    int id = 0;

    // Default starting line
    FreeLine line = new FreeLine(new ArrayList<Point>(), Color.black, 3, 0);
    StraightLine straightLine = new StraightLine(new Line2D.Double(), Color.black, 3, 0);
    Rectangle rectangle = new Rectangle(new Rectangle2D.Double(), Color.black, 3, 0);
    Oval oval = new Oval(new Ellipse2D.Double(), Color.black, 3, 0);
    Circle circle = new Circle(new javafx.scene.shape.Circle(), Color.black, 3 ,0);
    Text text = new Text("", 100, 100, Color.black, 3, 0);

    /**
     * Launch the window.
     */
    public void run() {
        try {
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WhiteBoardFrame(IRemoteWhiteBoard remoteWhiteBoard, String username){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.remoteWhiteBoard = remoteWhiteBoard;
        this.username = username;

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        whiteBoardPanel = new WhiteBoardPanel(remoteWhiteBoard);
        controlPanel = new JPanel();

        contentPane.add(whiteBoardPanel, BorderLayout.CENTER);
        contentPane.add(controlPanel, BorderLayout.NORTH);

        this.pack();
        this.setLocationRelativeTo(null);
//        this.setVisible(true);

        // Refresh Listener
        Timer timer = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                whiteBoardPanel.repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        // Mouse Listeners
        whiteBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    switch (currentShape) {
                        case "FreeLine":
                            ArrayList<Point> points = line.getPoints();
                            // Add new point to line points
                            points.add(e.getPoint());
                            // Update the lines points locally
                            line.setPoints(points);

                            // Push new line to WhiteBoardAccess
                            id = remoteWhiteBoard.addLine(line);
                            whiteBoardPanel.repaint();
                            break;
                        case "StraightLine":
                            // If the line already has two points, reset line and specify its start point
                            if(straightLine.getIsComplete() == 1) {
                                straightLine.getLine().setLine(e.getPoint(), new Point());
                                straightLine.setIsComplete(0);
                            }
                            // If the line is incomplete, update the end point and push to WhiteBoardAccess
                            else if(straightLine.getIsComplete() == 0){
                                Point2D startPoint = straightLine.getLine().getP1();
                                straightLine.getLine().setLine(startPoint, e.getPoint());
                                remoteWhiteBoard.addStraightLine(straightLine);
                                straightLine.setIsComplete(1);
                            }
                            break;
                        case "Rectangle":
                            // If the rectangle already has point, height and width, reset and specify its start point
                            if(rectangle.getIsComplete() == 1) {
                                rectangle.getRectangle2D().setFrame(e.getX(), e.getY(), 100, 100);
                                rectangle.setIsComplete(0);
                            }
                            // If the rectangle is incomplete, update the height and width and push to WhiteBoardAccess
                            else if(rectangle.getIsComplete() == 0){

                                /** TO DO FIX FLIP RECTANGLE **/
                                Point2D startPoint = rectangle.getRectangle2D().getBounds().getLocation();
                                rectangle.getRectangle2D().setFrame(startPoint.getX(), startPoint.getY(),
                                        Math.abs(e.getX()-startPoint.getX()), Math.abs(e.getY()-startPoint.getY()));
                                remoteWhiteBoard.addRectangle(rectangle);
                                rectangle.setIsComplete(1);
                            }
                            break;
                        case "Oval":
                            // If the oval already has point, height and width, reset and specify its start point
                            if(oval.getIsComplete() == 1) {
                                oval.getOval().setFrame(e.getX(), e.getY(), 100, 100);
                                oval.setIsComplete(0);
                            }
                            // If the oval is incomplete, update the height and width and push to WhiteBoardAccess
                            else if(oval.getIsComplete() == 0){

                                /** TO DO FIX FLIP RECTANGLE **/
                                Point2D startPoint = oval.getOval().getBounds().getLocation();
                                oval.getOval().setFrame(startPoint.getX(), startPoint.getY(),
                                        Math.abs(e.getX()-startPoint.getX()), Math.abs(e.getY()-startPoint.getY()));
                                remoteWhiteBoard.addOval(oval);
                                oval.setIsComplete(1);
                            }
                            break;
                        case "Circle":
                            // If the circle already has centre point and radius, reset and specify its centre point
                            if(circle.getIsComplete() == 1) {
                                circle.getCircle().setCenterX(e.getX());
                                circle.getCircle().setCenterY(e.getY());
                                circle.setIsComplete(0);
                            }
                            // If the circle is incomplete, update the radius and push to WhiteBoardAccess
                            else if(circle.getIsComplete() == 0){

                                double radius = Math.sqrt(Math.pow((e.getX() - circle.getCircle().getCenterX()), 2) +
                                        Math.pow((e.getY() - circle.getCircle().getCenterY()), 2));
                                circle.getCircle().setRadius(radius);

                                // Convert to serializable form
                                SerializableCircle serializableCircle = new SerializableCircle(
                                        circle.getCircle().getCenterX(),
                                        circle.getCircle().getCenterY(),
                                        circle.getCircle().getRadius(),
                                        circle.getColour(),
                                        circle.getStrokeSize(),
                                        circle.getFill()
                                );
                                remoteWhiteBoard.addCircle(serializableCircle);
                                circle.setIsComplete(1);
                            }
                            break;
                        case "Text":
                            String input = JOptionPane.showInputDialog("Hello");
                            text.setText(input);
                            text.setX(e.getX());
                            text.setY(e.getY());
                            remoteWhiteBoard.addText(text);
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }

                }catch(RemoteException ex){
                    ex.printStackTrace();
                }
            }

        });

        whiteBoardPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                try {
                    ArrayList<Point> points = line.getPoints();
                    // Add new point to line points
                    points.add(e.getPoint());
                    // Update the lines points locally
                    line.setPoints(points);

                    // Push update to WhiteBoardAccess
                    remoteWhiteBoard.updateLine(id, line);

                    whiteBoardPanel.repaint();

                }catch(RemoteException ex){
                    System.out.println("Exception");
                }
            }

        });
        whiteBoardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){

                // Reset new line
                line.setPoints(new ArrayList<>());
            }
        });

        // Button Listeners to select Colours
        colourBtn = new JButton("Select Colour");
        controlPanel.add(colourBtn);
        colourBtn.addActionListener(arg0 -> {
            currentColour = JColorChooser.showDialog(null, "Choose a color", Color.RED);
            line.setColour(currentColour);
            straightLine.setColour(currentColour);
            rectangle.setColour(currentColour);
            oval.setColour(currentColour);
            circle.setColour(currentColour);
            text.setColour(currentColour);
        });

        // Button Listeners to select Shapes (Free Line, Straight Line, Circle, Oval, Rectangle)
        freelineBtn = new JButton("Free Line");
        controlPanel.add(freelineBtn);
        freelineBtn.addActionListener(arg0 -> {
            currentShape = "FreeLine";
        });
        straightlineBtn = new JButton("Straight Line");
        controlPanel.add(straightlineBtn);
        straightlineBtn.addActionListener(arg0 -> {
            currentShape = "StraightLine";
        });
        rectangleBtn = new JButton("Rectangle");
        controlPanel.add(rectangleBtn);
        rectangleBtn.addActionListener(arg0 -> {
            currentShape = "Rectangle";
        });
        ovalBtn = new JButton("Oval");
        controlPanel.add(ovalBtn);
        ovalBtn.addActionListener(arg0 -> {
            currentShape = "Oval";
        });
        circleBtn = new JButton("Circle");
        controlPanel.add(circleBtn);
        circleBtn.addActionListener(arg0 -> {
            currentShape = "Circle";
        });
        textBtn = new JButton("Text");
        controlPanel.add(textBtn);
        textBtn.addActionListener(arg0 -> {
            currentShape = "Text";
        });

        // StrokeSize
        JSeparator sep = new JSeparator();
        sep.setOrientation(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(10, 50));
        controlPanel.add(sep);

        strokeSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 3);
        JLabel sliderLabel = new JLabel("Line Thickness");
        controlPanel.add(sliderLabel);
        controlPanel.add(strokeSizeSlider);
        strokeSizeSlider.addChangeListener(arg0 -> {
            currentStrokeSize = strokeSizeSlider.getValue();
            line.setStrokeSize(currentStrokeSize);
            straightLine.setStrokeSize(currentStrokeSize);
            rectangle.setStrokeSize(currentStrokeSize);
            oval.setStrokeSize(currentStrokeSize);
            circle.setStrokeSize(currentStrokeSize);
        });
        strokeSizeSlider.setMajorTickSpacing(9);
        strokeSizeSlider.setMinorTickSpacing(1);
        strokeSizeSlider.setPaintTicks(true);
        strokeSizeSlider.setPaintLabels(true);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    remoteWhiteBoard.removeUser(username);
                } catch (RemoteException remoteException) {
                    JOptionPane.showMessageDialog(contentPane, "Error sending message");
                }
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }
}
