package co.edu.udea.edatos.laboratorio1.modelo.dao.impl;

import ArbolB.ArbolB;
import ArbolB.LlaveEntero;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileConductorDAO implements ConductorDAO {

    private static final String NOMBRE_ARCHIVO = "Conductor.txt";
    private static final int LONGITUD_REGISTRO = 70;
    private static final int IDENTIFICACION_LONGITUD = 10;
    private static final int NOMBRES_LONGITUD = 20;
    private static final int APELLIDOS_LONGITUD = 20;
    private static final int GENERO_LONGITUD = 1;
    private static final int EDAD_LONGITUD = 3;
    private static final int TELEFONO_LONGITUD = 10;
    private static final int CODTURNO_LONGITUD = 6;

    private static final Path archivo = Paths.get(NOMBRE_ARCHIVO);
    public static final String ENCODING_WINDOWS = "Cp1252";

    private static final Map<String, Integer> conductorIndice = new HashMap<>();
    private static final ArbolB ARBOL = new ArbolB(2);
    
    public FileConductorDAO() {
        if (!Files.exists(archivo)) {
            try {
                Files.createFile(archivo);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        crearIndice();
    }
    
    @Override
    public ArbolB retornarArbol(){
        return ARBOL;
    }
    
    private void crearIndice() {
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            int posicion = 0;
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String identificacion = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
                //INSERTA EN EL INDICE: HASH O ÁRBOL (LO QUE SEA)
                ARBOL.insert(new LlaveEntero(Integer.parseInt(identificacion)), posicion);
                conductorIndice.put(identificacion, posicion);
                posicion++;
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @Override
    public List<Conductor> listarConductores() {
        List<Conductor> conductores = new ArrayList<>();
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Conductor conductor = parseConductor(registro);
                conductores.add(conductor);
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return conductores;
    }

    @Override
    public Conductor consultarConductor(String identificacion) {
       
        Integer dir = (Integer)ARBOL.search(new LlaveEntero(Integer.parseInt(identificacion)));
        if (dir == null) {
            return null;
        }
        return consultarConductorxId(dir);
    }
    
        private Conductor consultarConductorxId(int id) {
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            sbc.position(id * LONGITUD_REGISTRO);
            sbc.read(buf);
            buf.rewind();
            CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
            Conductor conductor = parseConductor(registro);
            buf.flip();
            return conductor;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
        

    @Override
    public boolean guardarConductor(Conductor conductor) {
        if (consultarConductor(conductor.getId()) != null) {
            return false;
        }
        String registro = parseConductorString(conductor);
        byte[] datos = registro.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(datos);
        try (FileChannel fc = (FileChannel.open(archivo, APPEND))) {
            fc.write(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
    }

    private String parseConductorString(Conductor conductor) {
        StringBuilder registro = new StringBuilder();
        registro.append(rellenarCampo(conductor.getId(), IDENTIFICACION_LONGITUD));
        registro.append(rellenarCampo(conductor.getNombres(), NOMBRES_LONGITUD));
        registro.append(rellenarCampo(conductor.getApellidos(), APELLIDOS_LONGITUD));
        registro.append(rellenarCampo(Character.toString(conductor.getGenero()), GENERO_LONGITUD));
        registro.append(rellenarCampo(conductor.getEdad(), EDAD_LONGITUD));
        registro.append(rellenarCampo(conductor.getTelefono(), TELEFONO_LONGITUD));
        registro.append(rellenarCampo(conductor.getCodTurno(), CODTURNO_LONGITUD));
        return registro.toString();
    }

    private String rellenarCampo(String campo, int tamanio) {
        if (campo.length() > tamanio) {
            return campo.substring(0, tamanio);
        }
        return String.format("%1$-" + tamanio + "s", campo);
    }

    /**
     * Convierte un registro almacenado en un CharBuffer a un Objeto de Persona
     *
     * @param registro
     * @return
     */
    private Conductor parseConductor(CharBuffer registro) {
        Conductor c = new Conductor();
        String identificacion = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
        registro.position(IDENTIFICACION_LONGITUD);
        registro = registro.slice();
        c.setId(identificacion);

        String nombres = registro.subSequence(0, NOMBRES_LONGITUD).toString().trim();
        registro.position(NOMBRES_LONGITUD);
        registro = registro.slice();
        c.setNombres(nombres);

        String apellidos = registro.subSequence(0, APELLIDOS_LONGITUD).toString().trim();
        registro.position(APELLIDOS_LONGITUD);
        registro = registro.slice();
        c.setApellidos(apellidos);

        char genero = registro.subSequence(0, GENERO_LONGITUD).toString().trim().charAt(0);
        registro.position(GENERO_LONGITUD);
        registro = registro.slice();
        c.setGenero(genero);

        String edad = registro.subSequence(0, EDAD_LONGITUD).toString().trim();
        registro.position(EDAD_LONGITUD);
        registro = registro.slice();
        c.setEdad(edad);

        String telefono = registro.subSequence(0, TELEFONO_LONGITUD).toString().trim();
        registro.position(TELEFONO_LONGITUD);
        registro = registro.slice();
        c.setTelefono(telefono);

        String codTurno = registro.toString().trim();
        c.setCodTurno(codTurno);

        return c;
    }

}
