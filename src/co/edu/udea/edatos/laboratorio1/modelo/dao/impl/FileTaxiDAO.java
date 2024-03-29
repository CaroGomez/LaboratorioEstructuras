/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao.impl;

import ArbolB.ArbolB;
import ArbolB.LlaveCadena;
import ArbolB.LlaveEntero;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
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
public class FileTaxiDAO implements TaxiDAO {

    private static final String NOMBRE_ARCHIVO = "Taxi.txt";
    private static final int LONGITUD_REGISTRO = 50;
    private static final int PLACA_LONGITUD = 6;
    private static final int NUMERO_LONGITUD = 4;
    private static final int MARCA_LONGITUD = 20;
    private static final int MODELO_LONGITUD = 10;
    private static final int IDPROPIETARIO_LONGITUD = 10;

    private static final Path archivo = Paths.get(NOMBRE_ARCHIVO);
    public static final String ENCODING_WINDOWS = "Cp1252";

    private static final Map<String, Integer> taxiIndice = new HashMap<>();
    private static final ArbolB ARBOL = new ArbolB(2);

    public FileTaxiDAO() {
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
    public ArbolB retornarArbol() {
        return ARBOL;
    }

    private void crearIndice() {
        System.out.println("Creando Indice del archivo: Taxi");
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            int posicion = 0;
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String placa = registro.subSequence(0, PLACA_LONGITUD).toString().trim();
                ARBOL.insert(new LlaveCadena(placa), posicion);
                taxiIndice.put(placa, posicion);
                posicion++;
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public List<Taxi> listarTaxis() {
        List<Taxi> taxis = new ArrayList<>();
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Taxi taxi = parseTaxi(registro);
                taxis.add(taxi);
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return taxis;
    }
    
    @Override
    public Taxi consultarTaxi(String placa) {
       
        Integer dir = (Integer)ARBOL.search(new LlaveCadena(placa));
        if (dir == null) {
            return null;
        }
        return consultarTaxixDir(dir);
    }

    private Taxi consultarTaxixDir(int dir) {
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            sbc.position(dir * LONGITUD_REGISTRO);
            sbc.read(buf);
            buf.rewind();
            CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
            Taxi taxi = parseTaxi(registro);
            buf.flip();
            return taxi;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    /*@Override
    public ArbolB retornarArbol() {
        ArbolB arbol = new ArbolB(2);

        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                Taxi taxi = parseTaxi(registro);
                arbol.insert(new LlaveCadena(taxi.getPlaca()), "Dirección");
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return arbol;
    }*/

    /*@Override
    public Taxi consultarTaxi(String numero_Taxi) {
        Taxi taxi = CACHE_TAXI.get(numero_Taxi);
        if (taxi != null) {
            return taxi;
        }
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String id = registro.subSequence(6, (6 + NUMERO_LONGITUD)).toString().trim();
                if (id.equals(numero_Taxi)) {
                    taxi = parseTaxi(registro);
                    CACHE_TAXI.put(numero_Taxi, taxi);
                    return taxi;
                }
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    @Override
    public Taxi consultarTaxixPlaca(String placa_taxi) {
        Taxi taxi = CACHE_TAXI.get(placa_taxi);
        if (taxi != null) {
            return taxi;
        }
        try (SeekableByteChannel sbc = Files.newByteChannel(archivo)) {
            ByteBuffer buf = ByteBuffer.allocate(LONGITUD_REGISTRO);
            while (sbc.read(buf) > 0) {
                buf.rewind();
                CharBuffer registro = Charset.forName(ENCODING_WINDOWS).decode(buf);
                String id = registro.subSequence(0, PLACA_LONGITUD).toString().trim();
                if (id.equals(placa_taxi)) {
                    taxi = parseTaxi(registro);
                    CACHE_TAXI.put(placa_taxi, taxi);
                    return taxi;
                }
                buf.flip();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }*/

    @Override
    public boolean guardarTaxi(Taxi taxi) {//throws LlaveDuplicadaException {
        if (consultarTaxi(taxi.getPlaca()) != null) {
            //throw new LlaveDuplicadaException();
            return false;
        }
        
        String registro = parseTaxiString(taxi);
        byte[] datos = registro.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(datos);
        try (FileChannel fc = (FileChannel.open(archivo, APPEND))) {
            fc.write(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return true;
    }

    private String parseTaxiString(Taxi taxi) {
        StringBuilder registro = new StringBuilder();
        registro.append(rellenarCampo(taxi.getPlaca(), PLACA_LONGITUD));
        registro.append(rellenarCampo(taxi.getNumero_taxi(), NUMERO_LONGITUD));
        registro.append(rellenarCampo(taxi.getMarca(), MARCA_LONGITUD));
        registro.append(rellenarCampo(taxi.getModelo(), MODELO_LONGITUD));
        registro.append(rellenarCampo(taxi.getIdPropietario(), IDPROPIETARIO_LONGITUD));

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
    private Taxi parseTaxi(CharBuffer registro) {
        Taxi t = new Taxi();
        String placa = registro.subSequence(0, PLACA_LONGITUD).toString().trim();
        registro.position(PLACA_LONGITUD);
        registro = registro.slice();
        t.setPlaca(placa);

        String numero = registro.subSequence(0, NUMERO_LONGITUD).toString().trim();
        registro.position(NUMERO_LONGITUD);
        registro = registro.slice();
        t.setNumero_taxi(numero);

        String marca = registro.subSequence(0, MARCA_LONGITUD).toString().trim();
        registro.position(MARCA_LONGITUD);
        registro = registro.slice();
        t.setMarca(marca);

        String modelo = registro.subSequence(0, MODELO_LONGITUD).toString().trim();
        registro.position(MODELO_LONGITUD);
        registro = registro.slice();
        t.setModelo(modelo);

        String idPropietario = registro.toString().trim();
        t.setIdPropietario(idPropietario);

        return t;
    }

}
