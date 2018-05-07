/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import java.io.FileWriter;

/**
 *
 * @author Jefry
 */
public class VerArbol {

    public void mostrarArbol(ArbolB tree) {

        System.out.println("prueba");

       // String tempFolder = "C:\\Users\\Jefry\\Desktop";
        
        String tempFolder = System.getProperty("java.io.tmpdir");
       

        try {

            FileWriter f = new FileWriter(tempFolder + "grafo.txt");

            f.write(tree.toDot());

            f.close();

           

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        doDot(tempFolder + "grafo.txt", tempFolder + "grafo.jpg");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        

    }

    static void doDot(String pInput, String pOutput) {
        try {

            String dotPath
                    = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            String fileInputPath = pInput;
            String fileOutputPath = pOutput;

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

          try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
         
        try {

            String[] cmd = new String[4];
            cmd[0] = "cmd";
            cmd[1] = "/C";
            cmd[2] = "start";
            cmd[3] = pOutput;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

    }
}
