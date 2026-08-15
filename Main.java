public class Main
{
	public static void main(String[] args) throws Exception{

	    Author author1 = new Author();
	    author1.setId(0);
        author1.setIdBook(0);
        author1.setFirstName("Adam");
        author1.setLastName("Mickiewicz");

        Author author2 = new Author();
	    author2.setId(1);
        author2.setIdBook(1);
        author2.setFirstName("Edgar Allan");
        author2.setLastName("Poe");

        Author author3 = new Author();
	    author3.setId(2);
        author3.setIdBook(2);
        author3.setFirstName("Witold");
        author3.setLastName("Gombrowicz");

        Author author4 = new Author();
	    author4.setId(3);
        author4.setIdBook(3);
        author4.setFirstName("Franz");
        author4.setLastName("Kafka");
	   
        AuthorThread thread1 = new AuthorThread(author1);
        AuthorThread thread2 = new AuthorThread(author2);
        AuthorThread thread3 = new AuthorThread(author3);
        AuthorThread thread4 = new AuthorThread(author4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();


        // Instead of manually checking, I used Selenium to retrieve the authors displayed in the Swagger UI. Here, I am comparing them with the data returned directly by the GET request. 
        // I am checking two aspects: if the authors' data is the same (I stop checking at the first discrepancy to keep the console output concise) and whether both sources return the same number of authors.
        
        System.out.println("Comparison of authors displayed in the UI with data retrieved from the API:\n");

        AuthorApiClient apiClient = new AuthorApiClient();
        Author[] apiAuthors = apiClient.getAllAuthors();

        AuthorUiTest uiTest = new AuthorUiTest();
        Author[] uiAuthors = uiTest.getAuthorsUi();

        int i = 0;

        while (true) {

            if (i >= apiAuthors.length || i >= uiAuthors.length) {
                System.out.println("Reached the end of one of the author lists.");
                break;
            }

            Author apiAuthor = apiAuthors[i];
            Author uiAuthor = uiAuthors[i];

            boolean authorsAreTheSame =
                    apiAuthor.getId().equals(uiAuthor.getId())
                    && apiAuthor.getIdBook().equals(uiAuthor.getIdBook())
                    && apiAuthor.getFirstName().equals(uiAuthor.getFirstName())
                    && apiAuthor.getLastName().equals(uiAuthor.getLastName());

            if (!authorsAreTheSame) {
                System.out.println("Author data from the GET request and the UI starts to differ at index: " + i);
                break;
            }
            i++;
        }
        
        assert apiAuthors.length == uiAuthors.length : "The number of authors returned by API and UI is different.";
        System.out.println( "API and UI returned the same number of authors: " + apiAuthors.length);


	}
}
