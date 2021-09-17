import java.util.Random;

public class ProducerAndConsumer {

    // create a buffer object (stack/heap),
    // create a producer thread creator (check if stack is full, if not place items)
    // create a consumer thread (check if the stack is empty, if not look at what's in it).

    public static String charToName(char c) {
        return switch(c) {
            case 'a' -> "aay";
            case 'b' -> "bee";
            case 'c' -> "see";
            case 'd' -> "dee";
            case 'e' -> "eee";
            case 'f' -> "eff";
            case 'g' -> "gee";
            case 'h' -> "ehch";
            case 'i' -> "eye";
            case 'j' -> "jay";
            case 'k' -> "kay";
            case 'l' -> "ell";
            case 'm' -> "emm";
            case 'n' -> "enn";
            case 'o' -> "ohh";
            case 'p' -> "pee";
            case 'q' -> "queue";
            case 'r' -> "arh";
            case 's' -> "ess";
            case 't' -> "tee";
            case 'u' -> "you";
            case 'v' -> "vee";
            case 'w' -> "double-you";
            case 'x' -> "eks";
            case 'y' -> "why";
            case 'z' -> "zee -> program ending";
            default -> "well this is odd...";
        };
    }

    public static char bufferPop(StringBuffer charBuffer) {
        char res = charBuffer.charAt(0);
        charBuffer.deleteCharAt(0);
        return res;
    }

    public static void main(String[] args) {
        StringBuffer charBuffer = new StringBuffer();

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting the random character producer thread...");
                Random rand = new Random();
                char ch = 'a';
                do {
                    // if the charBuffer has less than 100 characters, add new character
                    if (charBuffer.length() < 100) {
                        // pick a random number between 97 and 122 (ASCII 'a' - 'z')
                        int randNum = rand.nextInt(26)+97;
                        // convert the random number to a char
                        ch = (char)randNum;
                        // append the char to the charBuffer
                        charBuffer.append(ch);
                    }

                    // Let the thread sleep for an arbitrary time (shorter than
                    // the consumer to test buffer build up)
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (ch != 'z');    // exit the loop once a z shows up
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting the random character consumer thread...");

                char ch = 'a';

                // do loop to move through the characters of the charBuffer until none are left
                do {
                    // try block to pull the next character out of the buffer and
                    // write its name to the console.
                    try {
                        ch = bufferPop(charBuffer);
                        System.out.println(charToName(ch));
                        Thread.sleep(200);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                } while (!charBuffer.isEmpty());
            }
        };

        // creating the producer thread
        new Thread(producer).start();

        // try block to sleep for one second, to give the producer a head start
        try {
            Thread.sleep(1000);
        } catch (Exception e) { e.printStackTrace(); }

        // creating the consumer thread to begin processing the buffer
        new Thread(consumer).start();

    }
}
