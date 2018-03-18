/******************************************
  *  Final Project Part B; Winter 2018
  *  3/19/2018
  *  Meghana Kolasani, kolasanm
  *  Rushil Vora, voraru
*******************************************/

package finalprojectB;

import junit.framework.TestCase;
import java.io.Serializable;
import java.util.Random;


public class UrlValidatorTest extends TestCase {
   
   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
   //UrlValidator urlVal = new UrlValidator();
   //UrlValidator urlVal = new UrlValidator();


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   /*********************** MANUAL TESTING *****************/
   
   public void testManualTest()
   {  

      assertNotNull(urlVal); // Ensure that the url validator is not null 
      assertTrue(urlVal.isValid("http://www.google.com/")); // Tests a commonly used website with http protocol
      //assertTrue(urlVal.isValid("ftp://foo.bar.com/"));
      assertTrue(urlVal.isValidAuthority("www.google.com")); // isValidAuthority to DomainValidator.java bug 
      assertTrue(urlVal.isValid("file:///Users/rushilvora/Downloads/Rushil_Vora_hw4.pdf")); // Tests a URL with file URL scheme - finds http and file bug
      
      assertFalse(urlVal.isValid("xxx.gg.123")); // Tests a website without an authority
      assertFalse(urlVal.isValid("abcdefghijkl;")); // Tests a URL with garbage 
      
   }

    /*****************************************************/

    /**************** PARTITION TESTING ********************/
   
   
   public void testSchemePartition()
   {

    // Valid tests - keeps everything except scheme constant unless protocol requires otherwise
    assertTrue(urlVal.isValidScheme("h3p"));  // The following 4 are valid schemes 
    assertTrue(urlVal.isValidScheme("ftp"));
    assertTrue(urlVal.isValidScheme("http"));
    assertTrue(urlVal.isValidScheme("https"));
   

    // Invalid tests
     assertFalse(urlVal.isValidScheme("://")); // Invalid scheme
     assertFalse(urlVal.isValidScheme("http/"));
     assertFalse(urlVal.isValidScheme("3ht://"));
   }

   public void testAuthorityPartition()
   {

    // Valid tests
    assertFalse(urlVal.isValidAuthority(null));
    assertTrue(urlVal.isValidAuthority("http://www.google.com"));
    assertTrue(urlVal.isValidAuthority("255.255.255.255"));
    assertTrue(urlVal.isValidAuthority("go.au"));

    // Invalid tests
    assertFalse(urlVal.isValidAuthority("1.3.4.5"));
    assertFalse(urlVal.isValidAuthority(""));


   }

   public void testPathPartition()
   {

    // Valid tests - keeps everything except scheme constant unless protocol requires otherwise
    assertTrue(urlVal.isValidPath(""));
    assertTrue(urlVal.isValidPath("/test"));
    assertTrue(urlVal.isValidPath("/file"));
    assertTrue(urlVal.isValidPath("/$34")); // This is actually valid!! :-)

    // Invalid tests
    assertFalse(urlVal.isValidPath(null));
    assertFalse(urlVal.isValidPath("/.."));
    assertFalse(urlVal.isValidPath("/../"));
    assertFalse(urlVal.isValidPath("#/file")); 

   }

   public void testUrlQueryPartition()
   {
 
    // Valid tests - keeps everything except scheme constant unless protocol requires otherwise
    assertTrue(urlVal.isValidQuery("?action=view"));
    assertTrue(urlVal.isValidQuery(""));
    assertTrue(urlVal.isValidQuery("?action=edit&mode=up"));

    // Invalid tests
    assertFalse(urlVal.isValidQuery("&?&#@$%^&*JHBHV^Cfremp gukjqhe4tiupo`#")); // Invalid query because of the space 
  

   }
   
  /*****************************************************/

  /**************** PROGRAMMING BASED TESTING ********************/
   
