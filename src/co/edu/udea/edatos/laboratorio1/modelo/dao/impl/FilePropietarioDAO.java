/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao.impl;

import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
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
/**
 *
 * @author Andres
 */
public class FilePropietarioDAO implements PropietarioDAO {

    private static final String NOMBRE_ARCHIVO="Propietario.txt";
    private static final int LONGITUD_REGISTRO=64;
    private static final int IDENTIFICACION_LONGITUD=10;
    private static final int NOMBRES_LONGITUD=20;
    private static final int APELLIDOS_LONGITUD=20;
    private static final int GENERO_LONGITUD=1;
    private static final int EDAD_LONGITUD=3;
    private static final int TELEFONO_LONGITUD=10;

    private static final Path archivo = Paths.get(NOMBRE_ARCHIVO);
    public static final String ENCODING_WINDOWS = "Cp1252";

    private static final Map<String, Propietario> CACHE_PROPIETARIO = new HashMap<>();

        @Override
    public List<Propietario> listarPropietarios() {
        List<Propietario> propietarios=new ArrayList<>();
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)){
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while(sbc.read(buf)>0){
                buf.rewind();
                CharBuffer registro= Charset.forName(ENCODING_WINDOWS).decode(buf);
                Propietario propietario = parsePropietario(registro);
                propietarios.add(propietario);
                buf.flip();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return propietarios;
    }
    
    @Override
    public Propietario consultarPropietario(String identificacion) {
        Propietario propietario=CACHE_PROPIETARIO.get(identificacion);
        if(propietario!=null){
            return propietario;
        }
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)){
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while(sbc.read(buf)>0){
                buf.rewind();
                CharBuffer registro= Charset.forName(ENCODING_WINDOWS).decode(buf);
                String id = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
                if(id.equals(identificacion)){
                    propietario = parsePropietario(registro);
                    CACHE_PROPIETARIO.put(identificacion, propietario);
                    return propietario;
                }
                buf.flip();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    }

    @Override
    public void guardarPropietario(Propietario propietario) throws LlaveDuplicadaException {
        if(consultarPropietario(propietario.getId())!=null){
            throw new LlaveDuplicadaException();
        }
        String registro= parsePropietarioString(propietario);
        byte[] datos = registro.getBytes();
        ByteBuffer buffer=ByteBuffer.wrap(datos);
        try(FileChannel fc=(FileChannel.open(archivo, APPEND))){
            fc.write(buffer);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
        private String parsePropietarioString(Propietario propietario) {
        StringBuilder registro = new StringBuilder();
        registro.append(rellenarCampo(propietario.getId(), IDENTIFICACION_LONGITUD));
        registro.append(rellenarCampo(propietario.getNombres(), NOMBRES_LONGITUD));
        registro.append(rellenarCampo(propietario.getApellidos(), APELLIDOS_LONGITUD));
        registro.append(rellenarCampo(Character.toString(propietario.getGenero()), GENERO_LONGITUD));
        registro.append(rellenarCampo(propietario.getEdad(), EDAD_LONGITUD));
        registro.append(rellenarCampo(propietario.getTelefono(), TELEFONO_LONGITUD));
        return registro.toString();
    }
        
    private String rellenarCampo(String campo, int tamanio){
        if(campo.length()>tamanio){
            return campo.substring(0, tamanio);
        }
        return String.format("%1$-"+tamanio+"s", campo);
    }
    
    /**
     * Convierte un registro almacenado en un CharBuffer a un Objeto de Persona
     *
     * @param registro
     * @return
     */
    private Propietario parsePropietario(CharBuffer registro){
        Propietario p = new Propietario();
        String identificacion = registro.subSequence(0, IDENTIFICACION_LONGITUD).toString().trim();
        registro.position(IDENTIFICACION_LONGITUD);
        registro=registro.slice();
        p.setId(identificacion);

        String nombres = registro.subSequence(0, NOMBRES_LONGITUD).toString().trim();
        registro.position(NOMBRES_LONGITUD);
        registro=registro.slice();
        p.setNombres(nombres);

        String apellidos = registro.subSequence(0, APELLIDOS_LONGITUD).toString().trim();
        registro.position(APELLIDOS_LONGITUD);
        registro=registro.slice();
        p.setApellidos(apellidos);

        char genero = registro.subSequence(0, GENERO_LONGITUD).toString().trim().charAt(0);
        registro.position(GENERO_LONGITUD);
        registro = registro.slice();
        p.setGenero(genero);

        String edad = registro.subSequence(0, EDAD_LONGITUD).toString().trim();
        registro.position(EDAD_LONGITUD);
        registro = registro.slice();
        p.setEdad(edad);

        String telefono = registro.subSequence(0, TELEFONO_LONGITUD).toString().trim();
        registro.position(TELEFONO_LONGITUD);
        registro = registro.slice();
        p.setTelefono(telefono);

        return p;
    }
    
        
}
