/**
 * Created by Jan on 22.02.2016.
 */
public class Students {
    private String Name;
    private String id;
    private String Country;
    private final static char[] Letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    public Students(String SName){
        this.Name = SName;
        this.id = "NONE";
        this.Country = "NN";
    }
    public String getName(){
        return this.Name;
    }
    public String getID(){
        return this.id;
    }
    public void setUniqueID(){
        if(this.id == "NONE"){
            String UID = this.Country.substring(0,2).toUpperCase();//First two letters are from the country
            UID = UID+(this.Country+this.Name).hashCode();//Add a hash code of the country and the students name
            String[] Split = this.Name.split(" ");
            for(int i=0;i<Split.length;i++){
                UID = UID+Split[i].charAt(0);//Add the first letter of each name part
            }
            this.id = UID;
        }
    }
    public void setID(String newID){
        this.id = newID;
    }
    public void setName( String a_name){
        this.Name = a_name;
    }
    public void creds(){
        System.out.println( "Hello sir.  My name is " + this.getName() + " and my ID number is "+ this.id);
    }

    public void intro(Students other){
        System.out.println("Hello " + other.getName() + ". My name is " + this.getName() + ".");
    }
    public void setCountry(String C){
        this.Country = C;
    }
    public String getCountry(){
        return this.Country;
    }
}
