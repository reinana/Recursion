interface FlightInterface {

    public String getFlightNum();
    public String getOrigin();
    public String getDestination();
    public String getDate();
    public Seat getSeat(int rowIdx, int seatIdx);
    public Seat getSeat(String seatNumber);
    public int getPassengerCount();
    // public boolean readConfirmationFile();
    public String getSeatInfo();

}

class Flight implements FlightInterface{
    public static final char[] COLUMN = new char[]{'A','B','C','D','E','F'};
    public static final int ROW = 40;

    private String flightNumber;
    private String origin;
    private String destination;
    private String date; 
    private Seat[] seats;

    public Flight(String flightNumber, String origin, String destination, String date){
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.seats = generateSeat();
    }
    public Seat[] generateSeat(){
        Seat[] seats = new Seat[COLUMN.length * ROW];
        for(int i=0; i<ROW; i++){
            for(int j=0; j<COLUMN.length; j++){
                String seatNumber = String.valueOf(i+1) + COLUMN[j];
                seats[i*COLUMN.length+j] = new Seat(seatNumber);
            }
        }
        return seats;
    }
    public String getFlightNum(){
        return this.flightNumber;
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }
    public String getDate(){
        return this.date;
    }
    public Seat[] getSeat(){
        return this.seats;
    }
    public Seat getSeat(int rowIdx, int seatIdx){
        return seats[rowIdx*COLUMN.length+seatIdx];
    }
    public Seat getSeat(String seatNumber){
        int rowIdx;
        int seatIdx=0;
        if(seatNumber.length()>2){
            rowIdx = Integer.parseInt(seatNumber.substring(0,2));
        }else{
            rowIdx = Integer.parseInt(seatNumber.substring(0,1));
        }
        for(int i=0; i<COLUMN.length; i++){
            if(seatNumber.substring(seatNumber.length()-1).equals(String.valueOf(COLUMN[i]))){
                seatIdx = i;
            }
        }
        return this.seats[(rowIdx-1)*COLUMN.length+seatIdx];
    }

    public int getPassengerCount(){
        int count = 0;
        for(Seat seat: this.getSeat()){
            if(seat.getCustConf()!=null) count+=1;
        }
        return count;
    }
    // public boolean readConfirmationFile(){
    //     Scanner scanner = 
    // };わからないtextfileをとってくるメソッド
    public String getSeatInfo(){
        String allSeatInfo = "  ";
        for(int i=1; i<=COLUMN.length; i++){
            if(i%3==0) allSeatInfo+=COLUMN[i-1]+" ";
            else allSeatInfo+=COLUMN[i-1];
        }
        for(int i=0; i<ROW;i++){
            allSeatInfo+="\n" + (i+1);
            for(int j=0; j<COLUMN.length; j++){
                if(j==3) allSeatInfo+=" ";
                if(this.getSeat()[i*COLUMN.length+j].getCustConf()!=null){
                    allSeatInfo += "X";
                }
                else{
                    allSeatInfo +="_";
                }
            }
            
        }
        return allSeatInfo;
    }
    public String toString(){
        String flightInfo = "";
        flightInfo += "Flight Infomation: \n Flight Number: " + this.getFlightNum() +"\n"+" From " + this.getOrigin() +" To " + this.getDestination() + "\n" + " Date: " + this.getDate() + "\n PassengerCount: " + this.getPassengerCount();
        return flightInfo;
    }
}

interface SeatInterface {

    public enum MealType {GOURMET, FULL, SNACK,};
    public String getNumber();
    public double getPrice();
    public int getRecline();
    public MealType getMealType();
    public CustConf getCustConf();
    public void setPrice(double price);
    public void setRecline(int recline);
    public void setMealType(MealType mealType);
    public void setCustConf(CustConf custConf);

}
class Seat implements SeatInterface{
    public String seatNumber;
    private double price;
    private int recline;
    private MealType mealType;
    private CustConf custConf;

    public Seat(String seatNumber){
        this.seatNumber=seatNumber;
    }
    public Seat(String seatNumber, double price, int recline, MealType mealType,CustConf custConf){
        this.seatNumber = seatNumber;
        this.price = price;
        this.recline = recline;
        this.mealType = mealType;
        this.custConf = custConf;
    }
    public String getNumber(){
        return this.seatNumber;
    }
    public double getPrice(){
        return this.price;
    }
    public int getRecline(){
        return this.recline;
    }
    public MealType getMealType(){
        return this.mealType;
    }
    public CustConf getCustConf(){
        return this.custConf;
    }
    public void setPrice(double price){
        //例外処理  >0.0  ?????
        this.price = price;
    }
    public void setRecline(int recline){
        //例外処理　0 <  recline  < 180 ???
        this.recline=recline;
    }
    public void setMealType(MealType mealType){
        //enum ????
        this.mealType = mealType;
    }
    public void setCustConf(CustConf custConf){
        this.custConf = custConf;
    }
    public void cancelCustConf(){
        this.custConf = null;
    }
    public String toString(){
        String seatInfo = "SeatInfomation: \n ";
        seatInfo += this.getNumber()+ "\n $ " + this.getPrice() + "\n " + this.getRecline() + "\n MealType: " +this.getMealType() +"\n " + this.custConf.getName() + "\n " + this.custConf.getConfirmationCode();

        return seatInfo;
    }
}

interface CustConfInterface {

    public String getName();
    public String getConfirmationCode();

}
class CustConf implements CustConfInterface{
    private String customerName;
    private String confirmationNumber;//6文字英数字大文字

    public CustConf(String customerName, String confirmationNumber){
        this.customerName = customerName;
        this.confirmationNumber = confirmationNumber;
    }

    public String getName(){
        return this.customerName;
    }
    public String getConfirmationCode(){
        return this.confirmationNumber;
    }

}

class Main{

    public static void main(String[] args){
        Flight jal123 = new Flight("jal123", "HND", "CDG", "08/01/2021");
        // System.out.println(jal123.getSeat()[0].seatNumber);
        // System.out.println(jal123.getSeat(1,0).seatNumber);
        // System.out.println(jal123.getSeat("10A").seatNumber);
        // System.out.println(jal123.getSeat("1A").seatNumber);
        CustConf custConf1 = new CustConf("reina", "ABCdef");
        Seat seat1 = jal123.getSeat("1A");
        seat1.setCustConf(custConf1);
        seat1.setPrice(200.5);
        seat1.setRecline(180);
        CustConf custConf2 = new CustConf("rabi", "AAAAAA");
        Seat seat2 = jal123.getSeat("1B");
        seat2.setCustConf(custConf2);
        seat2.setPrice(200.5);
        seat2.setRecline(180);
        CustConf custConf3 = new CustConf("rabirabi", "AAcAAA");
        Seat seat3 = jal123.getSeat("10B");
        seat3.setCustConf(custConf3);
        seat3.setPrice(200.5);
        seat3.setRecline(180);
        CustConf custConf4 = new CustConf("rabirabi", "AAcAAA");
        Seat seat4 = jal123.getSeat("6F");
        seat4.setCustConf(custConf4);
        
        // seat1.setMealType("GOURMET);
        System.out.println(seat1);
        // System.out.println(seat1);
        System.out.println(jal123);
        System.out.println(jal123.getSeatInfo());
        seat3.cancelCustConf();
        System.out.println(jal123.getSeatInfo());

        


    }

}
