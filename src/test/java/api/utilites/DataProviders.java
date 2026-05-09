package api.utilites;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
//Reading test data from excel file
        String path = System.getProperty("user.dir") + "/testData/userdata.xlsx";
//created XLUtilities constructor read data
        XLUtility xl = new XLUtility(path);
//to know how many rows and columns by using below two objects
        int rowNum = xl.getRowCount("Sheet1");
        int colCount = xl.getCellCount("Sheet1", 1);
//creating two dimensional array
        String apidata[][] = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {

            for (int j = 0; j < colCount; j++) {
//below get data from excel sheet and assign to two dimensional array
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
//all data will store into apidata(two dimensional array)
        return apidata;
    }

    // Single Data Provider Example
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {

        String path = System.getProperty("user.dir") + "/testData/userdata.xlsx";

        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");

        String data[] = new String[rowNum];

        for (int i = 1; i <= rowNum; i++) {

            data[i - 1] = xl.getCellData("Sheet1", i, 1);
        }

        return data;
    }
}