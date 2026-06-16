package org.uade;

import org.uade.exception.ElementNotFoundException;
import org.uade.exception.EmptyStructureException;
import org.uade.model.Micro;
import org.uade.model.Ruta;
import org.uade.model.Terminal;
import org.uade.model.TipoMicro;
import org.uade.model.Viaje;
import org.uade.service.GestorFlota;
import org.uade.service.GestorRutas;
import org.uade.service.GestorViajes;
import org.uade.structure.implementation.DynamicLinkedListADT;

import java.util.Scanner;

public class App {

    private static GestorRutas  gestorRutas  = new GestorRutas();
    private static GestorFlota  gestorFlota  = new GestorFlota();
    private static GestorViajes gestorViajes = new GestorViajes();

    static Terminal[] terminales = new Terminal[10];
    static Micro[]    micros     = new Micro[15];

    public static void main(String[] args) {
        cargarTerminalesYRutas();
        cargarFlota();
        cargarViajes();
        menuPrincipal();
    }

    // ── Carga de datos ──────────────────────────────────────────────────────

    static void cargarTerminalesYRutas() {
        terminales[0] = new Terminal("BUE", "Buenos Aires");
        terminales[1] = new Terminal("COR", "Cordoba");
        terminales[2] = new Terminal("ROS", "Rosario");
        terminales[3] = new Terminal("MDZ", "Mendoza");
        terminales[4] = new Terminal("BAH", "Bahia Blanca");
        terminales[5] = new Terminal("SAL", "Salta");
        terminales[6] = new Terminal("SFE", "Santa Fe");
        terminales[7] = new Terminal("MDP", "Mar del Plata");
        terminales[8] = new Terminal("TUC", "Tucuman");
        terminales[9] = new Terminal("NQN", "Neuquen");

        for (int i = 0; i < terminales.length; i++) {
            gestorRutas.agregarTerminal(terminales[i]);
        }

        gestorRutas.agregarRuta("BUE", "COR", 700);
        gestorRutas.agregarRuta("BUE", "ROS", 300);
        gestorRutas.agregarRuta("BUE", "MDP", 400);
        gestorRutas.agregarRuta("BUE", "BAH", 650);
        gestorRutas.agregarRuta("ROS", "COR", 400);
        gestorRutas.agregarRuta("ROS", "SFE", 150);
        gestorRutas.agregarRuta("COR", "MDZ", 700);
        gestorRutas.agregarRuta("COR", "TUC", 700);
        gestorRutas.agregarRuta("COR", "SAL", 900);
        gestorRutas.agregarRuta("MDZ", "NQN", 600);
        gestorRutas.agregarRuta("TUC", "SAL", 320);
        gestorRutas.agregarRuta("SFE", "TUC", 900);

    }

    static void cargarFlota() {
        micros[0]  = new Micro("GHJ123", TipoMicro.EJECUTIVO);
        micros[1]  = new Micro("MNP456", TipoMicro.SEMI_CAMA);
        micros[2]  = new Micro("QRS789", TipoMicro.CAMA);
        micros[3]  = new Micro("ABC234", TipoMicro.EJECUTIVO);
        micros[4]  = new Micro("DEF567", TipoMicro.SEMI_CAMA);
        micros[5]  = new Micro("GHI890", TipoMicro.CAMA);
        micros[6]  = new Micro("JKL123", TipoMicro.EJECUTIVO);
        micros[7]  = new Micro("MNO456", TipoMicro.SEMI_CAMA);
        micros[8]  = new Micro("PQR789", TipoMicro.CAMA);
        micros[9]  = new Micro("STU012", TipoMicro.EJECUTIVO);
        micros[10] = new Micro("VWX345", TipoMicro.SEMI_CAMA);
        micros[11] = new Micro("YZA678", TipoMicro.CAMA);
        micros[12] = new Micro("BCD901", TipoMicro.EJECUTIVO);
        micros[13] = new Micro("EFG234", TipoMicro.SEMI_CAMA);
        micros[14] = new Micro("HIJ567", TipoMicro.CAMA);

        for (int i = 0; i < micros.length; i++) {
            gestorFlota.agregarMicro(micros[i]);
        }
    }

