public class Netmask {
    private final int cidr;
    private final int mask;

    //Constructor

    public Netmask(int net) {
        if (net < 0 || net > 32 ) {
            throw new IllegalArgumentException("Invalid CIDR: " + net);
        }
        this.cidr = net;
        this.mask = cidrToSubmask(net);
    }
    public int getCidr(){
        return cidr;
    }
    public int getMask(){
        return mask;
    }
    public int cidrToSubmask(int net){
        return 0xFFFFFFFF << (32 - net);
    }
    // Convert mask from 32-bit int to Subnetmask string
    public String toSubnetmaskString() {
        return ((mask >>> 24) & 0xFF) + "." +
                ((mask >>> 16) & 0xFF) + "." +
                ((mask >>> 8) & 0xFF) + "." +
                (mask & 0xFF);
    }

    // Convert mask from 32-bit to binary string
    public String toBinaryString() {
        StringBuilder sb = new StringBuilder();
        for (int shift = 24; shift >= 0; shift -= 8) {
            int octet = (mask >>> shift) & 0xFF;
            String bin = String.format("%8s", Integer.toBinaryString(octet))
                    .replace(' ', '0');
            sb.append(bin);
            if (shift > 0) sb.append(".");
        }
        return sb.toString();
    }
}