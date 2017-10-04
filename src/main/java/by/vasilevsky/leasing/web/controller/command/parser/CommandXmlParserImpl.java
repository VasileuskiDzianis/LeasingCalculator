package by.vasilevsky.leasing.web.controller.command.parser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import by.vasilevsky.leasing.web.controller.command.Command;

public class CommandXmlParserImpl implements CommandXmlParser {
	private static final String COMMAND_XML_TAG = "command";
	private static final String COMMAND_XML_URL_ATTRIBUTE = "url";
	private static final String COMMAND_XML_METHOD_ATTRIBUTE = "method";
	private static final String COMMAND_XML_CLASS_ATTRIBUTE = "class";
	private static final String METHOD_GET = "get";
	private static final String METHOD_POST = "post";
	
	private static final String COMMANDS_MAPPING_FILE = "commands.xml";

	public Map<String, Map<String, Command>> getCommandsMapping() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(this.getClass().getClassLoader().getResourceAsStream(COMMANDS_MAPPING_FILE));
		} catch (Exception e) {
			throw new RuntimeException("Command parsing error", e);
		}
		
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		NodeList commandsList = root.getElementsByTagName(COMMAND_XML_TAG);
		
		return buildCommandMapping(commandsList);
	}

	private Map<String, Map<String, Command>> buildCommandMapping(NodeList commandsList) {
		Map<String, Command> methodGetMapping = new HashMap<>();
		Map<String, Command> methodPostMapping = new HashMap<>();
		
		for (int i = 0; i < commandsList.getLength(); i++) {
			Element commandItem = (Element) commandsList.item(i);
			Command command = buildCommand(commandItem.getAttribute(COMMAND_XML_CLASS_ATTRIBUTE));
			String url = commandItem.getAttribute(COMMAND_XML_URL_ATTRIBUTE);
			String method = commandItem.getAttribute(COMMAND_XML_METHOD_ATTRIBUTE);
			if (method.equals(METHOD_GET)) {
				methodGetMapping.put(url, command);
			}
			if (method.equals(METHOD_POST)) {
				methodPostMapping.put(url, command);
			}
		}
		Map<String, Map<String, Command>> commandMapping = new HashMap<>();
		commandMapping.put(METHOD_GET, methodGetMapping);
		commandMapping.put(METHOD_POST, methodPostMapping);
		
		return commandMapping;
	}
	
	private Command buildCommand(String className) {
		Command command;
		
		try {
			command = (Command) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Command instantiation error", e);
		}
		
		return command;
	}
}
