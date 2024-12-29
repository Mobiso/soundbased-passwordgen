import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class javarecordtest {
    public static void main(String[] args) {
        //No error handling
        if(args.length > 0){
            for (String string : args) {
                switch (string) {
                    case "-bitmap":
                        generateAndPrintBitmap(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                        return;
                    case "-passgen":
                    genPasswords(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                    return;
                    default:
                        break;
                }
            }
        }

    }


    private static void genPasswords(int passLength, int amount){
        AmbPassgen passgen = new AmbPassgen();
        try {
            for (int i = 0; i < amount; i++) {
                System.out.println(passgen.generatePasswordString(passLength));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    private static void generateAndPrintBitmap(int width, int height){
        AmbsNumGen numgen = new AmbsNumGen();
        ProgressBar pb = new ProgressBar(height*width);
        BufferedImage bitmap = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        try {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (numgen.getLsb() == 1) {
                        bitmap.setRGB(x, y, Color.WHITE.getRGB()); // Set white pixel
                    } else {
                        bitmap.setRGB(x, y, Color.BLACK.getRGB()); // Set black pixel
                    }
                   pb.step();
                }
            }
            File outputFile = new File(width+"x"+height+".bmp");
            ImageIO.write(bitmap, "bmp", outputFile);
            System.out.println("Black and White Bitmap created successfully: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }
    
}