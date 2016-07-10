# xpath
This contains a plain Java program xpath.java that reads some search keywords from input, searches the eBay API for items in the category with id=72 (the computers category) that match these keywords, and displays information about these products by evaluating the following XPath queries over the returned XML data:
Print the full description of all products that have a rating 4.50 or higher. 
Print the name and the minimum price of all products whose name contains the word Sony. 
Print the names of all products whose name contains the word Sony and the price is between $1000 and $2000, inclusive.

Also, this contains an XSLT program search.xsl to display the search results by transforming the XML result to XHTML using XSLT that contains the components I generated for the search results in the shopping cart repository: the id, sourceURL, name, minPrice, and fullDescription. I used the Java program xslt.java to test the XSLT and then load the resulting html output file on the web browser.

To call a web service, I used the java.net.URL class. To URL-encode the query string, I used the java.net.URLEncoder class. For example, this calls the ISBN DB and prints the result.
