
import javax.swing.*;
import java.awt.*;

/**
 * Created by Jan on 14.03.2016.
 */
class ParticleDrawer extends JComponent {
    public static Particle[] AllStuff;
    public static boolean Update = false;
    public static long refrTime = 50;
    public static int Center = 497;
    public static int Runed = 0;
    public static boolean HasUpdated = false;
    public void paint(Graphics g) {
        g.fillRect(1, 1, 992,992);
        g.setColor(Color.WHITE);
        g.drawOval(2,2,990,990);
        g.setColor(Color.BLUE);

        if(Update){
            Particle.RoundsRun.setText("Simulations run: "+Runed);


            Runed++;
            double TEnergy = Particle.totalPe(AllStuff);
            Particle.LabelEnergy.setText("The total energy is: "+TEnergy);
            ParticleDrawer.HasUpdated = false;
            for (int i = 0; i < AllStuff.length; i++) {
                Particle.moveToSmallest(AllStuff[i]);

               /** boolean PosChanged1 = true;
                boolean PosChanged2 = true;
                Particle.moveLeft(AllStuff[i]);
                if (Particle.totalPe(AllStuff) < TEnergy) {
                    TEnergy = Particle.totalPe(AllStuff);
                    //System.out.println("Smaller RIGHT");
                } else {
                    Particle.moveRight(AllStuff[i]);
                    Particle.moveRight(AllStuff[i]);
                    if (Particle.totalPe(AllStuff) < TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller LEFT");
                    } else {
                        Particle.moveLeft(AllStuff[i]);
                        PosChanged1 = false;
                        //System.out.println("NO left/right");
                    }
                }
                Particle.moveUp(AllStuff[i]);
                if (Particle.totalPe(AllStuff) < TEnergy) {
                    TEnergy = Particle.totalPe(AllStuff);
                    //System.out.println("Smaller UP");
                } else {
                    Particle.moveDown(AllStuff[i]);
                    Particle.moveDown(AllStuff[i]);
                    if (Particle.totalPe(AllStuff)< TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller DOWN");
                    } else {
                        Particle.moveUp(AllStuff[i]);
                        //System.out.println("NO up/down");
                        PosChanged2 = false;
                    }
                }
                if(!(PosChanged1) & !(PosChanged2)){
                    Particle.moveDiagUpRight(AllStuff[i]);
                    if (Particle.totalPe(AllStuff) < TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller UP");
                    } else {
                        Particle.moveDiagDownLeft(AllStuff[i]);
                    }
                    Particle.moveDiagUpLeft(AllStuff[i]);
                    if (Particle.totalPe(AllStuff) < TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller UP");
                    } else {
                        Particle.moveDiagDownRight(AllStuff[i]);
                    }
                    Particle.moveDiagDownRight(AllStuff[i]);
                    if (Particle.totalPe(AllStuff) < TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller UP");
                    } else {
                        Particle.moveDiagUpLeft(AllStuff[i]);
                    }
                    Particle.moveDiagDownLeft(AllStuff[i]);
                    if (Particle.totalPe(AllStuff) < TEnergy) {
                        TEnergy = Particle.totalPe(AllStuff);
                        //System.out.println("Smaller UP");
                    } else {
                        Particle.moveDiagUpRight(AllStuff[i]);
                    }
                }
                */

                //double[] LOL = Particle.grad(i, AllStuff, 1);
                //AllStuff[i].setPosition(LOL[0],LOL[1],0);
            }
            if(!HasUpdated){
                Update = false;
                JOptionPane.showMessageDialog(Particle.main,
                        "The Simulation has finished",
                        "Simulation Done",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        for (int i = 0; i < AllStuff.length; i++) {
            g.fillOval(-15+(int) AllStuff[i].x, -15+(int) AllStuff[i].y, 30, 30);
        }
        //System.out.println("Painting");
    repaint(refrTime);
}

}
