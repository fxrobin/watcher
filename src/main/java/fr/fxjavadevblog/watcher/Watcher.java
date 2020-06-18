package fr.fxjavadevblog.watcher;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Slf4j
@Command(name="watcher", mixinStandardHelpOptions = true, version = "watcher 1.0",
         description = "watch folders (and subfolders) for changes and send vocal notifications.")
public class Watcher implements Callable<Integer> {
	
	public static final int STATUS_OK = 0;
	public static final int STATUS_ERROR = 1;
	
	@Parameters(arity = "1..*")
	private List<Path> paths;
	
	@Option(names = {"-R" , "--recurse"}, description = "Recursively watch subfolders")
	private Boolean recurse;
	
	@Option(names = { "-a", "--api-key" }, description = "Google API Key for Text-to-Speech")
	private String apiKey;
	

	public static void main(String[] args) {
		var cli = new CommandLine(new Watcher());
		var exitCode = cli.execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() throws Exception {
		
		log.info("Starting");
		log.info("API KEY : {}", apiKey);
		log.info("Recurse : {}", recurse);
	
		paths.forEach(p -> NotifierFacade.send("Listening to ...", p.toString()));
		
		return STATUS_OK;
	}

}
