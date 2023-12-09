/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import static com.coti.tools.Esdia.*;
/**
 *
 * @author sanch
 */
public class TTSView extends ApplicationView{

    @Override
    public void showApplicationStart(String initInfo) {
        System.out.println(initInfo);
    }

    @Override
    public void showMainMenu() {
        int option;
        do {
            System.out.println("\n--- MENU jLLM ---");
            System.out.println("1. Nueva conversacion");
            System.out.println("2. Menu CRUD");
            System.out.println("3. Menu exportacion");
            System.out.println("4. Salir");
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    startConversation();
                    break;
                case 2:
                    CRUDMenu();
                    break;
                case 3:
                    exportMenu();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 4);
    }
    
    @Override
    public void showApplicationEnd(String endInfo) {
        System.out.println(endInfo);
    }
    
    private void startConversation() {
        System.out.println("\n--- NUEVA CONVERSACION ---");
        String input;
        do {
            input = readString_ne(c.startMessage());
            System.out.println(c.getResponse(input));
        } while (!input.equals("/salir"));
        
        c.endConversation();
        System.out.println("Fin de la conversacion.");
    }
    
    private void CRUDMenu()  {
        int option;
        do {
            System.out.println("\n--- CRUD MENU jLLM ---");
            System.out.println("1. Listar conversaciones");
            System.out.println("2. Eliminar conversacion");
            System.out.println("3. Salir");
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    listConversations();
                    break;
                case 2:
                    removeConversation();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 3);
    }
    
    private void listConversations() {
        System.out.println("\n--- LISTADO DE CONVERSACIONES ---");
        
        int conversationsNumber = c.getConversationsNumber();
        
        if(conversationsNumber > 0) {
            for(int i = 0; i < conversationsNumber; i++)    {
                System.out.printf("%d. %s\n", i, c.getConversationPreview(i));
            }

            if(yesOrNo("\nDesea consultar una de las conversaciones")) {
                int index = readInt("Introduzca el indice de la conversacion: ");
                System.out.println("\n" + c.getConversationString(index));
            }
        }   else    {
            System.out.println("\nTodavia no hay ninguna conversacion.");
        }
    }
    
    private void removeConversation()    {
        System.out.println("\n--- ELIMINAR CONVERSACION ---");
        
        int index = readInt("Introduzca el indice de la conversacion que desea eliminar: ");
        
        if(c.removeConversation(index)) {
            System.out.println("Conversacion eliminada con exito.");
        }   else    {
            System.out.println("No existe una conversacion con ese indice.");
        }
    }
    
    private void exportMenu()    {
        int option;
        do {
            System.out.println("\n--- CRUD MENU jLLM ---");
            System.out.println("1. Exportar conversaciones");
            System.out.println("2. Importar conversaciones");
            System.out.println("3. Salir");
            option = readInt("Ingrese una opción: ");

            switch (option) {
                case 1:
                    exportConversations();
                    break;
                case 2:
                    importConversations();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 3);
    }
    
    private void exportConversations()   {
        if(c.exportConversations()) {
            System.out.println("Exportacion realizada con exito.");
        }   else    {
            System.out.println("Error al exportar.");
        }
    }
    
    private void importConversations()   {
        if(c.importConversations()) {
            System.out.println("Importacion realizada con exito.");
        }   else    {
            System.out.println("Error al importar.");
        }
    }
}
