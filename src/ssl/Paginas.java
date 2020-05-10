package ssl;

/**
 * ****************************************************************************
 * clase no instanciable donde se definen algunos valores finales
 *
 * @author IMCG
 */
public class Paginas {

    public static final String primeraCabecera
            = "Content-Type:text/html;charset=UTF-8";
    //contenido index
    public static final String html_index = "<html>"
            + "<head><title>index</title></head>"
            + "<body>"
            + "<h1>¡Enhorabuena!</h1>"
            + "<p>Tu servidor HTTPS mínimo funciona correctamente</p>"
            + "</body>"
            + "</html>";
    //contenido quijote
    public static final String html_prueba = "<!DOCTYPE html>\n"
            + "<html lang=\"es\">\n"
            + "<head>\n"
            + "  <meta charset=\"utf-8\">\n"
            + "  <title>HTML</title>\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "  <link rel=\"stylesheet\" href=\"estilo.css\">\n"
            + "</head>\n"
            + "\n"
            + "<body>\n"
            + "<h1>Página con seguridad HTTPS</h1>\n"
            + "  <p>Esta página web es una página HTML válida.</p>\n"
            + "</body>\n"
            + "</html>";
    //contenido noEncontrado
    public static final String html_noEncontrado = "<html>"
            + "<head><title>noEncontrado</title></head>"
            + "<body>"
            + "<h1>¡ERROR! Página no encontrada</h1>"
            + "<p>La página que solicitaste no existe en nuestro "
            + "servidor</p>"
            + "</body>"
            + "</html>";
}