    static void cargarViajes() {
        Viaje[] viajes = new Viaje[20];

        viajes[0]  = new Viaje(1,  ruta("BUE","COR"), micros[0],  "2025-07-01", 2);
        viajes[1]  = new Viaje(2,  ruta("BUE","ROS"), micros[1],  "2025-07-02", 1);
        viajes[2]  = new Viaje(3,  ruta("BUE","MDP"), micros[2],  "2025-07-03", 3);
        viajes[3]  = new Viaje(4,  ruta("BUE","BAH"), micros[3],  "2025-07-04", 2);
        viajes[4]  = new Viaje(5,  ruta("ROS","COR"), micros[4],  "2025-07-05", 1);
        viajes[5]  = new Viaje(6,  ruta("ROS","SFE"), micros[5],  "2025-07-06", 4);
        viajes[6]  = new Viaje(7,  ruta("COR","MDZ"), micros[6],  "2025-07-07", 3);
        viajes[7]  = new Viaje(8,  ruta("COR","TUC"), micros[7],  "2025-07-08", 2);
        viajes[8]  = new Viaje(9,  ruta("COR","SAL"), micros[8],  "2025-07-09", 1);
        viajes[9]  = new Viaje(10, ruta("MDZ","NQN"), micros[9],  "2025-07-10", 5);
        viajes[10] = new Viaje(11, ruta("TUC","SAL"), micros[10], "2025-07-11", 3);
        viajes[11] = new Viaje(12, ruta("SFE","TUC"), micros[11], "2025-07-12", 2);
        viajes[12] = new Viaje(13, ruta("BUE","COR"), micros[12], "2025-07-15", 4);
        viajes[13] = new Viaje(14, ruta("BUE","ROS"), micros[13], "2025-07-16", 1);
        viajes[14] = new Viaje(15, ruta("ROS","COR"), micros[14], "2025-07-17", 3);
        viajes[15] = new Viaje(16, ruta("COR","MDZ"), micros[0],  "2025-07-20", 2);
        viajes[16] = new Viaje(17, ruta("BUE","BAH"), micros[1],  "2025-07-21", 1);
        viajes[17] = new Viaje(18, ruta("MDZ","NQN"), micros[2],  "2025-07-22", 4);
        viajes[18] = new Viaje(19, ruta("TUC","SAL"), micros[3],  "2025-07-25", 5);
        viajes[19] = new Viaje(20, ruta("COR","SAL"), micros[4],  "2025-07-28", 2);

        for (int i = 0; i < viajes.length; i++) {
            gestorViajes.agregarViaje(viajes[i]);
            gestorFlota.asignarViaje(viajes[i].getMicro().getIdentificador(), viajes[i]);
            gestorRutas.registrarUso(viajes[i].getRuta().getOrigen().getCodigo(), viajes[i].getRuta().getDestino().getCodigo());
        }
    }

    // ── Menus ────────────────────────────────────────────────────────────────

