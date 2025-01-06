import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaCambio consulta = new ConsultaCambio();
        GerneradorJson generador = new GerneradorJson();
        boolean continuar = true;

        System.out.println("""				
				♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
				♦♦♦♦♦♦Conversor de monedas♦♦♦♦♦♦
				♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
				""");

        while (continuar) {
            System.out.println("""
				♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
				Por favor seleccione una opción:
				1. Convertir Moneda
				2. Salir
				♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
				""");



            String opcionInput = lectura.nextLine();

            try {
                int opcion = Integer.parseInt(opcionInput);

                switch (opcion) {
                    case 1:
                        realizarConversion(lectura, consulta, generador);
                        break;
                    case 2:
                        System.out.println("Programa finalizado con exito");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número correspondiente a las opciones.");
            }
        }

        lectura.close();
    }

    private static void realizarConversion(Scanner lectura, ConsultaCambio consulta, GerneradorJson generador) {
        try {
            // Seleccionar Moneda de Origen
            System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            System.out.println("Seleccione la moneda de origen:");
            System.out.println(Moneda.menuMonedas());

            int origenInput = Integer.parseInt(lectura.nextLine());
            Moneda origen = Moneda.fromOpcion(origenInput);

            // Seleccionar Moneda de Destino
            System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            System.out.println("Seleccione la moneda de destino:");
            System.out.println(Moneda.menuMonedas());
            int destinoInput = Integer.parseInt(lectura.nextLine());
            Moneda destino = Moneda.fromOpcion(destinoInput);

            // Ingresar Cantidad a Convertir
            System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            System.out.println(Moneda.menuMonedas());

            System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
            System.out.print("Ingrese el valor a convertir: (" + origen.getCodigo() + "): ");
            double cantidad = Double.parseDouble(lectura.nextLine());

            // Obtener Tipo de Cambio
            CambiarApi cambio = consulta.buscarTipoDeCambio(origen.getCodigo(), destino.getCodigo());

            // Realizar Conversión
            double resultado = cambio.getConversionRate() * cantidad;

            // Formatear Resultados
            DecimalFormat df = new DecimalFormat("#.##");

            // Mostrar Resultado
            System.out.printf("\n%.2f %s equivale a %.2f %s.\n", cantidad, origen.getCodigo(), resultado, destino.getCodigo());

            // Generar Archivo JSON
            generador.guardarJson(cambio, origen.getCodigo(), destino.getCodigo(), cantidad, resultado);
            System.out.println("Se ha generado el archivo JSON con los detalles de la conversión.");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Ingrese números válidos para las opciones y la cantidad.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al generar el archivo JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
