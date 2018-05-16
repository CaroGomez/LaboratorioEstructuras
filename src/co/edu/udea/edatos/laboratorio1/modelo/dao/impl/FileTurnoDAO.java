/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao.impl;

import ArbolB.ArbolB;
import ArbolB.LlaveEntero;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import static co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO.ENCODING_WINDOWS;
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
public class FileTurnoDAO implements TurnoDAO {
    
    private static final String NOMBRE_ARCHIVO = "Turno.txt";
    private static final int LONGITUD_REGISTRO = 25;
    private static final int CODIGO_LONGITUD = 6;
    private static final int HORARIO_LONGITUD = 10;
    private static final int HORAS_LONGITUD = 3;
    private static final int PLACATAXI_LONGITUD = 6;
    
    

    private static final Path archivo = Paths.get(NOMBRE_ARCHIVO);
    public static final String ENCODING_WINDOWS = "Cp1252";

    private static final Map<String, Integer> turnoIndice = new HashMap<>();
    private static final ArbolB ARBOL = new ArbolB(2);

    public FileTurnoDAO() {
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
        System.out.println("Creando Indice del archivo: turno");
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            int posicion = 0;
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String codigo = registro.subSequence(0, CODIGO_LONGITUD).toString().trim();
                ARBOL.insert(new LlaveEntero(Integer.parseInt(codigo)), posicion);
                turnoIndice.put(codigo, posicion);
                posicion++;
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public List<Turno> listarTurnos() {
        List<Turno> turnos = new ArrayList<>();
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Turno turno = parseTurno(registro);
                turnos.add(turno);
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return turnos;
    }
    
    /*@Override
    public ArbolB CrearArbol() {
        ArbolB arbol = new ArbolB(2);
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Turno turno = parseTurno(registro);
                arbol.insert(new LlaveEntero(Integer.parseInt(turno.getCodigo())), "Dirección");
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return arbol;
    }*/

    @Override
    public Turno consultarTurno(String codigo) {
       
        Integer dir = (Integer)ARBOL.search(new LlaveEntero(Integer.parseInt(codigo)));
        if (dir == null) {
            return null;
        }
        return consultarTurnoxDir(dir);
    }

    private Turno consultarTurnoxDir(int dir) {
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            sbc.position(dir * LONGITUD_REGISTRO);
            sbc.read(buf);
            buf.rewind();
            CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
            Turno turno = parseTurno(registro);
            buf.flip();
            return turno;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean guardarTurno(Turno turno) {//throws LlaveDuplicadaException {
        if (consultarTurno(turno.getCodigo()) != null) {
            //throw new LlaveDuplicadaException();
            return false;
        }
        String registro = parseTurnoString(turno);
        byte[] datos = registro.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(datos);
        try (FileChannel fc = (FileChannel.open(archivo, APPEND))) {
            fc.write(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
    }
    
    private String parseTurnoString(Turno turno) {
        StringBuilder registro = new StringBuilder();
        registro.append(rellenarCampo(turno.getCodigo(), CODIGO_LONGITUD));
        registro.append(rellenarCampo(turno.getHorario(), HORARIO_LONGITUD));
        registro.append(rellenarCampo(turno.getHoras(), HORAS_LONGITUD));
        registro.append(rellenarCampo(turno.getPlacaTaxi(), PLACATAXI_LONGITUD));
       
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
    private Turno parseTurno(CharBuffer registro) {
        Turno t = new Turno();
        String codigo = registro.subSequence(0, CODIGO_LONGITUD).toString().trim();
        registro.position(CODIGO_LONGITUD);
        registro = registro.slice();
        t.setCodigo(codigo);

        String horario = registro.subSequence(0, HORARIO_LONGITUD).toString().trim();
        registro.position(HORARIO_LONGITUD);
        registro = registro.slice();
        t.setHorario(horario);

        String horas = registro.subSequence(0, HORAS_LONGITUD).toString().trim();
        registro.position(HORAS_LONGITUD);
        registro = registro.slice();
        t.setHoras(horas);

        String placaTaxi = registro.toString().trim();
        t.setPlacaTaxi(placaTaxi);

        return t;
    }
}
