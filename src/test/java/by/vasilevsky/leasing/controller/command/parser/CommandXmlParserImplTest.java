package by.vasilevsky.leasing.controller.command.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.parser.CommandXmlParserImpl;

public class CommandXmlParserImplTest {
	private static final int EXPECTED_POST_COMMANS_NUMBER = 5;
	private static final int EXPECTED_GET_COMMANS_NUMBER = 8;
	private static final int EXPECTED_METHODS_NUMBER = 2;
	private static final String METHOD_GET = "get";
	private static final String METHOD_POST = "post";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCommandsMapping() throws ParserConfigurationException, SAXException, IOException {
		CommandXmlParserImpl parser = new CommandXmlParserImpl();
		Map<String, Map<String, Command>> commandMapping = parser.getCommandsMapping();
		
		assertEquals(EXPECTED_METHODS_NUMBER, commandMapping.size());
		assertEquals(EXPECTED_GET_COMMANS_NUMBER, commandMapping.get(METHOD_GET).size());
		assertEquals(EXPECTED_POST_COMMANS_NUMBER, commandMapping.get(METHOD_POST).size());
	}
}
