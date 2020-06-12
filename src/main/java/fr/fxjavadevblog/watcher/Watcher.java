package fr.fxjavadevblog.watcher;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="watcher", mixinStandardHelpOptions = true, version = "watcher 1.0",
         description = "watch folders (and subfolders) for changes and send vocal notifications.")
public class Watcher implements Callable<Integer> {
	
	public static final int STATUS_OK = 0;
	public static final int STATUS_ERROR = 1;
	
	@Parameters
	private List<Path> paths;
	
	private PrintStream out = System.out;
	
	@Option(names = {"-R" , "--recurse"}, description = "Recursivly watch subfolders")
	private Boolean recurse;
	
	@Option(names = { "-a", "--api-key" }, description = "Google API Key for Text-to-Speech")
	private String apiKey;
	

	public static void main(String[] args) {
		CommandLine cli = new CommandLine(new Watcher());
		int exitCode = cli.execute(args);
		System.exit(exitCode);

	}

	@Override
	public Integer call() throws Exception {
		
		out.println("watcher ...");
		out.printf("API KEY : %s %n", apiKey);
		out.printf("Recurse : %b %n", recurse);
		paths.forEach(out::println);
		return STATUS_OK;
	}

}
