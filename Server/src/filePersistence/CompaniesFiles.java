package filePersistence;

import model.Companies;

import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

/**
 * CompaniesFiles class is implementing CompaniesPersistence for saving and loading
 */

public class CompaniesFiles implements CompaniesPersistence{
    private XmlJsonParser parser;

    /**
     * Constructor initialising instance variable
     */

    public CompaniesFiles() {
        this.parser = new XmlJsonParser();
    }

    /**
     * saving companies list into a file
     * @param companiesList list of companies that is being stored
     * @param filename name of a file that is information stored on
     */

    @Override
    public void save(Companies companiesList, String filename) {
        File file = null;
        try {
            file = parser.toJson(companiesList, filename);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Saved to file: " + file.getAbsolutePath());
    }

    /**
     * loading companies list from a file
     * @param fileName from file to load
     * @return companies list
     */

    @Override
    public Companies load(String fileName) {
        Companies companiesList = new Companies();
        try {
            companiesList = parser.fromJson(fileName, Companies.class);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Loaded from file: " + fileName);
        return companiesList;
    }
}