    private static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n================================");
            System.out.println("   SISTEMA DE TRANSPORTE - UADE");
            System.out.println("================================");
            System.out.println("1. Gestion de rutas");
            System.out.println("2. Gestion de flota");
            System.out.println("3. Gestion de viajes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1: menuRutas(sc);   break;
                case 2: menuFlota(sc);   break;
                case 3: menuViajes(sc);  break;
                case 0: System.out.println("Hasta luego!"); break;
                default: System.out.println("Opcion invalida, intente de nuevo");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static void menuRutas(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Gestion de Rutas ---");
            System.out.println("1. Listar terminales");
            System.out.println("2. Buscar rutas entre dos terminales");
            System.out.println("3. Terminales sin conexiones");
            System.out.println("4. Terminal con mas salidas");
            System.out.println("5. Terminal con mas llegadas");
            System.out.println("6. Verificar si existe una ruta directa");
            System.out.println("7. Terminal con mas conexiones directas");
            System.out.println("8. Reporte de rutas mas/menos utilizadas");
            System.out.println("9. Agregar terminal");
            System.out.println("10. Agregar ruta");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1:
                    gestorRutas.listarTerminales();
                    break;
                case 2:
                    System.out.print("Codigo terminal origen (ej: BUE): ");
                    String origen = sc.next().toUpperCase();
                    System.out.print("Codigo terminal destino (ej: SAL): ");
                    String destino = sc.next().toUpperCase();
                    System.out.print("Maximo de paradas: ");
                    int maxParadas = leerEntero(sc);
                    gestorRutas.buscarRutas(origen, destino, maxParadas);
                    break;
                case 3:
                    gestorRutas.listarTerminalesDesconectados();
                    break;
                case 4:
                    gestorRutas.terminalConMasSalidas();
                    break;
                case 5:
                    gestorRutas.terminalConMasLlegadas();
                    break;
                case 6:
                    System.out.print("Codigo terminal origen: ");
                    String o = sc.next().toUpperCase();
                    System.out.print("Codigo terminal destino: ");
                    String d = sc.next().toUpperCase();
                    boolean existe = gestorRutas.existeRuta(o, d);
                    System.out.println("La ruta " + o + " -> " + d + (existe ? " existe" : " no existe"));
                    break;
                case 7:
                    gestorRutas.terminalConMasConexiones();
                    break;
                case 8:
                    gestorRutas.reporteRutasMasYMenosUsadas();
                    break;
                case 9:
                    System.out.print("Codigo de la nueva terminal (ej: BAR): ");
                    String codNueva = sc.next().toUpperCase();
                    if (gestorRutas.getTerminal(codNueva) != null) {
                        System.out.println("Ya existe una terminal con ese codigo");
                        break;
                    }
                    System.out.print("Descripcion: ");
                    String descNueva = sc.next();
                    gestorRutas.agregarTerminal(new Terminal(codNueva, descNueva));
                    System.out.println("Terminal " + codNueva + " agregada");
                    break;
                case 10:
                    System.out.print("Codigo terminal origen: ");
                    String oNueva = sc.next().toUpperCase();
                    System.out.print("Codigo terminal destino: ");
                    String dNueva = sc.next().toUpperCase();
                    if (gestorRutas.getTerminal(oNueva) == null || gestorRutas.getTerminal(dNueva) == null) {
                        System.out.println("Una o ambas terminales no existen");
                        break;
                    }
                    if (gestorRutas.existeRuta(oNueva, dNueva)) {
                        System.out.println("Ya existe esa ruta");
                        break;
                    }
                    System.out.print("Distancia (km): ");
                    int distNueva = leerEntero(sc);
                    gestorRutas.agregarRuta(oNueva, dNueva, distNueva);
                    System.out.println("Ruta " + oNueva + " -> " + dNueva + " agregada");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private static void menuFlota(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Gestion de Flota ---");
            System.out.println("1. Listar micros");
            System.out.println("2. Micro con mas asignaciones");
            System.out.println("3. Reporte de utilizacion");
            System.out.println("4. Actualizar disponibilidad de un micro");
            System.out.println("5. Ver viajes asignados a un micro");
            System.out.println("6. Agregar micro");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1:
                    gestorFlota.listarMicros();
                    break;
                case 2:
                    gestorFlota.microConMasAsignaciones();
                    break;
                case 3:
                    gestorFlota.reporteUtilizacion();
                    break;
                case 4:
                    System.out.print("Patente del micro: ");
                    String patente = sc.next().toUpperCase();
                    System.out.print("Disponible? (1=si / 0=no): ");
                    int disp = leerEntero(sc);
                    try {
                        gestorFlota.actualizarDisponibilidad(patente, disp == 1);
                        System.out.println("Disponibilidad actualizada correctamente");
                    } catch (ElementNotFoundException e) {
                        System.out.println("Error: micro no encontrado");
                    }
                    break;
                case 5:
                    System.out.print("Patente del micro: ");
                    String id = sc.next().toUpperCase();
                    try {
                        DynamicLinkedListADT viajesMicro = gestorFlota.getViajesDeMicro(id);
                        if (viajesMicro == null || viajesMicro.size() == 0) {
                            System.out.println("El micro no tiene viajes asignados");
                        } else {
                            System.out.println("Viajes del micro " + id + ":");
                            for (int i = 0; i < viajesMicro.size(); i++) {
                                System.out.println("  " + viajesMicro.get(i));
                            }
                        }
                    } catch (ElementNotFoundException e) {
                        System.out.println("Error: no se encontraron viajes para ese micro");
                    }
                    break;
                case 6:
                    System.out.print("Patente del nuevo micro: ");
                    String patenteNueva = sc.next().toUpperCase();
                    System.out.print("Tipo (1=EJECUTIVO, 2=SEMI_CAMA, 3=CAMA): ");
                    int tipoInt = leerEntero(sc);
                    TipoMicro tipoNuevo = null;
                    if (tipoInt == 1) tipoNuevo = TipoMicro.EJECUTIVO;
                    else if (tipoInt == 2) tipoNuevo = TipoMicro.SEMI_CAMA;
                    else if (tipoInt == 3) tipoNuevo = TipoMicro.CAMA;
                    if (tipoNuevo == null) {
                        System.out.println("Tipo invalido, operacion cancelada");
                    } else {
                        gestorFlota.agregarMicro(new Micro(patenteNueva, tipoNuevo));
                        System.out.println("Micro " + patenteNueva + " agregado");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private static void menuViajes(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Gestion de Viajes ---");
            System.out.println("1. Listar todos los viajes");
            System.out.println("2. Ver proximo viaje a despachar");
            System.out.println("3. Despachar proximo viaje");
            System.out.println("4. Modificar prioridad de un viaje");
            System.out.println("5. Reprogramar fecha de un viaje");
            System.out.println("6. Agregar viaje");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1:
                    gestorViajes.listarViajes();
                    break;
                case 2:
                    if (gestorViajes.isEmpty()) {
                        System.out.println("No hay viajes en cola");
                    } else {
                        try {
                            System.out.println("Proximo: " + gestorViajes.proximoViaje());
                        } catch (EmptyStructureException e) {
                            System.out.println("Error: la cola esta vacia");
                        }
                    }
                    break;
                case 3:
                    gestorViajes.despacharViaje();
                    break;
                case 4:
                    System.out.print("ID del viaje: ");
                    int idViaje = leerEntero(sc);
                    System.out.print("Nueva prioridad (1=urgente, 5=baja): ");
                    int nuevaPrioridad = leerEntero(sc);
                    try {
                        gestorViajes.modificarPrioridad(idViaje, nuevaPrioridad);
                    } catch (ElementNotFoundException e) {
                        System.out.println("Error: viaje no encontrado");
                    }
                    break;
                case 5:
                    System.out.print("ID del viaje: ");
                    int idRep = leerEntero(sc);
                    System.out.print("Nueva fecha (YYYY-MM-DD): ");
                    String nuevaFecha = sc.next();
                    try {
                        gestorViajes.reprogramarViaje(idRep, nuevaFecha);
                    } catch (ElementNotFoundException e) {
                        System.out.println("Error: viaje no encontrado");
                    }
                    break;
                case 6:
                    System.out.print("Codigo terminal origen: ");
                    String origenV = sc.next().toUpperCase();
                    System.out.print("Codigo terminal destino: ");
                    String destinoV = sc.next().toUpperCase();
                    if (!gestorRutas.existeRuta(origenV, destinoV)) {
                        System.out.println("No existe ruta directa entre " + origenV + " y " + destinoV);
                        break;
                    }
                    System.out.print("Patente del micro: ");
                    String patenteV = sc.next().toUpperCase();
                    if (!gestorFlota.microExiste(patenteV)) {
                        System.out.println("Micro no encontrado");
                        break;
                    }
                    Micro microV = gestorFlota.getMicro(patenteV);
                    if (!microV.isDisponible()) {
                        System.out.println("El micro " + patenteV + " no esta disponible");
                        break;
                    }
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fechaV = sc.next();
                    System.out.print("Prioridad (1=urgente, 5=baja): ");
                    int prioV = leerEntero(sc);
                    Terminal tOrigen = gestorRutas.getTerminal(origenV);
                    Terminal tDestino = gestorRutas.getTerminal(destinoV);
                    Ruta rutaNueva = new Ruta(tOrigen, tDestino, gestorRutas.getDistanciaRuta(origenV, destinoV));
                    Viaje viajeNuevo = new Viaje(gestorViajes.getContadorId(), rutaNueva, microV, fechaV, prioV);
                    gestorViajes.agregarViaje(viajeNuevo);
                    gestorFlota.asignarViaje(patenteV, viajeNuevo);
                    gestorRutas.registrarUso(origenV, destinoV);
                    System.out.println("Viaje #" + viajeNuevo.getId() + " agregado");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static Ruta ruta(String origen, String destino) {
        return new Ruta(
            gestorRutas.getTerminal(origen),
            gestorRutas.getTerminal(destino),
            gestorRutas.getDistanciaRuta(origen, destino)
        );
    }
}
