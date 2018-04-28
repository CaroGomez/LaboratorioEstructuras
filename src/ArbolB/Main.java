package ArbolB;

import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {

        System.out.println("prueba");

        String tempFolder = "C:\\Users\\Jefry\\Desktop\\";

        ArbolB tree = new ArbolB(2);
        ArbolB treeS = new ArbolB(2);

        int[] values = {100, 101, 40, 30, 25, 26, 15, 99, 205, 360, 1, 2, 3, 5, 2345, 67, 45, 689, 34, 67, 98, 33};

        String[] valores
                = {"juan", "pedro", "andres", "felipe", "sergio", "bobo", "juana", "federico", "tarado", "zapata", "andrea", "zara", "amapro", "rata", "piito", "uribe", "paraco", "hp",
                     "nata", "nato", "caro", "carmen"};

        for (int i = 0; i < values.length; i++) {
            tree.insert(new LlaveEntero(values[i]), "Dummy " + i);
        }

        for (int i = 0; i < valores.length; i++) {
            treeS.insert(new LlaveCadena(valores[i]), "Dummy " + i);
        }

        try {

            FileWriter f = new FileWriter(tempFolder + "grafo1.txt");

            f.write(tree.toDot());

            f.close();

            f = new FileWriter(tempFolder + "grafo2.txt");

            f.write(treeS.toDot());

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

        doDot(tempFolder + "grafo1.txt", tempFolder + "grafo1.jpg");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        doDot(tempFolder + "grafo2.txt", tempFolder + "grafo2.jpg");

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
