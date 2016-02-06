package directory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

	private WatchService watcher ;
	
	private WatchKey key ;
	
	private final Path dir = FileSystems.getDefault().getPath("/User/valeriedaras/Desktop") ;
	
	private static final Logger logger = Logger.getLogger("Watcher");
	
	public Watcher() {
		try {
			watcher = FileSystems.getDefault().newWatchService();
			 key = dir.register(watcher,ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Failed to create Watcher.",e);
		}
	}
	
	 
}
