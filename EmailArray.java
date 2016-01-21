/**
 * Created by Jan on 20.01.2016.
 */
import java.util.Scanner;

public class EmailArray {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] SaveArray;
        String[] FinalArray = new String[2];
        //FinalArray[0] = "Jan Kamburg!MyMail";
        //FinalArray[1] = "Peter Tim!HisMail";
        String Said;
        String[] LS;
        int Elements = 0;
        System.out.println("Welcome to my Database for email addresses!");
        System.out.println("To add a new person type : add Person as Mail");
        System.out.println("To find someones mail type: find Name");
        System.out.println("To see all persons type: all");
        System.out.println("To end the program type: end");
        System.out.println("Thank you for your attention!");
        System.out.println("Created by: Jan Kamburg @20.01.2015");
        System.out.println("----------------------------------------------------------------------------------");
        while (true) {
            Said = scan.nextLine();
            LS = new String[Said.split(" ").length + 1];
            //System.out.println(Said.split(" ").length);
            LS = Said.split(" ");
            if (LS[0].equals("add")) {
                String ToAdd = "";
                for (int i = 1; i < LS.length - 1; i++) {
                    if (!LS[i].equals("as")) {
                        if (i + 1 <= LS.length && !LS[i + 1].equals("as"))
                            ToAdd = ToAdd + LS[i] + " ";
                        else {
                            ToAdd = ToAdd + LS[i];
                        }

                    } else {
                        ToAdd = ToAdd + "!" + LS[LS.length - 1];
                        //System.out.println(ToAdd);
                        try {
                            FinalArray[Elements] = ToAdd;
                        } catch (Exception E) {
                            //System.out.println(E);
                            SaveArray = new String[FinalArray.length];
                            SaveArray = FinalArray;
                            FinalArray = new String[SaveArray.length+1];
                            for (int x = 0; x < SaveArray.length; x++) {
                                FinalArray[x] = SaveArray[x];
                            }
                            FinalArray[Elements] = ToAdd;
                        }
                        Elements = Elements+1;
                        System.out.println("Added!");
                        break;
                    }
                }

            }
            else if (LS[0].equals("find")) {
                String[] Name = new String[LS.length];
                String[] Split = new String[2];
                Name = Said.split("find ");
                boolean Found = false;
                for (int i = 0; i < FinalArray.length+1; i++) {
                    Split = FinalArray[i].split("!");
                    if(Split[0].toLowerCase().equals(Name[1].toLowerCase())){
                        System.out.println(Name[1]+"'s EMail is: "+ Split[1]);
                        Found = true;
                        break;
                    }

                }
                if(!Found){
                    System.out.println("There is no "+Name[1]+" in our database!");
                }
            }
            else if (LS[0].equals("all")) {
                String[] ArraySplit = new String[2];
                //System.out.println(FinalArray.length);
                for (int i = 0; i < FinalArray.length; i++) {
                    //System.out.println(i);
                    if (FinalArray[i] != null){
                        ArraySplit = FinalArray[i].split("!");
                        System.out.println(ArraySplit[0]+"'s EMail is: "+ ArraySplit[1]);
                    }
                }
            }
            else if (LS[0].equals("end")) {
                System.out.println("Program ended...");
                break;
            }
        }
    }
}
