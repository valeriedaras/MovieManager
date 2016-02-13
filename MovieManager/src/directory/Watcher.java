package directory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import manager.MovieManager;
import utils.Log;
import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

	private final MovieManager manager ;
	
	private WatchService watcher ;
    
    private Path dir ;
    
	private static final boolean verbose = true;
	
	private static final Log logger = new Log("Watcher", verbose);
	
	public Watcher(MovieManager manager, String path) {
		this.manager = manager;
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			this.dir = Paths.get(path);
			this.dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			
			logger.logInfo("Watch Service registered for dir: {0}" + dir.getFileName());
		} catch (IOException e) {
			logger.logSevere("Failed to create Watcher.",e);
		}
	}
	
	public void start() {
		while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                return;
            }
             
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                 
                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                 
                System.out.println(kind.name() + ": " + fileName);
                
                if (kind == ENTRY_MODIFY) {
                    System.out.println("File \""+ fileName + "\" has changed!");
                }
                else if(kind == ENTRY_CREATE) {
                	System.out.println("File \""+ fileName + "\" has been created!");
                	manager.performFileCreated(fileName.toString());
                	//manager.performDirCreated(fileName.toString()); ??
                }
                else if(kind == ENTRY_DELETE) {
                	System.out.println("File \""+ fileName + "\" has been deleted!");
                }
            }
             
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
	}
	
	public static void main(String[] args) {
		Watcher w = new Watcher(null,"/home/valeriedaras/Desktop");
		w.start();
	}
	 
}
