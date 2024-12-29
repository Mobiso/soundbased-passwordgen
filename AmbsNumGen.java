import javax.sound.sampled.*;
public class AmbsNumGen {
    private final int NUMBER_OF_BITS_IN_NUM = 7;
    private final int NUMBER_OF_BYTES_TO_CAPTURE = 4;
    private AudioFormat format;
    private DataLine.Info info;
    private TargetDataLine line;
    public AmbsNumGen(){
        format = new AudioFormat(44100, 16, 2, true, true);
        info = new DataLine.Info(TargetDataLine.class, format);
        try {
            initLine();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    //Makes code 100000000% faster to have init instead of opening a new line for each
    //recordAmbientBytes call 
    private void initLine()throws Exception{
        if (!AudioSystem.isLineSupported(info)) {
            throw new Exception("Microphone not supported");
         }
        line = (TargetDataLine) AudioSystem.getLine(info);
        //Open new line with 4 byte buffer. Larger made it crash if not flushed freq lol
        line.open(format,NUMBER_OF_BYTES_TO_CAPTURE);
    }

    private byte[] recordAmbientBytes() throws Exception{
        try {
            byte[] soundBytes = new byte[NUMBER_OF_BYTES_TO_CAPTURE];
            line.start();
            line.read(soundBytes, 0, NUMBER_OF_BYTES_TO_CAPTURE);
            line.stop();
            return soundBytes;

        } catch (Exception e) {
            System.out.println("Something fucked happened while recording");
            throw e;
        }

    }

    public int getNum()throws Exception{
        try {
            int num = 0;
            for (int i = 0; i < NUMBER_OF_BITS_IN_NUM; i++) {
                int bit = getLsb();
                //Add 2^i if lsb bit is 1 else add 0. lsb varies most
                //Convert binary to base10
                num += Math.pow(2,i)*bit;
            }
            //Avoid non visiable characters. Not very random of you tho bro
            if(num < 33)
                num = num + 33; 

            return num;
        } catch (Exception e) {
            throw e;
        }
       
    }

    public int getLsb()throws Exception{
        try {
    
                byte[] recordedBytes = recordAmbientBytes();
                //Extract lsb by masking
                return recordedBytes[recordedBytes.length-1] & 1;
            }
             catch (Exception e) {
                throw e;
        }
       
    }

    
}