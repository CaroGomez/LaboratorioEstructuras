/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao.impl;

import ArbolB.ArbolB;
import ArbolB.LlaveEntero;
import co.edu.udea.edatos.laboratorio1.modelo.Taller;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TallerDAO;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carolina
 */
public class FileTallerDAO implements TallerDAO {

    private static final String NOMBRE_ARCHIVO = "Taller.txt";
    private static final int LONGITUD_REGISTRO = 60;
    private static final int CODIGO_LONGITUD = 10;
    private static final int NOMBRE_LONGITUD = 20;
    private static final int TELEFONO_LONGITUD = 10;
    private static final int DIRECCION_LONGITUD = 20;

    private static final Path archivo = Paths.get(NOMBRE_ARCHIVO);
    public static final String ENCODING_WINDOWS = "Cp1252";

    private static final Map<String, Taller> CACHE_TALLER = new HashMap<>();

    @Override
    public List<Taller> listarTalleres() {
        List<Taller> talleres = new ArrayList<>();
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Taller taller = parseTaller(registro);
                talleres.add(taller);
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return talleres;
    }
    
    @Override
    public ArbolB CrearArbol() {
       ArbolB arbol = new ArbolB(2);
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Taller taller = parseTaller(registro);
                 arbol.insert(new LlaveEntero(Integer.parseInt(taller.getCodigo())), "Dirección");
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return arbol;
    }


    @Override
    public Taller consultarTaller(String codigo) {
        Taller taller = CACHE_TALLER.get(codigo);
        if (taller != null) {
            return taller;
        }
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String id = registro.subSequence(0, CODIGO_LONGITUD).toString().trim();
                if (id.equals(codigo)) {
                    taller = parseTaller(registro);
                    CACHE_TALLER.put(codigo, taller);
                    return taller;
                }
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean guardarTaller(Taller taller) {//throws LlaveDuplicadaException {
        if (consultarTaller(taller.getCodigo()) != null) {
           // throw new LlaveDuplicadaException();
           return false;

        }
        String registro = parseTallerString(taller);
        byte[] datos = registro.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(datos);
        try (FileChannel fc = (FileChannel.open(archivo, APPEND))) {
            fc.write(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;

    }

    private String parseTallerString(Taller taller) {
        StringBuilder registro = new StringBuilder();
        registro.append(rellenarCampo(taller.getCodigo(), CODIGO_LONGITUD));
        registro.append(rellenarCampo(taller.getNombre(), NOMBRE_LONGITUD));
        registro.append(rellenarCampo(taller.getTelefono(), TELEFONO_LONGITUD));
        registro.append(rellenarCampo(taller.getDireccion(), DIRECCION_LONGITUD));

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
    private Taller parseTaller(CharBuffer registro) {
        Taller t = new Taller();
        String codigo = registro.subSequence(0, CODIGO_LONGITUD).toString().trim();
        registro.position(CODIGO_LONGITUD);
        registro = registro.slice();
        t.setCodigo(codigo);

        String nombre = registro.subSequence(0, NOMBRE_LONGITUD).toString().trim();
        registro.position(NOMBRE_LONGITUD);
        registro = registro.slice();
        t.setNombre(nombre);

        String telefono = registro.subSequence(0, TELEFONO_LONGITUD).toString().trim();
        registro.position(TELEFONO_LONGITUD);
        registro = registro.slice();
        t.setTelefono(telefono);

        String direccion = registro.subSequence(0, DIRECCION_LONGITUD).toString().trim();
        registro.position(DIRECCION_LONGITUD);
        registro = registro.slice();
        t.setDireccion(direccion);

        return t;
    }

}
