import java.io.*;

public class Main {

    static private String dir;

    public static void main(String[] args) {



        if(args.length > 0) {

            dir = args[0];
        }
        else{
            dir = ".";
        }

        File file = new File(dir);
        String[] list = file.list();

        if(list!=null){

            File newDir = new File(dir+"/output");
            newDir.mkdir();


            for(String name : list) {
                if(name.endsWith(".dat")){
                    convertFile(name);
                }
            }
        }



    }


    private static void convertFile(String filename){

        String name = filename.substring(0,filename.indexOf("."));


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dir + "/" + filename), "CP1250"));
            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(dir + "/output/"+ name +".csv"),"CP1250");

            String line = reader.readLine();

            while (line != null){

                line = line.replace('\u0001', ';');
//                line = line.replaceAll("\\s+", "");
                line = line.substring(0,line.lastIndexOf(";"));
                fileWriter.append(line);
                fileWriter.append("\n");

                line = reader.readLine();
            }
            reader.close();
            fileWriter.flush();
            fileWriter.close();
            System.out.println(name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
