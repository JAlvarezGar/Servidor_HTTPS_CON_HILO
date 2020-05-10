package ssl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jjalv
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        imprimeDisponible();
        
       
        SSL hilo = new SSL(8066);
        hilo.start();
        SSL hilo2 = new SSL(8067);
        hilo2.start();
        
        
       

    }

    /**
     * **************************************************************************
     * muestra un mensaje en la Salida que confirma el arranque, y da algunas indicaciones posteriores
     */
    private static void imprimeDisponible() {

        System.out.println("El Servidor WEB se está ejecutando y permanece a la "
                + "escucha por el puerto 8066.\nEscribe en la barra de direcciones "
                + "de tu explorador preferido:\n\nhttps://localhost:8066\npara "
                + "solicitar la página de bienvenida\n\nhttps://localhost:8066/"
                + "prueba\n para solicitar una página prueba ,\n\nhttps://"
                + "localhost:8066/p\n para simular un error");
    }

}
