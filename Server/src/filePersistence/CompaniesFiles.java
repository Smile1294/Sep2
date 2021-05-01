package filePersistence;

import model.Companies;

import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

public class CompaniesFiles implements CompaniesPersistence{
    private XmlJsonParser parser;

    public CompaniesFiles() {
        this.parser = new XmlJsonParser();
    }

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
