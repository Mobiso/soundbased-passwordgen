public class AmbPassgen {
    private AmbsNumGen numgen;
    public AmbPassgen(){
        numgen = new AmbsNumGen();
    }

    public String generatePasswordString(int passLength)throws Exception{
         StringBuilder pass = new StringBuilder();
         try {
            for (int i = 0; i < passLength; i++) {
                char character = (char) numgen.getNum();
                pass.append(character);
             }
         } catch (Exception e) {
            throw e;
         }
        
         return pass.toString();
        
    }
}