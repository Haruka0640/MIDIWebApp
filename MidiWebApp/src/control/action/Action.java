package control.action;

import javax.servlet.http.HttpServletRequest;

import control.midi.MIDIException;
import control.xmlreader.XMLException;

public interface Action {
	boolean check(HttpServletRequest req);
	String execute(HttpServletRequest req) throws XMLException,MIDIException;
}
