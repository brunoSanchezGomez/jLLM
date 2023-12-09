package JLLM;

import controller.Controller;
import model.CSVLLM;
import model.ILLM;
import model.IRepository;
import model.JsonRepository;
import model.MemoryLLM;
import model.Model;
import model.SmartLLM;
import model.XmlRepository;
import view.ApplicationView;
import view.ConsoleView;
import view.TTSView;

/**
 *
 * @author sanch
 */
public class JLLM {
    
    public static void main(String[] args) {
        IRepository repository;
        ILLM llm;
        ApplicationView view;
        
        switch (args.length)    {
            case 3:
                repository = getRepository(args[0]);
                llm = getLLM(args[1]);
                view = getView(args[2]);
                break;
                
            case 4:
                repository = getRepository(args[0]);
                llm = getLLM(args[1], args[3]);
                view = getView(args[2]);
                break;
                
            default:
                repository = new JsonRepository();
                llm = new MemoryLLM();
                view = new ConsoleView();
                break;
        }
        
        Model model = new Model(repository, llm);
        Controller c = new Controller(model, view);
        
        c.initAplication();
    }
    
    
    public static IRepository getRepository(String arg){
        switch  (arg)    {
            case "xml":
                return new XmlRepository();
            
            case "json":
                return new JsonRepository();
                
            default:
                return new JsonRepository();
        }
    }
    
    
    public static ILLM getLLM(String arg){
        switch  (arg)    {
            case "fake":
                return new MemoryLLM();
                
            case "csv":
                return new CSVLLM();
                
            default:
                return new MemoryLLM();
        }
    }
    
    
    public static ILLM getLLM(String arg, String host){
        switch  (arg)    {
            case "fake":
                return new MemoryLLM();
                
            case "csv":
                return new CSVLLM();
                
            case "smart":
                return new SmartLLM(host);
                
            default:
                return new MemoryLLM();
        }
    }
    
    
    public static ApplicationView getView(String arg){
        switch  (arg)    {
            case "consola":
                return new ConsoleView();
            
            case "voz":
                return new TTSView();
                
            default:
                return new ConsoleView();
        }
    }
    
}
