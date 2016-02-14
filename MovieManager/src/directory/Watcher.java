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
	
	private WatchService watcher ;
    
    private Path dir ;
    
    private final MovieManager manager ;
    
    private final String path ;
    
	private static final boolean verbose = true;
	
	private static final Log logger = new Log("Watcher", verbose);
	
	public Watcher(MovieManager manager, String path) {
		this.manager = manager;
		this.path = path;
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			this.dir = Paths.get(path);
			this.dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			
			logger.logInfo("Watch Service registered for dir: {0}", dir.getFileName());
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
                    if(isFile(fileName)) {
                		//manager.performFileCreated(fileName.toString()); ??
                		System.out.println("File \""+ fileName + "\" is a file");
                	}
                	else {
                		//manager.performDirCreated(fileName.toString()); ??
                		System.out.println("File \""+ fileName + "\" is a directory");
                	}
                }
                else if(kind == ENTRY_CREATE) {
                	System.out.println("File \""+ fileName + "\" has been created!");
                	if(isFile(fileName)) {
                		//manager.performFileCreated(fileName.toString());
                		System.out.println("File \""+ fileName + "\" is a file");
                	}
                	else {
                		//manager.performDirCreated(fileName.toString()); ??
                		System.out.println("File \""+ fileName + "\" is a directory");
                	}
               	
                }
                else if(kind == ENTRY_DELETE) {
                	System.out.println("File \""+ fileName + "\" has been deleted!");
                	if(isFile(fileName)) {
                		//manager.performFileCreated(fileName.toString());
                		System.out.println("File \""+ fileName + "\" is a file");
                	}
                	else {
                		//manager.performDirCreated(fileName.toString()); ??
                		System.out.println("File \""+ fileName + "\" is a directory");
                	}
                	
                }
            }
             
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
	}
	
	private boolean isFile(Path file) {
		Path p = Paths.get(path+file) ;
    	if(p.toFile().isFile()) {
			return true;
        }
		return false;
	}
	 
	
	public static void main(String[] args) {
		Watcher w = new Watcher(null,"/Users/valeriedaras/Desktop/");
		w.start();
	}
	 
}
