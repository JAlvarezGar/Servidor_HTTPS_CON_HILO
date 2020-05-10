package ssl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public final class SSL{
   private SSLServerSocket serverSocket;

   public SSL(int port) throws Exception {

      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new FileInputStream("C:\\Users\\jjalv\\Desktop\\PSYP\\serverKey.jks"),
            "servpass".toCharArray());

      KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
      kmf.init(keyStore, "servpass".toCharArray());

      KeyStore trustedStore = KeyStore.getInstance("JKS");
      trustedStore.load(new FileInputStream(
            "C:\\Users\\jjalv\\Desktop\\PSYP\\serverKey.jks"), "servpass"
            .toCharArray());

      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(trustedStore);

      SSLContext sc = SSLContext.getInstance("TLS");
      TrustManager[] trustManagers = tmf.getTrustManagers();
      KeyManager[] keyManagers = kmf.getKeyManagers();
      sc.init(keyManagers, trustManagers, null);

      SSLServerSocketFactory ssf = sc.getServerSocketFactory();
      serverSocket = (SSLServerSocket) ssf.createServerSocket(port);
      
       //ante una petición entrante, procesa la petición por el socket cliente
    //por donde la recibe
    while (true) {
          //a la espera de peticiones
          Socket socCliente = serverSocket.accept();
      //atiendo un cliente
      System.out.println("Atendiendo al cliente ");
      procesaPeticion(socCliente);
      //cierra la conexión entrante
      socCliente.close();
      System.out.println("cliente atendido");
    }
  
    
    
 
      
   }
      public void procesaPeticion(Socket socketCliente) throws IOException {
    //variables locales
    String peticion;
    String html;

    //Flujo de entrada
    InputStreamReader inSR = new InputStreamReader(
            socketCliente.getInputStream());
    //espacio en memoria para la entrada de peticiones
    BufferedReader bufLeer = new BufferedReader(inSR);

    //objeto de java.io que entre otras características, permite escribir 
    //'línea a línea' en un flujo de salida
    PrintWriter printWriter = new PrintWriter(
            socketCliente.getOutputStream(), true);
    
    //mensaje petición cliente
    peticion = bufLeer.readLine();

    //para compactar la petición y facilitar así su análisis, suprimimos todos 
    //los espacios en blanco que contenga
    peticion = peticion.replaceAll(" ", "");

    //si realmente se trata de una petición 'GET' (que es la única que vamos a
    //implementar en nuestro Servidor)
    if (peticion.startsWith("GET")) {
//      extrae la subcadena entre 'GET' y 'HTTP/1.1'
      peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));
//        System.out.println(peticion.lastIndexOf("HTTP"));
//        System.out.println(peticion);
//      int peticion=peticion.lastIndexOf(3,"HTTP");

      //si corresponde a la página de inicio
      if (peticion.length() == 0 || peticion.equals("/")) {
        //sirve la página
        html = Paginas.html_index;
        printWriter.println(Mensajes.lineaInicial_OK);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      } //si corresponde a la página del Quijote
      else if (peticion.equals("/prueba")) {
        //sirve la página
        html = Paginas.html_prueba;
        printWriter.println(Mensajes.lineaInicial_OK);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      } //en cualquier otro caso
      else {
        //sirve la página
        html = Paginas.html_noEncontrado;
        printWriter.println(Mensajes.lineaInicial_NotFound);
        printWriter.println(Paginas.primeraCabecera);
        printWriter.println("Content-Length: " + html.length() + 1);
        printWriter.println("\n");
        printWriter.println(html);
      }
    
    }
  }
   
    
//
//   public void start() {
//      Util.startServerWorking(serverSocket);
//   }

}
