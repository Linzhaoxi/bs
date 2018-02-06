package com.zhuying;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class excelutils
{
    private Row ExcellRow;
    private XSSFSheet ExcelWSheet;
    private XSSFWorkbook ExcelWBook;

    //Constructor to connect to the Excel with sheetname and Path
    public excelutils(String Path, String SheetName) throws Exception
    {
        try
        {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        }
        catch (Exception e)
        {
            throw (e);
        }
    }

    //This method is to set the rowcount of the excel.
    public int excel_get_rows() throws Exception
    {
        try
        {
            return ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
        }
        catch (Exception e)
        {
            throw (e);

        }
    }

    //This method is to set the columncount of the excel.
    public int excel_get_columns() throws Exception
    {
        try
        {
            ExcellRow=ExcelWSheet.getRow(0);
            return ExcellRow.getLastCellNum();
        }
        catch (Exception e)
        {
            throw (e);

        }
    }


    //This method to get the data and get the value as strings.
    public String getCellDataasstring(int RowNum, int ColNum) throws Exception
    {
        try
        {
            String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
            System.out.println("The value of CellData " + CellData);
            return CellData;
        }
        catch (Exception e)
        {
            return "Errors in Getting Cell Data";
        }
    }


    //This method to get the data and get the value as number.
    public double getCellDataasnumber(int RowNum, int ColNum) throws Exception
    {
        try
        {
            double CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
            System.out.println("The value of CellData " + CellData);
            return CellData;
        }
        catch (Exception e)
        {
            return 000.00;
        }
    }
}
