# HTML Harvester
Simple HTML data harvester with XML scheme

#Dependences

This libraries need <a href="http://htmlunit.sourceforge.net/"> HtmlUnit</a> to work.<br>
I actually use the version 2.21.

#XML scheme file

XML document is used to describe what type of elements you need in a web page.<br>
The xml document should follow : <a href="https://raw.githubusercontent.com/nyradr/HTML_Harvester/master/HtmlHarvester/dtd/scheme.dtd">this DTD </a><br>

You can find XML documents sample <a href="https://github.com/nyradr/HTML_Harvester/tree/master/HtmlHarvester/xml">here</a>

Few things:
	<ol>
	<li>"page" elements represent a web page scheme.</li>
	<li>"data" elements represents a single elements to extract from the web page</li>
	<li>On XPath(s) constructions xargs id in the xpath is replaced by one of the arg value (all combinaisons of XPaths is build)</li>
	<li>All names and ids are unique and totaly arbitrary (user defined)</li>
	</ol>
