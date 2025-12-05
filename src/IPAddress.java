public class IPAddress {

    //IP address value
    private final int ipaddr;

    //Constructor

    public IPAddress(String ip){
        if(!isValidIP(ip)){
            throw new IllegalArgumentException("Invalid IP address " + ip);
        }
        this.ipaddr=fromStringtoInt(ip);
    }
    public IPAddress(int ip){
        this.ipaddr=ip;
    }

    //Get ip address value int and string
    //Convert from int to string and from string to int

   public int getIP(){
        return ipaddr;
   }

   public static boolean isValidIP(String ip) {
       //IP cannot be null -> first condition
       //IP must have only numbers -> number format exception
       //parts devided by '.' ->split regex
       //Only 4 parts -> if it is not null, check for number of parts
       //Check if parts are emtpy or not
       //Each part can have values from 0 to 255
       //values that start with 0 or 00 can be accepted.
       if (ip == null)
           return false;

       String[] parts = ip.split("\\.");
       if (parts.length != 4)
           return false;
       try {
           for (String part : parts) {
               if (part == null)
                   return false;

               int nrIP = Integer.parseInt(part);
               if (nrIP < 0 || nrIP > 256)
                   return false;
           }
       } catch (NumberFormatException e) {
           return false;
       }
       return true;
   }

   public  String convertIpToBinary(String ip) {
        String[] parts = ip.split("\\.");
        StringBuilder binaryIp = new StringBuilder();

        for (String part : parts) {
            int decimalParts = Integer.parseInt(part);
            String binaryParts = Integer.toBinaryString(decimalParts);

            // Pad with leading zeros to ensure 8 bits
            while (binaryParts.length() < 8) {
                binaryParts= "0" + binaryParts;
            }
            binaryIp.append(binaryParts).append(" "); // Use space as separator
            }
        return binaryIp.toString().trim(); // Trim trailing space
        }
   public int fromStringtoInt(String ip){
       String[] parts = ip.split("\\.");
       int result = 0;
       for (String part : parts) {
           result = (result << 8) | Integer.parseInt(part);
       }
       return result;
   }

   public String fromIntToString(int ipaddr){
        return  (ipaddr >>> 24 & 0xFF) + "." +
                (ipaddr >>> 16 & 0xFF) + "." +
                (ipaddr >>> 8  & 0xFF) + "." +
                (ipaddr & 0xFF);
    }
    public String toString(){  //Override
        return fromIntToString(ipaddr);
    }
}