   public void testIsValid()
   {
      // Create arrays for each component
      String[] urlSchemeValid = {"http", "https", "ftp", "3hp"};
      String[] urlSchemeInvalid = {"://", "http//", "333"};
      String[] urlAuthorityValid = {"www.google.com", "www.opb.org", "255.255.255.255", "USA.gov"};
      String[] urlAuthorityInvalid = {"1.2.3.4.5", "&^XX.go", "netflax.piss", "go.b2b"};
      String[] urlPathValid = {"/$23/", "/testpath/", "/testpath/file/"};
      String[] urlPathInvalid = {"/../", "/..//file", "$&$&$"};
      String[] urlQueryValid = {"?action=view", "", "?action=edit&mode=up"};
      String[] urlQueryInvalid = {"&?&#@$%^&*JHBHV^Cfremp gukjqhe4tiupo`#"};

      Random rand = new Random();
      int numUrlComponents = 4;

      // Loops through 4 times changing one, different component of the URL with a random selection and leaving the rest constant 
      for (int i = 0; i < numUrlComponents; i++) {
        
        String constantUrlOne; // Beginning of constant url portion
        String constantUrlTwo; // End of constant url portion 
        String fullValidUrl = null;
        String fullInvalidUrl = null;
        String randValidComp;
        String randInvalidComp;
        int validIndex;
        int invalidIndex;

        switch(i) {
          case 0: // Picks random scheme 
            constantUrlOne = "://www.google.com/file/?action=view"; // Defines constant portions of the url 
            validIndex = rand.nextInt(urlSchemeValid.length); // Gets random index for valid scheme
            invalidIndex = rand.nextInt(urlSchemeInvalid.length); // Gets random index for invalid scheme
            randValidComp = urlSchemeValid[validIndex]; // Sets a valid scheme
            fullValidUrl = randValidComp + constantUrlOne; // Concats valid scheme with constant   
            randInvalidComp = urlSchemeInvalid[invalidIndex]; // Sets an invalid scheme
            fullInvalidUrl = randInvalidComp + constantUrlOne; // Concats invalid scheme with constant 
            break;
          
          case 1: // Picks random authority
            constantUrlOne = "http://";
            constantUrlTwo = "/file/?action=view";
            validIndex = rand.nextInt(urlAuthorityValid.length); // Gets random index for valid auth
            invalidIndex = rand.nextInt(urlAuthorityInvalid.length); // Gets random index for invalid auth
            randValidComp = urlAuthorityValid[validIndex]; // Sets a valid auth
            fullValidUrl = constantUrlOne + randValidComp + constantUrlTwo; // Concats valid auth with constant   
            randInvalidComp = urlAuthorityInvalid[invalidIndex]; // Sets an invalid auth
            fullInvalidUrl = constantUrlOne + randInvalidComp + constantUrlTwo; // Concats invalid auth with constant 
            break;
          
          case 2: // Picks random path
            constantUrlOne = "http://www.google.com/";
            constantUrlTwo = "?action=view";
            validIndex = rand.nextInt(urlPathValid.length); // Gets random index for valid path
            invalidIndex = rand.nextInt(urlPathInvalid.length); // Gets random index for invalid path
            randValidComp = urlPathValid[validIndex]; // Sets a valid path
            fullValidUrl = constantUrlOne + randValidComp + constantUrlTwo; // Concats valid path with constant   
            randInvalidComp = urlPathInvalid[invalidIndex]; // Sets an invalid path
            fullInvalidUrl = constantUrlOne + randInvalidComp + constantUrlTwo; // Concats invalid path with constant 
            break;
          
          case 3: // Picks random query 
            constantUrlOne = "http://www.google.com/file/";
            validIndex = rand.nextInt(urlQueryValid.length); // Gets random index for valid query
            invalidIndex = rand.nextInt(urlQueryInvalid.length); // Gets random index for invalid query
            randValidComp = urlQueryValid[validIndex]; // Sets a valid query
            fullValidUrl = constantUrlOne + randValidComp; // Concats valid query with constant   
            randInvalidComp = urlQueryInvalid[invalidIndex]; // Sets an invalid query
            fullInvalidUrl = constantUrlOne + randInvalidComp; // Concats invalid query with constant 
            break;
        }

        assertTrue(urlVal.isValid(fullValidUrl)); // Checks to ensure that the full valid url is valid
        assertFalse(urlVal.isValid(fullInvalidUrl)); // Checks to ensure that the full invalid url is not 

      }

   }

  /*****************************************************/
   
}