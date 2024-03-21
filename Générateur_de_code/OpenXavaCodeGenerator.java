package com.yourcompany.projetbmo.run;
import java.io.*;

 
public class OpenXavaCodeGenerator {
    public static void main(String[] args) {
        generateEntities();
    }
 
    private static void generateEntities() {
        generateEntity("Utilisateur", "userId:String,email:String,password:String,solde_jeton:int");
        generateEntity("Pari", "id_p:int,userId:int,id_event:int,type_Pari:String,montant_jeton:int,resultat:String,gain:int");
        generateEntity("EvenementSportif", "id_event:int,sport:TypeSport,date:Date,result:String");
        generateEntity("Bookmaker", "id:int,nom:String");
        generateEntity("TypeSport", "oid:int,description:String");
    }
 
    private static void generateEntity(String className, String attributes) {
        String[] attributeArray = attributes.split(",");
        String content = "import javax.persistence.*;\n\n";
        content += "import java.util.Date;\n\n"; // Ajout de l'importation pour la classe Date
 
        content += "@Entity\npublic class " + className + " {\n\n";
 
        // Generate attributes
        for (String attribute : attributeArray) {
            String[] attributeParts = attribute.split(":");
            String attributeName = attributeParts[0];
            String attributeType = attributeParts[1];
            content += "    private " + attributeType + " " + attributeName + ";\n";
        }
 
        // Generate getters and setters
        for (String attribute : attributeArray) {
            String[] attributeParts = attribute.split(":");
            String attributeName = attributeParts[0];
            String attributeType = attributeParts[1];
            String capitalizedAttributeName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            content += "\n    public " + attributeType + " get" + capitalizedAttributeName + "() {\n";
            content += "        return " + attributeName + ";\n";
            content += "    }\n\n";
            content += "    public void set" + capitalizedAttributeName + "(" + attributeType + " " + attributeName + ") {\n";
            content += "        this." + attributeName + " = " + attributeName + ";\n";
            content += "    }\n\n";
        }
 
        content += "}\n";
 
        // Write content to file
        try {
            FileWriter writer = new FileWriter(className + ".java");
            writer.write(content);
            writer.close();
            System.out.println("Generated " + className + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
 
}