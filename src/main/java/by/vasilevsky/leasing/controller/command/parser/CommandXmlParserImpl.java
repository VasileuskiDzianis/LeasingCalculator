package by.vasilevsky.leasing.controller.command.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.vasilevsky.leasing.controller.command.Command;

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
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(this.getClass().getClassLoader().getResourceAsStream(COMMANDS_MAPPING_FILE));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException("Command parsing error", e);
		}
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		NodeList commandsList = root.getElementsByTagName(COMMAND_XML_TAG);
		
		Map<String, Command> methodGetMapping = new HashMap<String, Command>();
		Map<String, Command> methodPostMapping = new HashMap<String, Command>();
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
		Map<String, Map<String, Command>> commandMapping = new HashMap<String, Map<String, Command>>();
		commandMapping.put(METHOD_GET, methodGetMapping);
		commandMapping.put(METHOD_POST, methodPostMapping);
		
		return commandMapping;
	}
	
	private Command buildCommand(String className) {
		Command command = null;
		try {
			command = (Command) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException("Command instantiation error", e);
		}
		return command;
	}
}
