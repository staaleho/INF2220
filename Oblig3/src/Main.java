public class Main {

    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Insufficient parameters. Provide a needle and a haystack.");
            System.exit(1);
        }

        String needle = args[0];
        if(needle.length() < 1){
            System.out.println("Needle is zero length.");
            System.exit(1);
        }
        String haystack = args[1];
        if(haystack.length() < 1){
            System.out.println("Haystack is zero length.");
            System.exit(1);
        }

        if(haystack.length() < needle.length()){
            System.out.println("Needle is longer than haystack.");
            System.exit(1);
        }

        try {
            BMH bmh = new BMH(needle, haystack);
        }catch (Exception e){
            System.exit(1);
        }


    }
}
