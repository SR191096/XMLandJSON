package exercise;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class JSONDecoder {
    public static void main(String[] args) throws IOException{
        JSONParser parser = new JSONParser();
        Collection<Employee> employees = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/Employee.json"));


        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = bufferedReader.readLine();

        }
        bufferedReader.close();


        try{
            Object obj = parser.parse(sb.toString());
            JSONArray array = (JSONArray)obj;

            for(int i=0;i<array.size();i++)
            {
                JSONObject jsonObject = (JSONObject)array.get(i);
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(jsonObject.get("id").toString()));
                employee.setFirstName(jsonObject.get("firstName").toString());
                employee.setLastName(jsonObject.get("lastName").toString());
                employee.setDesignation(jsonObject.get("designation").toString());
                employees.add(employee);
            }


        }catch(ParseException pe){


            System.out.println(pe);
        }

        for(Employee emp : employees)
        {
            System.out.println(emp);
        }
    }
}
