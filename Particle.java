import javafx.scene.paint.Stop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

/**
 * Created by Jan on 10.03.2016.
 */




public class Particle {
    static final boolean[] RunSimulation = {false};
    final static double K = 9 * power(10,9);
    final static double E = 1.6 * power(10,-19);

    public static JLabel RoundsRun;
    public static JLabel Status;
    public static JLabel LabelEnergy;


    double mass;
    double charge;
    String name;

    double x;
    double y;
    double z;



    public Particle(String name, double mass, double charge){

        this.name = name;
        this.mass = mass;
        this.charge = charge;

    }

    public static void main(String[] args){

        Particle Pro1 = new Particle("Proton", 1.67*power(10, -27), E);
        Particle Elec1 = new Particle("Electron", 9.1*power(10,-31), -E);

        double Pe = potential_S(Pro1,Elec1,0.53*power(10,-10));

        print(String.valueOf(Pe));

        double pens = Pro1.potential(Elec1, 0.53*power(10,-10));

        print(String.valueOf(pens));


        int Size = 100;
        int Area = 500;
        Particle[] ParticleArray = new Particle[Size];
        for(int i=0;i <Size;i++){
            ParticleArray[i] = new Particle("Particle"+String.valueOf(i), 1.67*power(10, -27), E);
            //ParticleArray[i].setPosition(Math.random()*991,Math.random()*800, Math.random()*Area);
            double RAngle = Math.random() * Math.PI * 2;
            double length = Math.random();

            double x = ParticleDrawer.Center + Math.cos(RAngle) * 480 * length;
            double y = ParticleDrawer.Center + Math.sin(RAngle) * 480 * length;
            ParticleArray[i].setPosition(x,y,Math.random());
        }
        drawPositions(ParticleArray);
        print(String.valueOf(totalPe(ParticleArray)));
    }

    public static void drawPositions(Particle[] P){

        JFrame main = new JFrame("Particle Simulation");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton StartB = new JButton("Start");
        main.add(StartB);
        JButton StopB = new JButton("Stop");
        StopB.setEnabled(false);
        main.add(StopB);

        RoundsRun = new JLabel("Test");
        Status = new JLabel("Test2");
        LabelEnergy = new JLabel("Test3");

        main.add(RoundsRun);
        main.add(Status);
        main.add(LabelEnergy);

        ParticleDrawer.AllStuff = P;
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(1000,1200);
        main.setBounds(ss.width/2 - frameSize.width/2, ss.height/2 - frameSize.height/2,frameSize.width,frameSize.height);
        main.setResizable(false);
        main.setMinimumSize(new Dimension(1000,1200));
        main.getContentPane().add(new ParticleDrawer());
        main.setVisible(true);
        main.setLayout(null);
        main.pack();

        Status.setBounds(380, 1000,500,50);
        Status.setText("Simulation is : Stopped");
        Status.setFont(new Font("Arial", Font.PLAIN, 30));

        RoundsRun.setBounds(380, 1050,500,50);
        RoundsRun.setText("Simulations run: 0");
        RoundsRun.setFont(new Font("Arial", Font.PLAIN, 30));


        LabelEnergy.setBounds(380, 1100,800,50);
        LabelEnergy.setText("The total energy is: 0");
        LabelEnergy.setFont(new Font("Arial", Font.PLAIN, 30));


        StartB.setBounds(1,1010,350,60);
        StartB.setFont(new Font("Arial", Font.PLAIN, 40));
        StartB.setBackground(Color.GREEN);
        StartB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                StopB.setEnabled(true);
                StartB.setEnabled(false);
                RunSimulation[0] = true;
                ParticleDrawer.Update = true;
                Status.setText("Simulation is : Running");
            }
        });

        StopB.setBounds(1,1085,350,60);
        StopB.setFont(new Font("Arial", Font.PLAIN, 40));
        StopB.setBackground(Color.RED);
        StopB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                StartB.setEnabled(true);
                StopB.setEnabled(false);
                RunSimulation[0] = false;
                ParticleDrawer.Update = false;
                Status.setText("Simulation is : Stopped");

            }
        });





    }

    public static void moveArround(Particle[] P){
        while(RunSimulation[0]){
            double TEnergy = totalPe(P);
            for(int i=0;i <P.length;i++){
                moveLeft(P[i]);
                if(totalPe(P) < TEnergy){
                    TEnergy = totalPe(P);
                    //System.out.println("Smaller RIGHT");
                }
                else{
                    moveRight(P[i]);
                    moveRight(P[i]);
                    if(totalPe(P) < TEnergy){
                        TEnergy = totalPe(P);
                        //System.out.println("Smaller LEFT");
                    }
                    else{
                        moveLeft(P[i]);
                        //System.out.println("NO left/right");
                    }
                }
                moveUp(P[i]);
                if(totalPe(P) < TEnergy){
                    TEnergy = totalPe(P);
                    //System.out.println("Smaller UP");
                }
                else{
                    moveDown(P[i]);
                    moveDown(P[i]);
                    if(totalPe(P) < TEnergy){
                        TEnergy = totalPe(P);
                        //System.out.println("Smaller DOWN");
                    }
                    else{
                        moveUp(P[i]);
                        //System.out.println("NO up/down");
                    }
                }

            }


        }
        //System.out.println(P[1].x);
        ParticleDrawer.AllStuff = P;
    }

    public boolean isInCircle(){
        int Center = ParticleDrawer.Center;
        if ((Math.sqrt((Center - this.x)*(Center - this.x)+(Center - this.y)*(Center - this.y))) > 497){
            return false;
        }
        else{
            return true;
        }
    }

    public static void moveRight(Particle P){
        P.x = P.x +1;
        if(!P.isInCircle()) {
            P.x = P.x -1;
        }


    }
    public static void moveLeft(Particle P){
        P.x = P.x -1;
        if(!P.isInCircle()) {
            P.x = P.x +1;
        }
    }
    public static void moveUp(Particle P){
        P.y = P.y -1;
        if(!P.isInCircle()) {
            P.y = P.y +1;
        }
    }
    public static void moveDown(Particle P){
        P.y = P.y +1;
        if(!P.isInCircle()) {
            P.y = P.y -1;
        }
    }


    public static double totalPe(Particle[] particles){
        double Final = 0.0;
        for(int i=0;i < particles.length;i++){
            for(int x=i;x < particles.length;x++){
                if(!(x<=i)) {
                    Final = Final + particles[i].betterPotential(particles[x]);
                    //System.out.println(String.valueOf(i)+ "---"+ String.valueOf(x));
                    //System.out.println(Final);
                }
            }
        }
        return Final;
    }

    public void setPosition(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public double distance(Particle B){
        return Math.sqrt((B.x - this.x)*(B.x - this.x)+(B.y - this.y)*(B.y - this.y)+(B.z - this.z)*(B.z - this.z));
    }

    public double potential(Particle b, double distance){

        double x = (K * this.charge * b.charge)/distance;

        return x;

    }

    public double betterPotential(Particle b){

        double x = (K * this.charge * b.charge)/this.distance(b);

        return x;

    }

    public static double potential_S(Particle a, Particle b, double distance){

        double x = (K * a.charge * b.charge)/distance;

        return x;

    }

    public static double power(int a, int b){

        double x = Math.pow(a,b);

        return x;

    }

    public static void print(String s){

        System.out.println(s);

    }

}
